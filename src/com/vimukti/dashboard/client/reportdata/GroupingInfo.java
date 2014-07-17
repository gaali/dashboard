package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class GroupingInfo {

	private String name;
	private ColumnSortOrder sortOrder;
	private DateGranularity dateGranularity;
	private String sortAggregate;

	/**
	 * Returns the unique API name of the field or bucket field that is used for
	 * row or column grouping.
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
	 * Returns the order that is used to sort data in a row or column grouping (
	 * ASCENDING or DESCENDING ).
	 * 
	 * @return
	 */
	public ColumnSortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(ColumnSortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * Returns the date interval that is used for row or column grouping.
	 * 
	 * @return
	 */
	public DateGranularity getDateGranularity() {
		return dateGranularity;
	}

	public void setDateGranularity(DateGranularity dateGranularity) {
		this.dateGranularity = dateGranularity;
	}

	/**
	 * Returns the summary field that is used to sort data within a grouping in
	 * a summary report. The value is null when data within a grouping is not
	 * sorted by a summary field.
	 * 
	 * @return
	 */
	public String getSortAggregate() {
		return sortAggregate;
	}

	public void setSortAggregate(String sortAggregate) {
		this.sortAggregate = sortAggregate;
	}

	public void fromJSON(JSONObject jsonObject) {

		JSONValue jsonValue = jsonObject.get("name");
		if (jsonValue != null) {
			name = jsonValue.isString().stringValue();
		}
		jsonValue = jsonObject.get("sortOrder");
		if (jsonValue != null) {
			sortOrder = ColumnSortOrder.get(jsonValue.isNumber().doubleValue());
		}
		jsonValue = jsonObject.get("dateGranularity");
		if (jsonValue != null) {
			dateGranularity = DateGranularity.get(jsonValue.isNumber()
					.doubleValue());
		}
		jsonValue = jsonObject.get("sortAggregate");
		if (jsonValue != null) {
			sortAggregate = jsonValue.isString().stringValue();
		}

	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (name != null) {
			jsonObject.put("name", new JSONString(name));
		}

		if (sortOrder != null) {
			jsonObject.put("sortOrder", new JSONNumber(sortOrder.ordinal()));
		}
		if (dateGranularity != null) {
			jsonObject.put("dateGranularity",
					new JSONNumber(dateGranularity.ordinal()));
		}

		if (sortAggregate != null) {
			jsonObject.put("sortAggregate", new JSONString(sortAggregate));
		}
		return jsonObject;
	}
}
