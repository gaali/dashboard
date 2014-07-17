package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class ReportFilter {

	private String column;

	private String operator;

	private String value;

	public ReportFilter() {
	}

	public ReportFilter(String column, String operator, String value) {
		this.column = column;
		this.operator = operator;
		this.value = value;
	}

	/**
	 * Returns the unique API name for the field thatâ€™s being filtered.
	 * 
	 * @return
	 */
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * Returns the unique API name for the condition that is used to filter a
	 * field, such as â€œgreater thanâ€� or â€œnot equal to.â€� Filter
	 * conditions depend on the data type of the field.
	 * 
	 * @return
	 */
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * Returns the value by which a field can be filtered. For example, the
	 * field Age can be filtered by a numeric value.
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void fromJSON(JSONObject jsonObject) {

		JSONValue jsonValue = jsonObject.get("column");
		if (jsonValue != null) {
			column = jsonValue.isString().stringValue();
		}
		jsonValue = jsonObject.get("operator");
		if (jsonValue != null) {
			operator = jsonValue.isString().stringValue();
		}
		jsonValue = jsonObject.get("value");
		if (jsonValue != null) {
			value = jsonValue.isString().stringValue();
		}

	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (column != null) {
			jsonObject.put("column", new JSONString(column));
		}
		if (operator != null) {
			jsonObject.put("operator", new JSONString(operator));
		}
		if (value != null) {
			jsonObject.put("downGroupingContext", new JSONString(value));
		}
		return jsonObject;
	}
}
