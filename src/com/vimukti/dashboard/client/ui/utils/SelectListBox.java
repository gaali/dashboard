package com.vimukti.dashboard.client.ui.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class SelectListBox<T> extends ListBox {
	private List<T> items = new ArrayList<T>();
	private FlowPanel errorPanel;

	public SelectListBox() {
		this(null);
	}

	public SelectListBox(String label) {

		HorizontalPanel selectBoxPanel = new HorizontalPanel();
		selectBoxPanel.addStyleName("selectbox-panel");

		if (label != null) {
			Label lLabel = new Label(label);
			lLabel.addStyleName("title-panel");
			selectBoxPanel.add(lLabel);
		}
		this.addStyleName("list-box");
		selectBoxPanel.add(this);

		FlowPanel container = new FlowPanel();
		container.setStyleName("select-flow-panel");
		container.add(selectBoxPanel);

		errorPanel = new FlowPanel();
		errorPanel.addStyleName("error-panel");
		errorPanel.setVisible(false);
		container.add(errorPanel);
	}

	public void setItems(Collection<T> items) {
		this.clear();
		this.items.clear();
		for (T item : items) {
			addListItem(item);
		}
	}

	public void addListItem(T item) {
		this.items.add(item);
		this.addItem(getDisplayName(item));
	}

	public String getDisplayName(T item) {
		return item.toString();
	}

	public void setItems(List<T> items, List<String> values) {
		if (items.size() != values.size()) {
			throw new RuntimeException("items and values size should be same");
		}
		clear();
		this.items.clear();
		for (int i = 0; i < items.size(); i++) {
			this.addListItem(items.get(i), values.get(i));
		}
	}

	public void addListItem(T item, String value) {
		this.items.add(item);
		this.addItem(getDisplayName(item), value);
	}

	/*
	 * this will work only when you set all items throw setItems method only.
	 * other wise use {getSelectedIndex()} method.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getSelectedValue() {
		int index = getSelectedIndex();
		if (index == -1) {
			return null;
		}
		return (T) items.toArray()[index];
	}

	@SuppressWarnings("unchecked")
	public T getValueByIdex(int index) {
		if (items.toArray().length < index) {
			return null;
		}
		return (T) items.toArray()[index];
	}

	public void clearError() {
		errorPanel.clear();
		errorPanel.setVisible(false);
	}

	public void setError(String errString) {
		errorPanel.clear();

		SafeHtml fromString = SafeHtmlUtils.fromString(errString);
		HTML errorLabel = new HTML();
		errorLabel.getElement().setAttribute("color", "red");
		errorLabel.setHTML(fromString);
		errorPanel.add(errorLabel);
		errorPanel.setVisible(true);
	}

	public void setSelectedValue(T item) {
		int index = 0;
		if (items != null) {
			int indexOf = items.indexOf(item);
			if (indexOf != -1) {
				index = indexOf;
			}
		}

		this.setSelectedIndex(index);

	}
}
