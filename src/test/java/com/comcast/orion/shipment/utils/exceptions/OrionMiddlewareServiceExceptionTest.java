package com.comcast.orion.shipment.utils.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class OrionMiddlewareServiceExceptionTest {

	/**
	 * getErrorMessage() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetErrorMessage()
		throws Exception {
		OrionMiddlewareServiceException errorResponse = new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR_VMS, "");
		int result1 = errorResponse.getHttpStatus();
		assertEquals(590, result1);
	}
	
	/**
	 * getErrorMessage() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetErrorMessage1()
		throws Exception {
		OrionMiddlewareServiceException errorResponse = new OrionMiddlewareServiceException(ErrorCode.BUSINESS_ERROR_VMS);
		int result1 = errorResponse.getHttpStatus();
		assertEquals(590, result1);
	}


}