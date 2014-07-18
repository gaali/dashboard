package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.vimukti.dashboard.client.data.Folder;
import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class Report extends MetaObject {

	private List<ReportAggregate> aggregates = new ArrayList<ReportAggregate>();

	private List<Report> block;

	private ReportBlockInfo blockInfo;

	private List<ReportBucketField> buckets = new ArrayList<ReportBucketField>();

	private ReportChart chart;

	private List<ReportColorRange> colorRanges = new ArrayList<>();

	private List<ReportColumn> columns = new ArrayList<>();

	private List<ReportCrossFilter> crossFilters = new ArrayList<>();

	private String currency;

	private String description;

	private String division;

	private ReportFilter filter;

	private String format;

	private String fullName;

	private List<ReportGrouping> groupingsAcross = new ArrayList<>();

	private List<ReportGrouping> groupingsDown = new ArrayList<>();

	private ReportHistoricalSelector historicalSelector;

	private String name;

	private List<ReportParam> params = new ArrayList<>();

	private String reportType;

	private String roleHierarchyFilter;

	private int rowLimit;

	private String scope;

	private boolean showCurrentDate;

	private boolean showDetails;

	private String sortColumn;

	private String sortOrder;

	private String territoryHierarchyFilter;

	private ReportTimeFrameFilter timeFrameFilter;

	private String userFilter;

	private Folder folder;

	/**
	 * @return the aggregates
	 */
	public List<ReportAggregate> getAggregates() {
		return aggregates;
	}

	/**
	 * @param aggregates
	 *            the aggregates to set
	 */
	public void setAggregates(List<ReportAggregate> aggregates) {
		this.aggregates = aggregates;
	}

	/**
	 * @return the block
	 */
	public List<Report> getBlock() {
		return block;
	}

	/**
	 * @param block
	 *            the block to set
	 */
	public void setBlock(List<Report> block) {
		this.block = block;
	}

	/**
	 * @return the blockInfo
	 */
	public ReportBlockInfo getBlockInfo() {
		return blockInfo;
	}

	/**
	 * @param blockInfo
	 *            the blockInfo to set
	 */
	public void setBlockInfo(ReportBlockInfo blockInfo) {
		this.blockInfo = blockInfo;
	}

	/**
	 * @return the buckets
	 */
	public List<ReportBucketField> getBuckets() {
		return buckets;
	}

	/**
	 * @param buckets
	 *            the buckets to set
	 */
	public void setBuckets(List<ReportBucketField> buckets) {
		this.buckets = buckets;
	}

	/**
	 * @return the chart
	 */
	public ReportChart getChart() {
		return chart;
	}

	/**
	 * @param chart
	 *            the chart to set
	 */
	public void setChart(ReportChart chart) {
		this.chart = chart;
	}

	/**
	 * @return the colorRanges
	 */
	public List<ReportColorRange> getColorRanges() {
		return colorRanges;
	}

	/**
	 * @param colorRanges
	 *            the colorRanges to set
	 */
	public void setColorRanges(List<ReportColorRange> colorRanges) {
		this.colorRanges = colorRanges;
	}

	/**
	 * @return the columns
	 */
	public List<ReportColumn> getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            the columns to set
	 */
	public void setColumns(List<ReportColumn> columns) {
		this.columns = columns;
	}

	/**
	 * @return the crossFilters
	 */
	public List<ReportCrossFilter> getCrossFilters() {
		return crossFilters;
	}

	/**
	 * @param crossFilters
	 *            the crossFilters to set
	 */
	public void setCrossFilters(List<ReportCrossFilter> crossFilters) {
		this.crossFilters = crossFilters;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @param division
	 *            the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 * @return the filter
	 */
	public ReportFilter getFilter() {
		return filter;
	}

	/**
	 * @param filter
	 *            the filter to set
	 */
	public void setFilter(ReportFilter filter) {
		this.filter = filter;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the groupingsAcross
	 */
	public List<ReportGrouping> getGroupingsAcross() {
		return groupingsAcross;
	}

	/**
	 * @param groupingsAcross
	 *            the groupingsAcross to set
	 */
	public void setGroupingsAcross(List<ReportGrouping> groupingsAcross) {
		this.groupingsAcross = groupingsAcross;
	}

	/**
	 * @return the groupingsDown
	 */
	public List<ReportGrouping> getGroupingsDown() {
		return groupingsDown;
	}

	/**
	 * @param groupingsDown
	 *            the groupingsDown to set
	 */
	public void setGroupingsDown(List<ReportGrouping> groupingsDown) {
		this.groupingsDown = groupingsDown;
	}

	/**
	 * @return the historicalSelector
	 */
	public ReportHistoricalSelector getHistoricalSelector() {
		return historicalSelector;
	}

	/**
	 * @param historicalSelector
	 *            the historicalSelector to set
	 */
	public void setHistoricalSelector(
			ReportHistoricalSelector historicalSelector) {
		this.historicalSelector = historicalSelector;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the params
	 */
	public List<ReportParam> getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(List<ReportParam> params) {
		this.params = params;
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType
	 *            the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the roleHierarchyFilter
	 */
	public String getRoleHierarchyFilter() {
		return roleHierarchyFilter;
	}

	/**
	 * @param roleHierarchyFilter
	 *            the roleHierarchyFilter to set
	 */
	public void setRoleHierarchyFilter(String roleHierarchyFilter) {
		this.roleHierarchyFilter = roleHierarchyFilter;
	}

	/**
	 * @return the rowLimit
	 */
	public int getRowLimit() {
		return rowLimit;
	}

	/**
	 * @param rowLimit
	 *            the rowLimit to set
	 */
	public void setRowLimit(int rowLimit) {
		this.rowLimit = rowLimit;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the showCurrentDate
	 */
	public boolean isShowCurrentDate() {
		return showCurrentDate;
	}

	/**
	 * @param showCurrentDate
	 *            the showCurrentDate to set
	 */
	public void setShowCurrentDate(boolean showCurrentDate) {
		this.showCurrentDate = showCurrentDate;
	}

	/**
	 * @return the showDetails
	 */
	public boolean isShowDetails() {
		return showDetails;
	}

	/**
	 * @param showDetails
	 *            the showDetails to set
	 */
	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}

	/**
	 * @return the sortColumn
	 */
	public String getSortColumn() {
		return sortColumn;
	}

	/**
	 * @param sortColumn
	 *            the sortColumn to set
	 */
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	/**
	 * @return the sortOrder
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the territoryHierarchyFilter
	 */
	public String getTerritoryHierarchyFilter() {
		return territoryHierarchyFilter;
	}

	/**
	 * @param territoryHierarchyFilter
	 *            the territoryHierarchyFilter to set
	 */
	public void setTerritoryHierarchyFilter(String territoryHierarchyFilter) {
		this.territoryHierarchyFilter = territoryHierarchyFilter;
	}

	/**
	 * @return the timeFrameFilter
	 */
	public ReportTimeFrameFilter getTimeFrameFilter() {
		return timeFrameFilter;
	}

	/**
	 * @param timeFrameFilter
	 *            the timeFrameFilter to set
	 */
	public void setTimeFrameFilter(ReportTimeFrameFilter timeFrameFilter) {
		this.timeFrameFilter = timeFrameFilter;
	}

	/**
	 * @return the userFilter
	 */
	public String getUserFilter() {
		return userFilter;
	}

	/**
	 * @param userFilter
	 *            the userFilter to set
	 */
	public void setUserFilter(String userFilter) {
		this.userFilter = userFilter;
	}

	/**
	 * @return the folder
	 */
	public Folder getFolder() {
		return folder;
	}

	/**
	 * @param folder
	 *            the folder to set
	 */
	public void setFolder(Folder folder) {
		this.folder = folder;
	}

}
