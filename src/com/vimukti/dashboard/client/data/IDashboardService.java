package com.vimukti.dashboard.client.data;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

public interface IDashboardService extends RemoteService {

	ReportsAndPagesList getReportList();

	Dashboard getDashboard();

	List<Folder> getDashBoarFolders();

	PagesList getPage(String id);

	Report getReport(String id);

	void saveDashBoard(Dashboard dashboard);
}
