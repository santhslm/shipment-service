
package com.comcast.orion.shipment.onp;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
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
 * The Root Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "status",
    "orderNumber",
    "orderStatus",
    "transaction",
    "errors",
    "orderInfo",
    "vendorOrderDetails"
})
public class ShipmentStatusNotification {

    /**
     * The Status Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String status = "";
    /**
     * The Ordernumber Schema
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
    private String orderNumber = "";
    /**
     * The Orderstatus Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderStatus")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String orderStatus = "";
    /**
     * The Transaction Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("transaction")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private Transaction transaction;
    /**
     * The Errors Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("errors")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    private List<Error> errors = new ArrayList<Error>();
    /**
     * The Orderinfo Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("orderInfo")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private OrderInfo orderInfo;
    /**
     * The Vendororderdetails Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("vendorOrderDetails")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    private List<VendorOrderDetail> vendorOrderDetails = new ArrayList<VendorOrderDetail>();

    /**
     * The Status Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * The Status Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public ShipmentStatusNotification withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * The Ordernumber Schema
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
     * The Ordernumber Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderNumber")
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ShipmentStatusNotification withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    /**
     * The Orderstatus Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * The Orderstatus Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("orderStatus")
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShipmentStatusNotification withOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    /**
     * The Transaction Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("transaction")
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * The Transaction Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("transaction")
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public ShipmentStatusNotification withTransaction(Transaction transaction) {
        this.transaction = transaction;
        return this;
    }

    /**
     * The Errors Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("errors")
    public List<Error> getErrors() {
        return errors;
    }

    /**
     * The Errors Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("errors")
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public ShipmentStatusNotification withErrors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    /**
     * The Orderinfo Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("orderInfo")
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    /**
     * The Orderinfo Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("orderInfo")
    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public ShipmentStatusNotification withOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
        return this;
    }

    /**
     * The Vendororderdetails Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("vendorOrderDetails")
    public List<VendorOrderDetail> getVendorOrderDetails() {
        return vendorOrderDetails;
    }

    /**
     * The Vendororderdetails Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("vendorOrderDetails")
    public void setVendorOrderDetails(List<VendorOrderDetail> vendorOrderDetails) {
        this.vendorOrderDetails = vendorOrderDetails;
    }

    public ShipmentStatusNotification withVendorOrderDetails(List<VendorOrderDetail> vendorOrderDetails) {
        this.vendorOrderDetails = vendorOrderDetails;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(status).append(orderNumber).append(orderStatus).append(transaction).append(errors).append(orderInfo).append(vendorOrderDetails).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShipmentStatusNotification) == false) {
            return false;
        }
        ShipmentStatusNotification rhs = ((ShipmentStatusNotification) other);
        return new EqualsBuilder().append(status, rhs.status).append(orderNumber, rhs.orderNumber).append(orderStatus, rhs.orderStatus).append(transaction, rhs.transaction).append(errors, rhs.errors).append(orderInfo, rhs.orderInfo).append(vendorOrderDetails, rhs.vendorOrderDetails).isEquals();
    }

}
