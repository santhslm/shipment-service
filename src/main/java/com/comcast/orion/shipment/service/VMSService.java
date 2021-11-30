package com.comcast.orion.shipment.service;

import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.vms.request.BVERetrieveDeviceRequest;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;

public interface VMSService {

	public BVERetreiveDeviceResponse bveRetriveDevices(BVERetrieveDeviceRequest bVERetrieveDeviceRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException;

}
