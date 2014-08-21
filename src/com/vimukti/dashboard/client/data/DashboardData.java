package com.vimukti.dashboard.client.data;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

@SuppressWarnings("serial")
public class DashboardData extends MetaObject {

	private String backgroundEndColor;

	private ChartBackgroundDirection backgroundFadeDirection;

	private String backgroundStartColor;

	private List<DashboardFilters> dashboardFilters;

	private DashboardType dashboardType;

	private String description;

	private String fullName;

	private DashboardComponentSection leftSection;

	private DashboardComponentSection middleSection;

	private DashboardComponentSection rightSection;

	private String runningUser;

	private String textColor;

	private String title;

	private String titleColor;

	private int titleSize;

	private Folder saveToFolder;

	/**
	 * @return the backgroundEndColor
	 */
	public String getBackgroundEndColor() {
		return backgroundEndColor;
	}

	/**
	 * @param backgroundEndColor
	 *            the backgroundEndColor to set
	 */
	public void setBackgroundEndColor(String backgroundEndColor) {
		this.backgroundEndColor = backgroundEndColor;
	}

	/**
	 * @return the backgroundFadeDirection
	 */
	public ChartBackgroundDirection getBackgroundFadeDirection() {
		return backgroundFadeDirection;
	}

	/**
	 * @param backgroundFadeDirection
	 *            the backgroundFadeDirection to set
	 */
	public void setBackgroundFadeDirection(
			ChartBackgroundDirection backgroundFadeDirection) {
		this.backgroundFadeDirection = backgroundFadeDirection;
	}

	/**
	 * @return the backgroundStartColor
	 */
	public String getBackgroundStartColor() {
		return backgroundStartColor;
	}

	/**
	 * @param backgroundStartColor
	 *            the backgroundStartColor to set
	 */
	public void setBackgroundStartColor(String backgroundStartColor) {
		this.backgroundStartColor = backgroundStartColor;
	}

	/**
	 * @return the dashboardFilters
	 */
	public List<DashboardFilters> getDashboardFilters() {
		return dashboardFilters;
	}

	/**
	 * @param dashboardFilters
	 *            the dashboardFilters to set
	 */
	public void setDashboardFilters(List<DashboardFilters> dashboardFilters) {
		this.dashboardFilters = dashboardFilters;
	}

	/**
	 * @return the dashboardType
	 */
	public DashboardType getDashboardType() {
		return dashboardType;
	}

