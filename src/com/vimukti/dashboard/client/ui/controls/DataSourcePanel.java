package com.vimukti.dashboard.client.ui.controls;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.Folder;
import com.vimukti.dashboard.client.data.PagesList;
import com.vimukti.dashboard.client.data.ReportList;
import com.vimukti.dashboard.client.data.ReportsAndPagesList;

public class DataSourcePanel extends VerticalPanel {

	private Button recent;
	private Button my;
	private Button all;
	private ReportsAndPagesList source;

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
		all = createButton("All", hPanel);
		recent = createButton("Recent", hPanel);
		my = createButton("My", hPanel);
		ClickHandler clickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO
			}
		};
		recent.addClickHandler(clickHandler);
		my.addClickHandler(clickHandler);
		all.addClickHandler(clickHandler);
		all.addStyleName("active");
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

	public void showReportsPages() {
		FlowPanel dataPanel = new FlowPanel();

		if (source == null) {
			return;
		}

		List<Folder> folders = source.getFolders();
		Tree reportsTree = new Tree();
		for (Folder folder : folders) {
			TreeItem reportFolderItem = new TreeItem(
					new Label(folder.getName()));
			List<ReportList> reports = folder.getReports();
			for (ReportList report : reports) {
				DraggabelLableControl item = new DraggabelLableControl(report);
				TreeItem reportItem = new TreeItem(item);
				reportFolderItem.addItem(reportItem);
			}
			reportsTree.addItem(reportFolderItem);
		}

		List<PagesList> pages = source.getPages();
		Tree pagesTree = new Tree();
		for (PagesList page : pages) {
			DraggabelLableControl item = new DraggabelLableControl(page);
			TreeItem pageItem = new TreeItem(item);
			pagesTree.addItem(pageItem);
		}
		dataPanel.add(reportsTree);
		dataPanel.add(pagesTree);
		this.add(dataPanel);

	}

}
