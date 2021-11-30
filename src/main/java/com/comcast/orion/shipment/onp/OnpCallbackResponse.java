package com.comcast.orion.shipment.onp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "status",
    "errors"
})
public class OnpCallbackResponse{
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("errors")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
	@Valid
	private List<Error> errors = new ArrayList<Error>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	
}
