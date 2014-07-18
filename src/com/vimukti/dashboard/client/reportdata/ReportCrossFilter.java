package com.vimukti.dashboard.client.reportdata;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportCrossFilter extends MetaObject {

	// ReportSummaryType
	private String aggregate;

	// ChartAxis
	private String axisBinding;

	private String column;

	public ReportCrossFilter() {
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
