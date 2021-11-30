
package com.comcast.orion.shipment.onp;

import javax.validation.constraints.NotNull;
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
    "shipDate",
    "trackingNumber"
})
public class ShipmentTrackingDetail {

    /**
     * The Shipdate Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipDate")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String shipDate = "";
    /**
     * The Trackingnumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("trackingNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String trackingNumber = "";

    /**
     * The Shipdate Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipDate")
    public String getShipDate() {
        return shipDate;
    }

    /**
     * The Shipdate Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipDate")
    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public ShipmentTrackingDetail withShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    /**
     * The Trackingnumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("trackingNumber")
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * The Trackingnumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
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
        return new HashCodeBuilder().append(shipDate).append(trackingNumber).toHashCode();
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
        return new EqualsBuilder().append(shipDate, rhs.shipDate).append(trackingNumber, rhs.trackingNumber).isEquals();
    }

}
