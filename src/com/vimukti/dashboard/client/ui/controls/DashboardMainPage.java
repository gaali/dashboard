package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukt.dashboard.client.column.DashboardColumnsPanel;
import com.vimukti.dashboard.client.data.Dashboard;

public class DashboardMainPage extends VerticalPanel {

	private Dashboard dashboard;
	private FiltersPanel filtersPanel;
	private DescriptionPanel descriptionPanel;
	private DashboardColumnsPanel columnsPanel;

	public DashboardMainPage(Dashboard dashBoard) {
		this.dashboard = dashBoard;
		createControls();
	}

	private void createControls() {
		createFilterPanel();
		createDescriptionPanel();
		createColumnPanel();
	}

	private void createFilterPanel() {
		filtersPanel = new FiltersPanel(dashboard.getDashboardFilters());
		filtersPanel.addStyleName("filterspanel");
		this.add(filtersPanel);
	}

	private void createDescriptionPanel() {
		descriptionPanel = new DescriptionPanel(dashboard.getDescription());
		descriptionPanel.addStyleName("descriptionPanel");
		this.add(descriptionPanel);
	}

	private void createColumnPanel() {
		columnsPanel = new DashboardColumnsPanel(dashboard);
		this.add(columnsPanel);
	}

}
