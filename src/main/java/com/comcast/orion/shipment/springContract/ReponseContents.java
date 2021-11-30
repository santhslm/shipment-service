/**
 * 
 */
package com.comcast.orion.shipment.springContract;

import java.util.Map;

/**
 * @author 512833
 *
 */
public class ReponseContents {

	private String responseBodyJSON;
	private int fixedDelay;
	private int httpStatusCode;
	private Map<String, String> responseHeaders;
	
	public String getResponseBodyJSON() {
		return responseBodyJSON;
	}
	public void setResponseBodyJSON(String responseBodyJSON) {
		this.responseBodyJSON = responseBodyJSON;
	}
	public int getFixedDelay() {
		return fixedDelay;
	}
	public void setFixedDelay(int fixedDelay) {
		this.fixedDelay = fixedDelay;
	}
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}
	public void setResponseHeaders(Map<String, String> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
	@Override
	public String toString() {
		return "ReponseContents [responseBodyJSON=" + responseBodyJSON + ", fixedDelay=" + fixedDelay
				+ ", httpStatusCode=" + httpStatusCode + ", responseHeaders=" + responseHeaders
				+ ", getResponseBodyJSON()=" + getResponseBodyJSON() + ", getFixedDelay()=" + getFixedDelay()
				+ ", getHttpStatusCode()=" + getHttpStatusCode() + ", getResponseHeaders()=" + getResponseHeaders()
				+ "]";
	}
	
	
}
