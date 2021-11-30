package com.comcast.orion.shipment.utils.exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ErrorCodeTest {

	/**
	 * String toString() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testToString()
		throws Exception {
		ErrorCode errorCode = ErrorCode.BUSINESS_ERROR_VMS;

		String result = errorCode.toString();

		assertEquals("OrionErrorCode{errorCode=BUSINESS_ERROR_VMS, errorDescription='', httpStatus='590'}", result);
	}
}