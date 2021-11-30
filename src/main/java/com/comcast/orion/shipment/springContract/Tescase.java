/**
 * 
 */
package com.comcast.orion.shipment.springContract;

/**
 * @author 512833
 *
 */
public class Tescase {

	private String method;
	private String methodType;
	private RequestContents requestContents;
	private ReponseContents reponseContents;
	
	public ReponseContents getReponseContents() {
		return reponseContents;
	}

	public void setReponseContents(ReponseContents reponseContents) {
		this.reponseContents = reponseContents;
	}

	public RequestContents getRequestContents() {
		return requestContents;
	}

	public void setRequestContents(RequestContents requestContents) {
		this.requestContents = requestContents;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the methodType
	 */
	public String getMethodType() {
		return methodType;
	}

	/**
	 * @param methodType the methodType to set
	 */
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tescase [method=" + method + ", methodType=" + methodType + ", requestContents=" + requestContents
				+ ", reponseContents=" + reponseContents + ", getReponseContents()=" + getReponseContents()
				+ ", getRequestContents()=" + getRequestContents() + ", getMethod()=" + getMethod()
				+ ", getMethodType()=" + getMethodType() + "]";
	}
	
}
