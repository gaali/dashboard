package com.vimukti.dashboard.client.data;

import java.util.List;

@SuppressWarnings("serial")
public class DashboardComponentSection extends MetaObject{
	
	private  DashboardComponentSize columnSize;
	
	private List<DashboardComponent> components;

	/**
	 * @return the columnSize
	 */
	public DashboardComponentSize getColumnSize() {
		return columnSize;
	}

	/**
	 * @param columnSize the columnSize to set
	 */
	public void setColumnSize(DashboardComponentSize columnSize) {
		this.columnSize = columnSize;
	}

	/**
	 * @return the components
	 */
	public List<DashboardComponent> getComponents() {
		return components;
	}

	/**
	 * @param components the components to set
	 */
	public void setComponents(List<DashboardComponent> components) {
		this.components = components;
	}

}
