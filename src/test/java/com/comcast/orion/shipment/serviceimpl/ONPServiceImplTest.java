package com.comcast.orion.shipment.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.comcast.orion.shipment.onp.OnpCallbackResponse;
import com.comcast.orion.shipment.onp.ShipmentStatusNotification;
import com.comcast.orion.shipment.onp.Transaction;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

@RunWith(MockitoJUnitRunner.class)
public class ONPServiceImplTest {
	
	@InjectMocks
	ONPServiceImpl impl;
	
	@Mock
    RestTemplate onpRestTemplateTest;

	@Test
	public void testinvokeOnp() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException{
		OnpCallbackResponse response = new OnpCallbackResponse();
		ResponseEntity<OnpCallbackResponse> responseEntity = null;
		ShipmentStatusNotification shipmentStatusNotification = new ShipmentStatusNotification();
		shipmentStatusNotification.setStatus("Success");
		shipmentStatusNotification.setOrderStatus("RRT545");
		shipmentStatusNotification.setOrderNumber("121222");
		Transaction transaction =  new Transaction();
		transaction.setExternalTransactionId("124545454");
		transaction.setPurchaseOrderNumber("SS45454");
		shipmentStatusNotification.setTransaction(transaction );
		Mockito.when(onpRestTemplateTest.exchange(Matchers.anyString(),
			    Matchers.any(HttpMethod.class),
			    Matchers.any(HttpEntity.class),
			    eq(OnpCallbackResponse.class), (Object[]) Matchers.anyVararg()))
		.thenReturn(responseEntity);
		response = impl.invokeOnp("1156561", "DDDD", "DDD454", "Success", "Open", shipmentStatusNotification);
		assertNull(response);
	}
}
