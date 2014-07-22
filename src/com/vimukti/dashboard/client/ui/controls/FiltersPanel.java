package com.vimukti.dashboard.client.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.vimukti.dashboard.client.data.DashboardFilterOperation;
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
		if (dashboardFilters == null || dashboardFilters.isEmpty()) {
			return;
		}

		List<Label> listTOshow = new ArrayList<Label>();
		for (DashboardFilters filter : dashboardFilters) {
			List<DashboardFilterOptions> dashboardFilterOptions = filter
					.getDashboardFilterOptions();
			for (DashboardFilterOptions filterOptions2 : dashboardFilterOptions) {
				DashboardFilterOperation operator = filterOptions2
						.getOperator();
				String value = filterOptions2.getValue();
				Label showLabel = new Label(operator.toString() + " " + value);
				listTOshow.add(showLabel);
			}

			SelectListBox<Label> filterbox = new SelectListBox<Label>();
			filterbox.setItems(listTOshow);
		}
	}

}
