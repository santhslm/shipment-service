
package com.comcast.orion.shipment.domain;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * The shipmentOrder Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "id",
    "shipmentSystem",
    "productType",
    "specialInstructions",
    "shippingOptions"
})
public class ShipmentOrderDetail {

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
     * The Shipmentsystem Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentSystem")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private ShipmentOrderDetail.ShipmentSystem shipmentSystem = ShipmentOrderDetail.ShipmentSystem.fromValue("NetX");
    /**
     * The Producttype Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("productType")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private ShipmentOrderDetail.ProductType productType = ShipmentOrderDetail.ProductType.fromValue("BVE");
    /**
     * The Specialinstructions Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("specialInstructions")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String specialInstructions = "";
    /**
     * The Shippingoptions Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shippingOptions")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private ShippingOptions shippingOptions;

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

    public ShipmentOrderDetail withId(Object id) {
        this.id = id;
        return this;
    }

    /**
     * The Shipmentsystem Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentSystem")
    public ShipmentOrderDetail.ShipmentSystem getShipmentSystem() {
        return shipmentSystem;
    }

    /**
     * The Shipmentsystem Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentSystem")
    public void setShipmentSystem(ShipmentOrderDetail.ShipmentSystem shipmentSystem) {
        this.shipmentSystem = shipmentSystem;
    }

    public ShipmentOrderDetail withShipmentSystem(ShipmentOrderDetail.ShipmentSystem shipmentSystem) {
        this.shipmentSystem = shipmentSystem;
        return this;
    }

    /**
     * The Producttype Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("productType")
    public ShipmentOrderDetail.ProductType getProductType() {
        return productType;
    }

    /**
     * The Producttype Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("productType")
    public void setProductType(ShipmentOrderDetail.ProductType productType) {
        this.productType = productType;
    }

    public ShipmentOrderDetail withProductType(ShipmentOrderDetail.ProductType productType) {
        this.productType = productType;
        return this;
    }

    /**
     * The Specialinstructions Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("specialInstructions")
    public String getSpecialInstructions() {
        return specialInstructions;
    }

    /**
     * The Specialinstructions Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("specialInstructions")
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public ShipmentOrderDetail withSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
        return this;
    }

    /**
     * The Shippingoptions Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shippingOptions")
    public ShippingOptions getShippingOptions() {
        return shippingOptions;
    }

    /**
     * The Shippingoptions Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shippingOptions")
    public void setShippingOptions(ShippingOptions shippingOptions) {
        this.shippingOptions = shippingOptions;
    }

    public ShipmentOrderDetail withShippingOptions(ShippingOptions shippingOptions) {
        this.shippingOptions = shippingOptions;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(shipmentSystem).append(productType).append(specialInstructions).append(shippingOptions).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShipmentOrderDetail) == false) {
            return false;
        }
        ShipmentOrderDetail rhs = ((ShipmentOrderDetail) other);
        return new EqualsBuilder().append(id, rhs.id).append(shipmentSystem, rhs.shipmentSystem).append(productType, rhs.productType).append(specialInstructions, rhs.specialInstructions).append(shippingOptions, rhs.shippingOptions).isEquals();
    }

    public enum ProductType {

        BVE("BVE");
        private final String value;
        private final static Map<String, ShipmentOrderDetail.ProductType> CONSTANTS = new HashMap<String, ShipmentOrderDetail.ProductType>();

        static {
            for (ShipmentOrderDetail.ProductType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ProductType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static ShipmentOrderDetail.ProductType fromValue(String value) {
            ShipmentOrderDetail.ProductType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum ShipmentSystem {

        NET_X("NetX");
        private final String value;
        private final static Map<String, ShipmentOrderDetail.ShipmentSystem> CONSTANTS = new HashMap<String, ShipmentOrderDetail.ShipmentSystem>();

        static {
            for (ShipmentOrderDetail.ShipmentSystem c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ShipmentSystem(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static ShipmentOrderDetail.ShipmentSystem fromValue(String value) {
            ShipmentOrderDetail.ShipmentSystem constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
