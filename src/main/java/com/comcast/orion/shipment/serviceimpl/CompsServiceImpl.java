package com.comcast.orion.shipment.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.comcast.orion.shipment.comps.request.ComPSCreateShipmentRequest;
import com.comcast.orion.shipment.comps.request.CompsCancelShipmentRequest;
import com.comcast.orion.shipment.comps.response.CancelOrderResponse;
import com.comcast.orion.shipment.comps.response.CreateOrderResponse;
import com.comcast.orion.shipment.comps.response.OrderDetailsResponse;
import com.comcast.orion.shipment.domain.TDSRetrieveDeviceDetails;
import com.comcast.orion.shipment.service.CompsService;
import com.comcast.orion.shipment.utils.Constants;
import com.comcast.orion.shipment.utils.MessageConstants;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

@Component
public class CompsServiceImpl implements CompsService {

	private final Logger COMPS_SERVICEIMPL_LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate compsRestTemplate;

	@Value("${service.comps.url}")
	private String url;

	@Value("${service.comps.credential}")
	private String credential;

	@Value("${service.comps.cancelOrder}")
	private String cancelOrder;

	@Value("${service.comps.createOrder}")
	private String createOrder;

	@Value("${service.comps.retrieveOrder}")
	private String retrieveOrder;

	@Override
	public CreateOrderResponse createOrder(String trackingId, String productType,
			ComPSCreateShipmentRequest comPSCreateShipmentRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {

		MDC.put("downstreamName", "COMPS");
		COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::createOrder::stats");
		CreateOrderResponse compresponse = new CreateOrderResponse();

		HttpHeaders headers = new HttpHeaders();
		headers.set("ExternalTransactionId", trackingId);
		headers.set("ProductType", productType);
		Object response = invokeCompsService(createOrder, null, null, HttpMethod.POST,
				new ParameterizedTypeReference<CreateOrderResponse>() {
				}, comPSCreateShipmentRequest, headers);

		if (response != null) {
			compresponse = (CreateOrderResponse) response;
		} else {
			COMPS_SERVICEIMPL_LOGGER.error("CompsServiceImpl::createOrder::response -> null");
		}
		COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::createOrder::Ends");
		return compresponse;
	}

	@Override
	public CancelOrderResponse cancelOrder(String trackingId, String productType,
			CompsCancelShipmentRequest compsCancelShipmentRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "COMPS");
		CancelOrderResponse compresponse = new CancelOrderResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.set("ExternalTransactionId", trackingId);
		headers.set("ProductType", productType);
		Object response = invokeCompsService(cancelOrder, null, null, HttpMethod.DELETE,
				new ParameterizedTypeReference<CancelOrderResponse>() {
				}, compsCancelShipmentRequest, headers);

		if (response != null) {
			compresponse = (CancelOrderResponse) response;
		} else {
			COMPS_SERVICEIMPL_LOGGER.error("CompsServiceImpl::createOrder::response -> null");
		}
		COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::createOrder::Ends");
		return compresponse;

	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object invokeCompsService(String resourcePath, Map<String, Object> reqParamMap,
			Map<String, Object> queryParamMap, HttpMethod httpMethod, ParameterizedTypeReference responseParamType,
			Object requestBody, HttpHeaders headers)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "COMPS");
		COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::invokeVMSDataService::Starts");
		HttpEntity<Object> requestEntity = null;
		ResponseEntity<Object> responseEntity = null;
		Object response = null;

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(Constants.AUTH, "Basic " + credential);
		if (requestBody == null) {
			requestEntity = new HttpEntity<>(headers);
		} else {
			requestEntity = new HttpEntity<>(requestBody, headers);
		}

		COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::invokeVMSDataService::url {} ", url);

		String queryString = "";
		if (queryParamMap != null && queryParamMap.size() > 0) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(queryString);
			for (Entry<String, Object> entry : queryParamMap.entrySet()) {
				builder.queryParam(entry.getKey(), entry.getValue());
			}
			queryString = builder.toUriString();
			COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::invokeVMSDataService::queryString {} ", queryString);
		}
		final String fullURL = url + resourcePath + queryString;
		COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::invokeVMSDataService::fullURL {} ", fullURL);

		try {
			if (reqParamMap == null) {
				responseEntity = compsRestTemplate.exchange(fullURL, httpMethod, requestEntity, responseParamType);
			} else {
				responseEntity = compsRestTemplate.exchange(fullURL, httpMethod, requestEntity, responseParamType,
						reqParamMap);
			}
		} catch (HttpClientErrorException exception) {
			COMPS_SERVICEIMPL_LOGGER.error("CompsServiceImpl::invokeVMSDataService::HttpClientErrorException >> ",
					exception);
			if (exception.getResponseBodyAsString().contains("404 Not Found: Requested route")) {
				COMPS_SERVICEIMPL_LOGGER.debug("CompsServiceImpl::invokeOnp::Requested route does not exist");
				throw new OrionMiddlewareDownstreamException(exception.getCause(), ErrorCode.CONNECTIVITY_ERROR_COMPS);
			} else if (exception.getStatusCode() != null && exception.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::invokeOnp::Bad Request");
				throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
						exception.getResponseBodyAsString());
			} else if (exception.getStatusCode() != null && exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::invokeOnp::No Records found");
				throw new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR_COMPS,
						MessageConstants.COMPS_ERROR_MSG);
			}

		} catch (ResourceAccessException exception) {
			COMPS_SERVICEIMPL_LOGGER.error("CompsServiceImpl::invokeVMSDataService::ResourceAccessException >> ",
					exception);
			throw new OrionMiddlewareDownstreamException(exception.getCause(), ErrorCode.CONNECTIVITY_ERROR_COMPS);
		} catch (HttpServerErrorException exception) {
			COMPS_SERVICEIMPL_LOGGER.error("CompsServiceImpl::invokeVMSDataService::HttpServerErrorException >> ",
					exception);
			if (exception.getStatusCode() != null && (exception.getStatusCode().equals(HttpStatus.BAD_GATEWAY)
					|| exception.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)
					|| exception.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR))) {
				throw new OrionMiddlewareDownstreamException(exception.getCause(), ErrorCode.CONNECTIVITY_ERROR_COMPS);
			}
		}

		if (responseEntity != null && responseEntity.getBody() != null) {
			response = responseEntity.getBody();
		}

		COMPS_SERVICEIMPL_LOGGER.info("CompsServiceImpl::invokeVMSDataService::Ends");
		return response;
	}

}
