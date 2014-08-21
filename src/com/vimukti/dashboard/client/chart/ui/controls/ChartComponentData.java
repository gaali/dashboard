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

public class ChartComponentData extends FlowPanel {

	private DashboardComponentType type;
	private FlowPanel groupBy;
	private SelectListBox<ChartUnits> displayUnits;
	private CheckBox cumulative;
	private SelectListBox<ReportGrouping> plotBy;
	private Report result;

	private List<ReportAggregate> aggregates;
	private List<ReportGrouping> groupings;
	private IRefreshChartPanel refreshPanel;
	private List<ColumnInner> summaryList;

	private DashboardComponent component;

	// needCreate object for this field
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

	private void prepareReportData() {
		summaryList = new ArrayList<ColumnInner>();
		List<ReportColumn> columns = result.getColumns();
		for (ReportColumn column : columns) {
			ColumnInner columInner = new ColumnInner();
			columInner.setIsColumn(true);
			String field = column.getField();
			columInner.setColumn(field);
			List<ReportSummaryType> aggregateTypes = column.getAggregateTypes();
			for (ReportSummaryType summaryType : aggregateTypes) {
				String string = summaryType.toString();
				columInner.setName(string + " " + field);
				columInner.setType(summaryType);
				summaryList.add(columInner);
			}
		}

		aggregates = result.getAggregates();
		for (ReportAggregate aggregate : aggregates) {
			ColumnInner columInner = new ColumnInner();
			columInner.setIsColumn(false);
			columInner.setName(aggregate.getMasterLabel());
			columInner.setColumn(aggregate.getMasterLabel());
			summaryList.add(columInner);
		}

		groupings = result.getGroupings();

	}

