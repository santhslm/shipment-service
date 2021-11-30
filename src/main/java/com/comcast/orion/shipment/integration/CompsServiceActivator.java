package com.comcast.orion.shipment.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import com.comcast.orion.shipment.comps.request.ComPSCreateShipmentRequest;
import com.comcast.orion.shipment.comps.request.CompsCancelShipmentRequest;
import com.comcast.orion.shipment.comps.response.CancelOrderResponse;
import com.comcast.orion.shipment.comps.response.CreateOrderResponse;
import com.comcast.orion.shipment.omw.request.CancelShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.service.CompsService;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class CompsServiceActivator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CompsService compsService;

	@HystrixCommand(groupKey = "downstreamOperationHystrix", commandKey = "downstreamOperationHystrix", fallbackMethod = "createOrderFallBack", ignoreExceptions = {
			HttpServerErrorException.class, OrionMiddlewareServiceException.class })
	public CreateOrderResponse createOrder(@Header("trackingId") String trackingId,
			@Header(name = "shipmentOrderRequest", required = true) ShipmentOrderRequest shipmentOrderRequest,
			ComPSCreateShipmentRequest comPSCreateShipmentRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of CompsServiceActivator::createOrder method >> ");
		CreateOrderResponse comPSCreateShipmentSuccessResponse = compsService.createOrder(trackingId,
				shipmentOrderRequest.getCreateShipmentOrder().get(0).getProductType().toString(),
				comPSCreateShipmentRequest);
		if (null == comPSCreateShipmentSuccessResponse || null == comPSCreateShipmentSuccessResponse.getOrderNumber()) {
			String code = comPSCreateShipmentSuccessResponse.getStatus().getResultCode();
			String errorMsg = comPSCreateShipmentSuccessResponse.getStatus().getMessages().get(0);
			throw new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR_COMPS, code + ":" + errorMsg);

		}
		logger.info("End of CompsServiceActivator::createOrder method >> ");
		return comPSCreateShipmentSuccessResponse;
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix")
	public CreateOrderResponse createOrderFallBack(@Header("trackingId") String trackingId,
			@Header(name = "shipmentOrderRequest", required = true) ShipmentOrderRequest shipmentOrderRequest,
			ComPSCreateShipmentRequest comPSCreateShipmentRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of CompsServiceActivator::createOrderFallBack method >> ");
		throw new OrionMiddlewareDownstreamException(ErrorCode.CONNECTIVITY_ERROR_COMPS);
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix", commandKey = "downstreamOperationHystrix", fallbackMethod = "cancelOrderFallBack", ignoreExceptions = {
			HttpServerErrorException.class, OrionMiddlewareServiceException.class })
	public CancelOrderResponse cancelOrder(@Header("trackingId") String trackingId,
			CancelShipmentOrderRequest cancelShipmentOrderRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of createOrder::cancelOrder method >> ");
		CompsCancelShipmentRequest compsCancelShipmentRequest = new CompsCancelShipmentRequest();
		compsCancelShipmentRequest
				.setOrderNumber(cancelShipmentOrderRequest.getCancelShipmentOrder().get(0).getOrderNumber());
		CancelOrderResponse cancelOrder = compsService.cancelOrder(trackingId,
				cancelShipmentOrderRequest.getCancelShipmentOrder().get(0).getProductType(),
				compsCancelShipmentRequest);
		logger.info("End of createOrder::cancelOrder method >> ");
		return cancelOrder;
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix")
	public CancelOrderResponse cancelOrderFallBack(@Header("trackingId") String trackingId,
			CancelShipmentOrderRequest cancelShipmentOrderRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of CompsServiceActivator::cancelOrderFallBack method >> ");
		throw new OrionMiddlewareDownstreamException(ErrorCode.CONNECTIVITY_ERROR_COMPS);
	}

}
