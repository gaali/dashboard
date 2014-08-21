package com.vimukti.dashboard.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings("serial")
public class DashboardComponentSection extends MetaObject {

	private DashboardComponentSize columnSize;

	private List<DashboardComponent> components;

	/**
	 * @return the columnSize
	 */
	public DashboardComponentSize getColumnSize() {
		return columnSize;
	}

	/**
	 * @param columnSize
	 *            the columnSize to set
	 */
	public void setColumnSize(DashboardComponentSize columnSize) {
		this.columnSize = columnSize;
	}

	/**
	 * @return the components
	 */
	public List<DashboardComponent> getComponents() {
		return components;
	}

	/**
	 * @param components
	 *            the components to set
	 */
	public void setComponents(List<DashboardComponent> components) {
		this.components = components;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jColumnSize = jsonObject.get("columnSize");
		if (jColumnSize != null) {
			String stringValue = jColumnSize.isString().stringValue();
			DashboardComponentSize jComponentSizeObj = DashboardComponentSize
					.getComponentSize(stringValue);
			columnSize = jComponentSizeObj;
		}

		JSONValue jComponents = jsonObject.get("components");
		if (jComponents != null) {
			JSONArray jComponentsArray = jComponents.isArray();
			List<DashboardComponent> jComponentsList = new ArrayList<DashboardComponent>();
			for (int i = 0; i < jComponentsList.size(); i++) {
				DashboardComponent component = new DashboardComponent();
				JSONValue jsonValue = jComponentsArray.get(i);
				component.fromJSON(jsonValue.isObject());
				jComponentsList.add(component);
			}
			components = jComponentsList;
		}
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		if (columnSize != null) {
			String string = columnSize.toString();
			json.put("columnSize", new JSONString(string));
		}

		JSONArray componentsArray = new JSONArray();
		int index = 0;
		if (components != null) {
			for (DashboardComponent dc : components) {
				if (dc != null) {
					componentsArray.set(index++, dc.toJSON());
				}
			}
		}
		json.put("components", componentsArray);
		return json;
	}

}
