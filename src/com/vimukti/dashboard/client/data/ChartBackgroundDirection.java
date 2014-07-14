package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ChartBackgroundDirection {
	@XmlEnumValue("diagonal")
	DIAGONAL,

	@XmlEnumValue("leftToRight")
	LEFT_TO_RIGHT,

	@XmlEnumValue("topToBottom")
	TOP_TO_BOTTOM,

}
