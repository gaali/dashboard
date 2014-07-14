package com.vimukt.dashboard.client.column;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.ui.controls.dnd.IDroppable;
import com.vimukti.dashboard.portlet.ui.controls.Portlet;
import com.vimukti.dashboard.portlet.ui.controls.PortletFactory;

public class PortletContainerPanel extends FlowPanel implements IDroppable {
	private List<DashboardComponent> components;

	public PortletContainerPanel(List<DashboardComponent> components) {
		this.addStyleName("portlet-container");
		addPortlets();
	}

	private void addPortlets() {
		if (components.isEmpty()) {
			return;
		}
		PortletFactory portletFactory = PortletFactory.getPortletFactory();
		for (DashboardComponent component : components) {
			Portlet Portlet = portletFactory.createPortlet(component);
			this.add(Portlet);
		}
	}

}
