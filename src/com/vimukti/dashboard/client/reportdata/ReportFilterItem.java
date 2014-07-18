package com.vimukti.dashboard.client.reportdata;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportFilterItem extends MetaObject {

	private String column;

	private boolean columnToColum;

	// FilterOperation
	private String operator;

	private String snapshot;

	private String value;

	public ReportFilterItem() {
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public boolean isColumnToColum() {
		return columnToColum;
	}

	public void setColumnToColum(boolean columnToColum) {
		this.columnToColum = columnToColum;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
