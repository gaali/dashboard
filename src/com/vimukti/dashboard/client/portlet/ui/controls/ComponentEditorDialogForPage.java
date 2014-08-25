package com.vimukti.dashboard.client.portlet.ui.controls;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.SimplePanel;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.TabControl;
import com.vimukti.dashboard.client.ui.utils.TextItem;

@SuppressWarnings("rawtypes")
public abstract class ComponentEditorDialogForPage extends BaseDialog {

	/**
	 * height value take controller
	 */
	private TextItem heightInPixels;

	/**
	 * to store value after giving the value in height Text box
	 */
	protected int height;

	/**
	 * Url for page to show in preview panel
	 */
	private String url;

	public ComponentEditorDialogForPage(String url) {
		this.addStyleName("page-component-editor");
		this.url = url;
	}

	@Override
	protected void createControls() {
		heightInPixels = new TextItem("Height in (pixels)");
		heightInPixels.addStyleName("hightInPixels");

		heightInPixels.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String value = heightInPixels.getValue();
				if (value != null) {
					Integer hightInPixel = Integer.valueOf(value);
					if (hightInPixel > 1000) {
						heightInPixels
								.addError("The maximum value for this field is 1000");
					} else {

						height = hightInPixel.intValue();
					}
				}
			}
		});
		SimplePanel sPanel = new SimplePanel();
		sPanel.addStyleName("componentEditor-dialog-panel");
		sPanel.add(heightInPixels);
		TabControl tab = new TabControl();
		tab.addTab(sPanel, "Formatting");

		FlowPanel pagePreviewPanel = pagePreviewPanel();
		this.add(tab);
		this.add(pagePreviewPanel);

	}

	private FlowPanel pagePreviewPanel() {
		FlowPanel pagePreview = new FlowPanel();
		pagePreview.addStyleName("page-preview-panel");
		if (url != null) {
			Frame pageFrame = new Frame(url);
			pageFrame.setHeight(height + "px");
			pagePreview.add(pageFrame);
		} else {
			FlowPanel vfPageImage = new FlowPanel();
			vfPageImage.addStyleName("vforce-page");
		}
		return pagePreview;
	}

	@Override
	public void setFocus() {
		heightInPixels.setFocus(true);
	}

}
