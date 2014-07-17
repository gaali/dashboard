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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.colorPicker.ColorItem;
import com.vimukti.dashboard.client.data.ChartLegendPosition;
import com.vimukti.dashboard.client.data.ChartRangeType;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentFilter;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class ChartFormatting extends FlowPanel {
	DashboardComponentType type;
	private SelectListBox<DashboardComponentFilter> sortRowsBy;
	private TextItem maximumValueDisplayed;
	private SelectListBox<ChartRangeType> axisRange;
	private SelectListBox<ChartLegendPosition> legendPosition;
	private VerticalPanel dataLabelPanel;

	private TextItem minimumValue;
	private ColorItem lowRangeColor;
	private TextItem breakPoint1;
	private ColorItem middleRangeColor;
	private TextItem breakPoint2;
	private ColorItem highRangeColor;
	private TextItem maximum;

	private DashboardComponent component;

	public ChartFormatting(DashboardComponent component) {
		// TODO Auto-generated constructor stub
		this.component = component;
		type = component.getComponentType();
		createControls();
	}

	public void reRender(DashboardComponent component) {
		this.clear();
		this.component = component;
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
				TextItem from = new TextItem("From:");
				from.addStyleName("axis-from-textitem");
				from.addBlurHandler(new BlurHandler() {

					@Override
					public void onBlur(BlurEvent event) {
						// TODO Auto-generated method stub

					}
				});
				TextItem to = new TextItem("To:");
				to.addStyleName("axis-to-textitem");
				to.addBlurHandler(new BlurHandler() {

					@Override
					public void onBlur(BlurEvent event) {
						// TODO Auto-generated method stub

					}
				});
			}
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

			}
		});
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
				// TODO Auto-generated method stub
				// change Chart Preview and if need Call Rpc
				// change chart with changed value
			}
		});
		this.add(sortRowsBy);

		maximumValueDisplayed = new TextItem("Maximum Values Displayed");
		maximumValueDisplayed.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				// TODO Auto-generated method stub
				// change Chart Preview here with given value.
				// accept only numbers and accept up to 99 only
			}
		});
		this.add(maximumValueDisplayed);
	}

	private void createDataLabelFields() {

		HorizontalPanel hDataLablePanel = new HorizontalPanel();

		Label dataLabel = new Label("Data Label");
		VerticalPanel vPanel = new VerticalPanel();

		if (type == DashboardComponentType.PIE
				|| type == DashboardComponentType.DONUT
				|| type == DashboardComponentType.FUNNEL) {
			CheckBox combineSmallGroupsIntoOthers = new CheckBox(
					"Combine Small groups into 'Others'");
			combineSmallGroupsIntoOthers
					.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

						@Override
						public void onValueChange(
								ValueChangeEvent<Boolean> event) {
							// TODO Auto-generated method stub

						}
					});
			vPanel.add(combineSmallGroupsIntoOthers);
		}
		if (type == DashboardComponentType.BAR) {
			CheckBox showChatterPhotos = new CheckBox("Show Chatter Photos");
			showChatterPhotos
					.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

						@Override
						public void onValueChange(
								ValueChangeEvent<Boolean> event) {
							// TODO Auto-generated method stub

						}
					});
			vPanel.add(showChatterPhotos);
		}

		if (type != DashboardComponentType.SCATTER) {
			CheckBox showValues = new CheckBox("Show Value");
			showValues.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					// TODO Auto-generated method stub

				}
			});
			vPanel.add(showValues);
		}

		if (type == DashboardComponentType.PIE
				|| type == DashboardComponentType.DONUT
				|| type == DashboardComponentType.FUNNEL
				|| type == DashboardComponentType.GAUGE) {
			CheckBox showPercentage = new CheckBox("Show %");
			vPanel.add(showPercentage);
		}

		CheckBox showDetailsOnHover = new CheckBox("Show Details On Hover");
		vPanel.add(showDetailsOnHover);

		if (type == DashboardComponentType.DONUT
				|| type == DashboardComponentType.GAUGE) {
			CheckBox showTotal = new CheckBox("Show Total");
			showTotal.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					// TODO Auto-generated method stub

				}
			});
			vPanel.add(showTotal);
		}

		hDataLablePanel.add(dataLabel);
		hDataLablePanel.add(vPanel);

		this.add(hDataLablePanel);

	}

	private void createTabelControls() {
		if (type == DashboardComponentType.TABLE) {
			sortByMaximumValue();
			CheckBox chatterPhoto = new CheckBox();
			chatterPhoto.addStyleName("chatter-photo");
			chatterPhoto
					.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

						@Override
						public void onValueChange(
								ValueChangeEvent<Boolean> event) {
							// TODO Auto-generated method stub

						}
					});
			this.add(chatterPhoto);
		}
		if (type == DashboardComponentType.GAUGE) {
			minimumValue = new TextItem("Minimum Value");
			minimumValue.addStyleName("minimumvalue");
			minimumValue.addBlurHandler(new BlurHandler() {

				@Override
				public void onBlur(BlurEvent event) {
					// TODO Auto-generated method stub

				}
			});
			this.add(minimumValue);
		}

		lowRangeColor = new ColorItem("Low Range Color");
		lowRangeColor.addStyleName("lowranangecolor");
		// TODO add Change Handler
		this.add(lowRangeColor);

		breakPoint1 = new TextItem("Break Point1");
		breakPoint1.addStyleName("breakpoint1");
		breakPoint1.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				// TODO Auto-generated method stub

			}
		});
		this.add(breakPoint1);

		middleRangeColor = new ColorItem("Middle Range Color");
		middleRangeColor.addStyleName("middlerangecolor");
		// TODO add change Handler
		this.add(middleRangeColor);

		breakPoint2 = new TextItem("Break Point2");
		breakPoint2.addStyleName("breakpoint2");
		breakPoint2.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				// TODO Auto-generated method stub

			}
		});
		this.add(breakPoint2);

		highRangeColor = new ColorItem("Hight Range Color");
		highRangeColor.addStyleName("highrangecolor");
		// TODO add value change handler
		this.add(highRangeColor);

		if (type == DashboardComponentType.GAUGE) {
			maximum = new TextItem("Maximum");
			maximum.addStyleName("maximum");
			maximum.addBlurHandler(new BlurHandler() {

				@Override
				public void onBlur(BlurEvent event) {
					// TODO Auto-generated method stub

				}
			});
			this.add(maximum);
		}

		if (type == DashboardComponentType.GAUGE) {
			createDataLabelFields();
		}

	}

	public void update() {

	}
}
