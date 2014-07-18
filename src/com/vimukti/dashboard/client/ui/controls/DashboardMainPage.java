package com.vimukti.dashboard.client.ui.controls;

import java.util.List;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.column.DashboardColumnsPanel;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.data.DashboardFilters;

public class DashboardMainPage extends VerticalPanel {

	private DashboardData dashboard;
	private FiltersPanel filtersPanel;
	private DescriptionPanel descriptionPanel;
	private DashboardColumnsPanel columnsPanel;

	public DashboardMainPage(DashboardData dashBoard) {
		this.dashboard = dashBoard;
		createControls();
	}

	private void createControls() {
		createFilterPanel();
		createDescriptionPanel();
		createColumnPanel();
	}

	private void createFilterPanel() {
		List<DashboardFilters> dashboardFilters = null;
		if (dashboard != null) {
			dashboardFilters = dashboard.getDashboardFilters();
		}
		filtersPanel = new FiltersPanel(dashboardFilters);
		filtersPanel.addStyleName("filterspanel");
		this.add(filtersPanel);
	}

	private void createDescriptionPanel() {
		String description = "";
		if (dashboard.getDescription() != null) {
			description = dashboard.getDescription();
		}
		descriptionPanel = new DescriptionPanel(description);
		descriptionPanel.addStyleName("descriptionPanel");
		this.add(descriptionPanel);
	}

	private void createColumnPanel() {
		columnsPanel = new DashboardColumnsPanel(dashboard);
		this.add(columnsPanel);
	}

}
