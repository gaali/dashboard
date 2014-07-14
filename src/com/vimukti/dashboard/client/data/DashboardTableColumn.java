package com.vimukti.dashboard.client.data;

public class DashboardTableColumn {

	private ReportSummaryType aggregateType;
	private String column;
	private boolean showTotal;
	private DashboardComponentFilter sortBy;

	/**
	 * @return the aggregateType
	 */
	public ReportSummaryType getAggregateType() {
		return aggregateType;
	}

	/**
	 * @param aggregateType
	 *            the aggregateType to set
	 */
	public void setAggregateType(ReportSummaryType aggregateType) {
		this.aggregateType = aggregateType;
	}

	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * @return the showTotal
	 */
	public boolean isShowTotal() {
		return showTotal;
	}

	/**
	 * @param showTotal
	 *            the showTotal to set
	 */
	public void setShowTotal(boolean showTotal) {
		this.showTotal = showTotal;
	}

	/**
	 * @return the sortBy
	 */
	public DashboardComponentFilter getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy
	 *            the sortBy to set
	 */
	public void setSortBy(DashboardComponentFilter sortBy) {
		this.sortBy = sortBy;
	}
}
