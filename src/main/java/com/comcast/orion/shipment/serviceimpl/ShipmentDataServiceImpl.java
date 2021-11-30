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

import com.comcast.orion.shipment.domain.TDSRetrieveDeviceDetails;
import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.service.ShipmentDataService;
import com.comcast.orion.shipment.utils.MessageConstants;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

@Component
public class ShipmentDataServiceImpl implements ShipmentDataService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${service.shipmentData.url}")
	private String url;

	@Value("${service.shipmentData.retriveDeviceUri}")
	private String retriveDeviceUri;

	@Value("${service.shipmentData.persistOrderUri}")
	private String persistOrderUri;

	@Value("${service.shipmentData.getOrderDetails}")
	private String getOrderDetails;

	@Value("${service.shipmentData.updateOrderUri}")
	private String updateOrderUri;

	@Autowired
	private RestTemplate shipmentDataRestTemplate;

	@Override
	public TDSRetrieveDeviceDetails retriveDeviceDetails(TDSRetrieveDeviceDetails tDSRetrieveDeviceDetails,
			String trackingId) throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "Shipment Data");
		logger.info("ShipmentDataServiceImpl::retriveDeviceDetails::starts");
		TDSRetrieveDeviceDetails tDSRetrieveDeviceDetailsres = new TDSRetrieveDeviceDetails();
		Map<String, Object> reqParamMap = new HashMap<>();
		reqParamMap.put("trackingId", trackingId);
		HttpHeaders headers = new HttpHeaders();
		Object response = invokeShipmentDataService(retriveDeviceUri, reqParamMap, null, HttpMethod.POST,
				new ParameterizedTypeReference<TDSRetrieveDeviceDetails>() {
				}, tDSRetrieveDeviceDetails, headers);

		if (response != null) {
			tDSRetrieveDeviceDetailsres = (TDSRetrieveDeviceDetails) response;
		} else {
			logger.error("ShipmentDataServiceImpl::retriveDeviceDetails::response -> null");
		}
		logger.info("ShipmentDataServiceImpl::retriveDeviceDetails::Ends");
		return tDSRetrieveDeviceDetailsres;
	}

	@Override
	public String persistShipmentOrder(TDSShipmentOrderDetails tDSPostPersistShipmentOrder, String trackingId,
			String source) throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "Shipment Data");
		logger.info("ShipmentDataServiceImpl::persistShipmentOrder::starts");
		String tdsresponse = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("source", source);
		headers.set("trackingId", trackingId);
		Object response = invokeShipmentDataService(persistOrderUri, null, null, HttpMethod.POST,
				new ParameterizedTypeReference<String>() {
				}, tDSPostPersistShipmentOrder, headers);

		if (response != null) {
			tdsresponse = (String) response;
		} else {
			logger.error("ShipmentDataServiceImpl::persistShipmentOrder::response -> null");
		}
		logger.info("ShipmentDataServiceImpl::persistShipmentOrder::Ends");
		return tdsresponse;
	}

	@Override
	public String updateShipmentOrder(TDSShipmentOrderDetails tDSPostPersistShipmentOrderRequest, String trackingId,
			String source) throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "Shipment Data");
		logger.info("ShipmentDataServiceImpl::persistShipmentOrder::starts");
		String tdsresponse = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("source", source);
		headers.set("trackingId", trackingId);
		Object response = invokeShipmentDataService(updateOrderUri, null, null, HttpMethod.PUT,
				new ParameterizedTypeReference<String>() {
				}, tDSPostPersistShipmentOrderRequest, headers);

		if (response != null) {
			tdsresponse = (String) response;
		} else {
			logger.error("ShipmentDataServiceImpl::persistShipmentOrder::response -> null");
		}
		logger.info("ShipmentDataServiceImpl::persistShipmentOrder::Ends");
		return tdsresponse;

	}

	@Override
	public TDSShipmentOrderDetails getOrderDetails(String orderNumber, String trackingId)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "Shipment Data");
		logger.info("ShipmentDataServiceImpl::getOrderDetails::starts");
		TDSShipmentOrderDetails tDSPostPersistShipmentOrder = new TDSShipmentOrderDetails();
		Map<String, Object> reqParamMap = new HashMap<>();
		reqParamMap.put("vendorOrderId", orderNumber);
		HttpHeaders headers = new HttpHeaders();
		headers.set("trackingId", trackingId);
		Object response = invokeShipmentDataService(getOrderDetails, null, reqParamMap, HttpMethod.GET,
				new ParameterizedTypeReference<TDSShipmentOrderDetails>() {
				}, null, headers);

		if (response != null) {
			tDSPostPersistShipmentOrder = (TDSShipmentOrderDetails) response;
		} else {
			logger.error("ShipmentDataServiceImpl::getOrderDetails::response -> null");
		}
		logger.info("ShipmentDataServiceImpl::getOrderDetails::Ends");
		return tDSPostPersistShipmentOrder;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object invokeShipmentDataService(String resourcePath, Map<String, Object> reqParamMap,
			Map<String, Object> queryParamMap, HttpMethod httpMethod, ParameterizedTypeReference responseParamType,
			Object requestBody, HttpHeaders headers)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "Shipment Data");
		logger.info("ShipmentDataServiceImpl::invokeVMSDataService::Starts");
		HttpEntity<Object> requestEntity = null;
		ResponseEntity<Object> responseEntity = null;
		Object response = null;

		headers.setContentType(MediaType.APPLICATION_JSON);
		if (requestBody == null) {
			requestEntity = new HttpEntity<>(headers);
		} else {
			requestEntity = new HttpEntity<>(requestBody, headers);
		}

		logger.info("ShipmentDataServiceImpl::invokeShipmentDataService::url {} ", url);

		String queryString = "";
		if (queryParamMap != null && queryParamMap.size() > 0) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(queryString);
			for (Entry<String, Object> entry : queryParamMap.entrySet()) {
				builder.queryParam(entry.getKey(), entry.getValue());
			}
			queryString = builder.toUriString();
			logger.info("ShipmentDataServiceImpl::invokeShipmentDataService::queryString {} ", queryString);
		}
		final String fullURL = url + resourcePath + queryString;
		logger.info("ShipmentDataServiceImpl::invokeShipmentDataService::fullURL {} ", fullURL);

		try {
			if (reqParamMap == null) {
				responseEntity = shipmentDataRestTemplate.exchange(fullURL, httpMethod, requestEntity,
						responseParamType);
			} else {
				responseEntity = shipmentDataRestTemplate.exchange(fullURL, httpMethod, requestEntity,
						responseParamType, reqParamMap);
			}
		} catch (HttpClientErrorException exception) {
			logger.error("ShipmentDataServiceImpl::invokeShipmentDataService::HttpClientErrorException >> ", exception);

			if (exception.getResponseBodyAsString().contains("404 Not Found: Requested route")) {
				logger.debug("ShipmentDataServiceImpl::invokeOnp::Requested route does not exist");
				throw new OrionMiddlewareDownstreamException(exception.getCause(),
						ErrorCode.CONNECTIVITY_ERROR_SHIPMENTDATA);
			} else if (exception.getStatusCode() != null && exception.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				logger.info("ShipmentDataServiceImpl::invokeOnp::Bad Request");
				throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
						exception.getResponseBodyAsString());
			} else if (exception.getStatusCode() != null && exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				logger.info("ShipmentDataServiceImpl::invokeOnp::No Records found");
				throw new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR_SHIPMENTDATA,
						MessageConstants.SHIPMENTDATA_ERROR_MSG);
			}

		} catch (ResourceAccessException exception) {
			logger.error("ShipmentDataServiceImpl::invokeShipmentDataService::ResourceAccessException >> ", exception);
			throw new OrionMiddlewareDownstreamException(exception.getCause(),
					ErrorCode.CONNECTIVITY_ERROR_SHIPMENTDATA);
		} catch (HttpServerErrorException exception) {
			logger.error("ShipmentDataServiceImpl::invokeShipmentDataService::HttpServerErrorException >> ", exception);
			if (exception.getStatusCode() != null && (exception.getStatusCode().equals(HttpStatus.BAD_GATEWAY)
					|| exception.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)
					|| exception.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR))) {
				throw new OrionMiddlewareDownstreamException(exception.getCause(),
						ErrorCode.CONNECTIVITY_ERROR_SHIPMENTDATA);
			}
		}

		if (responseEntity != null && responseEntity.getBody() != null) {
			response = responseEntity.getBody();
		}

		logger.info("ShipmentDataServiceImpl::invokeShipmentDataService::Ends");
		return response;
	}

}
