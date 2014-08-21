package com.vimukti.dashboard.client.data;

public enum DashboardType {

	SPECIFIED_USER("SpecifiedUser"),

	LOGGED_IN_USER("LoggedInUser"),

	MY_TEAM_USER("MyTeamUser");

	private String name;

	private DashboardType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static DashboardType getDashboardType(String valeu) {
		for (DashboardType type : DashboardType.values()) {
			if (type.name.equals(valeu)) {
				return type;
			}
		}
		return null;
	}
}
