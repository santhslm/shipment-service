
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
    "shipmentTrackingDetail"
})
public class ShipmentTrackingDetails {

    @JsonProperty("shipmentTrackingDetail")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    private List<ShipmentTrackingDetail> shipmentTrackingDetail = new ArrayList<ShipmentTrackingDetail>();

    @JsonProperty("shipmentTrackingDetail")
    public List<ShipmentTrackingDetail> getShipmentTrackingDetail() {
        return shipmentTrackingDetail;
    }

    @JsonProperty("shipmentTrackingDetail")
    public void setShipmentTrackingDetail(List<ShipmentTrackingDetail> shipmentTrackingDetail) {
        this.shipmentTrackingDetail = shipmentTrackingDetail;
    }

    public ShipmentTrackingDetails withShipmentTrackingDetail(List<ShipmentTrackingDetail> shipmentTrackingDetail) {
        this.shipmentTrackingDetail = shipmentTrackingDetail;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(shipmentTrackingDetail).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ShipmentTrackingDetails) == false) {
            return false;
        }
        ShipmentTrackingDetails rhs = ((ShipmentTrackingDetails) other);
        return new EqualsBuilder().append(shipmentTrackingDetail, rhs.shipmentTrackingDetail).isEquals();
    }

}
