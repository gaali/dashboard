package com.vimukti.dashboard.client.chart.ui.controls;

public enum DrillDownToType {

	SOURCE_REPORT("Source Report"),

	FILTERED_SOURCE_REPORT("Filtered Source Report"),

	RECORD_DETAIL_PAGE("Record Detail Page"),

	OTHER_URL("Other Url");

	private String name;

	private DrillDownToType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
