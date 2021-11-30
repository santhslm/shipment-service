
package com.comcast.orion.shipment.omw.request;

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
    "orderNumber",
    "shipmentSystem",
    "productType"
})
public class CancelShipmentOrder {

    /**
     * The orderNumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String orderNumber;
    /**
     * The shipmentSystem Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentSystem")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String shipmentSystem = "NetX";
    /**
     * The productType Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("productType")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String productType = "BVE";

    /**
     * The orderNumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * The orderNumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderNumber")
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public CancelShipmentOrder withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    /**
     * The shipmentSystem Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentSystem")
    public String getShipmentSystem() {
        return shipmentSystem;
    }

    /**
     * The shipmentSystem Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentSystem")
    public void setShipmentSystem(String shipmentSystem) {
        this.shipmentSystem = shipmentSystem;
    }

    public CancelShipmentOrder withShipmentSystem(String shipmentSystem) {
        this.shipmentSystem = shipmentSystem;
        return this;
    }

    /**
     * The productType Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("productType")
    public String getProductType() {
        return productType;
    }

    /**
     * The productType Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("productType")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    public CancelShipmentOrder withProductType(String productType) {
        this.productType = productType;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orderNumber).append(shipmentSystem).append(productType).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CancelShipmentOrder) == false) {
            return false;
        }
        CancelShipmentOrder rhs = ((CancelShipmentOrder) other);
        return new EqualsBuilder().append(orderNumber, rhs.orderNumber).append(shipmentSystem, rhs.shipmentSystem).append(productType, rhs.productType).isEquals();
    }

}
