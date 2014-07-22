package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;

public class DescriptionPanel extends FlowPanel {

	private TextArea textArea;

	public DescriptionPanel(String description) {
		this.addStyleName("description-panel");
		textArea = new TextArea();
		textArea.addStyleName("description-textarea");
		textArea.setText(description);
		this.add(textArea);
	}

	public String getDescription() {
		String value = textArea.getValue();
		return value;
	}
}
