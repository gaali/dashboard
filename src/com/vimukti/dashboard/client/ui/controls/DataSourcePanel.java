package com.vimukti.dashboard.client.ui.controls;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.data.Folder;
import com.vimukti.dashboard.client.data.IDashboardServiceAsync;
import com.vimukti.dashboard.client.data.PagesList;
import com.vimukti.dashboard.client.data.ReportList;
import com.vimukti.dashboard.client.data.ReportsAndPageListType;
import com.vimukti.dashboard.client.data.ReportsAndPagesList;

public class DataSourcePanel extends VerticalPanel {

	private Button recentB;
	private Button myB;
	private Button allB;
	private ReportsAndPagesList source;
	private ReportsAndPagesList recent;
	private ReportsAndPagesList my;
	private IDashboardServiceAsync dashboardServiceObject = Dashboard
			.getDashboardServiceObject();

	public DataSourcePanel(ReportsAndPagesList source) {
		this.source = source;
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
				showReportsPages(source);
			}
		});

		recentB = createButton("Recent", hPanel);
		recentB.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dashboardServiceObject.getReportsAndPagesList(
						ReportsAndPageListType.RECENT,
						new AsyncCallback<ReportsAndPagesList>() {

							@Override
							public void onSuccess(ReportsAndPagesList result) {
								recent = result;
							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}
						});
				showReportsPages(recent);
			}
		});
		myB = createButton("My", hPanel);
		myB.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dashboardServiceObject.getReportsAndPagesList(
						ReportsAndPageListType.MY,
						new AsyncCallback<ReportsAndPagesList>() {

							@Override
							public void onSuccess(ReportsAndPagesList result) {
								my = result;
							}

							@Override
							public void onFailure(Throwable caught) {

							}

						});
				showReportsPages(my);
			}
		});

		allB.addStyleName("active");
		this.add(hPanel);
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
		// TODO Auto-generated method stub

	}

	public void showReportsPages(ReportsAndPagesList list) {
		FlowPanel dataPanel = new FlowPanel();
		dataPanel.clear();
		if (list == null) {
			return;
		}
		List<Folder> folders = list.getFolders();
		Tree reportsTree = new Tree();
		for (Folder folder : folders) {
			TreeItem reportFolderItem = new TreeItem(
					new Label(folder.getName()));
			List<ReportList> reports = folder.getReports();
			for (ReportList report : reports) {
				DraggabelLableControl item = new DraggabelLableControl(report);
				item.setType(DataSourceListType.REPORT);
				TreeItem reportItem = new TreeItem(item);
				reportFolderItem.addItem(reportItem);
			}
			reportsTree.addItem(reportFolderItem);
		}
		List<PagesList> pages = list.getPages();
		Tree pagesTree = new Tree();
		for (PagesList page : pages) {
			DraggabelLableControl item = new DraggabelLableControl(page);
			item.setType(DataSourceListType.PAGE);
			TreeItem pageItem = new TreeItem(item);
			pagesTree.addItem(pageItem);
		}
		dataPanel.add(reportsTree);
		dataPanel.add(pagesTree);
		this.add(dataPanel);
	}

}
