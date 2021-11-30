
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
    "orderNumber",
    "vendorOrderId",
    "orderStatus",
    "transaction",
    "status"
})
public class CreateOrderResponse {

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
    @JsonProperty("transaction")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private Transaction transaction;
    @JsonProperty("status")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private Status status;

    @JsonProperty("orderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }

    @JsonProperty("orderNumber")
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public CreateOrderResponse withOrderNumber(String orderNumber) {
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

    public CreateOrderResponse withVendorOrderId(String vendorOrderId) {
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

    public CreateOrderResponse withOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    @JsonProperty("transaction")
    public Transaction getTransaction() {
        return transaction;
    }

    @JsonProperty("transaction")
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public CreateOrderResponse withTransaction(Transaction transaction) {
        this.transaction = transaction;
        return this;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    public CreateOrderResponse withStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orderNumber).append(vendorOrderId).append(orderStatus).append(transaction).append(status).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreateOrderResponse) == false) {
            return false;
        }
        CreateOrderResponse rhs = ((CreateOrderResponse) other);
        return new EqualsBuilder().append(orderNumber, rhs.orderNumber).append(vendorOrderId, rhs.vendorOrderId).append(orderStatus, rhs.orderStatus).append(transaction, rhs.transaction).append(status, rhs.status).isEquals();
    }

}
