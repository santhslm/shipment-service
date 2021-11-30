
package com.comcast.orion.shipment.domain;

import java.math.BigInteger;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "company", "firstName", "lastName", "phone", "address" })
public class ShippingAddress {

	/**
	 * Internal ID
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("id")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
	private BigInteger id = null;

	@JsonProperty("company")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String company;
	@JsonProperty("firstName")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String firstName;
	@JsonProperty("lastName")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String lastName;
	@JsonProperty("phone")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	// @Pattern(regexp = "@\"^\\d{11}$", message = "phone number should be 10
	// digit")
	@Digits(integer = 10, fraction = 0, message = "Invalid TelephoneNumber")
	@Min(value = 999999999, message = "Invalid TelephoneNumber")
	private String phone;
	@JsonProperty("address")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
	@Valid
	private Address address;

	@JsonProperty("company")
	public String getCompany() {
		return company;
	}

	@JsonProperty("company")
	public void setCompany(String company) {
		this.company = company;
	}

	public ShippingAddress withCompany(String company) {
		this.company = company;
		return this;
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public ShippingAddress withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ShippingAddress withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	@JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ShippingAddress withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	public ShippingAddress withAddress(Address address) {
		this.address = address;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(company).append(firstName).append(lastName).append(phone).append(address)
				.toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof ShippingAddress) == false) {
			return false;
		}
		ShippingAddress rhs = ((ShippingAddress) other);
		return new EqualsBuilder().append(company, rhs.company).append(firstName, rhs.firstName)
				.append(lastName, rhs.lastName).append(phone, rhs.phone).append(address, rhs.address).isEquals();
	}

}
