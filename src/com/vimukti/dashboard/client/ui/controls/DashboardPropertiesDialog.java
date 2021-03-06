package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.TabControl;

@SuppressWarnings("rawtypes")
public class DashboardPropertiesDialog extends BaseDialog {

	private DashboardData dashBoard;
	private GeneralSettingsPanel general;
	private ComponentSettingsPanel componentSettings;
	protected Object sPanel;

	public DashboardPropertiesDialog(DashboardData dashBoard) {
		super("Dashboard Properties");
		this.addStyleName("dashboard-properties-dialog");
		this.dashBoard = dashBoard;
	}

	@Override
	protected void createControls() {
		super.createControls();

		general = new GeneralSettingsPanel(dashBoard);
		componentSettings = new ComponentSettingsPanel(dashBoard);

		final SimplePanel sPanel = new SimplePanel();
		sPanel.add(general);

		TabControl tabs = new TabControl();
		tabs.addTab("General", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sPanel.setWidget(general);
			}
		});
		tabs.addTab("Component Settings", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sPanel.setWidget(componentSettings);
			}
		});
		this.add(tabs);
		this.add(sPanel);
	}

	@Override
	protected boolean onOK() {
		update();
		return true;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	public void update() {
		general.update(dashBoard);
		componentSettings.update(dashBoard);
	}

}