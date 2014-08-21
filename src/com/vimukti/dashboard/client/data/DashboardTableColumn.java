package com.vimukti.dashboard.client.data;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings("serial")
public class DashboardTableColumn extends MetaObject {

	private ReportSummaryType aggregateType;
	private String column;
	private boolean showTotal;
	private DashboardComponentFilter sortBy;

	/**
	 * @return the aggregateType
	 */
	public ReportSummaryType getAggregateType() {
		return aggregateType;
	}

	/**
	 * @param aggregateType
	 *            the aggregateType to set
	 */
	public void setAggregateType(ReportSummaryType aggregateType) {
		this.aggregateType = aggregateType;
	}

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

	/**
	 * @return the showTotal
	 */
	public boolean isShowTotal() {
		return showTotal;
	}

	/**
	 * @param showTotal
	 *            the showTotal to set
	 */
	public void setShowTotal(boolean showTotal) {
		this.showTotal = showTotal;
	}

	/**
	 * @return the sortBy
	 */
	public DashboardComponentFilter getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy
	 *            the sortBy to set
	 */
	public void setSortBy(DashboardComponentFilter sortBy) {
		this.sortBy = sortBy;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jAggregateType = jsonObject.get("aggregateType");
		if (jAggregateType != null) {
			String aggregateString = jAggregateType.isString().stringValue();
			aggregateType = ReportSummaryType.getSummaryType(aggregateString);
		}

		JSONValue jColumn = jsonObject.get("column");
		if (jColumn != null) {
			column = jColumn.isString().stringValue();
		}
		JSONValue jShowTotal = jsonObject.get("showTotal");
		if (jShowTotal != null) {
			showTotal = jShowTotal.isBoolean().booleanValue();
		}

		JSONValue jSortBy = jsonObject.get("sortBy");
		if (jSortBy != null) {
			String sortByValue = jSortBy.isString().stringValue();
			sortBy = DashboardComponentFilter.getCompontentFilter(sortByValue);
		}

	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		String aggregateTypeV = aggregateType.toString();
		if (aggregateTypeV != null) {
			json.put("aggregateType", new JSONString(aggregateTypeV));
		}
		json.put("column", new JSONString(column));
		json.put("showTotal", JSONBoolean.getInstance(showTotal));

		String sortByV = sortBy.toString();
		if (sortByV != null) {
			json.put("sortBy", new JSONString(sortByV));
		}
		return json;
	}
	@Override
	protected DashboardTableColumn clone()  {
		DashboardTableColumn dashboardTableColumn = new DashboardTableColumn();
		super.clone(dashboardTableColumn);
		dashboardTableColumn.aggregateType = this.aggregateType;
		dashboardTableColumn.column = this.column;
		dashboardTableColumn.showTotal = this.showTotal;
		dashboardTableColumn.sortBy = this.sortBy;
		return dashboardTableColumn;
	}
}
