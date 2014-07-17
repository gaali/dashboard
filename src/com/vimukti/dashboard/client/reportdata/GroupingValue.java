package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class GroupingValue {

	private List<GroupingValue> grouping;
	private String key;
	private String label;
	private Object value;

	/**
	 * Returns a list of second- or third-level row or column groupings. If
	 * there are none, the value is an empty array.
	 * 
	 * @return
	 */
	public List<GroupingValue> getGroupings() {
		return grouping;
	}

	/**
	 * Returns the unique identifier for a row or column grouping. The
	 * identifier is used by the fact map to specify data values within each
	 * grouping.
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the localized display name of a row or column grouping. For date
	 * and time fields, the label is the localized date or time.
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the value of the field that is used as a row or column grouping.
	 * 
	 * @return
	 */
	public Object getValue() {
		return value;
	}

	public void fromJSON(JSONObject jsonObject) {

		JSONValue jsonValue = jsonObject.get("grouping");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<GroupingValue> inarray = new ArrayList<GroupingValue>();

			for (int i = 0; i < jsonArray.size(); i++) {

				GroupingValue inObj = new GroupingValue();
				inObj.fromJSON(jsonArray.get(i).isObject());
				inarray.add(inObj);
			}
			grouping = inarray;
		}
		jsonValue = jsonObject.get("key");
		if (jsonValue != null) {
			key = jsonValue.isString().stringValue();
		}
		jsonValue = jsonObject.get("label");
		if (jsonValue != null) {
			label = jsonValue.isString().stringValue();
		}
		jsonValue = jsonObject.get("value");
		if (jsonValue != null) {

			if (jsonValue.isBoolean() != null) {
				value = jsonValue.isBoolean().booleanValue();
			} else if (jsonValue.isNumber() != null) {
				value = jsonValue.isNumber().doubleValue();
			} else if (jsonValue.isString() != null) {
				value = jsonValue.isString().stringValue();
			} else {
				throw new RuntimeException();
			}
		}

	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		if (grouping != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (GroupingValue inArr : grouping) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("reportFilters", jsonArray);
		}
		if (key != null) {
			jsonObject.put("key", new JSONString(key));
		}
		if (label != null) {
			jsonObject.put("label", new JSONString(label));
		}
		if (value != null) {
			if (value instanceof String) {
				jsonObject.put("value", new JSONString((String) value));
			} else if (value instanceof Integer || value instanceof Double) {
				jsonObject.put("value",
						new JSONNumber(((Number) value).doubleValue()));
			} else if (value instanceof Boolean) {
				jsonObject.put("value",
						JSONBoolean.getInstance((boolean) value));
			} else {
				throw new RuntimeException();
			}

		}
		return jsonObject;
	}
}
