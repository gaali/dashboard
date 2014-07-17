package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class ReportTypeMetadata {

	private List<ReportTypeColumnCategory> categories;

	/**
	 * Returns all fields in the report type. The fields are organized by
	 * section.
	 * 
	 * @return
	 */
	public List<ReportTypeColumnCategory> getCategories() {
		// TODO
		return categories;
	}

	public void fromJSON(JSONObject jsonObject) {

		JSONValue jsonValue = jsonObject.get("categories");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<ReportTypeColumnCategory> inArray = new ArrayList<ReportTypeColumnCategory>();

			for (int i = 0; i < jsonArray.size(); i++) {

				ReportTypeColumnCategory inObj = new ReportTypeColumnCategory();
				inObj.fromJSON(jsonArray.get(i).isObject());
				inArray.add(inObj);

			}
			categories = inArray;
		}
	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (categories != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (ReportTypeColumnCategory inArr : categories) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("categories", jsonArray);
		}
		return jsonObject;
	}
}
