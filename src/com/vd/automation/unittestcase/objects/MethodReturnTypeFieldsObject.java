/**
 * 
 */
package com.vd.automation.unittestcase.objects;

/**
 * @author Naeem Siddiq
 *
 *         ASE
 */
public class MethodReturnTypeFieldsObject {

	String fieldName;
	String fieldType;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("[fieldName=");
		builder.append(fieldName);
		builder.append(", fieldType=");
		builder.append(fieldType);
		builder.append("]");
		return builder.toString();
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
}
