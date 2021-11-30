
package com.comcast.orion.shipment.domain;

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
    "errorMessage"
})
public class ErrorResponse {

    /**
     * Error message
     * 
     */
    @JsonProperty("errorMessage")
    @JsonPropertyDescription("Error message")
    @ApiModelProperty(required = false, dataType = "object", position = 0, value = "Error message", example = "")
    @Valid
    private ErrorMessage errorMessage;

    /**
     * Error message
     * 
     */
    @JsonProperty("errorMessage")
    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    /**
     * Error message
     * 
     */
    @JsonProperty("errorMessage")
    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponse withErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(errorMessage).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ErrorResponse) == false) {
            return false;
        }
        ErrorResponse rhs = ((ErrorResponse) other);
        return new EqualsBuilder().append(errorMessage, rhs.errorMessage).isEquals();
    }

}
