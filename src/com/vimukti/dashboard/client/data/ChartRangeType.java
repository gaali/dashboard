package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ChartRangeType {

	@XmlEnumValue("auto")
	AUTO,

	@XmlEnumValue("manual")
	MANUAL,

}
