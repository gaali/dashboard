package com.vimukti.dashboard.client.data;

import java.util.List;

@SuppressWarnings("serial")
public class DashboardFilters extends MetaObject {

	private List<DashboardFilterOptions> dashboardFilterOptions;

	private String name;

	private String DisplayLabel;

	/**
	 * @return the dashboardFilterOptions
	 */
	public List<DashboardFilterOptions> getDashboardFilterOptions() {
		return dashboardFilterOptions;
	}

	/**
	 * @param dashboardFilterOptions
	 *            the dashboardFilterOptions to set
	 */
	public void setDashboardFilterOptions(
			List<DashboardFilterOptions> dashboardFilterOptions) {
		this.dashboardFilterOptions = dashboardFilterOptions;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the displayLabel
	 */
	public String getDisplayLabel() {
		return DisplayLabel;
	}

	/**
	 * @param displayLabel
	 *            the displayLabel to set
	 */
	public void setDisplayLabel(String displayLabel) {
		DisplayLabel = displayLabel;
	}

}
