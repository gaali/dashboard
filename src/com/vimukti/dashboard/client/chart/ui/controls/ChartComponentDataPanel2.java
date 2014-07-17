package com.vimukti.dashboard.client.chart.ui.controls;

import com.google.gwt.user.client.ui.FlowPanel;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;

public class ChartComponentDataPanel2 extends FlowPanel {
	DashboardComponentType chartType;
	private SelectListBox values;
	private SelectListBox wedges;
	private SelectListBox<String> displayUnits;
	private SelectListBox<String> drillDownTo;
	private SelectListBox segments;

	public ChartComponentDataPanel2() {
		createControls();
	}

	private void createControls() {
		if (chartType != DashboardComponentType.TABLE) {
			values = new SelectListBox();
		}
		if (chartType == DashboardComponentType.FUNNEL) {
			segments = new SelectListBox();
		}
		if (chartType != DashboardComponentType.METRIC
				|| chartType != DashboardComponentType.TABLE) {
			wedges = new SelectListBox();
		}
		displayUnits = new SelectListBox();
		drillDownTo = new SelectListBox();
	}
	 
}
