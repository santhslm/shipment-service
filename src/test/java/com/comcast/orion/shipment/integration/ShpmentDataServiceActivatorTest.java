package com.comcast.orion.shipment.integration;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.comcast.orion.shipment.comps.response.CreateOrderResponse;
import com.comcast.orion.shipment.comps.response.Status;
import com.comcast.orion.shipment.domain.DeviceDetail;
import com.comcast.orion.shipment.domain.ShipmentOrder;
import com.comcast.orion.shipment.domain.ShipmentOrderDetail;
import com.comcast.orion.shipment.domain.TDSRetrieveDeviceDetails;
import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.LineItem;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.ShippingAddress;
import com.comcast.orion.shipment.omw.request.ShippingOptions;
import com.comcast.orion.shipment.omw.request.ShippingOptions.Method;
import com.comcast.orion.shipment.omw.request.ShippingOptions.Shipper;
import com.comcast.orion.shipment.service.ShipmentDataService;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.utils.mapper.ShipmentServiceMapper;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;
import com.comcast.orion.shipment.vms.response.Device;
import com.comcast.orion.shipment.vms.response.DeviceRetrieveResponseType;

@RunWith(MockitoJUnitRunner.class)
public class ShpmentDataServiceActivatorTest {

	@InjectMocks
	ShipmentDataServiceActivator activator;

	@Mock
	ShipmentDataService shipmentDataService;

	@Test
	public void TestRetriveDeviceDetails() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
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
		TDSRetrieveDeviceDetails tDSRetrieveDeviceDetailsres = new TDSRetrieveDeviceDetails();
		Mockito.when(shipmentDataService.retriveDeviceDetails(tDSRetrieveDeviceDetails, "99999"))
				.thenReturn(tDSRetrieveDeviceDetails);
		tDSRetrieveDeviceDetailsres = activator.retriveDeviceDetails("99999", tDSRetrieveDeviceDetails);
	}

	@Test
	public void TestpersistShipmentOrder() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		TDSShipmentOrderDetails tDSPostPersistShipmentOrderRequest = new TDSShipmentOrderDetails();
		ShipmentOrder shipmentOrder = new ShipmentOrder();
		shipmentOrder.setCompsOrderNumber("444455");
		tDSPostPersistShipmentOrderRequest.setShipmentOrder(shipmentOrder);
		Mockito.when(shipmentDataService.persistShipmentOrder(tDSPostPersistShipmentOrderRequest, "445445", "OPEN"))
				.thenReturn(null);
		activator.persistShipmentOrder("445445", "OPEN", tDSPostPersistShipmentOrderRequest);
	}

	@Test
	public void TestConstructShipmentDataObjectToPersist()
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		ShipmentOrderRequest shipmentOrderRequest = new ShipmentOrderRequest();
		CreateShipmentOrder createShipmentOrder = new CreateShipmentOrder();
		createShipmentOrder.setSpecialInstructions("OPen");
		LineItem item = new LineItem();
		item.setDeviceMake("Polycom");
		item.setDeviceModel("IP Phone");
		item.setDeviceType("VVX 310");
		item.setResourceId("12345688");
		List<LineItem> items = new ArrayList<LineItem>();
		items.add(item);
		createShipmentOrder.setLineItems(items);
		createShipmentOrder.setPurchaseOrderNumber("P445");
		ShippingAddress address = new ShippingAddress();
		address.setCompany("PCF");
		address.setFirstName("Hello");
		address.setLastName("World");
		address.setPhone("9871541665");
		createShipmentOrder.setShippingAddress(address);
		ShippingOptions shippingOptions = new ShippingOptions();
		Shipper shipper = Shipper.UPS;
		shippingOptions.setShipper(shipper);
		Method method = Method._2_DAY;
		shippingOptions.setMethod(method);
		shippingOptions.setSignatureRequired(false);
		createShipmentOrder.setShippingOptions(shippingOptions);
		List<CreateShipmentOrder> createShipmentOrders = new ArrayList<CreateShipmentOrder>();
		createShipmentOrders.add(createShipmentOrder);
		shipmentOrderRequest.setCreateShipmentOrder(createShipmentOrders);

		BVERetreiveDeviceResponse vmsResponse = new BVERetreiveDeviceResponse();
		DeviceRetrieveResponseType deviceRetrieveResponseType = new DeviceRetrieveResponseType();
		Device device = new Device();
		device.setArmResourceId("455");
		deviceRetrieveResponseType.setDevice(device);
		List<DeviceRetrieveResponseType> devices = new ArrayList<DeviceRetrieveResponseType>();
		devices.add(deviceRetrieveResponseType);
		vmsResponse.setDevices(devices);

		TDSRetrieveDeviceDetails shipmentDataResponse = new TDSRetrieveDeviceDetails();
		DeviceDetail detail = new DeviceDetail();
		detail.setDeviceMake("KKO");
		List<DeviceDetail> deviceDetail = new ArrayList<DeviceDetail>();
		deviceDetail.add(detail);
		shipmentDataResponse.setDeviceDetail(deviceDetail);

		CreateOrderResponse comPSCreateShipmentSuccessResponse = new CreateOrderResponse();
		comPSCreateShipmentSuccessResponse.setOrderNumber("OOP444");
		comPSCreateShipmentSuccessResponse.setVendorOrderId("5464");
		Status status = new Status();
		List<String> messages = new ArrayList<String>();
		messages.add("SJGSj");
		status.setMessages(messages);
		comPSCreateShipmentSuccessResponse.setStatus(status);
		ShipmentOrderDetail shipmentOrderDetail = new ShipmentOrderDetail();
		shipmentOrderDetail.setSpecialInstructions("4444");
		/*Mockito.when(ShipmentServiceMapper.MAPPER.mapShipmentOrderDetailFromCreateShipment(createShipmentOrder))
				.thenReturn(shipmentOrderDetail);*/
		List<com.comcast.orion.shipment.domain.LineItem> lineItems = new ArrayList<com.comcast.orion.shipment.domain.LineItem>();
		/*Mockito.when(ShipmentServiceMapper.MAPPER.mapTDSlineItemsFromVMSLineItems(vmsResponse.getDevices()))
				.thenReturn(lineItems);*/

		activator.constructShipmentDataObjectToPersist(shipmentOrderRequest, vmsResponse, shipmentDataResponse,
				comPSCreateShipmentSuccessResponse);

	}

	@Test
	public void TestconstructShipmentDataRequest()
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
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
		List<CreateShipmentOrder> createShipmentOrders = new ArrayList<CreateShipmentOrder>();
		createShipmentOrders.add(createShipmentOrder);
		shipmentOrderRequest.setCreateShipmentOrder(createShipmentOrders);
		activator.constructShipmentDataRequest("6555566", shipmentOrderRequest);
	}

}
