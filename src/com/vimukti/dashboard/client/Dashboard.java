package com.vimukti.dashboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.data.IDashboardService;
import com.vimukti.dashboard.client.data.IDashboardServiceAsync;
import com.vimukti.dashboard.client.ui.controls.AddFilterDialog;
import com.vimukti.dashboard.client.ui.controls.DashboardMainPage;
import com.vimukti.dashboard.client.ui.controls.DashboardPropertiesDialog;
import com.vimukti.dashboard.client.ui.controls.LeftPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Dashboard implements EntryPoint {
	private static final IDashboardServiceAsync greetingService = GWT
			.create(IDashboardService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Button sendButton = new Button("Send");
		sendButton.addStyleName("sendButton");
		RootPanel.get("sendButtonContainer").add(sendButton);
		sendButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createDashboardForTest();
			}
		});
	}

	public static IDashboardServiceAsync getDashboardServiceObject() {
		return greetingService;
	}

	private void createDashboardForTest() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel createDashbordBar = createDashbordBar();
		HorizontalPanel addMainPanel = addMainPanel();
		vPanel.add(createDashbordBar);
		vPanel.add(addMainPanel);
		rootPanel.add(vPanel);
	}

	public HorizontalPanel createDashbordBar() {
		HorizontalPanel hPanel = new HorizontalPanel();

		Button save = new Button("save");
		save.addStyleName("save-btn");
		hPanel.add(save);

		Button saveNclose = new Button("Save And Close");
		saveNclose.addStyleName("saveclose-btn");
		hPanel.add(saveNclose);

		Button close = new Button("Close");
		close.addStyleName("Close");
		hPanel.add(close);

		Button dashProperties = new Button("Dashboard Properties");
		dashProperties.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DashboardPropertiesDialog dialog = new DashboardPropertiesDialog(
						null);
				dialog.show();
				dialog.center();
			}
		});
		dashProperties.addStyleName("Dashboard Properties");
		hPanel.add(dashProperties);

		Button addFilter = new Button("Add Fillter");
		addFilter.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AddFilterDialog filterDialog = new AddFilterDialog(null, true);
				filterDialog.show();
				filterDialog.center();
			}
		});
		addFilter.addStyleName("addfilter");
		hPanel.add(addFilter);

		HorizontalPanel searchPanel = new HorizontalPanel();
		searchPanel.addStyleName("searchPanel");

		Label viewDashboardAs = new Label("View Dashboard As");
		searchPanel.add(viewDashboardAs);

		TextBox box = new TextBox();
		searchPanel.add(box);

		Button but = new Button("s");
		but.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});
		searchPanel.add(but);
		hPanel.add(searchPanel);

		return hPanel;
	}

	private HorizontalPanel addMainPanel() {

		HorizontalPanel panel = new HorizontalPanel();
		panel.addStyleName("mainPanel");
		getMainPage(panel);
		LeftPanel leftPanel = new LeftPanel();
		panel.add(leftPanel);
		return panel;
	}

	private void getMainPage(final HorizontalPanel panel) {
		greetingService.getDashboard(new AsyncCallback<DashboardData>() {

			@Override
			public void onSuccess(DashboardData result) {
				DashboardMainPage mainPage = new DashboardMainPage(result);
				mainPage.addStyleName("main-panel");
				panel.add(mainPage);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

		});
	}
}
