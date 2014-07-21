package com.vimukti.dashboard.client.column;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentSection;
import com.vimukti.dashboard.client.data.DashboardComponentSize;
import com.vimukti.dashboard.client.portlet.ui.controls.Portlet;
import com.vimukti.dashboard.client.portlet.ui.controls.PortletFactory;

public abstract class DashboardColumn extends FlowPanel {
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
		createHeder();
		createColumn();
		String size = DashboardComponentSize.MEDIUM.toString().toLowerCase();
		if (getSection() != null) {
			size = getSection().getColumnSize().toString().toLowerCase();
		}
		setColumnSize(size);
		createAddColumnIcon();
	}

	private void createHeder() {
		columnHeader = new ColumnHeader();
		this.add(columnHeader);
	}

	private void createAddColumnIcon() {
		addIcon = new FlowPanel();
		addIcon.addStyleName("column-addIcon");
		addIcon.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addColumn();
			}
		}, ClickEvent.getType());
	}

	protected abstract void addColumn();

	private void createColumn() {
		column = new VerticalPanel();
		column.addStyleName("column-body");
		// Column Header which contains Size and remove Icon
		columnHeader = new ColumnHeader();
		column.add(columnHeader);
		// Portlet Container Panel which is Droppable
		List<DashboardComponent> components = null;
		if (getSection() != null) {
			components = getSection().getComponents();
		}
		portletContainer = new PortletContainerPanel();
		portletContainer.addPortlets(components);
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
		if (getSection() == null) {
			return;
		}
		PortletFactory portletFactory = PortletFactory.getPortletFactory();
		List<DashboardComponent> components = getSection().getComponents();
		for (DashboardComponent component : components) {
			Portlet Portlet = portletFactory.createPortlet(component);
			this.add(Portlet);
		}

	}

	public void deleteColun(DeleteDashboardColumn col) {
		columnHeader.setDeletecolumn(col);

	}

	public DashboardComponentSection getSection() {
		return section;
	}

	public void setSection(DashboardComponentSection section) {
		this.section = section;
	}

	public void disableRemoveIcon() {
		// TODO Auto-generated method stub

	}

}
