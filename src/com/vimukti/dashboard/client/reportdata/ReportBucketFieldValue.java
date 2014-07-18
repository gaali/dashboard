package com.vimukti.dashboard.client.reportdata;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportBucketFieldValue extends MetaObject {

	// ReportBucketFieldSourceValue
	private String sourceValues;

	private String value;

	public ReportBucketFieldValue() {
	}

	public String getSourceValues() {
		return sourceValues;
	}

	public void setSourceValues(String sourceValues) {
		this.sourceValues = sourceValues;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
