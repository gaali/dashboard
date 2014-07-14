package com.vimukti.dashboard.client.ui.utils;

public interface ActionCallback<T> {

	/**
	 * This will be called when saving the View
	 * 
	 * @param result
	 */
	void onSuccess(T result);

	/**
	 * This will be called when canceling the View
	 * 
	 * @param result
	 */
	void onCancel(T result);
}
