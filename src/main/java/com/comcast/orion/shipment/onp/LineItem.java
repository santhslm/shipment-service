
package com.comcast.orion.shipment.onp;

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
 * The Items Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "deviceMake",
    "deviceModel",
    "deviceType",
    "firstName",
    "lastName",
    "resourceId",
    "deviceMacAddress",
    "deviceSerialNumber"
})
public class LineItem {

    /**
     * The Devicemake Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceMake")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String deviceMake = "";
    /**
     * The Devicemodel Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceModel")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String deviceModel = "";
    /**
     * The Devicetype Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceType")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String deviceType = "";
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
     * The Resourceid Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("resourceId")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    @NotNull
    private String resourceId = "";
    /**
     * The Devicemacaddress Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deviceMacAddress")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String deviceMacAddress = "";
    /**
     * The deviceSerialNumber Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deviceSerialNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String deviceSerialNumber = "";

    /**
     * The Devicemake Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceMake")
    public String getDeviceMake() {
        return deviceMake;
    }

    /**
     * The Devicemake Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceMake")
    public void setDeviceMake(String deviceMake) {
        this.deviceMake = deviceMake;
    }

    public LineItem withDeviceMake(String deviceMake) {
        this.deviceMake = deviceMake;
        return this;
    }

    /**
     * The Devicemodel Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceModel")
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * The Devicemodel Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceModel")
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public LineItem withDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    /**
     * The Devicetype Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceType")
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * The Devicetype Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceType")
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public LineItem withDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    public LineItem withFirstName(String firstName) {
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

    public LineItem withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * The Resourceid Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("resourceId")
    public String getResourceId() {
        return resourceId;
    }

    /**
     * The Resourceid Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("resourceId")
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public LineItem withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    /**
     * The Devicemacaddress Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deviceMacAddress")
    public String getDeviceMacAddress() {
        return deviceMacAddress;
    }

    /**
     * The Devicemacaddress Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deviceMacAddress")
    public void setDeviceMacAddress(String deviceMacAddress) {
        this.deviceMacAddress = deviceMacAddress;
    }

    public LineItem withDeviceMacAddress(String deviceMacAddress) {
        this.deviceMacAddress = deviceMacAddress;
        return this;
    }

    /**
     * The deviceSerialNumber Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deviceSerialNumber")
    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    /**
     * The deviceSerialNumber Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("deviceSerialNumber")
    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public LineItem withDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(deviceMake).append(deviceModel).append(deviceType).append(firstName).append(lastName).append(resourceId).append(deviceMacAddress).append(deviceSerialNumber).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItem) == false) {
            return false;
        }
        LineItem rhs = ((LineItem) other);
        return new EqualsBuilder().append(deviceMake, rhs.deviceMake).append(deviceModel, rhs.deviceModel).append(deviceType, rhs.deviceType).append(firstName, rhs.firstName).append(lastName, rhs.lastName).append(resourceId, rhs.resourceId).append(deviceMacAddress, rhs.deviceMacAddress).append(deviceSerialNumber, rhs.deviceSerialNumber).isEquals();
    }

}
