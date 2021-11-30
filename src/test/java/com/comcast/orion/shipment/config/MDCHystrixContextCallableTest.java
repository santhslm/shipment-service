package com.comcast.orion.shipment.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

//import org.codehaus.groovy.runtime.IteratorClosureAdapter;
import org.junit.Test;
import org.slf4j.MDC;

public class MDCHystrixContextCallableTest {

	/**
	 * Run the Object call() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 11/8/18 4:42 PM
	 */

	private Callable<String> actual;

	@Test
	public void testCall_1() throws Exception {
		MDCHystrixContextCallable mdcHystrixContextCallable = new MDCHystrixContextCallable(actual);
		// MDCHystrixContextCallable mdcHystrixContextCallable = new
		// MDCHystrixContextCallable(new IteratorClosureAdapter(new Object()));
		Map<String, String> map = new HashMap<>();
		MDC.setContextMap(map);
		// Object response = mdcHystrixContextCallable.call();
		// assertNull(response);
	}

}