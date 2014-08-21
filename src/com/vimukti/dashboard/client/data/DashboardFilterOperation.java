package com.vimukti.dashboard.client.data;


public enum DashboardFilterOperation {

	EQUALS("equals"),

	NOT_EQUAL("notEqual"),

	LESS_THAN("lessThan"),

	GREATER_THAN("greaterThan"),

	LESS_OR_EQUAL("lessOrEqual"),

	GREATER_OR_EQUAL("greaterOrEqual"),

	CONTAINS("contains"),

	NOT_CONTAIN("notContain"),

	STARTS_WITH("startsWith"),

	BETWEEN("between"),

	INCLUDES("includes"),

	EXCLUDES("excludes");

	private String name;

	private DashboardFilterOperation(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static DashboardFilterOperation getFilterOperation(String value) {
		for (DashboardFilterOperation operation : DashboardFilterOperation
				.values()) {
			if (operation.name == value) {
				return operation;
			}
		}
		return null;
	}
}
