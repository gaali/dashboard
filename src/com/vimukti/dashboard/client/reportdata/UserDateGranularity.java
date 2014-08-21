package com.vimukti.dashboard.client.reportdata;

public enum UserDateGranularity {
	NONE("None"), DAY("Day"), WEEK("Week"), MONTH("Month"), QUARTER("Quarter"), YEAR(
			"Year"), FISCALQUARTER("Fiscal Quarter"), FISCALYEAR("Fiscal Year"), MONTHINYEAR(
			"Month in Year"), DAYINMONTH("Day In Month"), FISCALPERIOD(
			"Fiscal Period"), FISCALWEEK("Fiscal Week");

	private String name;

	private UserDateGranularity(String name) {
		this.name = name;
	}

	public static UserDateGranularity get(String value) {

		for (UserDateGranularity granularity : UserDateGranularity.values()) {
			if (granularity.name == value) {
				return granularity;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
