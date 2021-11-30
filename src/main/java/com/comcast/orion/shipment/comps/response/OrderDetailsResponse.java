
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
    "orderInfo",
    "orderNumber",
    "orderStatus",
    "status"
})
public class OrderDetailsResponse {

    @JsonProperty("orderInfo")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private OrderInfo orderInfo;
    @JsonProperty("orderNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String orderNumber;
    @JsonProperty("orderStatus")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String orderStatus;
    @JsonProperty("status")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    private Status status;

    @JsonProperty("orderInfo")
    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    @JsonProperty("orderInfo")
    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public OrderDetailsResponse withOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
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

    public OrderDetailsResponse withOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public OrderDetailsResponse withOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public OrderDetailsResponse withStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orderInfo).append(orderNumber).append(orderStatus).append(status).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderDetailsResponse) == false) {
            return false;
        }
        OrderDetailsResponse rhs = ((OrderDetailsResponse) other);
        return new EqualsBuilder().append(orderInfo, rhs.orderInfo).append(orderNumber, rhs.orderNumber).append(orderStatus, rhs.orderStatus).append(status, rhs.status).isEquals();
    }

}
