package com.vimukti.dashboard.client.chart.ui.controls;

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

	public ChartFormatting( ) {
		createControls();
	}

	public ChartFormatting(DashboardComponent chartData) {
		// TODO Auto-generated constructor stub
	}

	private void createControls() {
		sortRowsBy = new SelectListBox();
		maximumValueDisplayed = new TextItem("Maximum Values Displayed");
		axisRange = new SelectListBox();
		legendPosition = new SelectListBox();
		createDataLabelFields();
	}

	private void createDataLabelFields() {
		HorizontalPanel hDataLablePanel = new HorizontalPanel();

		Label dataLabel = new Label("Data Label");
		VerticalPanel vPanel = new VerticalPanel();
		if (type == DashboardComponentType.BAR) {
			CheckBox showChatterPhotos = new CheckBox("Show Chatter Photos");
			vPanel.add(showChatterPhotos);
		}
		CheckBox showDetailsOnHover = new CheckBox("Show Details On Hover");
		if (type == DashboardComponentType.PIE
				|| type == DashboardComponentType.DONUT
				|| type == DashboardComponentType.FUNNEL) {
			CheckBox combineSmallGroupsIntoOthers = new CheckBox(
					"combine Small Groups In to Others");
			vPanel.add(combineSmallGroupsIntoOthers);
			CheckBox showPercentage = new CheckBox("Show %");
			vPanel.add(showPercentage);
		}
		CheckBox showValues = new CheckBox("Show Value");
		vPanel.add(showValues);

		vPanel.add(showDetailsOnHover);

		hDataLablePanel.add(dataLabel);
		hDataLablePanel.add(vPanel);

		this.add(hDataLablePanel);

	}

	private void createTabelControls() {
		minimumValue = new TextItem("Minimum Value");
		minimumValue.addStyleName("minimumvalue");
		lowRangeColor = new ColorItem("Low Range Color");
		lowRangeColor.addStyleName("LowRanangeColor");
		breakPoint1 = new TextItem("Break Point1");
		middleRangeColor = new ColorItem("Middle Range Color");
		breakPoint2 = new TextItem("Break Point2");
		breakPoint2.addStyleName("Break Point");

	}
}
