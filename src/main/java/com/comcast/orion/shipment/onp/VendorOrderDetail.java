
package com.comcast.orion.shipment.onp;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * The Items Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "shipper",
    "vendorOrderId",
    "shippingMethod",
    "shipmentTrackingDetails",
    "vendorOrderStatus"
})
public class VendorOrderDetail {

    /**
     * The Shipper Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipper")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String shipper = "";
    /**
     * The Vendororderid Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("vendorOrderId")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String vendorOrderId = "";
    /**
     * The Shippingmethod Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shippingMethod")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String shippingMethod = "";
    /**
     * The Shipmenttrackingdetails Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentTrackingDetails")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    private List<ShipmentTrackingDetail> shipmentTrackingDetails = new ArrayList<ShipmentTrackingDetail>();
    /**
     * The Vendororderstatus Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("vendorOrderStatus")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String vendorOrderStatus = "";

    /**
     * The Shipper Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipper")
    public String getShipper() {
        return shipper;
    }

    /**
     * The Shipper Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipper")
    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public VendorOrderDetail withShipper(String shipper) {
        this.shipper = shipper;
        return this;
    }

    /**
     * The Vendororderid Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("vendorOrderId")
    public String getVendorOrderId() {
        return vendorOrderId;
    }

    /**
     * The Vendororderid Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("vendorOrderId")
    public void setVendorOrderId(String vendorOrderId) {
        this.vendorOrderId = vendorOrderId;
    }

    public VendorOrderDetail withVendorOrderId(String vendorOrderId) {
        this.vendorOrderId = vendorOrderId;
        return this;
    }

    /**
     * The Shippingmethod Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shippingMethod")
    public String getShippingMethod() {
        return shippingMethod;
    }

    /**
     * The Shippingmethod Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shippingMethod")
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public VendorOrderDetail withShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
        return this;
    }

    /**
     * The Shipmenttrackingdetails Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentTrackingDetails")
    public List<ShipmentTrackingDetail> getShipmentTrackingDetails() {
        return shipmentTrackingDetails;
    }

    /**
     * The Shipmenttrackingdetails Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentTrackingDetails")
    public void setShipmentTrackingDetails(List<ShipmentTrackingDetail> shipmentTrackingDetails) {
        this.shipmentTrackingDetails = shipmentTrackingDetails;
    }

    public VendorOrderDetail withShipmentTrackingDetails(List<ShipmentTrackingDetail> shipmentTrackingDetails) {
        this.shipmentTrackingDetails = shipmentTrackingDetails;
        return this;
    }

    /**
     * The Vendororderstatus Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("vendorOrderStatus")
    public String getVendorOrderStatus() {
        return vendorOrderStatus;
    }

    /**
     * The Vendororderstatus Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("vendorOrderStatus")
    public void setVendorOrderStatus(String vendorOrderStatus) {
        this.vendorOrderStatus = vendorOrderStatus;
    }

    public VendorOrderDetail withVendorOrderStatus(String vendorOrderStatus) {
        this.vendorOrderStatus = vendorOrderStatus;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(shipper).append(vendorOrderId).append(shippingMethod).append(shipmentTrackingDetails).append(vendorOrderStatus).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VendorOrderDetail) == false) {
            return false;
        }
        VendorOrderDetail rhs = ((VendorOrderDetail) other);
        return new EqualsBuilder().append(shipper, rhs.shipper).append(vendorOrderId, rhs.vendorOrderId).append(shippingMethod, rhs.shippingMethod).append(shipmentTrackingDetails, rhs.shipmentTrackingDetails).append(vendorOrderStatus, rhs.vendorOrderStatus).isEquals();
    }

}
