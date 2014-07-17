package com.vimukti.dashboard.client.reportdata;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class ReportExtendedMetadata {

	private Map<String, AggregateColumn> aggregateColumnInfo;
	private Map<String, DetailColumn> detailColumnInfo;
	private Map<String, GroupingColumn> groupingColumnInfo;

	/**
	 * Returns all report summaries such as Record Count , Sum , Average , Max ,
	 * Min , and custom summary formulas. Contains values for each summary that
	 * is listed in the report metadata.
	 * 
	 * @return
	 */
	public Map<String, AggregateColumn> getAggregateColumnInfo() {
		return aggregateColumnInfo;
	}

	public void setAggregateColumnInfo(
			Map<String, AggregateColumn> aggregateColumnInfo) {
		this.aggregateColumnInfo = aggregateColumnInfo;
	}

	/**
	 * Returns a map of two properties for each field that has detailed data
	 * identified by its unique API name. The detailed data fields are also
	 * listed in the report metadata.
	 * 
	 * @return
	 */
	public Map<String, DetailColumn> getDetailColumnInfo() {
		return detailColumnInfo;
	}

	public void setDetailColumnInfo(Map<String, DetailColumn> detailColumnInfo) {
		this.detailColumnInfo = detailColumnInfo;
	}

	/**
	 * Returns a map of each row or column grouping to its metadata. Contains
	 * values for each grouping that is identified in the groupingsDown and
	 * groupingsAcross lists.
	 * 
	 * @return
	 */
	public Map<String, GroupingColumn> getGroupingColumnInfo() {
		return groupingColumnInfo;
	}

	public void setGroupingColumnInfo(
			Map<String, GroupingColumn> groupingColumnInfo) {
		this.groupingColumnInfo = groupingColumnInfo;
	}

	public void fromJSON(JSONObject object) {

		JSONValue jsonValue = object.get("aggregateColumnInfo");
		if (jsonValue != null) {

			Map<String, AggregateColumn> inMap = new TreeMap<String, AggregateColumn>();
			JSONObject object2 = jsonValue.isObject();
			Set<String> keySet = object2.keySet();
			java.util.Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				AggregateColumn inobj = new AggregateColumn();
				String key = iterator.next();
				JSONObject object3 = object2.get(key).isObject();
				inobj.fromJSON(object3);
				inMap.put(key, inobj);
			}
			aggregateColumnInfo = inMap;
		}
		jsonValue = object.get("detailColumnInfo");
		if (jsonValue != null) {

			Map<String, DetailColumn> inMap = new TreeMap<String, DetailColumn>();
			JSONObject object2 = jsonValue.isObject();
			Set<String> keySet = object2.keySet();
			java.util.Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				DetailColumn inobj = new DetailColumn();
				String key = iterator.next();
				JSONObject object3 = object2.get(key).isObject();
				inobj.fromJSON(object3);
				inMap.put(key, inobj);
			}
			detailColumnInfo = inMap;
		}

		jsonValue = object.get("groupingColumnInfo");
		if (jsonValue != null) {

			Map<String, GroupingColumn> inMap = new TreeMap<String, GroupingColumn>();
			JSONObject object2 = jsonValue.isObject();
			Set<String> keySet = object2.keySet();
			java.util.Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				GroupingColumn inobj = new GroupingColumn();
				String key = iterator.next();
				JSONObject object3 = object2.get(key).isObject();
				inobj.fromJSON(object3);
				inMap.put(key, inobj);
			}
			groupingColumnInfo = inMap;
		}

	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();

		if (aggregateColumnInfo != null) {
			JSONObject jsonObject2 = new JSONObject();
			for (Entry<String, AggregateColumn> entry : aggregateColumnInfo
					.entrySet()) {

				jsonObject2.put(entry.getKey(), entry.getValue().toJSON());
			}
			jsonObject.put("aggregateColumnInfo", jsonObject2);
		}
		if (detailColumnInfo != null) {
			JSONObject jsonObject2 = new JSONObject();
			for (Entry<String, DetailColumn> entry : detailColumnInfo
					.entrySet()) {

				jsonObject2.put(entry.getKey(), entry.getValue().toJSON());
			}
			jsonObject.put("detailColumnInfo", jsonObject2);
		}
		if (groupingColumnInfo != null) {
			JSONObject jsonObject2 = new JSONObject();
			for (Entry<String, GroupingColumn> entry : groupingColumnInfo
					.entrySet()) {

				jsonObject2.put(entry.getKey(), entry.getValue().toJSON());
			}
			jsonObject.put("groupingColumnInfo", jsonObject2);
		}
		return jsonObject;
	}
}
