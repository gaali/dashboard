package com.vimukti.dashboard.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings("serial")
public class DashboardFilterOptions extends MetaObject {

	private DashboardFilterOperation operator;

	private String value;

	private List<String> values;

	/**
	 * @return the operator
	 */
	public DashboardFilterOperation getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(DashboardFilterOperation operator) {
		this.operator = operator;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jOperator = jsonObject.get("operator");
		if (jOperator != null) {
			String stringValue = jOperator.isString().stringValue();
			operator = DashboardFilterOperation.getFilterOperation(stringValue);
		}

		JSONValue jValue = jsonObject.get("value");
		if (jValue != null) {
			value = jValue.isString().stringValue();
		}

		JSONValue jValues = jsonObject.get("values");
		if (jValues != null) {
			JSONArray valuesArray = jValues.isArray();
			List<String> valuesList = new ArrayList<String>();
			if (valuesArray != null) {
				for (int i = 0; i < valuesArray.size(); i++) {
					String value = "";
					JSONValue jValueString = valuesArray.get(i);
					value = jValueString.isString().stringValue();
					valuesList.add(value);
				}
				values = valuesList;
			}
		}
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		if (operator != null) {
			String string = operator.toString();
			json.put("operator", new JSONString(string));
		}
		if (value != null) {
			json.put("operator", new JSONString(value));
		}

		JSONArray valuesArray = new JSONArray();
		int index = 0;
		if (values != null) {
			for (String val : values) {
				valuesArray.set(index++, new JSONString(val));
			}
		}
		json.put("values", valuesArray);

		return json;
	}

}
