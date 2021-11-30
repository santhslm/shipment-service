
package com.comcast.orion.shipment.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.comcast.orion.shipment.omw.request.LineItem;
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
@JsonPropertyOrder({ "deviceMake", "deviceModel", "deviceType", "resourceId", "quantity", "deviceName",
		"requiredWANLicenseCount", "currentWANLicenseCount" })
public class DeviceDetail {

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
	private String deviceModel = "N/A";
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
	 * The resourceId Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("resourceId")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String resourceId = "0";
	/**
	 * The quantity of Product
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("quantity")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
	private Object quantity = null;
	/**
	 * For EdgeMark devices, populate the required WAN license Count
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("requiredWANLicenseCount")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "integer", position = 0, value = "", example = "")
	private Integer requiredWANLicenseCount;
	/**
	 * For EdgeMark devices, populate the required WAN license Count
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("currentWANLicenseCount")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "integer", position = 0, value = "", example = "")
	private Integer currentWANLicenseCount;
	/**
	 * The deviceName of Product
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("deviceName")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
	private Object deviceName = null;

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

	public DeviceDetail withDeviceMake(String deviceMake) {
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

	public DeviceDetail withDeviceModel(String deviceModel) {
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

	public DeviceDetail withDeviceType(String deviceType) {
		this.deviceType = deviceType;
		return this;
	}

	/**
	 * The resourceId Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("resourceId")
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * The resourceId Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("resourceId")
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public DeviceDetail withResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	/**
	 * The quantity of Product
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("quantity")
	public Object getQuantity() {
		return quantity;
	}

	/**
	 * The quantity of Product
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("quantity")
	public void setQuantity(Object quantity) {
		this.quantity = quantity;
	}

	public DeviceDetail withQuantity(Object quantity) {
		this.quantity = quantity;
		return this;
	}

	/**
	 * The deviceName of Product
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("deviceName")
	public Object getDeviceName() {
		return deviceName;
	}

	/**
	 * The deviceName of Product
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("deviceName")
	public void setDeviceName(Object deviceName) {
		this.deviceName = deviceName;
	}

	public DeviceDetail withDeviceName(Object deviceName) {
		this.deviceName = deviceName;
		return this;
	}

	/**
	 * For EdgeMark devices, populate the required WAN license Count
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("requiredWANLicenseCount")
	public Integer getRequiredWANLicenseCount() {
		return requiredWANLicenseCount;
	}

	/**
	 * For EdgeMark devices, populate the required WAN license Count
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("requiredWANLicenseCount")
	public void setRequiredWANLicenseCount(Integer requiredWANLicenseCount) {
		this.requiredWANLicenseCount = requiredWANLicenseCount;
	}

	public DeviceDetail withRequiredWANLicenseCount(Integer requiredWANLicenseCount) {
		this.requiredWANLicenseCount = requiredWANLicenseCount;
		return this;
	}

	/**
	 * For EdgeMark devices, populate the required WAN license Count
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("currentWANLicenseCount")
	public Integer getCurrentWANLicenseCount() {
		return currentWANLicenseCount;
	}

	/**
	 * For EdgeMark devices, populate the required WAN license Count
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("currentWANLicenseCount")
	public void setCurrentWANLicenseCount(Integer currentWANLicenseCount) {
		this.currentWANLicenseCount = currentWANLicenseCount;
	}

	public DeviceDetail withCurrentWANLicenseCount(Integer currentWANLicenseCount) {
		this.currentWANLicenseCount = currentWANLicenseCount;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(deviceMake).append(deviceModel).append(deviceType).append(resourceId)
				.append(quantity).append(deviceName).append(requiredWANLicenseCount).append(currentWANLicenseCount)
				.toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof DeviceDetail) == false) {
			return false;
		}
		DeviceDetail rhs = ((DeviceDetail) other);
		return new EqualsBuilder().append(deviceMake, rhs.deviceMake).append(deviceModel, rhs.deviceModel)
				.append(deviceType, rhs.deviceType).append(resourceId, rhs.resourceId).append(quantity, rhs.quantity)
				.append(deviceName, rhs.deviceName).append(requiredWANLicenseCount, rhs.requiredWANLicenseCount)
				.append(currentWANLicenseCount, rhs.currentWANLicenseCount).isEquals();
	}

}
