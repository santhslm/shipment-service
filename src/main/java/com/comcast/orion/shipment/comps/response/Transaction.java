
package com.comcast.orion.shipment.comps.response;

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
    "transactionNumber",
    "externalTransactionId"
})
public class Transaction {

    @JsonProperty("transactionNumber")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String transactionNumber;
    @JsonProperty("externalTransactionId")
    @JsonPropertyDescription("")
    @ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
    private String externalTransactionId;

    @JsonProperty("transactionNumber")
    public String getTransactionNumber() {
        return transactionNumber;
    }

    @JsonProperty("transactionNumber")
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Transaction withTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
        return this;
    }

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(transactionNumber).append(externalTransactionId).toHashCode();
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
        return new EqualsBuilder().append(transactionNumber, rhs.transactionNumber).append(externalTransactionId, rhs.externalTransactionId).isEquals();
    }

}
