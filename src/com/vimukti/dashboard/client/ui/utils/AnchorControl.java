package com.vimukti.dashboard.client.ui.utils;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Anchor;

public class AnchorControl extends Anchor {

	public AnchorControl(String title, String icon, ClickHandler handler) {
		super();
		if (handler != null) {
			addClickHandler(handler);
		}
		Element mainElement = getElement();
		Element textElement = DOM.createElement("span");
		Element iconElement = DOM.createElement("i");
		if (icon != null) {
			iconElement.addClassName(icon);
		}
		mainElement.appendChild(iconElement);
		mainElement.appendChild(textElement);
		setText(title);
	}

	public AnchorControl(String text, String icon) {
		this(text, icon, null);
	}

	public AnchorControl(String text, ClickHandler handler) {
		this(text, null, handler);
	}

	public AnchorControl(ClickHandler handler) {
		this(null, null, handler);
	}

	public AnchorControl(ClickHandler handler, String icon) {
		this(null, icon, handler);
	}

	public AnchorControl(String text) {
		this(text, null, null);
	}

	@Override
	public void setText(String text) {
		Element child = (Element) getElement().getChild(getTextIndex());
		child.setInnerText(text);
	}

	private int getTextIndex() {
		return 1;
	}

	@Override
	public void setHTML(SafeHtml html) {
		Element child = (Element) getElement().getChild(getIconIndex());
		child.setInnerHTML(html.asString());
	}

	private int getIconIndex() {
		return 0;
	}

	public void setStyle(String style) {
		if (style == null) {
			return;
		}

		addStyleName(style.toLowerCase());
	}

	public void setElementId(String elementId) {
		getElement().setId(elementId);
	}

	public void setActionWithHandler(String string) {
		setHref(string);
	}
}
