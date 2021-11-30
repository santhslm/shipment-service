
package com.comcast.orion.shipment.comps.request;

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
    "lineItem"
})
public class LineItems {

    @JsonProperty("lineItem")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    private List<LineItem> lineItem = new ArrayList<LineItem>();

    @JsonProperty("lineItem")
    public List<LineItem> getLineItem() {
        return lineItem;
    }

    @JsonProperty("lineItem")
    public void setLineItem(List<LineItem> lineItem) {
        this.lineItem = lineItem;
    }

    public LineItems withLineItem(List<LineItem> lineItem) {
        this.lineItem = lineItem;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lineItem).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LineItems) == false) {
            return false;
        }
        LineItems rhs = ((LineItems) other);
        return new EqualsBuilder().append(lineItem, rhs.lineItem).isEquals();
    }

}
