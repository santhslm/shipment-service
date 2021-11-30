
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
    "purchaseOrderNumber",
    "specialInstructions",
    "orderType",
    "shippingOptions",
    "shippingAddress",
    "lineItems"
})
public class CreateOrderAttr {

    @JsonProperty("purchaseOrderNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String purchaseOrderNumber;
    @JsonProperty("specialInstructions")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String specialInstructions;
    @JsonProperty("orderType")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String orderType;
    @JsonProperty("shippingOptions")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private ShippingOptions shippingOptions;
    @JsonProperty("shippingAddress")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private ShippingAddress shippingAddress;
    @JsonProperty("lineItems")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private LineItems lineItems;

    @JsonProperty("purchaseOrderNumber")
    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    @JsonProperty("purchaseOrderNumber")
    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public CreateOrderAttr withPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
        return this;
    }

    @JsonProperty("specialInstructions")
    public String getSpecialInstructions() {
        return specialInstructions;
    }

    @JsonProperty("specialInstructions")
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public CreateOrderAttr withSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
        return this;
    }

    @JsonProperty("orderType")
    public String getOrderType() {
        return orderType;
    }

    @JsonProperty("orderType")
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public CreateOrderAttr withOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    @JsonProperty("shippingOptions")
    public ShippingOptions getShippingOptions() {
        return shippingOptions;
    }

    @JsonProperty("shippingOptions")
    public void setShippingOptions(ShippingOptions shippingOptions) {
        this.shippingOptions = shippingOptions;
    }

    public CreateOrderAttr withShippingOptions(ShippingOptions shippingOptions) {
        this.shippingOptions = shippingOptions;
        return this;
    }

    @JsonProperty("shippingAddress")
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    @JsonProperty("shippingAddress")
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public CreateOrderAttr withShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
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

    public CreateOrderAttr withLineItems(LineItems lineItems) {
        this.lineItems = lineItems;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(purchaseOrderNumber).append(specialInstructions).append(orderType).append(shippingOptions).append(shippingAddress).append(lineItems).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreateOrderAttr) == false) {
            return false;
        }
        CreateOrderAttr rhs = ((CreateOrderAttr) other);
        return new EqualsBuilder().append(purchaseOrderNumber, rhs.purchaseOrderNumber).append(specialInstructions, rhs.specialInstructions).append(orderType, rhs.orderType).append(shippingOptions, rhs.shippingOptions).append(shippingAddress, rhs.shippingAddress).append(lineItems, rhs.lineItems).isEquals();
    }

}
