package com.vimukti.dashboard.client.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum DashboardFilterOperation {

	@XmlEnumValue("equals")
	EQUALS,

	@XmlEnumValue("notEqual")
	NOT_EQUAL,

	@XmlEnumValue("lessThan")
	LESS_THAN,

	@XmlEnumValue("greaterThan")
	GREATER_THAN,

	@XmlEnumValue("lessOrEqual")
	LESS_OR_EQUAL,

	@XmlEnumValue("greaterOrEqual")
	GREATER_OR_EQUAL,

	@XmlEnumValue("contains")
	CONTAINS,

	@XmlEnumValue("notContain")
	NOT_CONTAIN,

	@XmlEnumValue("startsWith")
	STARTS_WITH,

	@XmlEnumValue("between")
	BETWEEN,

	@XmlEnumValue("includes")
	INCLUDES,

	@XmlEnumValue("excludes")
	EXCLUDES;

	public String getDisplayName() {
		String name = this.toString().toLowerCase();
		return name;
	}
}
