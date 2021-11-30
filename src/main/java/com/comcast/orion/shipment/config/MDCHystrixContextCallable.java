package com.comcast.orion.shipment.config;

import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * The Class MDCHystrixContextCallable.
 *
 * @param <K>
 *            the key type
 */
public class MDCHystrixContextCallable<K> implements Callable {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MDCHystrixContextCallable.class);

	/** The actual. */
	private final Callable<K> actual;

	/** The parent MDC. */
	private final Map<String, String> parentMDC;

	/**
	 * Instantiates a new MDC hystrix context callable.
	 *
	 * @param actual
	 *            the actual
	 */
	public MDCHystrixContextCallable(Callable<K> actual) {
		this.actual = actual;
		this.parentMDC = MDC.getCopyOfContextMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public K call() throws Exception {
		Map<String, String> childMDC = MDC.getCopyOfContextMap();

		try {
			if (parentMDC != null) {
				MDC.setContextMap(parentMDC);
			}
			if (LOG.isDebugEnabled()) {
				LOG.debug("parentMDC : " + parentMDC);
			}
			return actual.call();
		} finally {
			MDC.setContextMap(childMDC);
		}
	}
}