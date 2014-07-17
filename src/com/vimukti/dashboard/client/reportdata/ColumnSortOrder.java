package com.vimukti.dashboard.client.reportdata;

public enum ColumnSortOrder {
	ASCENDING, DESCENDING;
	public static ColumnSortOrder get(double ordinalVal) {

		for (ColumnSortOrder type : ColumnSortOrder.values()) {
			if (type.ordinal() == ordinalVal) {
				return type;
			}
		}
		return null;
	}
}
