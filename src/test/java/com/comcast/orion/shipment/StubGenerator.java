package com.comcast.orion.shipment;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.comcast.orion.shipment.controller.ShipmentController;
import com.comcast.orion.shipment.springContract.ContractsConfig;
import com.comcast.orion.shipment.springContract.Tescase;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class StubGenerator {

	@Autowired
	ShipmentController shipmentController;

	@Autowired
	RestTemplate downstreamRestTemplate;

	@Autowired
	ContractsConfig contractsConfig;

	@Autowired
	private WebApplicationContext context;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private int count = 1;

	private String requestContentType = "";
	private String requestContentValue = "";
	private String responseContentType = "";
	private String responseContentValue = "";
	private Map<String, StringValuePattern> queryParameters;
	private Resource responseJson;
	private Resource requestJson;
	private MappingBuilder mockReq;
	private ResponseDefinitionBuilder mockResp;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(7007).httpsPort(8543));

	@Before
	public void setup() throws Exception {

		LOGGER.info("******************************Inside setup()   **********************************: ");
		RestAssuredMockMvc.standaloneSetup(shipmentController);
		RestAssuredMockMvc.standaloneSetup(downstreamRestTemplate);
		RestAssuredMockMvc.webAppContextSetup(context);

		contractsConfig.getTescases().stream().forEach(testCase -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (testCase.getMethodType().trim().equalsIgnoreCase("GET")) {
				mockReq = get(urlPathMatching(testCase.getRequestContents().getPath()));
			} else if (testCase.getMethodType().trim().equalsIgnoreCase("PATCH")) {
				LOGGER.info("***inside Patch Operation***** ");
				mockReq = patch(urlPathMatching(testCase.getRequestContents().getPath()));
				setRequestBodyForMock(testCase);
			} else if (testCase.getMethodType().trim().equalsIgnoreCase("POST")) {
				LOGGER.info("***inside POST Operation***** ");
				mockReq = post(urlPathMatching(testCase.getRequestContents().getPath()));
				setRequestBodyForMock(testCase);
			} else if (testCase.getMethodType().trim().equalsIgnoreCase("DELETE")) {
				LOGGER.info("***inside DELETE Operation***** ");
				mockReq = delete(urlPathMatching(testCase.getRequestContents().getPath()));
				setRequestBodyForMock(testCase);
			} else if (testCase.getMethodType().trim().equalsIgnoreCase("PUT")) {
				LOGGER.info("***inside PUT Operation***** ");
				mockReq = put(urlPathMatching(testCase.getRequestContents().getPath()));
				setRequestBodyForMock(testCase);
			}

			testCase.getRequestContents().getMatchParameters().forEach((k, v) -> {
				if (!k.equalsIgnoreCase("NA") && !v.equalsIgnoreCase("NA")) {
					queryParameters = new HashMap<>();
					queryParameters.put(k, equalTo(v));
					mockReq.withQueryParams(queryParameters);
				}
			});
			if (testCase.getRequestContents().getRequestHeaders() != null) {
				testCase.getRequestContents().getRequestHeaders().forEach((k, v) -> {
					if (k != null && v != null) {
						requestContentType = k;
						requestContentValue = v;
						mockReq.withHeader(k, containing(v));
					}
				});
			}
			if (testCase.getReponseContents().getResponseHeaders() != null) {
				testCase.getReponseContents().getResponseHeaders().forEach((k, v) -> {
					if (k != null && v != null) {
						responseContentType = k;
						responseContentValue = v;

					}
				});
			}

			if (testCase.getReponseContents().getResponseBodyJSON() != null) {
				responseJson = new ClassPathResource(
						"contracts/downstream/" + testCase.getReponseContents().getResponseBodyJSON());
			}

			mockResp = aResponse();
			mockResp.withFixedDelay(testCase.getReponseContents().getFixedDelay());
			mockResp.withStatus(testCase.getReponseContents().getHttpStatusCode());
			mockResp.withHeader(responseContentType, responseContentValue);
			mockResp.withBody(asJson(responseJson));

			wireMockRule.stubFor(mockReq.willReturn(mockResp));

			count++;
		});

	}

	private void setRequestBodyForMock(Tescase testCase) {
		LOGGER.info("DELETE path>>>>>" + testCase.getRequestContents().getPath());
		if (testCase.getRequestContents().getRequestBodyJSON() != null) {
			requestJson = new ClassPathResource(
					"contracts/downstream/" + testCase.getRequestContents().getRequestBodyJSON());
		}

		String json = asJson(requestJson);
		JSONObject jObject = null;
		try {
			jObject = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		mockReq.withRequestBody(equalToJson(jObject.toString()));
	}

	private String asJson(Resource resource) {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
			Stream<String> lines = bufferedReader.lines();
			return lines.collect(Collectors.joining());
		} catch (Exception e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}

}