package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class SummaryValue {

	private String label;
	private Number value;

	/**
	 * Returns the formatted summary data for a specified cell.
	 * 
	 * @return
	 */
	public String getLabel() {
		// TODO
		return label;
	}

	/**
	 * Returns the numeric value of the summary data for a specified cell.
	 * 
	 * @return
	 */
	public Number getValue() {
		// TODO
		return value;
	}

	public void fromJSON(JSONObject object) {

		JSONValue jsonValue = object.get("label");
		if (jsonValue != null) {
			label = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("value");
		if (jsonValue != null) {
			value = jsonValue.isNumber().doubleValue();
		}
	}

	public JSONValue toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (label != null) {
			jsonObject.put("label", new JSONString(label));
		}
		jsonObject.put("value", new JSONNumber((double) value));
		return jsonObject;
	}
}
