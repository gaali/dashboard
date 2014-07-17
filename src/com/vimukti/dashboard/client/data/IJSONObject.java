package com.vimukti.dashboard.client.data;

import com.google.gwt.json.client.JSONObject;

public interface IJSONObject {
	void fromJSON(JSONObject jsonObject);

	JSONObject toJSON();
}
