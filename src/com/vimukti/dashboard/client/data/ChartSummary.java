package com.vimukti.dashboard.client.data;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.vimukti.dashboard.client.reportdata.ChartAxis;

@SuppressWarnings("serial")
public class ChartSummary extends MetaObject {

	private ReportSummaryType aggregate;

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
			ReportSummaryType summaryType = ReportSummaryType
					.getSummaryType(stringValue);
			aggregate = summaryType;
		}

		JSONValue jAxisBinding = jsonObject.get("axisBinding");
		if (jAxisBinding != null) {
			String axisBindingString = jAxisBinding.isString().stringValue();
			ChartAxis axis = ChartAxis.getAxis(axisBindingString);
			axisBinding = axis;
		}

		JSONValue jColumn = jsonObject.get("column");
		if (jColumn != null) {
			String columnString = jColumn.isString().stringValue();
			column = columnString;
		}

	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		if (aggregate != null) {
			String aggregareSummaryType = aggregate.toString();
			json.put("aggregate", new JSONString(aggregareSummaryType));
		}

		if (axisBinding != null) {
			String axisBingingString = axisBinding.toString();
			json.put("axisBinding", new JSONString(axisBingingString));
		}
		if (column != null) {
			json.put("column", new JSONString(column));
		}
		return json;
	}

	protected ChartSummary clone() {
		ChartSummary chartSummary = new ChartSummary();
		super.clone(chartSummary);
		chartSummary.aggregate = this.aggregate;
		chartSummary.axisBinding = this.axisBinding;
		chartSummary.column = this.column;
		return chartSummary;
	}
}
