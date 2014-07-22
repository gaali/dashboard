package com.vimukti.dashboard.client.portlet.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.chart.ui.controls.ComponentEditorDialog;
import com.vimukti.dashboard.client.column.PortletContainerPanel;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.data.IDashboardServiceAsync;
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
	IDashboardServiceAsync dashboardServiceObject = Dashboard
			.getDashboardServiceObject();

	public Portlet(DashboardComponent component) {
		this.component = component;
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
								component);
						editorPage.show();
						editorPage.center();
					} else {
						ComponentEditorDialog componentEditor = new ComponentEditorDialog(
								component, results);
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

	class ChartContainer extends AbsolutePanel implements IDroppable {

		public ChartContainer() {
			super();
		}

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
			VerticalPanel chartPanel = createChartPanel();

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

		public VerticalPanel createChartPanel() {
			VerticalPanel bodyPanel = new VerticalPanel();
			bodyPanel.addStyleName("portlet-bodyPanel");
			final VerticalPanel chartPanel = new VerticalPanel();
			chartPanel.addStyleName("ChartPanel");

			if (component.getPage() == null && component.getReport() == null) {
				FlowPanel icon = new FlowPanel();
				icon.addStyleName(type.toString().toLowerCase() + "-icon");
				Label helpText = new Label(
						"Draga a Data source here to add data.");
				helpText.addStyleName("portlet-helptext");
				chartPanel.add(icon);
				chartPanel.add(helpText);
			} else {
				final HorizontalPanel datasourceTitle = new HorizontalPanel();
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

		private void preparePagePanel(VerticalPanel bodyPanel) {
			if (type == DashboardComponentType.PAGE) {
				String pageLink = getPageObject(objectId);
				Frame frame = new Frame(pageLink);
				bodyPanel.add(frame);
			}
		}

		private void prepareChart() {
		}

		private String getPageObject(String id) {
			dashboardServiceObject.getPage(id, new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(String result) {
					// TODO Auto-generated method stub

				}
			});
			return null;
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
					dashboardServiceObject.getReport(widget.getId(),
							new AsyncCallback<Report>() {

								@Override
								public void onSuccess(Report result) {
									Portlet.this.results = results;
									prepareChart();
								}

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub

								}
							});

				} else if (type2 == DataSourceListType.PAGE) {
					String pageObject = getPageObject(widget.getId());
					// TODO
				}

			}

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
