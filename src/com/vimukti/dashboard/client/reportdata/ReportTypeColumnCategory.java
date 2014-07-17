package com.vimukti.dashboard.client.reportdata;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class ReportTypeColumnCategory {

	private String label;
	private Map<String, ReportTypeColumn> columns;

	/**
	 * Returns information for all fields in the report type. The information is
	 * organized by each section’s unique API name.
	 * 
	 * @return
	 */
	public Map<String, ReportTypeColumn> getColumns() {
		// TODO
		return columns;
	}

	/**
	 * Returns the localized display name of a section in the report type under
	 * which fields are organized. For example, in an Accounts with Contacts
	 * custom report type, Account General is the display name of the section
	 * that contains fields on general account information.
	 * 
	 * @return
	 */
	public String getLabel() {
		// TODO
		return label;
	}

	public void fromJSON(JSONObject object) {

		JSONValue jsonValue = object.get("label");
		if (jsonValue != null) {
			label = jsonValue.isString().stringValue();
		}
		jsonValue = object.get("columns");
		if (jsonValue != null) {

			Map<String, ReportTypeColumn> inMap = new TreeMap<String, ReportTypeColumn>();
			JSONObject object2 = jsonValue.isObject();
			Set<String> keySet = object2.keySet();
			java.util.Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				ReportTypeColumn inobj = new ReportTypeColumn();
				String key = iterator.next();
				JSONObject object3 = object2.get(key).isObject();
				inobj.fromJSON(object3);
				inMap.put(key, inobj);
			}
			columns = inMap;
		}

	}

	public JSONValue toJSON() {
		JSONObject jsonObject = new JSONObject();
		if (label != null) {
			jsonObject.put("label", new JSONString(label));
		}

		if (columns != null) {
			for (Entry<String, ReportTypeColumn> entry : columns.entrySet()) {

				jsonObject.put(entry.getKey(), entry.getValue().toJSON());
			}

		}
		return jsonObject;
	}

}
