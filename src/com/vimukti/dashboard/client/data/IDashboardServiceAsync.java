package com.vimukti.dashboard.client.data;

import java.util.List;

import com.gargoylesoftware.htmlunit.Page;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IDashboardServiceAsync {
	// this method get fields depending on Report fields and dashboard type
	// dashboard parameter(this parameter for permission base)
	// need to one more add parameter (report type or report fields object) to
	// what object fields to show

	public void getReportList(AsyncCallback<ReportsAndPagesList> callback);

	void getDashboard(AsyncCallback<Dashboard> callback);

	void getDashBoarFolders(AsyncCallback<List<Folder>> callback);

	void getPage(String id, AsyncCallback<String> callback);

	void saveDashBoard(Dashboard dashboard, AsyncCallback<Void> callback);

	void getReport(String id, AsyncCallback<Report> callback);
}
