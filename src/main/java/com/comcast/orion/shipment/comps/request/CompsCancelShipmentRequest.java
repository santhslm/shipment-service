
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
@JsonPropertyOrder({ "orderNumber" })
public class CompsCancelShipmentRequest {

	@JsonProperty("orderNumber")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	@Valid
	private String orderNumber;

	@JsonProperty("orderNumber")
	public String getOrderNumber() {
		return orderNumber;
	}

	@JsonProperty("orderNumber")
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public CompsCancelShipmentRequest withOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(orderNumber).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof CompsCancelShipmentRequest) == false) {
			return false;
		}
		CompsCancelShipmentRequest rhs = ((CompsCancelShipmentRequest) other);
		return new EqualsBuilder().append(orderNumber, rhs.orderNumber).isEquals();
	}

}
