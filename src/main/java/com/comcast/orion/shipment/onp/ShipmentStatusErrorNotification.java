
package com.comcast.orion.shipment.onp;

import java.util.ArrayList;
import java.util.List;
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
    "status",
    "orderNumber",
    "vendorOrderId",
    "orderStatus",
    "errors"
})
public class ShipmentStatusErrorNotification {

    @JsonProperty("status")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String status;
    @JsonProperty("orderNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String orderNumber;
    @JsonProperty("vendorOrderId")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String vendorOrderId;
    @JsonProperty("orderStatus")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String orderStatus;
    @JsonProperty("errors")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    private List<Error> errors = new ArrayList<Error>();

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public ShipmentStatusErrorNotification withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("orderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ShipmentStatusErrorNotification withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    @JsonProperty("vendorOrderId")
    public String getVendorOrderId() {
        return vendorOrderId;
    }

    @JsonProperty("vendorOrderId")
    public void setVendorOrderId(String vendorOrderId) {
        this.vendorOrderId = vendorOrderId;
    }

    public ShipmentStatusErrorNotification withVendorOrderId(String vendorOrderId) {
        this.vendorOrderId = vendorOrderId;
        return this;
    }

    @JsonProperty("orderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    @JsonProperty("orderStatus")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShipmentStatusErrorNotification withOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    @JsonProperty("errors")
    public List<Error> getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public ShipmentStatusErrorNotification withErrors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(orderNumber).append(vendorOrderId).append(orderStatus).append(errors).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShipmentStatusErrorNotification) == false) {
            return false;
        }
        ShipmentStatusErrorNotification rhs = ((ShipmentStatusErrorNotification) other);
        return new EqualsBuilder().append(status, rhs.status).append(orderNumber, rhs.orderNumber).append(vendorOrderId, rhs.vendorOrderId).append(orderStatus, rhs.orderStatus).append(errors, rhs.errors).isEquals();
    }

}
