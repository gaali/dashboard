package com.vimukti.dashboard.client.portlet.ui.controls;

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
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.TextBox;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.chart.ui.controls.ComponentEditorDialog;
import com.vimukti.dashboard.client.column.PortletContainerPanel;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.reportdata.Report;
import com.vimukti.dashboard.client.ui.controls.ChartComponent;
import com.vimukti.dashboard.client.ui.controls.DataSourceListType;
import com.vimukti.dashboard.client.ui.controls.DraggabelLableControl;
import com.vimukti.dashboard.client.ui.controls.dnd.IDraggable;
import com.vimukti.dashboard.client.ui.controls.dnd.IDroppable;

public class Portlet extends AbsolutePanel implements RequiresResize,
		IDraggable {
	private DashboardComponent component;
	private DashboardComponentType type;
	private String objectId;
	private Report results;

	private static Logger logger = Logger.getLogger("DataSourcePanel");
	public static final String GET_DASHBOARD_PAGE = "/dashboard/apexpage";
	public static final String GET_REPORT_DATA_FOR_DASHBOARD = "/dashboard/reportdata";
	private static final String CONTENT_TYPE_JSON = "application/json";
	public static final String GET_CHART_HTML = "/dashboard/chartpreview";

	public Portlet(DashboardComponent component) {
		this.setComponent(component);
		type = component.getComponentType();
		createControls();
	}

	private void createControls() {
		createHeaderBarForPortlet(getComponentTitle(type));
		createPortletBody();
	}

	private void createPortletBody() {
		// body panel
		ChartContainer container = new ChartContainer();
		container.createBody();
		this.add(container);
	}

	private void createHeaderBarForPortlet(String titleString) {
		HorizontalPanel headerBar = new HorizontalPanel();
		headerBar.addStyleName("portlet-headerBar");
		Label title = new Label(titleString);
		title.addStyleName("portler-title");
		headerBar.add(title);
		if (type != DashboardComponentType.REPORT) {
			Image settings = new Image();
			settings.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (type == DashboardComponentType.PAGE) {
						ComponentEditorDialogForPage editorPage = new ComponentEditorDialogForPage(
								getComponent());
						editorPage.show();
						editorPage.center();
					} else {
						ComponentEditorDialog componentEditor = new ComponentEditorDialog(
								getComponent().clone(), results) {
							@Override
							protected boolean onOK() {
								setComponent(componentData);
								return true;
							}

							@Override
							public void setFocus() {
								// TODO Auto-generated method stub
							}
						};
						componentEditor.show();
						componentEditor.center();
					}
				}
			});
			settings.addStyleName("portlet-settings-icon");
			headerBar.add(settings);
		}
		Image removeIcon = new Image();
		removeIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PortletContainerPanel parent = (PortletContainerPanel) Portlet.this
						.getParent();
				parent.removePortlet(Portlet.this);
			}
		});
		removeIcon.addStyleName("portlet-remove-icon");
		headerBar.add(removeIcon);
		this.add(headerBar);
	}

	private String getComponentTitle(DashboardComponentType type) {
		switch (type) {
		case BAR:
		case BAR_GROUPED:
		case BAR_STACKED:
		case BAR_STACKED100:
			return "Horizontal Bar Chart";
		case COLUMN:
		case COLUMN_GROUPED:
		case COLUMN_LINE:
		case COLUMN_LINE_GROUPED:
		case COLUMN_LINE_STACKED:
		case COLUMN_LINE_STACKED100:
		case COLUMN_STACKED:
		case COLUMN_STACKED100:
			return "Vertical Bar Chart";
		case DONUT:
			return "Donut Chart";
		case FUNNEL:
			return "Funnel Chart";
		case GAUGE:
			return "Gauge Chart";
		case LINE:
		case LINE_CUMULATIVE:
		case LINE_GROUPED:
		case LINE_GROUPED_CUMULATIVE:
			return "Line Chart";
		case METRIC:
			return "Metric ";
		case PAGE:
			return "VisualForce Page";
		case PIE:
			return "Pie Chart";
		case SCATTER:
		case SCATTER_GROUPED:
			return "Scatter Chart";
		case TABLE:
			return "Tabel";
		case REPORT:
			return "source Report";
		default:
			break;

		}
		return null;
	}

	@Override
	public void onResize() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the component
	 */
	public DashboardComponent getComponent() {
		return component;
	}

	/**
	 * @param component
	 *            the component to set
	 */
	public void setComponent(DashboardComponent component) {
		this.component = component;
	}

	public void update() {

	}

	class ChartContainer extends AbsolutePanel implements IDroppable {

		public void createBody() {
			AbsolutePanel panel = new AbsolutePanel();

			// header Panel
			FlowPanel headerPanel = new FlowPanel();
			headerPanel.addStyleName("portler-headerPanel");
			TextBox header = new TextBox();
			header.addStyleName("portlet-header");
			header.getElement().setAttribute("placeholder", "Edit Header");
			headerPanel.add(header);

			// title Panel
			if (type == DashboardComponentType.REPORT
					|| type == DashboardComponentType.PAGE) {
				FlowPanel titlePanel = new FlowPanel();
				titlePanel.addStyleName("portlet-titlePanel");
				TextBox title = new TextBox();
				title.addStyleName("portlet-title");
				title.getElement().setAttribute("placeholder", "Edit Title");
				titlePanel.add(title);
				panel.add(titlePanel);
			}

			// chartPanel
			FlowPanel chartPanel = createChartPanel();

			// Footer Panel
			FlowPanel footerPanel = new FlowPanel();
			footerPanel.addStyleName("portlet-footerPanel");
			TextBox footer = new TextBox();
			footer.addStyleName("portlet-title");
			footer.getElement().setAttribute("placeholder", "Edit footer");
			footerPanel.add(footer);

			panel.add(headerPanel);

			panel.add(chartPanel);
			panel.add(footerPanel);
		}

		public FlowPanel createChartPanel() {
			FlowPanel bodyPanel = new FlowPanel();
			bodyPanel.addStyleName("portlet-bodyPanel");
			final FlowPanel chartPanel = new FlowPanel();
			chartPanel.addStyleName("ChartPanel");

			if (getComponent().getPage() == null
					&& getComponent().getReport() == null) {
				FlowPanel icon = new FlowPanel();
				icon.addStyleName(type.toString().toLowerCase() + "-icon");
				Label helpText = new Label(
						"Draga a Data source here to add data.");
				helpText.addStyleName("portlet-helptext");
				chartPanel.add(icon);
				chartPanel.add(helpText);
			} else {
				final FlowPanel datasourceTitle = new FlowPanel();
				datasourceTitle.addStyleName("datasource title");
				Label soureceTitle = new Label();
				if (type != DashboardComponentType.REPORT) {
					Image removeIcon = new Image();
					removeIcon.addClickHandler(new ClickHandler() {

						@Override
						public void onClick(ClickEvent event) {
							chartPanel.clear();
							datasourceTitle.removeFromParent();
							// TODO
						}
					});
					datasourceTitle.add(removeIcon);
				}
				datasourceTitle.add(soureceTitle);
				bodyPanel.add(datasourceTitle);
			}
			bodyPanel.add(chartPanel);
			return bodyPanel;
		}

		private void getPageUrl(String id) {

			RequestBuilder requestBuilder = new RequestBuilder(
					RequestBuilder.POST, GET_DASHBOARD_PAGE);

			requestBuilder.setHeader(HttpHeaders.CONTENT_TYPE,
					CONTENT_TYPE_JSON);

			requestBuilder.setRequestData("pageId=" + id);

			requestBuilder.setCallback(new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {

					Frame frame = new Frame(response.getText());
					ChartContainer.this.add(frame);

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

		@Override
		public void onDrop(IDraggable draggabelWidget) {
			if (draggabelWidget instanceof ChartComponent) {
				ChartComponent asWidget = (ChartComponent) draggabelWidget
						.asWidget();
				Portlet.this.clear();
				type = asWidget.getType();
				createControls();
			} else if (draggabelWidget instanceof DraggabelLableControl) {
				DraggabelLableControl widget = (DraggabelLableControl) draggabelWidget
						.asWidget();
				DataSourceListType type2 = widget.getType();
				if (type2 == DataSourceListType.REPORT) {
					retrieveReportData(widget.getId());
				} else if (type2 == DataSourceListType.PAGE) {
					getPageUrl(widget.getId());
				}

			}

		}

		private void retrieveReportData(String reportId) {
			RequestBuilder requestBuilder = new RequestBuilder(
					RequestBuilder.POST, GET_REPORT_DATA_FOR_DASHBOARD);

			requestBuilder.setRequestData("reportId=" + reportId);

			requestBuilder.setCallback(new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {

					String text = response.getText();

					JSONValue jsonValue = JSONParser.parseStrict(text);

					Report report = new Report();
					report.fromJSON(jsonValue.isObject());

					Portlet.this.results = report;
					// call again RPC for get Chart UI
					getChartData();
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

		private void getChartData() {
			RequestBuilder requestBuilder = new RequestBuilder(
					RequestBuilder.POST, GET_CHART_HTML);

			String componentString = component.toJSON().toString();
			DashboardData dashboardData = Dashboard.getDashboardData();
			String requestString = "componentData=" + componentString + ","
					+ "dashboardId=" + dashboardData.getId();
			requestBuilder.setRequestData(requestString);

			requestBuilder.setCallback(new RequestCallback() {

				@Override
				public void onResponseReceived(Request request,
						Response response) {
					String text = response.getText();
					FlowPanel charHtmlContainer = new FlowPanel();
					HTML html = new HTML(text);
					charHtmlContainer.add(html);
					ChartContainer.this.add(charHtmlContainer);

				}

				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub

				}
			});
		}

		@Override
		public boolean canDroppable(IDraggable draggabelWidget) {
			if (draggabelWidget instanceof ChartComponent) {
				ChartComponent cpmnt = (ChartComponent) draggabelWidget
						.asWidget();
				DashboardComponentType componentType = cpmnt.getType();
				if (componentType == DashboardComponentType.PAGE
						&& type != DashboardComponentType.PAGE) {
					return false;
				}
				return true;
			} else

			if (draggabelWidget instanceof DraggabelLableControl) {
				DraggabelLableControl dlc = (DraggabelLableControl) draggabelWidget
						.asWidget();
				DataSourceListType sourceType = dlc.getType();
				if (sourceType == DataSourceListType.REPORT
						&& type != DashboardComponentType.PAGE) {
					return true;
				} else if (sourceType == DataSourceListType.PAGE
						&& type == DashboardComponentType.PAGE) {
					return true;
				}

			}
			// TODO
			return false;
		}
	}

}
