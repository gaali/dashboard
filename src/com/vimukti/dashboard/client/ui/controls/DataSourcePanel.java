package com.vimukti.dashboard.client.ui.controls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.dev.util.HttpHeaders;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.data.Folder;
import com.vimukti.dashboard.client.data.PagesList;
import com.vimukti.dashboard.client.data.ReportDetails;
import com.vimukti.dashboard.client.data.ReportsAndPageListType;
import com.vimukti.dashboard.client.data.ReportsAndPagesList;

public class DataSourcePanel extends AbsolutePanel {

	private Button recentB;
	private Button myB;
	private Button allB;
	private ReportsAndPagesList all;
	private ReportsAndPagesList recent;
	private ReportsAndPagesList my;
	private ReportsAndPagesList source;
	private FlowPanel dataPanel;

	private static Logger logger = Logger.getLogger("DataSourcePanel");

	public static final String GET_REPORT_AND_PAGES_LIST = "/dashboard/getreportandpages";
	private static final String CONTENT_TYPE_JSON = "application/json";

	public DataSourcePanel(ReportsAndPagesList all) {
		this.all = all;
		dataPanel = new FlowPanel();

		createControls();
	}

	private void createControls() {
		createButtonsBar();
		createSearchBox();
	}

	private void createButtonsBar() {
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleName("dashboard-datasourcetab-btn-group");
		allB = createButton("All", hPanel);
		allB.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showReportsPages(all);
			}
		});

		recentB = createButton("Recent", hPanel);
		recentB.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (recent != null) {
					showReportsPages(recent);
					return;
				}

				requestToGetReportsAndPagesList(ReportsAndPageListType.RECENT);
			}
		});
		myB = createButton("My", hPanel);
		myB.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (my != null) {
					showReportsPages(my);
				}

				requestToGetReportsAndPagesList(ReportsAndPageListType.MY);
			}
		});

		allB.addStyleName("active");
		this.add(hPanel);
	}

	protected void requestToGetReportsAndPagesList(
			final ReportsAndPageListType type) {

		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST,
				GET_REPORT_AND_PAGES_LIST);

		requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);
		requestBuilder.setRequestData("type=" + type.toString());
		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {

				ReportsAndPagesList list = new ReportsAndPagesList();

				String text = response.getText();

				JSONValue jsonValue = JSONParser.parseStrict(text);
				list.fromJSON(jsonValue.isObject());

				switch (type) {
				case ALL:

					break;
				case MY:
					my = list;
					showReportsPages(my);
					break;
				case RECENT:
					recent = list;
					showReportsPages(recent);
					break;
				}

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

	public Button createButton(String buttonName, HorizontalPanel panel) {
		Button button = new Button(buttonName);
		button.addStyleName("btn-group");
		panel.add(button);
		return button;
	}

	private void createSearchBox() {
		FlowPanel searchPanel = new FlowPanel();
		final TextBox searchBox = new TextBox();
		searchBox.getElement().setId("Search");
		searchBox.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				String value = searchBox.getValue();
				getSearchResult(value);
			}
		});
		searchPanel.add(searchBox);
		this.add(searchPanel);
	}

	protected void getSearchResult(String value) {
		ReportsAndPagesList searchResult = new ReportsAndPagesList();

		Map<String, List<ReportDetails>> sFolders = new HashMap<String, List<ReportDetails>>();

		Map<String, List<ReportDetails>> folders = source.getFolders();

		for (String folderId : folders.keySet()) {
			List<ReportDetails> sReports = new ArrayList<ReportDetails>();

			for (ReportDetails reportDetails : folders.get(folderId)) {
				boolean contains = reportDetails.getName().contains(value);
				if (contains) {
					sReports.add(reportDetails);
				}
			}
			sFolders.put(folderId, sReports);
		}
		searchResult.setFolders(sFolders);

		List<PagesList> pages = source.getPages();
		List<PagesList> sPages = new ArrayList<PagesList>();
		for (PagesList pagesList : pages) {
			boolean contains = pagesList.getName().contains(value);
			if (contains) {
				sPages.add(pagesList);
			}
		}
		searchResult.setPages(sPages);

		showReportsPages(searchResult, true);
	}

	public void showReportsPages(ReportsAndPagesList list) {
		showReportsPages(list, false);
	}

	public void showReportsPages(ReportsAndPagesList list, boolean isForSearch) {
		if (!isForSearch) {
			source = list;
		}
		dataPanel.clear();
		dataPanel.addStyleName("data-panel");
		if (list == null) {
			return;
		}
		Map<String, List<ReportDetails>> folders = list.getFolders();
		Tree reportsTree = new Tree();
		TreeItem rootTree = new TreeItem();
		rootTree.setText("Rports");
		reportsTree.addItem(rootTree);
		for (String folderId : folders.keySet()) {

			Folder folder = Dashboard.getFolderBy(folderId);

			TreeItem reportFolderItem = new TreeItem(
					new Label(folder.getName()));
			List<ReportDetails> reports = folders.get(folderId);
			for (ReportDetails report : reports) {
				DraggabelLableControl item = new DraggabelLableControl(report);
				item.setType(DataSourceListType.REPORT);
				TreeItem reportItem = new TreeItem(item);
				reportFolderItem.addItem(reportItem);
			}
			rootTree.addItem(reportFolderItem);
		}
		List<PagesList> pages = list.getPages();
		Tree pagesTree = new Tree();
		TreeItem pageRootPage = new TreeItem();
		pagesTree.addItem(pageRootPage);
		for (PagesList page : pages) {
			DraggabelLableControl item = new DraggabelLableControl(page);
			item.setType(DataSourceListType.PAGE);
			TreeItem pageItem = new TreeItem(item);
			pageRootPage.addItem(pageItem);
		}
		dataPanel.add(reportsTree);
		dataPanel.add(pagesTree);
		this.add(dataPanel);
	}

}
