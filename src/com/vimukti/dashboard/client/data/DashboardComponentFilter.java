package com.vimukti.dashboard.client.data;

public enum DashboardComponentFilter {

	ROW_LABEL_ASCENDING("Label Ascending"),

	ROW_LABEL_DESCENDING("Label Descending"),

	ROW_VALUE_ASCENDING("Value Ascending"),

	ROW_VALUE_DESCENDING("Value Descending");

	private String name;

	private DashboardComponentFilter(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static DashboardComponentFilter getCompontentFilter(String value) {
		for (DashboardComponentFilter filter : DashboardComponentFilter
				.values()) {
			if (filter.name.equals(value)) {
				return filter;
			}
		}
		return null;
	}
}
