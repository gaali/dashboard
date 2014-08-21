package com.vimukti.dashboard.client.data;

public enum ChartBackgroundDirection {
	DIAGONAL("Diagonal"),

	LEFT_TO_RIGHT("LeftToRight"),

	TOP_TO_BOTTOM("TopToBottom");

	private String name;

	private ChartBackgroundDirection(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static ChartBackgroundDirection getDirection(String value) {
		for (ChartBackgroundDirection ch : ChartBackgroundDirection.values()) {
			if (ch.name == value) {
				return ch;
			}
		}
		return null;
	}

}
