package com.vimukti.dashboard.client.reportdata;

public enum ChartAxis {
	X("X"), Y("Y"), Y2("Y2");
	private String name;

	private ChartAxis(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static ChartAxis getAxis(String value) {
		for (ChartAxis axis : ChartAxis.values()) {
			if (axis.name.equals(value)) {
				return axis;
			}
		}
		return null;
	}
}
