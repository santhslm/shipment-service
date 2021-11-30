package com.comcast.orion.shipment.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.comcast.orion.shipment.comps.response.CreateOrderResponse;
import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.omw.response.CancelShipmentOrderDetails;
import com.comcast.orion.shipment.omw.response.CancelShipmentOrderResponse;
import com.comcast.orion.shipment.omw.response.OmwOnpResponse;
import com.comcast.orion.shipment.omw.response.Transaction;
import com.comcast.orion.shipment.service.ShipmentDataService;
import com.comcast.orion.shipment.utils.Constants;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;

@Component
public class ShipmentServiceActivator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${shipmentservice.createOrder.serviceError}")
	private String serviceError;

	@Value("${shipmentservice.createOrder.status}")
	private String serviceStatus;

	@Value("${shipmentservice.createOrder.message}")
	private String serviceMessage;

	@Autowired
	ShipmentDataService shipmentDataService;

	public Message<?> restoreMessage(Message<MessagingException> message)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		Throwable exception = message.getPayload().getCause();
		logger.info("Start of ShipmentServiceActivator::restoreMessage::" + exception.getMessage());
		Message<?> failedMessage = message.getPayload().getFailedMessage();

		if (exception instanceof OrionMiddlewareDownstreamException) {
			throw (OrionMiddlewareDownstreamException) exception;
			/*
			 * throw new OrionMiddlewareDownstreamException(
			 * com.comcast.orion.shipment.utils.exceptions.ErrorCode.CONNECTIVITY_ERROR);
			 */
		}
		logger.info("End of ShipmentServiceActivator::restoreMessage::" + exception.getMessage());
		return MessageBuilder.withPayload(exception).copyHeaders(message.getHeaders())
				.copyHeadersIfAbsent(failedMessage.getHeaders()).build();
	}

	public OmwOnpResponse terminate(@Header(name = "exceptionMsg", required = false) Throwable exception,
			@Header("trackingId") String trackingId,
			@Header(name = "shipmentOrderRequest", required = true) ShipmentOrderRequest shipmentOrderRequest,
			@Header(name = "compsResponse", required = false) CreateOrderResponse compsResponse) {

		logger.info("Start - ShipmentServiceActivator::terminate:: trackingId {}", trackingId);
		OmwOnpResponse shipmentProvisionResponse = new OmwOnpResponse();
		List<com.comcast.orion.shipment.omw.response.Error> errorlist = constructErrorMsg(exception);
		if (null != shipmentOrderRequest) {
			shipmentProvisionResponse
					.setOrderNumber(shipmentOrderRequest.getCreateShipmentOrder().get(0).getPurchaseOrderNumber());
			shipmentProvisionResponse.setOrderStatus(Constants.OPEN);
		}
		if (null != compsResponse) {
			shipmentProvisionResponse.setVendorOrderId(compsResponse.getVendorOrderId());
		}
		shipmentProvisionResponse.setStatus("SUCCESS");
		logger.info("End - ShipmentServiceActivator::terminate:: trackingId {}", trackingId);
		return shipmentProvisionResponse;
	}

	public CancelShipmentOrderResponse cancelTerminate(
			@Header(name = "exceptionMsg", required = false) Throwable exception,
			@Header("trackingId") String trackingId,
			@Header(name = "shipmentOrder", required = false) TDSShipmentOrderDetails tDSShipmentOrderDetails) {
		logger.info("ShipmentServiceActivator::cancelTerminate::start");
		CancelShipmentOrderResponse cancelShipmentOrderResponse = new CancelShipmentOrderResponse();
		CancelShipmentOrderDetails cancelShipmentOrder = new CancelShipmentOrderDetails();
		if (exception != null) {
			cancelShipmentOrder.setStatus(Constants.FAILURE);
			List<com.comcast.orion.shipment.omw.response.Error> errorlist = constructErrorMsg(exception);
			cancelShipmentOrder.setErrors(errorlist);

		} else {
			cancelShipmentOrder.setStatus(Constants.SUCCESS);
		}

		// setting trasaction details
		Transaction transaction = new Transaction();
		transaction.setExternalTransactionId(trackingId);
		if (null != tDSShipmentOrderDetails) {
			cancelShipmentOrder.setOrderNumber(null != tDSShipmentOrderDetails.getShipmentOrder()
					? tDSShipmentOrderDetails.getShipmentOrder().getVendorOrderNumber()
					: null);
			transaction.setPurchaseOrderNumber(null != tDSShipmentOrderDetails.getShipmentOrder()
					? tDSShipmentOrderDetails.getShipmentOrder().getPurchaseOrderNumber()
					: null);
		}
		cancelShipmentOrder.setTransaction(transaction);
		List<CancelShipmentOrderDetails> cancelList = new ArrayList();
		cancelList.add(cancelShipmentOrder);
		cancelShipmentOrderResponse.setCancelShipmentOrderResponse(cancelList);
		logger.info("ShipmentServiceActivator::cancelTerminate::End");
		return cancelShipmentOrderResponse;
	}

	public List<com.comcast.orion.shipment.omw.response.Error> constructErrorMsg(Throwable exception) {
		List<com.comcast.orion.shipment.omw.response.Error> errorlist = new ArrayList();
		logger.info("ShipmentServiceActivator::constructErrorMsg::Start");
		if (null != exception) {
			if (exception instanceof OrionMiddlewareServiceException) {
				OrionMiddlewareServiceException shipmentServiceAppException = (OrionMiddlewareServiceException) exception;
				com.comcast.orion.shipment.omw.response.Error error = new com.comcast.orion.shipment.omw.response.Error();
				error.setMessage(shipmentServiceAppException.getErrorMessage());
				error.setCode(serviceError);
				errorlist.add(error);
			} else {
				OrionMiddlewareServiceException shipmentServiceAppException = new OrionMiddlewareServiceException(
						ErrorCode.TECHNICAL_ERROR, exception.getMessage());
				com.comcast.orion.shipment.omw.response.Error error = new com.comcast.orion.shipment.omw.response.Error();
				error.setMessage(shipmentServiceAppException.getErrorMessage());
				error.setCode(serviceError);
				errorlist.add(error);
			}
		}
		logger.info("ShipmentServiceActivator::constructErrorMsg::End");
		return errorlist;
	}

}