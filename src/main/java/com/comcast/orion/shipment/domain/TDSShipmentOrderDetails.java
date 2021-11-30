
package com.comcast.orion.shipment.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * The Root Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "shipmentOrder"
})
public class TDSShipmentOrderDetails {

    /**
     * The shipmentOrder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipmentOrder")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private ShipmentOrder shipmentOrder;

    /**
     * The shipmentOrder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipmentOrder")
    public ShipmentOrder getShipmentOrder() {
        return shipmentOrder;
    }

    /**
     * The shipmentOrder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipmentOrder")
    public void setShipmentOrder(ShipmentOrder shipmentOrder) {
        this.shipmentOrder = shipmentOrder;
    }

    public TDSShipmentOrderDetails withShipmentOrder(ShipmentOrder shipmentOrder) {
        this.shipmentOrder = shipmentOrder;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(shipmentOrder).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TDSShipmentOrderDetails) == false) {
            return false;
        }
        TDSShipmentOrderDetails rhs = ((TDSShipmentOrderDetails) other);
        return new EqualsBuilder().append(shipmentOrder, rhs.shipmentOrder).isEquals();
    }

}
