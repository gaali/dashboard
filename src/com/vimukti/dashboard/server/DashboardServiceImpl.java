package com.vimukti.dashboard.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vimukti.dashboard.client.data.Dashboard;
import com.vimukti.dashboard.client.data.Folder;
import com.vimukti.dashboard.client.data.IDashboardService;
import com.vimukti.dashboard.client.data.PagesList;
import com.vimukti.dashboard.client.data.Report;
import com.vimukti.dashboard.client.data.ReportsAndPagesList;

@SuppressWarnings("serial")
public class DashboardServiceImpl extends RemoteServiceServlet implements
		IDashboardService {

	@Override
	public ReportsAndPagesList getReportList() {
		// TODO Auto-generated method stub
		ReportsAndPagesList b = new ReportsAndPagesList();

		return b;
	}

	@Override
	public Dashboard getDashboard() {
		Dashboard d = new Dashboard();
		return d;
	}

	@Override
	public List<Folder> getDashBoarFolders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagesList getPage(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report getReport(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDashBoard(Dashboard dashboard) {
		// TODO Auto-generated method stub

	}

}
