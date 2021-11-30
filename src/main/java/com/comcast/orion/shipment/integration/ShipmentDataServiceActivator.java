package com.comcast.orion.shipment.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import com.comcast.orion.shipment.comps.response.CreateOrderResponse;
import com.comcast.orion.shipment.domain.DeviceDetail;
import com.comcast.orion.shipment.domain.ShipmentOrder;
import com.comcast.orion.shipment.domain.ShipmentOrderDetail;
import com.comcast.orion.shipment.domain.TDSRetrieveDeviceDetails;
import com.comcast.orion.shipment.domain.TDSShipmentOrderDetails;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.service.ShipmentDataService;
import com.comcast.orion.shipment.utils.Constants;
import com.comcast.orion.shipment.utils.MessageConstants;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.utils.mapper.ShipmentServiceMapper;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;
import com.comcast.orion.shipment.vms.response.DeviceRetrieveResponseType;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class ShipmentDataServiceActivator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ShipmentDataService shipmentDataService;

	public boolean modified;

	@Value("${devices.wanupgradesupport}")
	private String wanupgradesupport;

	@Value("${devices.upgradesizes}")
	private Integer[] upgradesizes;

	String missedrecords = "";

	@HystrixCommand(groupKey = "downstreamOperationHystrix", commandKey = "downstreamOperationHystrix", fallbackMethod = "retriveDeviceDetailsFallBack", ignoreExceptions = {
			HttpServerErrorException.class, OrionMiddlewareServiceException.class })
	public TDSRetrieveDeviceDetails retriveDeviceDetails(@Header("trackingId") String trackingId,
			TDSRetrieveDeviceDetails tDSRetrieveDeviceDetails)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of ShipmentDataServiceActivator::retriveDeviceDetails method >> ");

		// check for duplicate resourceids
		List<String> duplicateresorceids = tDSRetrieveDeviceDetails.getDeviceDetail().stream()
				.map(DeviceDetail::getResourceId).collect(Collectors.toList());
		List<String> uniqueresourceIds = duplicateresorceids.stream().distinct().collect(Collectors.toList());
		if (duplicateresorceids.size() != uniqueresourceIds.size()) {
			throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
					"Devices should have unique resourceID");
		}

		TDSRetrieveDeviceDetails tDSRetrieveDeviceDetailsres = shipmentDataService
				.retriveDeviceDetails(tDSRetrieveDeviceDetails, trackingId);

		if (tDSRetrieveDeviceDetailsres.getDeviceDetail().size() == 0) {
			throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR, "Invalid Device Details");
		}

		// upgrade check whether all devices are present
		List<String> wanlist = Arrays.asList(wanupgradesupport);
		List<String> wanlistwithoutdefault = new ArrayList();
		missedrecords = "";
		wanlist.stream().forEach(r -> {
			String licensecount[] = r.split("-");
			String wandevice = licensecount[0].concat("-").concat(licensecount[1]).concat("-").concat(licensecount[2]);
			wanlistwithoutdefault.add(wandevice);

		});

		List<DeviceDetail> wanlists = tDSRetrieveDeviceDetailsres.getDeviceDetail().stream()
				.filter(t -> wanlistwithoutdefault
						.contains(t.getDeviceMake() + "-" + t.getDeviceType() + "-" + t.getDeviceModel()))
				.collect(Collectors.toList());

		List<String> resourceIds1 = wanlists.stream().distinct().map(DeviceDetail::getResourceId)
				.collect(Collectors.toList());
		List<String> resourceIds = resourceIds1.stream().distinct().collect(Collectors.toList());
		if (null != resourceIds && resourceIds1.size() != resourceIds.size())
			resourceIds.stream().forEach(t -> {
				List<DeviceDetail> wanlistforresourceid = wanlists.stream()
						.filter(r -> r.getResourceId().equalsIgnoreCase(t)).collect(Collectors.toList());
				int count = 0;
				Optional<DeviceDetail> obj = tDSRetrieveDeviceDetails.getDeviceDetail().stream()
						.filter(k -> k.getResourceId().equalsIgnoreCase(t)).findFirst();

				if (null != wanlistforresourceid && wanlistforresourceid.size() != 0)

					if (wanlistforresourceid.size() != calculatewanrecords(
							(obj.get() != null && obj.get().getCurrentWANLicenseCount() != null)
									? obj.get().getCurrentWANLicenseCount()
									: 0,
							(obj.get() != null && obj.get().getRequiredWANLicenseCount() != null)
									? obj.get().getRequiredWANLicenseCount()
									: 0)) {
						missedrecords += wanlistforresourceid.get(0).getResourceId() + ", ";
					}
			});

		if (!StringUtils.isEmpty(missedrecords)) {
			throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
					"Devices with resourceids " + missedrecords + "could not upgrade");
		}
		// check, does all devices has names
		List<String> resourceidsofalldevices = tDSRetrieveDeviceDetailsres.getDeviceDetail().stream()
				.map(DeviceDetail::getResourceId).collect(Collectors.toList());
		List<String> noresourceids = duplicateresorceids.stream().filter(t -> !resourceidsofalldevices.contains(t))
				.collect(Collectors.toList());
		if (noresourceids.size() != 0) {
			throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
					" Devices with resourcesids>> " + noresourceids + " are invalid");
		}

		if (tDSRetrieveDeviceDetailsres.getDeviceDetail().size() < tDSRetrieveDeviceDetails.getDeviceDetail().size()) {
			throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR, "Invalid Device Details");
		}

		for (DeviceDetail deviceDetail : tDSRetrieveDeviceDetailsres.getDeviceDetail()) {
			if (null == deviceDetail.getDeviceName())
				throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
						"Devices with Resource Id " + deviceDetail.getResourceId() + " not found");
		}
		logger.info("End of ShipmentDataServiceActivator::retriveDeviceDetails method >> ");
		return tDSRetrieveDeviceDetailsres;
	}

	private int calculatewanrecords(int curwan, int reqwam) {
		List<String> upgrades = new ArrayList(Arrays.asList(upgradesizes));
		int start = upgrades.indexOf(curwan);
		int end = upgrades.indexOf(reqwam);
		return (end - start);
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix")
	public TDSRetrieveDeviceDetails retriveDeviceDetailsFallBack(@Header("trackingId") String trackingId,
			TDSRetrieveDeviceDetails tDSRetrieveDeviceDetails)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of ShipmentDataServiceActivator::retriveDeviceDetailsFallBack method >> ");
		throw new OrionMiddlewareDownstreamException(ErrorCode.CONNECTIVITY_ERROR_SHIPMENTDATA);
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix", commandKey = "downstreamOperationHystrix", fallbackMethod = "persistShipmentOrderFallBack", ignoreExceptions = {
			HttpServerErrorException.class, OrionMiddlewareServiceException.class })
	public TDSShipmentOrderDetails persistShipmentOrder(@Header("trackingId") String trackingId,
			@Header(name = "source", defaultValue = "Amdocs") String source,
			TDSShipmentOrderDetails tDSPostPersistShipmentOrderRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of ShipmentDataServiceActivator::retriveDeviceDetails method >> ");
		if (tDSPostPersistShipmentOrderRequest.getShipmentOrder() != null) {// added condition to check non empty object
																			// related to retrival operation
			shipmentDataService.persistShipmentOrder(tDSPostPersistShipmentOrderRequest, trackingId, source);
		}
		// TDSShipmentOrderDetails
		logger.info("End of ShipmentDataServiceActivator::retriveDeviceDetails method>> ");
		return tDSPostPersistShipmentOrderRequest;
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix")
	public TDSShipmentOrderDetails persistShipmentOrderFallBack(@Header("trackingId") String trackingId,
			@Header(name = "source", required = false) String source,
			TDSShipmentOrderDetails tDSPostPersistShipmentOrderRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of ShipmentDataServiceActivator::persistShipmentOrderFallBack method >> ");
		throw new OrionMiddlewareDownstreamException(ErrorCode.CONNECTIVITY_ERROR_SHIPMENTDATA);
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix", commandKey = "downstreamOperationHystrix", fallbackMethod = "getOrderDetailsFallBack", ignoreExceptions = {
			HttpServerErrorException.class, OrionMiddlewareServiceException.class })
	public TDSShipmentOrderDetails getOrderDetails(@Header(name = "trackingId", required = false) String trackingId,
			@Header(name = "vendorOrderId") String vendorOrderId)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {

		logger.info("Start of ShipmentDataServiceActivator::getOrderDetails method >> ");
		TDSShipmentOrderDetails tDSPostPersistShipmentOrderRequest = shipmentDataService.getOrderDetails(vendorOrderId,
				trackingId);
		// TDSShipmentOrderDetails
		logger.info("End of ShipmentDataServiceActivator::getOrderDetails method>> ");
		return tDSPostPersistShipmentOrderRequest;
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix")
	public TDSShipmentOrderDetails getOrderDetailsFallBack(
			@Header(name = "trackingId", required = false) String trackingId,
			@Header(name = "vendorOrderId") String vendorOrderId)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of ShipmentDataServiceActivator::getOrderDetailsFallBack method >> ");
		throw new OrionMiddlewareDownstreamException(ErrorCode.CONNECTIVITY_ERROR_SHIPMENTDATA);
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix", commandKey = "downstreamOperationHystrix", fallbackMethod = "updateOrderStatusFallBack", ignoreExceptions = {
			HttpServerErrorException.class, OrionMiddlewareServiceException.class })
	public TDSShipmentOrderDetails updateOrderStatus(@Header("trackingId") String trackingId,
			@Header(name = "source", required = false) String source,
			@Header(name = "shipmentOrder", required = false) TDSShipmentOrderDetails tDSPostPersistShipmentOrderRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of ShipmentDataServiceActivator::retriveDeviceDetails method >> ");
		tDSPostPersistShipmentOrderRequest.getShipmentOrder().setOrderStatus(Constants.CLOSED);
		tDSPostPersistShipmentOrderRequest.getShipmentOrder().setVendorOrderStatus(Constants.CANCELLED);
		shipmentDataService.updateShipmentOrder(tDSPostPersistShipmentOrderRequest, trackingId, source);
		// TDSShipmentOrderDetails
		logger.info("End of ShipmentDataServiceActivator::retriveDeviceDetails method>> ");
		return tDSPostPersistShipmentOrderRequest;

	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix")
	public TDSShipmentOrderDetails updateOrderStatusFallBack(@Header("trackingId") String trackingId,
			@Header(name = "source", required = false) String source,
			@Header(name = "shipmentOrder", required = false) TDSShipmentOrderDetails tDSPostPersistShipmentOrderRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		throw new OrionMiddlewareDownstreamException(ErrorCode.CONNECTIVITY_ERROR_SHIPMENTDATA);
	}

	public TDSShipmentOrderDetails constructShipmentDataObjectToPersist(
			@Header(name = "shipmentOrderRequest", required = true) ShipmentOrderRequest shipmentOrderRequest,
			@Header(name = "vmsResponse", required = true) BVERetreiveDeviceResponse vmsResponse,
			@Header("shipmentDataResponse") TDSRetrieveDeviceDetails shipmentDataResponse,
			CreateOrderResponse comPSCreateShipmentSuccessResponse) {
		logger.info("Start of ShipmentDataServiceActivator::constructShipmentDataObjectToPersist method >> ");

		TDSShipmentOrderDetails tDSPostPersistShipmentOrderRequest = new TDSShipmentOrderDetails();

		CreateShipmentOrder createShipmentOrder = shipmentOrderRequest.getCreateShipmentOrder().get(0);

		ShipmentOrder shipmentOrder = ShipmentServiceMapper.MAPPER
				.mapTDSPersistReqToShipmentOrderReq(createShipmentOrder);
		shipmentOrder.setCompsOrderNumber(comPSCreateShipmentSuccessResponse.getOrderNumber());
		shipmentOrder.setVendorOrderNumber(comPSCreateShipmentSuccessResponse.getVendorOrderId());
		shipmentOrder.setOrderStatus(comPSCreateShipmentSuccessResponse.getOrderStatus());
		shipmentOrder.setVendorOrderStatus(Constants.NEW);
		// construct shipmentorder Details
		ShipmentOrderDetail shipmentOrderDetail = ShipmentServiceMapper.MAPPER
				.mapShipmentOrderDetailFromCreateShipment(createShipmentOrder);
		shipmentOrder.setShipmentOrderDetail(shipmentOrderDetail);
		// construct line items
		List<DeviceDetail> shimpdataDevices = shipmentDataResponse.getDeviceDetail();

		List<com.comcast.orion.shipment.domain.LineItem> lineItems = new ArrayList();
		for (DeviceRetrieveResponseType vmddevice : vmsResponse.getDevices()) {
			for (DeviceDetail device : shimpdataDevices) {
				if (device.getResourceId().equalsIgnoreCase(vmddevice.getDevice().getArmResourceId())) {
					com.comcast.orion.shipment.domain.LineItem item = new com.comcast.orion.shipment.domain.LineItem();
					item.setDeviceMake(device.getDeviceMake());
					item.setDeviceModel(device.getDeviceModel());
					item.setDeviceType(device.getDeviceType());
					item.setResourceId(device.getResourceId());
					item.setDeviceName(String.valueOf(device.getDeviceName()));
					item.setFirstName(vmddevice.getDevice().getFirstName());
					item.setLastName(vmddevice.getDevice().getLastName());
					item.setCurrentWANLicenseCount(device.getCurrentWANLicenseCount());
					item.setRequiredWANLicenseCount(device.getRequiredWANLicenseCount());
					lineItems.add(item);
				}

			}
		}
		shipmentOrder.setLineItems(lineItems);

		tDSPostPersistShipmentOrderRequest.setShipmentOrder(shipmentOrder);

		// setting the quatitiy value

		logger.info("End of ShipmentDataServiceActivator::constructShipmentDataObjectToPersist method >> ");
		return tDSPostPersistShipmentOrderRequest;

	}

	public TDSRetrieveDeviceDetails constructShipmentDataRequest(@Header("trackingId") String trackingId,
			ShipmentOrderRequest shipmentOrderRequest) throws OrionMiddlewareServiceException {
		logger.info("Start of ShipmentDataServiceActivator::constructShipmentDataRequest method >> ");
		TDSRetrieveDeviceDetails tDSRetrieveDeviceDetails = new TDSRetrieveDeviceDetails();
		List<DeviceDetail> deviceDetail = ShipmentServiceMapper.MAPPER.mapLineItemsToDeviceDetailsResponseList(
				shipmentOrderRequest.getCreateShipmentOrder().get(0).getLineItems());
		// remove downgrade devices

		deviceDetail.removeIf(t -> ((null != t.getCurrentWANLicenseCount() ? t.getCurrentWANLicenseCount()
				: 0) > (null != t.getRequiredWANLicenseCount() ? t.getRequiredWANLicenseCount() : 0)));
		// add default value for new new scnario
		List<String> wanlist = Arrays.asList(wanupgradesupport);
		if (deviceDetail.size() != 0)
			deviceDetail.stream().forEach(t -> {
				String s = t.getDeviceMake() + "-" + t.getDeviceType() + "-" + t.getDeviceModel();
				wanlist.stream().forEach(r -> {
					String licensecount[] = r.split("-");
					String wandevice = licensecount[0].concat("-").concat(licensecount[1]).concat("-")
							.concat(licensecount[2]);
					if (s.equalsIgnoreCase(wandevice) && t.getCurrentWANLicenseCount() == null
							&& t.getRequiredWANLicenseCount() == null) {
						t.setRequiredWANLicenseCount(Integer.parseInt(licensecount[3]));
					}

				});

			});

		if (deviceDetail.size() == 0) {
			throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
					MessageConstants.NO_DOWNGRADE);
		}
		tDSRetrieveDeviceDetails.setDeviceDetail(deviceDetail);
		logger.info("End of ShipmentDataServiceActivator::constructShipmentDataRequest method>> ");
		return tDSRetrieveDeviceDetails;
	}

}