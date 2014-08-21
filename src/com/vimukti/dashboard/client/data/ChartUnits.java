package com.vimukti.dashboard.client.data;

public enum ChartUnits {

	AUTO("Auto"),

	INTEGER("Integer"),

	HUNDREDS("Hundreds"),

	THOUSANDS("Thousands"),

	MILLIONS("Millions"),

	BILLIONS("Billions"),

	TRILLIONS("Trillions");

	private String name;

	private ChartUnits(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static ChartUnits getUnits(String value) {
		for (ChartUnits unit : ChartUnits.values()) {
			if (unit.name.equals(value)) {
				return unit;
			}
		}
		return null;
	}
}
