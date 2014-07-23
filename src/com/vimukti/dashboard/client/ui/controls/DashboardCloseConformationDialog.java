package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;

@SuppressWarnings("rawtypes")
public class DashboardCloseConformationDialog extends BaseDialog {

	@Override
	protected void createControls() {
		super.createControls();
		Button close = addNewButton("Close");
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				onClose();
			}
		});
		okbtn.setText("Save And Close");
	}

	@Override
	protected boolean onOK() {
		return false;
	}

	protected void onClose() {
		// nothing to do here
	}

	@Override
	public void setFocus() {
		okbtn.setFocus(true);
	}

}
