package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.user.client.ui.FlowPanel;
import com.vimukti.dashboard.client.data.DashboardComponentType;

public class ComponentsPanel extends FlowPanel {

	public ComponentsPanel() {
		this.addStyleName("components-panel");
		createControls();
	}

	private void createControls() {
		FlowPanel fPanel = new FlowPanel();
		fPanel.setStyleName("char-componenet-grid");

		ChartComponent horizontalBar = createComponent(
				DashboardComponentType.BAR, "HorizontalBar");
		fPanel.add(horizontalBar);
		ChartComponent verticalBar = createComponent(
				DashboardComponentType.COLUMN, "verticalBar");
		fPanel.add(verticalBar);
		ChartComponent line = createComponent(DashboardComponentType.LINE,
				"line");
		fPanel.add(line);
		ChartComponent pie = createComponent(DashboardComponentType.PIE, "pie");
		fPanel.add(pie);
		ChartComponent donut = createComponent(DashboardComponentType.DONUT,
				"donut");
		fPanel.add(donut);
		ChartComponent funnel = createComponent(DashboardComponentType.FUNNEL,
				"funnel");
		fPanel.add(funnel);
		ChartComponent scatter = createComponent(
				DashboardComponentType.SCATTER, "scatter");
		fPanel.add(scatter);
		ChartComponent gauge = createComponent(DashboardComponentType.GAUGE,
				"gauge");
		fPanel.add(gauge);
		ChartComponent metric = createComponent(DashboardComponentType.METRIC,
				"metric");
		fPanel.add(metric);
		ChartComponent table = createComponent(DashboardComponentType.TABLE,
				"table");
		fPanel.add(table);
		ChartComponent page = createComponent(DashboardComponentType.PAGE,
				"page");
		fPanel.add(page);

		this.add(fPanel);

	}

	private ChartComponent createComponent(DashboardComponentType type,
			String name) {
		ChartComponent chartComponent = new ChartComponent(type, name);
		return chartComponent;
	}

}
