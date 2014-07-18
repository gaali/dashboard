package com.vimukti.dashboard.client.reportdata;

import java.util.List;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportFilter extends MetaObject {

	private String booleanFilter;

	private List<ReportFilterItem> criteriaItems;

	private String language;

	public ReportFilter() {
	}

	public String getBooleanFilter() {
		return booleanFilter;
	}

	public void setBooleanFilter(String booleanFilter) {
		this.booleanFilter = booleanFilter;
	}

	public List<ReportFilterItem> getCriteriaItems() {
		return criteriaItems;
	}

	public void setCriteriaItems(List<ReportFilterItem> criteriaItems) {
		this.criteriaItems = criteriaItems;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
