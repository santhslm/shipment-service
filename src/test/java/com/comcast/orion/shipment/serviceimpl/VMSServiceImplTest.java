package com.comcast.orion.shipment.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;

import java.util.ArrayList;
import java.util.List;

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

import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.vms.request.BVERetrieveDeviceRequest;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;

@RunWith(MockitoJUnitRunner.class)
public class VMSServiceImplTest {
	
	@InjectMocks
	VMSServiceImpl impl;
	
	@Mock
	RestTemplate vmsRestTemplateTest;
	
    ResponseEntity<BVERetreiveDeviceResponse> responseEntity;

	@Test
	public void testbveRetriveDevices() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException{
		
		BVERetrieveDeviceRequest bVERetrieveDeviceRequest = new BVERetrieveDeviceRequest();
		BVERetreiveDeviceResponse vmsresponse = new BVERetreiveDeviceResponse();
		org.springframework.test.util.ReflectionTestUtils.setField(impl,
				"bveRetriveDeviceUri",
				"/bveRetrieveDevice");
		List<String> armResourceId = new ArrayList<String>();
		armResourceId.add("1255646");
		armResourceId.add("5655666");
		bVERetrieveDeviceRequest.setArmResourceId(armResourceId );
		bVERetrieveDeviceRequest.setDesignId("111255");
		Mockito.when(vmsRestTemplateTest.exchange(Matchers.anyString(),
			    Matchers.any(HttpMethod.class),
			    Matchers.any(HttpEntity.class),
			    eq(BVERetreiveDeviceResponse.class), (Object[]) Matchers.anyVararg()))
		.thenReturn(responseEntity);
		vmsresponse = impl.bveRetriveDevices(bVERetrieveDeviceRequest);
		assertNotNull(vmsresponse);
	}
}
