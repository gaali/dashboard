package com.vimukti.dashboard.client.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings("serial")
public class ReportsAndPagesList extends MetaObject {

	private Map<String, List<ReportDetails>> folders;
	private List<PagesList> pages;

	/**
	 * @return the folders
	 */
	public Map<String, List<ReportDetails>> getFolders() {
		return folders;
	}

	/**
	 * @param folders
	 *            the folders to set
	 */
	public void setFolders(Map<String, List<ReportDetails>> folders) {
		this.folders = folders;
	}

	/**
	 * @return the pages
	 */
	public List<PagesList> getPages() {
		return pages;
	}

	/**
	 * @param pages
	 *            the pages to set
	 */
	public void setPages(List<PagesList> pages) {
		this.pages = pages;
	}

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jsonValue = jsonObject.get("folders");
		if (jsonValue != null) {
			Map<String, List<ReportDetails>> foldersMap = new HashMap<String, List<ReportDetails>>();
			JSONObject object = jsonValue.isObject();
			for (String string : object.keySet()) {
				JSONValue jsonList = object.get(string);
				JSONArray foldersArray = jsonList.isArray();
				ArrayList<ReportDetails> arrayList = new ArrayList<ReportDetails>();
				for (int i = 0; i < foldersArray.size(); i++) {
					ReportDetails folderObj = new ReportDetails();
					JSONValue jsonValue2 = foldersArray.get(i);
					folderObj.fromJSON(jsonValue2.isObject());
					arrayList.add(folderObj);
				}
				foldersMap.put(string, arrayList);
			}
			folders = foldersMap;
		}

		JSONValue jsonValue2 = jsonObject.get("pages");
		List<PagesList> jpages = null;
		if (jsonValue2 != null) {
			JSONArray pagesArray = jsonValue2.isArray();
			jpages = new ArrayList<PagesList>();
			for (int i = 0; i < pagesArray.size(); i++) {
				PagesList pageList = new PagesList();
				JSONValue jPageList = pagesArray.get(i);
				pageList.fromJSON(jPageList.isObject());
				pages.add(pageList);
			}

		}
		pages = jpages;
	}

}
