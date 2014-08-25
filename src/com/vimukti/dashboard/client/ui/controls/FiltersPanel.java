package com.vimukti.dashboard.client.ui.controls;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.data.DashboardFilterOperation;
import com.vimukti.dashboard.client.data.DashboardFilterOptions;
import com.vimukti.dashboard.client.data.DashboardFilters;

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

		for (final DashboardFilters filter : dashboardFilters) {

			final PopupPanel popupPanel = new PopupPanel(true);

			final TextBox textBox = new TextBox();
			textBox.addStyleName("drop-down-readonly-box");
			textBox.setReadOnly(true);
			textBox.addFocusHandler(new FocusHandler() {

				@Override
				public void onFocus(FocusEvent event) {
					showPopup(popupPanel, textBox);
				}
			});

			textBox.setText(filter.getDisplayLabel());

			Anchor html = new Anchor();
			html.addStyleName("addon");
			html.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					showPopup(popupPanel, textBox);
				}
			});

			FlowPanel flowPanel = new FlowPanel();
			flowPanel.addStyleName("drop-down-box");

			flowPanel.add(textBox);
			flowPanel.add(html);

			this.add(flowPanel);

			Anchor clearFilter = new Anchor("Clear Filter");
			clearFilter.addStyleName("clearfilter");
			clearFilter.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					textBox.setText(filter.getDisplayLabel());
					refreshMainPage();
				}
			});

			Anchor editFilter = new Anchor("Edit Filter");
			editFilter.addStyleName("Edit Filter");
			editFilter.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					getEditFilterDialog(filter);
				}
			});

			Anchor remove = new Anchor("Remov Filter");
			remove.addStyleName("removefilter");
			remove.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					dashboardFilters.remove(filter);
					refreshMainPage();
				}
			});

			FlowPanel popupContainer = new FlowPanel();
			popupContainer.addStyleName("popup-container");

			popupContainer.add(clearFilter);

			List<DashboardFilterOptions> dashboardFilterOptions = filter
					.getDashboardFilterOptions();
			for (DashboardFilterOptions filterOptions2 : dashboardFilterOptions) {
				DashboardFilterOperation operator = filterOptions2
						.getOperator();
				String value = filterOptions2.getValue();

				final String anchorTitle = operator.toString() + " " + value;
				Anchor showLabel = new Anchor(anchorTitle);

				showLabel.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						textBox.setText(filter.getDisplayLabel() + ": "
								+ anchorTitle);
						refreshMainPage();
					}
				});
				popupContainer.add(showLabel);
			}

			popupContainer.add(editFilter);

			popupContainer.add(remove);

			popupPanel.add(popupContainer);

		}
	}

	/**
	 * recreating dashboard page on dashboard filter change,remove or add.
	 */
	protected void refreshMainPage() {
		Dashboard.reCreateMainPage();
	}

	protected void showPopup(PopupPanel popupPanel, TextBox textBox) {
		popupPanel.showRelativeTo(textBox);
		popupPanel.setPopupPosition(textBox.getAbsoluteLeft(),
				textBox.getAbsoluteTop());
		popupPanel.show();
	}

	private void getEditFilterDialog(DashboardFilters filter) {
		AddFilterDialog editFilter = new AddFilterDialog(filter);
		editFilter.show();
		editFilter.center();
	}

}
