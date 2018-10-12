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
public class ClassesObject {

	String className;
	List<MethodsObject> methodObject;
	String baseUrl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("[className=");
		builder.append(className);
		builder.append(", methodObject=");
		builder.append(methodObject);
		builder.append(", baseUrl=");
		builder.append(baseUrl);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the methodObject
	 */
	public List<MethodsObject> getMethodObject() {
		return methodObject;
	}

	/**
	 * @param methodObject
	 *            the methodObject to set
	 */
	public void setMethodObject(List<MethodsObject> methodObject) {
		this.methodObject = methodObject;
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl
	 *            the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
