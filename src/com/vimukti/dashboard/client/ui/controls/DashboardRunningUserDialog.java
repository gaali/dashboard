package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;

@SuppressWarnings("rawtypes")
public class DashboardRunningUserDialog extends BaseDialog {

	private Label description;
	private Anchor learnMore;

	private RadioButton specifiedUser;
	private RadioButton loggedInUser;
	private CheckBox box;

	public DashboardRunningUserDialog() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createControls() {
		super.createControls();
		box = new CheckBox("Let authorized users change running user");
		FlowPanel fPanel = new FlowPanel();
		fPanel.addStyleName("description-panel");
		description = new Label(
				"Show all users the same data in the dashboard by choosing a specific running user, or show data according to each viewer's access level by choosing to run as the logged-in user");
		description.addStyleName("description");
		fPanel.add(description);
		learnMore = new Anchor("Learn More...");
		// add URL to this learMore Anchor
		fPanel.add(learnMore);
		this.add(fPanel);

		specifiedUser = createRadioButtons("rBtn", "Run as Specified User");
		specifiedUser.setValue(true);
		specifiedUser.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				box.removeFromParent();
			}
		});

		loggedInUser = createRadioButtons("rBtn", "Run as Logged-in User");
		loggedInUser.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				box.addStyleName("checkBox");
				DashboardRunningUserDialog.this.add(box);
			}
		});

		this.add(specifiedUser);
		this.add(loggedInUser);
	}

	private RadioButton createRadioButtons(String name, String lable) {
		RadioButton rButton = new RadioButton(name, lable);
		rButton.addStyleName("rBtn");
		return rButton;
	}

	@Override
	protected boolean onOK() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
