package com.vimukti.dashboard.client.data;

import java.util.List;

@SuppressWarnings("serial")
public class DashboardFilterOptions extends MetaObject {

	private DashboardFilterOperation operator;

	private String value;

	private List<String> values;

	/**
	 * @return the operator
	 */
	public DashboardFilterOperation getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(DashboardFilterOperation operator) {
		this.operator = operator;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the values
	 */
	public List<String> getValues() {
		return values;
	}

	/**
	 * @param values
	 *            the values to set
	 */
	public void setValues(List<String> values) {
		this.values = values;
	}

}
