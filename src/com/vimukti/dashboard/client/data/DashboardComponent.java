package com.vimukti.dashboard.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings("serial")
public class DashboardComponent extends MetaObject {

	public static String HIGH_COLOR = "ca54c2";

	public static String MID_COLOR = "c2545a";

	public static String LOW_COLOR = "c2e354";

	private ChartRangeType chartAxisRange = ChartRangeType.AUTO;

	private double chartAxisRangeMax;

	private double chartAxisRangeMin;

	private ChartSummary chartSummary;

	private DashboardComponentType componentType;

	private List<DashboardFilterColumns> dashboardFilterColumns;

	private List<DashboardTableColumn> dashboardTableColumn;

	private ChartUnits displayUnits = ChartUnits.AUTO;

	private String drillDownUrl;

	private boolean drillEnabled;

	private boolean drillToDetailEnabled;

	private boolean enableHover;

	private boolean expandOthers = true;

	private String footer;

	private double gaugeMax;

	private double gaugeMin;

	private String groupingColumn;

	private String header;

	private double indicatorBreakpoint1;

	private double indicatorBreakpoint2;

	private String indicatorHighColor = HIGH_COLOR;

	private String indicatorLowColor = MID_COLOR;

	private String indicatorMiddleColor = LOW_COLOR;

	private ChartLegendPosition legendPosition = ChartLegendPosition.BOTTOM;

	private int maxValuesDisplayed;

	private String metricLabel;

	private String page;

	private int pageHeightInPixels;

	private String report;

	private String scontrol;

	private int scontrolHeightInPixels;

	private boolean showPercentage;

	private boolean showPicturesOnCharts;

	private boolean showPicturesOnTables;

	private boolean showTotal;

	private boolean showValues = true;

	private DashboardComponentFilter sortBy = DashboardComponentFilter.ROW_LABEL_ASCENDING;

	private String title;

	private boolean useReportChart;

	public DashboardComponent() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the chartAxisRange
	 */
	public ChartRangeType getChartAxisRange() {
		return chartAxisRange;
	}

	/**
	 * @param chartAxisRange
	 *            the chartAxisRange to set
	 */
	public void setChartAxisRange(ChartRangeType chartAxisRange) {
		this.chartAxisRange = chartAxisRange;
	}

	/**
	 * @return the chartAxisRangeMax
	 */
	public double getChartAxisRangeMax() {
		return chartAxisRangeMax;
	}

	/**
	 * @param chartAxisRangeMax
	 *            the chartAxisRangeMax to set
	 */
	public void setChartAxisRangeMax(double chartAxisRangeMax) {
		this.chartAxisRangeMax = chartAxisRangeMax;
	}

	/**
	 * @return the chartAxisRangeMin
	 */
	public double getChartAxisRangeMin() {
		return chartAxisRangeMin;
	}

	/**
	 * @param chartAxisRangeMin
	 *            the chartAxisRangeMin to set
	 */
	public void setChartAxisRangeMin(double chartAxisRangeMin) {
		this.chartAxisRangeMin = chartAxisRangeMin;
	}

	/**
	 * @return the chartSummary
	 */
	public ChartSummary getChartSummary() {
		return chartSummary;
	}

	/**
	 * @param chartSummary
	 *            the chartSummary to set
	 */
	public void setChartSummary(ChartSummary chartSummary) {
		this.chartSummary = chartSummary;
	}

	/**
	 * @return the componentType
	 */
	public DashboardComponentType getComponentType() {
		return componentType;
	}

	/**
	 * @param componentType
	 *            the componentType to set
	 */
	public void setComponentType(DashboardComponentType componentType) {
		this.componentType = componentType;
	}

	/**
	 * @return the dashboardFilterColumns
	 */
	public List<DashboardFilterColumns> getDashboardFilterColumns() {
		return dashboardFilterColumns;
	}

	/**
	 * @param dashboardFilterColumns
	 *            the dashboardFilterColumns to set
	 */
	public void setDashboardFilterColumns(
			List<DashboardFilterColumns> dashboardFilterColumns) {
		this.dashboardFilterColumns = dashboardFilterColumns;
	}

	/**
	 * @return the dashboardTableColumn
	 */
	public List<DashboardTableColumn> getDashboardTableColumn() {
		return dashboardTableColumn;
	}

