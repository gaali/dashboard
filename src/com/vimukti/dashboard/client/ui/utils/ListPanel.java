package com.vimukti.dashboard.client.ui.utils;

import java.util.Iterator;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.WidgetCollection;

public class ListPanel extends ComplexPanel implements InsertPanel.ForIsWidget {

	/**
	 * Creates an empty flow panel.
	 */
	public ListPanel() {
		Element ul = DOM.createElement("ul");
		setElement(ul);
	}

	@Override
	public void add(Widget child) {
		// Detach new child.
		child.removeFromParent();

		// Logical attach.
		getChildren().add(child);

		Element li = DOM.createElement("li");
		li.appendChild(child.getElement());
		// Physical attach.
		DOM.appendChild(getElement(), li);

		// Adopt.
		adopt(child);
	}

	public void addSeperator() {
		Element li = DOM.createElement("li");
		li.addClassName("divider");
		// Physical attach.
		DOM.appendChild(getElement(), li);
	}

	@Override
	public void clear() {
		Element element = getElement();
		Iterator<Widget> it = iterator();
		while (it.hasNext()) {
			Widget next = it.next();
			DOM.removeChild(element, DOM.getParent(next.getElement()));
		}
		super.clear();
	}

	@Override
	public void insert(Widget w, int beforeIndex) {
		insert(w, getElement(), beforeIndex, true);
	}

	@Override
	public void insert(IsWidget w, int beforeIndex) {
		insert(asWidgetOrNull(w), beforeIndex);
	}

	public void setSelected(Widget widget) {
		WidgetCollection childNodes = getChildren();
		if (!childNodes.contains(widget)) {
			return;
		}
		for (Widget child : childNodes) {
			com.google.gwt.dom.client.Element parentElement = child
					.getElement().getParentElement();
			parentElement.removeClassName("active");
		}
		com.google.gwt.dom.client.Element parentElement = widget.getElement()
				.getParentElement();
		parentElement.addClassName("active");
	}

	public WidgetCollection getChildNodes() {
		return getChildren();
	}

}
