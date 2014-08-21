package com.vimukti.dashboard.client.reportdata;

public enum ReportAggrType {

	SUM("Sum"),

	AVERAGE("Average"),

	MAXIMUM("Maximum"),

	MINIMUM("Minimum"),

	ROW_COUNT("Row Count");

	private String name;

	private ReportAggrType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static ReportAggrType getReportAggrType(String value) {

		for (ReportAggrType type : ReportAggrType.values()) {
			if (type.name.equals(value)) {
				return type;
			}
		}
		return null;
	}
}
