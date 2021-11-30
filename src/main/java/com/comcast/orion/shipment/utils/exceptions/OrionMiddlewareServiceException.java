package com.comcast.orion.shipment.utils.exceptions;

import static net.logstash.logback.marker.Markers.append;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.logstash.logback.encoder.org.apache.commons.lang.exception.ExceptionUtils;

/**
 * The Class OrionMiddlewareServiceException.
 */
public class OrionMiddlewareServiceException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());


	/** The error code. */
	private String errorCode;

	/** The error message. */
	private String errorMessage;

	/** The http status. */
	private int httpStatus;

	/**
	 * Instantiates a new orion middleware service exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the errorMessage
	 */
	public OrionMiddlewareServiceException(ErrorCode errorCode, String errorMessage) {
		super(errorCode.getErrorCode() + errorCode.getErrorDescription() + errorMessage);
		this.errorCode = errorCode.getErrorCode();
		this.errorMessage = errorCode.getErrorDescription() + errorMessage;
		this.httpStatus = errorCode.getHttpStatus();
	}
	
	/**
	 * Instantiates a new orion middleware service exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the errorMessage
	 */
	public OrionMiddlewareServiceException(ErrorCode errorCode) {
		super(errorCode.getErrorCode() + errorCode.getErrorDescription());
		this.errorCode = errorCode.getErrorCode();
		this.errorMessage = errorCode.getErrorDescription();
		this.httpStatus = errorCode.getHttpStatus();
	}

	/**
	 * Instantiates a new orion middleware service exception.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorMessage
	 *            the errorMessage
	 */
	public OrionMiddlewareServiceException(Throwable e, ErrorCode errorCode) {
		super("Fallback " + errorCode.getErrorCode() + errorCode.getErrorDescription());
		log.error("Fallback ", e);
		this.errorCode = errorCode.getErrorCode();
		this.errorMessage = errorCode.getErrorDescription();
		this.httpStatus = errorCode.getHttpStatus();

		//jsonlogging change for logging fallback exception in variable "exception", and adding an event field "isFallBack" to record fallback
        log.error(append("isFallBack",true).and(append("exception",ExceptionUtils.getStackTrace(e))),"Fallback exception");
   
	}
	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	public int getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

}