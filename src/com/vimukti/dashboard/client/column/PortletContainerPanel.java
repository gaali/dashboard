package com.vimukti.dashboard.client.column;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.portlet.ui.controls.Portlet;
import com.vimukti.dashboard.client.portlet.ui.controls.PortletFactory;
import com.vimukti.dashboard.client.ui.controls.ChartComponent;
import com.vimukti.dashboard.client.ui.controls.DataSourceListType;
import com.vimukti.dashboard.client.ui.controls.DraggabelLableControl;
import com.vimukti.dashboard.client.ui.controls.dnd.IDraggable;
import com.vimukti.dashboard.client.ui.controls.dnd.IDroppable;

public class PortletContainerPanel extends AbsolutePanel implements IDroppable {

	private PortletFactory portletFactory = PortletFactory.getPortletFactory();
	private List<Portlet> portlets = new ArrayList<Portlet>();

	public PortletContainerPanel() {
		this.addStyleName("portlet-container");
	}

	public void addPortlets(List<DashboardComponent> components) {
		if (components == null || components.isEmpty()) {
			return;
		}
		for (DashboardComponent component : components) {
			Portlet portlet = portletFactory.createPortlet(component);
			portlets.add(portlet);
			this.add(portlet);
		}
	}

	@Override
	public boolean canDroppable(IDraggable isDroppableWidget) {
		Widget asWidget = isDroppableWidget.asWidget();
		if (asWidget instanceof ChartComponent) {
			return true;
		}
		if (asWidget instanceof DraggabelLableControl) {
			return true;
		}
		if (asWidget instanceof Portlet) {
			return true;
		}
		return false;
	}

	@Override
	public void onDrop(IDraggable draggabelWidget) {
		if (draggabelWidget instanceof ChartComponent) {
			ChartComponent component = (ChartComponent) draggabelWidget
					.asWidget();
			DashboardComponentType type = component.getType();
			DashboardComponent emptyComponent = new DashboardComponent();
			emptyComponent.setComponentType(type);
			Portlet portlet = portletFactory.createPortlet(emptyComponent);
			portlets.add(portlet);
			this.add(portlet);
		} else if (draggabelWidget instanceof DraggabelLableControl) {
			DraggabelLableControl control = (DraggabelLableControl) draggabelWidget
					.asWidget();
			Portlet portlet = null;
			DataSourceListType type = control.getType();
			if (type == DataSourceListType.REPORT) {
				portlet = portletFactory.createPortlerReport(control.getId());
				Dashboard.retrieveAllCustomObjects();
			} else if (type == DataSourceListType.PAGE) {
				portlet = portletFactory.createPortlerPage(control.getId());
			}
			portlets.add(portlet);
			this.add(portlet);
		} else if (draggabelWidget instanceof Portlet) {
			Portlet portlet = (Portlet) draggabelWidget.asWidget();
			PortletContainerPanel container = (PortletContainerPanel) portlet
					.getParent();
			container.removePortlet(portlet);
			portlets.add(portlet);
			this.add(portlet);
		}

	}

	public void removePortlet(Portlet portlet) {
		this.remove(portlet);
		portlets.remove(portlet);
	}

	public void clearAllPortlets() {
		portlets.clear();
	}

}
