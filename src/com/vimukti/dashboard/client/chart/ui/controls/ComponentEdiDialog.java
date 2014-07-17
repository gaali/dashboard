package com.vimukti.dashboard.client.chart.ui.controls;

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
import com.vimukti.dashboard.client.reportdata.ReportResults;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.TabControl;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class ComponentEdiDialog extends BaseDialog {
	private ChartFormatting formatting;
	private ChartComponentData data;
	private DashboardComponent chartData;
	private TabLayoutPanel settingsPanel;
	private SimplePanel targetPanel;

	private FlowPanel horizontalBar;
	private FlowPanel vertical;
	private FlowPanel line;
	private FlowPanel pie;
	private FlowPanel donut;
	private FlowPanel funnel;
	private FlowPanel scatter;
	private FlowPanel gauge;
	private FlowPanel metric;
	private FlowPanel tabel;

	public ComponentEdiDialog(DashboardComponent chartData,
			ReportResults reportData) {
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

		data = new ChartComponentData(chartData);
		formatting = new ChartFormatting(chartData);

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

		horizontalBar = new FlowPanel();
		horizontalBar.addStyleName("horizontal-chart");
		horizontalBar.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				horizontalBar.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.BAR);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());
		horizontalBar.addStyleName("horizontalbarchart");

		vertical = new FlowPanel();
		vertical.addStyleName("verticalbarchart");
		vertical.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				vertical.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.COLUMN);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		line = new FlowPanel();
		line.addStyleName("linechart");
		line.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				line.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.LINE);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub
			}
		}, ClickEvent.getType());

		pie = new FlowPanel();
		pie.addStyleName("pie");
		pie.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				pie.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.PIE);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub
			}
		}, ClickEvent.getType());

		donut = new FlowPanel();
		donut.addStyleName("donutChart");
		donut.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				donut.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.DONUT);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		funnel = new FlowPanel();
		funnel.addStyleName("funnelchart");
		funnel.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				funnel.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.FUNNEL);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		scatter = new FlowPanel();
		scatter.addStyleName("sacatterchar");
		scatter.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				scatter.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.SCATTER);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub
			}
		}, ClickEvent.getType());

		gauge = new FlowPanel();
		gauge.addStyleName("gauge");
		gauge.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				gauge.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.GAUGE);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		metric = new FlowPanel();
		metric.addStyleName("metricchart");
		metric.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				metric.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.METRIC);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		tabel = new FlowPanel();
		tabel.addStyleName("tabel");
		tabel.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				tabel.addStyleName("selected-chart");
				chartData.setComponentType(DashboardComponentType.TABLE);
				data.reRendar(chartData);
				formatting.reRender(chartData);
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());

		hPanel.add(selectChart);
		hPanel.add(horizontalBar);
		hPanel.add(vertical);
		hPanel.add(line);
		hPanel.add(pie);
		hPanel.add(donut);
		hPanel.add(funnel);
		hPanel.add(scatter);
		hPanel.add(gauge);
		hPanel.add(metric);
		hPanel.add(tabel);
		this.add(hPanel);
		setSelectedStyleToPanel();
	}

	private void setSelectedStyleToPanel() {

		switch (chartData.getComponentType()) {
		case BAR:
		case BAR_GROUPED:
		case BAR_STACKED:
		case BAR_STACKED100:
			horizontalBar.addStyleName("selected-chart");
			break;
		case COLUMN:
		case COLUMN_GROUPED:
		case COLUMN_LINE:
		case COLUMN_LINE_GROUPED:
		case COLUMN_LINE_STACKED:
		case COLUMN_LINE_STACKED100:
		case COLUMN_STACKED:
		case COLUMN_STACKED100:
			vertical.addStyleName("selected-chart");
			break;
		case DONUT:
			donut.addStyleName("selected-chart");
			break;
		case FUNNEL:
			funnel.addStyleName("selected-chart");
			break;
		case GAUGE:
			gauge.addStyleName("selected-chart");
			break;
		case LINE:
		case LINE_CUMULATIVE:
		case LINE_GROUPED:
		case LINE_GROUPED_CUMULATIVE:
			line.addStyleName("selected-chart");
			break;
		case METRIC:
			metric.addStyleName("selected-chart");
			break;
		case PIE:
			pie.addStyleName("selected-chart");
			break;
		case SCATTER:
		case SCATTER_GROUPED:
			scatter.addStyleName("selected-chart");
			break;
		case TABLE:
			tabel.addStyleName("selected-chart");
			break;
		default:
			break;
		}

	}

	private void clearSelectedStyleName() {
		horizontalBar.removeStyleName("selected-chart");
		vertical.removeStyleName("selected-chart");
		line.removeStyleName("selected-chart");
		pie.removeStyleName("selected-chart");
		donut.removeStyleName("selected-chart");
		funnel.removeStyleName("selected-chart");
		scatter.removeStyleName("selected-chart");
		gauge.removeStyleName("selected-chart");
		metric.removeStyleName("selected-chart");
		tabel.removeStyleName("selected-chart");
	}

	@Override
	protected boolean onOK() {
		update();
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void update() {
		data.update();
		formatting.update();
	}

}
