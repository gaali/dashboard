package com.vimukti.dashboard.client.data;

public enum DashboardComponentType {

	BAR("Bar"),

	BAR_GROUPED("Bar Grouped"),

	BAR_STACKED("Bar Stacked"),

	BAR_STACKED100("Bar Stacked Full"),

	COLUMN("Column"),

	COLUMN_GROUPED("Column Grouped"),

	COLUMN_LINE("Column Line"),

	COLUMN_LINE_GROUPED("Column Line Grouped"),

	COLUMN_LINE_STACKED("Column Line Stacked"),

	COLUMN_LINE_STACKED100("Column Line Stacked Full"),

	COLUMN_STACKED("Column Stacked"),

	COLUMN_STACKED100("Column Stacked Full"),

	DONUT("Donut"),

	FUNNEL("Funnel"),

	GAUGE("Gauge"),

	LINE("Line"),

	LINE_CUMULATIVE("Line Cumulative"),

	LINE_GROUPED("LineGrouped"),

	LINE_GROUPED_CUMULATIVE("Line Grouped Cumulative"),

	METRIC("Metric"),

	PIE("Pie"),

	SCATTER("Scatter"),

	SCATTER_GROUPED("Scatter Grouped"),

	SCONTROL("Scontrol"),

	TABLE("Table"),

	PAGE("Page"), REPORT("Report");

	private String name;

	private DashboardComponentType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static DashboardComponentType getComponentType(String value) {
		for (DashboardComponentType type : DashboardComponentType.values()) {
			if (type.name.equals(value)) {
				return type;
			}
		}
		return null;
	}
}
