package com.vimukti.dashboard.client.reportdata;

public enum ReportAggregateDatatype {

	CURRENCY("currency"),

	NUMBER("number"),

	PERCENT("percent");

	private String name;

	private ReportAggregateDatatype(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static ReportAggregateDatatype getAggregateDatatype(String value) {
		for (ReportAggregateDatatype type : ReportAggregateDatatype.values()) {
			if (type.name.equals(value)) {
				return type;
			}
		}
		return null;
	}
}