package com.comcast.orion.shipment.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.domain.TDSRetrieveDeviceDetails;

@FeignClient("SHIPMENTDATA-SERVICE")
public interface ShipmentDataServiceFeign {

	@RequestMapping(value = "/shipmentdata/v1/retrieveDeviceDetails", method = RequestMethod.POST)
	public TDSRetrieveDeviceDetails retriveDeviceDetails(TDSRetrieveDeviceDetails tDSRetrieveDeviceDetails);

	@RequestMapping(value = "/shipmentdata/v1/persistShipmentOrder", method = RequestMethod.POST)
	public TDSShipmentOrderDetails persistShipmentOrder(TDSShipmentOrderDetails tDSPostPersistShipmentOrder);

}
