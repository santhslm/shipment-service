package com.comcast.orion.shipment.vms.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "designId",
        "devices",
		"responseStatus", "commonOTT"
})
public class BVERetreiveDeviceResponse {

    @JsonProperty("designId")
    private String designId;

	@JsonProperty("commonOTT")
	private String commonOTT;

    @JsonProperty("devices")
    private List<DeviceRetrieveResponseType> devices;

    @JsonProperty("responseStatus")
    private ResponseStatusType responseStatus;

    public String getDesignId() {
        return designId;
    }

    public void setDesignId(String designId) {
        this.designId = designId;
    }

	public String getCommonOTT() {
		return commonOTT;
	}

	public void setCommonOTT(String commonOTT) {
		this.commonOTT = commonOTT;
	}

    public List<DeviceRetrieveResponseType> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceRetrieveResponseType> devices) {
        this.devices = devices;
    }

    public ResponseStatusType getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatusType responseStatus) {
        this.responseStatus = responseStatus;
    }
}