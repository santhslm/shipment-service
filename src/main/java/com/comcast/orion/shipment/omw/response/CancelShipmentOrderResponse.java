
package com.comcast.orion.shipment.omw.response;

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
    "cancelShipmentOrderResponse"
})
public class CancelShipmentOrderResponse {

    /**
     * The cancelShipmentOrderResponse Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("cancelShipmentOrderResponse")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private List<CancelShipmentOrderDetails> cancelShipmentOrderResponse = new ArrayList<CancelShipmentOrderDetails>();

    /**
     * The cancelShipmentOrderResponse Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("cancelShipmentOrderResponse")
    public List<CancelShipmentOrderDetails> getCancelShipmentOrderResponse() {
        return cancelShipmentOrderResponse;
    }

    /**
     * The cancelShipmentOrderResponse Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("cancelShipmentOrderResponse")
    public void setCancelShipmentOrderResponse(List<CancelShipmentOrderDetails> cancelShipmentOrderResponse) {
        this.cancelShipmentOrderResponse = cancelShipmentOrderResponse;
    }

    public CancelShipmentOrderResponse withCancelShipmentOrderResponse(List<CancelShipmentOrderDetails> cancelShipmentOrderResponse) {
        this.cancelShipmentOrderResponse = cancelShipmentOrderResponse;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(cancelShipmentOrderResponse).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CancelShipmentOrderResponse) == false) {
            return false;
        }
        CancelShipmentOrderResponse rhs = ((CancelShipmentOrderResponse) other);
        return new EqualsBuilder().append(cancelShipmentOrderResponse, rhs.cancelShipmentOrderResponse).isEquals();
    }

}
