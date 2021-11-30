package com.comcast.orion.shipment.config;

import java.util.concurrent.Callable;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

/**
 * The Class MDCHystrixConcurrencyStrategy.
 */
public class MDCHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

	/*
	 * Provides an opportunity to wrap/decorate a Callable<T> before execution.
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy#
	 * wrapCallable(java.util.concurrent.Callable)
	 */
	@Override
	public <T> Callable<T> wrapCallable(Callable<T> callable) {
		return new MDCHystrixContextCallable<>(callable);
	}
}
