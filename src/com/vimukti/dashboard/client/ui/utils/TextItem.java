package com.vimukti.dashboard.client.ui.utils;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class TextItem extends HorizontalPanel {

	private TextBox textBox;
	private FlowPanel errorPanel;

	public TextItem(String label) {
		this(label, false);
	}

	public TextItem(String label, boolean isRequired) {

		Label lLabel = new Label(label);
		this.add(lLabel);
		if (isRequired) {
			FlowPanel requiredPanel = new FlowPanel();
			requiredPanel.addStyleName("required");
			this.add(requiredPanel);
		}
		FlowPanel textBoxPanel = new FlowPanel();
		textBoxPanel.addStyleName("textbox-panel");

		textBox = new TextBox();
		textBoxPanel.add(textBox);

		errorPanel = new FlowPanel();
		errorPanel.addStyleName("error-panel");
		errorPanel.setVisible(false);
		textBoxPanel.add(errorPanel);

		this.add(textBoxPanel);
	}

	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return textBox.addBlurHandler(handler);
	}

	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		return textBox.addValueChangeHandler(handler);
	}

	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return textBox.addKeyDownHandler(handler);
	}

	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return textBox.addKeyUpHandler(handler);
	}

	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return textBox.addKeyPressHandler(handler);
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

	public TextBox getMainWidget() {
		return textBox;
	}

	public String getText() {
		return textBox.getText();
	}

	public String getValue() {
		return textBox.getValue();
	}

	public void setText(String text) {
		textBox.setText(text);
	}

	public void setValue(String value) {
		textBox.setValue(value);
	}

	public String getTitle() {
		return textBox.getTitle();
	}

	public void setTitle(String title) {
		textBox.setTitle(title);
	}

	public void setFocus(boolean b) {
		textBox.setFocus(true);
	}

}
