package com.vimukti.dashboard.portlet.ui.controls;

import com.vimukti.dashboard.client.data.DashboardComponent;

public class PortletFactory {

	private static PortletFactory portletFactory;

	public static PortletFactory getPortletFactory() {
		if (portletFactory == null) {
			portletFactory = new PortletFactory();
		}
		return portletFactory;
	}

	public Portlet createPortlet(DashboardComponent component) {
		Portlet porlet = new Portlet(component);
		return porlet;
	}
}
