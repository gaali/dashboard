package com.vimukti.dashboard.client.reportdata;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class ReportResults {

	private Boolean allData;
	private Map<String, ReportFact> factMap;
	private Dimension groupingAcross;
	private Dimension grouingDown;
	private Boolean hasDetailRows;
	private ReportExtendedMetadata reportExtendedMetadata;
	private ReportMetadata reportMetadata;

	/**
	 * Returns all report data.
	 * 
	 * @return
	 */
	public Boolean getAllData() {
		return allData;
	}

	/**
	 * Returns summary-level data or summary and detailed data for each row or
	 * column grouping. Detailed data is available if the includeDetails
	 * parameter is set to true when the report is run.
	 * 
	 * @return
	 */
	public Map<String, ReportFact> getFactMap() {
		return factMap;
	}

	/**
	 * Returns a collection of column groupings, keys, and values.
	 * 
	 * @return
	 */
	public Dimension getGroupingsAcross() {
		return groupingAcross;
	}

	/**
	 * Returns a collection of row groupings, keys, and values.
	 * 
	 * @return
	 */
	public Dimension getGroupingsDown() {
		return grouingDown;
	}

	/**
	 * Returns information about whether the fact map has detail rows.
	 * 
	 * @return
	 */
	public Boolean getHasDetailRows() {
		return hasDetailRows;
	}

	/**
	 * Returns additional, detailed metadata about the report, including data
	 * type and label information for groupings and summaries.
	 * 
	 * @return
	 */
	public ReportExtendedMetadata getReportExtendedMetadata() {
		return reportExtendedMetadata;
	}

	/**
	 * Returns metadata about the report, including grouping and summary
	 * information.
	 * 
	 * @return
	 */
	public ReportMetadata getReportMetadata() {
		return reportMetadata;
	}

	public void fromJSON(JSONObject object) {

		allData = object.get("allData").isBoolean().booleanValue();
		JSONValue jsonValue = object.get("groupingAcross");
		if (jsonValue != null) {

			Dimension in = new Dimension();
			in.fromJSON(jsonValue.isObject());
			groupingAcross = in;
		}
		jsonValue = object.get("grouingDown");
		if (jsonValue != null) {

			Dimension in = new Dimension();
			in.fromJSON(jsonValue.isObject());
			grouingDown = in;
		}
		hasDetailRows = object.get("hasDetailRows").isBoolean().booleanValue();
		jsonValue = object.get("reportExtendedMetadata");
		if (jsonValue != null) {

			ReportExtendedMetadata in = new ReportExtendedMetadata();
			in.fromJSON(jsonValue.isObject());
			reportExtendedMetadata = in;
		}
		jsonValue = object.get("reportMetadata");
		if (jsonValue != null) {

			ReportMetadata in = new ReportMetadata();
			in.fromJSON(jsonValue.isObject());
			reportMetadata = in;
		}

		JSONObject factMapObject = object.get("factMap").isObject();

		if (factMapObject != null) {
			ReportFormat format = reportMetadata.getReportFormat();
			factMap = new TreeMap<String, ReportFact>();

			if (format == ReportFormat.SUMMARY) {
				Comparator<String> comparator = getComparator();
				factMap = new TreeMap<String, ReportFact>(comparator);
			}

			for (String key : factMapObject.keySet()) {
				JSONValue factMapValue = factMapObject.get(key);

				JSONObject reportFactObj = factMapValue.isObject();

				ReportFactWithDetails reportFact = new ReportFactWithDetails();
				reportFact.fromJSON(reportFactObj);

				factMap.put(key, reportFact);
			}

		}
	}

	private Comparator<String> getComparator() {

		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {

				String[] arg1 = o1.split("!");
				String[] arg2 = o2.split("!");

				// we are sorting just on grouping down only
				String[] split = arg1[0].split("_");
				String[] split2 = arg2[0].split("_");

				for (int i = 0; i < split.length; i++) {
					String string = split[i];

					if (string.equals("T")) {
						return 1;
					}

					if (i < split2.length) {
						String string2 = split2[i];

						Integer integer = Integer.valueOf(string);
						Integer integer2 = Integer.valueOf(string2);

						int compareTo = integer.compareTo(integer2);

						if (compareTo != 0) {
							return compareTo;
						}

					}

				}

				return (split.length < split2.length) == true ? -1 : 1;
			}
		};
		return comparator;
	}

	public JSONValue toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allData", JSONBoolean.getInstance(allData));
		if (groupingAcross != null) {

			jsonObject.put("groupingAcross", groupingAcross.toJSON());
		}
		if (grouingDown != null) {

			jsonObject.put("grouingDown", grouingDown.toJSON());
		}
		jsonObject.put("hasDetailRows", JSONBoolean.getInstance(hasDetailRows));
		if (reportExtendedMetadata != null) {

			jsonObject.put("reportExtendedMetadata",
					reportExtendedMetadata.toJSON());
		}
		if (reportMetadata != null) {

			jsonObject.put("reportMetadata", reportMetadata.toJSON());
		}

		if (factMap != null) {
			JSONObject factMapObj = new JSONObject();

			for (String key : factMap.keySet()) {

				ReportFact reportFact = factMap.get(key);

				if (reportFact instanceof ReportFactWithDetails) {
					factMapObj.put(key,
							((ReportFactWithDetails) reportFact).toJSON());
					continue;
				}
				factMapObj.put(key, reportFact.toJSON());
			}

			jsonObject.put("factMap", factMapObj);
		}
		return jsonObject;
	}
}
