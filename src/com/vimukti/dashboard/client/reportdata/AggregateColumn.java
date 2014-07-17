package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class AggregateColumn {

	private String name;
	private String label;
	private ColumnDataType dataType;
	private String acrossGroupingContext;
	private String downGroupingContext;

	/**
	 * Returns the unique API name of the summary field.
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
	 * Returns the localized display name for the summarized or custom summary
	 * formula field.
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
	 * Returns the data type of the summarized or custom summary formula field.
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
	 * Returns the column grouping in the report where the summary field is
	 * displayed.
	 * 
	 * @return
	 */
	public String getAcrossGroupingContext() {
		return acrossGroupingContext;
	}

	public void setAcrossGroupingContext(String acrossGroupingContext) {
		this.acrossGroupingContext = acrossGroupingContext;
	}

	/**
	 * Returns the row grouping in the report where the summary field is
	 * displayed.
	 * 
	 * @return
	 */
	public String getDownGroupingContext() {
		return downGroupingContext;
	}

	public void setDownGroupingContext(String downGroupingContext) {
		this.downGroupingContext = downGroupingContext;
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
		jsonValue = object.get("dataType");
		if (jsonValue != null) {
			dataType = ColumnDataType.get(jsonValue.isNumber().doubleValue());
		}
		jsonValue = object.get("acrossGroupingContext");
		if (jsonValue != null) {
			acrossGroupingContext = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("downGroupingContext");
		if (jsonValue != null) {
			downGroupingContext = jsonValue.isString().stringValue();
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
		if (dataType != null) {
			jsonObject.put("dataType", new JSONNumber(dataType.ordinal()));
		}
		if (acrossGroupingContext != null) {
			jsonObject.put("name", new JSONString(acrossGroupingContext));
		}
		if (acrossGroupingContext != null) {
			jsonObject.put("acrossGroupingContext", new JSONString(
					acrossGroupingContext));
		}
		return jsonObject;
	}
}
