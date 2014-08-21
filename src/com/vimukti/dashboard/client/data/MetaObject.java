package com.vimukti.dashboard.client.data;

import java.io.Serializable;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class MetaObject implements IsSerializable, Serializable, IJSONData {
	private String id;
	private String name;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	@Override
	public void fromJSON(JSONObject jsonObject) {
		JSONValue jsonValue = jsonObject.get("id");
		if (jsonValue != null) {
			id = jsonValue.isString().stringValue();
		}
		jsonValue = jsonObject.get("name");
		if (jsonValue != null) {
			name = jsonValue.isString().stringValue();
		}
	}

	@Override
	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		if (id != null) {
			jsonObject.put("id", new JSONString(id));
		}
		if (name != null) {
			jsonObject.put("lastModifiedBy", new JSONString(name));
		}
		return jsonObject;
	}

	protected MetaObject clone(MetaObject mObjc) {
		mObjc.id = this.id;
		mObjc.name = this.name;
		return mObjc;
	}

}
