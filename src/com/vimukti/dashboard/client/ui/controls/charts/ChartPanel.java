package com.vimukti.dashboard.client.ui.controls.charts;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.BarChart;
import com.google.gwt.visualization.client.visualizations.corechart.ColumnChart;
import com.google.gwt.visualization.client.visualizations.corechart.HorizontalAxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;
import com.google.gwt.visualization.client.visualizations.corechart.ScatterChart;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.reportdata.Report;

public class ChartPanel extends FlowPanel {

	private Report report;
	private DashboardComponent component;
	private DataTable dataTable;
	private Options options;

	public ChartPanel(DashboardComponent component, Report results) {
		this.addStyleName("chart-panel");
		this.component = component;
		this.report = results;
	}

	public ChartPanel getChart() {
		return this;
	}

	private void preparePieChartDataTable() {
		DataTable dataTable = DataTable.create();
		dataTable.addRows(report.getColumns().size());
		for (int index = 0; (index < report.getColumns().size()); index++) {
			dataTable.setValue(index, 0, "");
			dataTable.setValue(index, 1, "");
		}
		createChart();
	}

	private void getChartOptions(DataTable table) {
		options = Options.create();
		HorizontalAxisOptions xAxixOptions = HorizontalAxisOptions.create();
		xAxixOptions.setTitle();
		options.setLegend(component.getLegendPosition().toString()
				.toLowerCase());
		options.setHAxisOptions(xAxixOptions);
		table.addColumn();
		AxisOptions yAxisOptions = AxisOptions.create();
		yAxisOptions.setTitle();
		options.setLegend(component.getLegendPosition().toString()
				.toLowerCase());
		options.setVAxisOptions(yAxisOptions);
	}

	private void createChart() {
		this.clear();
		switch (component.getComponentType()) {
		case BAR:
		case BAR_GROUPED:
		case BAR_STACKED:
		case BAR_STACKED100:
			BarChart barChart = new BarChart(dataTable, options);
			this.add(barChart);
		case COLUMN:
		case COLUMN_GROUPED:
		case COLUMN_LINE:
		case COLUMN_LINE_GROUPED:
		case COLUMN_LINE_STACKED:
		case COLUMN_LINE_STACKED100:
		case COLUMN_STACKED:
		case COLUMN_STACKED100:
			ColumnChart colunChart = new ColumnChart(dataTable, options);
			break;
		case DONUT:
			// need to add pie hole this options to make pie chart to donut
			// chart
			// TODO
			PieChart donutChart = new PieChart(dataTable, options);
			this.add(donutChart);
			break;
		case FUNNEL:
			// Not supporting now
			break;
		case GAUGE:
			// need to pass Gauge.options to this options argument(not options)
			// TODO
			// Gauge gauge = new Gauge(dataTable, options);
			break;
		case LINE:
		case LINE_CUMULATIVE:
		case LINE_GROUPED:
		case LINE_GROUPED_CUMULATIVE:
			LineChart lineChart = new LineChart(dataTable, options);
			this.add(lineChart);
		case METRIC:
			// google charts not supporting
			break;
		case PIE:
			PieChart pieChar = new PieChart(dataTable, options);
			this.add(pieChar);
			break;
		case SCATTER:
		case SCATTER_GROUPED:
			ScatterChart scatterChart = new ScatterChart(dataTable, options);
			this.add(scatterChart);
			break;
		case TABLE:
			// TODO
			// create Table.Options object for this Table Charts
			// Table table = new Table(dataTable, options);
			break;
		default:
			break;
		}
	}

}
