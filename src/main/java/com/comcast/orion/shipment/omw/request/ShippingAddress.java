
package com.comcast.orion.shipment.omw.request;

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
 * The Shippingaddress Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "company",
    "firstName",
    "lastName",
    "phone",
    "address"
})
public class ShippingAddress {

    /**
     * The Company Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("company")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String company = "";
    /**
     * The Firstname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("firstName")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String firstName = "";
    /**
     * The Lastname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("lastName")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String lastName = "";
    /**
     * The Phone Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("phone")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String phone = "";
    /**
     * The Address Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("address")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private Address address;

    /**
     * The Company Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    /**
     * The Company Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    public ShippingAddress withCompany(String company) {
        this.company = company;
        return this;
    }

    /**
     * The Firstname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    /**
     * The Firstname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ShippingAddress withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * The Lastname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    /**
     * The Lastname Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ShippingAddress withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * The Phone Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    /**
     * The Phone Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ShippingAddress withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * The Address Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    /**
     * The Address Schema
     * <p>
     * 
     * (Required)
     * 
     */
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
        return new HashCodeBuilder().append(company).append(firstName).append(lastName).append(phone).append(address).toHashCode();
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
        return new EqualsBuilder().append(company, rhs.company).append(firstName, rhs.firstName).append(lastName, rhs.lastName).append(phone, rhs.phone).append(address, rhs.address).isEquals();
    }

}
