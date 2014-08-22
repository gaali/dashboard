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

	/**
	 * default value for high color
	 */
	public static String HIGH_COLOR = "ca54c2";

	/**
	 * default value for mid color
	 */
	public static String MID_COLOR = "c2545a";

	/**
	 * default value for low color
	 */
	public static String LOW_COLOR = "c2e354";

	/**
	 * A manual or automatic axis range for bar or line charts. The valid values
	 * are: auto, manual
	 */
	private ChartRangeType chartAxisRange = ChartRangeType.AUTO;

	/**
	 * The maximum axis range to be displayed. This only applies to bar and line
	 * charts in which the manual axis range is selected for the chartAxisRange
	 * field.
	 */
	private double chartAxisRangeMax;

	/**
	 * The minimum axis range to be displayed. This only applies to bar and line
	 * charts in which the manual axis range is selected for the chartAxisRange
	 * field.
	 */
	private double chartAxisRangeMin;

	/**
	 * Specifies the summary field for the chart data. Required if
	 * isAutoSelectFromReport is set to false.
	 */
	private ChartSummary chartSummary;

	/**
	 * 
	 */
	private DashboardComponentType componentType;

	/**
	 * A list of dashboard filter columns. Each report-based component must have
	 * a dashboard filter column that defines the column that the filter applies
	 * to.
	 */
	private List<DashboardFilterColumns> dashboardFilterColumns;

	/**
	 * Represents a list of columns on a customized dashboard table component.
	 */
	private List<DashboardTableColumn> dashboardTableColumn;

	/**
	 * Chart Units. The valid values are:
	 * 
	 * Auto Integer Hundreds Thousands Millions Billions Trillions
	 */
	private ChartUnits displayUnits = ChartUnits.AUTO;

	/**
	 * specifies a URL that users go to when they click the dashboard component.
	 * Use this option to send users to another dashboard, report, record detail
	 * page, or other system that uses a Web interface. This field overrides the
	 * drillEnabled and drillToDetailEnabled fields.
	 */
	private String drillDownUrl;

	/**
	 * Specifies whether to take users to the full or filtered source report
	 * when they click the dashboard component. Set to false to drill to the
	 * full source report; set to true to drill to the source report filtered by
	 * what they clicked. If set to true, users can click individual groups,
	 * axis values, or legend entries.
	 * 
	 * This overrides the drillToDetailEnabled field
	 */
	private boolean drillEnabled;

	/**
	 * When enabled, users are taken to the record detail page when they click a
	 * record name, record owner, or feed post in a table or chart. When set to
	 * true users can click axis and legend values, chart elements, and table
	 * entries. The drillDownUrl and drillEnabled fields override this field.
	 * This field is available in API version 20.0 and later.
	 */
	private boolean drillToDetailEnabled;

	/**
	 * Specifies whether to display values, labels, and percentages when
	 * hovering over charts. Hover details depend on chart type. Percentages
	 * apply to pie, donut, and funnel charts only.
	 */
	private boolean enableHover;

	/**
	 * Specifies whether to combine all groups less than or equal to 3% of the
	 * total into a single 'Others' wedge or segment. This only applies to pie,
	 * donut, and funnel charts. Set to true to show all values individually on
	 * the chart; set to false to combine small groups into 'Others.
	 */
	private boolean expandOthers = true;

	/**
	 * Footer displayed at the bottom of the dashboard component. Maximum of 255
	 * characters.
	 */
	private String footer;

	/**
	 * The maximum value on a gauge. A gauge is used to see how far you are from
	 * reaching a goal. It looks like a speedometer in a car.
	 */
	private double gaugeMax;

	/**
	 * The minimum value on a gauge.
	 */
	private double gaugeMin;

	/**
	 * Specifies the field by which to group data. This data is displayed on the
	 * X-axis for vertical column charts and on the Y-axis for horizontal bar
	 * charts.
	 */
	private String groupingColumn;

	/**
	 * Header displayed at the top of the dashboard component.
	 */
	private String header;

	/**
	 * The value that separates the indicatorLowColor from the
	 * indicatorMiddleColor on the dashboard.
	 * 
	 */
	private double indicatorBreakpoint1;

	/**
	 * The value that separates the indicatorMiddleColor from the
	 * indicatorHighColor on the dashboard.
	 */
	private double indicatorBreakpoint2;

	/**
	 * The color representing a high number range on the gauge.
	 */
	private String indicatorHighColor = HIGH_COLOR;

	/**
	 * The color representing a low number range on the gauge.
	 */
	private String indicatorLowColor = MID_COLOR;

	/**
	 * The color representing a medium number range on the gauge.
	 */
	private String indicatorMiddleColor = LOW_COLOR;

	/**
	 * 
	 The location of the legend with respect to the chart. The valid values
	 * are:
	 * 
	 * Bottom OnChart Right
	 */
	private ChartLegendPosition legendPosition = ChartLegendPosition.BOTTOM;

	/**
	 * The maximum number of elements to include in the top-level grouping of
	 * the horizontal axis of a horizontal chart, vertical axis of a vertical
	 * chart, or selected axis of a stacked bar chart. For example, if you want
	 * to list only your top five salespeople, create an opportunity report that
	 * lists total opportunity amounts by owner and enter 5 in this field.
	 */
	private int maxValuesDisplayed;

	/**
	 * Descriptive label for the metric. This is relevant if metric is the value
	 * of the componentType field.
	 */
	private String metricLabel;

	/**
	 * Visualforce page associated with the component.
	 */
	private String page;

	/**
	 * Display height of the s-control in pixels.
	 */
	private int pageHeightInPixels;

	/**
	 * Name of the report associated with the component.
	 */
	private String report;

	/**
	 * S-control associated with component if scontrol is the value of the
	 * componentType field. For more information, see “Defining Custom
	 * S-Controls” in the Salesforce online help.
	 */
	private String scontrol;

	/**
	 * Display height of the s-control in pixels.
	 */
	private int scontrolHeightInPixels;

	/**
	 * Indicates if percentages are displayed for regions of gauges and wedges
	 * and segments of pie, donut, and funnel charts (true), or not (false).
	 * 
	 */
	private boolean showPercentage;

	/**
	 * Display Chatter photos for up to 20 records in a horizontal bar chart
	 * component whose source report is grouped by a user or group name field.
	 * If there are more than 20 records with photos, record names are shown
	 * instead of photos. Set Grouping Display to None to show photos. Set the
	 * Drill Down to option to Record Detail Page to take users directly to user
	 * profile or group pages when they click photos. Chatter must be enabled
	 * for photos to be displayed. Depending on your organization's setup, you
	 * may not see photos on tables and charts.
	 */
	private boolean showPicturesOnCharts;

	/**
	 * Display Chatter photos for up to 20 records in a horizontal bar chart
	 * component whose source report is grouped by a user or group name field.
	 * If there are more than 20 records with photos, record names are shown
	 * instead of photos. Set Grouping Display to None to show photos. Set the
	 * Drill Down to option to Record Detail Page to take users directly to user
	 * profile or group pages when they click photos. Chatter must be enabled
	 * for photos to be displayed. Depending on your organization's setup, you
	 * may not see photos on tables and charts.
	 */
	private boolean showPicturesOnTables;

	/**
	 * Indicates if the total of all wedges is displayed for gauges and donut
	 * charts (true), or not (false).
	 */
	private boolean showTotal;

	/**
	 * Indicates if the values of individual records or groups are displayed for
	 * charts (true), or not (false).
	 */
	private boolean showValues = true;

	/**
	 * The sort option for the dashboard component.
	 */
	private DashboardComponentFilter sortBy = DashboardComponentFilter.ROW_LABEL_ASCENDING;

	/**
	 * The title of the dashboard component. Maximum of 40 characters.
	 */
	private String title;

	/**
	 * Specifies whether to use the chart defined in the source report on this
	 * dashboard component. The chart settings in the source report determine
	 * how the chart displays in the dashboard, and any chart settings you
	 * define for the dashboard are overridden. If you defined a combination
	 * chart in the source report, use this option to use that combination chart
	 * on this dashboard.
	 * 
	 */
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
