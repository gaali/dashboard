package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum ChartRangeType {

	AUTO("Auto"),

	MANUAL("Manual");

	private String name;

	private ChartRangeType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static ChartRangeType getRangeType(String value) {
		for (ChartRangeType rangeType : ChartRangeType.values()) {
			if (rangeType.name.equals(value)) {
				return rangeType;
			}
		}
		return null;
	}

}
