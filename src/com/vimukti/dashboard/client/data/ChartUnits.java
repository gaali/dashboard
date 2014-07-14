package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ChartUnits {

	@XmlEnumValue("Auto")
	AUTO,

	@XmlEnumValue("Integer")
	INTEGER,

	@XmlEnumValue("Hundreds")
	HUNDREDS,

	@XmlEnumValue("Thousands")
	THOUSANDS,

	@XmlEnumValue("Millions")
	MILLIONS,

	@XmlEnumValue("Billions")
	BILLIONS,

	@XmlEnumValue("Trillions")
	TRILLIONS

}
