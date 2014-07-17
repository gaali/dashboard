package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class ReportTypeColumn {

	private ColumnDataType columnDataType;
	private Boolean filterable;
	private String name;
	private String label;
	private List<FilterValue> filterValues;

	/**
	 * Returns the data type of the field.
	 * 
	 * @return
	 */
	public ColumnDataType getDataType() {
		return columnDataType;
	}

	/**
	 * If the field data type is picklist, multi-select picklist, boolean, or
	 * checkbox, returns all filter values for a field. For example, checkbox
	 * fields always have a value of true or false . For fields of other data
	 * types, the filter value is an empty array, because their values can’t be
	 * determined.
	 * 
	 * @return
	 */
	public List<FilterValue> getFilterValues() {
		return filterValues;
	}

	/**
	 * If the field is of a type that can’t be filtered, returns False . For
	 * example, fields of the type Encrypted Text can’t be filtered.
	 * 
	 * @return
	 */
	public Boolean getFilterable() {
		return filterable;
	}

	/**
	 * Returns the localized display name of the field.
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the unique API name of the field.
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
		filterable = object.get("filterable").isBoolean().booleanValue();
		jsonValue = object.get("label");
		if (jsonValue != null) {
			label = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("columnDataType");
		if (jsonValue != null) {
			columnDataType = ColumnDataType.get(jsonValue.isNumber()
					.doubleValue());
		}
		jsonValue = object.get("filterValues");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<FilterValue> inarray = new ArrayList<FilterValue>();

			for (int i = 0; i < jsonArray.size(); i++) {

				FilterValue inObj = new FilterValue();
				inObj.fromJSON(jsonArray.get(i).isObject());
				inarray.add(inObj);
			}
			filterValues = inarray;
		}
	}

	public JSONValue toJSON() {
		JSONObject jsonObject = new JSONObject();
		if (columnDataType != null) {
			jsonObject.put("columnDataType",
					new JSONNumber(columnDataType.ordinal()));
		}
		jsonObject.put("filterable", JSONBoolean.getInstance(filterable));
		if (name != null) {
			jsonObject.put("name", new JSONString(name));
		}
		if (label != null) {
			jsonObject.put("label", new JSONString(label));
		}

		if (filterValues != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (FilterValue inArr : filterValues) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("filterValues", jsonArray);
		}
		return jsonObject;
	}
}
