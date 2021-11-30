
package com.comcast.orion.shipment.omw.response;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    "status",
    "orderNumber",
    "transaction",
    "errors"
})
public class CancelShipmentOrderDetails {

    @JsonProperty("status")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String status;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @NotNull
    private String orderNumber;
    @JsonProperty("transaction")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private Transaction transaction;
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

    public CancelShipmentOrderDetails withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderNumber")
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderNumber")
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public CancelShipmentOrderDetails withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public CancelShipmentOrderDetails withTransaction(Transaction transaction) {
        this.transaction = transaction;
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

    public CancelShipmentOrderDetails withErrors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(orderNumber).append(transaction).append(errors).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CancelShipmentOrderDetails) == false) {
            return false;
        }
        CancelShipmentOrderDetails rhs = ((CancelShipmentOrderDetails) other);
        return new EqualsBuilder().append(status, rhs.status).append(orderNumber, rhs.orderNumber).append(transaction, rhs.transaction).append(errors, rhs.errors).isEquals();
    }

}
