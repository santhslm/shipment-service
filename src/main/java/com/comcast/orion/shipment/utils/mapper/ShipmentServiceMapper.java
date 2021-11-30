package com.comcast.orion.shipment.utils.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.comcast.orion.shipment.comps.request.CreateOrderAttr;
import com.comcast.orion.shipment.comps.response.ShipmentTrackingDetail;
import com.comcast.orion.shipment.domain.DeviceDetail;
import com.comcast.orion.shipment.domain.ShipmentOrder;
import com.comcast.orion.shipment.domain.ShipmentOrderDetail;
import com.comcast.orion.shipment.omw.request.CreateShipmentOrder;
import com.comcast.orion.shipment.omw.request.LineItem;
import com.comcast.orion.shipment.vms.response.DeviceRetrieveResponseType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShipmentServiceMapper {
	ShipmentServiceMapper MAPPER = Mappers.getMapper(ShipmentServiceMapper.class);

	/**
	 * @param amilresponse
	 * @return
	 */

	List<DeviceDetail> mapLineItemsToDeviceDetailsResponseList(List<LineItem> LineItems);

	DeviceDetail mapLineItemsToDeviceDetailsResponse(LineItem LineItem);

	@Mappings({ @Mapping(target = "lineItems.lineItem", source = "lineItems"),
			@Mapping(target = "shippingOptions.signature", source = "shippingOptions.signatureRequired"),
			@Mapping(target = "shippingOptions.method", expression = "java(com.comcast.orion.shipment.utils.MapperConverterFunctions.enumToStringConverter(shippingOptions.getMethod()))")

	})
	CreateOrderAttr mapCreateOrderReqToCompsRequest(CreateShipmentOrder createShipmentOrder);

	ShipmentOrder mapTDSPersistReqToShipmentOrderReq(CreateShipmentOrder createShipmentOrder);

	ShipmentOrderDetail mapShipmentOrderDetailFromCreateShipment(CreateShipmentOrder createShipmentOrder);

	List<com.comcast.orion.shipment.domain.LineItem> mapTDSlineItemsFromVMSLineItems(
			List<DeviceRetrieveResponseType> devices);

	@Mappings({ @Mapping(target = "deviceMake", source = "device.deviceMake"),
			@Mapping(target = "deviceModel", source = "device.deviceModel"),
			@Mapping(target = "deviceType", source = "device.deviceType"),
			@Mapping(target = "resourceId", source = "device.armResourceId"),
			@Mapping(target = "firstName", source = "device.firstName"),
			@Mapping(target = "lastName", source = "device.lastName"),
			// @Mapping(source = "quantity", target = "device.lineCount"),
			@Mapping(target = "macAddress", source = "device.macAddress"),
			@Mapping(target = "serialNumber", source = "device.serialNumber") })
	com.comcast.orion.shipment.domain.LineItem mapTDSlineItemsFromVMSLineItems(DeviceRetrieveResponseType devices);

	com.comcast.orion.shipment.domain.ShipmentTrackingDetail mapTrackingDetails(ShipmentTrackingDetail track);

}
