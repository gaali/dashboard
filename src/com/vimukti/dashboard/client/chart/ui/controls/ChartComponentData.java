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
	 * all groued columns in report
	 */
	private List<ReportGrouping> groupings;

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
			columInner.setColumn(aggregate.getMasterLabel());
			summaryList.add(columInner);
		}

		groupings = result.getGroupings();

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
		plotBy = new SelectListBox<ReportGrouping>() {
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
				setGroupingColumnToComponent(field);
				refreshPanel.refreshChartPanel();
			}
		});
		plotBy.setItems(groupings);
		this.add(plotBy);
		// for x axis it shows summaries and aggregates of report
		final SelectListBox<SummaryAndAggregatesColumn> xAxis = new SelectListBox<SummaryAndAggregatesColumn>() {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};
		xAxis.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				SummaryAndAggregatesColumn selectedValue = xAxis
						.getSelectedValue();
				setChartDataToComponent(selectedValue, ChartAxis.Y);
				refreshPanel.refreshChartPanel();
			}
		});
		xAxis.setItems(summaryList);
		this.add(xAxis);

		final SelectListBox<SummaryAndAggregatesColumn> yAxis = new SelectListBox<SummaryAndAggregatesColumn>() {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};
		yAxis.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				SummaryAndAggregatesColumn selectedValue = yAxis
						.getSelectedValue();
				setChartDataToComponent(selectedValue, ChartAxis.Y);
				refreshPanel.refreshChartPanel();
			}
		});
		yAxis.setItems(summaryList);
		this.add(yAxis);
	}

	/**
	 * creating Bar and Column and line charts controls
	 * 
	 */
	private void createControlsForBarColumnLine() {
		switch (type) {
		case COLUMN:
		case COLUMN_GROUPED:
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
			createGroupByPanel();
			createCombinationChartsPanel();
			break;
		default:
			break;
		}
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
				return item.getField();
			}
		};
		groupByListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ReportGrouping selectedValue = groupByListBox
						.getSelectedValue();
				String field = selectedValue.getField();
				secondGroupBy(field);
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
		final SelectListBox<SummaryAndAggregatesColumn> yAxis = new SelectListBox<SummaryAndAggregatesColumn>() {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};
		yAxis.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				SummaryAndAggregatesColumn selectedValue = yAxis
						.getSelectedValue();
				setChartDataToComponent(selectedValue, ChartAxis.Y);
				refreshPanel.refreshChartPanel();
			}
		});
		yAxis.setItems(summaryList);
		this.add(yAxis);

		final SelectListBox<ReportGrouping> xAxis = new SelectListBox<ReportGrouping>() {
			@Override
			public String getDisplayName(ReportGrouping item) {
				return item.getField();
			}
		};
		xAxis.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ReportGrouping selectedValue = xAxis.getSelectedValue();
				String field = selectedValue.getField();
				setGroupingColumnToComponent(field);
				refreshPanel.refreshChartPanel();
			}
		});
		xAxis.setItems(groupings);
		this.add(xAxis);
	}

	/**
	 * 
	 * here field selected value this method takes selected value and set in
	 * first positions if it has multiple grouping values else it replace the
	 * older value with field
	 */
	private void setGroupingColumnToComponent(String field) {
		String groupingColumn = component.getGroupingColumn();
		String groupingColumnString = "";
		if (groupingColumn != null) {
			String[] split = groupingColumn.split(",");
			split[0] = field;
			groupingColumnString = split.toString();
		} else {
			groupingColumnString = field;
		}
		component.setGroupingColumn(groupingColumnString);
	}

	/**
	 * 
	 * here field selected value this method takes selected value and set in
	 * second positions, if it has multiple grouping values else it replace the
	 * older value with field,
	 */
	private void secondGroupBy(String field) {
		String groupingColumn = component.getGroupingColumn();
		String groupingColumnString = "";
		if (groupingColumn != null) {
			String[] split = groupingColumn.split(",");
			if (split.length == 2) {
				split[1] = field;
			} else {
				groupingColumnString = split.toString();
				groupingColumnString += "," + field;
			}
		} else {
			groupingColumnString = field;
		}
		component.setGroupingColumn(groupingColumnString);
	}

	/**
	 * @param data
	 *            selected value for chart summary data and sets
	 * @param axis
	 *            data shows in this axis
	 */
	private void setChartDataToComponent(SummaryAndAggregatesColumn data,
			ChartAxis axis) {
		String column = data.getColumn();
		ReportSummaryType type2 = data.getType();

		ChartSummary chartSummary = component.getChartSummary();
		chartSummary.setAggregate(type2);
		if (axis != null) {
			chartSummary.setAxisBinding(axis);
		}
		chartSummary.setColumn(column);
	}

	/**
	 * creating controls for Donut funnel charts
	 */
	private void pieDonutFunnelChartType() {
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
				setChartDataToComponent(values.getSelectedValue(), null);
				refreshPanel.refreshChartPanel();
			}
		});
		values.setItems(summaryList);
		values.addStyleName("values-list");
		this.add(values);

		if (type == DashboardComponentType.DONUT) {
			wedgesForDonut();
		} else {
			segmentsForFunnel();
		}
	}

	/**
	 * creating segmet control for funnel chart
	 */
	private void segmentsForFunnel() {
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
				setGroupingColumnToComponent(field);
				refreshPanel.refreshChartPanel();
			}
		});
		segments.addStyleName("segmets-list");
		this.add(segments);
	}

	/**
	 * creating wedges control for Donut
	 */
	private void wedgesForDonut() {
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
				setGroupingColumnToComponent(field);
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
				setChartDataToComponent(value.getSelectedValue(), null);
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

		Label name = new Label("Group by");
		FlowPanel vPanel = new FlowPanel();
		vPanel.addStyleName("groupby-section");

		final SelectListBox<ReportGrouping> box = new SelectListBox<ReportGrouping>() {
			@Override
			public String getDisplayName(ReportGrouping item) {
				return item.getField();
			}
		};
		box.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ReportGrouping selectedValue = box.getSelectedValue();
				String field = selectedValue.getField();
				secondGroupBy(field);
				refreshPanel.refreshChartPanel();
			}
		});
		box.addStyleName("groupby-listbox");
		FlowPanel fPanel = new FlowPanel();

		FlowPanel sideBySide = new FlowPanel();
		sideBySide.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				type = DashboardComponentType.getComponentType("Bar") != null ? DashboardComponentType.BAR_GROUPED
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
				type = DashboardComponentType.getComponentType("Bar") != null ? DashboardComponentType.BAR_GROUPED
						: DashboardComponentType.COLUMN_GROUPED;
				component.setComponentType(type);
				refreshPanel.refreshChartPanel();

			}
		}, ClickEvent.getType());
		stacked.addStyleName("stacked-" + type.toString().toLowerCase());

		FlowPanel fullStacked = new FlowPanel();
		fullStacked.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				type = DashboardComponentType.getComponentType("Bar") != null ? DashboardComponentType.BAR_GROUPED
						: DashboardComponentType.COLUMN_GROUPED;
				component.setComponentType(type);
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
		vPanel.add(box);
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
		FlowPanel fPanel = new FlowPanel();
		final FlowPanel comboPanel = new FlowPanel();
		final CheckBox additionalValue = new CheckBox("Plot additions value");
		additionalValue
				.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						if (additionalValue.getValue()) {
							additionalValuesPanel(comboPanel);
						} else {
							comboPanel.clear();
						}

					}
				});
		fPanel.add(comboPanel);
		fPanel.add(additionalValue);
		hPanel.add(combinationCharts);
		hPanel.add(fPanel);
		this.add(hPanel);
	}

	private void additionalValuesPanel(final FlowPanel panel) {
		if (type != DashboardComponentType.LINE) {
			Button addButton = new Button();
			addButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					addValueForCombitionsChart(panel);
				}
			});
			panel.add(addButton);
		}
		addValueForCombitionsChart(panel);
	}

	private void addValueForCombitionsChart(FlowPanel panel) {
		SelectListBox<SummaryAndAggregatesColumn> value = new SelectListBox<SummaryAndAggregatesColumn>() {
			@Override
			public String getDisplayName(SummaryAndAggregatesColumn item) {
				return item.getDisplayName();
			}
		};
		value.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// TODO
				refreshPanel.refreshChartPanel();

			}
		});

		List<SummaryAndAggregatesColumn> sumlist = new ArrayList<SummaryAndAggregatesColumn>();
		for (SummaryAndAggregatesColumn summary : summaryList) {
			if (summary.isColumn) {
				sumlist.add(summary);
			}
		}
		value.setItems(sumlist);
		int widgetCount = panel.getWidgetCount();
		panel.insert(value, widgetCount);
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