	/**
	 * @param dashboardType
	 *            the dashboardType to set
	 */
	public void setDashboardType(DashboardType dashboardType) {
		this.dashboardType = dashboardType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the leftSection
	 */
	public DashboardComponentSection getLeftSection() {
		return leftSection;
	}

	/**
	 * @param leftSection
	 *            the leftSection to set
	 */
	public void setLeftSection(DashboardComponentSection leftSection) {
		this.leftSection = leftSection;
	}

	/**
	 * @return the middleSection
	 */
	public DashboardComponentSection getMiddleSection() {
		return middleSection;
	}

	/**
	 * @param middleSection
	 *            the middleSection to set
	 */
	public void setMiddleSection(DashboardComponentSection middleSection) {
		this.middleSection = middleSection;
	}

	/**
	 * @return the rightSection
	 */
	public DashboardComponentSection getRightSection() {
		return rightSection;
	}

	/**
	 * @param rightSection
	 *            the rightSection to set
	 */
	public void setRightSection(DashboardComponentSection rightSection) {
		this.rightSection = rightSection;
	}

	/**
	 * @return the runningUser
	 */
	public String getRunningUser() {
		return runningUser;
	}

	/**
	 * @param runningUser
	 *            the runningUser to set
	 */
	public void setRunningUser(String runningUser) {
		this.runningUser = runningUser;
	}

	/**
	 * @return the textColor
	 */
	public String getTextColor() {
		return textColor;
	}

	/**
	 * @param textColor
	 *            the textColor to set
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the titleColor
	 */
	public String getTitleColor() {
		return titleColor;
	}

	/**
	 * @param titleColor
	 *            the titleColor to set
	 */
	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	/**
	 * @return the titleSize
	 */
	public int getTitleSize() {
		return titleSize;
	}

	/**
	 * @param titleSize
	 *            the titleSize to set
	 */
	public void setTitleSize(int titleSize) {
		this.titleSize = titleSize;
	}

	/**
	 * @return the saveToFolder
	 */
	public Folder getSaveToFolder() {
		return saveToFolder;
	}

	/**
	 * @param saveToFolder
	 *            the saveToFolder to set
	 */
	public void setSaveToFolder(Folder saveToFolder) {
		this.saveToFolder = saveToFolder;
	}

	/**
	 * @param dashBoardFolders
	 *            the dashBoardFolders to set
	 */

	@Override
	public void fromJSON(JSONObject jsonObject) {
		super.fromJSON(jsonObject);
		JSONValue jBackgroundEndColor = jsonObject.get("backgroundEndColor");
		if (jBackgroundEndColor != null) {
			backgroundEndColor = jBackgroundEndColor.isString().stringValue();
		}

		JSONValue jBackgroundFadeDirection = jsonObject
				.get("backgroundFadeDirection");
		if (jBackgroundFadeDirection != null) {
			String stringValue = jBackgroundFadeDirection.isString()
					.stringValue();
			ChartBackgroundDirection direction = ChartBackgroundDirection
					.getDirection(stringValue);
			backgroundFadeDirection = direction;
		}

		JSONValue jBackgroundStartColor = jsonObject
				.get("backgroundStartColor");
		if (jBackgroundStartColor != null) {
			backgroundStartColor = jBackgroundStartColor.isString()
					.stringValue();
		}

		JSONValue jDashboardFilters = jsonObject.get("dashboardFilters");
		if (jDashboardFilters != null) {
			JSONArray filtersArray = jDashboardFilters.isArray();
			List<DashboardFilters> jFiltersList = new ArrayList<DashboardFilters>();
			for (int i = 0; i < filtersArray.size(); i++) {
				DashboardFilters filter = new DashboardFilters();
				JSONValue jFiter = filtersArray.get(i);
				filter.fromJSON(jFiter.isObject());
				jFiltersList.add(filter);
			}
			dashboardFilters = jFiltersList;
		}

		JSONValue jDashboardType = jsonObject.get("dashboardType");
		if (jDashboardFilters != null) {
			String stringValue = jDashboardType.isString().stringValue();
			dashboardType = DashboardType.getDashboardType(stringValue);
		}

		JSONValue jDescription = jsonObject.get("description");
		if (jDescription != null) {
			description = jDescription.isString().stringValue();
		}

		JSONValue jFullName = jsonObject.get("fullName");
		if (jDescription != null) {
			fullName = jFullName.isString().stringValue();
		}

		JSONValue jRightSection = jsonObject.get("rightSection");
		if (jRightSection != null) {
			DashboardComponentSection jRightSetinObjectCreation = new DashboardComponentSection();
			jRightSetinObjectCreation.fromJSON(jRightSection.isObject());
			rightSection = jRightSetinObjectCreation;
		}

		JSONValue jLeftSection = jsonObject.get("leftSection");
		if (jRightSection != null) {
			DashboardComponentSection jleftSectionObj = new DashboardComponentSection();
			jleftSectionObj.fromJSON(jLeftSection.isObject());
			rightSection = jleftSectionObj;
		}

		JSONValue jMiddleSection = jsonObject.get("middleSection");
		if (jRightSection != null) {
			DashboardComponentSection jMiddleSectionObj = new DashboardComponentSection();
			jMiddleSectionObj.fromJSON(jMiddleSection.isObject());
			rightSection = jMiddleSectionObj;
		}

		JSONValue jRunningUser = jsonObject.get("runningUser");
		if (jRunningUser != null) {
			runningUser = jRunningUser.isString().stringValue();
		}

		JSONValue jTextColor = jsonObject.get("textColor");
		if (jTextColor != null) {
			textColor = jTextColor.isString().stringValue();
		}

		JSONValue jTitle = jsonObject.get("title");
		if (jTitle != null) {
			title = jTitle.isString().stringValue();
		}

		JSONValue jTitleColor = jsonObject.get("titleColor");
		if (jTitleColor != null) {
			titleColor = jTitleColor.isString().stringValue();
		}

		JSONValue jTitleSize = jsonObject.get("titleSize");
		double jTiltleS = jTitleSize.isNumber().doubleValue();
		titleSize = (int) jTiltleS;

		JSONValue jSaveToFolder = jsonObject.get("saveToFolder");
		if (jSaveToFolder != null) {
			Folder fold = new Folder();
			fold.fromJSON(jSaveToFolder.isObject());
			saveToFolder = fold;
		}

	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		if (backgroundEndColor != null) {
			json.put("backgroundEndColor", new JSONString(backgroundEndColor));
		}
		if (backgroundFadeDirection != null) {
			String string = backgroundFadeDirection.toString();
			json.put("backgroundFadeDirection", new JSONString(string));
		}
		if (backgroundStartColor != null) {
			json.put("backgroundStartColor ", new JSONString(
					backgroundStartColor));
		}
		JSONArray filtersArray = new JSONArray();
		int index = 0;
		if (dashboardFilters != null) {
			for (DashboardFilters filter : dashboardFilters) {
				if (filter != null) {
					filtersArray.set(index++, filter.toJSON());
				}
			}
			json.put("dashboardFilters", filtersArray);
		}

		if (dashboardType != null) {
			String string = dashboardType.toString();
			json.put("dashboardType", new JSONString(string));
		}

		if (description != null) {
			json.put("description", new JSONString(description));
		}

		if (fullName != null) {
			json.put("fullName", new JSONString(fullName));
		}

		if (leftSection != null) {
			json.put("leftSection", leftSection.toJSON());
		}

		if (middleSection != null) {
			json.put("middleSection", middleSection.toJSON());
		}

		if (rightSection != null) {
			json.put("rightSection", rightSection.toJSON());
		}
		if (runningUser != null) {
			json.put("runningUser", new JSONString(runningUser));
		}
		if (textColor != null) {
			json.put("textColor", new JSONString(textColor));
		}
		if (title != null) {
			json.put("title", new JSONString(title));
		}
		if (titleColor != null) {
			json.put("titleColor", new JSONString(titleColor));
		}
		json.put("titleSize", new JSONNumber(titleSize));

		if (saveToFolder != null) {
			json.put("saveToFolder", saveToFolder.toJSON());
		}
		return json;
	}
}
