package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class ReportType {

	private String label;
	private String type;

	/**
	 * Returns the localized display name of the report type.
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the unique identifier of the report type.
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	public void fromJSON(JSONObject jsonObject) {

		JSONValue jsonValue = jsonObject.get("type");
		if (jsonValue != null) {
			type = jsonValue.isString().stringValue();
		}
		jsonValue = jsonObject.get("label");
		if (jsonValue != null) {
			label = jsonValue.isString().stringValue();
		}

	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (type != null) {
			jsonObject.put("type", new JSONString(type));
		}
		if (label != null) {
			jsonObject.put("label", new JSONString(label));
		}
		return jsonObject;
	}

}
