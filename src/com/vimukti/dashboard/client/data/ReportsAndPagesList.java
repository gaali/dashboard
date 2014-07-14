package com.vimukti.dashboard.client.data;

import java.util.List;

@SuppressWarnings("serial")
public class ReportsAndPagesList extends MetaObject {

	private List<Folder> folders;
	private List<PagesList> pages;

	/**
	 * @return the folders
	 */
	public List<Folder> getFolders() {
		return folders;
	}

	/**
	 * @param folders
	 *            the folders to set
	 */
	public void setFolders(List<Folder> folders) {
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

}
