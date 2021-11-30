
package com.comcast.orion.shipment.onp;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * The Orderinfo Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
    "lineItems",
    "company"
})
public class OrderInfo {

    /**
     * The Lineitems Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("lineItems")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
    @Valid
    private List<LineItem> lineItems = new ArrayList<LineItem>();
    /**
     * The Company Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("company")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    @Pattern(regexp = "^(.*)$")
    private String company = "";

    /**
     * The Lineitems Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("lineItems")
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * The Lineitems Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("lineItems")
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public OrderInfo withLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        return this;
    }

    /**
     * The Company Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    /**
     * The Company Schema
     * <p>
     * 
     * 
     */
    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    public OrderInfo withCompany(String company) {
        this.company = company;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(lineItems).append(company).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderInfo) == false) {
            return false;
        }
        OrderInfo rhs = ((OrderInfo) other);
        return new EqualsBuilder().append(lineItems, rhs.lineItems).append(company, rhs.company).isEquals();
    }

}
