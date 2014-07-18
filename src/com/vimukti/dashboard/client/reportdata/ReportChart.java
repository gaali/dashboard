package com.vimukti.dashboard.client.reportdata;

import java.util.List;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportChart extends MetaObject {

	private String backgroundColor1;

	private String backgroundColor2;

	// ChartBackgroundDirection
	private String backgroundFadeDir;

	private List<ChartSummary> chartSummaries;

	// ChartType
	private String chartType;

	private boolean enableHoverLabels;

	private boolean expandOthers;

	private String groupingColumn;

	// ChartLegendPosition
	private String legendPosition;

	// ChartPosition
	private String location;

	private String secondaryGroupingColumn;

	private boolean booleanshowAxisLabels;

	private boolean showPercentage;

	private boolean showTotal;

	private boolean showValues;

	// ReportChartSize
	private String size;

	// ReportSummaryType
	private String summaryAggregate;

	private double summaryAxisManualRangeEnd;

	private double summaryAxisManualRangeStart;

	// ChartRangeType
	private String summaryAxisRange;

	private String summaryColumn;

	private String textColor;

	private int textSize;

	public ReportChart() {
	}

	public String getBackgroundColor1() {
		return backgroundColor1;
	}

	public void setBackgroundColor1(String backgroundColor1) {
		this.backgroundColor1 = backgroundColor1;
	}

	public String getBackgroundColor2() {
		return backgroundColor2;
	}

	public void setBackgroundColor2(String backgroundColor2) {
		this.backgroundColor2 = backgroundColor2;
	}

	public String getBackgroundFadeDir() {
		return backgroundFadeDir;
	}

	public void setBackgroundFadeDir(String backgroundFadeDir) {
		this.backgroundFadeDir = backgroundFadeDir;
	}

	public List<ChartSummary> getChartSummaries() {
		return chartSummaries;
	}

	public void setChartSummaries(List<ChartSummary> chartSummaries) {
		this.chartSummaries = chartSummaries;
	}

	public String getString() {
		return chartType;
	}

	public void setString(String chartType) {
		this.chartType = chartType;
	}

	public boolean isEnableHoverLabels() {
		return enableHoverLabels;
	}

	public void setEnableHoverLabels(boolean enableHoverLabels) {
		this.enableHoverLabels = enableHoverLabels;
	}

	public boolean isExpandOthers() {
		return expandOthers;
	}

	public void setExpandOthers(boolean expandOthers) {
		this.expandOthers = expandOthers;
	}

	public String getGroupingColumn() {
		return groupingColumn;
	}

	public void setGroupingColumn(String groupingColumn) {
		this.groupingColumn = groupingColumn;
	}

	public String getLegendPosition() {
		return legendPosition;
	}

	public void setLegendPosition(String legendPosition) {
		this.legendPosition = legendPosition;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSecondaryGroupingColumn() {
		return secondaryGroupingColumn;
	}

	public void setSecondaryGroupingColumn(String secondaryGroupingColumn) {
		this.secondaryGroupingColumn = secondaryGroupingColumn;
	}

	public boolean isBooleanshowAxisLabels() {
		return booleanshowAxisLabels;
	}

	public void setBooleanshowAxisLabels(boolean booleanshowAxisLabels) {
		this.booleanshowAxisLabels = booleanshowAxisLabels;
	}

	public boolean isShowPercentage() {
		return showPercentage;
	}

	public void setShowPercentage(boolean showPercentage) {
		this.showPercentage = showPercentage;
	}

	public boolean isShowTotal() {
		return showTotal;
	}

	public void setShowTotal(boolean showTotal) {
		this.showTotal = showTotal;
	}

	public boolean isShowValues() {
		return showValues;
	}

	public void setShowValues(boolean showValues) {
		this.showValues = showValues;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSummaryAggregate() {
		return summaryAggregate;
	}

	public void setSummaryAggregate(String summaryAggregate) {
		this.summaryAggregate = summaryAggregate;
	}

	public double getSummaryAxisManualRangeEnd() {
		return summaryAxisManualRangeEnd;
	}

	public void setSummaryAxisManualRangeEnd(double summaryAxisManualRangeEnd) {
		this.summaryAxisManualRangeEnd = summaryAxisManualRangeEnd;
	}

	public double getSummaryAxisManualRangeStart() {
		return summaryAxisManualRangeStart;
	}

	public void setSummaryAxisManualRangeStart(
			double summaryAxisManualRangeStart) {
		this.summaryAxisManualRangeStart = summaryAxisManualRangeStart;
	}

	public String getSummaryAxisRange() {
		return summaryAxisRange;
	}

	public void setSummaryAxisRange(String summaryAxisRange) {
		this.summaryAxisRange = summaryAxisRange;
	}

	public String getSummaryColumn() {
		return summaryColumn;
	}

	public void setSummaryColumn(String summaryColumn) {
		this.summaryColumn = summaryColumn;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

}
