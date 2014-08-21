package com.vimukti.dashboard.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings("serial")
public class DashboardFilters extends MetaObject {

	private List<DashboardFilterOptions> dashboardFilterOptions;

	private String name;

	private String displayLabel;

	/**
	 * @return the dashboardFilterOptions
	 */
	public List<DashboardFilterOptions> getDashboardFilterOptions() {
		return dashboardFilterOptions;
	}

	/**
	 * @param dashboardFilterOptions
	 *            the dashboardFilterOptions to set
	 */
	public void setDashboardFilterOptions(
			List<DashboardFilterOptions> dashboardFilterOptions) {
		this.dashboardFilterOptions = dashboardFilterOptions;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the displayLabel
	 */
	public String getDisplayLabel() {
		return displayLabel;
	}

	/**
	 * @param displayLabel
	 *            the displayLabel to set
	 */
	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jDashboardFilterOptions = jsonObject
				.get("dashboardFilterOptions");
		if (jDashboardFilterOptions != null) {
			JSONArray filterOptionsArray = jDashboardFilterOptions.isArray();
			List<DashboardFilterOptions> filterOptionsList = new ArrayList<DashboardFilterOptions>();
			for (int i = 0; i < filterOptionsArray.size(); i++) {
				DashboardFilterOptions option = new DashboardFilterOptions();
				JSONValue jOption = filterOptionsArray.get(i);
				option.fromJSON(jOption.isObject());
				filterOptionsList.add(option);
			}
			dashboardFilterOptions = filterOptionsList;
		}

		JSONValue jName = jsonObject.get(name);
		if (jName != null) {
			name = jName.isString().stringValue();
		}

		JSONValue jdisplayLabel = jsonObject.get("displayLabel");
		if (jdisplayLabel != null) {
			displayLabel = jdisplayLabel.isString().stringValue();
		}
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		JSONArray filterOptionsArray = new JSONArray();
		int index = 0;
		if (dashboardFilterOptions != null) {
			for (DashboardFilterOptions options : dashboardFilterOptions) {
				filterOptionsArray.set(index++, options.toJSON());
			}
		}
		json.put("dashboardFilterOptions", filterOptionsArray);
		if (name != null) {
			json.put("name", new JSONString(name));
		}
		if (displayLabel != null) {
			json.put("displayLabel", new JSONString(displayLabel));
		}
		return json;
	}
}
