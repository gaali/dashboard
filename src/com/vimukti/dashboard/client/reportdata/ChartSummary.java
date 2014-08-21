package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.vimukti.dashboard.client.data.MetaObject;
import com.vimukti.dashboard.client.data.ReportSummaryType;

@SuppressWarnings("serial")
public class ChartSummary extends MetaObject {

	// ReportSummaryType
	private ReportSummaryType aggregate;

	// ChartAxis
	private ChartAxis axisBinding;

	private String column;

	public ChartSummary() {
	}

	public ReportSummaryType getAggregate() {
		return aggregate;
	}

	public void setAggregate(ReportSummaryType aggregate) {
		this.aggregate = aggregate;
	}

	public ChartAxis getAxisBinding() {
		return axisBinding;
	}

	public void setAxisBinding(ChartAxis axisBinding) {
		this.axisBinding = axisBinding;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jAggregate = jsonObject.get("aggregate");
		if (jAggregate != null) {
			String stringValue = jAggregate.isString().stringValue();
			aggregate = ReportSummaryType.getSummaryType(stringValue);
		}

		JSONValue jAxisBindings = jsonObject.get("axisBinding");
		if (jAxisBindings != null) {
			String stringValue = jAxisBindings.isString().stringValue();
			axisBinding = ChartAxis.getAxis(stringValue);
		}
		JSONValue jColumn = jsonObject.get("column");
		if (jColumn != null) {
			column = jColumn.isString().stringValue();
		}
	}

	@Override
	public JSONObject toJSON() {
		return super.toJSON();
	}
}
