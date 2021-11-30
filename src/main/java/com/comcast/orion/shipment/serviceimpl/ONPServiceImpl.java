package com.comcast.orion.shipment.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.comcast.orion.shipment.onp.OnpCallbackResponse;
import com.comcast.orion.shipment.onp.ShipmentStatusNotification;
import com.comcast.orion.shipment.service.ONPService;
import com.comcast.orion.shipment.utils.MessageConstants;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

@Service
public class ONPServiceImpl implements ONPService {

	private static final Logger ONP_SERVICEIMPL_LOGGER = LoggerFactory.getLogger(ONPServiceImpl.class);

	@Value("${service.onp.url}")
	private String url;

	@Autowired
	private RestTemplate onpRestTemplate;

	@Override
	public OnpCallbackResponse invokeOnp(String trackingId, String eventName, String source, String status,
			String notificationType, ShipmentStatusNotification shipmentStatusNotification)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		MDC.put("downstreamName", "ONP");
		ONP_SERVICEIMPL_LOGGER.info(" >> Invoked ONPServiceImpl::invokeONP ");
		OnpCallbackResponse response = null;
		ResponseEntity<OnpCallbackResponse> responseEntity = null;
		HttpEntity<?> requestEntity = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.set("trackingId", trackingId);
		headers.set("eventName", eventName);
		headers.set("source", source);
		headers.set("status", status);
		headers.set("notificationType", notificationType);
		headers.set("orderType", eventName);

		if (null != shipmentStatusNotification) {
			requestEntity = new HttpEntity<>(shipmentStatusNotification, headers);
		} else {
			requestEntity = new HttpEntity<>(headers);
		}

		try {
			responseEntity = onpRestTemplate.exchange(url, HttpMethod.POST, requestEntity, OnpCallbackResponse.class);
		} catch (HttpClientErrorException exception) {
			ONP_SERVICEIMPL_LOGGER.error("ONPServiceImpl::invokeOnp::HttpClientErrorException", exception);
			if (exception.getResponseBodyAsString().contains("404 Not Found: Requested route")) {
				ONP_SERVICEIMPL_LOGGER.debug("ONPServiceImpl::invokeOnp::Requested route does not exist");
				throw new OrionMiddlewareDownstreamException(exception.getCause(), ErrorCode.CONNECTIVITY_ERROR_ONP);
			} else if (exception.getStatusCode() != null && exception.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				ONP_SERVICEIMPL_LOGGER.info("ONPServiceImpl::invokeOnp::Bad Request");
				throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
						exception.getResponseBodyAsString());
			} else if (exception.getStatusCode() != null && exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				ONP_SERVICEIMPL_LOGGER.info("ONPServiceImpl::invokeOnp::No Records found");
				throw new OrionMiddlewareServiceException(ErrorCode.NO_DATA_FOUND, exception.getResponseBodyAsString());
			}
		} catch (ResourceAccessException exception) {
			ONP_SERVICEIMPL_LOGGER.error("ONPServiceImpl::invokeOnp::ResourceAccessException", exception);
			throw new OrionMiddlewareDownstreamException(exception.getCause(), ErrorCode.CONNECTIVITY_ERROR_ONP);
		} catch (HttpServerErrorException exception) {
			ONP_SERVICEIMPL_LOGGER.error("ONPServiceImpl::invokeOnp::HttpServerErrorException", exception);
			if (exception.getStatusCode() != null && (exception.getStatusCode().equals(HttpStatus.BAD_GATEWAY)
					|| exception.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)
					|| exception.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR))) {
				throw new OrionMiddlewareDownstreamException(exception.getCause(), ErrorCode.CONNECTIVITY_ERROR_ONP);
			}
		}

		if (responseEntity != null && responseEntity.getBody() != null) {
			response = responseEntity.getBody();
		}

		ONP_SERVICEIMPL_LOGGER.info("ONPServiceImpl::invokeOnp::Ends");
		return response;
	}

}
