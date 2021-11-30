package com.comcast.orion.shipment.utils.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.comcast.orion.shipment.domain.ErrorMessage;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * The Class ShipmentServiceExceptionHandler.
 */
@RestControllerAdvice
@RestController
public class ShipmentServiceExceptionHandler extends ResponseEntityExceptionHandler {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handle exception.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class) ResponseEntity<ErrorMessage> handleException(Exception exception) {
		log.error("ShipmentServiceExceptionHandler:Exception {}", exception);
		ErrorMessage errorMessage = mapErrorResponse(ErrorCode.TECHNICAL_ERROR.getErrorCode(),
				ErrorCode.TECHNICAL_ERROR.getErrorDescription());
		return ResponseEntity.status(ErrorCode.TECHNICAL_ERROR.getHttpStatus()).body(errorMessage);
	}

	/**
	 * Handle HttpClientErrorException.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(HttpClientErrorException.class)
	private ResponseEntity<ErrorMessage> handleException(HttpClientErrorException exception) {
		log.error("ShipmentServiceExceptionHandler:Exception {}", exception);
		ErrorMessage errorMessage = mapErrorResponse(ErrorCode.TECHNICAL_ERROR.getErrorCode(),
				ErrorCode.TECHNICAL_ERROR.getErrorDescription());
		return ResponseEntity.status(ErrorCode.TECHNICAL_ERROR.getHttpStatus()).body(errorMessage);
	}

	/**
	 * Handle OrionMiddlewareServiceException.
	 *
	 * @param exception
	 *            the exception
	 * @return the response entity
	 */
	@ExceptionHandler(OrionMiddlewareServiceException.class)
	private ResponseEntity<ErrorMessage> handleException(OrionMiddlewareServiceException exception) {
		log.error("ShipmentServiceExceptionHandler:OrionMiddlewareServiceException {}", exception);
		ErrorMessage errorMessage = mapErrorResponse(exception.getErrorCode(), exception.getErrorMessage());
		return ResponseEntity.status(exception.getHttpStatus()).body(errorMessage);
	}

	@ExceptionHandler(OrionMiddlewareDownstreamException.class)
	private ResponseEntity<ErrorMessage> handleException(OrionMiddlewareDownstreamException exception) {
		log.error("ShipmentServiceExceptionHandler:OrionMiddlewareServiceException {}", exception);
		ErrorMessage errorMessage = mapErrorResponse(exception.getErrorCode(), exception.getErrorMessage());
		return ResponseEntity.status(exception.getHttpStatus()).body(errorMessage);
	}

	/**
	 * Handle rest client exception.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<ErrorMessage> handleRestClientException(RestClientException ex) {
		log.error("ShipmentServiceExceptionHandler:handleRestClientException {}", ex);
		ErrorMessage errorMessage = mapErrorResponse(ErrorCode.CONNECTIVITY_ERROR.getErrorCode(),
				ErrorCode.CONNECTIVITY_ERROR.getErrorDescription());
		return ResponseEntity.status(ErrorCode.CONNECTIVITY_ERROR.getHttpStatus()).body(errorMessage);

	}

	/**
	 * Handle resource not found exception.
	 *
	 * @param e
	 *            the e
	 * @return the response entity
	 */
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ResponseEntity<Object> handleResourceNotFoundException(ConstraintViolationException e) {
		log.error("ShipmentServiceExceptionHandler:handleResourceNotFoundException(Path Variable Exception) : {}", e);
		ErrorMessage errorMessage = new ErrorMessage();
		List<com.comcast.orion.shipment.domain.Error> errors = new ArrayList<>();
		Set<ConstraintViolation<?>> violationSet = e.getConstraintViolations();
		for (ConstraintViolation<?> violation : violationSet) {
			com.comcast.orion.shipment.domain.Error error = new com.comcast.orion.shipment.domain.Error();
			error.setCode(ErrorCode.CONTRACT_VALIDATION_ERROR.getErrorCode());
			error.setMessage(violation.getMessage());
			errors.add(error);
		}
		errorMessage.setErrors(errors);
		return ResponseEntity.status(ErrorCode.CONTRACT_VALIDATION_ERROR.getHttpStatus()).body(errorMessage);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.method.annotation.
	 * ResponseEntityExceptionHandler#handleHttpMessageNotReadable(org.
	 * springframework.http.converter.HttpMessageNotReadableException,
	 * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
	 * org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("ShipmentServiceExceptionHandler:handleHttpMessageNotReadable(ENUM Validation Exception) : {}", ex);
		StringBuilder errorMessage = new StringBuilder();

		Throwable throwable = ex.getCause();
		JsonMappingException jsonMappingException = ((JsonMappingException) throwable);
		errorMessage.append(jsonMappingException.getOriginalMessage());

		ErrorMessage error = mapErrorResponse(ErrorCode.CONTRACT_VALIDATION_ERROR.getErrorCode(),
				errorMessage.toString());
		return ResponseEntity.status(ErrorCode.CONTRACT_VALIDATION_ERROR.getHttpStatus()).body(error);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.method.annotation.
	 * ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.
	 * springframework.web.bind.MethodArgumentNotValidException,
	 * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
	 * org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			org.springframework.web.bind.MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		log.error("ShipmentServiceExceptionHandler:handleMethodArgumentNotValid(Validation Exception) : {}", ex);
		ErrorMessage errorMessage = new ErrorMessage();
		List<com.comcast.orion.shipment.domain.Error> errors = new ArrayList<>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			com.comcast.orion.shipment.domain.Error error = new com.comcast.orion.shipment.domain.Error();
			error.setCode(ErrorCode.CONTRACT_VALIDATION_ERROR.getErrorCode());
			error.setMessage(fieldError.getField() + " : " + fieldError.getDefaultMessage());
			errors.add(error);
		}
		errorMessage.setErrors(errors);
		return ResponseEntity.status(ErrorCode.CONTRACT_VALIDATION_ERROR.getHttpStatus()).body(errorMessage);
	}

	/**
	 * Map error response.
	 *
	 * @param errorCode
	 *            the error code
	 * @param errorDetails
	 *            the error details
	 * @return the error message
	 */
	ErrorMessage mapErrorResponse(String errorCode, String errorDetails) {
		ErrorMessage errorMessage = new ErrorMessage();
		List<com.comcast.orion.shipment.domain.Error> errors = new ArrayList<>();
		com.comcast.orion.shipment.domain.Error error = new com.comcast.orion.shipment.domain.Error();
		error.setMessage(errorDetails);
		error.setCode(errorCode);
		errors.add(error);
		errorMessage.setErrors(errors);
		return errorMessage;
	}
}
