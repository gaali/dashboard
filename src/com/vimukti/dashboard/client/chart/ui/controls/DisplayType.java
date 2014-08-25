package com.vimukti.dashboard.client.chart.ui.controls;

/**
 * Display type for chart Preview in Chart type for Column in Combination panel
 * 
 *
 */
public enum DisplayType {

	LINE("Line"),

	COlUMN("Column");
	private String name;

	private DisplayType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
