package com.comcast.orion.shipment.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.comcast.orion.shipment.comps.request.ComPSCreateShipmentRequest;
import com.comcast.orion.shipment.comps.request.CreateOrderAttr;
import com.comcast.orion.shipment.comps.request.LineItems;
import com.comcast.orion.shipment.comps.response.CancelOrderResponse;
import com.comcast.orion.shipment.config.FetchCommonOTTConfig;
import com.comcast.orion.shipment.domain.DeviceDetail;
import com.comcast.orion.shipment.domain.TDSRetrieveDeviceDetails;
import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.omw.request.CancelShipmentOrderRequest;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.omw.response.CancelShipmentOrderDetails;
import com.comcast.orion.shipment.omw.response.CancelShipmentOrderResponse;
import com.comcast.orion.shipment.omw.response.Transaction;
import com.comcast.orion.shipment.onp.ShipmentStatusNotification;
import com.comcast.orion.shipment.utils.Constants;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.utils.mapper.ShipmentServiceMapper;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;
import com.comcast.orion.shipment.vms.response.DeviceRetrieveResponseType;

@Component
public class ShipmentServiceAggregator {

	@Value("${devices.wanupgradesupport}")
	private String wanupgradesupport;

	@Autowired
	private FetchCommonOTTConfig fetchCommonOTTConfig;

	private static final Logger logger = LoggerFactory.getLogger(ShipmentServiceAggregator.class);

	@SuppressWarnings("unchecked")
	public ComPSCreateShipmentRequest aggregateCreateOrderRequestForComps(
			@Header(name = "trackingId") String trackingId,
			@Header(name = "shipmentOrderRequest", required = true) ShipmentOrderRequest shipmentOrderRequest,
			@Header(name = "shipmentDataResponse", required = true) TDSRetrieveDeviceDetails tDSRetrieveDeviceDetails,
			@Header(name = "vmsResponse", required = true) BVERetreiveDeviceResponse bVERetreiveDeviceResponse)
			throws OrionMiddlewareServiceException {
		logger.info("ShipmentServiceAggregator::aggregateCreateOrderRequestForComps::start >> {}");
		ComPSCreateShipmentRequest compsreq = new ComPSCreateShipmentRequest();
		CreateOrderAttr createorder = new CreateOrderAttr();
		createorder = ShipmentServiceMapper.MAPPER
				.mapCreateOrderReqToCompsRequest(shipmentOrderRequest.getCreateShipmentOrder().get(0));
		createorder
				.setPurchaseOrderNumber(shipmentOrderRequest.getCreateShipmentOrder().get(0).getPurchaseOrderNumber());
		List<com.comcast.orion.shipment.comps.request.LineItem> lineitemList = new ArrayList();
		LineItems lineitems = new LineItems();
		// remove invalid devices
		List<com.comcast.orion.shipment.omw.request.LineItem> omwlineitems = shipmentOrderRequest
				.getCreateShipmentOrder().get(0).getLineItems();
		omwlineitems.removeIf(t -> ((null != t.getCurrentWANLicenseCount() ? t.getCurrentWANLicenseCount()
				: 0) > (null != t.getRequiredWANLicenseCount() ? t.getRequiredWANLicenseCount() : 0)));

		List<String> wanlist = Arrays.asList(wanupgradesupport);
		if (omwlineitems.size() != 0)
			omwlineitems.stream().forEach(t -> {
				String s = t.getDeviceMake() + "-" + t.getDeviceType() + "-" + t.getDeviceModel();
				wanlist.stream().forEach(r -> {
					String licensecount[] = r.split("-");
					String wandevice = licensecount[0].concat("-").concat(licensecount[1]).concat("-")
							.concat(licensecount[2]);
					if (s.equalsIgnoreCase(wandevice) && t.getCurrentWANLicenseCount() == null && t.getRequiredWANLicenseCount() == null ) {
						t.setRequiredWANLicenseCount(Integer.parseInt(licensecount[3]));
					}

				});

			});
		
		// mapping device name form shipment data and first name and last name from vms
		// for (LineItem lineItems : omwlineitems) {
		for (DeviceDetail deviceDetail : tDSRetrieveDeviceDetails.getDeviceDetail()) {
			com.comcast.orion.shipment.comps.request.LineItem lineitemComps = new com.comcast.orion.shipment.comps.request.LineItem();

			lineitemComps
					.setDevice(null != deviceDetail.getDeviceName() ? deviceDetail.getDeviceName().toString() : null);

			for (DeviceRetrieveResponseType device : bVERetreiveDeviceResponse.getDevices()) {
				if (device.getDevice().getArmResourceId().equalsIgnoreCase(deviceDetail.getResourceId())) {
					lineitemComps.setFirstName(device.getDevice().getFirstName());
					lineitemComps.setLastName(device.getDevice().getLastName());
				}
			}

			lineitemList.add(lineitemComps);
		}
		// }
		lineitems.setLineItem(lineitemList);
		createorder.setLineItems(lineitems);
		// US1760919 - pass orderType to comps request based on commonOTT from
		// vms
		String commonOTT = null;
		if (StringUtils.isNotBlank(bVERetreiveDeviceResponse.getCommonOTT())) {
			commonOTT = bVERetreiveDeviceResponse.getCommonOTT();
		Map<String, String> orderType = fetchCommonOTTConfig.getCommonOTT();
		for (Map.Entry<String, String> entry : orderType.entrySet()) {
			if (StringUtils.isNotBlank(entry.getKey()) && commonOTT.equalsIgnoreCase(entry.getKey())) {
				createorder.setOrderType(entry.getValue());
			}
		}
		}
		compsreq.setCreateOrderAttr(createorder);
		logger.info("ShipmentServiceAggregator::aggregateCreateOrderRequestForComps::End >> {}");
		return compsreq;
	}

