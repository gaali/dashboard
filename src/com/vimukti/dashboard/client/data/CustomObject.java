package com.vimukti.dashboard.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class CustomObject implements IJSONData {

	/**
	 * name of the this object
	 */
	private String name;
	/**
	 * list of this object fields
	 */
	private List<Field> fields;

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
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		JSONValue jName = jsonObject.get("name");
		if (jName != null) {
			name = jName.isString().stringValue();
		}
		JSONValue jsonValue = jsonObject.get("fields");
		List<Field> fieldsList = new ArrayList<Field>();
		if (jsonValue != null) {
			JSONArray fieldsArray = jsonValue.isArray();
			for (int i = 0; i < fieldsArray.size(); i++) {
				Field field = new Field();
				JSONValue jsonValue2 = fieldsArray.get(i);
				field.fromJSON(jsonValue2.isObject());
				fieldsList.add(field);
			}
		}
		fields = fieldsList;
	}

	@Override
	public JSONObject toJSON() {
		// this object will not send to server so no need to implement this
		// method
		return null;
	}

}
