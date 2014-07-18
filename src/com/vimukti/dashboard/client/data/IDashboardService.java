package com.vimukti.dashboard.client.data;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.vimukti.dashboard.client.reportdata.Report;

public interface IDashboardService extends RemoteService {

	ReportsAndPagesList getReportsAndPagesList(ReportsAndPageListType type);

	DashboardData getDashboard();

	List<Folder> getDashBoarFolders();

	String getPage(String id);

	Report getReport(String id);

	void saveDashBoard(DashboardData dashboard);
}
