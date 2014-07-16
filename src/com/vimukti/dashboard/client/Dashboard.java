package com.vimukti.dashboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.IDashboardService;
import com.vimukti.dashboard.client.data.IDashboardServiceAsync;
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
		HorizontalPanel hpanel = new HorizontalPanel();
		HorizontalPanel h2Panel = new HorizontalPanel();
		rootPanel.add(hpanel);

		Button dashboardPropertiesDialog = new Button(
				"dashboardPropertiesDialog");
		dashboardPropertiesDialog.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DashboardPropertiesDialog dialog = new DashboardPropertiesDialog(
						null);
				dialog.show();
				dialog.center();
			}
		});
		hpanel.add(dashboardPropertiesDialog);
		LeftPanel addDashboardLeftPanel = addDashboardLeftPanel();
		h2Panel.add(addDashboardLeftPanel);
		DashboardMainPage mainPage = addDasboardMainPage();
		h2Panel.add(mainPage);
		vPanel.add(hpanel);
		vPanel.add(h2Panel);
		rootPanel.add(vPanel);
	}

	private DashboardMainPage addDasboardMainPage() {
		DashboardMainPage mainPage = new DashboardMainPage(
				new com.vimukti.dashboard.client.data.Dashboard());
		return mainPage;
	}

	private LeftPanel addDashboardLeftPanel() {
		LeftPanel leftPanel = new LeftPanel(null);
		return leftPanel;
	}
}
