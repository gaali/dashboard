package com.vimukti.dashboard.chart.ui.controls;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.TabControl;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class ComponentEdiDialog extends BaseDialog {
	private ChartComponentData data;
	private ChartFormatting formatting;
	private DashboardComponent chartData;
	private TabLayoutPanel settingsPanel;
	private SimplePanel targetPanel;

	public ComponentEdiDialog(DashboardComponent chartData) {
		this.chartData = chartData;
		this.addStyleName("chart-settings-dialog");
	}

	@Override
	protected void createControls() {
		super.createControls();
		createChartTypesPanel();
		CreateChartDataAndChartPanel();
	}

	public void pageTypeComponentSettings() {
		TextItem heightOfpag = new TextItem("Height (in pixels)");
		heightOfpag.addStyleName("height-textbox");
		TabControl tab = new TabControl();
		tab.addTab("Formatting", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void CreateChartDataAndChartPanel() {
		HorizontalPanel hPanel = new HorizontalPanel();

		settingsPanel = new TabLayoutPanel(1.5, Unit.EM);
		settingsPanel.addStyleName("settings-tabLayouts");
		settingsPanel.add(data, "Component Data");
		settingsPanel.add(formatting, "Formatting");

		targetPanel = new SimplePanel();

		TabControl tabs = new TabControl();
		tabs.addTab("Component Data", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				targetPanel.setWidget(data);

			}
		});

		tabs.addTab("Formatting", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				targetPanel.setWidget(formatting);
			}
		});

		FlowPanel chartPreview = createChartPreviewPanel();
		chartPreview.addStyleName("chartpreview");
		hPanel.add(chartPreview);
		hPanel.add(targetPanel);

		this.add(hPanel);
	}

	private FlowPanel createChartPreviewPanel() {
		// TODO
		return null;
	}

	private void createChartTypesPanel() {
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.addStyleName("chart-types-panel");

		Label selectChart = new Label("Select Type:");
		selectChart.addStyleName("lable-selectChart");

		final FlowPanel horizontal = new FlowPanel();
		horizontal.addStyleName("horizontal-chart");
		horizontal.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				horizontal.addStyleName("selectedChart");
				targetPanel.setWidget(settingsPanel);

			}
		}, ClickEvent.getType());
		horizontal.addStyleName("horizontalbarchart");

		final FlowPanel vertical = new FlowPanel();
		vertical.addStyleName("verticalbarchart");
		vertical.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				vertical.addStyleName("selectedChart");

			}
		}, ClickEvent.getType());

		final FlowPanel line = new FlowPanel();
		line.addStyleName("linechart");
		line.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				line.addStyleName("selectedChart");
			}
		}, ClickEvent.getType());

		final FlowPanel pie = new FlowPanel();
		pie.addStyleName("pie");
		pie.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				pie.addStyleName("seletedChart");
			}
		}, ClickEvent.getType());

		FlowPanel donut = new FlowPanel();
		donut.addStyleName("donutChart");
		donut.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		FlowPanel funnel = new FlowPanel();
		funnel.addStyleName("funnelchart");
		funnel.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		FlowPanel scatter = new FlowPanel();
		scatter.addStyleName("sacatterchar");
		scatter.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		FlowPanel gauge = new FlowPanel();
		gauge.addStyleName("gauge");
		gauge.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		FlowPanel metric = new FlowPanel();
		metric.addStyleName("metricchart");
		metric.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		FlowPanel tabel = new FlowPanel();
		tabel.addStyleName("tabel");
		tabel.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		hPanel.add(selectChart);
		hPanel.add(horizontal);
		hPanel.add(vertical);
		hPanel.add(line);
		hPanel.add(pie);
		hPanel.add(donut);
		hPanel.add(funnel);
		hPanel.add(scatter);
		hPanel.add(gauge);
		hPanel.add(metric);
		hPanel.add(tabel);

		DashboardComponentType componentType = chartData.getComponentType();
		componentType = componentType == null ? DashboardComponentType.BAR
				: componentType;

		this.add(hPanel);
	}

	private void componentPanelControls() {
		FlowPanel componentPanel = new FlowPanel();

	}

	private void formattingPanel() {
		FlowPanel formattingPanel = new FlowPanel();

	}

	@Override
	protected boolean onOK() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
