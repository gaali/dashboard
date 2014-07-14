package com.vimukti.dashboard.chart.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.ChartUnits;
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

	// needCreate object for this field
	private SelectListBox<Object> drillDownTo;

	public ChartComponentData(DashboardComponentType type) {
		this.type = type;
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
			createControlsForBarColumnLine();
		case DONUT:
		case FUNNEL:
		case GAUGE:
			pieChartType();
		case METRIC:
			metricChart();
		case PAGE:
			pageSettings();
		case PIE:
			break;
		case SCATTER:
			break;
		case SCATTER_GROUPED:
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
		xAxis = new SelectListBox<Object>();
		yAxis = new SelectListBox<Object>();
		if (type == DashboardComponentType.COLUMN
				|| type == DashboardComponentType.LINE) {
			this.add(xAxis);
			this.add(yAxis);
		} else if (type == DashboardComponentType.BAR) {
			this.add(yAxis);
			this.add(xAxis);
		}
		if (type == DashboardComponentType.LINE) {
			SelectListBox<Object> groupByListBox = new SelectListBox<Object>();
			groupByListBox.addStyleName("groupByList");
		} else if (type == DashboardComponentType.COLUMN
				|| type == DashboardComponentType.LINE) {
			createGroupByPanel();
		}
		if (type == DashboardComponentType.COLUMN
				|| type == DashboardComponentType.LINE
				|| type == DashboardComponentType.BAR) {
			createCombinationChartsPanel();
		}
		if (type == DashboardComponentType.LINE) {
			cumulative = new CheckBox("Cumulative");
			cumulative.addStyleName("cumulative");
		}
	}

	private void pieChartType() {
		SelectListBox<Object> values = new SelectListBox<Object>("values");
		values.addStyleName("values-list");
		this.add(values);
		SelectListBox<Object> wedges = new SelectListBox<Object>("Wedges");
		wedges.addStyleName("wedges-list");
		this.add(wedges);
		SelectListBox<Object> segments = new SelectListBox<Object>("Segments");
		segments.addStyleName("segmets-list");
	}

	private void metricChart() {
		SelectListBox<Object> value = new SelectListBox<Object>("Value");
		value.addStyleName("value");
	}

	private void pageSettings() {
		TextItem heightInPixels = new TextItem("Height ");
	}

	private void createGroupByPanel() {
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
		sideBySide.addStyleName("sidebyside");

		FlowPanel stacked = new FlowPanel();
		stacked.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());
		stacked.addStyleName("stacked");

		FlowPanel fullStacked = new FlowPanel();
		fullStacked.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());
		fullStacked.addStyleName("fullStacked");

		fPanel.add(sideBySide);
		fPanel.add(stacked);
		fPanel.add(fullStacked);
		vPanel.add(box);
		vPanel.add(fPanel);
		groupBy.add(name);
		groupBy.add(vPanel);
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
	}

}
