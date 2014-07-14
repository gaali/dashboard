package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ReportSummaryType {

	@XmlEnumValue("Sum")
	SUM,

	@XmlEnumValue("Average")
	AVERAGE,

	@XmlEnumValue("Maximum")
	MAXIMUM,

	@XmlEnumValue("Minimum")
	MINIMUM,

	@XmlEnumValue("None")
	NONE
}
