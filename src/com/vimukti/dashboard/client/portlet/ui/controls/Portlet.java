package com.vimukti.dashboard.client.portlet.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.ui.controls.dnd.IDraggable;
import com.vimukti.dashboard.client.ui.controls.dnd.IDroppable;

public class Portlet extends FlowPanel implements RequiresResize {
	private DashboardComponent component;
	private DashboardComponentType type;

	public Portlet(DashboardComponent component) {
		this.component = component;
		type = component.getComponentType();
		createControls();
	}

	private void createControls() {
		createHeaderBarForPortlet();
		createTitleForPortlet();
	}

	private void createTitleForPortlet() {
		AbsolutePanel panel = new AbsolutePanel();

		// header Panel
		FlowPanel headerPanel = new FlowPanel();
		headerPanel.addStyleName("portler-headerPanel");
		TextBox header = new TextBox();
		header.addStyleName("portlet-header");
		header.getElement().setAttribute("placeholder", "Edit Header");
		headerPanel.add(header);

		// title Panel
		FlowPanel titlePanel = new FlowPanel();
		titlePanel.addStyleName("portlet-titlePanel");
		TextBox title = new TextBox();
		title.addStyleName("portlet-title");
		title.getElement().setAttribute("placeholder", "Edit Title");
		titlePanel.add(title);

		// title Panel
		FlowPanel footerPanel = new FlowPanel();
		footerPanel.addStyleName("portlet-footerPanel");
		TextBox footer = new TextBox();
		footer.addStyleName("portlet-title");
		footer.getElement().setAttribute("placeholder", "Edit footer");
		footerPanel.add(footer);

		// body panel
		ChartContainer container = new ChartContainer();
		container.createChartPanel();

		panel.add(headerPanel);
		panel.add(titlePanel);
		panel.add(container);
		panel.add(footerPanel);
	}

	private void prepareChart(VerticalPanel bodyPanel) {
		// TODO Auto-generated method stub

	}

	private void createHeaderBarForPortlet() {
		HorizontalPanel headerBar = new HorizontalPanel();
		headerBar.addStyleName("portlet-headerBar");
		Label title = new Label(getComponentTitle());
		Image settings = new Image();
		settings.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// ChartSettingsDialog componentEditor = new
				// ChartSettingsDialog(
				// component);
			}
		});
		settings.addStyleName("portlet-settings-icon");
		Image removeIcon = new Image();
		removeIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Portlet.this.removeFromParent();

			}
		});
		settings.addStyleName("portlet-remove-icon");
		headerBar.add(title);
		headerBar.add(settings);
		headerBar.add(removeIcon);
		this.add(headerBar);
	}

	private String getComponentTitle() {
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
		default:
			break;

		}
		return null;
	}

	@Override
	public void onResize() {
		// TODO Auto-generated method stub

	}

	class ChartContainer extends FlowPanel implements IDroppable {

		public ChartContainer() {
			super();
		}

		public void createChartPanel() {
			VerticalPanel bodyPanel = new VerticalPanel();
			bodyPanel.addStyleName("portlet-bodyPanel");
			if (component.getPage() == null && component.getReport() == null) {
				FlowPanel icon = new FlowPanel();
				icon.addStyleName(type.toString().toLowerCase() + "-icon");
				Label helpText = new Label(
						"Draga a Data source here to add data.");
				helpText.addStyleName("portlet-helptext");
				bodyPanel.add(icon);
				bodyPanel.add(helpText);
			} else {
				prepareChart(bodyPanel);
				HorizontalPanel datasourceTitle = new HorizontalPanel();
				datasourceTitle.addStyleName("datasource title");
				Label soureceTitle = new Label();
				Image removeIcon = new Image();
				removeIcon.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
				datasourceTitle.add(soureceTitle);
				datasourceTitle.add(removeIcon);
			}
		}

		@Override
		public void onDrop(IDraggable draggabelWidget) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean canDroppable(IDraggable draggabelWidget) {
			// TODO Auto-generated method stub
			return false;
		}

	}

}
