package com.vimukti.dashboard.client.chart.ui.controls;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.Gauge;
import com.google.gwt.visualization.client.visualizations.Table;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.BarChart;
import com.google.gwt.visualization.client.visualizations.corechart.ColumnChart;
import com.google.gwt.visualization.client.visualizations.corechart.HorizontalAxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;
import com.google.gwt.visualization.client.visualizations.corechart.ScatterChart;
import com.vimukti.dashboard.client.data.ChartLegendPosition;
import com.vimukti.dashboard.client.data.ChartSummary;
import com.vimukti.dashboard.client.data.ChartUnits;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.data.DashboardTableColumn;
import com.vimukti.dashboard.client.reportdata.Report;

public class ChartPanel extends FlowPanel {
	private DashboardComponent component;
	private Report report;
	private DataTable dataTable;
	private Options options;

	public ChartPanel(Report report, DashboardComponent component) {
		this.report = report;
		this.component = component;
		prepareDataTable();
		prepareOptions();
		createChart();
	}

	private void prepareOptions() {
		options = Options.create();
	}

	private void getChartSettings() {
		double chartAxisRangeMax = component.getChartAxisRangeMax();
		// AxisOption.setMaxValue()

		double chartAxisRangeMin = component.getChartAxisRangeMin();
		ChartSummary chartSummary = component.getChartSummary();
		DashboardComponentType componentType = component.getComponentType();
		List<DashboardTableColumn> dashboardTableColumn = component
				.getDashboardTableColumn();
		ChartUnits displayUnits = component.getDisplayUnits();
		String drillDownUrl = component.getDrillDownUrl();
		String footer = component.getFooter();
		double gaugeMax = component.getGaugeMax();
		double gaugeMin = component.getGaugeMin();
		String groupingColumn = component.getGroupingColumn();
		String header = component.getHeader();
		double indicatorBreakpoint1 = component.getIndicatorBreakpoint1();
		double indicatorBreakpoint2 = component.getIndicatorBreakpoint2();
		String indicatorHighColor = component.getIndicatorHighColor();
		String title = component.getTitle();
		String report2 = component.getReport();
		int pageHeightInPixels = component.getPageHeightInPixels();
		String metricLabel = component.getMetricLabel();
		int maxValuesDisplayed = component.getMaxValuesDisplayed();
		ChartLegendPosition legendPosition = component.getLegendPosition();
		String indicatorMiddleColor = component.getIndicatorMiddleColor();
		String indicatorLowColor = component.getIndicatorLowColor();

		HorizontalAxisOptions xOptions = HorizontalAxisOptions.create();
		xOptions.setTextPosition("out");
		// need to set Axis value useing displayUnits .setMaxValue and
		// setMinValue

		AxisOptions yOptions = AxisOptions.create();
		yOptions.setMaxValue(chartAxisRangeMax);
		yOptions.setMinValue(chartAxisRangeMin);

		// options.setAxisTitlesPosition(position);
		// options.setBackgroundColor(color);
		// options.setChartArea(chartArea);
		// options.setColors(colors);
		// options.setCurveType(type);
		// options.setFontName(name);
		// options.setFontSize(fontSize);
		// options.setHAxisOptions(options);
		// options.setHeight(height);
		// options.setIsStacked(isStacked);
		// options.setLegend(position);
		// options.setLegendTextStyle(style);
		// options.setLegend(legend);
		// options.setLineWidth(width);
		// options.setPointSize(size);
		// options.setReverseCategories(reverseCategories);
		// options.setTitle(title);
		// options.setTitleTextStyle(style);
		// options.setType(type);
		// options.setVAxisOptions(options);
		// options.setWidth(width);
	}

	private void prepareDataTable() {
		// TODO Auto-generated method stub
		// dataTable = DataTable.create();
		// List<ReportAggregate> aggregates = report.getAggregates();
		// dataTable.addRows(componetData.size());
		// for (int index = 0; (index < componetData.size()); index++) {
		//
		// dataTable.setValue(index, 0, "");
		// dataTable.setValue(index, 1, "");
		// }

	}

	private void createChart() {
		switch (component.getComponentType()) {
		case BAR:
		case BAR_GROUPED:
		case BAR_STACKED:
		case BAR_STACKED100:
			BarChart barChart = new BarChart(dataTable, options);
			this.add(barChart);
			break;
		case COLUMN:
		case COLUMN_GROUPED:
		case COLUMN_LINE:
		case COLUMN_LINE_GROUPED:
		case COLUMN_LINE_STACKED:
		case COLUMN_LINE_STACKED100:
		case COLUMN_STACKED:
		case COLUMN_STACKED100:
			ColumnChart columnChart = new ColumnChart(dataTable, options);
			this.add(columnChart);
			break;
		case DONUT:
			PieChart donutChart = new PieChart(dataTable, options);
			this.add(donutChart);
			break;
		case FUNNEL:
			break;
		case GAUGE:
			// need to pass options Object (Gauge.Options)
			Gauge gauge = new Gauge(dataTable, null);
			break;
		case LINE:
		case LINE_CUMULATIVE:
		case LINE_GROUPED:
		case LINE_GROUPED_CUMULATIVE:
			LineChart lineChart = new LineChart(dataTable, options);
			this.add(lineChart);
			break;
		case METRIC:
			// google charts not supporting
			break;
		case PIE:
			PieChart pieChart = new PieChart(dataTable, options);
			this.add(pieChart);
			break;
		case SCATTER:
		case SCATTER_GROUPED:
			ScatterChart scatterChart = new ScatterChart(dataTable, options);
			this.add(scatterChart);
			break;
		case TABLE:
			// need to pass options object(Table.Options)
			Table table = new Table(dataTable, null);
			this.add(table);
			break;
		default:
			break;

		}
	}
}
