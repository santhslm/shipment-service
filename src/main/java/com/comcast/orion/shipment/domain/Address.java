
package com.comcast.orion.shipment.domain;

import java.math.BigInteger;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "addressLine1", "city", "state", "zip" })
public class Address {

	@JsonProperty("id")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
	private BigInteger id = null;

	@JsonProperty("addressLine1")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String addressLine1;
	@JsonProperty("city")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String city;
	@JsonProperty("state")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String state;
	@JsonProperty("zip")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String zip;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@JsonProperty("addressLine1")
	public String getAddressLine1() {
		return addressLine1;
	}

	@JsonProperty("addressLine1")
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public Address withAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
		return this;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	public Address withCity(String city) {
		this.city = city;
		return this;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	public Address withState(String state) {
		this.state = state;
		return this;
	}

	@JsonProperty("zip")
	public String getZip() {
		return zip;
	}

	@JsonProperty("zip")
	public void setZip(String zip) {
		this.zip = zip;
	}

	public Address withZip(String zip) {
		this.zip = zip;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(addressLine1).append(city).append(state).append(zip).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Address) == false) {
			return false;
		}
		Address rhs = ((Address) other);
		return new EqualsBuilder().append(addressLine1, rhs.addressLine1).append(city, rhs.city)
				.append(state, rhs.state).append(zip, rhs.zip).isEquals();
	}

}
