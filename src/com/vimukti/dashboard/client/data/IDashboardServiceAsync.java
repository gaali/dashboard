package com.vimukti.dashboard.client.data;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.vimukti.dashboard.client.reportdata.Report;

public interface IDashboardServiceAsync {
	// this method get fields depending on Report fields and dashboard type
	// dashboard parameter(this parameter for permission base)
	// need to one more add parameter (report type or report fields object) to
	// what object fields to show

	void getReportsAndPagesList(ReportsAndPageListType type,
			AsyncCallback<ReportsAndPagesList> callback);

	void getDashboard(AsyncCallback<DashboardData> callback);

	void getDashBoarFolders(AsyncCallback<List<Folder>> callback);

	void getPage(String id, AsyncCallback<String> callback);

	void saveDashBoard(DashboardData dashboard, AsyncCallback<Void> callback);

	void getReport(String id, AsyncCallback<Report> callback);
}
