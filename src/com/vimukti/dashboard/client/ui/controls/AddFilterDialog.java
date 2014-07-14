package com.vimukti.dashboard.client.ui.controls;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.vimukti.dashboard.client.data.Dashboard;
import com.vimukti.dashboard.client.data.DashboardFilterOptions;
import com.vimukti.dashboard.client.data.DashboardFilters;
import com.vimukti.dashboard.client.data.DashboardType;
import com.vimukti.dashboard.client.data.Field;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class AddFilterDialog extends BaseDialog {

	private SelectListBox<Field> fields;
	private TextItem displayName;
	private Button addRow;
	private DashboardType dashboardType;
	private List<DashboardFilterOptions> filterOptionsList;
	private Dashboard dashboard;
	private DashboardFilters options;

	// use this dialog to as edit Filter dialog
	public AddFilterDialog(Dashboard dashboard) {
		this.dashboard = dashboard;
		this.addStyleName("dashboard-filter-dialog");
		createControls();
	}

	@Override
	protected void createControls() {
		super.createControls();

	}

	@Override
	protected boolean onOK() {
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void update() {

	}

}
