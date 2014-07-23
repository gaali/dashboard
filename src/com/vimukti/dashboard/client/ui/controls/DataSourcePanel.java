package com.vimukti.dashboard.client.ui.controls;

import java.util.ArrayList;
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
import com.vimukti.dashboard.client.data.ReportDetails;
import com.vimukti.dashboard.client.data.ReportsAndPageListType;
import com.vimukti.dashboard.client.data.ReportsAndPagesList;

public class DataSourcePanel extends VerticalPanel {

	private Button recentB;
	private Button myB;
	private Button allB;
	private ReportsAndPagesList all;
	private ReportsAndPagesList recent;
	private ReportsAndPagesList my;
	private ReportsAndPagesList source;
	private FlowPanel dataPanel;
	private IDashboardServiceAsync dashboardServiceObject = Dashboard
			.getDashboardServiceObject();

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
				dashboardServiceObject.getReportsAndPagesList(
						ReportsAndPageListType.RECENT,
						new AsyncCallback<ReportsAndPagesList>() {

							@Override
							public void onSuccess(ReportsAndPagesList result) {
								recent = result;
								showReportsPages(recent);
							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}
						});
			}
		});
		myB = createButton("My", hPanel);
		myB.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (my != null) {
					showReportsPages(my);
				}
				dashboardServiceObject.getReportsAndPagesList(
						ReportsAndPageListType.MY,
						new AsyncCallback<ReportsAndPagesList>() {

							@Override
							public void onSuccess(ReportsAndPagesList result) {
								my = result;
								showReportsPages(my);
							}

							@Override
							public void onFailure(Throwable caught) {

							}

						});

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
		ReportsAndPagesList searchResult = new ReportsAndPagesList();

		List<Folder> sFolders = new ArrayList<Folder>();

		List<Folder> folders = source.getFolders();

		for (Folder folder : folders) {
			Folder sFolder = new Folder();
			String name = folder.getName();
			String id = folder.getId();
			sFolder.setName(name);
			sFolder.setId(id);
			sFolders.add(sFolder);

			List<ReportDetails> sReports = new ArrayList<ReportDetails>();
			List<ReportDetails> reports = folder.getReports();
			for (ReportDetails reportDetails : reports) {
				boolean contains = reportDetails.getName().contains(value);
				if (contains) {
					sReports.add(reportDetails);
				}
			}
			sFolder.setReports(sReports);
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
		List<Folder> folders = list.getFolders();
		Tree reportsTree = new Tree();
		TreeItem rootTree = new TreeItem();
		rootTree.setText("Rports");
		reportsTree.addItem(rootTree);
		for (Folder folder : folders) {
			TreeItem reportFolderItem = new TreeItem(
					new Label(folder.getName()));
			List<ReportDetails> reports = folder.getReports();
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
