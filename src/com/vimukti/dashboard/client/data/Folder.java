package com.vimukti.dashboard.client.data;

import java.util.List;

@SuppressWarnings("serial")
public class Folder extends MetaObject {
	private String name;
	private List<ReportDetails> reports;

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
	public List<ReportDetails> getReports() {
		return reports;
	}

	/**
	 * @param reports
	 *            the reports to set
	 */
	public void setReports(List<ReportDetails> reports) {
		this.reports = reports;
	}

}
