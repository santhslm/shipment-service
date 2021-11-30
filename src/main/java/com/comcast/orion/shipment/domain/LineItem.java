package com.comcast.orion.shipment.domain;

import java.math.BigInteger;

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
    "id",
    "deviceMake",
    "deviceModel",
    "deviceType",
    "resourceId",
    "quantity",
    "createdDate",
    "modifiedDate",
    "deviceName",
    "firstName",
    "lastName",
    "macAddress",
    "serialNumber",
    "requiredWANLicenseCount",
    "currentWANLicenseCount"
})
public class LineItem {

    /**
     * Internal  ID
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
    private BigInteger id ;
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
    @NotNull(message="deviceMake should not be null")
    private String deviceMake ;
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
    @NotNull(message="deviceModel should not be null")
    private String deviceModel="N/A" ;
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
    @NotNull(message="deviceType should not be null")
    private String deviceType ;
    /**
     * The resourceId Device
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("resourceId")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @NotNull(message="resourceId should not be null")
    private String resourceId = "0";
    /**
     * The quantity of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("quantity")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
    private Object quantity = null;
    /**
     * The deviceName of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("createdDate")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String createdDate ;
    /**
     * The deviceName of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("modifiedDate")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String modifiedDate ;
    /**
     * The deviceName of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("deviceName")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String deviceName = "1";
    /**
     * The firstName to whom Device assigned
     * <p>
     * 
     * 
     */
    @JsonProperty("firstName")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String firstName = "";
    /**
     * The lastName to whom Device assigned
     * <p>
     * 
     * 
     */
    @JsonProperty("lastName")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String lastName = "";
    /**
     * The macAddress of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("macAddress")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String macAddress = "";
    /**
     * The serialNumber of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("serialNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String serialNumber = "";

    /**
     * The quantity of requiredWANLicenseCount
     * <p>
     * 
     * 
     */
    @JsonProperty("requiredWANLicenseCount")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
    private Integer requiredWANLicenseCount ;
    /**
     * The deviceName of currentWANLicenseCount
     * <p>
     * 
     * 
     */
    @JsonProperty("currentWANLicenseCount")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
    private Integer currentWANLicenseCount ;
    
    /**
     * Internal  ID
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    public BigInteger getId() {
        return id;
    }

    /**
     * Internal  ID
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    public void setId(BigInteger id) {
        this.id = id;
    }

    public LineItem withId(BigInteger id) {
        this.id = id;
        return this;
    }

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
	 * @return the requiredWANLicenseCount
	 */
	public Integer getRequiredWANLicenseCount() {
		return requiredWANLicenseCount;
	}

	/**
	 * @param requiredWANLicenseCount the requiredWANLicenseCount to set
	 */
	public void setRequiredWANLicenseCount(Integer requiredWANLicenseCount) {
		this.requiredWANLicenseCount = requiredWANLicenseCount;
	}

	/**
	 * @return the currentWANLicenseCount
	 */
	public Integer getCurrentWANLicenseCount() {
		return currentWANLicenseCount;
	}

	/**
	 * @param currentWANLicenseCount the currentWANLicenseCount to set
	 */
	public void setCurrentWANLicenseCount(Integer currentWANLicenseCount) {
		this.currentWANLicenseCount = currentWANLicenseCount;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
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
     * The resourceId Device
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
     * The resourceId Device
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
     * The quantity of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("quantity")
    public Object getQuantity() {
        return quantity;
    }

    /**
     * The quantity of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("quantity")
    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    public LineItem withQuantity(Object quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * The deviceName of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("deviceName")
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * The deviceName of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("deviceName")
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public LineItem withDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    /**
     * The firstName to whom Device assigned
     * <p>
     * 
     * 
     */
    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    /**
     * The firstName to whom Device assigned
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
     * The lastName to whom Device assigned
     * <p>
     * 
     * 
     */
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    /**
     * The lastName to whom Device assigned
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
     * The macAddress of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("macAddress")
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * The macAddress of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("macAddress")
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public LineItem withMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    /**
     * The serialNumber of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * The serialNumber of Device
     * <p>
     * 
     * 
     */
    @JsonProperty("serialNumber")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LineItem withSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(deviceMake).append(deviceModel).append(deviceType).append(resourceId).append(quantity).append(deviceName).append(firstName).append(lastName).append(macAddress).append(serialNumber).toHashCode();
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
        return new EqualsBuilder().append(id, rhs.id).append(deviceMake, rhs.deviceMake).append(deviceModel, rhs.deviceModel).append(deviceType, rhs.deviceType).append(resourceId, rhs.resourceId).append(quantity, rhs.quantity).append(deviceName, rhs.deviceName).append(firstName, rhs.firstName).append(lastName, rhs.lastName).append(macAddress, rhs.macAddress).append(serialNumber, rhs.serialNumber).isEquals();
    }

}
