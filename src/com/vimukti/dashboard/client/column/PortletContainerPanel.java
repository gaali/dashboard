package com.vimukti.dashboard.client.column;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;
import com.vimukti.dashboard.client.portlet.ui.controls.Portlet;
import com.vimukti.dashboard.client.portlet.ui.controls.PortletFactory;
import com.vimukti.dashboard.client.ui.controls.ChartComponent;
import com.vimukti.dashboard.client.ui.controls.DataSourceListType;
import com.vimukti.dashboard.client.ui.controls.DraggabelLableControl;
import com.vimukti.dashboard.client.ui.controls.dnd.IDraggable;
import com.vimukti.dashboard.client.ui.controls.dnd.IDroppable;

public class PortletContainerPanel extends FlowPanel implements IDroppable {

	private PortletFactory portletFactory = PortletFactory.getPortletFactory();

	public PortletContainerPanel() {
		this.addStyleName("portlet-container");
	}

	public void addPortlets(List<DashboardComponent> components) {
		if (components == null || components.isEmpty()) {
			return;
		}
		for (DashboardComponent component : components) {
			Portlet portlet = portletFactory.createPortlet(component);
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
			this.add(portlet);
		} else if (draggabelWidget instanceof DraggabelLableControl) {
			DraggabelLableControl control = (DraggabelLableControl) draggabelWidget
					.asWidget();
			DataSourceListType type = control.getType();
			if (type == DataSourceListType.REPORT) {

			} else if (type == DataSourceListType.PAGE) {

			}

		}
	}

}
