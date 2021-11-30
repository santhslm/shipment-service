
package com.comcast.orion.shipment.vms.request;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "designId",
    "devices"
})
public class DeviceUpdateRequest {

    @JsonProperty("designId")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String designId;
    @JsonProperty("devices")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    private List<Device> devices = new ArrayList<Device>();

    @JsonProperty("designId")
    public String getDesignId() {
        return designId;
    }

    @JsonProperty("designId")
    public void setDesignId(String designId) {
        this.designId = designId;
    }

    public DeviceUpdateRequest withDesignId(String designId) {
        this.designId = designId;
        return this;
    }

    @JsonProperty("devices")
    public List<Device> getDevices() {
        return devices;
    }

    @JsonProperty("devices")
    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public DeviceUpdateRequest withDevices(List<Device> devices) {
        this.devices = devices;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(designId).append(devices).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DeviceUpdateRequest) == false) {
            return false;
        }
        DeviceUpdateRequest rhs = ((DeviceUpdateRequest) other);
        return new EqualsBuilder().append(designId, rhs.designId).append(devices, rhs.devices).isEquals();
    }

}
