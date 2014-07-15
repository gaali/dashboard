package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.user.client.ui.FlowPanel;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.ui.controls.dnd.IDraggable;

public class ChartComponent extends FlowPanel implements IDraggable {
	private DashboardComponentType type;
	private String chartName;

	public ChartComponent(DashboardComponentType ComponentType, String chartName) {
		this.type = ComponentType;
		this.chartName = chartName;
	}

	/**
	 * @return the type
	 */
	public DashboardComponentType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(DashboardComponentType type) {
		this.type = type;
	}

	/**
	 * @return the chartName
	 */
	public String getChartName() {
		return chartName;
	}

	/**
	 * @param chartName
	 *            the chartName to set
	 */
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

}
