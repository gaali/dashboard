package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class ReportDataCell {

	private String label;
	private Object value;

	public String getLabel() {
		return label;
	}

	public Object getValue() {
		return value;
	}

	public void fromJSON(JSONObject object) {

		JSONValue jsonValue = object.get("label");
		if (jsonValue != null) {
			label = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("value");
		if (jsonValue != null) {
			value = (Object) jsonValue.isObject();
		}
	}

	public JSONValue toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (label != null) {
			jsonObject.put("label", new JSONString(label));
		}
		if (value != null) {
			jsonObject.put("value", (JSONObject) value);
		}
		return jsonObject;
	}
}
