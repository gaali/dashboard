package com.vimukti.dashboard.client.reportdata;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportColorRange extends MetaObject {

	// ReportSummaryType
	private String aggregate;

	private String columnName;

	private double highBreakpoint;

	private String highColor;

	private double lowBreakpoint;

	private String lowColor;

	private String midColor;

	public ReportColorRange() {
	}

	public String getAggregate() {
		return aggregate;
	}

	public void setAggregate(String aggregate) {
		this.aggregate = aggregate;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public double getHighBreakpoint() {
		return highBreakpoint;
	}

	public void setHighBreakpoint(double highBreakpoint) {
		this.highBreakpoint = highBreakpoint;
	}

	public String getHighColor() {
		return highColor;
	}

	public void setHighColor(String highColor) {
		this.highColor = highColor;
	}

	public double getLowBreakpoint() {
		return lowBreakpoint;
	}

	public void setLowBreakpoint(double lowBreakpoint) {
		this.lowBreakpoint = lowBreakpoint;
	}

	public String getLowColor() {
		return lowColor;
	}

	public void setLowColor(String lowColor) {
		this.lowColor = lowColor;
	}

	public String getMidColor() {
		return midColor;
	}

	public void setMidColor(String midColor) {
		this.midColor = midColor;
	}
}
