package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.user.client.ui.FlowPanel;
import com.vimukti.dashboard.client.data.DashboardComponentType;

public class ComponentsPanel extends FlowPanel {

	public ComponentsPanel() {
		this.getElement().setPropertyString("min-width:100px;", "25%");
		this.addStyleName("components-panel");
		createControls();
	}

	private void createControls() {

		ChartComponent horizontalBar = createComponent(
				DashboardComponentType.BAR, "HorizontalBar");
		horizontalBar.addStyleName("horizontalbar");
		this.add(horizontalBar);

		ChartComponent verticalBar = createComponent(
				DashboardComponentType.COLUMN, "verticalBar");
		verticalBar.addStyleName("verticalbar");
		this.add(verticalBar);

		ChartComponent line = createComponent(DashboardComponentType.LINE,
				"line");
		line.addStyleName("linechart");
		this.add(line);

		ChartComponent pie = createComponent(DashboardComponentType.PIE, "pie");
		pie.addStyleName("piechart");
		this.add(pie);

		ChartComponent donut = createComponent(DashboardComponentType.DONUT,
				"donut");
		donut.addStyleName("donutchart");
		this.add(donut);

		ChartComponent funnel = createComponent(DashboardComponentType.FUNNEL,
				"funnel");
		this.add(funnel);
		ChartComponent scatter = createComponent(
				DashboardComponentType.SCATTER, "scatter");
		this.add(scatter);
		ChartComponent gauge = createComponent(DashboardComponentType.GAUGE,
				"gauge");
		gauge.addStyleName("gauagechart");
		this.add(gauge);

		ChartComponent metric = createComponent(DashboardComponentType.METRIC,
				"metric");
		this.add(metric);
		ChartComponent table = createComponent(DashboardComponentType.TABLE,
				"table");
		table.addStyleName("table-component");
		this.add(table);

		ChartComponent page = createComponent(DashboardComponentType.PAGE,
				"page");
		page.addStyleName("page-component");
		this.add(page);

	}

	private ChartComponent createComponent(DashboardComponentType type,
			String name) {
		ChartComponent chartComponent = new ChartComponent(type, name);
		return chartComponent;
	}

}
