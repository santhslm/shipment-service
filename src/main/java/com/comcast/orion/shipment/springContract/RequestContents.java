package com.comcast.orion.shipment.springContract;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Component
//@ConfigurationProperties("requestContents")
public class RequestContents {

	private String path;
	private String requestBodyJSON;
	private Map<String, String> matchParameters;
	private Map<String, String> requestHeaders;
	
	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}
	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}
	public Map<String, String> getMatchParameters() {
		return matchParameters;
	}
	public void setMatchParameters(Map<String, String> matchParameters) {
		this.matchParameters = matchParameters;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRequestBodyJSON() {
		return requestBodyJSON;
	}
	public void setRequestBodyJSON(String requestBodyJSON) {
		this.requestBodyJSON = requestBodyJSON;
	}
	@Override
	public String toString() {
		return "RequestContents [path=" + path + ", requestBodyJSON=" + requestBodyJSON + ", matchParameters="
				+ matchParameters + ", requestHeaders=" + requestHeaders + ", getRequestHeaders()="
				+ getRequestHeaders() + ", getMatchParameters()=" + getMatchParameters() + ", getPath()=" + getPath()
				+ ", getRequestBodyJSON()=" + getRequestBodyJSON() + "]";
	}
	
	
}
