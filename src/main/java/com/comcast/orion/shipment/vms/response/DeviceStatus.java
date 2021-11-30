package com.comcast.orion.shipment.vms.response;

public enum DeviceStatus {
    SUCCESS("Success"),
    DEVICE_NOT_FOUND("Device not found");

    String str;
    DeviceStatus(String str) {
        this.str = str;
    }
}
