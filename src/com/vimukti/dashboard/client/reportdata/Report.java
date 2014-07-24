package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class Report extends MetaObject {

	private List<ReportAggregate> aggregates = new ArrayList<ReportAggregate>();

	private List<ReportColumn> columns = new ArrayList<>();

	private ReportChart chart;

	private String currency;

	private String fullName;

	private List<ReportGrouping> groupings = new ArrayList<>();

	private String name;

	private String reportType;

	/**
	 * @return the aggregates
	 */
	public List<ReportAggregate> getAggregates() {
		return aggregates;
	}

	/**
	 * @param aggregates
	 *            the aggregates to set
	 */
	public void setAggregates(List<ReportAggregate> aggregates) {
		this.aggregates = aggregates;
	}

	/**
	 * @return the chart
	 */
	public ReportChart getChart() {
		return chart;
	}

	/**
	 * @param chart
	 *            the chart to set
	 */
	public void setChart(ReportChart chart) {
		this.chart = chart;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType
	 *            the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the columns
	 */
	public List<ReportColumn> getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<ReportColumn> columns) {
		this.columns = columns;
	}

	/**
	 * @return the groupings
	 */
	public List<ReportGrouping> getGroupings() {
		return groupings;
	}

	/**
	 * @param groupings
	 *            the groupings to set
	 */
	public void setGroupings(List<ReportGrouping> groupings) {
		this.groupings = groupings;
	}

}
