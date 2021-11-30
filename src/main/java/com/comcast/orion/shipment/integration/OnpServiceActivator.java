package com.comcast.orion.shipment.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import com.comcast.orion.shipment.comps.response.CreateOrderResponse;
import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.onp.ShipmentStatusNotification;
import com.comcast.orion.shipment.onp.Transaction;
import com.comcast.orion.shipment.onp.VendorOrderDetail;
import com.comcast.orion.shipment.service.ONPService;
import com.comcast.orion.shipment.utils.Constants;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class OnpServiceActivator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ONPService oNPService;

	@HystrixCommand(groupKey = "downstreamOperationHystrix", commandKey = "downstreamOperationHystrix", fallbackMethod = "sendNotificationFallBack", ignoreExceptions = {
			HttpServerErrorException.class, OrionMiddlewareServiceException.class })
	public String sendNotification(@Header("trackingId") String trackingId, @Header("eventName") String eventName,
			@Header("source") String source, @Header("status") String status,
			@Header("notificationType") String notificationType, ShipmentStatusNotification shipmentStatusNotification)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of OnpServiceActivator::sendNotification method >> ");
		oNPService.invokeOnp(trackingId, eventName, source, status, notificationType, shipmentStatusNotification);
		logger.info("End of OnpServiceActivator::sendNotification method >> ");
		return "Success";
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix")
	public String sendNotificationFallBack(@Header("trackingId") String trackingId,
			@Header("eventName") String eventName, @Header("source") String source, @Header("status") String status,
			@Header("notificationType") String notificationType, ShipmentStatusNotification shipmentStatusNotification)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of OnpServiceActivator::sendNotificationFallBack method >> ");
		throw new OrionMiddlewareDownstreamException(ErrorCode.CONNECTIVITY_ERROR_ONP);
	}

	public ShipmentStatusNotification constructSendNotificationObject(
			@Header(name = "compsResponse", required = true) CreateOrderResponse compsResponse,
			@Header(name = "shipmentOrder", required = true) TDSShipmentOrderDetails tDSShipmentOrderDetails,
			TDSShipmentOrderDetails tdsres) throws OrionMiddlewareServiceException {
		ShipmentStatusNotification onpSuccessnotification = new ShipmentStatusNotification();
		onpSuccessnotification.setStatus(Constants.SUCCESS);
		onpSuccessnotification
				.setOrderNumber(null != compsResponse.getOrderNumber() ? compsResponse.getOrderNumber() : " ");
		onpSuccessnotification.setOrderStatus(
				null != compsResponse.getOrderStatus() ? compsResponse.getOrderStatus() : Constants.OPEN);

		Transaction transaction = new Transaction();
		transaction.setExternalTransactionId(compsResponse.getTransaction().getExternalTransactionId());
		transaction.setPurchaseOrderNumber(tdsres.getShipmentOrder().getPurchaseOrderNumber());
		onpSuccessnotification.setTransaction(transaction);

		VendorOrderDetail vendorOrderDetail = new VendorOrderDetail();
		List<VendorOrderDetail> VendorOrderDetaillist = new ArrayList();
		// tracking details not available
		// vendorOrderDetail.setShipmentTrackingDetails();

		vendorOrderDetail.setShipper(tDSShipmentOrderDetails.getShipmentOrder().getShipmentOrderDetail()
				.getShippingOptions().getShipper().value());
		vendorOrderDetail.setShippingMethod(tDSShipmentOrderDetails.getShipmentOrder().getShipmentOrderDetail()
				.getShippingOptions().getMethod().toString());
		vendorOrderDetail.setVendorOrderId(compsResponse.getVendorOrderId());
		vendorOrderDetail.setVendorOrderStatus(Constants.NEW);
		VendorOrderDetaillist.add(vendorOrderDetail);
		onpSuccessnotification.setVendorOrderDetails(VendorOrderDetaillist);

		return onpSuccessnotification;

	}

	public ShipmentStatusNotification constructONPRequest(@Header("trackingId") String trackingId,
			@Header(name = "shipmentOrderRequest", required = true) ShipmentOrderRequest shipmentOrderRequest,
			@Header(name = "compsResponse", required = false) CreateOrderResponse compsResponse,
			OrionMiddlewareServiceException orionexception) {
		ShipmentStatusNotification shipmenterror = new ShipmentStatusNotification();
		shipmenterror.setStatus("FAILURE");
		if (compsResponse != null) {
			shipmenterror.setOrderNumber(compsResponse.getOrderNumber());
			shipmenterror.setOrderStatus(compsResponse.getOrderStatus());
		} else {
			shipmenterror.setOrderNumber(" ");
			shipmenterror.setOrderStatus("Open");
		}
		if (orionexception != null) {
			List<com.comcast.orion.shipment.onp.Error> errorlist = new ArrayList();
			com.comcast.orion.shipment.onp.Error error = new com.comcast.orion.shipment.onp.Error();
			error.setCode(orionexception.getErrorCode());
			error.setMessage(orionexception.getErrorMessage());
			errorlist.add(error);
			shipmenterror.setErrors(errorlist);
		}
		Transaction transaction = new Transaction();
		transaction.setExternalTransactionId(trackingId);
		transaction
				.setPurchaseOrderNumber(shipmentOrderRequest.getCreateShipmentOrder().get(0).getPurchaseOrderNumber());
		shipmenterror.setTransaction(transaction);
		return shipmenterror;

	}
}