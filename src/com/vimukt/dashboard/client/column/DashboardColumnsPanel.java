package com.vimukt.dashboard.client.column;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.vimukti.dashboard.client.data.Dashboard;

public class DashboardColumnsPanel extends FlowPanel {

	private DashboardColumn columnPanel;
	private FlowPanel addIcon;
	private Dashboard dashboard;

	public DashboardColumnsPanel(Dashboard dashboard) {
		this.dashboard = dashboard;
		this.addStyleName("columns-panel");
		CreateControls();
	}

	private void CreateControls() {
		columnAddIcon();
	}

	private void columnAddIcon() {
		addIcon = new FlowPanel();
		addIcon.addStyleName("column-addIcon");
		addIcon.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}, ClickEvent.getType());
		this.add(addIcon);
	}

	private DashboardColumn createColumn() {
		columnPanel = new DashboardColumn(dashboard.getLeftSection());
		columnPanel = new DashboardColumn(dashboard.getMiddleSection());
		columnPanel = new DashboardColumn(dashboard.getRightSection());
		return null;
	}

}
