package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportAggregate extends MetaObject {

	/**
	 * Defines the row grouping level at which you want your custom summary
	 * formula to be displayed.
	 */
	private String acrossGroupingContext;

	/**
	 * The custom summary formula. For example, AMOUNT:SUM + OPP_QUANTITY:SUM
	 */
	private String calculatedFormula;

	/**
	 * Specifies the data type for formatting and display of the custom summary
	 * formula results.
	 */
	private ReportAggregateDatatype datatype;

	/**
	 * The custom summary formula description
	 */
	private String description;

	/**
	 * The internal development name of the custom summary formula, for example,
	 * FORMULA1. This is used to reference custom summary formulas from other
	 * report components, including conditional highlighting.
	 */
	private String developerName;

	/**
	 * Defines the column grouping level at which you want your custom summary
	 * formula to be displayed
	 */
	private String downGroupingContext;

	/**
	 * Determines whether the custom summary formula is a cross-block formula,
	 * which is available with joined reports. true indicates a cross-block
	 * custom summary formula. false indicates a standard custom summary
	 * formula.
	 */
	private boolean isCrossBlock;

	/**
	 * The custom summary formula label (name).
	 */
	private String masterLabel;

	/**
	 * Required for joined reports. Specifies the reportType of the blocks to
	 * which the aggregate can be added.
	 */
	private String reportType;

	/**
	 * The formula result is calculated to the specified number of decimal
	 * places. Valid values 0 through 18.
	 */
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
	public ReportAggregateDatatype getDatatype() {
		return datatype;
	}

	/**
	 * @param datatype
	 *            the datatype to set
	 */
	public void setDatatype(ReportAggregateDatatype datatype) {
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

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jContext = jsonObject.get("acrossGroupingContext");
		if (jContext != null) {
			acrossGroupingContext = jContext.isString().stringValue();
		}
		JSONValue jFormula = jsonObject.get("calculatedFormula");
		if (jFormula != null) {
			calculatedFormula = jFormula.isString().stringValue();
		}
		JSONValue jDataType = jsonObject.get("datatype");
		if (jDataType != null) {
			String stringValue = jDataType.isString().stringValue();
			ReportAggregateDatatype.getAggregateDatatype(stringValue);
		}
		JSONValue jDescription = jsonObject.get("description");
		if (jDescription != null) {
			description = jDescription.isString().stringValue();
		}

		JSONValue jdeveloperName = jsonObject.get("developerName");
		if (jdeveloperName != null) {
			developerName = jDescription.isString().stringValue();
		}

		JSONValue jIsCrossBlock = jsonObject.get("isCrossBlock");
		if (jIsCrossBlock != null) {
			isCrossBlock = jIsCrossBlock.isBoolean().booleanValue();
		}

		JSONValue jMasterLabel = jsonObject.get("masterLabel");
		if (jMasterLabel != null) {
			masterLabel = jMasterLabel.isString().stringValue();
		}
		JSONValue jReportType = jsonObject.get("reportType");
		if (jReportType != null) {
			reportType = jReportType.isString().stringValue();
		}

		JSONValue jScale = jsonObject.get("scale");
		if (jScale != null) {
			scale = (int) jScale.isNumber().doubleValue();
		}

	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		if (acrossGroupingContext != null) {
			json.put("acrossGroupingContext", new JSONString(
					acrossGroupingContext));
		}
		if (calculatedFormula != null) {
			json.put("calculatedFormula", new JSONString(calculatedFormula));
		}
		if (datatype != null) {
			String string = datatype.toString();
			json.put("datatype", new JSONString(string));
		}
		if (description != null) {
			json.put("description", new JSONString(description));
		}
		if (developerName != null) {
			json.put("developerName", new JSONString(developerName));
		}
		if (downGroupingContext != null) {
			json.put("downGroupingContext", new JSONString(downGroupingContext));
		}
		json.put("isCrossBlock", JSONBoolean.getInstance(isCrossBlock));
		if (masterLabel != null) {
			json.put("masterLabel", new JSONString(masterLabel));
		}
		return json;
	}
}
