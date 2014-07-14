package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class LeftPanel extends FlowPanel {

	private ComponentsPanel componentsPanel;
	private DataSourcePanel dataPanel;

	public LeftPanel() {
		createControls();
		init();
	}

	private void createControls() {
		TabLayoutPanel tabLayout = new TabLayoutPanel(1.5, Unit.EM);
		tabLayout.add(getComponetsPanel(), "Components");
		tabLayout.add(getDataPanel(), "Data Source");
		this.add(tabLayout);
		// TODO
	}

	private DataSourcePanel getDataPanel() {
		// call RPC to get and reportsandpages object and creat dataSourcePanel
		// object passing rpc given object
		// dataPanel = new DataSourcePanel(Rpc given objct);
		return dataPanel;
	}

	private ComponentsPanel getComponetsPanel() {
		componentsPanel = new ComponentsPanel();
		return componentsPanel;
	}

	private void init() {
		// TODO Auto-generated method stub
	}

}
