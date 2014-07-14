package com.vimukti.dashboard.client.data;

@SuppressWarnings("serial")
public class ChartSummary extends MetaObject {

	// ReportSummaryType
	private String aggregate;

	// ChartAxis
	private String axisBinding;

	private String column;

	public ChartSummary() {
	}

	public String getAggregate() {
		return aggregate;
	}

	public void setAggregate(String aggregate) {
		this.aggregate = aggregate;
	}

	public String getAxisBinding() {
		return axisBinding;
	}

	public void setAxisBinding(String axisBinding) {
		this.axisBinding = axisBinding;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

}
