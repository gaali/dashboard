package com.vimukti.dashboard.client.data;

public enum FieldType {
	INT("Int"),

	BOOLEAN("Boolean"),

	STRING("String"),

	ADRESS("Adress"),

	REFRENCE("Refrence"),

	TEXT("Text"),

	DATEANDTIME("Date And Time");

	private String name;

	private FieldType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static FieldType getFiedType(String vlaue) {
		for (FieldType type : FieldType.values()) {
			if (type.name.equals(vlaue)) {
				return type;
			}
		}
		return null;
	}
}
