package com.vimukti.dashboard.client.data;

import com.vimukti.dashboard.client.reportdata.ReportAggregate;
import com.vimukti.dashboard.client.reportdata.ReportGrouping;

public class DashboardComponentData {
	private ReportGrouping groups;
	private ReportAggregate aggregate;

	/**
	 * @return the groups
	 */
	public ReportGrouping getGroups() {
		return groups;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(ReportGrouping groups) {
		this.groups = groups;
	}

	/**
	 * @return the aggregatesList
	 */
	public ReportAggregate getAggregate() {
		return aggregate;
	}

	/**
	 * @param aggregatesList
	 *            the aggregatesList to set
	 */
	public void setAggregatesList(ReportAggregate aggregate) {
		this.aggregate = aggregate;
	}

	public DashboardComponentData() {
		// TODO Auto-generated constructor stub
	}

}
