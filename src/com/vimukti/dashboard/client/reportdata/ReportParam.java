package com.vimukti.dashboard.client.reportdata;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportParam extends MetaObject {

	private String name;

	private String value;

	public ReportParam() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
