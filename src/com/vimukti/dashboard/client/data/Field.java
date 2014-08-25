package com.vimukti.dashboard.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class Field implements IJSONData {

	/**
	 * name of this field name
	 */
	private String name;
	/**
	 * type of this filed
	 */
	private FieldType fieldType;
	/**
	 * if this field referenced type , that object
	 */
	private CustomObject referenceType;

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
	public CustomObject getReferenceType() {
		return referenceType;
	}

	/**
	 * @param refrenceType
	 *            the refrenceType to set
	 */
	public void setReferenceType(CustomObject referenceType) {
		this.referenceType = referenceType;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		JSONValue jName = jsonObject.get("name");
		if (jName != null) {
			name = jName.isString().stringValue();
		}
		JSONValue jType = jsonObject.get("fieldType");
		if (jType != null) {
			String stringValue = jType.isString().stringValue();
			fieldType = FieldType.getFiedType(stringValue);
		}
		JSONValue jReferenceType = jsonObject.get("referenceType");
		if (jReferenceType != null) {
			CustomObject customObj = new CustomObject();
			customObj.fromJSON(jReferenceType.isObject());
			referenceType = customObj;
		}
	}

	@Override
	public JSONObject toJSON() {
		// this object will not send to server that why no need to implement
		return null;
	}
}
