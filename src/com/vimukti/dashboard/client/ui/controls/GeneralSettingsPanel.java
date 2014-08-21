package com.vimukti.dashboard.client.ui.controls;

import java.util.List;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.data.Folder;
import com.vimukti.dashboard.client.ui.utils.LabelItem;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class GeneralSettingsPanel extends FlowPanel {

	private TextItem title;
	private TextItem dashBoardUniqName;
	private LabelItem nameSpacePrefix;
	private SelectListBox<Folder> saveTo;
	private DashboardData settings;

	public GeneralSettingsPanel(DashboardData settings) {
		this.settings = settings;
		this.addStyleName("dashboard-general-settings-panel");
		createControls();
		init();
	}

	public void createControls() {
		title = new TextItem("Title", true);
		title.addStyleName("title");

		prepareDafaultUniqueDashboardName();

		HorizontalPanel hPanel = new HorizontalPanel();
		dashBoardUniqName = new TextItem("Dashboard Unique Name", true);
		dashBoardUniqName.addStyleName("dashboard-unique-name");
		Image helpIcon = new Image();
		helpIcon.addStyleName("help-icon");
		hPanel.add(dashBoardUniqName);
		hPanel.add(helpIcon);
		helpIcon.addStyleName("help-icon");

		nameSpacePrefix = new LabelItem("Name Space Prefix");
		// need To provide NameSapace of this user onLoad current loged in user
		// Fix me
		nameSpacePrefix.setText("");
		nameSpacePrefix.addStyleName("namespace-prefix");

		Label folder = new Label("Folder");
		folder.addStyleName("folder-label");
		Label folderDescription = new Label(
				"To avoid exposing sensitive data to the wrong people, choose a folder visible only to the right users.");
		folderDescription.addStyleName("folder-description");

		// need To getCollection set to saveTo selectList
		saveTo = new SelectListBox<Folder>("SaveTo");
		saveTo.addStyleName("saveto-folder selectlist-box");

		this.add(title);
		this.add(hPanel);
		this.add(nameSpacePrefix);
		this.add(folder);
		this.add(folderDescription);
		this.add(saveTo);
	}

	public void prepareDafaultUniqueDashboardName() {
		title.getMainWidget().addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				if (settings == null) {
					dashBoardUniqName.getMainWidget().setReadOnly(true);
				}
				String replaceSpaceWith_ = title.getValue().replace(' ', '_');
				dashBoardUniqName.setValue(replaceSpaceWith_);
			}
		});
	}

	private void init() {
		if (settings == null) {
			return;
		}
		String title2 = settings.getTitle();
		if (title2 != null) {
			title.setTitle(title2);
		}
		String dashboardUniqueName = settings.getFullName();
		if (dashboardUniqueName != null) {
			dashBoardUniqName.setText(dashboardUniqueName);
		}
		String nameSpacePrifix = settings.getRunningUser();
		if (nameSpacePrifix != null) {
			nameSpacePrefix.setText(nameSpacePrifix);
		}
		// saveTo.setItems(settings.getDashBoardFolders());
		List<Folder> saveToFolders = Dashboard.getDashboardFolders();

		if (saveToFolders != null) {
			saveTo.setItems(saveToFolders);
			saveTo.setSelectedValue(settings.getSaveToFolder());
		}
	}

	public void update(DashboardData settings) {
		settings.setTitle(title.getText());
		settings.setFullName(dashBoardUniqName.getText());
		settings.setSaveToFolder(saveTo.getSelectedValue());
	}

}
