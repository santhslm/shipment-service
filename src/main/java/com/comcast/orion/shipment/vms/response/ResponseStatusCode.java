package com.comcast.orion.shipment.vms.response;

public enum ResponseStatusCode {
    SUCCESS("Success"),DESIGN_ID_NOT_FOUND("Design Id is not found"), ONE_OR_MORE_DEVICES_NOT_FOUND ("One or more devices not found");

    private String value;
    ResponseStatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
