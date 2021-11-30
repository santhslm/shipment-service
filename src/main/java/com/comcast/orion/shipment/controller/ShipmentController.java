package com.comcast.orion.shipment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.orion.shipment.integration.ShipmentServiceGateway;
import com.comcast.orion.shipment.omw.request.CancelShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.omw.response.CancelShipmentOrderResponse;
import com.comcast.orion.shipment.omw.response.OmwOnpResponse;
import com.comcast.orion.shipment.utils.Constants;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//shipment service
@RestController
@Api(value = "shipment")
@RequestMapping("/shipmentService/v1")
@Validated
public class ShipmentController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShipmentServiceGateway shipmentServiceGateway;

	@PostMapping(value = "/createShipmentOrder", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "creates the shipment order", notes = "creates the shipment order", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class),
			@ApiResponse(code = 400, message = "Bad request", response = String.class),
			@ApiResponse(code = 500, message = "Orion Internal Server Error", response = String.class),
			@ApiResponse(code = 503, message = "Service Not Reachable", response = String.class),
			@ApiResponse(code = 590, message = "Business Error from downstream", response = String.class) })
	public ResponseEntity<OmwOnpResponse> createShipment(HttpServletRequest httpServletRequest,
			@Valid @RequestBody ShipmentOrderRequest shipmentOrderRequest,
			@RequestHeader(value = "trackingId", required = false) String trackingId,
			@RequestHeader(value = "sourceSystemId", required = false) String sourceSystemId,
			HttpServletResponse httpServletResponse)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		log.info("start of  ShipmentController::createShipment>>>");
		OmwOnpResponse response = shipmentServiceGateway.createOrder(trackingId, sourceSystemId, shipmentOrderRequest);
		log.info("End of  ShipmentController::createShipment>>>");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/cancelShipmentOrder", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "cancel the shipment order", notes = "cancel the shipment order", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class),
			@ApiResponse(code = 400, message = "Bad request", response = String.class),
			@ApiResponse(code = 500, message = "Orion Internal Server Error", response = String.class),
			@ApiResponse(code = 503, message = "Service Not Reachable", response = String.class),
			@ApiResponse(code = 590, message = "Business Error from downstream", response = String.class) })
	public ResponseEntity<Object> cancelShipment(HttpServletRequest httpServletRequest,
			@Valid @RequestBody CancelShipmentOrderRequest cancelShipmentOrderRequest,
			@RequestHeader(value = "trackingId", required = false) String trackingId,
			@RequestHeader(value = "sourceSystemId", required = false) String sourceSystemId,
			HttpServletResponse httpServletResponse)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		log.info("start of  ShipmentController::createShipment>>>");
		String vendorOrderId = cancelShipmentOrderRequest.getCancelShipmentOrder().get(0).getOrderNumber();
		CancelShipmentOrderResponse response = shipmentServiceGateway.cancelOrder(trackingId, sourceSystemId,
				vendorOrderId, cancelShipmentOrderRequest);
		if (null!=response && response.getCancelShipmentOrderResponse().get(0).getStatus().equalsIgnoreCase(Constants.FAILURE)) {
			return ResponseEntity.status(ErrorCode.BUSINESS_ERROR.getHttpStatus()).body(response);
		}
		log.info("End of  ShipmentController::createShipment>>>");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
