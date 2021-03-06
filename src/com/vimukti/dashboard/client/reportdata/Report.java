package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class Report extends MetaObject {

	private List<ReportAggregate> aggregates = new ArrayList<ReportAggregate>();

	private List<ReportColumn> columns = new ArrayList<ReportColumn>();

	private ReportChart chart;

	private String currency;

	private String fullName;

	private List<ReportGrouping> groupings = new ArrayList<ReportGrouping>();

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
	 * constructing Report object from JSON object
	 */
	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jAggregates = jsonObject.get("aggregates");
		List<ReportAggregate> aggregateList = null;
		if (jAggregates != null) {
			JSONArray aggregaresArray = jAggregates.isArray();
			aggregateList = new ArrayList<ReportAggregate>();
			for (int i = 0; i < aggregaresArray.size(); i++) {
				ReportAggregate rAggreate = new ReportAggregate();
				JSONValue jsonValue = aggregaresArray.get(i);
				rAggreate.fromJSON(jsonValue.isObject());
				aggregateList.add(rAggreate);
			}
		}
		aggregates = aggregateList;
		JSONValue jColumns = jsonObject.get("columns");
		List<ReportColumn> columnsList = new ArrayList<ReportColumn>();
		if (jColumns != null) {
			JSONArray columnArray = jColumns.isArray();
			for (int i = 0; i < columnArray.size(); i++) {
				ReportColumn col = new ReportColumn();
				JSONValue jsonValue = columnArray.get(i);
				col.fromJSON(jsonValue.isObject());
				columnsList.add(col);
			}
		}
		columns = columnsList;

		JSONValue jCurrency = jsonObject.get("currency");
		if (jCurrency != null) {
			currency = jCurrency.isString().toString();
		}

		JSONValue jFullName = jsonObject.get("fullName");
		if (jFullName != null) {
			fullName = jFullName.isString().toString();
		}

		JSONValue jGroupings = jsonObject.get("groupings");
		List<ReportGrouping> groupingList = new ArrayList<ReportGrouping>();
		if (jGroupings != null) {
			JSONArray jGroupingsAcrossArray = jGroupings.isArray();
			for (int i = 0; i < jGroupingsAcrossArray.size(); i++) {
				ReportGrouping acrossGrouping = new ReportGrouping();
				JSONValue jsonValue = jGroupingsAcrossArray.get(i);
				acrossGrouping.fromJSON(jsonValue.isObject());
				groupingList.add(acrossGrouping);
			}
		}
		groupings = groupingList;

		JSONValue jName = jsonObject.get("name");
		if (jName != null) {
			name = jName.isString().toString();
		}

		JSONValue jReportType = jsonObject.get("reportType");
		if (jReportType != null) {
			reportType = jReportType.isString().toString();
		}
	}

}
