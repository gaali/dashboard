package com.vimukti.dashboard.client.chart.ui.controls;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.vimukti.dashboard.client.colorPicker.ColorItem;
import com.vimukti.dashboard.client.data.ChartLegendPosition;
import com.vimukti.dashboard.client.data.ChartRangeType;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentFilter;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.reportdata.Report;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class ChartFormatting extends FlowPanel {
	/**
	 * Chart or component type
	 */
	DashboardComponentType type;

	/**
	 * Sort rows for charts assending or desending
	 */
	private SelectListBox<DashboardComponentFilter> sortRowsBy;

	/**
	 * it takes number value to show max value in chart,value is up to 99 only
	 */
	private TextItem maximumValueDisplayed;
	/**
	 * axis range for the up value from and to values can enter
	 */
	private SelectListBox<ChartRangeType> axisRange;

	/**
	 * legend position in chart this field is not available for all chars
	 */
	private SelectListBox<ChartLegendPosition> legendPosition;

	/**
	 * if chart control vaues changed preview need to refresh by calling rpc
	 */
	private IRefreshChartPanel refreshPanel;

	/**
	 * minimum value to show in chart
	 */
	private TextItem minimumValue;

	/**
	 * The color representing a low number range on the gauge.
	 */
	private ColorItem lowRangeColor;

	/**
	 * The value that separates the indicatorLowColor from the
	 * indicatorMiddleColor on the dashboard.
	 */
	private TextItem breakPoint1;
	/**
	 * The color representing a mid number range on the gauge.
	 */
	private ColorItem middleRangeColor;
	/**
	 * The value that separates the indicatorLowColor from the
	 * indicatorMiddleColor on the dashboard.
	 */
	private TextItem breakPoint2;
	/**
	 * The color representing a high number range on the gauge.
	 */
	private ColorItem highRangeColor;
	/**
	 * maximum value of the chart to show
	 */
	private TextItem maximum;

	private double minValue;
	private double point1Value;
	private double point2Value;
	private double maxValue;

	private DashboardComponent component;

	public ChartFormatting(DashboardComponent component, Report reportData,
			IRefreshChartPanel refreshPanel) {
		this.component = component;
		this.refreshPanel = refreshPanel;
		type = component.getComponentType();
		createControls();
	}

	public void reRender() {
		this.clear();
		type = component.getComponentType();
		createControls();
		if (type == DashboardComponentType.GAUGE
				|| type == DashboardComponentType.TABLE
				|| type == DashboardComponentType.METRIC) {
			createTabelControls();
		}
	}

	private void createControls() {
		sortByMaximumValue();
		if (type == DashboardComponentType.BAR
				|| type == DashboardComponentType.COLUMN
				|| type == DashboardComponentType.LINE
				|| type == DashboardComponentType.SCATTER) {

			axisRange = new SelectListBox<ChartRangeType>("Axis-Range");
			ChartRangeType[] values2 = ChartRangeType.values();
			List<ChartRangeType> asList2 = Arrays.asList(values2);
			axisRange.setItems(asList2);
			if (axisRange.getSelectedValue() == ChartRangeType.MANUAL) {
				final TextItem from = new TextItem("From:");
				from.addStyleName("axis-from-textitem");
				from.addBlurHandler(new BlurHandler() {

					@Override
					public void onBlur(BlurEvent event) {
						ChartRangeType selectedValue = axisRange
								.getSelectedValue();

						component.setChartAxisRange(selectedValue);
						String value = from.getValue();
						try {
							double parseDouble = Double.parseDouble(value);
							component.setChartAxisRangeMin(parseDouble);
						} catch (Exception e) {
							// TODO: handle exception
							// show an error under the textbox
						}
						refreshPanel.refreshChartPanel();
					}
				});
				final TextItem to = new TextItem("To:");
				to.addStyleName("axis-to-textitem");
				to.addBlurHandler(new BlurHandler() {

					@Override
					public void onBlur(BlurEvent event) {
						String value = to.getValue();
						String value2 = from.getValue();

						try {
							double fromValue = Double.parseDouble(value);
							double toValue = Double.parseDouble(value2);
							component.setChartAxisRangeMax(toValue);
							if (fromValue >= toValue) {
								// show an error under the textbox
							}
						} catch (Exception e) {
							// TODO: handle exception
							// show an error under the textbox
						}
						refreshPanel.refreshChartPanel();
					}
				});
			}
			ChartRangeType chartAxisRange = component.getChartAxisRange();
			axisRange.setSelectedValue(chartAxisRange);
			this.add(axisRange);
		}

		legendPosition = new SelectListBox<ChartLegendPosition>();
		ChartLegendPosition[] values3 = ChartLegendPosition.values();
		List<ChartLegendPosition> asList3 = Arrays.asList(values3);
		legendPosition.setItems(asList3);
		legendPosition.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				ChartLegendPosition selectedValue = legendPosition
						.getSelectedValue();
				component.setLegendPosition(selectedValue);
				refreshPanel.refreshChartPanel();
			}
		});
		ChartLegendPosition legendPosition2 = component.getLegendPosition();
		legendPosition.setSelectedValue(legendPosition2);
		this.add(legendPosition);
		if (type == DashboardComponentType.BAR
				|| type == DashboardComponentType.COLUMN
				|| type == DashboardComponentType.LINE) {
			legendPosition.setEnabled(false);
		}

		createDataLabelFields();
	}

	public void sortByMaximumValue() {
		sortRowsBy = new SelectListBox<DashboardComponentFilter>("Sort Rows By");
		DashboardComponentFilter[] values = DashboardComponentFilter.values();
		List<DashboardComponentFilter> asList = Arrays.asList(values);
		sortRowsBy.setItems(asList);
		sortRowsBy.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				DashboardComponentFilter selectedValue = sortRowsBy
						.getSelectedValue();
				component.setSortBy(selectedValue);
				refreshPanel.refreshChartPanel();
			}
		});
		DashboardComponentFilter sortBy = component.getSortBy();
		sortRowsBy.setSelectedValue(sortBy);
		this.add(sortRowsBy);

		maximumValueDisplayed = new TextItem("Maximum Values Displayed");
		maximumValueDisplayed.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String value = maximumValueDisplayed.getValue();
				try {
					int maxDisplayedValue = Integer.parseInt(value);
					if (maxDisplayedValue > 100) {
						// show an error under the text box
					}
					component.setMaxValuesDisplayed(maxDisplayedValue);
					refreshPanel.refreshChartPanel();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		int maxValuesDispld = component.getMaxValuesDisplayed();
		maximumValueDisplayed.setText(String.valueOf(maxValuesDispld));
		this.add(maximumValueDisplayed);
	}

	private void createDataLabelFields() {
		FlowPanel hDataLablePanel = new FlowPanel();
		Label dataLabel = new Label("Data Label");
		FlowPanel vPanel = new FlowPanel();
		if (type == DashboardComponentType.PIE
				|| type == DashboardComponentType.DONUT
				|| type == DashboardComponentType.FUNNEL) {
			final CheckBox combineSmallGroupsIntoOthers = new CheckBox(
					"Combine Small groups into 'Others'");
			combineSmallGroupsIntoOthers
					.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
						@Override
						public void onValueChange(
								ValueChangeEvent<Boolean> event) {
							// TODO Auto-generated method stub
							Boolean value = combineSmallGroupsIntoOthers
									.getValue();
							refreshPanel.refreshChartPanel();
						}
					});
			vPanel.add(combineSmallGroupsIntoOthers);
		}
		if (type == DashboardComponentType.BAR) {
			final CheckBox showChatterPhotos = new CheckBox(
					"Show Chatter Photos");
			showChatterPhotos
					.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

						@Override
						public void onValueChange(
								ValueChangeEvent<Boolean> event) {
							Boolean value = showChatterPhotos.getValue();
							component.setShowPicturesOnCharts(value);
							refreshPanel.refreshChartPanel();
						}
					});
			showChatterPhotos.setValue(component.isShowPicturesOnCharts());
			vPanel.add(showChatterPhotos);
		}

		if (type != DashboardComponentType.SCATTER) {
			final CheckBox showValues = new CheckBox("Show Value");
			showValues.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					Boolean value = showValues.getValue();
					component.setShowValues(value);
					refreshPanel.refreshChartPanel();
				}
			});
			boolean showValues2 = component.isShowValues();
			showValues.setValue(showValues2);
			vPanel.add(showValues);
		}

		if (type == DashboardComponentType.PIE
				|| type == DashboardComponentType.DONUT
				|| type == DashboardComponentType.FUNNEL
				|| type == DashboardComponentType.GAUGE) {
			final CheckBox showPercentage = new CheckBox("Show %");
			showPercentage
					.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

						@Override
						public void onValueChange(
								ValueChangeEvent<Boolean> event) {
							Boolean value = showPercentage.getValue();
							component.setShowPercentage(value);
							refreshPanel.refreshChartPanel();
						}
					});
			boolean showPercentage2 = component.isShowPercentage();
			showPercentage.setValue(showPercentage2);
			vPanel.add(showPercentage);
		}

		final CheckBox showDetailsOnHover = new CheckBox(
				"Show Details On Hover");
		showDetailsOnHover
				.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

					@Override
					public void onValueChange(ValueChangeEvent<Boolean> event) {
						Boolean value = showDetailsOnHover.getValue();
						component.setEnableHover(value);
						refreshPanel.refreshChartPanel();
					}
				});
		boolean enableHover = component.isEnableHover();
		showDetailsOnHover.setValue(enableHover);
		vPanel.add(showDetailsOnHover);

		if (type == DashboardComponentType.DONUT
				|| type == DashboardComponentType.GAUGE) {
			final CheckBox showTotal = new CheckBox("Show Total");
			showTotal.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					Boolean value = showTotal.getValue();
					component.setShowTotal(value);
					refreshPanel.refreshChartPanel();
				}
			});
			boolean showTotal2 = component.isShowTotal();
			showTotal.setValue(showTotal2);
			vPanel.add(showTotal);
		}

		hDataLablePanel.add(dataLabel);
		hDataLablePanel.add(vPanel);

		this.add(hDataLablePanel);

	}

	private void createTabelControls() {

		if (type == DashboardComponentType.TABLE) {
			sortByMaximumValue();
			final CheckBox chatterPhoto = new CheckBox();
			chatterPhoto.addStyleName("chatter-photo");
			chatterPhoto
					.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

						@Override
						public void onValueChange(
								ValueChangeEvent<Boolean> event) {
							Boolean value = chatterPhoto.getValue();
							// TODO no field to found ,in DashboardComponent
							refreshPanel.refreshChartPanel();
						}
					});
			chatterPhoto.setValue(component.isShowPicturesOnTables());
			this.add(chatterPhoto);
		}
		if (type == DashboardComponentType.GAUGE) {
			minimumValue = new TextItem("Minimum Value");
			minimumValue.addStyleName("minimumvalue");
			minimumValue.addBlurHandler(new BlurHandler() {

				@Override
				public void onBlur(BlurEvent event) {
					String value = minimumValue.getValue();
					try {
						minValue = Double.parseDouble(value);
						if (minValue < point1Value && point1Value < point2Value
								&& point2Value < maxValue) {
							component.setGaugeMin(minValue);
						} else {
							// show an error
						}
					} catch (Exception e) {
						// TODO: handle exception
						// show an error under the text box
					}
					refreshPanel.refreshChartPanel();
				}
			});
			double gaugeMin = component.getGaugeMin();
			minimumValue.setValue(String.valueOf(gaugeMin));
			this.add(minimumValue);
		}

		lowRangeColor = new ColorItem("Low Range Color");
		lowRangeColor.addStyleName("lowranangecolor");
		lowRangeColor.addDomHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				String color = lowRangeColor.getColor();
				component.setIndicatorLowColor(color);
				refreshPanel.refreshChartPanel();
			}
		}, ChangeEvent.getType());
		String indicatorLowColor = component.getIndicatorLowColor();
		lowRangeColor.setColor(indicatorLowColor);
		this.add(lowRangeColor);

		breakPoint1 = new TextItem("Break Point1");
		breakPoint1.addStyleName("breakpoint1");
		breakPoint1.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				// TODO Auto-generated method stub
				String value = breakPoint1.getValue();
				try {
					double parseDouble = Double.parseDouble(value);
					// TODO validation
					if (minValue < point1Value && point1Value < point2Value
							&& point2Value < maxValue) {
						component.setIndicatorBreakpoint1(parseDouble);
					} else {
						// show an error
					}

				} catch (Exception e) {
					// TODO: handle exception
					// show an error under text box
				}
				refreshPanel.refreshChartPanel();
			}
		});
		double indicatorBreakpoint1 = component.getIndicatorBreakpoint1();
		breakPoint1.setValue(String.valueOf(indicatorBreakpoint1));
		this.add(breakPoint1);

		middleRangeColor = new ColorItem("Middle Range Color");
		middleRangeColor.addStyleName("middlerangecolor");
		middleRangeColor.addDomHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				String color = middleRangeColor.getColor();
				component.setIndicatorMiddleColor(color);
				refreshPanel.refreshChartPanel();
			}
		}, ChangeEvent.getType());
		String indicatorMiddleColor = component.getIndicatorMiddleColor();
		middleRangeColor.setColor(indicatorMiddleColor);
		this.add(middleRangeColor);

		breakPoint2 = new TextItem("Break Point2");
		breakPoint2.addStyleName("breakpoint2");
		breakPoint2.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				// TODO Auto-generated method stub
				String value = breakPoint2.getValue();
				try {
					double parseDouble = Double.parseDouble(value);
					if (minValue < point1Value && point1Value < point2Value
							&& point2Value < maxValue) {
						component.setIndicatorBreakpoint2(parseDouble);
					} else {
						// show an error
					}

				} catch (Exception e) {
					// TODO: handle exception
					// show an error under the text box
				}
				refreshPanel.refreshChartPanel();
			}
		});
		double indicatorBreakpoint2 = component.getIndicatorBreakpoint2();
		breakPoint2.setValue(String.valueOf(indicatorBreakpoint2));
		this.add(breakPoint2);

		highRangeColor = new ColorItem("Hight Range Color");
		highRangeColor.addStyleName("highrangecolor");
		highRangeColor.addDomHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				String color = highRangeColor.getColor();
				component.setIndicatorHighColor(color);
				refreshPanel.refreshChartPanel();
			}
		}, ChangeEvent.getType());

		highRangeColor.setColor(component.getIndicatorHighColor());
		// TODO add value change handler
		this.add(highRangeColor);

		if (type == DashboardComponentType.GAUGE) {
			maximum = new TextItem("Maximum");
			maximum.addStyleName("maximum");
			maximum.addBlurHandler(new BlurHandler() {

				@Override
				public void onBlur(BlurEvent event) {
					// TODO Auto-generated method stub
					String value = maximum.getValue();
					try {
						double parseDouble = Double.parseDouble(value);
						if (minValue < point1Value && point1Value < point2Value
								&& point2Value < maxValue) {
							component.setGaugeMax(parseDouble);
						} else {
							// show an error
						}
					} catch (Exception e) {
						// TODO: handle exception
						// show an error under the text box
					}
					refreshPanel.refreshChartPanel();
				}
			});
			maximum.setValue(String.valueOf(component.getGaugeMax()));
			this.add(maximum);
		}
		if (type == DashboardComponentType.GAUGE) {
			createDataLabelFields();
		}

	}

}
