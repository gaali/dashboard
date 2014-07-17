package com.vimukti.dashboard.client.chart.ui.controls;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.colorPicker.ColorItem;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class ChartFormattingPanel2 extends FlowPanel {

	private SelectListBox<Object> sortRowsBy;
	private TextItem maximumValueDisplayed;
	private CheckBox showChartPhoto;

	private TextItem minimumValue;
	private ColorItem lowRangeColor;
	private TextItem breakPoint1;
	private ColorItem middleRangeColor;
	private TextItem breakPoint2;
	private ColorItem highRangeColor;
	private TextItem maximum;
	DashboardComponentType type;

	public void createControls() {
		sortRowsBy = new SelectListBox();
		maximumValueDisplayed = new TextItem("Maximum Value Displayed");
		showChartPhoto = new CheckBox("Show Chart Photo");
		if (type != DashboardComponentType.METRIC) {
			minimumValue = new TextItem("Minimum Value");
			this.add(minimumValue);
		}
		lowRangeColor = new ColorItem("Low Range Color");
		breakPoint1 = new TextItem("Break Point");
		middleRangeColor = new ColorItem("Middle Range Color");
		breakPoint2 = new TextItem("Break Point2");
		highRangeColor = new ColorItem("High Range Color");
		maximum = new TextItem("Maximum");
		createDataLabelPanel();

		this.add(lowRangeColor);
		this.add(middleRangeColor);
		this.add(breakPoint2);
		this.add(highRangeColor);
		this.add(maximum);
	}

	private void createDataLabelPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		Label dataLabels = new Label("Data labels");
		VerticalPanel vPanel = new VerticalPanel();
		CheckBox showPercentage = new CheckBox("Show %");
		CheckBox showTotal = new CheckBox("Show Total");
		vPanel.add(showPercentage);
		vPanel.add(showTotal);
		panel.add(dataLabels);
		this.add(panel);
	}

}
