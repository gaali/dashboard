package com.vimukti.dashboard.client.reportdata;

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

}
