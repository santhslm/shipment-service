package com.comcast.orion.shipment.integration;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.comcast.orion.shipment.comps.request.ComPSCreateShipmentRequest;
import com.comcast.orion.shipment.comps.request.CompsCancelShipmentRequest;
import com.comcast.orion.shipment.comps.request.CreateOrderAttr;
import com.comcast.orion.shipment.comps.request.LineItem;
import com.comcast.orion.shipment.comps.request.LineItems;
import com.comcast.orion.shipment.comps.request.ShippingAddress;
import com.comcast.orion.shipment.comps.request.ShippingOptions;
import com.comcast.orion.shipment.comps.response.CancelOrderResponse;
import com.comcast.orion.shipment.comps.response.CreateOrderResponse;
import com.comcast.orion.shipment.comps.response.Status;
import com.comcast.orion.shipment.omw.request.CancelShipmentOrder;
import com.comcast.orion.shipment.omw.request.CancelShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder.ProductType;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.service.CompsService;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

@RunWith(MockitoJUnitRunner.class)
public class CompsServiceActivatorTest {

	@InjectMocks
	CompsServiceActivator activator;
	@Mock
	CompsService compsService;

	CreateOrderResponse comPSCreateShipmentSuccessResponse;
	ComPSCreateShipmentRequest comPSCreateShipmentRequest;
	ShipmentOrderRequest shipmentOrderRequest;
	CancelShipmentOrderRequest cancelShipmentOrderRequest;
	CancelShipmentOrder cancelShipmentOrder;
	CancelOrderResponse cancelOrder;
	CompsCancelShipmentRequest compsCancelShipmentRequest;

	@Before
	public void setup() {
		shipmentOrderRequest = new ShipmentOrderRequest();
		comPSCreateShipmentRequest = new ComPSCreateShipmentRequest();
		CreateOrderAttr createOrderAttr = new CreateOrderAttr();
		LineItems lineItems = new LineItems();
		List<LineItem> lineItem = new ArrayList<LineItem>();
		LineItem e = new LineItem();
		e.setDevice("AAA");
		e.setFirstName("TOM");
		e.setLastName("TOM");
		lineItem.add(e);
		lineItems.setLineItem(lineItem);
		createOrderAttr.setLineItems(lineItems);
		createOrderAttr.setPurchaseOrderNumber("545446");
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setPhone("9966996699");
		shippingAddress.setFirstName("DAVID");
		shippingAddress.setLastName("JOY");
		shippingAddress.setCompany("BOTT");
		createOrderAttr.setShippingAddress(shippingAddress);
		ShippingOptions shippingOptions = new ShippingOptions();
		shippingOptions.setMethod("Online");
		shippingOptions.setShipper("S1254PB");
		shippingOptions.setSignature(true);
		createOrderAttr.setShippingOptions(shippingOptions);
		comPSCreateShipmentRequest.setCreateOrderAttr(createOrderAttr);
		CreateShipmentOrder createShipmentOrder = new CreateShipmentOrder();
		ProductType productType = ProductType.BVE;
		createShipmentOrder.setProductType(productType);
		List<CreateShipmentOrder> createShipmentOrders = new ArrayList<CreateShipmentOrder>();
		createShipmentOrders.add(createShipmentOrder);
		shipmentOrderRequest.setCreateShipmentOrder(createShipmentOrders);

		comPSCreateShipmentSuccessResponse = new CreateOrderResponse();
		comPSCreateShipmentSuccessResponse.setOrderNumber("879879");
		com.comcast.orion.shipment.comps.response.Status status = new Status();
		List<String> erromsgs = new ArrayList();
		erromsgs.add(new String("error"));
		status.setMessages(erromsgs);
		status.setResultCode("000");
		comPSCreateShipmentSuccessResponse.setStatus(status);
		cancelShipmentOrderRequest = new CancelShipmentOrderRequest();
		cancelShipmentOrder = new CancelShipmentOrder();
		cancelShipmentOrder.setOrderNumber("123");
		List<CancelShipmentOrder> cancelShipmentOrderlist = new ArrayList();
		cancelShipmentOrderlist.add(cancelShipmentOrder);
		cancelShipmentOrderRequest.setCancelShipmentOrder(cancelShipmentOrderlist);
		cancelOrder = new CancelOrderResponse();
		cancelOrder.setStatus(status);
		compsCancelShipmentRequest = new CompsCancelShipmentRequest();
		compsCancelShipmentRequest.setOrderNumber("123");

	}

	@Test
	public void testcreateOrder() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {

		Mockito.when(compsService.createOrder("4454545", "BVE", comPSCreateShipmentRequest))
				.thenReturn(comPSCreateShipmentSuccessResponse);
		comPSCreateShipmentSuccessResponse = activator.createOrder("4454545", shipmentOrderRequest,
				comPSCreateShipmentRequest);
		assertNotNull(comPSCreateShipmentSuccessResponse);
	}

	@Test(expected = OrionMiddlewareDownstreamException.class)
	public void testcreateOrderFallback() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		comPSCreateShipmentSuccessResponse = activator.createOrderFallBack("4454545", shipmentOrderRequest,
				comPSCreateShipmentRequest);
	}

	@Test
	public void testCaneclOrder() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {

		Mockito.when(compsService.cancelOrder("123", "BVE", compsCancelShipmentRequest)).thenReturn(cancelOrder);
		CancelOrderResponse cancelOrderResponse = activator.cancelOrder("123", cancelShipmentOrderRequest);
		assertNotNull(cancelOrderResponse);
	}

	@Test(expected = OrionMiddlewareDownstreamException.class)
	public void testCaneclOrderfallback() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {

		CancelOrderResponse cancelOrderResponse = activator.cancelOrderFallBack("123", cancelShipmentOrderRequest);
	}

}
