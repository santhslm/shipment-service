package com.comcast.orion.shipment.vms.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "designId",
        "armResourceId"
})
public class BVERetrieveDeviceRequest {

    @NotNull
    @JsonProperty("designId")
    private String designId;

    @NotNull
    @JsonProperty("armResourceId")
    private List<String> armResourceId = null;

    public String getDesignId() {
        return designId;
    }

    public void setDesignId(String designId) {
        this.designId = designId;
    }

    public List<String> getArmResourceId() {
        return armResourceId;
    }

    public void setArmResourceId(List<String> armResourceId) {
        this.armResourceId = armResourceId;
    }
}
