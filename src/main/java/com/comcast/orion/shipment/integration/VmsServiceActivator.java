package com.comcast.orion.shipment.integration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import com.comcast.orion.shipment.omw.request.AdditionalAttribute;
import com.comcast.orion.shipment.omw.request.LineItem;
import com.comcast.orion.shipment.omw.request.ShipmentOrderRequest;
import com.comcast.orion.shipment.service.VMSService;
import com.comcast.orion.shipment.utils.Constants;
import com.comcast.orion.shipment.utils.exceptions.ErrorCode;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareDownstreamException;
import com.comcast.orion.shipment.utils.exceptions.OrionMiddlewareServiceException;
import com.comcast.orion.shipment.vms.request.BVERetrieveDeviceRequest;
import com.comcast.orion.shipment.vms.response.BVERetreiveDeviceResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class VmsServiceActivator {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	VMSService vMSService;

	boolean modified;

	@Value("${devices.wanupgradesupport}")
	private String wanupgradesupport;

	@HystrixCommand(groupKey = "downstreamOperationHystrix", commandKey = "downstreamOperationHystrix", fallbackMethod = "bveRetriveDevicesFallBack", ignoreExceptions = {
			HttpServerErrorException.class, OrionMiddlewareServiceException.class })
	public BVERetreiveDeviceResponse bveRetriveDevices(@Header("trackingId") String trackingId,
			BVERetrieveDeviceRequest bVERetrieveDeviceRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of OnpServiceActivator::bveRetriveDevices method >> ");
		BVERetreiveDeviceResponse BVERetreiveDeviceResponse = vMSService.bveRetriveDevices(bVERetrieveDeviceRequest);
		logger.info("End of OnpServiceActivator::bveRetriveDevices method >> ");
		return BVERetreiveDeviceResponse;
	}

	@HystrixCommand(groupKey = "downstreamOperationHystrix")
	public BVERetreiveDeviceResponse bveRetriveDevicesFallBack(@Header("trackingId") String trackingId,
			BVERetrieveDeviceRequest bVERetrieveDeviceRequest)
			throws OrionMiddlewareServiceException, OrionMiddlewareDownstreamException {
		logger.info("Start of VmsServiceActivator::bveRetriveDevicesFallBack method >> ");
		throw new OrionMiddlewareDownstreamException(ErrorCode.CONNECTIVITY_ERROR_VMS);
	}

	public BVERetrieveDeviceRequest constructVmsRequest(@Header("trackingId") String trackingId,
			@Header("shipmentOrderRequest") ShipmentOrderRequest shipmentOrderRequest)
			throws OrionMiddlewareServiceException {
		logger.info("Start of OnpServiceActivator::constructVmsRequest method >> ");
		// remove invalid devices
		List<LineItem> omwlineitems = shipmentOrderRequest.getCreateShipmentOrder().get(0).getLineItems();

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

		BVERetrieveDeviceRequest bVERetrieveDeviceRequest = new BVERetrieveDeviceRequest();
		List<String> resourceIds = omwlineitems.stream().map(LineItem::getResourceId).collect(Collectors.toList());
		Optional<AdditionalAttribute> optionalEntry = shipmentOrderRequest.getCreateShipmentOrder().get(0)
				.getAdditionalAttributes().stream()
				.filter(attrValue -> attrValue.getName().equalsIgnoreCase(Constants.VMSDESIGNID)).findFirst();
		String designId = optionalEntry.isPresent() ? optionalEntry.get().getValue() : null;
		if (StringUtils.isEmpty(designId)) {
			throw new OrionMiddlewareServiceException(ErrorCode.CONTRACT_VALIDATION_ERROR,
					"VMS Design Id is mandatory");
		}
		bVERetrieveDeviceRequest.setDesignId(designId);
		// remove duplicate resourceids
		List<String> uniqueresourceIds = resourceIds.stream().distinct().collect(Collectors.toList());
		bVERetrieveDeviceRequest.setArmResourceId(uniqueresourceIds);

		logger.info("End of OnpServiceActivator::constructVmsRequest method >> ");
		return bVERetrieveDeviceRequest;
	}

}