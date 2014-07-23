package com.vimukti.dashboard.client.ui.utils;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class LabelItem extends HorizontalPanel {
	private Label label;
	private FlowPanel errorPanel;

	public LabelItem(String label) {
		this(label, false);
	}

	public LabelItem(String labelName, boolean isRequired) {

		Label lLabel = new Label(labelName);
		this.add(lLabel);
		if (isRequired) {
			FlowPanel requiredPanel = new FlowPanel();
			requiredPanel.addStyleName("required");
			this.add(requiredPanel);
		}
		FlowPanel labelPanel = new FlowPanel();
		labelPanel.addStyleName("textbox-panel");

		label = new Label();
		label.addStyleName("color-label");
		labelPanel.add(label);

		errorPanel = new FlowPanel();
		errorPanel.addStyleName("error-panel");
		errorPanel.setVisible(false);
		labelPanel.add(errorPanel);

		this.add(labelPanel);
	}

	public HandlerRegistration addClickHandlerRegistration(ClickHandler handler) {
		return label.addClickHandler(handler);
	}

	public void addError(String errString) {
		errorPanel.clear();
		SafeHtml fromString = SafeHtmlUtils.fromString(errString);
		HTML errorLabel = new HTML();
		errorLabel.setHTML(fromString);
		errorPanel.add(errorLabel);
		errorPanel.setVisible(true);
	}

	public void clearErrors() {
		errorPanel.clear();
		errorPanel.setVisible(false);
	}

	public Label getMainWidget() {
		return label;
	}

	public String getText() {
		return label.getText();
	}

	public void setText(String text) {
		label.setText(text);
	}

	public String getTitle() {
		return label.getTitle();
	}

	public void setTitle(String title) {
		label.setTitle(title);
	}

}
