package com.comcast.orion.shipment.service;

import com.comcast.orion.shipment.onp.OnpCallbackResponse;
import com.comcast.orion.shipment.onp.ShipmentStatusNotification;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

public interface ONPService {

	public OnpCallbackResponse invokeOnp(String trackingId, String eventName, String source, String status,
			String notificationType, ShipmentStatusNotification shipmentStatusNotification)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

}
