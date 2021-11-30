package com.comcast.orion.shipment.vms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "description"
})
public class ResponseStatusType {

    @JsonProperty("status")
    private ResponseStatusCode statusCode;

    @JsonProperty("description")
    private String description;

    public ResponseStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(ResponseStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
