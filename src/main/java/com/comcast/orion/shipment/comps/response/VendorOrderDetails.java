
package com.comcast.orion.shipment.comps.response;

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
    "vendorOrderDetailInfo"
})
public class VendorOrderDetails {

    @JsonProperty("vendorOrderDetailInfo")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    private List<VendorOrderDetailInfo> vendorOrderDetailInfo = new ArrayList<VendorOrderDetailInfo>();

    @JsonProperty("vendorOrderDetailInfo")
    public List<VendorOrderDetailInfo> getVendorOrderDetailInfo() {
        return vendorOrderDetailInfo;
    }

    @JsonProperty("vendorOrderDetailInfo")
    public void setVendorOrderDetailInfo(List<VendorOrderDetailInfo> vendorOrderDetailInfo) {
        this.vendorOrderDetailInfo = vendorOrderDetailInfo;
    }

    public VendorOrderDetails withVendorOrderDetailInfo(List<VendorOrderDetailInfo> vendorOrderDetailInfo) {
        this.vendorOrderDetailInfo = vendorOrderDetailInfo;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(vendorOrderDetailInfo).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof VendorOrderDetails) == false) {
            return false;
        }
        VendorOrderDetails rhs = ((VendorOrderDetails) other);
        return new EqualsBuilder().append(vendorOrderDetailInfo, rhs.vendorOrderDetailInfo).isEquals();
    }

}
