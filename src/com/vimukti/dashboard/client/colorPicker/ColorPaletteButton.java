package com.vimukti.dashboard.client.colorPicker;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ColorPaletteButton extends FlowPanel implements
		HasValueChangeHandlers<String> {

	private String color;
	private Widget widget;

	public ColorPaletteButton(Widget widget) {
		this.widget = widget;
		this.add(widget);
		createControls();
		this.setStyleName("colorPalertte-btn");
	}

	private void createControls() {

		widget.addDomHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showColorPalette();
			}
		}, ClickEvent.getType());
	}

	protected void showColorPalette() {
		final ColorPalette palettePopup = new ColorPalette();
		palettePopup.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (palettePopup.isShowing()) {
					return;
				}
				setColor(palettePopup.getColor());
				ValueChangeEvent.fire(ColorPaletteButton.this,
						palettePopup.getColor());
			}
		});
		palettePopup.show();
		palettePopup.showRelativeTo(widget);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

}
