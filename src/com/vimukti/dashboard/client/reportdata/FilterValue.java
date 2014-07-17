package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class FilterValue {

	private String name;
	private String label;

	/**
	 * Returns the localized display name of the filter value. Possible values
	 * for this name are restricted based on the data type of the column being
	 * filtered.
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the unique API name of the filter value. Possible values for this
	 * name are restricted based on the data type of the column being filtered.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void fromJSON(JSONObject object) {

		JSONValue jsonValue = object.get("name");
		if (jsonValue != null) {
			name = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("label");
		if (jsonValue != null) {
			label = jsonValue.isString().stringValue();
		}
	}

	public JSONValue toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (name != null) {
			jsonObject.put("name", new JSONString(name));
		}
		if (label != null) {
			jsonObject.put("label", new JSONString(label));
		}
		return jsonObject;
	}
}
