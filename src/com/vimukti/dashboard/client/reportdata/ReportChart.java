package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.vimukti.dashboard.client.data.ChartBackgroundDirection;
import com.vimukti.dashboard.client.data.ChartLegendPosition;
import com.vimukti.dashboard.client.data.ChartRangeType;
import com.vimukti.dashboard.client.data.MetaObject;
import com.vimukti.dashboard.client.data.ReportSummaryType;

@SuppressWarnings("serial")
public class ReportChart extends MetaObject {

	private String backgroundColor1;

	private String backgroundColor2;

	private ChartBackgroundDirection backgroundFadeDir;

	private List<ChartSummary> chartSummaries;

	// ChartType
	private String chartType;

	private boolean enableHoverLabels;

	private boolean expandOthers;

	private String groupingColumn;

	private ChartLegendPosition legendPosition;

	// ChartPosition
	private String location;

	private String secondaryGroupingColumn;

	private boolean showAxisLabels;

	private boolean showPercentage;

	private boolean showTotal;

	private boolean showValues;

	// ReportChartSize
	private String size;

	private ReportSummaryType summaryAggregate;

	private double summaryAxisManualRangeEnd;

	private double summaryAxisManualRangeStart;

	private ChartRangeType summaryAxisRange;

	private String summaryColumn;

	private String textColor;

	private int textSize;

	/**
	 * @return the backgroundColor1
	 */
	public String getBackgroundColor1() {
		return backgroundColor1;
	}

	/**
	 * @param backgroundColor1
	 *            the backgroundColor1 to set
	 */
	public void setBackgroundColor1(String backgroundColor1) {
		this.backgroundColor1 = backgroundColor1;
	}

	/**
	 * @return the backgroundColor2
	 */
	public String getBackgroundColor2() {
		return backgroundColor2;
	}

	/**
	 * @param backgroundColor2
	 *            the backgroundColor2 to set
	 */
	public void setBackgroundColor2(String backgroundColor2) {
		this.backgroundColor2 = backgroundColor2;
	}

	/**
	 * @return the backgroundFadeDir
	 */
	public ChartBackgroundDirection getBackgroundFadeDir() {
		return backgroundFadeDir;
	}

	/**
	 * @param backgroundFadeDir
	 *            the backgroundFadeDir to set
	 */
	public void setBackgroundFadeDir(ChartBackgroundDirection backgroundFadeDir) {
		this.backgroundFadeDir = backgroundFadeDir;
	}

	/**
	 * @return the chartSummaries
	 */
	public List<ChartSummary> getChartSummaries() {
		return chartSummaries;
	}

	/**
	 * @param chartSummaries
	 *            the chartSummaries to set
	 */
	public void setChartSummaries(List<ChartSummary> chartSummaries) {
		this.chartSummaries = chartSummaries;
	}

	/**
	 * @return the chartType
	 */
	public String getChartType() {
		return chartType;
	}

	/**
	 * @param chartType
	 *            the chartType to set
	 */
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	/**
	 * @return the enableHoverLabels
	 */
	public boolean isEnableHoverLabels() {
		return enableHoverLabels;
	}

