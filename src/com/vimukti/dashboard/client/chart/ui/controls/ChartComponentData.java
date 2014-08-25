package com.vimukti.dashboard.client.chart.ui.controls;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.vimukti.dashboard.client.data.ChartSummary;
import com.vimukti.dashboard.client.data.ChartUnits;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.data.ReportSummaryType;
import com.vimukti.dashboard.client.reportdata.ChartAxis;
import com.vimukti.dashboard.client.reportdata.Report;
import com.vimukti.dashboard.client.reportdata.ReportAggregate;
import com.vimukti.dashboard.client.reportdata.ReportColumn;
import com.vimukti.dashboard.client.reportdata.ReportGrouping;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

/**
 * This class fields help to show data in charts
 *
 */
public class ChartComponentData extends FlowPanel {

	/**
	 * add this auto filed for chart Summaries and Report grouping
	 */
	public static final String AUTO = "Auto";

	/**
	 * add this None field for groupings when in groupBy list
	 */

	public static final String NONE = "None";
	/**
	 * Type of chart now showing chart preview
	 */
	private DashboardComponentType type;

	/**
	 * units for chart axis
	 */
	private SelectListBox<ChartUnits> displayUnits;

	/**
	 * Cumulative chart type for Line chart
	 */
	private CheckBox cumulative;

	/**
	 * plotBy chart type Scatter chart this is one of the OPTION in Scatter
	 * chart
	 */
	private SelectListBox<ReportGrouping> plotBy;

	/**
	 * this Report data will show in charts
	 */
	private Report result;

	/**
	 * All aggregates(formulas) of the reports have
	 */
	private List<ReportAggregate> aggregates;
	/**
	 * all grouped columns in report
	 */
	private List<ReportGrouping> groupings;

	/**
	 * when data changes in dialog it refresh charPreview panel
	 */
	private IRefreshChartPanel refreshPanel;

	/**
	 * to show summary columns and report grouping columns in list
	 */
	private List<SummaryAndAggregatesColumn> summaryList;

	/**
	 * to save report data in component
	 */
	private DashboardComponent component;

	/**
	 * first chart summaries list box
	 */
	private SelectListBox<SummaryAndAggregatesColumn> summaryListBox;

	/**
	 * second chart summaries list box
	 */
	private SelectListBox<SummaryAndAggregatesColumn> summaryListBox2;

	/**
	 * first grouping list box
	 */
	private SelectListBox<ReportGrouping> groupingListBox;

	/**
	 * combinations panel for bar and column panel
	 */
	private FlowPanel combinationPanel;

	/**
	 * second grouping list by list box
	 */
	private SelectListBox<ReportGrouping> groupByListBox;

	/**
	 * combination chart summaries list
	 */
	private SelectListBox<SummaryAndAggregatesColumn> combinationChartValue;

	/**
	 * when click on the chart it redirect to given url
	 */
	private SelectListBox<DrillDownToType> drillDownTo;

	public ChartComponentData(DashboardComponent component, Report results,
			IRefreshChartPanel refreshPanel) {
		if (component == null) {
			component = new DashboardComponent();
		}
		this.refreshPanel = refreshPanel;
		this.component = component;
		this.result = results;
		type = component.getComponentType();
		prepareReportData();
		createControls();
	}

	/**
	 * preparing data from report to show in dialog, summaryList and aggregates
	 * need to in one list
	 */
	private void prepareReportData() {
		summaryList = new ArrayList<SummaryAndAggregatesColumn>();
		// auto is default value
		SummaryAndAggregatesColumn auto = new SummaryAndAggregatesColumn();
		auto.setDisplayName(AUTO);
		summaryList.add(auto);

		// adding Reporcoluns and summaries to summaries list
		List<ReportColumn> columns = result.getColumns();
		for (ReportColumn column : columns) {
			SummaryAndAggregatesColumn columInner = new SummaryAndAggregatesColumn();
			columInner.setIsColumn(true);
			String field = column.getField();
			columInner.setColumn(field);
			List<ReportSummaryType> aggregateTypes = column.getAggregateTypes();
			for (ReportSummaryType summaryType : aggregateTypes) {
				String string = summaryType.toString();
				columInner.setDisplayName(string + " " + field);
				columInner.setType(summaryType);
				summaryList.add(columInner);
			}
		}

		aggregates = result.getAggregates();
		for (ReportAggregate aggregate : aggregates) {
			SummaryAndAggregatesColumn columInner = new SummaryAndAggregatesColumn();
			columInner.setIsColumn(false);
			columInner.setDisplayName(aggregate.getMasterLabel());
			columInner.setColumn(aggregate.getDeveloperName());
			summaryList.add(columInner);
		}

		// adding to types of groupings into collection
		groupings = new ArrayList<ReportGrouping>();
		ReportGrouping rAuto = new ReportGrouping();
		rAuto.setField(AUTO);
		groupings.add(rAuto);
		groupings.addAll(result.getGroupings());
	}

