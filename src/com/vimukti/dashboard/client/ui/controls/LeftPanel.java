package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.vimukti.dashboard.client.data.ReportsAndPagesList;
import com.vimukti.dashboard.client.ui.utils.TabControl;

public class LeftPanel extends FlowPanel {

	private ComponentsPanel componentsPanel;
	private DataSourcePanel dataPanel;
	private ReportsAndPagesList list;

	public LeftPanel(ReportsAndPagesList list) {
		createControls();
		init();
	}

	private void createControls() {

		dataPanel = new DataSourcePanel(list);

		final SimplePanel sPanel = new SimplePanel();
		TabControl tabs = new TabControl();
		tabs.addTab("Componts", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sPanel.setWidget(getComponetsPanel());
			}
		});
		tabs.addTab("Data Source", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sPanel.setWidget(dataPanel);
			}
		});
		this.add(tabs);
		this.add(sPanel);
	}

	private ComponentsPanel getComponetsPanel() {
		componentsPanel = new ComponentsPanel();
		return componentsPanel;
	}

	private void init() {
		// TODO Auto-generated method stub
	}

}
