package com.vimukti.dashboard.client.data;

public enum ChartLegendPosition {

	BOTTOM("Bottom"),

	ON_CHART("OnChart"),

	RIGHT("Right");

	private String name;

	@Override
	public String toString() {
		return this.name;
	}

	private ChartLegendPosition(String name) {
		this.name = name;
	}

	public static ChartLegendPosition gtLegendPosition(String value) {
		for (ChartLegendPosition position : ChartLegendPosition.values()) {
			if (position.name.equals(value)) {
				return position;
			}
		}
		return null;
	}

}
