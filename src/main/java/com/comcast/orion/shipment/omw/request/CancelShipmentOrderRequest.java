
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
    "cancelShipmentOrder"
})
public class CancelShipmentOrderRequest {

    /**
     * The cancelShipmentOrder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("cancelShipmentOrder")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private List<CancelShipmentOrder> cancelShipmentOrder = new ArrayList<CancelShipmentOrder>();

    /**
     * The cancelShipmentOrder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("cancelShipmentOrder")
    public List<CancelShipmentOrder> getCancelShipmentOrder() {
        return cancelShipmentOrder;
    }

    /**
     * The cancelShipmentOrder Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("cancelShipmentOrder")
    public void setCancelShipmentOrder(List<CancelShipmentOrder> cancelShipmentOrder) {
        this.cancelShipmentOrder = cancelShipmentOrder;
    }

    public CancelShipmentOrderRequest withCancelShipmentOrder(List<CancelShipmentOrder> cancelShipmentOrder) {
        this.cancelShipmentOrder = cancelShipmentOrder;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cancelShipmentOrder).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CancelShipmentOrderRequest) == false) {
            return false;
        }
        CancelShipmentOrderRequest rhs = ((CancelShipmentOrderRequest) other);
        return new EqualsBuilder().append(cancelShipmentOrder, rhs.cancelShipmentOrder).isEquals();
    }

}
