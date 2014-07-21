package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.data.IDashboardServiceAsync;
import com.vimukti.dashboard.client.data.ReportsAndPageListType;
import com.vimukti.dashboard.client.data.ReportsAndPagesList;
import com.vimukti.dashboard.client.ui.utils.TabControl;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class LeftPanel extends FlowPanel {

	private ComponentsPanel componentsPanel;
	private DataSourcePanel dataPanel;
	private ReportsAndPagesList list;
	private IDashboardServiceAsync dashboardServiceObject = Dashboard
			.getDashboardServiceObject();

	public LeftPanel(ReportsAndPagesList list) {
		createControls();
		init();
	}

	private void createControls() {

		componentsPanel = new ComponentsPanel();

		final SimplePanel sPanel = new SimplePanel();
		sPanel.addStyleName("simpanel");
		sPanel.add(componentsPanel);
		TabControl tabs = new TabControl();
		tabs.addTab("Componts", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sPanel.setWidget(componentsPanel);
			}
		});
		tabs.addTab("Data Source", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (list == null) {
					dashboardServiceObject.getReportsAndPagesList(
							ReportsAndPageListType.ALL,
							new AsyncCallback<ReportsAndPagesList>() {

								@Override
								public void onSuccess(ReportsAndPagesList result) {
									list = result;
									dataPanel = new DataSourcePanel(list);
								}

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
								}
							});
				}
				sPanel.setWidget(dataPanel);
			}
		});
		this.add(tabs);
		this.add(sPanel);
	}

	private void init() {
		// TODO Auto-generated method stub
	}

}
