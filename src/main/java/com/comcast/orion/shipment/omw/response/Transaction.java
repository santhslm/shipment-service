
package com.comcast.orion.shipment.omw.response;

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
    "externalTransactionId",
    "purchaseOrderNumber"
})
public class Transaction {

    @JsonProperty("externalTransactionId")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String externalTransactionId;
    @JsonProperty("purchaseOrderNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String purchaseOrderNumber;

    @JsonProperty("externalTransactionId")
    public String getExternalTransactionId() {
        return externalTransactionId;
    }

    @JsonProperty("externalTransactionId")
    public void setExternalTransactionId(String externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
    }

    public Transaction withExternalTransactionId(String externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
        return this;
    }

    @JsonProperty("purchaseOrderNumber")
    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    @JsonProperty("purchaseOrderNumber")
    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public Transaction withPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(externalTransactionId).append(purchaseOrderNumber).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Transaction) == false) {
            return false;
        }
        Transaction rhs = ((Transaction) other);
        return new EqualsBuilder().append(externalTransactionId, rhs.externalTransactionId).append(purchaseOrderNumber, rhs.purchaseOrderNumber).isEquals();
    }

}
