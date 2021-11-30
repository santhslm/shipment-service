package com.comcast.orion.shipment.omw.response;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "status", "message"})
public class ShipmentOrderResponse {

	/**
	 * 
	 * (Required)
	 * 
	 */
	@NotNull
	@JsonProperty("status")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = true, dataType = "string", position = 0, value = "", example = "CONTRACT_VOILATION")
	@Size(min = 1, max = 50)
	private String status;
	/**
	 * 
	 * (Required)
	 * 
	 */
	@NotNull
	@JsonProperty("message")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = true, dataType = "string", position = 0, value = "", example = "Field Name - Mandatory Field is missing in the request")
	@Size(min = 1, max = 500)
	private String message;
	
	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	public ShipmentOrderResponse withStatus(String status) {
		this.status = status;
		return this;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	public ShipmentOrderResponse withMessage(String message) {
		this.message = message;
		return this;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(status).append(message).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Error) == false) {
			return false;
		}
		ShipmentOrderResponse rhs = ((ShipmentOrderResponse) other);
		return new EqualsBuilder().append(status, rhs.status).append(message, rhs.message).isEquals();
	}

}
