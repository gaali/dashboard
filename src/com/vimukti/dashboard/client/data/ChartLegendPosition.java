package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ChartLegendPosition {

	@XmlEnumValue("Bottom")
	BOTTOM,

	@XmlEnumValue("OnChart")
	ON_CHART,

	@XmlEnumValue("Right")
	RIGHT

}
