package com.vimukti.dashboard.client.portlet.ui.controls;

import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.data.DashboardComponentType;

public class PortletFactory {

	private static PortletFactory portletFactory;

	public static PortletFactory getPortletFactory() {
		if (portletFactory == null) {
			portletFactory = new PortletFactory();
		}
		return portletFactory;
	}

	public Portlet createPortlet(DashboardComponent component) {
		Portlet portlet = new Portlet(component);
		return portlet;
	}

	public Portlet createPortlerReport(String recordId) {
		DashboardComponent component = new DashboardComponent();
		component.setComponentType(DashboardComponentType.REPORT);
		Portlet portlet = new Portlet(component);
		portlet.setObjectId(recordId);
		return portlet;
	}

	public Portlet createPortlerPage(String pageId) {
		DashboardComponent component = new DashboardComponent();
		component.setComponentType(DashboardComponentType.PAGE);
		Portlet portlet = new Portlet(component);
		portlet.setObjectId(pageId);
		return portlet;
	}

}