	/**
	 * re rendering the panel when chart(component) type changed
	 */
	public void reRendar() {
		this.clear();
		createControls();
	}

	/**
	 * creating controls by chart(component) type and grouping similar type
	 * charts
	 */

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
			break;
		case SCATTER:
		case SCATTER_GROUPED:
			createControlsForScatter();
			groupByForLineAndScatter();
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
		// these two fields are available for all chart tyeps
		displayUnits = new SelectListBox<ChartUnits>("Display Units") {
			@Override
			public String getDisplayName(ChartUnits item) {
				return item.toString();
			}
		};
		displayUnits.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ChartUnits selectedValue = displayUnits.getSelectedValue();
				component.setDisplayUnits(selectedValue);
				refreshPanel.refreshChartPanel();
			}
		});

		ChartUnits displayUnits2 = component.getDisplayUnits();
		displayUnits.setSelectedValue(displayUnits2);
		this.add(displayUnits);

		drillDownTo = new SelectListBox<DrillDownToType>("Drill Down to") {
			@Override
			public String getDisplayName(DrillDownToType item) {
				return item.toString();
			}
		};
		// this text box show when Other url is selcted
		// and user can enter custom url
		final TextBox box = new TextBox();
		box.addStyleName("url-texbox");
		drillDownTo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				DrillDownToType selectedValue = drillDownTo.getSelectedValue();
				if (selectedValue == DrillDownToType.SOURCE_REPORT) {
					ChartComponentData.this.remove(box);
					component.setDrillEnabled(true);
					component.setDrillToDetailEnabled(false);
				} else if (selectedValue == DrillDownToType.FILTERED_SOURCE_REPORT) {
					ChartComponentData.this.remove(box);
					component.setDrillEnabled(false);
					component.setDrillToDetailEnabled(false);
				} else if (selectedValue == DrillDownToType.RECORD_DETAIL_PAGE) {
					ChartComponentData.this.remove(box);
					component.setDrillToDetailEnabled(true);
				} else if (selectedValue == DrillDownToType.OTHER_URL) {
					ChartComponentData.this.add(box);
					component.setDrillDownUrl(box.getValue());
				}
			}
		});
		this.add(drillDownTo);
	}

	/**
	 * creating Scatter chart controls ,plotBy:takes Grouping column,
	 * xAsix:takes Summary column yAsix:takes summary Column
	 */
	private void createControlsForScatter() {
		// for x axis it shows summaries and aggregates of report
		summaryListBox = new SelectListBox<SummaryAndAggregatesColumn>("x-Axis") {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};

		summaryListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				SummaryAndAggregatesColumn selectedValue = summaryListBox
						.getSelectedValue();
				if (selectedValue.getDisplayName().equals(AUTO)) {
					selectedValue = null;
					summaryListBox2.setSelectedIndex(0);
					plotBy.setSelectedIndex(0);
				}
				setChartSummaryToComponent(selectedValue, ChartAxis.X, 0);
				refreshPanel.refreshChartPanel();
			}
		});
		summaryListBox.setItems(summaryList);

		// in scatter y axis also take summaries list
		summaryListBox2 = new SelectListBox<SummaryAndAggregatesColumn>(
				"Y-Axis") {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};

		summaryListBox2.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				SummaryAndAggregatesColumn selectedValue = summaryListBox2
						.getSelectedValue();
				if (selectedValue.getDisplayName().equals(AUTO)) {
					selectedValue = null;
					summaryListBox.setSelectedIndex(0);
					plotBy.setSelectedIndex(0);
				}
				setChartSummaryToComponent(selectedValue, ChartAxis.Y, 1);
				refreshPanel.refreshChartPanel();
			}
		});
		summaryListBox2.setItems(summaryList);

		// it show grouping list
		plotBy = new SelectListBox<ReportGrouping>("Plot By") {
			@Override
			public String getDisplayName(ReportGrouping item) {
				return item.getField();
			}
		};
		plotBy.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ReportGrouping selectedValue = plotBy.getSelectedValue();
				String field = selectedValue.getField();
				if (field.equals(AUTO)) {
					field = null;
					summaryListBox.setSelectedIndex(0);
					setChartSummaryToComponent(null, ChartAxis.Y, 0);
				}
				component.setGroupingColumn(field);
				refreshPanel.refreshChartPanel();
			}
		});

		plotBy.setItems(groupings);
		this.add(plotBy);
		this.add(summaryListBox);
		this.add(summaryListBox2);
	}

	/**
	 * creating Bar and Column and line charts controls
	 * 
	 */
	private void createControlsForBarColumnLine() {
		switch (type) {
		case COLUMN:
		case COLUMN_GROUPED:
		case COLUMN_LINE:
		case COLUMN_LINE_GROUPED:
		case COLUMN_LINE_STACKED:
		case COLUMN_LINE_STACKED100:
		case COLUMN_STACKED:
		case COLUMN_STACKED100:
			xandYforLineAndColumn();
			createGroupByPanel();
			createCombinationChartsPanel();
			break;
		case LINE:
		case LINE_CUMULATIVE:
		case LINE_GROUPED:
		case LINE_GROUPED_CUMULATIVE:
			xandYforLineAndColumn();
			groupByForLineAndScatter();
			cumulativeForLine();
			createCombinationChartsPanel();
			break;
		case BAR:
		case BAR_GROUPED:
		case BAR_STACKED:
		case BAR_STACKED100:
			createControlsForBar();
			createGroupByPanel();
			createCombinationChartsPanel();
			break;
		default:
			break;
		}
	}

	/**
	 * x and y axis for bar chart
	 */
	private void createControlsForBar() {

		// x- axis show summaries list of report
		summaryListBox = new SelectListBox<SummaryAndAggregatesColumn>("x-Axis") {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};

		summaryListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				SummaryAndAggregatesColumn selectedValue = summaryListBox
						.getSelectedValue();
				if (selectedValue.getDisplayName().equals(AUTO)) {
					selectedValue = null;
					summaryListBox2.setSelectedIndex(0);
					plotBy.setSelectedIndex(0);
				}
				setChartSummaryToComponent(selectedValue, ChartAxis.X, 0);
				refreshPanel.refreshChartPanel();
			}
		});
		summaryListBox.setItems(summaryList);

		// y- axis show report groupings list of report

		groupByListBox = new SelectListBox<ReportGrouping>("Y-Axis") {
			@Override
			public String getDisplayName(ReportGrouping item) {
				return item.getField();
			}
		};
		groupByListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ReportGrouping selectedValue = groupByListBox
						.getSelectedValue();
				String field = selectedValue.getField();
				if (field.equals(AUTO)) {
					field = null;
					summaryListBox.setSelectedIndex(0);
					setChartSummaryToComponent(null, ChartAxis.Y, 0);
				}
				component.setGroupingColumn(field);
				refreshPanel.refreshChartPanel();
			}
		});

		groupByListBox.setItems(groupings);

	}

	/**
	 * Line Cumulative chart type check box, this control shows in line chart if
	 * this box checks chart type changes to line cumulative
	 */
	private void cumulativeForLine() {
		cumulative = new CheckBox("Cumulative");
		cumulative.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (cumulative.getValue()) {
					component
							.setComponentType(DashboardComponentType.LINE_CUMULATIVE);
					refreshPanel.refreshChartPanel();
				} else {
					component.setComponentType(DashboardComponentType.LINE);
					refreshPanel.refreshChartPanel();
				}
			}
		});

		cumulative.addStyleName("cumulative");
		if (type == DashboardComponentType.LINE_CUMULATIVE) {
			cumulative.setValue(true);
		}
	}

	/**
	 * Group by control for line and scatter charts it shows grouping columns of
	 * reports
	 */
	private void groupByForLineAndScatter() {

		final SelectListBox<ReportGrouping> groupByListBox = new SelectListBox<ReportGrouping>() {
			@Override
			public String getDisplayName(ReportGrouping item) {
				String field = item.getField();
				if (field.equals(AUTO)) {
					field = "None";
				}
				return field;
			}
		};
		groupByListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ReportGrouping selectedValue = groupByListBox
						.getSelectedValue();
				String field = selectedValue.getField();
				component.setSecondaryGroupingColumn(field);
				// when group by list value. add it changed to grouped
				component
						.setComponentType(DashboardComponentType.SCATTER_GROUPED);
				refreshPanel.refreshChartPanel();
			}
		});
		groupByListBox.addStyleName("groupByList");
		this.add(groupByListBox);
	}

	/**
	 * x and y axis for line column charts x show summary and aggreagets and y
	 * show grouping of reports
	 */
	private void xandYforLineAndColumn() {
		// y- axis show summaries list of report
		summaryListBox = new SelectListBox<SummaryAndAggregatesColumn>("Y-Axis") {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};
		summaryListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				SummaryAndAggregatesColumn selectedValue = summaryListBox
						.getSelectedValue();
				if (selectedValue.getDisplayName().equals(AUTO)) {
					selectedValue = summaryListBox.getValueByIdex(1);
					groupingListBox.setSelectedIndex(0);
					component.setGroupingColumn(null);
				}
				setChartSummaryToComponent(selectedValue, ChartAxis.Y, 0);
				refreshPanel.refreshChartPanel();
			}
		});
		summaryListBox.setItems(summaryList);
		this.add(summaryListBox);
		// x- axis show groupings list of report
		groupingListBox = new SelectListBox<ReportGrouping>("X-Axis") {
			@Override
			public String getDisplayName(ReportGrouping item) {
				return item.getField();
			}
		};
		groupingListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ReportGrouping selectedValue = groupingListBox
						.getSelectedValue();
				String field = selectedValue.getField();
				if (field.equals(AUTO)) {
					field = null;
					summaryListBox.setSelectedIndex(0);
					setChartSummaryToComponent(null, ChartAxis.Y, 0);
				}
				component.setGroupingColumn(field);
				refreshPanel.refreshChartPanel();
			}
		});
		groupingListBox.setItems(groupings);
		this.add(groupingListBox);
	}

	/**
	 * @param data
	 *            selected value for chart summary data and sets
	 * @param axis
	 *            data shows in this axis
	 */

	private void setChartSummaryToComponent(SummaryAndAggregatesColumn data,
			ChartAxis axis, int index) {
		if (data == null) {
			component.setChartSummary(null);
			return;
		}
		String column = data.getColumn();
		ReportSummaryType type2 = data.getType();
		List<ChartSummary> chartSummaries = component.getChartSummary();
		// if given index not available in summaries list create new and add to
		// that list
		if (chartSummaries.size() - 1 < index) {
			ChartSummary chartSummary2 = new ChartSummary();
			chartSummaries.add(chartSummary2);
		}
		ChartSummary chartSummary = chartSummaries.get(index);
		chartSummary.setAggregate(type2);
		chartSummary.setAxisBinding(axis);
		chartSummary.setColumn(column);

	}

	/**
	 * creating controls for Donut funnel charts
	 */
	private void pieDonutFunnelChartType() {
		// values show groupings list of report
		final SelectListBox<SummaryAndAggregatesColumn> values = new SelectListBox<SummaryAndAggregatesColumn>(
				"values") {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};
		values.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				SummaryAndAggregatesColumn selectedValue = values
						.getSelectedValue();
				if (selectedValue.getDisplayName().equals(AUTO)) {
					selectedValue = null;

				}
				setChartSummaryToComponent(selectedValue, null, 0);
				refreshPanel.refreshChartPanel();
			}
		});
		values.setItems(summaryList);
		values.addStyleName("values-list");
		this.add(values);

		if (type != DashboardComponentType.FUNNEL) {
			wedgesForPieAndDonut();
		} else {
			segmentsForFunnel();
		}
	}

	/**
	 * creating segmet control for funnel chart
	 */
	private void segmentsForFunnel() {
		// segments show groupings list of report
		final SelectListBox<ReportGrouping> segments = new SelectListBox<ReportGrouping>(
				"Segments") {
			@Override
			public String getDisplayName(ReportGrouping item) {
				return item.getField();
			}
		};
		segments.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ReportGrouping selectedValue = segments.getSelectedValue();
				String field = selectedValue.getField();
				if (field.equals(AUTO)) {
					field = null;
				}
				component.setGroupingColumn(field);
				refreshPanel.refreshChartPanel();
			}
		});
		segments.addStyleName("segmets-list");
		this.add(segments);
	}

	/**
	 * creating wedges control for Donut
	 */
	private void wedgesForPieAndDonut() {
		// wedges show groupings list of report
		final SelectListBox<ReportGrouping> wedges = new SelectListBox<ReportGrouping>(
				"Wedges") {
			@Override
			public String getDisplayName(ReportGrouping item) {
				return item.getField();
			}
		};
		wedges.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ReportGrouping selectedValue = wedges.getSelectedValue();
				String field = selectedValue.getField();
				component.setGroupingColumn(field);
				refreshPanel.refreshChartPanel();
			}
		});
		wedges.addStyleName("wedges-list");
		this.add(wedges);
	}

	/**
	 * creating value for control for gauge and metric charts
	 */
	private void gaugeMetricChart() {
		final SelectListBox<SummaryAndAggregatesColumn> value = new SelectListBox<SummaryAndAggregatesColumn>(
				"value") {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};
		value.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				setChartSummaryToComponent(value.getSelectedValue(), null, 0);
				refreshPanel.refreshChartPanel();
			}
		});
		value.setItems(summaryList);
		value.addStyleName("value-list");
		this.add(value);
	}

	private void pageSettings() {
		// TODO method not belongs here
		// it may moves to other class
		TextItem heightInPixels = new TextItem("Height ");
		this.add(heightInPixels);
	}

	/**
	 * creating groupBy pane for Bar and column here 3 type charts will be added
	 * side-by-side,stacked and full stacked these 3 types of charts appears in
	 * groupby panel
	 */
	private void createGroupByPanel() {
		FlowPanel groupBy = new FlowPanel();
		// chart types panel
		final FlowPanel fPanel = new FlowPanel();

		Label name = new Label("Group by");
		FlowPanel vPanel = new FlowPanel();
		vPanel.addStyleName("groupby-section");

		// grouping list for groupbylist
		groupByListBox = new SelectListBox<ReportGrouping>() {
			@Override
			public String getDisplayName(ReportGrouping item) {
				String field = item.getField();
				if (field.equals(AUTO)) {
					field = NONE;
				}
				return field;
			}
		};
		groupByListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				fPanel.removeStyleName("disable");
				ReportGrouping selectedValue = groupByListBox
						.getSelectedValue();
				String field = selectedValue.getField();
				if (field.equals(NONE)) {
					field = null;
					fPanel.addStyleName("disable");
					combinationPanel.removeStyleName("disable");
				} else {
					combinationPanel.addStyleName("disable");
					// changing chat type
					type = type == DashboardComponentType.BAR ? DashboardComponentType.BAR_GROUPED
							: DashboardComponentType.COLUMN_GROUPED;
				}
				component.setComponentType(type);
				component.setSecondaryGroupingColumn(field);
				refreshPanel.refreshChartPanel();
			}
		});
		groupByListBox.addStyleName("groupby-listbox");

		// side - by -side chart when it valye chang
		FlowPanel sideBySide = new FlowPanel();
		sideBySide.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				type = type == DashboardComponentType.BAR ? DashboardComponentType.BAR_GROUPED
						: DashboardComponentType.COLUMN_GROUPED;
				component.setComponentType(type);
				refreshPanel.refreshChartPanel();
			}
		}, ClickEvent.getType());
		sideBySide.addStyleName("sidebyside-" + type.toString().toLowerCase());

		FlowPanel stacked = new FlowPanel();
		stacked.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				type = type == DashboardComponentType.BAR ? DashboardComponentType.BAR_STACKED
						: DashboardComponentType.COLUMN_STACKED;
				component.setComponentType(type);
				refreshPanel.refreshChartPanel();
			}
		}, ClickEvent.getType());
		stacked.addStyleName("stacked-" + type.toString().toLowerCase());

		FlowPanel fullStacked = new FlowPanel();
		fullStacked.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				type = type == DashboardComponentType.BAR ? DashboardComponentType.BAR_STACKED100
						: DashboardComponentType.COLUMN_STACKED100;
				component.setComponentType(type);
				// TODO
				// disable combination charts panel
				refreshPanel.refreshChartPanel();

			}
		}, ClickEvent.getType());
		fullStacked
				.addStyleName("fullStacked-" + type.toString().toLowerCase());

		sideBySide.removeStyleName("Selected");
		stacked.removeStyleName("Selected");
		fullStacked.removeStyleName("Selected");

		switch (type) {
		case BAR_GROUPED:
		case COLUMN_GROUPED:
			sideBySide.addStyleName("Selected");
			break;
		case BAR_STACKED:
		case COLUMN_STACKED:
			stacked.addStyleName("selected");
			break;
		case BAR_STACKED100:
		case COLUMN_STACKED100:
			fullStacked.addStyleName("selected");
		default:
			break;
		}

		fPanel.add(sideBySide);
		fPanel.add(stacked);
		fPanel.add(fullStacked);
		vPanel.add(groupByListBox);
		vPanel.add(fPanel);
		groupBy.add(name);
		groupBy.add(vPanel);
		this.add(groupBy);
	}

	/**
	 * creating Combination chart panel this allow us to show up to 3 chart to
	 * show in one chart
	 */
	private void createCombinationChartsPanel() {
		FlowPanel hPanel = new FlowPanel();
		hPanel.addStyleName("combination-chart-panel");
		Label combinationCharts = new Label("Cmbination Charts");

		combinationPanel = new FlowPanel();

		// condition for combination panel to show or not
		combinationPanel.addStyleName("disable");
		if (!summaryListBox.getSelectedValue().equals(AUTO)) {
			combinationPanel.removeStyleName("disable");
		}

		final FlowPanel comboPanel = new FlowPanel();
		final CheckBox additionalValue = new CheckBox("Plot additions value");
		additionalValue
				.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						if (additionalValue.getValue()) {
							switch (type) {
							case COLUMN:
							case COLUMN_GROUPED:
							case COLUMN_STACKED:
								additionalValuesPanelForColumn(comboPanel);
								break;
							case BAR:
							case BAR_GROUPED:
							case BAR_STACKED:
							case LINE:
							case LINE_GROUPED:
							case LINE_GROUPED_CUMULATIVE:
								additionalValuesPanelForBarLineAndColumn(comboPanel);
							default:
								break;
							}
						} else {
							comboPanel.clear();
						}
					}
				});
		combinationPanel.add(comboPanel);
		combinationPanel.add(additionalValue);
		hPanel.add(combinationCharts);
		hPanel.add(combinationPanel);
		this.add(hPanel);
	}

	/**
	 * it add summaries values
	 * 
	 * @param panel
	 *            in combination panel Addtion value panel
	 */
	private void additionalValuesPanelForBarLineAndColumn(final FlowPanel panel) {

		if (type != DashboardComponentType.LINE) {
			Button addButton = new Button();
			addButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					addValueForCombitionsChart(panel, component
							.getChartSummary().size());
				}
			});
			panel.add(addButton);
		}

		addValueForCombitionsChart(panel, component.getChartSummary().size());
	}

	//

	/**
	 * combinational panel for Column here two types need to show display and
	 * values in here we two values column and line
	 * 
	 * @param panel
	 *            combinational panel
	 */
	private void additionalValuesPanelForColumn(final FlowPanel panel) {
		final SelectListBox<DisplayType> display = new SelectListBox<DisplayType>(
				"Display") {
			@Override
			public String getDisplayName(DisplayType item) {
				return item.toString();
			}
		};

		display.addStyleName("disable");
		if (!summaryListBox.getSelectedValue().equals(AUTO)) {
			display.removeStyleName("disable");
		}

		display.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				combinationsPlotpanel(panel, display.getSelectedValue());
			}
		});

	}

	//

	/**
	 * column chart addition values adding y2-axis to chart by second summaries
	 * column
	 * 
	 * @param panel
	 *            combinational panel
	 * @param selectedValue
	 *            displayType column or line
	 */
	private void combinationsPlotpanel(FlowPanel panel,
			DisplayType selectedValue) {
		if (selectedValue == DisplayType.COlUMN) {
			additionalValuesPanelForBarLineAndColumn(panel);
		} else {
			combinationChartValue = new SelectListBox<SummaryAndAggregatesColumn>(
					"Value") {
				@Override
				public String getDisplayName(SummaryAndAggregatesColumn item) {
					return item.getDisplayName();
				}
			};
			combinationChartValue.setItems(summaryList);
			combinationChartValue.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					SummaryAndAggregatesColumn sval = combinationChartValue
							.getSelectedValue();
					setChartSummaryToComponent(sval, ChartAxis.X, 1);
					// TODO set Chart type ,this depend on groupBy
				}
			});
			CheckBox useSecondAxis = new CheckBox();
			useSecondAxis
					.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

						@Override
						public void onValueChange(
								ValueChangeEvent<Boolean> event) {
							SummaryAndAggregatesColumn sval = combinationChartValue
									.getSelectedValue();
							setChartSummaryToComponent(sval, ChartAxis.Y2, 1);
						}
					});

			panel.add(combinationChartValue);
			panel.add(useSecondAxis);
		}
	}

	/**
	 * showing and save adding chart summaries to component
	 * 
	 * @param panel
	 *            Combinational panel
	 * @param index
	 *            to which index store in summaries in component
	 */
	private void addValueForCombitionsChart(FlowPanel panel, final int index) {
		final SelectListBox<SummaryAndAggregatesColumn> value = new SelectListBox<SummaryAndAggregatesColumn>() {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};
		value.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				SummaryAndAggregatesColumn selectedValue = value
						.getSelectedValue();
				setChartSummaryToComponent(selectedValue, ChartAxis.X, index);
				refreshPanel.refreshChartPanel();
			}
		});

		value.setItems(getSummaryList());
		int widgetCount = panel.getWidgetCount();
		panel.insert(value, widgetCount);
	}

	/**
	 * 
	 * @return summaries filtered summaries list(remove aggregate list form
	 *         SummaryAndAggregatesColumn)
	 */
	private List<SummaryAndAggregatesColumn> getSummaryList() {
		List<SummaryAndAggregatesColumn> sumlist = new ArrayList<SummaryAndAggregatesColumn>();
		for (SummaryAndAggregatesColumn summary : summaryList) {
			if (summary.isColumn) {
				sumlist.add(summary);
			}
		}
		return summaryList;
	}

	/**
	 * this class help to hold ChartSummary and aggregate values of report and
	 * set into on list
	 *
	 */
	class SummaryAndAggregatesColumn {
		/**
		 * Name that in show in ListBox
		 */
		String displayName;
		/**
		 * to Differentiate summary column or aggregate
		 */
		boolean isColumn;
		/**
		 * type of summary column
		 */
		ReportSummaryType type;

		/**
		 * summary field or aggregate field
		 */
		String column;

		/**
		 * @return the name
		 */
		public String getDisplayName() {
			return displayName;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setDisplayName(String name) {
			this.displayName = name;
		}

		/**
		 * @return the isColumn
		 */
		public boolean isColumn() {
			return isColumn;
		}

		/**
		 * @param isColumn
		 *            the isColumn to set
		 */
		public void setIsColumn(boolean isColumn) {
			this.isColumn = isColumn;
		}

		/**
		 * @return the type
		 */
		public ReportSummaryType getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(ReportSummaryType type) {
			this.type = type;
		}

		/**
		 * @return the column
		 */
		public String getColumn() {
			return column;
		}

		/**
		 * @param column
		 *            the column to set
		 */
		public void setColumn(String column) {
			this.column = column;
		}

	}

}
