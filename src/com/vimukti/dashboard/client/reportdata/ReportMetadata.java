package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class ReportMetadata {

	private List<String> aggregates;

	private String currencyCode;

	private List<String> detailColumns;

	private String developerName;

	private List<GroupingInfo> groupingsAcross;

	private List<GroupingInfo> groupingsDown;

	private List<String> historicalSnapshotDates;

	private String id;

	private String name;

	private String reportBooleanFilter;

	private List<ReportFilter> reportFilters;

	private ReportFormat reportFormat;

	private ReportType reportType;

	/**
	 * Returns unique identifiers for summary or custom summary formula fields
	 * in the report.
	 * 
	 * @return
	 */
	public List<String> getAggregates() {
		return aggregates;
	}

	public void setAggregates(List<String> aggregates) {
		this.aggregates = aggregates;
	}

	/**
	 * Returns report currency, such as USD, EUR, or GBP, for an organization
	 * that has multicurrency enabled. The value is null if the organization
	 * does not have multicurrency enabled.
	 * 
	 * @return
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * Returns unique API names (column names) for the fields that contain
	 * detailed data. For example, the method might return the following values:
	 * â€œOPPORTUNITY_NAME, TYPE, LEAD_SOURCE, AMOUNT.â€�
	 * 
	 * @return
	 */
	public List<String> getDetailColumns() {
		return detailColumns;
	}

	public void setDetailColumns(List<String> detailColumns) {
		this.detailColumns = detailColumns;
	}

	/**
	 * Returns the report API name. For example, the method might return the
	 * following value: â€œClosed_Sales_This_Quarter.â€�
	 * 
	 * @return
	 */
	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	/**
	 * Returns the unique identifiers for each column grouping in a report.
	 * 
	 * @return
	 */
	public List<GroupingInfo> getGroupingsAcross() {
		return groupingsAcross;
	}

	public void setGroupingsAcross(List<GroupingInfo> groupingsAcross) {
		this.groupingsAcross = groupingsAcross;
	}

	/**
	 * Returns the unique identifiers for each row grouping in a report.
	 * 
	 * @return
	 */
	public List<GroupingInfo> getGroupingsDown() {
		return groupingsDown;
	}

	public void setGroupingsDown(List<GroupingInfo> groupingsDown) {
		this.groupingsDown = groupingsDown;
	}

	/**
	 * Returns a list of historical snapshot dates.
	 * 
	 * @return
	 */
	public List<String> getHistoricalSnapshotDates() {
		return historicalSnapshotDates;
	}

	/**
	 * Sets a list of historical snapshot dates
	 * 
	 * @param historicalSnapshotDates
	 */
	public void setHistoricalSnapshotDates(List<String> historicalSnapshotDates) {
		this.historicalSnapshotDates = historicalSnapshotDates;
	}

	/**
	 * Returns the unique report ID.
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the report name.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns logic to parse custom field filters. The value is null when
	 * filter logic is not specified.
	 * 
	 * @return
	 */
	public String getReportBooleanFilter() {
		return reportBooleanFilter;
	}

	/**
	 * Sets logic to parse custom field filters.
	 * 
	 * @param reportBooleanFilter
	 */
	public void setReportBooleanFilter(String reportBooleanFilter) {
		this.reportBooleanFilter = reportBooleanFilter;
	}

	/**
	 * Returns a list of each custom filter in the report along with the field
	 * name, filter operator, and filter value.
	 * 
	 * @return
	 */
	public List<ReportFilter> getReportFilters() {
		return reportFilters;
	}

	/**
	 * Sets a list of each custom filter in the report along with the field
	 * name, filter operator, and filter value.
	 * 
	 * @param reportFilters
	 */
	public void setReportFilters(List<ReportFilter> reportFilters) {
		this.reportFilters = reportFilters;
	}

	/**
	 * Returns the format of the report.
	 * 
	 * @return
	 */
	public ReportFormat getReportFormat() {
		return reportFormat;
	}

	public void setReportFormat(ReportFormat reportFormat) {
		this.reportFormat = reportFormat;
	}

	/**
	 * Returns the unique API name and display name for the report type.
	 * 
	 * @return
	 */
	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public void fromJSON(JSONObject object) {

		JSONValue jsonValue = object.get("aggregates");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<String> jfields = new ArrayList<String>();
			for (int i = 0; i < jsonArray.size(); i++) {
				jfields.add(jsonArray.get(i).isString().stringValue());
			}
			if (aggregates == null) {
				aggregates = new ArrayList<String>();
			}
			aggregates = jfields;
		}

		jsonValue = object.get("currencyCode");
		if (jsonValue != null) {
			currencyCode = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("detailColumns");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<String> jfields = new ArrayList<String>();
			for (int i = 0; i < jsonArray.size(); i++) {
				jfields.add(jsonArray.get(i).isString().stringValue());
			}
			if (detailColumns == null) {
				detailColumns = new ArrayList<String>();
			}
			detailColumns = jfields;
		}
		jsonValue = object.get("developerName");
		if (jsonValue != null) {
			developerName = jsonValue.isString().stringValue();
		}

		jsonValue = object.get("groupingsAcross");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<GroupingInfo> jaggregateReferences = new ArrayList<GroupingInfo>();

			for (int i = 0; i < jsonArray.size(); i++) {

				GroupingInfo inObj = new GroupingInfo();
				inObj.fromJSON(jsonArray.get(i).isObject());
				jaggregateReferences.add(inObj);

			}
			if (groupingsAcross == null) {
				groupingsAcross = new ArrayList<GroupingInfo>();
			}
			groupingsAcross = jaggregateReferences;

		}

		jsonValue = object.get("groupingsDown");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<GroupingInfo> jaggregateReferences = new ArrayList<GroupingInfo>();

			for (int i = 0; i < jsonArray.size(); i++) {

				GroupingInfo inObj = new GroupingInfo();
				inObj.fromJSON(jsonArray.get(i).isObject());
				jaggregateReferences.add(inObj);

			}
			if (groupingsDown == null) {
				groupingsDown = new ArrayList<GroupingInfo>();
			}
			groupingsDown = jaggregateReferences;

		}

		jsonValue = object.get("historicalSnapshotDates");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<String> jfields = new ArrayList<String>();
			for (int i = 0; i < jsonArray.size(); i++) {
				jfields.add(jsonArray.get(i).isString().stringValue());
			}
			if (historicalSnapshotDates == null) {
				historicalSnapshotDates = new ArrayList<String>();
			}
			historicalSnapshotDates = jfields;
		}
		jsonValue = object.get("id");
		if (jsonValue != null) {
			id = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("name");
		if (jsonValue != null) {
			name = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("reportBooleanFilter");
		if (jsonValue != null) {
			reportBooleanFilter = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("reportFilters");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<ReportFilter> jaggregateReferences = new ArrayList<ReportFilter>();

			for (int i = 0; i < jsonArray.size(); i++) {

				ReportFilter inObj = new ReportFilter();
				inObj.fromJSON(jsonArray.get(i).isObject());
				jaggregateReferences.add(inObj);

			}
			if (reportFilters == null) {
				reportFilters = new ArrayList<ReportFilter>();
			}
			reportFilters = jaggregateReferences;

		}
		jsonValue = object.get("reportFormat");
		if (jsonValue != null) {
			reportFormat = ReportFormat.get(jsonValue.isNumber().doubleValue());
		}
		jsonValue = object.get("reportType");
		if (jsonValue != null) {
			ReportType inobj = new ReportType();
			inobj.fromJSON(jsonValue.isObject());
			reportType = inobj;
		}
	}

	public JSONValue toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (aggregates != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (String inArr : aggregates) {

				if (inArr != null) {
					jsonArray.set(index++, new JSONString(inArr));
				}

			}
			jsonObject.put("fields", jsonArray);
		}
		if (currencyCode != null) {
			jsonObject.put("currencyCode", new JSONString(currencyCode));
		}
		if (detailColumns != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (String inArr : detailColumns) {

				if (inArr != null) {
					jsonArray.set(index++, new JSONString(inArr));
				}

			}
			jsonObject.put("detailColumns", jsonArray);
		}
		if (developerName != null) {
			jsonObject.put("developerName", new JSONString(developerName));
		}
		if (groupingsAcross != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (GroupingInfo inArr : groupingsAcross) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("groupingsAcross", jsonArray);
		}
		if (groupingsDown != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (GroupingInfo inArr : groupingsDown) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("groupingsDown", jsonArray);
		}
		if (historicalSnapshotDates != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (String inArr : historicalSnapshotDates) {

				if (inArr != null) {
					jsonArray.set(index++, new JSONString(inArr));
				}

			}
			jsonObject.put("historicalSnapshotDates", jsonArray);
		}
		if (id != null) {
			jsonObject.put("id", new JSONString(id));
		}
		if (name != null) {
			jsonObject.put("name", new JSONString(name));
		}
		if (reportBooleanFilter != null) {
			jsonObject.put("reportBooleanFilter", new JSONString(
					reportBooleanFilter));
		}
		if (reportFilters != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (ReportFilter inArr : reportFilters) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("reportFilters", jsonArray);
		}
		if (reportFormat != null) {
			jsonObject.put("reportFormat",
					new JSONNumber(reportFormat.ordinal()));
		}
		if (reportType != null) {
			jsonObject.put("reportType", reportType.toJSON());
		}
		return jsonObject;
	}
}
