package com.vimukti.dashboard.client.reportdata;

public enum DateGranularity {
	;
	public static DateGranularity get(double ordinalVal) {

		for (DateGranularity type : DateGranularity.values()) {
			if (type.ordinal() == ordinalVal) {
				return type;
			}
		}
		return null;
	}
}
