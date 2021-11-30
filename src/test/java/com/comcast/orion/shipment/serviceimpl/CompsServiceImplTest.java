//package com.comcast.orion.shipment.serviceimpl;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.eq;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Matchers;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import com.comcast.orion.shipment.comps.request.ComPSCreateShipmentRequest;
//import com.comcast.orion.shipment.comps.request.CreateOrderAttr;
//import com.comcast.orion.shipment.comps.request.LineItem;
//import com.comcast.orion.shipment.comps.request.LineItems;
//import com.comcast.orion.shipment.comps.request.ShippingAddress;
//import com.comcast.orion.shipment.comps.request.ShippingOptions;
//import com.comcast.orion.shipment.comps.response.ComPSCreateShipmentSuccessResponse;
//import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
//import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CompsServiceImplTest {
//
//	@InjectMocks
//	CompsServiceImpl impl;
//	
//	@Mock
//    RestTemplate compsRestTemplate;
//	
//	ResponseEntity<ComPSCreateShipmentSuccessResponse> responseEntity;
//	
//	@Test
//	public void testCreateOrder() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException{
//		
//		ComPSCreateShipmentSuccessResponse compresponse = new ComPSCreateShipmentSuccessResponse();
//		ComPSCreateShipmentRequest comPSCreateShipmentRequest = new ComPSCreateShipmentRequest();
//		CreateOrderAttr createOrderAttr = new CreateOrderAttr();
//		LineItems lineItems = new LineItems();
//		List<LineItem> lineItem = new ArrayList<LineItem>();
//		LineItem e = new LineItem();
//		e.setDevice("AAA");
//		e.setFirstName("TOM");
//		e.setLastName("TOM");
//		lineItem.add(e);
//		lineItems.setLineItem(lineItem);
//		createOrderAttr.setLineItems(lineItems);
//		createOrderAttr.setPurchaseOrderNumber("545446");
//		ShippingAddress shippingAddress = new ShippingAddress();
//		shippingAddress.setPhone("9966996699");
//		shippingAddress.setFirstName("DAVID");
//		shippingAddress.setLastName("JOY");
//		shippingAddress.setCompany("BOTT");
//		createOrderAttr.setShippingAddress(shippingAddress );
//		ShippingOptions shippingOptions = new ShippingOptions();
//		shippingOptions.setMethod("Online");
//		shippingOptions.setShipper("S1254PB");
//		shippingOptions.setSignature(true);
//		createOrderAttr.setShippingOptions(shippingOptions );
//		comPSCreateShipmentRequest.setCreateOrderAttr(createOrderAttr );
//		Mockito.when(compsRestTemplate.exchange(Matchers.anyString(),
//			    Matchers.any(HttpMethod.class),
//			    Matchers.any(HttpEntity.class),
//			    eq(ComPSCreateShipmentSuccessResponse.class), (Object[]) Matchers.anyVararg()))
//		.thenReturn(responseEntity);
//		compresponse = impl.createOrder("1254588", "Account", comPSCreateShipmentRequest);
//		assertNotNull(compresponse);
//	}
//		
////	@Test(expected = OrionMiddlewareServiceException.class)
////	public void testCreateOrdererror() throws OrionMiddlewareServiceException, JsonParseException, JsonMappingException, IOException, OrionMiddlewareDownstreamException {
////		HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
////		Mockito.when(compsRestTemplate.exchange(Matchers.anyString(),
////			    Matchers.any(HttpMethod.class),
////			    Matchers.any(HttpEntity.class),
////			    eq(ComPSCreateShipmentSuccessResponse.class), (Object[]) Matchers.anyVararg()))
////		.thenThrow(ex);
////		HttpMethod httpMethod=null;
////		ParameterizedTypeReference responseParamType = null;
////		Object requestBody = null;
////		HttpHeaders headers = new HttpHeaders();
////		headers.set("ExternalTransactionId", "968999999999");
////		headers.set("ProductType", "555545");
////		Map<String, Object> reqParamMap = null;
////		Map<String, Object> queryParamMap = null;
////		Object response = impl.invokeCompsService("2121212", reqParamMap, queryParamMap, httpMethod, responseParamType , requestBody , headers);
////		
////	}
//	
//}
