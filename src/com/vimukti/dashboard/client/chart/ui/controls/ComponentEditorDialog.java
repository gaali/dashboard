package com.vimukti.dashboard.client.chart.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.reportdata.Report;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.TabControl;
import com.vimukti.dashboard.client.ui.utils.TextItem;

/**
 * this edit portlet chart type
 *
 */
@SuppressWarnings("rawtypes")
public abstract class ComponentEditorDialog extends BaseDialog {
	/**
	 * this panel controls formats the chart position of legend axis range
	 * colors of chart
	 */
	private ChartFormatting formatting;

	/**
	 * this panel controls gives which data show in charts
	 */
	private ChartComponentData data;

	/**
	 * the data which is shown in charts
	 */
	private Report reportData;

	private String chartHtml;

	/**
	 * component data will save in this object
	 */
	protected DashboardComponent componentData;

	public static final String GET_CHART_HTML = "/dashboard/chartpreview";

	/**
	 * these all flow panels are chart(component) types
	 */
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
	private FlowPanel previewPanel = new FlowPanel();

	public ComponentEditorDialog(DashboardComponent componentData,
			Report reportData) {
		this.componentData = componentData;
		this.reportData = reportData;
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
		FlowPanel hPanel = new FlowPanel();

		data = new ChartComponentData(componentData, reportData,
				new IRefreshChartPanel() {

					@Override
					public void refreshChartPanel() {
						ComponentEditorDialog.this.refreshChartPanel();
						createChartPreviewPanel();
					}
				});
		formatting = new ChartFormatting(componentData, reportData,
				new IRefreshChartPanel() {

					@Override
					public void refreshChartPanel() {
						ComponentEditorDialog.this.refreshChartPanel();
						createChartPreviewPanel();
					}
				});
		final SimplePanel targetPanel = new SimplePanel();

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

	/**
	 * creating chart preview panel
	 */
	private FlowPanel createChartPreviewPanel() {
		previewPanel.addStyleName("preview-panel");
		previewPanel.clear();
		HTML html = new HTML(chartHtml);
		previewPanel.add(html);
		return previewPanel;
	}

	/**
	 * creating chart type
	 */
	private void createChartTypesPanel() {
		FlowPanel hPanel = new FlowPanel();
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
				componentData.setComponentType(DashboardComponentType.BAR);
				data.reRendar();
				formatting.reRender();
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
				componentData.setComponentType(DashboardComponentType.COLUMN);
				data.reRendar();
				formatting.reRender();
			}
		}, ClickEvent.getType());

		line = new FlowPanel();
		line.addStyleName("linechart");
		line.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				line.addStyleName("selected-chart");
				componentData.setComponentType(DashboardComponentType.LINE);
				data.reRendar();
				formatting.reRender();
			}
		}, ClickEvent.getType());

		pie = new FlowPanel();
		pie.addStyleName("pie");
		pie.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				pie.addStyleName("selected-chart");
				componentData.setComponentType(DashboardComponentType.PIE);
				data.reRendar();
				formatting.reRender();
			}
		}, ClickEvent.getType());

		donut = new FlowPanel();
		donut.addStyleName("donutChart");
		donut.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				donut.addStyleName("selected-chart");
				componentData.setComponentType(DashboardComponentType.DONUT);
				data.reRendar();
				formatting.reRender();
			}
		}, ClickEvent.getType());

		funnel = new FlowPanel();
		funnel.addStyleName("funnelchart");
		funnel.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				funnel.addStyleName("selected-chart");
				componentData.setComponentType(DashboardComponentType.FUNNEL);
				data.reRendar();
				formatting.reRender();
			}
		}, ClickEvent.getType());

		scatter = new FlowPanel();
		scatter.addStyleName("sacatterchar");
		scatter.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				scatter.addStyleName("selected-chart");
				componentData.setComponentType(DashboardComponentType.SCATTER);
				data.reRendar();
				formatting.reRender();
			}
		}, ClickEvent.getType());

		gauge = new FlowPanel();
		gauge.addStyleName("gauge");
		gauge.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				gauge.addStyleName("selected-chart");
				componentData.setComponentType(DashboardComponentType.GAUGE);
				data.reRendar();
				formatting.reRender();
			}
		}, ClickEvent.getType());

		metric = new FlowPanel();
		metric.addStyleName("metricchart");
		metric.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				metric.addStyleName("selected-chart");
				componentData.setComponentType(DashboardComponentType.METRIC);
				data.reRendar();
				formatting.reRender();
			}
		}, ClickEvent.getType());

		tabel = new FlowPanel();
		tabel.addStyleName("tabel");
		tabel.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				clearSelectedStyleName();
				tabel.addStyleName("selected-chart");
				componentData.setComponentType(DashboardComponentType.TABLE);
				data.reRendar();
				formatting.reRender();
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

	/**
	 * setting the selected style to selected chart panel
	 */
	private void setSelectedStyleToPanel() {

		switch (componentData.getComponentType()) {
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

	/**
	 * clearing the all chart type panels selected style
	 */
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

	/**
	 * when data changes in dialog rpc will call get the chart in Html format
	 * assign chart html
	 */
	public void refreshChartPanel() {
		RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST,
				GET_CHART_HTML);

		String componentString = componentData.toJSON().toString();
		DashboardData dashboardData = Dashboard.getDashboardData();
		String requestString = "componentData=" + componentString + ","
				+ "dashboardId=" + dashboardData.getId();
		requestBuilder.setRequestData(requestString);

		requestBuilder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				chartHtml = response.getText();
			}

			@Override
			public void onError(Request request, Throwable exception) {
				// TODO Auto-generated method stub
			}
		});
	}
}
