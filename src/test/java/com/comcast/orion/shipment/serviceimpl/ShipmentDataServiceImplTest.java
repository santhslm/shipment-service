package com.comcast.orion.shipment.serviceimpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.comcast.orion.shipment.controller.ShipmentController;
import com.comcast.orion.shipment.domain.DeviceDetail;
import com.comcast.orion.shipment.domain.TDSRetrieveDeviceDetails;
import com.comcast.orion.shipment.integration.ShipmentServiceGateway;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.LineItem;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.ShippingAddress;
import com.comcast.orion.shipment.omw.request.ShippingOptions;
import com.comcast.orion.shipment.omw.response.ShipmentOrderResponse;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;

@RunWith(MockitoJUnitRunner.class)
public class ShipmentDataServiceImplTest {

	@InjectMocks
	ShipmentController controller;
	
	ShipmentDataServiceImpl dataServiceImpl;
	
	@Mock
	HttpServletResponse httpServletResponse;
	
	@Mock
	HttpServletRequest httpServletrequest;
	
	@Mock
	ShipmentServiceGateway gateway;
	
	@InjectMocks
	ShipmentDataServiceImpl impl;
	
	private ShipmentOrderRequest orderRequest;
	
	private ShipmentOrderResponse orderResponse;
	
	@Mock
    RestTemplate shipmentDataRestTemplateTest;
	
	@Value("${service.shipmentData.retriveDeviceUri}")
	private String retriveDeviceUri;
	
	private ResponseEntity<TDSRetrieveDeviceDetails> responseEntity;
	
	@Test
	public void testRetriveDeviceDetails() throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException{
		TDSRetrieveDeviceDetails tDSRetrieveDeviceDetails = new TDSRetrieveDeviceDetails();
		DeviceDetail detail = new DeviceDetail();
		List<DeviceDetail> deviceDetail = new ArrayList<DeviceDetail>();
		org.springframework.test.util.ReflectionTestUtils.setField(impl,
				"retriveDeviceUri",
				"/shipmentdata/v1/retrieveDeviceDetails");
		String trackingId = "4585546";
		detail.setDeviceMake("MM4845");
		detail.setDeviceModel("DFGG");
		detail.setDeviceType("MP");
		detail.setDeviceName("dkdhjd");
		detail.setQuantity(1221);
		deviceDetail.add(detail);
		tDSRetrieveDeviceDetails.setDeviceDetail(deviceDetail);
		Map<String, Object> reqParamMap = new HashMap<>();
		reqParamMap.put("trackingId", trackingId);
		Mockito.when(shipmentDataRestTemplateTest.exchange(Matchers.anyString(),
			    Matchers.any(HttpMethod.class),
			    Matchers.any(HttpEntity.class),
			    eq(TDSRetrieveDeviceDetails.class), (Object[]) Matchers.anyVararg()))
		.thenReturn(responseEntity);
		tDSRetrieveDeviceDetails = impl.retriveDeviceDetails(tDSRetrieveDeviceDetails, trackingId);
		assertNotNull(tDSRetrieveDeviceDetails);
	}
}
