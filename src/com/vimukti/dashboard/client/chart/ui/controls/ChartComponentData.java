package com.vimukti.dashboard.client.chart.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.ChartUnits;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class ChartComponentData extends FlowPanel {

	private DashboardComponentType type;
	private SelectListBox<Object> xAxis;
	private SelectListBox<Object> yAxis;
	private HorizontalPanel groupBy;
	private HorizontalPanel combinationCharts;
	private SelectListBox<ChartUnits> displayUnits;
	private CheckBox cumulative;
	private SelectListBox<Object> plotBy;

	private DashboardComponent component;

	// needCreate object for this field
	private SelectListBox<Object> drillDownTo;

	public ChartComponentData(DashboardComponent component) {
		this.component = component;
		createControls();
	}

	public void reRendar(DashboardComponent component) {
		this.clear();
		this.component = component;
		createControls();
	}

	private void createControls() {
		switch (type) {
		case BAR:
		case BAR_GROUPED:
		case BAR_STACKED:
		case BAR_STACKED100:
		case COLUMN:
		case COLUMN_GROUPED:
		case COLUMN_LINE:
		case COLUMN_LINE_GROUPED:
		case COLUMN_LINE_STACKED:
		case COLUMN_LINE_STACKED100:
		case COLUMN_STACKED:
		case COLUMN_STACKED100:
		case LINE:
		case LINE_CUMULATIVE:
		case LINE_GROUPED:
		case LINE_GROUPED_CUMULATIVE:
		case SCATTER:
		case SCATTER_GROUPED:
			createControlsForBarColumnLine();
			break;
		case DONUT:
		case FUNNEL:
		case PIE:
			pieDonutFunnelChartType();
			break;
		case GAUGE:
		case METRIC:
			gaugeMetricChart();
		case PAGE:
			pageSettings();
			break;
		case TABLE:
			break;
		default:
			break;

		}

		displayUnits = new SelectListBox<ChartUnits>("Display Units") {
			@Override
			public String getDisplayName(ChartUnits item) {
				return item.toString().toLowerCase();
			}
		};
		drillDownTo = new SelectListBox<Object>("Drill Down to");
		this.add(displayUnits);
		this.add(drillDownTo);

	}

	public void createControlsForBarColumnLine() {
		if (type == DashboardComponentType.SCATTER) {
			plotBy = new SelectListBox<Object>();
			String groupingColumn = component.getGroupingColumn();
			// TODO
		}
		xAxis = new SelectListBox<Object>();
		yAxis = new SelectListBox<Object>();
		if (type == DashboardComponentType.COLUMN
				|| type == DashboardComponentType.LINE) {
			this.add(yAxis);
			this.add(xAxis);
		} else if (type == DashboardComponentType.BAR
				|| type == DashboardComponentType.SCATTER) {
			this.add(xAxis);
			this.add(yAxis);
		}
		if (type == DashboardComponentType.LINE) {
			SelectListBox<Object> groupByListBox = new SelectListBox<Object>();
			groupByListBox.addStyleName("groupByList");
			this.add(groupByListBox);
		} else if (type == DashboardComponentType.BAR
				|| type == DashboardComponentType.COLUMN) {
			createGroupByPanel(type);
		}
		if (type == DashboardComponentType.LINE) {
			cumulative = new CheckBox("Cumulative");
			cumulative.addStyleName("cumulative");
		}
		if (type == DashboardComponentType.COLUMN
				|| type == DashboardComponentType.LINE
				|| type == DashboardComponentType.BAR) {
			createCombinationChartsPanel();
		}

	}

	private void pieDonutFunnelChartType() {
		SelectListBox<Object> values = new SelectListBox<Object>("values");
		values.addStyleName("values-list");
		this.add(values);

		if (type != DashboardComponentType.FUNNEL) {
			SelectListBox<Object> wedges = new SelectListBox<Object>("Wedges");
			wedges.addStyleName("wedges-list");
			this.add(wedges);
		} else {
			SelectListBox<Object> segments = new SelectListBox<Object>(
					"Segments");
			segments.addStyleName("segmets-list");
		}
	}

	private void gaugeMetricChart() {
		SelectListBox<Object> value = new SelectListBox<Object>("Value");
		value.addStyleName("value");
		this.add(value);
	}

	private void pageSettings() {
		// TODO method not belongs here
		// it may moves to other class
		TextItem heightInPixels = new TextItem("Height ");
		this.add(heightInPixels);
	}

	private void createGroupByPanel(DashboardComponentType type) {
		groupBy = new HorizontalPanel();

		Label name = new Label();
		VerticalPanel vPanel = new VerticalPanel();

		ListBox box = new ListBox();
		box.addStyleName("groupby-listbox");
		FlowPanel fPanel = new FlowPanel();

		FlowPanel sideBySide = new FlowPanel();
		sideBySide.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());
		sideBySide.addStyleName("sidebyside-" + type.toString().toLowerCase());

		FlowPanel stacked = new FlowPanel();
		stacked.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());
		stacked.addStyleName("stacked-" + type.toString().toLowerCase());

		FlowPanel fullStacked = new FlowPanel();
		fullStacked.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());
		fullStacked
				.addStyleName("fullStacked-" + type.toString().toLowerCase());

		fPanel.add(sideBySide);
		fPanel.add(stacked);
		fPanel.add(fullStacked);
		vPanel.add(box);
		vPanel.add(fPanel);
		groupBy.add(name);
		groupBy.add(vPanel);
		this.add(groupBy);
	}

	private void createCombinationChartsPanel() {
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.addStyleName("combination-chart-panel");
		Label combinationCharts = new Label("Cmbination Charts");
		FlowPanel fPanel = new FlowPanel();
		CheckBox additonalValues = new CheckBox("Plot additions value");
		fPanel.add(additonalValues);
		hPanel.add(combinationCharts);
		hPanel.add(fPanel);
		this.add(hPanel);
	}

	public void update() {

	}

}
