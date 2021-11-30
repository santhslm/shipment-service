package com.comcast.orion.shipment.service;

import com.comcast.orion.shipment.comps.request.ComPSCreateShipmentRequest;
import com.comcast.orion.shipment.comps.request.CompsCancelShipmentRequest;
import com.comcast.orion.shipment.comps.response.CancelOrderResponse;
import com.comcast.orion.shipment.comps.response.CreateOrderResponse;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

public interface CompsService {

	public CreateOrderResponse createOrder(String trackingId, String productType,
			ComPSCreateShipmentRequest comPSCreateShipmentRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

	public CancelOrderResponse cancelOrder(String trackingId, String productType,
			CompsCancelShipmentRequest compsCancelShipmentRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

}
