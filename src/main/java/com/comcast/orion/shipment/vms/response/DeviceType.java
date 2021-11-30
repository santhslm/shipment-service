package com.comcast.orion.shipment.vms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum DeviceType {
    GateWay, PhoneAccessory
}
