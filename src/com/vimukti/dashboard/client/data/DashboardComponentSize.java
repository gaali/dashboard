package com.vimukti.dashboard.client.data;

public enum DashboardComponentSize {
	MEDIUM("Medium"), NARROW("Narrow"), WIDE("Wide");

	private String name;

	private DashboardComponentSize(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static DashboardComponentSize getComponentSize(String value) {
		for (DashboardComponentSize size : DashboardComponentSize.values()) {
			if (size.name.equals(value)) {
				return size;
			}
		}
		return null;
	}

}
