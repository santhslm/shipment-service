
package com.comcast.orion.shipment.comps.request;

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
    "createOrderAttr"
})
public class ComPSCreateShipmentRequest {

    @JsonProperty("createOrderAttr")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private CreateOrderAttr createOrderAttr;

    @JsonProperty("createOrderAttr")
    public CreateOrderAttr getCreateOrderAttr() {
        return createOrderAttr;
    }

    @JsonProperty("createOrderAttr")
    public void setCreateOrderAttr(CreateOrderAttr createOrderAttr) {
        this.createOrderAttr = createOrderAttr;
    }

    public ComPSCreateShipmentRequest withCreateOrderAttr(CreateOrderAttr createOrderAttr) {
        this.createOrderAttr = createOrderAttr;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(createOrderAttr).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ComPSCreateShipmentRequest) == false) {
            return false;
        }
        ComPSCreateShipmentRequest rhs = ((ComPSCreateShipmentRequest) other);
        return new EqualsBuilder().append(createOrderAttr, rhs.createOrderAttr).isEquals();
    }

}
