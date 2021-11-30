
package com.comcast.orion.shipment.domain;

import java.util.HashMap;
import java.util.Map;
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
 * The Shippingoptions Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "method",
    "shipper",
    "signatureRequired"
})
public class ShippingOptions {

    /**
     * The Method Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("method")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private ShippingOptions.Method method;
    /**
     * The Shipper Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipper")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private ShippingOptions.Shipper shipper;
    /**
     * The Signaturerequired Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("signatureRequired")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "boolean", position = 0, value = "", example = "")
    @NotNull
    private Boolean signatureRequired = false;

    /**
     * The Method Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("method")
    public ShippingOptions.Method getMethod() {
        return method;
    }

    /**
     * The Method Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("method")
    public void setMethod(ShippingOptions.Method method) {
        this.method = method;
    }

    public ShippingOptions withMethod(ShippingOptions.Method method) {
        this.method = method;
        return this;
    }

    /**
     * The Shipper Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipper")
    public ShippingOptions.Shipper getShipper() {
        return shipper;
    }

    /**
     * The Shipper Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("shipper")
    public void setShipper(ShippingOptions.Shipper shipper) {
        this.shipper = shipper;
    }

    public ShippingOptions withShipper(ShippingOptions.Shipper shipper) {
        this.shipper = shipper;
        return this;
    }

    /**
     * The Signaturerequired Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("signatureRequired")
    public Boolean getSignatureRequired() {
        return signatureRequired;
    }

    /**
     * The Signaturerequired Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("signatureRequired")
    public void setSignatureRequired(Boolean signatureRequired) {
        this.signatureRequired = signatureRequired;
    }

    public ShippingOptions withSignatureRequired(Boolean signatureRequired) {
        this.signatureRequired = signatureRequired;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(method).append(shipper).append(signatureRequired).toHashCode();
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
        return new EqualsBuilder().append(method, rhs.method).append(shipper, rhs.shipper).append(signatureRequired, rhs.signatureRequired).isEquals();
    }

    public enum Method {

        GROUND("Ground"),
        _3_DAY("3-day"),
        _2_DAY("2-day"),
        OVERNIGHT("Overnight"),
        PRIORITY_OVERNIGHT("Priority Overnight"),
        OVERNIGHT_EARLY_AM("Overnight Early AM");
        private final String value;
        private final static Map<String, ShippingOptions.Method> CONSTANTS = new HashMap<String, ShippingOptions.Method>();

        static {
            for (ShippingOptions.Method c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Method(String value) {
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
        public static ShippingOptions.Method fromValue(String value) {
            ShippingOptions.Method constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public enum Shipper {

        UPS("UPS");
        private final String value;
        private final static Map<String, ShippingOptions.Shipper> CONSTANTS = new HashMap<String, ShippingOptions.Shipper>();

        static {
            for (ShippingOptions.Shipper c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Shipper(String value) {
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
        public static ShippingOptions.Shipper fromValue(String value) {
            ShippingOptions.Shipper constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