	/**
	 * @param dashboardTableColumn
	 *            the dashboardTableColumn to set
	 */
	public void setDashboardTableColumn(
			List<DashboardTableColumn> dashboardTableColumn) {
		this.dashboardTableColumn = dashboardTableColumn;
	}

	/**
	 * @return the displayUnits
	 */
	public ChartUnits getDisplayUnits() {
		return displayUnits;
	}

	/**
	 * @param displayUnits
	 *            the displayUnits to set
	 */
	public void setDisplayUnits(ChartUnits displayUnits) {
		this.displayUnits = displayUnits;
	}

	/**
	 * @return the drillDownUrl
	 */
	public String getDrillDownUrl() {
		return drillDownUrl;
	}

	/**
	 * @param drillDownUrl
	 *            the drillDownUrl to set
	 */
	public void setDrillDownUrl(String drillDownUrl) {
		this.drillDownUrl = drillDownUrl;
	}

	/**
	 * @return the drillEnabled
	 */
	public boolean isDrillEnabled() {
		return drillEnabled;
	}

	/**
	 * @param drillEnabled
	 *            the drillEnabled to set
	 */
	public void setDrillEnabled(boolean drillEnabled) {
		this.drillEnabled = drillEnabled;
	}

	/**
	 * @return the drillToDetailEnabled
	 */
	public boolean isDrillToDetailEnabled() {
		return drillToDetailEnabled;
	}

	/**
	 * @param drillToDetailEnabled
	 *            the drillToDetailEnabled to set
	 */
	public void setDrillToDetailEnabled(boolean drillToDetailEnabled) {
		this.drillToDetailEnabled = drillToDetailEnabled;
	}

	/**
	 * @return the enableHover
	 */
	public boolean isEnableHover() {
		return enableHover;
	}

	/**
	 * @param enableHover
	 *            the enableHover to set
	 */
	public void setEnableHover(boolean enableHover) {
		this.enableHover = enableHover;
	}

	/**
	 * @return the expandOthers
	 */
	public boolean isExpandOthers() {
		return expandOthers;
	}

	/**
	 * @param expandOthers
	 *            the expandOthers to set
	 */
	public void setExpandOthers(boolean expandOthers) {
		this.expandOthers = expandOthers;
	}

	/**
	 * @return the footer
	 */
	public String getFooter() {
		return footer;
	}

	/**
	 * @param footer
	 *            the footer to set
	 */
	public void setFooter(String footer) {
		this.footer = footer;
	}

	/**
	 * @return the gaugeMax
	 */
	public double getGaugeMax() {
		return gaugeMax;
	}

	/**
	 * @param gaugeMax
	 *            the gaugeMax to set
	 */
	public void setGaugeMax(double gaugeMax) {
		this.gaugeMax = gaugeMax;
	}

	/**
	 * @return the gaugeMin
	 */
	public double getGaugeMin() {
		return gaugeMin;
	}

	/**
	 * @param gaugeMin
	 *            the gaugeMin to set
	 */
	public void setGaugeMin(double gaugeMin) {
		this.gaugeMin = gaugeMin;
	}

	/**
	 * @return the groupingColumn
	 */
	public String getGroupingColumn() {
		return groupingColumn;
	}

