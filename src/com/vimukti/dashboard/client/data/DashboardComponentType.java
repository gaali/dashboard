package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum DashboardComponentType {

	@XmlEnumValue("Bar")
	BAR,

	@XmlEnumValue("BarGrouped")
	BAR_GROUPED,

	@XmlEnumValue("BarStacked")
	BAR_STACKED,

	@XmlEnumValue("BarStacked100")
	BAR_STACKED100,

	@XmlEnumValue("Column")
	COLUMN,

	@XmlEnumValue("ColumnGrouped")
	COLUMN_GROUPED,

	@XmlEnumValue("ColumnLine")
	COLUMN_LINE,

	@XmlEnumValue("ColumnLineGrouped")
	COLUMN_LINE_GROUPED,

	@XmlEnumValue("ColumnLineStacked")
	COLUMN_LINE_STACKED,

	@XmlEnumValue("ColumnLineStacked100")
	COLUMN_LINE_STACKED100,

	@XmlEnumValue("ColumnStacked")
	COLUMN_STACKED,

	@XmlEnumValue("ColumnStacked100")
	COLUMN_STACKED100,

	@XmlEnumValue("Donut")
	DONUT,

	@XmlEnumValue("Funnel")
	FUNNEL,

	@XmlEnumValue("Gauge")
	GAUGE,

	@XmlEnumValue("Line")
	LINE,

	@XmlEnumValue("lineCumulative")
	LINE_CUMULATIVE,

	@XmlEnumValue("LineGrouped")
	LINE_GROUPED,

	@XmlEnumValue("lineGroupedCumulative")
	LINE_GROUPED_CUMULATIVE,

	@XmlEnumValue("Metric")
	METRIC,

	@XmlEnumValue("Pie")
	PIE,

	@XmlEnumValue("Scatter")
	SCATTER,

	@XmlEnumValue("ScatterGrouped")
	SCATTER_GROUPED,

	@XmlEnumValue("Scontrol")
	SCONTROL,

	@XmlEnumValue("Table")
	TABLE,

	PAGE

}
