package com.vimukt.dashboard.client.column;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentSection;
import com.vimukti.dashboard.portlet.ui.controls.Portlet;
import com.vimukti.dashboard.portlet.ui.controls.PortletFactory;

public class DashboardColumn extends FlowPanel {
	private VerticalPanel column;
	private PortletContainerPanel portletContainer;
	private ColumnHeader columnHeader;
	private FlowPanel addIcon;
	private DashboardComponentSection section;

	public DashboardColumn(DashboardComponentSection section) {
		this.section = section;
		this.addStyleName("dashboard-column");
		createControls();
		init();
	}

	private void createControls() {
		this.add(columnHeader);
		createHeder();
		createColumn();
		setColumnSize(section.getColumnSize().toString().toLowerCase());
		createAddColumnIcon();
	}

	private void createHeder() {
		columnHeader = new ColumnHeader();
	}

	private void createAddColumnIcon() {
		addIcon = new FlowPanel();
		addIcon.addStyleName("column-addIcon");
		addIcon.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DashboardColumn.this.removeFromParent();

			}
		}, ClickEvent.getType());
	}

	private void createColumn() {
		column = new VerticalPanel();
		column.addStyleName("column-body");
		// Column Header which contains Size and remove Icon
		columnHeader = new ColumnHeader();
		column.add(columnHeader);
		// Portlet Container Panel which is Droppable
		portletContainer = new PortletContainerPanel(section.getComponents());
		column.add(portletContainer);
		this.add(column);
	}

	public void setColumnSize(String size) {
		clearSizeStyle();
		switch (size) {
		case "wide":
			this.addStyleName("colunsize-wide");
			break;
		case "narrow":
			this.addStyleName("column-narrow");
			break;
		default:
			this.addStyleName("column-medium");
			break;
		}

	}

	private void clearSizeStyle() {
		this.removeStyleName("colunsize-wide");
		this.removeStyleName("column-narrow");
		this.removeStyleName("column-medium");
	}

	private void init() {
		if (section == null) {
			return;
		}
		PortletFactory portletFactory = PortletFactory.getPortletFactory();
		List<DashboardComponent> components = section.getComponents();
		for (DashboardComponent component : components) {
			Portlet Portlet = portletFactory.createPortlet(component);
			this.add(Portlet);
		}

	}
}