	public void reRendar() {
		this.clear();
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
			break;
		case SCATTER:
		case SCATTER_GROUPED:
			createControlsForScatter();
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
				String groupingColumn = component.getGroupingColumn();
				if (groupingColumn != null) {
					String[] split = groupingColumn.split(",");
					split[0] = field;
				}
				component.setGroupingColumn(field);
				refreshPanel.refreshChartPanel();
			}
		});
		plotBy.setItems(groupings);
		this.add(plotBy);

		final SelectListBox<ColumnInner> xAxis = new SelectListBox<ColumnInner>() {
			@Override
			public String getDisplayName(ColumnInner item) {
				return item.getName();
			}
		};
		xAxis.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ColumnInner selectedValue = xAxis.getSelectedValue();
				setChartDataToComponent(selectedValue, ChartAxis.Y);
				refreshPanel.refreshChartPanel();
			}
		});
		xAxis.setItems(summaryList);
		this.add(xAxis);

		final SelectListBox<ColumnInner> yAxis = new SelectListBox<ColumnInner>() {
			@Override
			public String getDisplayName(ColumnInner item) {
				return item.getName();
			}
		};
		yAxis.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				ColumnInner selectedValue = yAxis.getSelectedValue();
				setChartDataToComponent(selectedValue, ChartAxis.Y);
				refreshPanel.refreshChartPanel();
			}
		});
		yAxis.setItems(summaryList);
		this.add(yAxis);
	}

	private void createControlsForBarColumnLine() {
		if (type == DashboardComponentType.COLUMN
				|| type == DashboardComponentType.LINE) {

			final SelectListBox<ColumnInner> yAxis = new SelectListBox<ColumnInner>() {
				@Override
				public String getDisplayName(ColumnInner item) {
					return item.getName();
				}
			};
			yAxis.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					ColumnInner selectedValue = yAxis.getSelectedValue();
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
		} else if (type == DashboardComponentType.BAR
				|| type == DashboardComponentType.SCATTER) {
			final SelectListBox<ColumnInner> xAxis = new SelectListBox<ColumnInner>() {
				@Override
				public String getDisplayName(ColumnInner item) {
					return item.getName();
				}
			};
			xAxis.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					ColumnInner selectedValue = xAxis.getSelectedValue();
					setChartDataToComponent(selectedValue, ChartAxis.X);
					refreshPanel.refreshChartPanel();
				}
			});
			xAxis.setItems(summaryList);
			this.add(xAxis);
			final SelectListBox<ReportGrouping> yAxis = new SelectListBox<ReportGrouping>() {
				@Override
				public String getDisplayName(ReportGrouping item) {
					return item.getField();
				}
			};
			yAxis.addChangeHandler(new ChangeHandler() {

				@Override
				public void onChange(ChangeEvent event) {
					ReportGrouping selectedValue = yAxis.getSelectedValue();
					String field = selectedValue.getField();
					setGroupingColumnToComponent(field);
					refreshPanel.refreshChartPanel();
				}
			});
			yAxis.setItems(groupings);
			this.add(yAxis);
		}
		if (type == DashboardComponentType.LINE) {
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
					setGroupingColumnToComponent(field);
					refreshPanel.refreshChartPanel();
				}
			});
			groupByListBox.addStyleName("groupByList");
			this.add(groupByListBox);
		} else if (type == DashboardComponentType.BAR
				|| type == DashboardComponentType.COLUMN) {
			createGroupByPanel(type);
		}
		if (type == DashboardComponentType.LINE) {
			cumulative = new CheckBox("Cumulative");
			cumulative.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					if (cumulative.getValue()) {
						component
								.setComponentType(DashboardComponentType.LINE_CUMULATIVE);
					} else {
						component.setComponentType(DashboardComponentType.LINE);
					}
				}
			});

			cumulative.addStyleName("cumulative");
			if (type == DashboardComponentType.LINE_CUMULATIVE) {
				cumulative.setValue(true);
			}
		}
		if (type == DashboardComponentType.COLUMN
				|| type == DashboardComponentType.LINE
				|| type == DashboardComponentType.BAR) {
			createCombinationChartsPanel();
		}
	}

	private void setGroupingColumnToComponent(String field) {
		String groupingColumn = component.getGroupingColumn();
		String groupingColumnString = "";
		if (groupingColumn != null) {
			String[] split = groupingColumn.split(",");
			split[0] = field;
			for (String groupinString : split) {
				groupingColumnString = groupinString;
				if (split.length == 2) {
					groupingColumnString = groupingColumnString + ",";
				}
			}
		} else {
			groupingColumnString = field;
		}
		component.setGroupingColumn(groupingColumnString);
	}

	private void setChartDataToComponent(ColumnInner selectedValue,
			ChartAxis axis) {
		String column = selectedValue.getColumn();
		ReportSummaryType type2 = selectedValue.getType();

		ChartSummary chartSummary = component.getChartSummary();
		chartSummary.setAggregate(type2);
		if (axis != null) {
			chartSummary.setAxisBinding(axis);
		}
		chartSummary.setColumn(column);
	}

	private void pieDonutFunnelChartType() {
		final SelectListBox<ColumnInner> values = new SelectListBox<ColumnInner>(
				"values") {
			@Override
			public String getDisplayName(ColumnInner item) {
				return item.getName();
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

		if (type != DashboardComponentType.FUNNEL) {
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
		} else {
			SelectListBox<Object> segments = new SelectListBox<Object>(
					"Segments");
			segments.addStyleName("segmets-list");
		}
	}

	private void gaugeMetricChart() {
		final SelectListBox<ColumnInner> value = new SelectListBox<ColumnInner>(
				"value") {
			@Override
			public String getDisplayName(ColumnInner item) {
				return item.getName();
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

	private void createGroupByPanel(DashboardComponentType type) {
		groupBy = new FlowPanel();

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
				setGroupingColumnToComponent(field);
				refreshPanel.refreshChartPanel();
			}
		});
		box.addStyleName("groupby-listbox");
		FlowPanel fPanel = new FlowPanel();

		FlowPanel sideBySide = new FlowPanel();
		sideBySide.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DashboardComponentType dType = null;
				dType = DashboardComponentType.getComponentType("Bar") != null ? DashboardComponentType.BAR_GROUPED
						: DashboardComponentType.COLUMN_GROUPED;
				component.setComponentType(dType);
				refreshPanel.refreshChartPanel();

			}
		}, ClickEvent.getType());
		sideBySide.addStyleName("sidebyside-" + type.toString().toLowerCase());

		FlowPanel stacked = new FlowPanel();
		stacked.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DashboardComponentType dType = null;
				if (DashboardComponentType.getComponentType("Bar") != null) {
					dType = DashboardComponentType.BAR_STACKED;
				} else {
					dType = DashboardComponentType.COLUMN_STACKED;
				}
				component.setComponentType(dType);
				refreshPanel.refreshChartPanel();

			}
		}, ClickEvent.getType());
		stacked.addStyleName("stacked-" + type.toString().toLowerCase());

		FlowPanel fullStacked = new FlowPanel();
		fullStacked.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DashboardComponentType dType = null;
				if (DashboardComponentType.getComponentType("Bar") != null) {
					dType = DashboardComponentType.BAR_STACKED100;
				} else {
					dType = DashboardComponentType.COLUMN_STACKED100;
				}
				component.setComponentType(dType);
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
		SelectListBox<ColumnInner> value = new SelectListBox<ColumnInner>() {
			@Override
			public String getDisplayName(ColumnInner item) {
				return item.getName();
			}
		};
		value.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// TODO
				refreshPanel.refreshChartPanel();

			}
		});

		List<ColumnInner> sumlist = new ArrayList<ColumnInner>();
		for (ColumnInner summary : summaryList) {
			if (summary.isColumn) {
				sumlist.add(summary);
			}
		}
		value.setItems(sumlist);
		int widgetCount = panel.getWidgetCount();
		panel.insert(value, widgetCount);
	}

	class ColumnInner {
		String displayName;
		boolean isColumn;
		ReportSummaryType type;
		String column;

		/**
		 * @return the name
		 */
		public String getName() {
			return displayName;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
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
