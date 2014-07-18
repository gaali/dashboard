package com.vimukti.dashboard.client.data;

import java.util.List;

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

	private List<Folder> dashBoardFolders;

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
	 * @return the dashBoardFolders
	 */
	public List<Folder> getDashBoardFolders() {
		return dashBoardFolders;
	}

	/**
	 * @param dashBoardFolders
	 *            the dashBoardFolders to set
	 */
	public void setDashBoardFolders(List<Folder> dashBoardFolders) {
		this.dashBoardFolders = dashBoardFolders;
	}

}
