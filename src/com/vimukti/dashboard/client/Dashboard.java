package com.vimukti.dashboard.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dev.util.HttpHeaders;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.thirdparty.guava.common.base.Joiner;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.ChartBackgroundDirection;
import com.vimukti.dashboard.client.data.CustomObject;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentSection;
import com.vimukti.dashboard.client.data.DashboardComponentSize;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.data.DashboardFilters;
import com.vimukti.dashboard.client.data.DashboardType;
import com.vimukti.dashboard.client.data.Field;
import com.vimukti.dashboard.client.data.FieldType;
import com.vimukti.dashboard.client.data.Folder;
import com.vimukti.dashboard.client.data.MetaObject;
import com.vimukti.dashboard.client.ui.controls.AddFilterDialog;
import com.vimukti.dashboard.client.ui.controls.DashboardCloseConformationDialog;
import com.vimukti.dashboard.client.ui.controls.DashboardMainPage;
import com.vimukti.dashboard.client.ui.controls.DashboardPropertiesDialog;
import com.vimukti.dashboard.client.ui.controls.DashboardRunningUserDialog;
import com.vimukti.dashboard.client.ui.controls.LeftPanel;
import com.vimukti.dashboard.client.ui.controls.dnd.DragAndDropController;
import com.vimukti.dashboard.client.ui.utils.ActionCallback;
import com.vimukti.dashboard.client.ui.utils.TextItem;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Dashboard implements EntryPoint {

	public static final int MAXIMUM_FILTERS_SIZE = 3;
	private DashboardData dashboard;
	private Button addFilter;
	private static FlowPanel mainPageContainer;
	public static DashboardData data;

	private static List<Folder> dashboardFolders;

	private static Logger logger = Logger.getLogger("DataSourcePanel");
	private static final String CONTENT_TYPE_JSON = "application/json";
	public static final String GET_DASHBOARD_DATA = "/dashboard/getdata";
	public static final String GET_DASHBOARD_FOLDERS = "/dashboard/folders";
	public static final String SAVE_DASHBOARD = "/dashboard/save";
	public static final String GET_CUSTOM_OBJECT_FIELDS = "/dashboard/customobject/fields";
	private static final String GET_DASHBOARD_RUNNING_USER = "/dashboard/getRunningUser";
	private static DragAndDropController dragAndDropController;

	private static List<CustomObject> customObjects;

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

		FlowPanel searchPanel = new FlowPanel();
		searchPanel.addStyleName("searchPanel");

		// user search box
		final TextItem box = new TextItem("View dashboard As");

		// to show user list while
		final PopupPanel runningUserPopup = new PopupPanel();
		runningUserPopup.addStyleName("userslist");
		box.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				getUsersByName(event.getValue(),
						new ActionCallback<List<User>>() {

							@Override
							public void onSuccess(List<User> result) {
								FlowPanel usersListPopUp = UsersListWithName(
										result, box);
								runningUserPopup.add(usersListPopUp);
							}

							@Override
							public void onCancel(List<User> result) {
								// TODO Auto-generated method stub
							}
						});
			}
		});
		searchPanel.add(box);
		runningUserPopup.showRelativeTo(box);

		final FlowPanel runningUserDialiog = new FlowPanel();
		runningUserDialiog.addStyleName("down-arrow");
		runningUserDialiog.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DashboardRunningUserDialog runningUserDialog = new DashboardRunningUserDialog();
				runningUserDialog.showRelativeTo(runningUserDialiog);
			}
		}, ClickEvent.getType());
		searchPanel.add(runningUserDialiog);
		hPanel.add(searchPanel);

		return hPanel;
	}

	/**
	 * 
	 * adding all user to flow panel,user name as anchor and adding click
	 * handler to names ,and refreshing the dash board
	 * 
	 * @param users
	 *            users list
	 * @param box
	 *            after selecting click on anchor .setting text to search box
	 * @return
	 */
	private FlowPanel UsersListWithName(List<User> users, final TextItem box) {
		FlowPanel namesPanel = new FlowPanel();
		for (User userName : users) {
			final Anchor anchor = new Anchor(userName.getName());
			anchor.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					box.setValue(anchor.getText());
					dashboard.setRunningUser(anchor.getText());
					reCreateMainPage();
				}
			});
			namesPanel.add(anchor);
		}
		return namesPanel;
	}

	/**
	 * Searching for users list given text
	 * 
	 * @param text
	 *            entered text in search box
	 * @param callBack
	 *            returns the users list
	 */
	private void getUsersByName(String text,
			final ActionCallback<List<User>> callBack) {
		String dashboardId = getDashboardId();
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST,
				GET_DASHBOARD_RUNNING_USER);
		requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);
		requestBuilder.setRequestData("text=" + text + "," + "dashboardId="
				+ dashboardId);
		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				// TODO Auto-generated method stub
				String text = response.getText();

				JSONValue jsonValue = JSONParser.parseStrict(text);

				List<User> usersList = new ArrayList<User>();

				if (jsonValue != null) {
					JSONArray array = jsonValue.isArray();
					for (int i = 0; i < array.size(); i++) {
						User user = new User();
						JSONValue jsonValue2 = array.get(i);
						user.fromJSON(jsonValue2.isObject());
						usersList.add(user);
					}
				}
				callBack.onSuccess(usersList);
				logger.info("Succesfully users List");
			}

			@Override
			public void onError(Request request, Throwable exception) {
				logger.info("failed to Showed Dashboard view for Running User");
			}
		});

	}

	@SuppressWarnings("serial")
	class User extends MetaObject {

	}

	private void addFilter() {
		final DashboardFilters filter = new DashboardFilters();
		AddFilterDialog filterDialog = new AddFilterDialog(filter) {
			@Override
			protected boolean onOK() {
				super.onOK();
				List<DashboardFilters> dashboardFilters = dashboard
						.getDashboardFilters();
				if (dashboardFilters == null) {
					dashboardFilters = new ArrayList<DashboardFilters>();
					dashboard.setDashboardFilters(dashboardFilters);
				}
				dashboardFilters.add(filter);
				if (dashboardFilters.size() == MAXIMUM_FILTERS_SIZE) {
					addFilter.setEnabled(false);
				}

				reCreateMainPage();
				return true;
			}
		};
		filterDialog.show();
		filterDialog.center();
	}

	private FlowPanel addMainPanel() {

		FlowPanel panel = new FlowPanel();
		panel.addStyleName("mainPanel");

		// left panel
		LeftPanel leftPanel = new LeftPanel();
		panel.add(leftPanel);

		// main page of dash board designer
		mainPageContainer = new FlowPanel();
		panel.add(mainPageContainer);
		reCreateMainPage();

		retrieveAllCustomObjects();

		return panel;
	}

	/**
	 * recreating dash board page on dash board filter change,remove or add.
	 */
	public static void reCreateMainPage() {

		String dashboardId = getDashboardId();

		if (dashboardId == null) {
			return;
		}

		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST,
				GET_DASHBOARD_DATA);

		requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

		requestBuilder.setRequestData("dashboardId=" + dashboardId);
		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {

				data = new DashboardData();

				String text = response.getText();

				JSONValue jsonValue = JSONParser.parseStrict(text);
				data.fromJSON(jsonValue.isObject());

				DashboardMainPage mainPage = new DashboardMainPage();
				mainPage.addStyleName("main-panel");

				mainPageContainer.clear();

				mainPageContainer.add(mainPage);

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
	 * retrieving all report-type base object fields
	 */
	public static void retrieveAllCustomObjects() {

		if (data == null) {
			return;
		}

		HashSet<String> reports = getAllReports();

		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST,
				GET_CUSTOM_OBJECT_FIELDS);

		requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

		Joiner on = Joiner.on(",");
		StringBuilder builder = new StringBuilder();
		on.appendTo(builder, reports);

		requestBuilder.setRequestData("reports=" + builder.toString());
		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {

				String text = response.getText();

				JSONValue jsonValue = JSONParser.parseStrict(text);

				customObjects = new ArrayList<CustomObject>();

				JSONArray array = jsonValue.isArray();
				for (int i = 0; i < array.size(); i++) {
					JSONValue jsonValue2 = array.get(i);

					CustomObject customObject = new CustomObject();
					customObject.fromJSON(jsonValue2.isObject());
					customObjects.add(customObject);
				}
				logger.info("retrieved all custom objects data");
			}

			@Override
			public void onError(Request request, Throwable exception) {
				logger.info("failed to get custom objects");
			}

		});

		try {
			requestBuilder.send();
		} catch (RequestException e) {
			e.printStackTrace();
		}

	}

	/**
	 * get all used reports in this dashboard
	 * 
	 * @return
	 */
	private static HashSet<String> getAllReports() {

		DashboardComponentSection leftSection = data.getLeftSection();

		List<String> reports1 = getAllReports(leftSection);

		DashboardComponentSection middleSection = data.getMiddleSection();

		List<String> reports2 = getAllReports(middleSection);

		DashboardComponentSection rightSection = data.getRightSection();
		List<String> reports3 = getAllReports(rightSection);

		HashSet<String> reports = new HashSet<String>();

		reports.addAll(reports1);
		reports.addAll(reports2);
		reports.addAll(reports3);

		return reports;
	}

	/**
	 * retrieving used reports in given component section
	 * 
	 * @param section
	 * @return
	 */
	private static List<String> getAllReports(DashboardComponentSection section) {
		ArrayList<String> list = new ArrayList<String>();
		if (section == null) {
			return list;
		}

		List<DashboardComponent> components = section.getComponents();

		for (DashboardComponent component : components) {
			String report = component.getReport();
			if (report != null) {
				list.add(report);
			}
		}

		return list;
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

	private void prepareNewDashborad() {
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

	/**
	 * returning customn object all fields
	 * 
	 * @return
	 */
	public static List<Field> getAllFields() {
		ArrayList<Field> arrayList = new ArrayList<Field>();
		for (CustomObject customObject : customObjects) {
			List<Field> prepareFields = prepareFieldsFromCustomObject(customObject);
			arrayList.addAll(prepareFields);
		}
		return arrayList;
	}

	/**
	 * preparing fields from all custom objects
	 * 
	 * @param object
	 * @return
	 */
	private static List<Field> prepareFieldsFromCustomObject(CustomObject object) {
		List<Field> fields2 = object.getFields();
		List<Field> filterdFields = new ArrayList<Field>();
		for (Field field : fields2) {
			FieldType fieldType = field.getFieldType();
			if (fieldType == FieldType.REFRENCE) {
				List<Field> fromCustomObject = prepareFieldsFromCustomObject(field
						.getReferenceType());
				filterdFields.addAll(fromCustomObject);
				continue;
			}
			filterdFields.add(field);
		}
		return filterdFields;
	}

}
