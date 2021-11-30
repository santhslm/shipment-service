
package com.comcast.orion.shipment.domain;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * The Root Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "deviceDetail"
})
public class TDSRetrieveDeviceDetails {

    /**
     * The deviceDetail Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceDetail")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    @NotNull
    private List<DeviceDetail> deviceDetail = new ArrayList<DeviceDetail>();

    /**
     * The deviceDetail Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceDetail")
    public List<DeviceDetail> getDeviceDetail() {
        return deviceDetail;
    }

    /**
     * The deviceDetail Schema
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceDetail")
    public void setDeviceDetail(List<DeviceDetail> deviceDetail) {
        this.deviceDetail = deviceDetail;
    }

    public TDSRetrieveDeviceDetails withDeviceDetail(List<DeviceDetail> deviceDetail) {
        this.deviceDetail = deviceDetail;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(deviceDetail).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TDSRetrieveDeviceDetails) == false) {
            return false;
        }
        TDSRetrieveDeviceDetails rhs = ((TDSRetrieveDeviceDetails) other);
        return new EqualsBuilder().append(deviceDetail, rhs.deviceDetail).isEquals();
    }

}