	@SuppressWarnings("unchecked")
	public Object aggregateCancelOrder(List<Message> messages,
			@Header(name = "cancelOrderRequst", required = false) CancelShipmentOrderRequest cancelShipmentOrderRequest,
			@Header(name = "trackingId", required = false) String trackingId) throws OrionMiddlewareServiceException {
		logger.info("ShipmentServiceAggregator::aggregateCancelOrder::Start >> {}" + messages.size());
		CancelOrderResponse cancelOrder = new CancelOrderResponse();
		TDSShipmentOrderDetails tDSShipmentOrderDetails = new TDSShipmentOrderDetails();
		Object response = null;
		if (messages != null && !messages.isEmpty()) {
			for (Message msg : messages) {
				if (msg.getHeaders().get(Constants.SEQUENCENUMBER).toString().equals("1")) {
					cancelOrder = (CancelOrderResponse) msg.getPayload();
				} else if (msg.getHeaders().get(Constants.SEQUENCENUMBER).toString().equals("2")) {
					tDSShipmentOrderDetails = (TDSShipmentOrderDetails) msg.getPayload();
				}
			}
		}
		if (cancelOrder.getStatus().getResultCode().equalsIgnoreCase("00000")) {
			ShipmentStatusNotification shipmentStatusNotification = new ShipmentStatusNotification();
			shipmentStatusNotification
					.setOrderNumber(cancelShipmentOrderRequest.getCancelShipmentOrder().get(0).getOrderNumber());
			shipmentStatusNotification.setOrderStatus(Constants.CLOSED);
			shipmentStatusNotification.setStatus(Constants.SUCCESS);

			com.comcast.orion.shipment.onp.Transaction transaction = new com.comcast.orion.shipment.onp.Transaction();
			transaction.setExternalTransactionId(trackingId);
			transaction.setPurchaseOrderNumber(tDSShipmentOrderDetails.getShipmentOrder().getPurchaseOrderNumber());
			shipmentStatusNotification.setTransaction(transaction);
			response = shipmentStatusNotification;

		} else {
			// setting error response to amdocs
			CancelShipmentOrderResponse cancelShipmentOrderResponse = new CancelShipmentOrderResponse();
			List<CancelShipmentOrderDetails> cancelShipmentOrders = new ArrayList<>();
			CancelShipmentOrderDetails cancelShipmentOrder = new CancelShipmentOrderDetails();
			cancelShipmentOrder.setStatus(Constants.FAILURE);
			cancelShipmentOrder
					.setOrderNumber(cancelShipmentOrderRequest.getCancelShipmentOrder().get(0).getOrderNumber());

			// setting trasaction details
			Transaction transaction = new Transaction();
			transaction.setExternalTransactionId(trackingId);
			transaction.setPurchaseOrderNumber(null != tDSShipmentOrderDetails.getShipmentOrder()
					? tDSShipmentOrderDetails.getShipmentOrder().getPurchaseOrderNumber()
					: null);
			cancelShipmentOrder.setTransaction(transaction);

			// setting error messages
			List<com.comcast.orion.shipment.omw.response.Error> erros = new ArrayList();
			com.comcast.orion.shipment.omw.response.Error error = new com.comcast.orion.shipment.omw.response.Error();
			error.setCode(Constants.BUSINESS_ERROR_COMPS);
			error.setMessage(cancelOrder.getStatus().getResultCode() + ":" + cancelOrder.getStatus().getMessages());
			erros.add(error);
			cancelShipmentOrder.setErrors(erros);
			cancelShipmentOrders.add(cancelShipmentOrder);
			cancelShipmentOrderResponse.setCancelShipmentOrderResponse(cancelShipmentOrders);
			response = cancelShipmentOrderResponse;
		}
		logger.info("ShipmentServiceAggregator::aggregateCancelOrder::End >> {}");
		return response;
	}

}
