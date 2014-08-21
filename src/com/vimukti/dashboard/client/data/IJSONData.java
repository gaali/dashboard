package com.vimukti.dashboard.client.data;

import com.google.gwt.json.client.JSONObject;

public interface IJSONData {

	void fromJSON(JSONObject value);

	JSONObject toJSON();
}