	/**
	 * @param groupingColumn
	 *            the groupingColumn to set
	 */
	public void setGroupingColumn(String groupingColumn) {
		this.groupingColumn = groupingColumn;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the indicatorBreakpoint1
	 */
	public double getIndicatorBreakpoint1() {
		return indicatorBreakpoint1;
	}

	/**
	 * @param indicatorBreakpoint1
	 *            the indicatorBreakpoint1 to set
	 */
	public void setIndicatorBreakpoint1(double indicatorBreakpoint1) {
		this.indicatorBreakpoint1 = indicatorBreakpoint1;
	}

	/**
	 * @return the indicatorBreakpoint2
	 */
	public double getIndicatorBreakpoint2() {
		return indicatorBreakpoint2;
	}

	/**
	 * @param indicatorBreakpoint2
	 *            the indicatorBreakpoint2 to set
	 */
	public void setIndicatorBreakpoint2(double indicatorBreakpoint2) {
		this.indicatorBreakpoint2 = indicatorBreakpoint2;
	}

	/**
	 * @return the indicatorHighColor
	 */
	public String getIndicatorHighColor() {
		return indicatorHighColor;
	}

	/**
	 * @param indicatorHighColor
	 *            the indicatorHighColor to set
	 */
	public void setIndicatorHighColor(String indicatorHighColor) {
		this.indicatorHighColor = indicatorHighColor;
	}

	/**
	 * @return the indicatorLowColor
	 */
	public String getIndicatorLowColor() {
		return indicatorLowColor;
	}

	/**
	 * @param indicatorLowColor
	 *            the indicatorLowColor to set
	 */
	public void setIndicatorLowColor(String indicatorLowColor) {
		this.indicatorLowColor = indicatorLowColor;
	}

	/**
	 * @return the indicatorMiddleColor
	 */
	public String getIndicatorMiddleColor() {
		return indicatorMiddleColor;
	}

	/**
	 * @param indicatorMiddleColor
	 *            the indicatorMiddleColor to set
	 */
	public void setIndicatorMiddleColor(String indicatorMiddleColor) {
		this.indicatorMiddleColor = indicatorMiddleColor;
	}

	/**
	 * @return the legendPosition
	 */
	public ChartLegendPosition getLegendPosition() {
		return legendPosition;
	}

	/**
	 * @param legendPosition
	 *            the legendPosition to set
	 */
	public void setLegendPosition(ChartLegendPosition legendPosition) {
		this.legendPosition = legendPosition;
	}

	/**
	 * @return the maxValuesDisplayed
	 */
	public int getMaxValuesDisplayed() {
		return maxValuesDisplayed;
	}

	/**
	 * @param maxValuesDisplayed
	 *            the maxValuesDisplayed to set
	 */
	public void setMaxValuesDisplayed(int maxValuesDisplayed) {
		this.maxValuesDisplayed = maxValuesDisplayed;
	}

	/**
	 * @return the metricLabel
	 */
	public String getMetricLabel() {
		return metricLabel;
	}

	/**
	 * @param metricLabel
	 *            the metricLabel to set
	 */
	public void setMetricLabel(String metricLabel) {
		this.metricLabel = metricLabel;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the pageHeightInPixels
	 */
	public int getPageHeightInPixels() {
		return pageHeightInPixels;
	}

	/**
	 * @param pageHeightInPixels
	 *            the pageHeightInPixels to set
	 */
	public void setPageHeightInPixels(int pageHeightInPixels) {
		this.pageHeightInPixels = pageHeightInPixels;
	}

	/**
	 * @return the report
	 */
	public String getReport() {
		return report;
	}

	/**
	 * @param report
	 *            the report to set
	 */
	public void setReport(String report) {
		this.report = report;
	}

	/**
	 * @return the scontrol
	 */
	public String getScontrol() {
		return scontrol;
	}

	/**
	 * @param scontrol
	 *            the scontrol to set
	 */
	public void setScontrol(String scontrol) {
		this.scontrol = scontrol;
	}

	/**
	 * @return the scontrolHeightInPixels
	 */
	public int getScontrolHeightInPixels() {
		return scontrolHeightInPixels;
	}

	/**
	 * @param scontrolHeightInPixels
	 *            the scontrolHeightInPixels to set
	 */
	public void setScontrolHeightInPixels(int scontrolHeightInPixels) {
		this.scontrolHeightInPixels = scontrolHeightInPixels;
	}

	/**
	 * @return the showPercentage
	 */
	public boolean isShowPercentage() {
		return showPercentage;
	}

	/**
	 * @param showPercentage
	 *            the showPercentage to set
	 */
	public void setShowPercentage(boolean showPercentage) {
		this.showPercentage = showPercentage;
	}

	/**
	 * @return the showPicturesOnCharts
	 */
	public boolean isShowPicturesOnCharts() {
		return showPicturesOnCharts;
	}

	/**
	 * @param showPicturesOnCharts
	 *            the showPicturesOnCharts to set
	 */
	public void setShowPicturesOnCharts(boolean showPicturesOnCharts) {
		this.showPicturesOnCharts = showPicturesOnCharts;
	}

	/**
	 * @return the showPicturesOnTables
	 */
	public boolean isShowPicturesOnTables() {
		return showPicturesOnTables;
	}

	/**
	 * @param showPicturesOnTables
	 *            the showPicturesOnTables to set
	 */
	public void setShowPicturesOnTables(boolean showPicturesOnTables) {
		this.showPicturesOnTables = showPicturesOnTables;
	}

	/**
	 * @return the showTotal
	 */
	public boolean isShowTotal() {
		return showTotal;
	}

	/**
	 * @param showTotal
	 *            the showTotal to set
	 */
	public void setShowTotal(boolean showTotal) {
		this.showTotal = showTotal;
	}

	/**
	 * @return the showValues
	 */
	public boolean isShowValues() {
		return showValues;
	}

	/**
	 * @param showValues
	 *            the showValues to set
	 */
	public void setShowValues(boolean showValues) {
		this.showValues = showValues;
	}

	/**
	 * @return the sortBy
	 */
	public DashboardComponentFilter getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy
	 *            the sortBy to set
	 */
	public void setSortBy(DashboardComponentFilter sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the useReportChart
	 */
	public boolean isUseReportChart() {
		return useReportChart;
	}

	/**
	 * @param useReportChart
	 *            the useReportChart to set
	 */
	public void setUseReportChart(boolean useReportChart) {
		this.useReportChart = useReportChart;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jChartAxisRange = jsonObject.get("chartAxisRange");
		if (jChartAxisRange != null) {
			String stringValue = jChartAxisRange.isString().stringValue();
			ChartRangeType rangeType = ChartRangeType.getRangeType(stringValue);
			chartAxisRange = rangeType;
		}

		JSONValue jChartAxisRangeMax = jsonObject.get("chartAxisRangeMax");
		if (jChartAxisRangeMax != null) {
			double rangetMaxValue = jChartAxisRangeMax.isNumber().doubleValue();
			chartAxisRangeMax = rangetMaxValue;
		}

		JSONValue jChartAxisRangeMin = jsonObject.get("chartAxisRangeMin");
		if (jChartAxisRangeMin != null) {
			double rangeMinValue = jChartAxisRangeMin.isNumber().doubleValue();
			chartAxisRangeMin = rangeMinValue;
		}

		JSONValue jChartSummary = jsonObject.get("chartSummary");
		if (jChartSummary != null) {
			ChartSummary jChartSummaryObj = new ChartSummary();
			jChartSummaryObj.fromJSON(jChartSummary.isObject());
			chartSummary = jChartSummaryObj;
		}

		JSONValue jComponentType = jsonObject.get("componentType");
		if (jComponentType != null) {
			String componentTypeString = jComponentType.isString()
					.stringValue();
			componentType = DashboardComponentType
					.getComponentType(componentTypeString);
		}

		JSONValue jDashboardFilterColumns = jsonObject
				.get("dashboardFilterColumns");
		if (jDashboardFilterColumns != null) {
			JSONArray jDashboardFilterColumnsArray = jDashboardFilterColumns
					.isArray();
			List<DashboardFilterColumns> dashboardFilterColumnsList = new ArrayList<DashboardFilterColumns>();
			for (int i = 0; i < jDashboardFilterColumnsArray.size(); i++) {
				DashboardFilterColumns filterColumn = new DashboardFilterColumns();
				JSONValue jFilterColumn = jDashboardFilterColumnsArray.get(i);
				filterColumn.fromJSON(jFilterColumn.isObject());
				dashboardFilterColumnsList.add(filterColumn);
			}
			dashboardFilterColumns = dashboardFilterColumnsList;
		}

		JSONValue jTableColumns = jsonObject.get("dashboardTableColumn");
		if (jTableColumns != null) {
			JSONArray jTableColumnArray = jTableColumns.isArray();
			List<DashboardTableColumn> tableColumnList = new ArrayList<DashboardTableColumn>();
			for (int i = 0; i < jTableColumnArray.size(); i++) {
				DashboardTableColumn tableColumn = new DashboardTableColumn();
				JSONValue jTableColumn = jTableColumnArray.get(i);
				tableColumn.fromJSON(jTableColumn.isObject());
				tableColumnList.add(tableColumn);
			}
			dashboardTableColumn = tableColumnList;
		}

		JSONValue jDisplayUnits = jsonObject.get("displayUnits");
		if (jDisplayUnits != null) {
			String unit = jDisplayUnits.toString();
			displayUnits = ChartUnits.getUnits(unit);
		}

		JSONValue jDrillDownUrl = jsonObject.get("drillDownUrl");
		if (jDrillDownUrl != null) {
			drillDownUrl = jDrillDownUrl.isString().stringValue();
		}
		JSONValue jDrillEnabled = jsonObject.get("drillEnabled");
		if (jDrillEnabled != null) {
			drillEnabled = jDrillEnabled.isBoolean().booleanValue();
		}

		JSONValue jDrillToDetailEnabled = jsonObject
				.get("drillToDetailEnabled");
		if (jDrillToDetailEnabled != null) {
			drillToDetailEnabled = jDrillToDetailEnabled.isBoolean()
					.booleanValue();
		}
		JSONValue jEnableHover = jsonObject.get("enableHover");
		if (jEnableHover != null) {
			enableHover = jEnableHover.isBoolean().booleanValue();
		}

		JSONValue jExpandOthers = jsonObject.get("expandOthers");
		if (jExpandOthers != null) {
			expandOthers = jExpandOthers.isBoolean().booleanValue();
		}

		JSONValue jFooter = jsonObject.get("footer");
		if (jFooter != null) {
			footer = jFooter.isString().stringValue();
		}

		JSONValue jGaugeMax = jsonObject.get("gaugeMax");
		if (jGaugeMax != null) {
			gaugeMax = jGaugeMax.isNumber().doubleValue();
		}

		JSONValue jGaugeMin = jsonObject.get("gaugeMin");
		if (jGaugeMin != null) {
			gaugeMin = jGaugeMin.isNumber().doubleValue();
		}

		JSONValue jGroupingColumn = jsonObject.get("groupingColumn");
		if (jGroupingColumn != null) {
			groupingColumn = jGroupingColumn.isString().stringValue();
		}
		JSONValue jHeader = jsonObject.get("header");
		if (jHeader != null) {
			header = jHeader.isString().stringValue();
		}

		JSONValue jBreackPoint1 = jsonObject.get("indicatorBreakpoint1");
		if (jBreackPoint1 != null) {
			indicatorBreakpoint1 = jBreackPoint1.isNumber().doubleValue();
		}
		JSONValue jBreakPoint2 = jsonObject.get("indicatorBreakpoint2");
		if (jBreakPoint2 != null) {
			indicatorBreakpoint2 = jBreakPoint2.isNumber().doubleValue();
		}
		JSONValue jHightColor = jsonObject.get("indicatorHighColor");
		if (jHightColor != null) {
			indicatorHighColor = jHightColor.isString().stringValue();
		}
		JSONValue jLowColor = jsonObject.get("indicatorLowColor");
		if (jLowColor != null) {
			indicatorLowColor = jLowColor.isString().stringValue();
		}
		JSONValue jMiddleColor = jsonObject.get("indicatorMiddleColor");
		if (jMiddleColor != null) {
			indicatorMiddleColor = jMiddleColor.isString().stringValue();
		}
		JSONValue jLegendPosition = jsonObject.get("legendPosition");
		if (jLegendPosition != null) {
			String stringValue2 = jLegendPosition.isString().stringValue();
			legendPosition = ChartLegendPosition.gtLegendPosition(stringValue2);
		}
		JSONValue jMaxValuesDisplayed = jsonObject.get("maxValuesDisplayed");
		if (jMaxValuesDisplayed != null) {
			maxValuesDisplayed = (int) jMaxValuesDisplayed.isNumber()
					.doubleValue();
		}

		JSONValue jMetricLabel = jsonObject.get("metricLabel");
		if (jMetricLabel != null) {
			metricLabel = jMetricLabel.isString().stringValue();
		}

		JSONValue jPage = jsonObject.get("page");
		if (jPage != null) {
			page = jPage.isString().stringValue();
		}

		JSONValue jPageHeightInPixels = jsonObject.get("pageHeightInPixels");
		if (jPageHeightInPixels != null) {
			pageHeightInPixels = (int) jPageHeightInPixels.isNumber()
					.doubleValue();
		}

		JSONValue jReport = jsonObject.get("report");
		if (jReport != null) {
			report = jReport.isString().stringValue();
		}

		JSONValue jScontrol = jsonObject.get("scontrol");
		if (jScontrol != null) {
			scontrol = jScontrol.isString().stringValue();
		}

		JSONValue jScontrolHeightInPixels = jsonObject
				.get("scontrolHeightInPixels");
		if (jScontrolHeightInPixels != null) {
			scontrolHeightInPixels = (int) jScontrolHeightInPixels.isNumber()
					.doubleValue();
		}

		JSONValue jShowPercentage = jsonObject.get("showPercentage");
		if (jShowPercentage != null) {
			showPercentage = jShowPercentage.isBoolean().booleanValue();
		}
		JSONValue jShowPicturesOnCharts = jsonObject
				.get("showPicturesOnCharts");
		if (jShowPicturesOnCharts != null) {
			showPicturesOnCharts = jShowPicturesOnCharts.isBoolean()
					.booleanValue();
		}

		JSONValue jShowPicturesOnTables = jsonObject
				.get("showPicturesOnTables");
		if (jShowPicturesOnTables != null) {
			showPicturesOnTables = jShowPicturesOnTables.isBoolean()
					.booleanValue();
		}

		JSONValue jShowTotal = jsonObject.get("showTotal");
		if (jShowTotal != null) {
			showTotal = jShowTotal.isBoolean().booleanValue();
		}

		JSONValue jShowValues = jsonObject.get("showValues");
		if (jShowValues != null) {
			showValues = jShowValues.isBoolean().booleanValue();
		}
		JSONValue jSortBy = jsonObject.get("sortBy");
		if (jSortBy != null) {
			String stringValue2 = jSortBy.isString().stringValue();
			sortBy = DashboardComponentFilter.getCompontentFilter(stringValue2);
		}

		JSONValue jUseReportChart = jsonObject.get("useReportChart");
		if (jUseReportChart != null) {
			useReportChart = jUseReportChart.isBoolean().booleanValue();
		}
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		if (chartAxisRange != null) {
			String string = chartAxisRange.toString();
			json.put("chartAxisRange", new JSONString(string));
		}
		json.put("chartAxisRangeMax", new JSONNumber(chartAxisRangeMax));
		json.put("chartAxisRangeMin", new JSONNumber(chartAxisRangeMin));

		if (chartSummary != null) {
			String string = chartSummary.toString();
			json.put("chartSummary", new JSONString(string));
		}

		if (componentType != null) {
			String string = componentType.toString();
			json.put("componentType", new JSONString(string));
		}

		JSONArray filterColumnArray = new JSONArray();
		int filterIndex = 0;
		if (dashboardFilterColumns != null) {
			for (DashboardFilterColumns filterColumn : dashboardFilterColumns) {
				if (filterColumn != null) {
					filterColumnArray.set(filterIndex++, filterColumn.toJSON());
				}
			}
		}
		json.put("dashboardFilterColumns", filterColumnArray);

		JSONArray tableColumnArray = new JSONArray();
		int tableIndex = 0;
		if (dashboardTableColumn != null) {
			for (DashboardTableColumn tableColumn : dashboardTableColumn) {
				if (tableColumn != null) {
					tableColumnArray.set(tableIndex++, tableColumn.toJSON());
				}
			}
		}
		json.put("dashboardTableColumn", tableColumnArray);

		if (displayUnits != null) {
			String string = displayUnits.toString();
			json.put("displayUnits", new JSONString(string));
		}

		if (drillDownUrl != null) {
			json.put("drillDownUrl", new JSONString(drillDownUrl));
		}

		json.put("drillEnabled", JSONBoolean.getInstance(drillEnabled));
		json.put("drillToDetailEnabled",
				JSONBoolean.getInstance(drillToDetailEnabled));
		json.put("enableHover", JSONBoolean.getInstance(enableHover));
		json.put("expandOthers", JSONBoolean.getInstance(expandOthers));
		if (footer != null) {
			json.put("footer", new JSONString(footer));
		}
		json.put("gaugeMax", new JSONNumber(gaugeMax));
		json.put("gaugeMin`", new JSONNumber(gaugeMin));
		if (groupingColumn != null) {
			json.put("groupingColumn", new JSONString(groupingColumn));
		}
		if (header != null) {
			json.put("header", new JSONString(header));
		}
		json.put("indicatorBreakpoint1", new JSONNumber(indicatorBreakpoint1));
		json.put("indicatorBreakpoint2", new JSONNumber(indicatorBreakpoint2));
		if (indicatorHighColor != null) {
			json.put("indicatorHighColor", new JSONString(indicatorHighColor));
		}
		if (indicatorLowColor != null) {
			json.put("indicatorLowColor", new JSONString(indicatorLowColor));
		}
		if (indicatorMiddleColor != null) {
			json.put("indicatorMiddleColor", new JSONString(
					indicatorMiddleColor));
		}
		if (legendPosition != null) {
			String string = legendPosition.toString();
			json.put("legendPosition", new JSONString(string));
		}

		json.put("maxValuesDisplayed", new JSONNumber(maxValuesDisplayed));
		json.put("metricLabel", new JSONString(metricLabel));
		json.put("page", new JSONString(page));
		json.put("pageHeightInPixels", new JSONNumber(pageHeightInPixels));
		if (report != null) {
			json.put("report", new JSONString(report));
		}

		json.put("scontrol", new JSONString(scontrol));
		json.put("scontrolHeightInPixels", new JSONNumber(
				scontrolHeightInPixels));
		json.put("showPercentage", JSONBoolean.getInstance(showPercentage));
		json.put("showPicturesOnCharts",
				JSONBoolean.getInstance(showPicturesOnCharts));
		json.put("showPicturesOnTables",
				JSONBoolean.getInstance(showPicturesOnTables));
		json.put("showTotal", JSONBoolean.getInstance(showTotal));
		json.put("showValues", JSONBoolean.getInstance(showValues));
		if (sortBy != null) {
			String string = sortBy.toString();
			json.put("sortBy", new JSONString(string));
		}
		if (title != null) {
			json.put("title", new JSONString(title));
		}
		json.put("useReportChart", JSONBoolean.getInstance(useReportChart));

		return json;
	}

	public DashboardComponent clone() {
		DashboardComponent dashboardComponent = new DashboardComponent();
		super.clone(dashboardComponent);
		dashboardComponent.chartAxisRange = this.chartAxisRange;
		dashboardComponent.chartAxisRangeMax = this.chartAxisRangeMin;
		dashboardComponent.chartAxisRangeMin = this.chartAxisRangeMin;

		if (chartSummary != null) {
			dashboardComponent.chartSummary = this.chartSummary.clone();
		}
		dashboardComponent.componentType = this.componentType;

		List<DashboardFilterColumns> clonedColumns = new ArrayList<DashboardFilterColumns>();
		for (DashboardFilterColumns columns : this.dashboardFilterColumns) {
			if (columns != null) {
				DashboardFilterColumns clone = columns.clone();
				clonedColumns.add(clone);
			}

		}
		dashboardComponent.dashboardFilterColumns = clonedColumns;

		List<DashboardTableColumn> clonedTableColumns = new ArrayList<DashboardTableColumn>();
		for (DashboardTableColumn column : this.dashboardTableColumn) {
			if (column != null) {
				DashboardTableColumn clone = column.clone();
				clonedTableColumns.add(clone);
			}
		}
		dashboardComponent.dashboardTableColumn = clonedTableColumns;

		dashboardComponent.displayUnits = this.displayUnits;
		dashboardComponent.drillDownUrl = this.drillDownUrl;
		dashboardComponent.drillEnabled = this.drillEnabled;
		dashboardComponent.drillToDetailEnabled = this.drillToDetailEnabled;
		dashboardComponent.enableHover = this.enableHover;
		dashboardComponent.expandOthers = this.expandOthers;
		dashboardComponent.footer = this.footer;
		dashboardComponent.gaugeMax = this.gaugeMax;
		dashboardComponent.gaugeMin = this.gaugeMin;
		dashboardComponent.groupingColumn = this.groupingColumn;
		dashboardComponent.header = this.header;
		dashboardComponent.indicatorBreakpoint1 = this.indicatorBreakpoint1;
		dashboardComponent.indicatorBreakpoint2 = this.indicatorBreakpoint2;
		dashboardComponent.indicatorHighColor = this.indicatorHighColor;
		dashboardComponent.indicatorLowColor = this.indicatorLowColor;
		dashboardComponent.indicatorMiddleColor = this.indicatorMiddleColor;
		dashboardComponent.legendPosition = this.legendPosition;
		dashboardComponent.maxValuesDisplayed = this.maxValuesDisplayed;
		dashboardComponent.metricLabel = this.metricLabel;
		dashboardComponent.page = this.page;
		dashboardComponent.pageHeightInPixels = this.pageHeightInPixels;
		dashboardComponent.report = this.report;
		dashboardComponent.scontrol = this.scontrol;
		dashboardComponent.scontrolHeightInPixels = this.scontrolHeightInPixels;
		dashboardComponent.showPercentage = this.showPercentage;
		dashboardComponent.showPicturesOnCharts = this.showPicturesOnCharts;
		dashboardComponent.showPicturesOnTables = this.showPicturesOnTables;
		dashboardComponent.showTotal = this.showTotal;
		dashboardComponent.showValues = this.showValues;
		dashboardComponent.sortBy = this.sortBy;
		dashboardComponent.title = this.title;
		return dashboardComponent;
	}
}
