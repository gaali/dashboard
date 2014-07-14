package com.vimukti.dashboard.client.data;

import java.util.List;

@SuppressWarnings("serial")
public class DashboardComponent extends MetaObject {

	private ChartRangeType chartAxisRange;

	private double chartAxisRangeMax;

	private double chartAxisRangeMin;

	private ChartSummary chartSummary;

	private DashboardComponentType componentType;

	private List<DashboardFilterColumns> dashboardFilterColumns;

	private List<DashboardTableColumn> dashboardTableColumn;

	private ChartUnits displayUnits;

	private String drillDownUrl;

	private boolean drillEnabled;

	private boolean drillToDetailEnabled;

	private boolean enableHover;

	private boolean expandOthers;

	private String footer;

	private double gaugeMax;

	private double gaugeMin;

	private String groupingColumn;

	private String header;

	private double indicatorBreakpoint1;

	private double indicatorBreakpoint2;

	private String indicatorHighColor;

	private String indicatorLowColor;

	private String indicatorMiddleColor;

	private ChartLegendPosition legendPosition;

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

	private boolean showValues;

	private DashboardComponentFilter sortBy;

	private String title;

	private boolean useReportChart;

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
}
