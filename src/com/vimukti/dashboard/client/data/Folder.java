package com.vimukti.dashboard.client.data;

import java.util.List;

@SuppressWarnings("serial")
public class Folder extends MetaObject {
	private String name;
	private List<ReportList> reports;

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
	 * @return the reports
	 */
	public List<ReportList> getReports() {
		return reports;
	}

	/**
	 * @param reports
	 *            the reports to set
	 */
	public void setReports(List<ReportList> reports) {
		this.reports = reports;
	}

}
