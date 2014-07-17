package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class ReportFactWithDetails extends ReportFact {

	private List<ReportDetailRow> rows;

	/**
	 * Returns a list of detailed report data in the order of the detail columns
	 * that are provided by the report metadata.
	 * 
	 * @return
	 */
	public List<ReportDetailRow> getRows() {
		return rows;
	}

	public void fromJSON(JSONObject object) {

		super.fromJSON(object);

		JSONValue jsonValue = object.get("rows");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<ReportDetailRow> inarray = new ArrayList<ReportDetailRow>();

			for (int i = 0; i < jsonArray.size(); i++) {

				ReportDetailRow inObj = new ReportDetailRow();

				inObj.fromJSON(jsonArray.get(i).isObject());
				inarray.add(inObj);

			}
			rows = inarray;
		}

	}

	public JSONObject toJSON() {
		JSONObject jsonObject = super.toJSON();

		if (rows != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (ReportDetailRow inArr : rows) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("rows", jsonArray);
		}
		return jsonObject;
	}
}
