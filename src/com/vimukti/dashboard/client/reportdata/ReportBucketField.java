package com.vimukti.dashboard.client.reportdata;

import com.vimukti.dashboard.client.data.MetaObject;

@SuppressWarnings("serial")
public class ReportBucketField extends MetaObject {

	// ReportBucketFieldType
	private String bucketType;

	private String developerName;

	private String masterLabel;

	// ReportBucketFieldNullTreatment
	private String nullTreatment;

	private String otherBucketLabel;

	private String sourceColumnName;

	private ReportBucketFieldValue values;

	public ReportBucketField() {
	}

	public String getBucketType() {
		return bucketType;
	}

	public void setBucketType(String bucketType) {
		this.bucketType = bucketType;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getMasterLabel() {
		return masterLabel;
	}

	public void setMasterLabel(String masterLabel) {
		this.masterLabel = masterLabel;
	}

	public String getNullTreatment() {
		return nullTreatment;
	}

	public void setNullTreatment(String nullTreatment) {
		this.nullTreatment = nullTreatment;
	}

	public String getOtherBucketLabel() {
		return otherBucketLabel;
	}

	public void setOtherBucketLabel(String otherBucketLabel) {
		this.otherBucketLabel = otherBucketLabel;
	}

	public String getSourceColumnName() {
		return sourceColumnName;
	}

	public void setSourceColumnName(String sourceColumnName) {
		this.sourceColumnName = sourceColumnName;
	}

	public ReportBucketFieldValue getValues() {
		return values;
	}

	public void setValues(ReportBucketFieldValue values) {
		this.values = values;
	}

}
