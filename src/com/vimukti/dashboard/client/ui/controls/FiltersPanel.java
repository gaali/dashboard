package com.vimukti.dashboard.client.ui.controls;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.vimukti.dashboard.client.data.DashboardFilterOptions;
import com.vimukti.dashboard.client.data.DashboardFilters;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;

public class FiltersPanel extends FlowPanel {
	private List<DashboardFilters> dashboardFilters;

	public FiltersPanel(List<DashboardFilters> dashboardFilters) {
		this.addStyleName("Fileres-panel");
		this.dashboardFilters = dashboardFilters;
		createControls();
	}

	private void createControls() {
		if (dashboardFilters == null) {
			return;
		}
		for (DashboardFilters filter : dashboardFilters) {
			List<DashboardFilterOptions> dashboardFilterOptions = filter
					.getDashboardFilterOptions();
			SelectListBox<DashboardFilterOptions> filterbox = new SelectListBox<DashboardFilterOptions>();
			filterbox.setItems(dashboardFilterOptions);
		}
	}

}
