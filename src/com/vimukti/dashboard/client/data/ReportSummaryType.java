package com.vimukti.dashboard.client.data;

public enum ReportSummaryType {

	SUM("Sum"),

	AVERAGE("Average"),

	MAXIMUM("Maximum"),

	MINIMUM("Minimum"),

	NONE("None");

	private String name;

	private ReportSummaryType(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public static ReportSummaryType getSummaryType(String name) {
		for (ReportSummaryType type : ReportSummaryType.values()) {
			if (type.name.equals(name)) {
				return type;
			}
		}
		return null;
	}
}
