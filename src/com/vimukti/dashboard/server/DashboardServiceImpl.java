package com.vimukti.dashboard.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.data.Folder;
import com.vimukti.dashboard.client.data.IDashboardService;
import com.vimukti.dashboard.client.data.ReportsAndPageListType;
import com.vimukti.dashboard.client.data.ReportsAndPagesList;
import com.vimukti.dashboard.client.reportdata.Report;

@SuppressWarnings("serial")
public class DashboardServiceImpl extends RemoteServiceServlet implements
		IDashboardService {

	@Override
	public ReportsAndPagesList getReportsAndPagesList(
			ReportsAndPageListType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DashboardData getDashboard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Folder> getDashBoarFolders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPage(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report getReport(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDashBoard(DashboardData dashboard) {
		// TODO Auto-generated method stub

	}

}
