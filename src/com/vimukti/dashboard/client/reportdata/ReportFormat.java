package com.vimukti.dashboard.client.reportdata;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ReportFormat {

	@XmlEnumValue("Matrix")
	MATRIX,

	@XmlEnumValue("Summary")
	SUMMARY,

	@XmlEnumValue("Tabular")
	TABULAR,

	@XmlEnumValue("Joined")
	JOINED;

	public static ReportFormat get(double doubleValue) {

		return null;
	}

}
