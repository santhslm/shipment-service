package com.comcast.orion.shipment.integration;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.comcast.orion.shipment.omw.request.CancelShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.omw.response.CancelShipmentOrderResponse;
import com.comcast.orion.shipment.omw.response.OmwOnpResponse;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;;

@Component
@CacheConfig(cacheNames = "configuration")
public interface ShipmentServiceGateway {

	public OmwOnpResponse createOrder(@Header(name = "trackingId", required = false) String trackingId,
			@Header(name = "sourceSystemId", required = false) String sourceSystemId,
			@Payload ShipmentOrderRequest shipmentOrderRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

	public CancelShipmentOrderResponse cancelOrder(@Header(name = "trackingId", required = false) String trackingId,
			@Header(name = "sourceSystemId", required = false) String sourceSystemId,
			@Header(name = "vendorOrderId", required = false) String vendorOrderId,
			@Payload CancelShipmentOrderRequest cancelShipmentOrderRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

}
