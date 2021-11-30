package com.comcast.orion.shipment.serviceimpl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.comcast.orion.shipment.service.VMSService;
import com.comcast.orion.shipment.utils.MessageConstants;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.vms.request.BVERetrieveDeviceRequest;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;

@Component
public class VMSServiceImpl implements VMSService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${service.vms.url}")
	private String url;

	@Value("${service.vms.bveRetriveDeviceUri}")
	private String bveRetriveDeviceUri;

	@Value("${service.vms.bveUpdateDeviceDetails}")
	private String bveUpdateDeviceDetails;

	@Autowired
	private RestTemplate vmsRestTemplate;

	@Override
	public BVERetreiveDeviceResponse bveRetriveDevices(BVERetrieveDeviceRequest bVERetrieveDeviceRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "VMS");
		logger.info("VMSServiceImpl::bveRetriveDevices::stats");
		BVERetreiveDeviceResponse vmsresponse = new BVERetreiveDeviceResponse();
		Object response = invokeVMSService(bveRetriveDeviceUri, null, null, HttpMethod.POST,
				new ParameterizedTypeReference<BVERetreiveDeviceResponse>() {
				}, bVERetrieveDeviceRequest);

		if (response != null) {
			vmsresponse = (BVERetreiveDeviceResponse) response;
		} else {
			logger.error("VMSServiceImpl::bveRetriveDevices::response -> null");
		}
		logger.info("VMSServiceImpl::bveRetriveDevices::Ends");
		return vmsresponse;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object invokeVMSService(String resourcePath, Map<String, Object> reqParamMap,
			Map<String, Object> queryParamMap, HttpMethod httpMethod, ParameterizedTypeReference responseParamType,
			Object requestBody) throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "VMS");
		logger.info("VMSServiceImpl::invokeVMSService::Starts");
		HttpEntity<Object> requestEntity = null;
		ResponseEntity<Object> responseEntity = null;
		Object response = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String trackingId = MDC.get("trackingId");
		if (StringUtils.isEmpty(trackingId)) {
			trackingId = UUID.randomUUID().toString();
		}
		headers.set("trackingId", trackingId);
		if (requestBody == null) {
			requestEntity = new HttpEntity<>(headers);
		} else {
			requestEntity = new HttpEntity<>(requestBody, headers);
		}

		logger.info("VMSServiceImpl::invokeVMSService::url {} ", url);

		String queryString = "";
		if (queryParamMap != null && queryParamMap.size() > 0) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(queryString);
			for (Entry<String, Object> entry : queryParamMap.entrySet()) {
				builder.queryParam(entry.getKey(), entry.getValue());
			}
			queryString = builder.toUriString();
			logger.info("VMSServiceImpl::invokeVMSService::queryString {} ", queryString);
		}
		final String fullURL = url + resourcePath + queryString;
		logger.info("VMSServiceImpl::invokeVMSService::fullURL {} ", fullURL);

		try {
			if (reqParamMap == null) {
				responseEntity = vmsRestTemplate.exchange(fullURL, httpMethod, requestEntity, responseParamType);
			} else {
				responseEntity = vmsRestTemplate.exchange(fullURL, httpMethod, requestEntity, responseParamType,
						reqParamMap);
			}
		} catch (HttpClientErrorException exception) {
			logger.error("VMSServiceImpl::invokeVMSService::HttpClientErrorException >> ", exception);
			if (exception.getResponseBodyAsString().contains("404 Not Found: Requested route")) {
				logger.debug("VMSServiceImpl::invokeOnp::Requested route does not exist");
				throw new OrionMiddlewareDownstreamException(exception.getCause(), ErrorCode.CONNECTIVITY_ERROR_VMS);
			} else if (exception.getStatusCode() != null && exception.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				logger.info("VMSServiceImpl::invokeOnp::Bad Request");
				throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
						exception.getResponseBodyAsString());
			} else if (exception.getStatusCode() != null) {
				logger.info("VMSServiceImpl::invokeOnp::No Records found");
				throw new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR_VMS, MessageConstants.VMS_ERROR_MSG);
			}

		} catch (ResourceAccessException exception) {
			logger.error("VMSServiceImpl::invokeVMSService::ResourceAccessException >> ", exception);
			throw new OrionMiddlewareDownstreamException(exception.getCause(), ErrorCode.CONNECTIVITY_ERROR_VMS);
		} catch (HttpServerErrorException exception) {
			logger.error("VMSServiceImpl::invokeVMSService::HttpServerErrorException >> ", exception);
			if (exception.getStatusCode() != null && (exception.getStatusCode().equals(HttpStatus.BAD_GATEWAY)
					|| exception.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)
					|| exception.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR))) {
				throw new OrionMiddlewareDownstreamException(exception.getCause(), ErrorCode.CONNECTIVITY_ERROR_VMS);
			}
		} catch (RestClientException exception) {
			throw new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR_VMS, MessageConstants.VMS_ERROR_MSG);
		}

		if (responseEntity != null && responseEntity.getBody() != null) {
			response = responseEntity.getBody();
		}

		logger.info("VMSServiceImpl::invokeVMSService::Ends");
		return response;
	}

}
