package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class DetailColumn {

	private String name;
	private String label;
	private ColumnDataType dataType;

	/**
	 * Returns the unique API name of the detail column field.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the localized display name of a standard field, the ID of a
	 * custom field, or the API name of a bucket field that has detailed data.
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the data type of a detail column field.
	 * 
	 * @return
	 */
	public ColumnDataType getDataType() {
		return dataType;
	}

	public void setDataType(ColumnDataType dataType) {
		this.dataType = dataType;
	}

	public void fromJSON(JSONObject jsonObject) {

		JSONValue jsonValue = jsonObject.get("name");
		if (jsonValue != null) {
			name = jsonValue.isString().stringValue();
		}
		jsonValue = jsonObject.get("label");
		if (jsonValue != null) {
			label = jsonValue.isString().stringValue();
		}
		jsonValue = jsonObject.get("dataType");
		if (jsonValue != null) {
			dataType = ColumnDataType.get(jsonValue.isNumber().doubleValue());
		}

	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (name != null) {
			jsonObject.put("name", new JSONString(name));
		}
		if (label != null) {
			jsonObject.put("label", new JSONString(label));
		}
		if (dataType != null) {
			jsonObject.put("dataType", new JSONNumber(dataType.ordinal()));
		}
		return jsonObject;
	}
}
