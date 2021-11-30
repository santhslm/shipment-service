
package com.comcast.orion.shipment.omw.request;

import java.util.ArrayList;
import java.util.List;
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
    "createShipmentOrder"
})
public class ShipmentOrderRequest {

    /**
     * The Createshipmentorder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("createShipmentOrder")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private List<CreateShipmentOrder> createShipmentOrder = new ArrayList<CreateShipmentOrder>();

    /**
     * The Createshipmentorder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("createShipmentOrder")
    public List<CreateShipmentOrder> getCreateShipmentOrder() {
        return createShipmentOrder;
    }

    /**
     * The Createshipmentorder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("createShipmentOrder")
    public void setCreateShipmentOrder(List<CreateShipmentOrder> createShipmentOrder) {
        this.createShipmentOrder = createShipmentOrder;
    }

    public ShipmentOrderRequest withCreateShipmentOrder(List<CreateShipmentOrder> createShipmentOrder) {
        this.createShipmentOrder = createShipmentOrder;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(createShipmentOrder).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShipmentOrderRequest) == false) {
            return false;
        }
        ShipmentOrderRequest rhs = ((ShipmentOrderRequest) other);
        return new EqualsBuilder().append(createShipmentOrder, rhs.createShipmentOrder).isEquals();
    }

}
