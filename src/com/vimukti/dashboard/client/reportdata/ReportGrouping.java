package com.vimukti.dashboard.client.reportdata;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportGrouping extends MetaObject {

	private ReportAggrType aggregateType;

	private UserDateGranularity dateGranularity;

	private String field;

	public ReportAggrType getAggregateType() {
		return aggregateType;
	}

	public void setAggregateType(ReportAggrType aggregateType) {
		this.aggregateType = aggregateType;
	}

	public UserDateGranularity getDateGranularity() {
		return dateGranularity;
	}

	public void setDateGranularity(UserDateGranularity dateGranularity) {
		this.dateGranularity = dateGranularity;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jAggregateType = jsonObject.get("aggregateType");
		if (jAggregateType != null) {
			String stringValue = jAggregateType.isString().stringValue();
			aggregateType = ReportAggrType.getReportAggrType(stringValue);
		}

		JSONValue jDateGranularity = jsonObject.get("dateGranularity");
		if (jDateGranularity != null) {
			String stringValue = jDateGranularity.isString().stringValue();
			dateGranularity = UserDateGranularity.get(stringValue);
		}

		JSONValue jField = jsonObject.get("field");
		if (jField != null) {
			field = jField.isString().stringValue();
		}

	}
}
