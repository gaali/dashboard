package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum DashboardComponentFilter {

	@XmlEnumValue("RowLabelAscending")
	ROW_LABEL_ASCENDING,

	@XmlEnumValue("RowLabelDescending")
	ROW_LABEL_DESCENDING,

	@XmlEnumValue("RowValueAscending")
	ROW_VALUE_ASCENDING,

	@XmlEnumValue("RowValueDescending")
	ROW_VALUE_DESCENDING

}
