package com.vimukti.dashboard.client.colorPicker;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ColorPickerDialog extends DialogBox implements
		HasValueChangeHandlers<String> {

	private static Logger logger = Logger
			.getLogger("SimulatorColorPickerDialog");

	private ColorPicker picker;

	private String resultColor;

	public ColorPickerDialog() {
		this(null);
	}

	public ColorPickerDialog(String color) {
		addStyleName("simulator-color-picker-dialog");
		setText("Color Picker");

		// Define the panels
		VerticalPanel panel = new VerticalPanel();
		FlowPanel okcancel = new FlowPanel();
		picker = new ColorPicker();
		resultColor = color;

		if (resultColor != null && !resultColor.isEmpty()) {
			try {
				picker.setHex(resultColor);
			} catch (Exception e) {
				logger.log(Level.SEVERE,
						"Unable to Set Hex Value Getting Exception", e);
			}
		}

		// Define the buttons
		// ok button
		Button ok = new Button("Ok");
		ok.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String hexColor = picker.getHexColor();
				setColor(hexColor);
				ColorPickerDialog.this.hide();
				ValueChangeEvent
						.fire(ColorPickerDialog.this, hexColor);
			}
		});
		// cancel button
		Button cancel = new Button("Cancel");
		cancel.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ColorPickerDialog.this.hide();
			}
		});

		okcancel.add(ok);
		okcancel.add(cancel);

		// Put it together
		panel.add(picker);
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		panel.add(okcancel);

		setWidget(panel);
	}

	protected void setColor(String hexColor) {
		this.resultColor = hexColor;
	}

	public String getColor() {
		return resultColor;
	}

	// public HandlerRegistration addDialogClosedHandler(
	// IDialogClosedHandler handler) {
	// return addHandler(handler, DialogClosedEvent.getType());
	// }

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
}
