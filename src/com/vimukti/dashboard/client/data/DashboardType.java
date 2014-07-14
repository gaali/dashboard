package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum DashboardType {

	@XmlEnumValue("SpecifiedUser")
	SPECIFIED_USER,

	@XmlEnumValue("LoggedInUser")
	LOGGED_IN_USER,

	@XmlEnumValue("MyTeamUser")
	MY_TEAM_USER,

}
