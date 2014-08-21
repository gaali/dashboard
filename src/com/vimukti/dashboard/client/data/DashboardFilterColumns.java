package com.vimukti.dashboard.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings("serial")
public class DashboardFilterColumns extends MetaObject {

	private String column;

	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jColumn = jsonObject.get(column);
		if (jColumn != null) {
			column = jColumn.isString().stringValue();
		}
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		if (column != null) {
			json.put("column", new JSONString(column));
		}
		return json;

	}

	protected DashboardFilterColumns clone() {
		DashboardFilterColumns dashboardFilterColumns = new DashboardFilterColumns();
		super.clone(dashboardFilterColumns);
		dashboardFilterColumns.column = this.column;
		return dashboardFilterColumns;
	}
}
