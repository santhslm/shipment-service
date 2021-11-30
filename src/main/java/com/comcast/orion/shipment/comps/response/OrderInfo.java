
package com.comcast.orion.shipment.comps.response;

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
    "company",
    "lineItems",
    "vendorOrderDetails"
})
public class OrderInfo {

    @JsonProperty("company")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String company;
    @JsonProperty("lineItems")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private LineItems lineItems;
    @JsonProperty("vendorOrderDetails")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private VendorOrderDetails vendorOrderDetails;

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    public OrderInfo withCompany(String company) {
        this.company = company;
        return this;
    }

    @JsonProperty("lineItems")
    public LineItems getLineItems() {
        return lineItems;
    }

    @JsonProperty("lineItems")
    public void setLineItems(LineItems lineItems) {
        this.lineItems = lineItems;
    }

    public OrderInfo withLineItems(LineItems lineItems) {
        this.lineItems = lineItems;
        return this;
    }

    @JsonProperty("vendorOrderDetails")
    public VendorOrderDetails getVendorOrderDetails() {
        return vendorOrderDetails;
    }

    @JsonProperty("vendorOrderDetails")
    public void setVendorOrderDetails(VendorOrderDetails vendorOrderDetails) {
        this.vendorOrderDetails = vendorOrderDetails;
    }

    public OrderInfo withVendorOrderDetails(VendorOrderDetails vendorOrderDetails) {
        this.vendorOrderDetails = vendorOrderDetails;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(company).append(lineItems).append(vendorOrderDetails).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderInfo) == false) {
            return false;
        }
        OrderInfo rhs = ((OrderInfo) other);
        return new EqualsBuilder().append(company, rhs.company).append(lineItems, rhs.lineItems).append(vendorOrderDetails, rhs.vendorOrderDetails).isEquals();
    }

}
