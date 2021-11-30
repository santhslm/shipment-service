
package com.comcast.orion.shipment.comps.response;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "orderStatus",
    "shipmentTrackingDetails",
    "shipper",
    "shippingMethod",
    "vendorOrderId"
})
public class VendorOrderDetailInfo {

    @JsonProperty("orderStatus")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String orderStatus;
    @JsonProperty("shipmentTrackingDetails")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private ShipmentTrackingDetails shipmentTrackingDetails;
    @JsonProperty("shipper")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String shipper;
    @JsonProperty("shippingMethod")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String shippingMethod;
    @JsonProperty("vendorOrderId")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String vendorOrderId;

    @JsonProperty("orderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("orderStatus")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public VendorOrderDetailInfo withOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    @JsonProperty("shipmentTrackingDetails")
    public ShipmentTrackingDetails getShipmentTrackingDetails() {
        return shipmentTrackingDetails;
    }

    @JsonProperty("shipmentTrackingDetails")
    public void setShipmentTrackingDetails(ShipmentTrackingDetails shipmentTrackingDetails) {
        this.shipmentTrackingDetails = shipmentTrackingDetails;
    }

    public VendorOrderDetailInfo withShipmentTrackingDetails(ShipmentTrackingDetails shipmentTrackingDetails) {
        this.shipmentTrackingDetails = shipmentTrackingDetails;
        return this;
    }

    @JsonProperty("shipper")
    public String getShipper() {
        return shipper;
    }

    @JsonProperty("shipper")
    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public VendorOrderDetailInfo withShipper(String shipper) {
        this.shipper = shipper;
        return this;
    }

    @JsonProperty("shippingMethod")
    public String getShippingMethod() {
        return shippingMethod;
    }

    @JsonProperty("shippingMethod")
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public VendorOrderDetailInfo withShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }

    @JsonProperty("vendorOrderId")
    public String getVendorOrderId() {
        return vendorOrderId;
    }

    @JsonProperty("vendorOrderId")
    public void setVendorOrderId(String vendorOrderId) {
        this.vendorOrderId = vendorOrderId;
    }

    public VendorOrderDetailInfo withVendorOrderId(String vendorOrderId) {
        this.vendorOrderId = vendorOrderId;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orderStatus).append(shipmentTrackingDetails).append(shipper).append(shippingMethod).append(vendorOrderId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VendorOrderDetailInfo) == false) {
            return false;
        }
        VendorOrderDetailInfo rhs = ((VendorOrderDetailInfo) other);
        return new EqualsBuilder().append(orderStatus, rhs.orderStatus).append(shipmentTrackingDetails, rhs.shipmentTrackingDetails).append(shipper, rhs.shipper).append(shippingMethod, rhs.shippingMethod).append(vendorOrderId, rhs.vendorOrderId).isEquals();
    }

}
