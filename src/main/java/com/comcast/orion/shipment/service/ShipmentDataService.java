package com.comcast.orion.shipment.service;

import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.domain.TDSRetrieveDeviceDetails;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

public interface ShipmentDataService {

	public TDSRetrieveDeviceDetails retriveDeviceDetails(TDSRetrieveDeviceDetails tDSRetrieveDeviceDetails,
			String trackingId) throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

	public String persistShipmentOrder(TDSShipmentOrderDetails tDSPostPersistShipmentOrder, String trackingId,
			String source) throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

	public TDSShipmentOrderDetails getOrderDetails(String orderNumber, String trackingId)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

	public String updateShipmentOrder(TDSShipmentOrderDetails tDSPostPersistShipmentOrderRequest, String trackingId,
			String source) throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

}
