package com.comcast.orion.shipment.vms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "armResourceId", "netXOrderId", "deviceMake", "deviceModel", "deviceType", "firstName", "lastName",
		"lineCount", "macAddress", "serialNumber" })
public class Device {

	@JsonProperty("armResourceId")
	private String armResourceId;

	@JsonProperty("netXOrderId")
	private String netXOrderId;

	@JsonProperty("deviceMake")
	private String deviceMake;

	@JsonProperty("deviceModel")
	private String deviceModel;

	@JsonProperty("deviceType")
	private String deviceType;

	@JsonProperty("macAddress")
	private String macAddress;

	@JsonProperty("serialNumber")
	private String serialNumber;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("lineCount")
	private String lineCount;

	public String getArmResourceId() {
		return armResourceId;
	}

	public void setArmResourceId(String armResourceId) {
		this.armResourceId = armResourceId;
	}

	public String getNetXOrderId() {
		return netXOrderId;
	}

	public void setNetXOrderId(String netXOrderId) {
		this.netXOrderId = netXOrderId;
	}

	public String getDeviceMake() {
		return deviceMake;
	}

	public void setDeviceMake(String deviceMake) {
		this.deviceMake = deviceMake;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLineCount() {
		return lineCount;
	}

	public void setLineCount(String lineCount) {
		this.lineCount = lineCount;
	}
}