package com.vimukti.dashboard.client.reportdata;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportGrouping extends MetaObject {

	// ReportAggrType
	private String aggregateType;

	// UserDateGranularity
	private String dateGranularity;

	private String field;

	private String sortByName;

	// SortOrder
	private String sortOrder;

	// ReportSortType
	private String sortType;

	public ReportGrouping() {
	}

	public String getAggregateType() {
		return aggregateType;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	public String getDateGranularity() {
		return dateGranularity;
	}

	public void setDateGranularity(String dateGranularity) {
		this.dateGranularity = dateGranularity;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSortByName() {
		return sortByName;
	}

	public void setSortByName(String sortByName) {
		this.sortByName = sortByName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

}
