package com.vimukti.dashboard.client.reportdata;

public enum ColumnDataType {
	BOOLEAN_DATA, COMBOBOX_DATA, CURRENCY_DATA, DATETIME_DATA, DATE_DATA, DOUBLE_DATA, EMAIL_DATA, ID_DATA, INT_DATA, MULTIPICKLIST_DATA, PERCENT_DATA, PHONE_DATA, PICKLIST_DATA, REFERENCE_DATA, STRING_DATA, TEXTAREA_DATA, TIME_DATA, URL_DATA;
	public static ColumnDataType get(double ordinalVal) {

		for (ColumnDataType type : ColumnDataType.values()) {
			if (type.ordinal() == ordinalVal) {
				return type;
			}
		}
		return null;
	}
}