	/**
	 * @param enableHoverLabels
	 *            the enableHoverLabels to set
	 */
	public void setEnableHoverLabels(boolean enableHoverLabels) {
		this.enableHoverLabels = enableHoverLabels;
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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the secondaryGroupingColumn
	 */
	public String getSecondaryGroupingColumn() {
		return secondaryGroupingColumn;
	}

	/**
	 * @param secondaryGroupingColumn
	 *            the secondaryGroupingColumn to set
	 */
	public void setSecondaryGroupingColumn(String secondaryGroupingColumn) {
		this.secondaryGroupingColumn = secondaryGroupingColumn;
	}

	/**
	 * @return the booleanshowAxisLabels
	 */
	public boolean isBooleanshowAxisLabels() {
		return showAxisLabels;
	}

	/**
	 * @param booleanshowAxisLabels
	 *            the booleanshowAxisLabels to set
	 */
	public void setBooleanshowAxisLabels(boolean booleanshowAxisLabels) {
		this.showAxisLabels = booleanshowAxisLabels;
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
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the summaryAggregate
	 */
	public ReportSummaryType getSummaryAggregate() {
		return summaryAggregate;
	}

	/**
	 * @param summaryAggregate
	 *            the summaryAggregate to set
	 */
	public void setSummaryAggregate(ReportSummaryType summaryAggregate) {
		this.summaryAggregate = summaryAggregate;
	}

	/**
	 * @return the summaryAxisManualRangeEnd
	 */
	public double getSummaryAxisManualRangeEnd() {
		return summaryAxisManualRangeEnd;
	}

	/**
	 * @param summaryAxisManualRangeEnd
	 *            the summaryAxisManualRangeEnd to set
	 */
	public void setSummaryAxisManualRangeEnd(double summaryAxisManualRangeEnd) {
		this.summaryAxisManualRangeEnd = summaryAxisManualRangeEnd;
	}

	/**
	 * @return the summaryAxisManualRangeStart
	 */
	public double getSummaryAxisManualRangeStart() {
		return summaryAxisManualRangeStart;
	}

	/**
	 * @param summaryAxisManualRangeStart
	 *            the summaryAxisManualRangeStart to set
	 */
	public void setSummaryAxisManualRangeStart(
			double summaryAxisManualRangeStart) {
		this.summaryAxisManualRangeStart = summaryAxisManualRangeStart;
	}

	/**
	 * @return the summaryAxisRange
	 */
	public ChartRangeType getSummaryAxisRange() {
		return summaryAxisRange;
	}

	/**
	 * @param summaryAxisRange
	 *            the summaryAxisRange to set
	 */
	public void setSummaryAxisRange(ChartRangeType summaryAxisRange) {
		this.summaryAxisRange = summaryAxisRange;
	}

	/**
	 * @return the summaryColumn
	 */
	public String getSummaryColumn() {
		return summaryColumn;
	}

	/**
	 * @param summaryColumn
	 *            the summaryColumn to set
	 */
	public void setSummaryColumn(String summaryColumn) {
		this.summaryColumn = summaryColumn;
	}

	/**
	 * @return the textColor
	 */
	public String getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor
	 *            the textColor to set
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return the textSize
	 */
	public int getTextSize() {
		return textSize;
	}

	/**
	 * @param textSize
	 *            the textSize to set
	 */
	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	public ReportChart() {
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jBackgroundCol1 = jsonObject.get("backgroundColor1");
		if (jBackgroundCol1 != null) {
			backgroundColor1 = jBackgroundCol1.isString().stringValue();
		}
		JSONValue jBackgroundCol2 = jsonObject.get("backgroundColor2");
		if (jBackgroundCol2 != null) {
			backgroundColor2 = jBackgroundCol2.isString().stringValue();
		}
		JSONValue jFadeDir = jsonObject.get("backgroundFadeDir");
		if (jFadeDir != null) {
			String stringValue = jFadeDir.isString().stringValue();
			backgroundFadeDir = ChartBackgroundDirection
					.getDirection(stringValue);
		}

		JSONValue jSummaries = jsonObject.get("chartSummaries");
		if (jSummaries != null) {
			JSONArray jSummariesArray = jSummaries.isArray();
			List<ChartSummary> chartSummariesList = new ArrayList<ChartSummary>();
			for (int i = 0; i < jSummariesArray.size(); i++) {
				ChartSummary summary = new ChartSummary();
				JSONValue jsonValue = jSummariesArray.get(i);
				summary.fromJSON(jsonValue.isObject());
				chartSummariesList.add(summary);
			}
			chartSummaries = chartSummariesList;
		}

		JSONValue jChartType = jsonObject.get("chartType");
		if (jChartType != null) {
			chartType = jChartType.isString().stringValue();
		}
		JSONValue jHoverLabel = jsonObject.get("enableHoverLabels");
		if (jHoverLabel != null) {
			enableHoverLabels = jHoverLabel.isBoolean().booleanValue();
		}
		JSONValue jExpandOthers = jsonObject.get("expandOthers");
		if (jExpandOthers != null) {
			expandOthers = jExpandOthers.isBoolean().booleanValue();
		}

		JSONValue jGroupingColumn = jsonObject.get("groupingColumn");
		if (jGroupingColumn != null) {
			groupingColumn = jGroupingColumn.isString().stringValue();
		}

		JSONValue jLegendPosition = jsonObject.get("legendPosition");
		if (jLegendPosition != null) {
			String stringValue = jLegendPosition.isString().stringValue();
			ChartLegendPosition.gtLegendPosition(stringValue);
		}

		JSONValue jLocation = jsonObject.get("location");
		if (jLocation != null) {
			location = jLocation.isString().stringValue();
		}
		JSONValue jSecondaryGroupingColumn = jsonObject
				.get("secondaryGroupingColumn");

		if (jSecondaryGroupingColumn != null) {
			secondaryGroupingColumn = jSecondaryGroupingColumn.isString()
					.stringValue();
		}

		JSONValue jshowAxixLabel = jsonObject.get("showAxisLabels");
		if (jshowAxixLabel != null) {
			showAxisLabels = jshowAxixLabel.isBoolean().booleanValue();
		}

		JSONValue jshowPercentage = jsonObject.get("showPercentage");
		if (jshowPercentage != null) {
			showPercentage = jshowPercentage.isBoolean().booleanValue();
		}

		JSONValue jShowTotal = jsonObject.get("showTotal");
		if (jShowTotal != null) {
			showTotal = jShowTotal.isBoolean().booleanValue();
		}

		JSONValue jShowValues = jsonObject.get("showValues");
		if (jShowValues != null) {
			showValues = jShowValues.isBoolean().booleanValue();
		}

		JSONValue jSize = jsonObject.get("size");
		if (jSize != null) {
			size = jSize.isString().stringValue();
		}

		JSONValue jSummaryAggregate = jsonObject.get("summaryAggregate");
		if (jSummaryAggregate != null) {
			String stringValue = jSummaryAggregate.isString().stringValue();
			summaryAggregate = ReportSummaryType.getSummaryType(stringValue);
		}

		JSONValue jAxisManualRange = jsonObject
				.get("summaryAxisManualRangeStart");
		if (jAxisManualRange != null) {
			summaryAxisManualRangeStart = jAxisManualRange.isNumber()
					.doubleValue();
		}
		JSONValue jAxisRange = jsonObject.get("summaryAxisRange");
		if (jAxisRange != null) {
			String stringValue = jAxisRange.isString().stringValue();
			ChartRangeType.getRangeType(stringValue);
		}

		JSONValue jSimmaryColumn = jsonObject.get("summaryColumn");
		if (jSimmaryColumn != null) {
			summaryColumn = jSimmaryColumn.isString().stringValue();
		}

		JSONValue jTextColor = jsonObject.get("textColor");
		if (jTextColor != null) {
			textColor = jTextColor.isString().stringValue();
		}

		JSONValue jTextSize = jsonObject.get("textSize");
		if (jTextSize != null) {
			textSize = (int) jTextSize.isNumber().doubleValue();
		}

	}

}
