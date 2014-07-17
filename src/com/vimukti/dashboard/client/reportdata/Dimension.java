package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class Dimension {

	private List<GroupingValue> groupings;

	public List<GroupingValue> getGroupings() {
		return groupings;
	}

	public void fromJSON(JSONObject object) {
		JSONValue jsonValue = object.get("groupings");
		if (jsonValue != null) {

			JSONArray jsonArray = jsonValue.isArray();
			ArrayList<GroupingValue> inarray = new ArrayList<GroupingValue>();

			for (int i = 0; i < jsonArray.size(); i++) {

				GroupingValue inObj = new GroupingValue();
				inObj.fromJSON(jsonArray.get(i).isObject());
				inarray.add(inObj);
			}
			groupings = inarray;
		}

	}

	public JSONValue toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (groupings != null) {
			JSONArray jsonArray = new JSONArray();
			int index = 0;
			for (GroupingValue inArr : groupings) {

				if (inArr != null) {
					jsonArray.set(index++, inArr.toJSON());
				}

			}
			jsonObject.put("groupings", jsonArray);
		}
		return jsonObject;
	}
}
