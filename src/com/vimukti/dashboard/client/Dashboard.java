package com.vimukti.dashboard.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dev.util.HttpHeaders;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.ChartBackgroundDirection;
import com.vimukti.dashboard.client.data.DashboardComponentSection;
import com.vimukti.dashboard.client.data.DashboardComponentSize;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.data.DashboardFilters;
import com.vimukti.dashboard.client.data.DashboardType;
import com.vimukti.dashboard.client.data.Field;
import com.vimukti.dashboard.client.data.Folder;
import com.vimukti.dashboard.client.ui.controls.AddFilterDialog;
import com.vimukti.dashboard.client.ui.controls.DashboardCloseConformationDialog;
import com.vimukti.dashboard.client.ui.controls.DashboardMainPage;
import com.vimukti.dashboard.client.ui.controls.DashboardPropertiesDialog;
import com.vimukti.dashboard.client.ui.controls.DashboardRunningUserDialog;
import com.vimukti.dashboard.client.ui.controls.LeftPanel;
import com.vimukti.dashboard.client.ui.controls.dnd.DragAndDropController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Dashboard implements EntryPoint {

	public static final int MAXIMUM_FILTERS_SIZE = 3;
	private DashboardData dashboard;
	private Button addFilter;
	private List<Field> fields;
	private DashboardMainPage mainPage;
	public static DashboardData data;

	private static List<Folder> dashboardFolders;

	private static Logger logger = Logger.getLogger("DataSourcePanel");
	private static final String CONTENT_TYPE_JSON = "application/json";
	public static final String GET_DASHBOARD_DATA = "/dashboard/getdata";
	public static final String GET_DASHBOARD_FOLDERS = "/dashboard/folders";
	public static final String SAVE_DASHBOARD = "dashboard/save";
	private static DragAndDropController dragAndDropController;

	public void onModuleLoad() {
		retrieveDashboardFolders();
		loadDashboardDesigner();
	}

	private void loadDashboardDesigner() {
		RootPanel rootPanel = RootPanel.get();
		rootPanel.clear();
		VerticalPanel vPanel = new VerticalPanel();
		FlowPanel createDashbordBar = createDashbordBar();
		FlowPanel addMainPanel = addMainPanel();
		vPanel.add(createDashbordBar);
		vPanel.add(addMainPanel);

		AbsolutePanel panel = new AbsolutePanel();
		addDragAndDropController(panel);
		panel.add(vPanel);
		rootPanel.add(panel);
	}

	private void addDragAndDropController(AbsolutePanel panel) {
		dragAndDropController = new DragAndDropController(panel);
	}

	public static DragAndDropController getDragAndDropController() {
		return dragAndDropController;
	}

	public FlowPanel createDashbordBar() {
		FlowPanel hPanel = new FlowPanel();

		Button save = new Button("save");
		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				saveDashboard();
			}
		});
		save.addStyleName("save-btn");
		hPanel.add(save);

		Button saveNclose = new Button("Save And Close");
		saveNclose.addStyleName("saveclose-btn");
		hPanel.add(saveNclose);

		Button close = new Button("Close");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DashboardCloseConformationDialog conformDialog = new DashboardCloseConformationDialog() {
					@Override
					protected boolean onOK() {
						saveDashboard();
						return true;
					}

					@Override
					protected void onClose() {
						// redirect the page without save the object
					}
				};
				conformDialog.show();
				conformDialog.center();

			}
		});
		close.addStyleName("Close");
		hPanel.add(close);

		Button dashProperties = new Button("Dashboard Properties");
		dashProperties.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DashboardPropertiesDialog dialog = new DashboardPropertiesDialog(
						dashboard);
				dialog.show();
				dialog.center();
			}
		});
		dashProperties.addStyleName("Dashboard Properties");
		hPanel.add(dashProperties);

		addFilter = new Button("Add Fillter");
		addFilter.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addFilter();
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

		final Button runningUserDialiog = new Button("s");
		runningUserDialiog.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DashboardRunningUserDialog runningUserDialog = new DashboardRunningUserDialog();
				runningUserDialog.showRelativeTo(runningUserDialiog);
			}
		});
		searchPanel.add(runningUserDialiog);
		hPanel.add(searchPanel);

		return hPanel;
	}

	private void addFilter() {
		final DashboardFilters filter = new DashboardFilters();
		AddFilterDialog filterDialog = new AddFilterDialog(filter, fields) {
			@Override
			protected boolean onOK() {
				super.onOK();
				if (dashboard.getDashboardFilters() == null) {
					List<DashboardFilters> filtersList = new ArrayList<DashboardFilters>();
					filtersList.add(filter);
					if (dashboard.getDashboardFilters().size() == MAXIMUM_FILTERS_SIZE) {
						addFilter.setEnabled(false);
					}
					mainPage.reRender(dashboard);
				}
				return true;
			}
		};
		filterDialog.show();
		filterDialog.center();
	}

	private FlowPanel addMainPanel() {

		FlowPanel panel = new FlowPanel();
		panel.addStyleName("mainPanel");
		getMainPage(panel);
		LeftPanel leftPanel = new LeftPanel();
		panel.add(leftPanel);
		return panel;
	}

	private void getMainPage(final FlowPanel panel) {

		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST,
				GET_DASHBOARD_DATA);

		requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

		String dashboardId = getDashboardId();

		requestBuilder.setRequestData("dashboardId=" + dashboardId);
		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {

				data = new DashboardData();

				String text = response.getText();

				JSONValue jsonValue = JSONParser.parseStrict(text);
				data.fromJSON(jsonValue.isObject());

				mainPage = new DashboardMainPage(data);
				mainPage.addStyleName("main-panel");
				panel.add(mainPage);

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

	private static native String getDashboardId() /*-{
		return $wnd.dashboardId;
	}-*/;

	private void saveDashboard() {

		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST,
				SAVE_DASHBOARD);

		requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

		JSONObject object = dashboard.toJSON();

		requestBuilder.setRequestData("dashboard=" + object.toString());

		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
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

	private void prepateNewDashborad() {
		dashboard = new DashboardData();
		dashboard.setBackgroundFadeDirection(ChartBackgroundDirection.DIAGONAL);
		dashboard.setBackgroundEndColor("FFFFFF");
		dashboard.setBackgroundStartColor("FFFFFF");
		dashboard.setDashboardFilters(null);
		dashboard.setDashboardType(DashboardType.SPECIFIED_USER);
		dashboard.setDescription("");
		dashboard.setFullName("");
		dashboard.setLeftSection(getDashboardComponentSection());
		dashboard.setMiddleSection(getDashboardComponentSection());
		dashboard.setRightSection(getDashboardComponentSection());
		dashboard.setRunningUser("");
		dashboard.setTextColor("000000");
		dashboard.setTitle("");
		dashboard.setTitleColor("000000");
		dashboard.setTitleSize(8);
	}

	private static void retrieveDashboardFolders() {
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST,
				GET_DASHBOARD_FOLDERS);

		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {

				List<Folder> folders = new ArrayList<Folder>();

				String text = response.getText();

				JSONValue jsonValue = JSONParser.parseStrict(text);

				JSONArray jsonArray = jsonValue.isArray();

				for (int i = 0; i < jsonArray.size(); i++) {
					JSONValue value = jsonArray.get(i);

					Folder folder = new Folder();
					folder.fromJSON(value.isObject());

					folders.add(folder);
				}

				setDashboardFolders(folders);

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

	/**
	 * @return the dashboardFolders
	 */
	public static List<Folder> getDashboardFolders() {
		return dashboardFolders;
	}

	/**
	 * @param dashboardFolders
	 *            the dashboardFolders to set
	 */
	public static void setDashboardFolders(List<Folder> dashboardFolders) {
		Dashboard.dashboardFolders = dashboardFolders;
	}

	private DashboardComponentSection getDashboardComponentSection() {
		DashboardComponentSection section = new DashboardComponentSection();
		section.setColumnSize(DashboardComponentSize.MEDIUM);
		return section;
	}

	public static Folder getFolderBy(String folderId) {
		for (Folder folder : dashboardFolders) {
			if (folder.getId().equals(folderId)) {
				return folder;
			}
		}
		return null;
	}

	public static DashboardData getDashboardData() {
		return data;
	}

}
