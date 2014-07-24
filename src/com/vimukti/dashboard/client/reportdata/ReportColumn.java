package com.vimukti.dashboard.client.reportdata;

import java.util.List;

import com.vimukti.dashboard.client.data.MetaObject;
import com.vimukti.dashboard.client.data.ReportSummaryType;

@SuppressWarnings("serial")
public class ReportColumn extends MetaObject {

	// ReportSummaryType
	private List<ReportSummaryType> aggregateTypes;

	private String field;

	private boolean reverseColors;

	private boolean showChanges;

	public ReportColumn() {
	}

	public List<ReportSummaryType> getAggregateTypes() {
		return aggregateTypes;
	}

	public void setAggregateTypes(List<ReportSummaryType> aggregateTypes) {
		this.aggregateTypes = aggregateTypes;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isReverseColors() {
		return reverseColors;
	}

	public void setReverseColors(boolean reverseColors) {
		this.reverseColors = reverseColors;
	}

	public boolean isShowChanges() {
		return showChanges;
	}

	public void setShowChanges(boolean showChanges) {
		this.showChanges = showChanges;
	}

}
