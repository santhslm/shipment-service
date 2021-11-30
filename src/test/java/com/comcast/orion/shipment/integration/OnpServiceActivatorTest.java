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
import com.comcast.orion.shipment.domain.ShipmentOrder;
import com.comcast.orion.shipment.domain.ShipmentOrderDetail;
import com.comcast.orion.shipment.domain.ShippingOptions.Method;
import com.comcast.orion.shipment.domain.ShippingOptions.Shipper;
import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.onp.ShipmentStatusNotification;
import com.comcast.orion.shipment.onp.Transaction;
import com.comcast.orion.shipment.service.ONPService;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

@RunWith(MockitoJUnitRunner.class)
public class OnpServiceActivatorTest {

	@InjectMocks
	OnpServiceActivator onpServiceActivator;
	
	@Mock
	ONPService oNPService;
	
	@Test
	public void testsendNotification() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		ShipmentStatusNotification shipmentStatusNotification = new ShipmentStatusNotification();
		shipmentStatusNotification.setStatus("Success");
		shipmentStatusNotification.setOrderStatus("RRT545");
		shipmentStatusNotification.setOrderNumber("121222");
		Transaction transaction =  new Transaction();
		transaction.setExternalTransactionId("124545454");
		transaction.setPurchaseOrderNumber("SS45454");
		shipmentStatusNotification.setTransaction(transaction );
		Mockito.when(oNPService.invokeOnp("4454545", "45455554", "5545","ACCS","INMP",shipmentStatusNotification)).thenReturn(null);
		onpServiceActivator.sendNotification("4454545", "45455554", "5545", "ACCS", "INMP", shipmentStatusNotification);
	}
	
	
	@Test(expected = OrionMiddlewareDownstreamException.class)
	public void testsendNotificationfallback() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		onpServiceActivator.sendNotificationFallBack("4454545", "45455554", "5545", "ACCS", "INMP", null);
	}
	
	
	@Test
	public void testcConstructSendNotificationObject() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		CreateOrderResponse compsResponse = new CreateOrderResponse();
		compsResponse.setVendorOrderId("11554545");
		compsResponse.setOrderStatus("Completed");
		com.comcast.orion.shipment.comps.response.Transaction transaction = new com.comcast.orion.shipment.comps.response.Transaction();
		transaction.setExternalTransactionId("2454");
		transaction.setTransactionNumber("545444");
		compsResponse.setTransaction(transaction );
		com.comcast.orion.shipment.domain.ShippingOptions shippingOptions = new com.comcast.orion.shipment.domain.ShippingOptions();
		TDSShipmentOrderDetails tdsres = new TDSShipmentOrderDetails();
		TDSShipmentOrderDetails tdsresss = new TDSShipmentOrderDetails();
		ShipmentOrder shipmentOrder = new ShipmentOrder();
		shipmentOrder.setCompsOrderNumber("CMPS115");
		ShipmentOrderDetail shipmentOrderDetail = new ShipmentOrderDetail();
	    Shipper shipper = Shipper.UPS;
		shippingOptions.setShipper(shipper );
		Method method = Method._2_DAY;
		shippingOptions.setMethod(method);
		shipmentOrderDetail.setShippingOptions(shippingOptions);
		shipmentOrder.setShipmentOrderDetail(shipmentOrderDetail );
		tdsres.setShipmentOrder(shipmentOrder );
		tdsresss.setShipmentOrder(shipmentOrder);
		onpServiceActivator.constructSendNotificationObject(compsResponse, tdsresss, tdsres);
	}
	
	@Test
	public void tesConstructONPCallbackRequest() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		ShipmentOrderRequest shipmentOrderRequest = new ShipmentOrderRequest();
		CreateShipmentOrder createShipmentOrder = new CreateShipmentOrder();
		CreateOrderResponse compsResponse = new CreateOrderResponse();
		compsResponse.setOrderNumber("A114114");
		compsResponse.setOrderStatus("Completed");
		createShipmentOrder.setPurchaseOrderNumber("P445");
		List<CreateShipmentOrder> createShipmentOrders = new ArrayList<CreateShipmentOrder>();
		createShipmentOrders.add(createShipmentOrder);
		shipmentOrderRequest.setCreateShipmentOrder(createShipmentOrders );
		ErrorCode errorCode =ErrorCode.AUTHORIZATION_ERROR;
		OrionMiddlewareServiceException orionexception = new OrionMiddlewareServiceException(errorCode );
		onpServiceActivator.constructONPRequest("74878", shipmentOrderRequest, compsResponse, orionexception);
	}
}
