package com.vimukti.dashboard.client.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
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
		final List<Label> listTOshow = new ArrayList<Label>();
		for (final DashboardFilters filter : dashboardFilters) {
			List<DashboardFilterOptions> dashboardFilterOptions = filter
					.getDashboardFilterOptions();
			for (DashboardFilterOptions filterOptions2 : dashboardFilterOptions) {
				DashboardFilterOperation operator = filterOptions2
						.getOperator();
				String value = filterOptions2.getValue();
				Label showLabel = new Label(operator.toString() + " " + value);
				listTOshow.add(showLabel);
			}

			final SelectListBox<Label> filterbox = new SelectListBox<Label>();
			filterbox.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					if (filterbox.getSelectedIndex() == 0) {
						clearFilter();
					} else if (filterbox.getSelectedIndex() == listTOshow
							.size() - 1) {
						removeFilter(filterbox);
						dashboardFilters.remove(filterbox);
					} else if (filterbox.getSelectedIndex() == listTOshow
							.size() - 2) {
						getEditFilterDialog(filter);
					}
				}
			});

			Label clearFilter = new Label("Clear Filter");
			clearFilter.addStyleName("clearfilter");

			Label editFilter = new Label("Edit Filter");
			editFilter.addStyleName("Edit Filter");

			Label remove = new Label("Remov Filter");
			remove.addStyleName("removefilter");

			listTOshow.add(0, clearFilter);
			listTOshow.add(editFilter);
			listTOshow.add(remove);
			filterbox.setItems(listTOshow);
			this.add(filterbox);
		}
	}

	private void removeFilter(Widget v) {
		this.remove(v);
		DashboardMainPage parent2 = (DashboardMainPage) this.getParent();
		parent2.reRender(null);
	}

	private void getEditFilterDialog(DashboardFilters filter) {
		AddFilterDialog editFilter = new AddFilterDialog(filter, null);
		editFilter.show();
		editFilter.center();
	}

	private void clearFilter() {

	}

}
