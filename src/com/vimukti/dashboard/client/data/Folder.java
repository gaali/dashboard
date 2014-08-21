package com.vimukti.dashboard.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings("serial")
public class Folder extends MetaObject {
	private List<ReportDetails> reports;

	/**
	 * @return the reports
	 */
	public List<ReportDetails> getReports() {
		return reports;
	}

	/**
	 * @param reports
	 *            the reports to set
	 */
	public void setReports(List<ReportDetails> reports) {
		this.reports = reports;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jsonValue = jsonObject.get("reports");
		List<ReportDetails> reportsList = null;
		if (jsonValue != null) {
			JSONArray reportsArray = jsonValue.isArray();
			reportsList = new ArrayList<ReportDetails>();
			for (int i = 0; i < reportsArray.size(); i++) {
				ReportDetails jReportDetails = new ReportDetails();
				JSONValue jsonValue2 = reportsArray.get(i);
				jReportDetails.fromJSON(jsonValue2.isObject());
				reportsList.add(jReportDetails);
			}
		}
		reports = reportsList;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		JSONArray reportArray = new JSONArray();
		int index = 0;
		for (ReportDetails report : reports) {
			if (report != null) {
				reportArray.set(index++, report.toJSON());
			}
		}
		json.put("reports", reportArray);
		return json;
	}
}
