package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class GroupingColumn {

	private String name;
	private String label;
	private ColumnDataType dataType;
	private Integer groupingLevel;

	/**
	 * Returns the unique API name of the field or bucket field that is used for
	 * column grouping.
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
	 * Returns the localized display name of the field that is used for column
	 * grouping.
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
	 * Returns the data type of the field that is used for column grouping.
	 * 
	 * @return
	 */
	public ColumnDataType getDataType() {
		return dataType;
	}

	public void setDataType(ColumnDataType dataType) {
		this.dataType = dataType;
	}

	/**
	 * Returns the level of grouping for the column.
	 * 
	 * @return
	 */
	public Integer getGroupingLevel() {
		return groupingLevel;
	}

	public void setGroupingLevel(Integer groupingLevel) {
		this.groupingLevel = groupingLevel;
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
		jsonValue = jsonObject.get("groupingLevel");
		if (jsonValue != null) {
			groupingLevel = (int) jsonValue.isNumber().doubleValue();
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
		if (groupingLevel != null) {
			jsonObject
					.put("downGroupingContext", new JSONNumber(groupingLevel));
		}
		return jsonObject;
	}
}
