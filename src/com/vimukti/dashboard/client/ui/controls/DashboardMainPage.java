package com.vimukti.dashboard.client.ui.controls;

import java.util.List;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.column.DashboardColumnsPanel;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.data.DashboardFilters;

public class DashboardMainPage extends VerticalPanel {

	private DashboardData dashboard;
	private FiltersPanel filtersPanel;
	private DescriptionPanel descriptionPanel;
	private DashboardColumnsPanel columnsPanel;
	private Label showDescription;

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
		this.add(filtersPanel);
	}

	private void createDescriptionPanel() {
		showDescription = new Label(dashboard.getDescription());
		showDescription.addStyleName("show description");

		showDescription.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int widgetIndex = DashboardMainPage.this
						.getWidgetIndex(showDescription);
				DashboardMainPage.this.remove(showDescription);

				addEditableDescriptionPanel(widgetIndex);
			}
		}, ClickEvent.getType());
	}

	private void addEditableDescriptionPanel(final int widgetIndex) {
		descriptionPanel = new DescriptionPanel(dashboard.getDescription());
		descriptionPanel.addDomHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String description = descriptionPanel.getDescription();
				DashboardMainPage.this.remove(descriptionPanel);
				DashboardMainPage.this.insert(showDescription, widgetIndex);
				showDescription.setText(description);
			}
		}, BlurEvent.getType());
		descriptionPanel.addStyleName("descriptionPanel");
		this.insert(descriptionPanel, widgetIndex);
	}

	private void createColumnPanel() {
		columnsPanel = new DashboardColumnsPanel(dashboard);
		this.add(columnsPanel);
	}

}
