
package com.comcast.orion.shipment.comps.request;

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
    "method",
    "shipper",
    "signature"
})
public class ShippingOptions {

    @JsonProperty("method")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String method;
    @JsonProperty("shipper")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String shipper;
    @JsonProperty("signature")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "boolean", position = 0, value = "", example = "")
    private Boolean signature;

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    public ShippingOptions withMethod(String method) {
        this.method = method;
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

    public ShippingOptions withShipper(String shipper) {
        this.shipper = shipper;
        return this;
    }

    @JsonProperty("signature")
    public Boolean getSignature() {
        return signature;
    }

    @JsonProperty("signature")
    public void setSignature(Boolean signature) {
        this.signature = signature;
    }

    public ShippingOptions withSignature(Boolean signature) {
        this.signature = signature;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(method).append(shipper).append(signature).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShippingOptions) == false) {
            return false;
        }
        ShippingOptions rhs = ((ShippingOptions) other);
        return new EqualsBuilder().append(method, rhs.method).append(shipper, rhs.shipper).append(signature, rhs.signature).isEquals();
    }

}
