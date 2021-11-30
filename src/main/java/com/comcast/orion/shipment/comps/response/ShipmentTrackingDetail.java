
package com.comcast.orion.shipment.comps.response;

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
    "deliveryDate",
    "deliveryLocation",
    "deliveryName",
    "shipDate",
    "trackingNumber"
})
public class ShipmentTrackingDetail {

    @JsonProperty("deliveryDate")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String deliveryDate;
    @JsonProperty("deliveryLocation")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String deliveryLocation;
    @JsonProperty("deliveryName")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String deliveryName;
    @JsonProperty("shipDate")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String shipDate;
    @JsonProperty("trackingNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String trackingNumber;

    @JsonProperty("deliveryDate")
    public String getDeliveryDate() {
        return deliveryDate;
    }

    @JsonProperty("deliveryDate")
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ShipmentTrackingDetail withDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    @JsonProperty("deliveryLocation")
    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    @JsonProperty("deliveryLocation")
    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public ShipmentTrackingDetail withDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
        return this;
    }

    @JsonProperty("deliveryName")
    public String getDeliveryName() {
        return deliveryName;
    }

    @JsonProperty("deliveryName")
    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public ShipmentTrackingDetail withDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
        return this;
    }

    @JsonProperty("shipDate")
    public String getShipDate() {
        return shipDate;
    }

    @JsonProperty("shipDate")
    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public ShipmentTrackingDetail withShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    @JsonProperty("trackingNumber")
    public String getTrackingNumber() {
        return trackingNumber;
    }

    @JsonProperty("trackingNumber")
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public ShipmentTrackingDetail withTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(deliveryDate).append(deliveryLocation).append(deliveryName).append(shipDate).append(trackingNumber).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShipmentTrackingDetail) == false) {
            return false;
        }
        ShipmentTrackingDetail rhs = ((ShipmentTrackingDetail) other);
        return new EqualsBuilder().append(deliveryDate, rhs.deliveryDate).append(deliveryLocation, rhs.deliveryLocation).append(deliveryName, rhs.deliveryName).append(shipDate, rhs.shipDate).append(trackingNumber, rhs.trackingNumber).isEquals();
    }

}
