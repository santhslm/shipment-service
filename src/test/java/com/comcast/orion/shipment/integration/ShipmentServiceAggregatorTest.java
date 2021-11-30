package com.comcast.orion.shipment.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.messaging.Message;

import com.comcast.orion.shipment.comps.response.CancelOrderResponse;
import com.comcast.orion.shipment.domain.DeviceDetail;
import com.comcast.orion.shipment.domain.ShipmentOrder;
import com.comcast.orion.shipment.domain.TDSRetrieveDeviceDetails;
import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.omw.request.CancelShipmentOrder;
import com.comcast.orion.shipment.omw.request.CancelShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.LineItem;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.utils.mapper.ShipmentServiceMapper;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;
import com.comcast.orion.shipment.vms.response.Device;
import com.comcast.orion.shipment.vms.response.DeviceRetrieveResponseType;
import com.comcast.orion.shipment.vms.response.DeviceStatus;

@RunWith(MockitoJUnitRunner.class)
public class ShipmentServiceAggregatorTest {

	@InjectMocks
	ShipmentServiceAggregator activator;

	@Mock
	ShipmentServiceMapper shipmentServiceMapper;

	CancelShipmentOrderRequest cancelShipmentOrderRequest;
	CancelShipmentOrder cancelShipmentOrder;
	CancelOrderResponse cancelOrderResponse;
	TDSShipmentOrderDetails tDSShipmentOrderDetails;
	ShipmentOrder shipmentOrder;

	@Before
	public void setup() {
		cancelShipmentOrderRequest = new CancelShipmentOrderRequest();
		List<CancelShipmentOrder> cancelShipmentOrders = new ArrayList<CancelShipmentOrder>();
		cancelShipmentOrder = new CancelShipmentOrder();
		cancelShipmentOrder.setOrderNumber("123");
		cancelShipmentOrder.setProductType("BVE");
		cancelShipmentOrder.setShipmentSystem("Netx");
		cancelShipmentOrders.add(cancelShipmentOrder);
		cancelShipmentOrderRequest.setCancelShipmentOrder(cancelShipmentOrders);
		tDSShipmentOrderDetails = new TDSShipmentOrderDetails();
		tDSShipmentOrderDetails.setShipmentOrder(shipmentOrder);
		shipmentOrder = new ShipmentOrder();
		shipmentOrder.setPurchaseOrderNumber("123");

	}

	@Test
	public void testaggregateCreateOrderRequestForComps() throws OrionMiddlewareServiceException {

		List<Message> messages = new ArrayList<Message>();
		ShipmentOrderRequest shipmentOrderRequest = new ShipmentOrderRequest();
		CreateShipmentOrder createShipmentOrder = new CreateShipmentOrder();
		createShipmentOrder.setPurchaseOrderNumber("5454544");
		List<CreateShipmentOrder> createShipmentOrders = new ArrayList<CreateShipmentOrder>();
		createShipmentOrders.add(createShipmentOrder);
		LineItem item = new LineItem();
		item.setDeviceMake("Polycom");
		item.setDeviceModel("IP Phone");
		item.setDeviceType("VVX 310");
		item.setResourceId("12345688");
		List<LineItem> items = new ArrayList<LineItem>();
		items.add(item);
		createShipmentOrder.setLineItems(items);
		shipmentOrderRequest.setCreateShipmentOrder(createShipmentOrders);
		TDSRetrieveDeviceDetails tDSRetrieveDeviceDetails = new TDSRetrieveDeviceDetails();
		DeviceDetail detail = new DeviceDetail();
		List<DeviceDetail> deviceDetail = new ArrayList<DeviceDetail>();
		detail.setDeviceMake("MM4845");
		detail.setDeviceModel("DFGG");
		detail.setDeviceType("MP");
		detail.setDeviceName("dkdhjd");
		detail.setQuantity(1221);
		deviceDetail.add(detail);
		tDSRetrieveDeviceDetails.setDeviceDetail(deviceDetail);
		BVERetreiveDeviceResponse bVERetreiveDeviceResponse = new BVERetreiveDeviceResponse();
		DeviceRetrieveResponseType deviceRetrieveResponseType = new DeviceRetrieveResponseType();
		Device device = new Device();
		device.setArmResourceId("AVV44");
		device.setDeviceMake("AHHH");
		device.setDeviceModel("BBH");
		deviceRetrieveResponseType.setDevice(device);
		DeviceStatus deviceStatus = DeviceStatus.SUCCESS;
		deviceRetrieveResponseType.setDeviceStatus(deviceStatus);
		List<DeviceRetrieveResponseType> devices = new ArrayList<DeviceRetrieveResponseType>();
		devices.add(deviceRetrieveResponseType);
		bVERetreiveDeviceResponse.setDevices(devices);
		Mockito.when(shipmentServiceMapper
				.mapCreateOrderReqToCompsRequest(shipmentOrderRequest.getCreateShipmentOrder().get(0)))
				.thenReturn(null);
		activator.aggregateCreateOrderRequestForComps("54545", shipmentOrderRequest, tDSRetrieveDeviceDetails,
				bVERetreiveDeviceResponse);

	}


}
