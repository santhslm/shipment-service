package com.comcast.orion.shipment.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.comcast.orion.shipment.integration.ShipmentServiceGateway;
import com.comcast.orion.shipment.omw.request.CancelShipmentOrder;
import com.comcast.orion.shipment.omw.request.CancelShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.LineItem;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.ShippingAddress;
import com.comcast.orion.shipment.omw.request.ShippingOptions;
import com.comcast.orion.shipment.omw.response.CancelShipmentOrderDetails;
import com.comcast.orion.shipment.omw.response.CancelShipmentOrderResponse;
import com.comcast.orion.shipment.omw.response.OmwOnpResponse;
import com.comcast.orion.shipment.omw.response.ShipmentOrderResponse;
import com.comcast.orion.shipment.serviceimpl.ShipmentDataServiceImpl;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

@RunWith(MockitoJUnitRunner.class)
public class ShipmentControllerTest {

	@InjectMocks
	ShipmentController controller;

	@Mock
	ShipmentDataServiceImpl dataServiceImpl;

	@Mock
	HttpServletResponse httpServletResponse;

	@Mock
	HttpServletRequest httpServletrequest;
	@Mock
	ShipmentServiceGateway gateway;

	ShipmentOrderRequest orderRequest;

	ShipmentOrderResponse orderResponse;

	CancelShipmentOrderRequest cancelShipmentOrderRequest;

	CancelShipmentOrderResponse omwresponse;
	
	CancelShipmentOrderDetails cancelShipmentOrderDetails;
	List<CancelShipmentOrderDetails> cancelorderDetails;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		orderRequest = new ShipmentOrderRequest();
		LineItem item = new LineItem();
		List<LineItem> items = new ArrayList<LineItem>();
		ShippingAddress address = new ShippingAddress();
		CreateShipmentOrder order = new CreateShipmentOrder();
		List<CreateShipmentOrder> list = new ArrayList<CreateShipmentOrder>();
		ShippingOptions shippingOptions = new ShippingOptions();
		shippingOptions.setSignatureRequired(true);
		item.setDeviceMake("Polycom");
		item.setDeviceModel("IP Phone");
		item.setDeviceType("VVX 310");
		item.setResourceId("12345688");
		items.add(item);
		address.setCompany("PCF");
		address.setFirstName("Hello");
		address.setLastName("World");
		address.setPhone("9871541665");
		order.setLineItems(items);
		order.setPurchaseOrderNumber("ORION-PO-123");
		order.setShippingAddress(address);
		order.setShippingOptions(shippingOptions);
		order.setSpecialInstructions("Please ship ASAP");
		list.add(order);
		orderRequest.setCreateShipmentOrder(list);
		List<CancelShipmentOrder> cancelShipmentOrderlist = new ArrayList();
		CancelShipmentOrder cancelShipmentOrder = new CancelShipmentOrder();
		cancelShipmentOrder.setOrderNumber("123");
		cancelShipmentOrder.setProductType("BVE");
		cancelShipmentOrder.setShipmentSystem("Netx");
		cancelShipmentOrderlist.add(cancelShipmentOrder);
		cancelShipmentOrderRequest = new CancelShipmentOrderRequest();
		cancelShipmentOrderRequest.setCancelShipmentOrder(cancelShipmentOrderlist);
		cancelShipmentOrderDetails = new CancelShipmentOrderDetails();
		cancelorderDetails = new ArrayList();
		cancelShipmentOrderDetails.setStatus("FAILURE");
		cancelorderDetails.add(cancelShipmentOrderDetails);
		omwresponse = new CancelShipmentOrderResponse();
		omwresponse.setCancelShipmentOrderResponse(cancelorderDetails);

	}

	@Test
	public void createTest() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		// OmwOnpResponse response=new OmwOnpResponse();
		// Mockito.when(gateway.createOrder(any(), any(), any())).thenReturn(response);
		ResponseEntity<OmwOnpResponse> response1 = controller.createShipment(httpServletrequest, orderRequest, "567578",
				"S23457", httpServletResponse);
		controller.createShipment(httpServletrequest, orderRequest, "567578", "S23457", httpServletResponse);
		assertNotNull("Verify", response1);

	}

	@Test
	public void cancelTest() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		// OmwOnpResponse response=new OmwOnpResponse();
		// Mockito.when(gateway.createOrder(any(), any(), any())).thenReturn(response);
		ResponseEntity<Object> response1 = controller.cancelShipment(httpServletrequest, cancelShipmentOrderRequest,
				"112233", "Netx", httpServletResponse);
		assertNotNull("Verify", response1);
	}
	
	@Test
	public void cancelBusinessErrorTest() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		// OmwOnpResponse response=new OmwOnpResponse();
		// Mockito.when(gateway.createOrder(any(), any(), any())).thenReturn(response);
		//Mockito.when(gateway.cancelOrder("123", "Netx", "2233", cancelShipmentOrderRequest)).thenReturn(omwresponse);
		ResponseEntity<Object> response1 = controller.cancelShipment(httpServletrequest, cancelShipmentOrderRequest,
				"112233", "Netx", httpServletResponse);
		assertNotNull("Verify", response1);
	}

}
