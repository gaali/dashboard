package com.vimukti.dashboard.client.portlet.ui.controls;

import com.google.gwt.user.client.ui.SimplePanel;
import com.vimukti.dashboard.client.data.DashboardComponent;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.TabControl;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class ComponentEditorDialogForPage extends BaseDialog {

	private TextItem hightInPixels;
	private DashboardComponent component;

	public ComponentEditorDialogForPage(DashboardComponent component) {
		this.component = component;
		this.addStyleName("page-component-editor");
	}

	@Override
	protected void createControls() {
		super.createControls();
		hightInPixels = new TextItem("Height in (pixels)");
		hightInPixels.addStyleName("hightInPixes");
		SimplePanel sPanel = new SimplePanel();
		sPanel.addStyleName("componentEditor-dialog-panel");
		sPanel.add(hightInPixels);
		TabControl tab = new TabControl();
		tab.addTab(sPanel, "Formatting");
	}

	@Override
	protected boolean onOK() {
		String value = hightInPixels.getValue();
		if (value != null || value.isEmpty()) {
			return false;
		}
		return true;
		// TODO save this value and change Portlet page container("IFrame") size
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
