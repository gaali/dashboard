package com.vimukti.dashboard.client.ui.utils;

import java.util.ArrayList;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TabControl extends FlowPanel {

	ListPanel tabs;

	private AcceptsOneWidget target;

	ArrayList<HasText> tabsList = new ArrayList<HasText>();
	private HasText currentTab;
	private final String TABS_LEFT = "tabs-left";
	private final String TABS_RIGHT = "tabs-right";
	private final String TABS_TOP = "tabs-top";

	public TabControl() {
		this(null);
	}

	public TabControl(String title) {
		this.getElement().addClassName("tabbable");
		tabs = new ListPanel();
		tabs.addStyleName("nav nav-tabs");
		super.add(tabs);

		final SimplePanel targetControl = new SimplePanel();
		super.add(targetControl);

		setTarget(new AcceptsOneWidget() {

			@Override
			public void setWidget(IsWidget w) {
				targetControl.setWidget(w);
			}
		});
	}

	public void addTab(Widget control, String title) {
		if (control instanceof AnchorControl) {
			addTab((AnchorControl) control);
			return;
		}

		addItem(control, title);
	}

	private void addTab(final AnchorControl anchor) {
		tabsList.add(anchor);
		anchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				currentTab = anchor;
				tabs.setSelected(anchor);
			}
		});

		tabs.add(anchor);
	}

	public void selectTab(int index, boolean fireEvent) {
		Widget anchor = tabs.getWidget(index);
		if (anchor == null) {
			return;
		}
		if (!anchor.isVisible()) {
			anchor = getVisibleAnchorControl();
			if (anchor == null) {
				return;
			}
			fireEvent = true;
		}

		currentTab = (AnchorControl) anchor;
		tabs.setSelected(anchor);
		if (!fireEvent) {
			return;
		}
		NativeEvent changeEvent = Document.get().createChangeEvent();
		NativeEvent event = Document.get().createClickEvent(0,
				changeEvent.getScreenX(), changeEvent.getScreenY(),
				changeEvent.getClientX(), changeEvent.getClientY(),
				changeEvent.getCtrlKey(), changeEvent.getAltKey(),
				changeEvent.getShiftKey(), changeEvent.getMetaKey());
		DomEvent.fireNativeEvent(event, anchor);

	}

	private AnchorControl getVisibleAnchorControl() {
		AnchorControl contrl = null;
		for (HasText wi : tabsList) {
			AnchorControl ctrl = (AnchorControl) wi;
			if (!ctrl.isVisible()) {
				continue;
			}
			contrl = ctrl;
			break;
		}
		return contrl;
	}

	private void addItem(final Widget control, String title) {
		// Tad Control
		// Anything other that those, Just add Group title as tab and
		// show it in control body on selecting the tab

		final AnchorControl anchor = new AnchorControl(title,
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						target.setWidget(control);
					}
				});

		addTab(anchor);

		this.selectTab(0, true);
	}

	public void addTab(String title, ClickHandler handler) {
		addTab(new AnchorControl(title, handler));
	}

	public void setTarget(AcceptsOneWidget parent) {
		this.target = parent;
	}

	public void selectTab(String tabName, boolean fireEvent) {
		if (tabName == null) {
			return;
		}
		for (HasText tab : tabsList) {
			if (tab.getText().equals(tabName)) {
				selectTab(tab, fireEvent);
				break;
			}
		}
	}

	public void selectTab(HasText tab, boolean fireEvent) {
		this.currentTab = tab;
		int index = tabsList.indexOf(tab);
		selectTab(index, fireEvent);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (currentTab == null) {
			selectTabByUrl();
		}
	}

	private void selectTabByUrl() {
		String token = History.getToken();
		HasText tab = getTabFromUrl(token);
		if (tab != null) {
			selectTab(tab, true);
		}
	}

	private HasText getTabFromUrl(String token) {
		for (HasText tab : tabsList) {
			if (tab instanceof AnchorControl) {
				String href = ((AnchorControl) tab).getHref();
				if (href == null) {
					continue;
				}
				String[] split = href.split("#");
				if (split.length > 1 && token.startsWith(split[1])) {
					return tab;
				}
			}
		}
		return null;
	}

	public Object getCurrentTabName() {
		if (currentTab != null) {
			return currentTab.getText();
		}
		return null;
	}

	public void setTabPosition(TabPosition tabPosition) {
		clearTabPosition();
		if (tabPosition.equals(TabPosition.LEFT)) {
			this.getElement().addClassName(TABS_LEFT);
		} else if (tabPosition.equals(TabPosition.RIGHT)) {
			this.getElement().addClassName(TABS_RIGHT);
		} else if (tabPosition.equals(TabPosition.TOP)) {
			this.getElement().addClassName(TABS_TOP);
		}
	}

	private void clearTabPosition() {
		this.getElement().removeClassName(TABS_LEFT);
		this.getElement().removeClassName(TABS_RIGHT);
		this.getElement().removeClassName(TABS_TOP);
	}

	public enum TabPosition {
		TOP, LEFT, RIGHT
	};
}
