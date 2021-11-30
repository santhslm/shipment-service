
package com.comcast.orion.shipment.domain;

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
    "id",
    "deliverDate",
    "deliverLocation",
    "deliverName",
    "trackingNumber",
    "shipDate"
})
public class ShipmentTrackingDetail {

    /**
     * Internal  ID
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
    private Object id = null;
    /**
     * The deliveryDate of the Shipment
     * <p>
     * 
     * 
     */
    @JsonProperty("deliverDate")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String deliverDate = "";
    /**
     * The deliveryLocation Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deliverLocation")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String deliverLocation = "";
    /**
     * The deliverName Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deliverName")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String deliverName = "";
    /**
     * The trackingNumber of Shipment
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("trackingNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @NotNull
    private String trackingNumber = "";
    /**
     * The shipDate of Shipment
     * <p>
     * 
     * 
     */
    @JsonProperty("shipDate")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String shipDate = "";

    /**
     * Internal  ID
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    public Object getId() {
        return id;
    }

    /**
     * Internal  ID
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    public void setId(Object id) {
        this.id = id;
    }

    public ShipmentTrackingDetail withId(Object id) {
        this.id = id;
        return this;
    }

    /**
     * The deliveryDate of the Shipment
     * <p>
     * 
     * 
     */
    @JsonProperty("deliverDate")
    public String getDeliverDate() {
        return deliverDate;
    }

    /**
     * The deliveryDate of the Shipment
     * <p>
     * 
     * 
     */
    @JsonProperty("deliverDate")
    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public ShipmentTrackingDetail withDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
        return this;
    }

    /**
     * The deliveryLocation Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deliverLocation")
    public String getDeliverLocation() {
        return deliverLocation;
    }

    /**
     * The deliveryLocation Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deliverLocation")
    public void setDeliverLocation(String deliverLocation) {
        this.deliverLocation = deliverLocation;
    }

    public ShipmentTrackingDetail withDeliverLocation(String deliverLocation) {
        this.deliverLocation = deliverLocation;
        return this;
    }

    /**
     * The deliverName Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deliverName")
    public String getDeliverName() {
        return deliverName;
    }

    /**
     * The deliverName Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deliverName")
    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public ShipmentTrackingDetail withDeliverName(String deliverName) {
        this.deliverName = deliverName;
        return this;
    }

    /**
     * The trackingNumber of Shipment
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
     * The trackingNumber of Shipment
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

    /**
     * The shipDate of Shipment
     * <p>
     * 
     * 
     */
    @JsonProperty("shipDate")
    public String getShipDate() {
        return shipDate;
    }

    /**
     * The shipDate of Shipment
     * <p>
     * 
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(deliverDate).append(deliverLocation).append(deliverName).append(trackingNumber).append(shipDate).toHashCode();
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
        return new EqualsBuilder().append(id, rhs.id).append(deliverDate, rhs.deliverDate).append(deliverLocation, rhs.deliverLocation).append(deliverName, rhs.deliverName).append(trackingNumber, rhs.trackingNumber).append(shipDate, rhs.shipDate).isEquals();
    }

}
