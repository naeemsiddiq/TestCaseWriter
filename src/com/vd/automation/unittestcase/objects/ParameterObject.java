/**
 * 
 */
package com.vd.automation.unittestcase.objects;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class ParameterObject {

	String methodParamDataType;
	String methodParamAnnotation;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("[methodParamDataType=");
		builder.append(methodParamDataType);
		builder.append(", methodParamAnnotation=");
		builder.append(methodParamAnnotation);
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * @return the methodParamDataType
	 */
	public String getMethodParamDataType() {
		return methodParamDataType;
	}

	/**
	 * @param methodParamDataType
	 *            the methodParamDataType to set
	 */
	public void setMethodParamDataType(String methodParamDataType) {
		this.methodParamDataType = methodParamDataType;
	}

	/**
	 * @return the methodParamAnnotation
	 */
	public String getMethodParamAnnotation() {
		return methodParamAnnotation;
	}

	/**
	 * @param methodParamAnnotation
	 *            the methodParamAnnotation to set
	 */
	public void setMethodParamAnnotation(String methodParamAnnotation) {
		this.methodParamAnnotation = methodParamAnnotation;
	}
}
