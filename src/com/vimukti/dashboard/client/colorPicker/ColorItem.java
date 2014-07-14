package com.vimukti.dashboard.client.colorPicker;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vimukti.dashboard.client.ui.utils.LabelItem;

public class ColorItem extends HorizontalPanel {

	private LabelItem colorItem;
	private ColorPaletteButton paletteWidget;
	private String color;
	private String title;

	public ColorItem(String title) {
		this.title = title;
		createControls();
		this.addStyleName("colorItem");
	}

	private void createControls() {
		colorItem = new LabelItem(title);
		colorItem.addStyleName("color-control");
		getColorPalette();
		this.add(colorItem);
		this.add(paletteWidget);
	}

	private void getColorPalette() {
		paletteWidget = new ColorPaletteButton(new Button());
		paletteWidget.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				setColor(paletteWidget.getColor());
			}
		});
	}

	public Widget getMainWidget() {
		return colorItem;
	}

	public void setColor(String color) {
		this.color = color;
		colorItem.getMainWidget().getElement().getStyle()
				.setBackgroundColor("#" + color);
	}

	public String getColor() {
		return color;
	}

}
