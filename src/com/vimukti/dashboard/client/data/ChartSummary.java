package com.vimukti.dashboard.client.data;

@SuppressWarnings("serial")
public class ChartSummary extends MetaObject {

	// ReportSummaryType
	private ReportSummaryType aggregate;

	// ChartAxis
	private String axisBinding;

	private String column;

	public ChartSummary() {
	}

	public ReportSummaryType getAggregate() {
		return aggregate;
	}

	public void setAggregate(ReportSummaryType aggregate) {
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
