package com.vimukti.dashboard.client.chart.ui.controls;

public enum DisplayType {
	LINE("Line"), COlUMN("Column");
	private String name;

	private DisplayType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
