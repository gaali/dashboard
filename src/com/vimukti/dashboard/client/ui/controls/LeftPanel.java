package com.vimukti.dashboard.client.ui.controls;

import java.util.logging.Logger;

import com.google.gwt.dev.util.HttpHeaders;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.vimukti.dashboard.client.data.ReportsAndPageListType;
import com.vimukti.dashboard.client.data.ReportsAndPagesList;
import com.vimukti.dashboard.client.ui.utils.TabControl;

public class LeftPanel extends FlowPanel {

	private ComponentsPanel componentsPanel;
	private DataSourcePanel dataPanel;
	private ReportsAndPagesList list;

	private static Logger logger = Logger.getLogger("DataSourcePanel");

	public static final String GET_REPORT_AND_PAGES_LIST = "/dashboard/getreportandpages";
	private static final String CONTENT_TYPE_JSON = "application/json";

	public LeftPanel() {
		this.addStyleName("left-panel");
		createControls();
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

					RequestBuilder requestBuilder = new RequestBuilder(
							RequestBuilder.POST, GET_REPORT_AND_PAGES_LIST);

					requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE,
							CONTENT_TYPE_JSON);
					requestBuilder.setRequestData("type="
							+ ReportsAndPageListType.ALL.toString());
					requestBuilder.setCallback(new RequestCallback() {

						@Override
						public void onResponseReceived(Request request,
								Response response) {

							ReportsAndPagesList reportsAndPagesList = new ReportsAndPagesList();

							String text = response.getText();

							JSONValue jsonValue = JSONParser.parseStrict(text);
							reportsAndPagesList.fromJSON(jsonValue.isObject());

							list = reportsAndPagesList;
							dataPanel = new DataSourcePanel(list);

							logger.info("successfully saved report");
						}

						@Override
						public void onError(Request request, Throwable exception) {
							logger.info("failed to save report");
						}

					});

					try {
						requestBuilder.send();
					} catch (RequestException e) {
						e.printStackTrace();
					}
				}
				sPanel.setWidget(dataPanel);
			}
		});
		this.add(tabs);
		this.add(sPanel);
	}

}
