
package com.comcast.orion.shipment.vms.request;

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
    "armResourceId",
    "macAddress",
    "serialNumber"
})
public class Device {

    @JsonProperty("armResourceId")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String armResourceId;
    @JsonProperty("macAddress")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String macAddress;
    @JsonProperty("serialNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String serialNumber;

    @JsonProperty("armResourceId")
    public String getArmResourceId() {
        return armResourceId;
    }

    @JsonProperty("armResourceId")
    public void setArmResourceId(String armResourceId) {
        this.armResourceId = armResourceId;
    }

    public Device withArmResourceId(String armResourceId) {
        this.armResourceId = armResourceId;
        return this;
    }

    @JsonProperty("macAddress")
    public String getMacAddress() {
        return macAddress;
    }

    @JsonProperty("macAddress")
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Device withMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    @JsonProperty("serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    @JsonProperty("serialNumber")
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Device withSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(armResourceId).append(macAddress).append(serialNumber).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Device) == false) {
            return false;
        }
        Device rhs = ((Device) other);
        return new EqualsBuilder().append(armResourceId, rhs.armResourceId).append(macAddress, rhs.macAddress).append(serialNumber, rhs.serialNumber).isEquals();
    }

}
