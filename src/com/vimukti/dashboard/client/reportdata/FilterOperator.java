package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class FilterOperator {

	private String label;
	private String name;

	/**
	 * Returns the localized display name of the filter operator. Possible
	 * values for this name are restricted based on the data type of the column
	 * being filtered.
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the unique API name of the filter operator. Possible values for
	 * this name are restricted based on the data type of the column being
	 * filtered. For example multipicklist fields can use the following filter
	 * operators: â€œequals,â€� â€œnot equal to,â€� â€œincludes,â€� and
	 * â€œexcludes.â€� Bucket fields are considered to be of the String type.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
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

	}

	public JSONObject toJSON() {
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
