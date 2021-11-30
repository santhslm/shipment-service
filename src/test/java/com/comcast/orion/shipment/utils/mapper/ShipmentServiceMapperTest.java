//package com.comcast.orion.shipment.utils.mapper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mapstruct.factory.Mappers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import com.comcast.orion.shipment.controller.ShipmentController;
//import com.comcast.orion.shipment.integration.ShipmentServiceGateway;
//import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
//import com.comcast.orion.shipment.omw.request.LineItem;
//import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
//import com.comcast.orion.shipment.omw.request.ShippingAddress;
//import com.comcast.orion.shipment.omw.request.ShippingOptions;
//import com.comcast.orion.shipment.omw.response.ShipmentOrderResponse;
//import com.comcast.orion.shipment.serviceimpl.ShipmentDataServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ShipmentServiceMapperTest {
//
//	@InjectMocks
//	ShipmentController controller;
//	
//	@Mock
//	ShipmentDataServiceImpl dataServiceImpl;
//	
//	@Mock
//	HttpServletResponse httpServletResponse;
//	
//	@Mock
//	HttpServletRequest httpServletrequest;
//	
//	@Mock
//	ShipmentServiceGateway gateway;
//	
//	ShipmentServiceMapper mapper;
//	
//	private ShipmentOrderRequest orderRequest;
//	
//	private ShipmentOrderResponse orderResponse;
//	
//	@Before
//	public void setUp() throws Exception {
//		mapper = Mappers.getMapper(ShipmentServiceMapper.class);
//	}
//	
//	/**
//	 * @throws Exception
//	 */
//	@Test
//	public void setUp1() throws Exception {
//		orderRequest = new ShipmentOrderRequest();
//		LineItem item = new LineItem();
//		List<LineItem> items = new ArrayList<LineItem>();
//		ShippingAddress address = new ShippingAddress();
//		CreateShipmentOrder order = new CreateShipmentOrder();
//		List<CreateShipmentOrder> list = new ArrayList<CreateShipmentOrder>();
//		ShippingOptions shippingOptions = new ShippingOptions();		
//		shippingOptions.setSignatureRequired(true);		
//	    item.setDeviceMake("Polycom");
//	    item.setDeviceModel("IP Phone");
//	    item.setDeviceType("VVX 310");
//	    item.setResourceId("12345688");
//	    items.add(item);
//	    address.setCompany("PCF");
//	    address.setFirstName("Hello");
//	    address.setLastName("World");
//	    address.setPhone("9871541665");
//        order.setLineItems(items);
//		order.setPurchaseOrderNumber("ORION-PO-123");
//		order.setShippingAddress(address);
//		order.setShippingOptions(shippingOptions);
//		order.setSpecialInstructions("Please ship ASAP");
//		list.add(order);
//		orderRequest.setCreateShipmentOrder(list);				
//	}
//	
//	
//	
//}
