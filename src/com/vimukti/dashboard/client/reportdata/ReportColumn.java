package com.vimukti.dashboard.client.reportdata;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.vimukti.dashboard.client.data.MetaObject;
import com.vimukti.dashboard.client.data.ReportSummaryType;

@SuppressWarnings("serial")
public class ReportColumn extends MetaObject {

	// ReportSummaryType
	private List<ReportSummaryType> aggregateTypes;

	private String field;

	private boolean reverseColors;

	private boolean showChanges;

	public ReportColumn() {
	}

	public List<ReportSummaryType> getAggregateTypes() {
		return aggregateTypes;
	}

	public void setAggregateTypes(List<ReportSummaryType> aggregateTypes) {
		this.aggregateTypes = aggregateTypes;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isReverseColors() {
		return reverseColors;
	}

	public void setReverseColors(boolean reverseColors) {
		this.reverseColors = reverseColors;
	}

	public boolean isShowChanges() {
		return showChanges;
	}

	public void setShowChanges(boolean showChanges) {
		this.showChanges = showChanges;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jAggregateTypes = jsonObject.get("aggregateTypes");
		if (jAggregateTypes != null) {
			JSONArray aggretArray = jAggregateTypes.isArray();
			List<ReportSummaryType> aggregateTypesList = null;
			if (aggretArray != null) {
				aggregateTypesList = new ArrayList<ReportSummaryType>();
				for (int i = 0; i < aggretArray.size(); i++) {
					JSONValue jAggregateType = aggretArray.get(i);
					String stringValue = jAggregateType.isString()
							.stringValue();
					ReportSummaryType summaryType = ReportSummaryType
							.getSummaryType(stringValue);
					aggregateTypesList.add(summaryType);
				}
			}
			aggregateTypes = aggregateTypesList;
		}

		JSONValue jField = jsonObject.get("field");
		if (jField != null) {
			field = jField.isString().stringValue();
		}

		JSONValue jReverseColors = jsonObject.get("reverseColors");
		if (jReverseColors != null) {
			reverseColors = jReverseColors.isBoolean().booleanValue();
		}

		JSONValue jShowChanges = jsonObject.get("showChanges");
		if (jShowChanges != null) {
			showChanges = jShowChanges.isBoolean().booleanValue();
		}

	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		JSONArray aggregatesArray = new JSONArray();
		if (aggregateTypes != null) {
			int index = 0;
			for (ReportSummaryType type : aggregateTypes) {
				if (type != null) {
					String string = type.toString();
					aggregatesArray.set(index++, new JSONString(string));
				}
			}
		}
		json.put("aggregateTypes", aggregatesArray);
		if (field != null) {
			json.put("field", new JSONString(field));
		}
		json.put("reverseColors", JSONBoolean.getInstance(reverseColors));
		json.put("showChanges", JSONBoolean.getInstance(showChanges));
		return json;
	}
}
