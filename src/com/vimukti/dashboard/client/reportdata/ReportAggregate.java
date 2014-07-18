package com.vimukti.dashboard.client.reportdata;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportAggregate extends MetaObject {

	private String acrossGroupingContext;

	private String calculatedFormula;

	// ReportAggregateDatatype
	private String datatype;

	private String description;

	private String developerName;

	private String downGroupingContext;

	private boolean isActive;

	private boolean isCrossBlock;

	private String masterLabel;

	private String reportType;

	private int scale;

	/**
	 * @return the acrossGroupingContext
	 */
	public String getAcrossGroupingContext() {
		return acrossGroupingContext;
	}

	/**
	 * @param acrossGroupingContext
	 *            the acrossGroupingContext to set
	 */
	public void setAcrossGroupingContext(String acrossGroupingContext) {
		this.acrossGroupingContext = acrossGroupingContext;
	}

	/**
	 * @return the calculatedFormula
	 */
	public String getCalculatedFormula() {
		return calculatedFormula;
	}

	/**
	 * @param calculatedFormula
	 *            the calculatedFormula to set
	 */
	public void setCalculatedFormula(String calculatedFormula) {
		this.calculatedFormula = calculatedFormula;
	}

	/**
	 * @return the datatype
	 */
	public String getDatatype() {
		return datatype;
	}

	/**
	 * @param datatype
	 *            the datatype to set
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
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
	 * @return the developerName
	 */
	public String getDeveloperName() {
		return developerName;
	}

	/**
	 * @param developerName
	 *            the developerName to set
	 */
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	/**
	 * @return the downGroupingContext
	 */
	public String getDownGroupingContext() {
		return downGroupingContext;
	}

	/**
	 * @param downGroupingContext
	 *            the downGroupingContext to set
	 */
	public void setDownGroupingContext(String downGroupingContext) {
		this.downGroupingContext = downGroupingContext;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the isCrossBlock
	 */
	public boolean isCrossBlock() {
		return isCrossBlock;
	}

	/**
	 * @param isCrossBlock
	 *            the isCrossBlock to set
	 */
	public void setCrossBlock(boolean isCrossBlock) {
		this.isCrossBlock = isCrossBlock;
	}

	/**
	 * @return the masterLabel
	 */
	public String getMasterLabel() {
		return masterLabel;
	}

	/**
	 * @param masterLabel
	 *            the masterLabel to set
	 */
	public void setMasterLabel(String masterLabel) {
		this.masterLabel = masterLabel;
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
	 * @return the scale
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
}
