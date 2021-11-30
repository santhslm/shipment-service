package com.comcast.orion.shipment.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConstantTest {

	@InjectMocks
	Constants constants;
	
	@Test
	public void test()
	{
		assertEquals("success",constants.SUCCESS_STR);
	}
}
