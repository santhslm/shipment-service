
package com.comcast.orion.shipment.integration;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "access_token", "token_type", "expires_in" })
public class GetAuthorizationHeader {

	@JsonProperty("access_token")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String accessToken;
	@JsonProperty("token_type")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String tokenType;
	@JsonProperty("expires_in")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "integer", position = 0, value = "", example = "")
	private Timestamp expiresIn;
	@JsonProperty("token")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String token;

	@JsonProperty("token")
	public String getToken() {
		return token;
	}

	@JsonProperty("token")
	public void setToken(String token) {
		this.token = token;
	}

	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	@JsonProperty("access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public GetAuthorizationHeader withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	@JsonProperty("token_type")
	public String getTokenType() {
		return tokenType;
	}

	@JsonProperty("token_type")
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public GetAuthorizationHeader withTokenType(String tokenType) {
		this.tokenType = tokenType;
		return this;
	}

	@JsonProperty("expires_in")
	public Timestamp getExpiresIn() {
		return expiresIn;
	}

	@JsonProperty("expires_in")
	public void setExpiresIn(Timestamp expiresIn) {
		this.expiresIn = expiresIn;
	}

	public GetAuthorizationHeader withExpiresIn(Timestamp expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(accessToken).append(tokenType).append(expiresIn).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof GetAuthorizationHeader) == false) {
			return false;
		}
		GetAuthorizationHeader rhs = ((GetAuthorizationHeader) other);
		return new EqualsBuilder().append(accessToken, rhs.accessToken).append(tokenType, rhs.tokenType)
				.append(expiresIn, rhs.expiresIn).isEquals();
	}

}
