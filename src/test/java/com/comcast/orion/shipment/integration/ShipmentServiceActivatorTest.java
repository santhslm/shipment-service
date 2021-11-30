package com.comcast.orion.shipment.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

import com.comcast.orion.shipment.comps.response.CreateOrderResponse;
import com.comcast.orion.shipment.domain.ShipmentOrder;
import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

@RunWith(MockitoJUnitRunner.class)
public class ShipmentServiceActivatorTest {

	@InjectMocks
	ShipmentServiceActivator activator;

	ShipmentOrderRequest shipmentOrderRequest;
	CreateShipmentOrder createShipmentOrder;
	CreateOrderResponse compsResponse;
	TDSShipmentOrderDetails tDSShipmentOrderDetails;
	ShipmentOrder shipmentOrder;

	@Before
	public void setup() {
		shipmentOrderRequest = new ShipmentOrderRequest();
		List<CreateShipmentOrder> createShipmentOrders = new ArrayList<CreateShipmentOrder>();
		createShipmentOrder = new CreateShipmentOrder();
		createShipmentOrder.setPurchaseOrderNumber("5454544");
		createShipmentOrders.add(createShipmentOrder);
		shipmentOrderRequest.setCreateShipmentOrder(createShipmentOrders);
		compsResponse = new CreateOrderResponse();
		compsResponse.setVendorOrderId("5665565");
		tDSShipmentOrderDetails = new TDSShipmentOrderDetails();
		shipmentOrder = new ShipmentOrder();
		shipmentOrder.setVendorOrderNumber("123");
		shipmentOrder.setPurchaseOrderNumber("123");
		tDSShipmentOrderDetails.setShipmentOrder(shipmentOrder);
	}

	@Test
	public void terminateTest() {

		Throwable exception = null;
		activator.terminate(exception, "565556", shipmentOrderRequest, compsResponse);
	}

	/*
	 * @Test public void restoreMessageTest() throws
	 * OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
	 * OrionMiddlewareServiceException oexp = new
	 * OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR_100); Throwable
	 * exception = null; Message<MessagingException> message =
	 * (Message<MessagingException>) new MessagingException("213123");;
	 * MessagingException msg = new MessagingException("213123");
	 * activator.restoreMessage(message); }
	 */

	@Test
	public void cancelTerminateTest() {

		Throwable exception = new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR);
		activator.cancelTerminate(exception, "565556", tDSShipmentOrderDetails);
	}
	
	@Test
	public void cancelTerminateFailTest() {

		Throwable exception = null;
		activator.cancelTerminate(exception, "565556", tDSShipmentOrderDetails);
	}
	
	
	@Test
	public void constructErrorMsgTest() {

		Throwable exception = new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR);
		activator.constructErrorMsg(exception);
	}
	
	@Test
	public void constructErrorMsgTechnicalTest() {

		Throwable exception = null;
		activator.constructErrorMsg(exception);
	}
	
	
}
