package com.comcast.orion.shipment.utils.exceptions;

import org.springframework.http.HttpStatus;

/**
 * The Enum ErrorCode.
 */
public enum ErrorCode {

	CONTRACT_VALIDATION_ERROR("CONTRACT_VALIDATION_ERROR", "", HttpStatus.BAD_REQUEST.value()),
	CONNECTIVITY_ERROR("CONNECTIVITY_ERROR", "Not able to connect to downstream",HttpStatus.SERVICE_UNAVAILABLE.value()),
	CONNECTIVITY_ERROR_ONP("CONNECTIVITY_ERROR", "Not able to connect to ONP",HttpStatus.SERVICE_UNAVAILABLE.value()),
	CONNECTIVITY_ERROR_SHIPMENTDATA("CONNECTIVITY_ERROR", "Not able to connect to SHIPMENTDATA",HttpStatus.SERVICE_UNAVAILABLE.value()),
	CONNECTIVITY_ERROR_COMPS("CONNECTIVITY_ERROR", "Not able to connect to COMPS",HttpStatus.SERVICE_UNAVAILABLE.value()),
	CONNECTIVITY_ERROR_VMS("CONNECTIVITY_ERROR", "Not able to connect to VMS",HttpStatus.SERVICE_UNAVAILABLE.value()),
	TECHNICAL_ERROR("TECHNICAL_ERROR","Unable to process request. Please contact Orion Middleware Team",HttpStatus.INTERNAL_SERVER_ERROR.value()), 
	BUSINESS_ERROR_SHIPMENTDATA("BUSINESS_ERROR_SHIPMENTDATA", "",590),
	BUSINESS_ERROR_VMS("BUSINESS_ERROR_VMS", "",590),
	BUSINESS_ERROR_COMPS("BUSINESS_ERROR_COMPS", "",590),
	BUSINESS_ERROR("BUSINESS_ERROR", "",590),
	BUSINESS_ERROR_100("BUSINESS_ERROR_100", "Unable to process the operation. ",HttpStatus.INTERNAL_SERVER_ERROR.value()),
	NO_DATA_FOUND("NO_DATA_FOUND","NO_DATA_FOUND", HttpStatus.NOT_FOUND.value()),
	AUTHORIZATION_ERROR("COMPS AUTHORIZATION_ERROR", "", 590);

	/** The error code. */
	private String errorCode;

	/** The error description. */
	private String errorDescription;

	/** The http status. */
	private int httpStatus;

	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	public int getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Instantiates a new orion error code.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorDescription
	 *            the error description
	 * @param httpStatus
	 *            the http status
	 */
	ErrorCode(String errorCode, String errorDescription, int httpStatus) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.httpStatus = httpStatus;
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
	 * Gets the error description.
	 *
	 * @return the error description
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return "OrionErrorCode{" + "errorCode=" + errorCode + ", errorDescription='" + errorDescription + '\''
				+ ", httpStatus='" + httpStatus + '\'' + '}';
	}

}
