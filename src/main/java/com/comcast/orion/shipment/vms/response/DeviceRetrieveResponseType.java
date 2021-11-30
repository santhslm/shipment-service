package com.comcast.orion.shipment.vms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "device",
        "deviceStatus"
})
public class DeviceRetrieveResponseType {

    @JsonProperty("device")
    private Device device;

    @JsonProperty("deviceStatus")
    private DeviceStatus deviceStatus;

    @JsonProperty("device")
    public Device getDevice() {
        return device;
    }

    @JsonProperty("device")
    public void setDevice(Device device) {
        this.device = device;
    }

    @JsonProperty("deviceStatus")
    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    @JsonProperty("deviceStatus")
    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
