package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class ReportDetailRow {

	private List<ReportDataCell> dataCells;

	/**
	 * Returns a list of data cells for a detail row.
	 * 
	 * @return
	 */
	public List<ReportDataCell> getDataCells() {
		return dataCells;
	}

	public JSONValue toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (dataCells != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (ReportDataCell inArr : dataCells) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("dataCells", jsonArray);
		}
		return jsonObject;
	}

	public void fromJSON(JSONObject object) {

		JSONValue jsonValue = object.get("dataCells");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<ReportDataCell> inarray = new ArrayList<ReportDataCell>();

			for (int i = 0; i < jsonArray.size(); i++) {

				ReportDataCell inObj = new ReportDataCell();
				inObj.fromJSON(jsonArray.get(i).isObject());
				inarray.add(inObj);
			}
			dataCells = inarray;
		}

	}
}
