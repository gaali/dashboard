package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.vimukti.dashboard.client.data.IJSONObject;

public class ReportFact implements IJSONObject {

	private String key;
	private List<SummaryValue> aggregates;

	/**
	 * Returns summary-level data for a report, including the record count.
	 * 
	 * @return
	 */
	public List<SummaryValue> getAggregates() {
		return aggregates;
	}

	/**
	 * Returns the unique identifier for a row or column grouping. This
	 * identifier can be used to index specific data values within each
	 * grouping.
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

	public void fromJSON(JSONObject object) {

		JSONValue jsonValue = object.get("key");
		if (jsonValue != null) {
			key = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("aggregates");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<SummaryValue> inarray = new ArrayList<SummaryValue>();

			for (int i = 0; i < jsonArray.size(); i++) {

				SummaryValue inObj = new SummaryValue();
				inObj.fromJSON(jsonArray.get(i).isObject());
				inarray.add(inObj);

			}
			aggregates = inarray;
		}

	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (key != null) {
			jsonObject.put("key", new JSONString(key));
		}

		if (aggregates != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (SummaryValue inArr : aggregates) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("aggregates", jsonArray);
		}
		return jsonObject;
	}
}
