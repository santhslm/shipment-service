
package com.comcast.orion.shipment.omw.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
 * The Items Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "purchaseOrderNumber",
    "shipmentSystem",
    "productType",
    "specialInstructions",
    "shippingOptions",
    "shippingAddress",
    "additionalAttributes",
    "lineItems"
})
public class CreateShipmentOrder {

    /**
     * The Purchaseordernumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("purchaseOrderNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String purchaseOrderNumber = "";
    /**
     * The Shipmentsystem Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentSystem")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
   //@Pattern(regexp = "^(.*)$")
    private CreateShipmentOrder.ShipmentSystem shipmentSystem = CreateShipmentOrder.ShipmentSystem.fromValue("NetX");
    /**
     * The Producttype Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("productType")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    //@Pattern(regexp = "^(.*)$")
    private CreateShipmentOrder.ProductType productType = CreateShipmentOrder.ProductType.fromValue("BVE");
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
     * (Required)
     * 
     */
    @JsonProperty("shippingOptions")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private ShippingOptions shippingOptions;
    /**
     * The Shippingaddress Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shippingAddress")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private ShippingAddress shippingAddress;
    /**
     * The Additionalattributes Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("additionalAttributes")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private List<AdditionalAttribute> additionalAttributes = new ArrayList<AdditionalAttribute>();
    /**
     * The Lineitems Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("lineItems")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private List<LineItem> lineItems = new ArrayList<LineItem>();

    /**
     * The Purchaseordernumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("purchaseOrderNumber")
    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    /**
     * The Purchaseordernumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("purchaseOrderNumber")
    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public CreateShipmentOrder withPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
        return this;
    }

    /**
     * The Shipmentsystem Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentSystem")
    public CreateShipmentOrder.ShipmentSystem getShipmentSystem() {
        return shipmentSystem;
    }

    /**
     * The Shipmentsystem Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("shipmentSystem")
    public void setShipmentSystem(CreateShipmentOrder.ShipmentSystem shipmentSystem) {
        this.shipmentSystem = shipmentSystem;
    }

    public CreateShipmentOrder withShipmentSystem(CreateShipmentOrder.ShipmentSystem shipmentSystem) {
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
    public CreateShipmentOrder.ProductType getProductType() {
        return productType;
    }

    /**
     * The Producttype Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("productType")
    public void setProductType(CreateShipmentOrder.ProductType productType) {
        this.productType = productType;
    }

    public CreateShipmentOrder withProductType(CreateShipmentOrder.ProductType productType) {
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

    public CreateShipmentOrder withSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
        return this;
    }

    /**
     * The Shippingoptions Schema
     * <p>
     * 
     * (Required)
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
     * (Required)
     * 
     */
    @JsonProperty("shippingOptions")
    public void setShippingOptions(ShippingOptions shippingOptions) {
        this.shippingOptions = shippingOptions;
    }

    public CreateShipmentOrder withShippingOptions(ShippingOptions shippingOptions) {
        this.shippingOptions = shippingOptions;
        return this;
    }

    /**
     * The Shippingaddress Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shippingAddress")
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    /**
     * The Shippingaddress Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shippingAddress")
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public CreateShipmentOrder withShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    /**
     * The Additionalattributes Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("additionalAttributes")
    public List<AdditionalAttribute> getAdditionalAttributes() {
        return additionalAttributes;
    }

    /**
     * The Additionalattributes Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("additionalAttributes")
    public void setAdditionalAttributes(List<AdditionalAttribute> additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }

    public CreateShipmentOrder withAdditionalAttributes(List<AdditionalAttribute> additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
        return this;
    }

    /**
     * The Lineitems Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("lineItems")
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * The Lineitems Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("lineItems")
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public CreateShipmentOrder withLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(purchaseOrderNumber).append(shipmentSystem).append(productType).append(specialInstructions).append(shippingOptions).append(shippingAddress).append(additionalAttributes).append(lineItems).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreateShipmentOrder) == false) {
            return false;
        }
        CreateShipmentOrder rhs = ((CreateShipmentOrder) other);
        return new EqualsBuilder().append(purchaseOrderNumber, rhs.purchaseOrderNumber).append(shipmentSystem, rhs.shipmentSystem).append(productType, rhs.productType).append(specialInstructions, rhs.specialInstructions).append(shippingOptions, rhs.shippingOptions).append(shippingAddress, rhs.shippingAddress).append(additionalAttributes, rhs.additionalAttributes).append(lineItems, rhs.lineItems).isEquals();
    }

    public enum ProductType {

        BVE("BVE");
        private final String value;
        private final static Map<String, CreateShipmentOrder.ProductType> CONSTANTS = new HashMap<String, CreateShipmentOrder.ProductType>();

        static {
            for (CreateShipmentOrder.ProductType c: values()) {
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
        public static CreateShipmentOrder.ProductType fromValue(String value) {
            CreateShipmentOrder.ProductType constant = CONSTANTS.get(value);
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
        private final static Map<String, CreateShipmentOrder.ShipmentSystem> CONSTANTS = new HashMap<String, CreateShipmentOrder.ShipmentSystem>();

        static {
            for (CreateShipmentOrder.ShipmentSystem c: values()) {
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
        public static CreateShipmentOrder.ShipmentSystem fromValue(String value) {
            CreateShipmentOrder.ShipmentSystem constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
