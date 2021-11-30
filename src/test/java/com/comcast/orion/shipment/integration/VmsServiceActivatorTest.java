package com.comcast.orion.shipment.integration;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.comcast.orion.shipment.omw.request.AdditionalAttribute;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.LineItem;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.service.VMSService;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.vms.request.BVERetrieveDeviceRequest;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;

@RunWith(MockitoJUnitRunner.class)
public class VmsServiceActivatorTest {

	@InjectMocks
	VmsServiceActivator activator;
	@Mock
	VMSService vMSService;

	@Test
	public void TestBveRetriveDevices() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		BVERetrieveDeviceRequest bVERetrieveDeviceRequest = new BVERetrieveDeviceRequest();
		List<String> armResourceId = new ArrayList<String>();
		armResourceId.add("1255646");
		armResourceId.add("5655666");
		bVERetrieveDeviceRequest.setArmResourceId(armResourceId);
		bVERetrieveDeviceRequest.setDesignId("111255");
		BVERetreiveDeviceResponse BVERetreiveDeviceResponse = new BVERetreiveDeviceResponse();
		Mockito.when(vMSService.bveRetriveDevices(bVERetrieveDeviceRequest)).thenReturn(BVERetreiveDeviceResponse);
		activator.bveRetriveDevices("444545", bVERetrieveDeviceRequest);
	}

	@Test(expected = OrionMiddlewareDownstreamException.class)
	public void TestBveRetriveDevicesFallback()
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		activator.bveRetriveDevicesFallBack("444545", null);
	}

	@Test
	public void TestconstructVmsRequest() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		ShipmentOrderRequest shipmentOrderRequest = new ShipmentOrderRequest();
		CreateShipmentOrder createShipmentOrder = new CreateShipmentOrder();
		LineItem item = new LineItem();
		item.setDeviceMake("Polycom");
		item.setDeviceModel("IP Phone");
		item.setDeviceType("VVX 310");
		item.setResourceId("12345688");
		List<LineItem> items = new ArrayList<LineItem>();
		items.add(item);
		createShipmentOrder.setLineItems(items);
		AdditionalAttribute attribute = new AdditionalAttribute();
		attribute.setName("OPEn");
		attribute.setValue("125555");
		List<AdditionalAttribute> additionalAttributes = new ArrayList<AdditionalAttribute>();
		additionalAttributes.add(attribute);
		createShipmentOrder.setAdditionalAttributes(additionalAttributes);
		List<CreateShipmentOrder> createShipmentOrders = new ArrayList<CreateShipmentOrder>();
		createShipmentOrders.add(createShipmentOrder);
		shipmentOrderRequest.setCreateShipmentOrder(createShipmentOrders);
		activator.constructVmsRequest("1213", shipmentOrderRequest);

	}
}
