package com.vimukti.dashboard.client.data;

public class Field {

	public enum FieldType {
		// Need to add all types of field
		INT, BOOLEAN, STRING, ADRESS, REFRENCE, TEXT, DATEANDTIME
	}

	private String name;
	private FieldType fieldType;
	private CustomObject refrenceType;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fieldType
	 */
	public FieldType getFieldType() {
		return fieldType;
	}

	/**
	 * @param fieldType
	 *            the fieldType to set
	 */
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * @return the refrenceType
	 */
	public CustomObject getRefrenceType() {
		return refrenceType;
	}

	/**
	 * @param refrenceType
	 *            the refrenceType to set
	 */
	public void setRefrenceType(CustomObject refrenceType) {
		this.refrenceType = refrenceType;
	}
}
