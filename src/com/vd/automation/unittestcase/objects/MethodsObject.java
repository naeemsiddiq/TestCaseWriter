/**
 * 
 */
package com.vd.automation.unittestcase.objects;

import java.util.List;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class MethodsObject {

	String url;
	String requestMethod;
	List<ParameterObject> parameterObject;
	String methodName;
	String methodReturnType;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("[url=");
		builder.append(url);
		builder.append(", requestMethod=");
		builder.append(requestMethod);
		builder.append(", parameterObject=");
		builder.append(parameterObject);
		builder.append(", methodName=");
		builder.append(methodName);
		builder.append(", methodReturnType=");
		builder.append(methodReturnType);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the requestMethod
	 */
	public String getRequestMethod() {
		return requestMethod;
	}

	/**
	 * @param requestMethod
	 *            the requestMethod to set
	 */
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the parameterObject
	 */
	public List<ParameterObject> getParameterObject() {
		return parameterObject;
	}

	/**
	 * @param parameterObject
	 *            the parameterObject to set
	 */
	public void setParameterObject(List<ParameterObject> parameterObject) {
		this.parameterObject = parameterObject;
	}

	public String getMethodReturnType() {
		return methodReturnType;
	}

	public void setMethodReturnType(String methodReturnType) {
		this.methodReturnType = methodReturnType;
	}

}
