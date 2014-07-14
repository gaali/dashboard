package com.vimukti.dashboard.client.colorPicker;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class ColorPalette extends PopupPanel implements
		HasValueChangeHandlers<String> {

	private static final int GRID_ROWS = 7;
	private static final int GRID_COLUMNS = 5;
	private String color;
	private String[] colors =

	{ "000000", "444444", "888888", "bbbbbb", "dcd5ba", "002851", "006bd6",
			"3399FF", "70b7ff", "b7dbff", "5b0000", "c10000", "FF0000",
			"ff3d3d", "ff8e8e", "c17500", "ea8d00", "ffa214", "ffba51",
			"ffd28e", "7a7a00", "d6d600", "FFFF00", "feff70", "fefeb7",
			"004700", "00b700", "00FF00", "47ff47", "a3fea3", "73165b",
			"b72391", "d62dab", "e169c3", "efadde" };

	String[] colors2 =

	{ "AC725E", "D06B64", "F83A22", "FA573C", "FF7537", "FFAD46", "42D692",
			"16A76E", "7bd148", "b3dc6c", "fbe983", "fad165", "92E1C0",
			"9FE1E7", "9fc6e7", "4986e7", "9a9cff", "00FF00", "ffba51",
			"ffd28e", "d62dab", "efadde", "FFFF00", "feff70", "004700",
			"ffd28e", "3399FF", "ffa214", "47ff47", "a3fea3", "00b700",
			"b72391", "7a7a00", "e169c3", "006bd6" };
	private int currentIdex;
	private Grid colorGrid;

	ArrayList<String[]> stringArrayList = new ArrayList<String[]>();

	{
		stringArrayList.add(colors);
		stringArrayList.add(colors2);
	}

	public ColorPalette() {
		super(true);
		FlowPanel panel = new FlowPanel();
		panel.setStyleName("ColorPalette");
		colorGrid = new Grid(GRID_ROWS, GRID_COLUMNS);
		drawColorGrid(colors);
		addNavigationButtons(panel);
		panel.add(colorGrid);
		addCustomColorButton(panel);
		this.add(panel);
	}

	public ColorPalette(String color) {
		this();
	}

	private void addNavigationButtons(FlowPanel panel) {
		FlowPanel topButtonsPanel = new FlowPanel();
		topButtonsPanel.addStyleName("color-palatte-button-panel");
		Button previous = new Button("«");
		previous.addStyleName("pull-left");
		previous.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				int index = currentIdex;
				index--;
				if (index < 0) {
					return;
				}
				drawColorGrid(stringArrayList.get(index));

			}
		});
		topButtonsPanel.add(previous);
		Label palatteNumber = new Label("Color Palatte");
		topButtonsPanel.add(palatteNumber);
		Button next = new Button("»");
		next.addStyleName("pull-right");
		next.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int index = currentIdex;
				index++;
				if (index >= stringArrayList.size()) {
					return;
				}
				drawColorGrid(stringArrayList.get(index));
			}

		});
		topButtonsPanel.add(next);
		panel.add(topButtonsPanel);
	}

	private void drawColorGrid(String[] colorArray) {
		currentIdex = stringArrayList.indexOf(colorArray);
		final CellFormatter cf = colorGrid.getCellFormatter();
		int index = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				BorderStyle solid = Style.BorderStyle.SOLID;
				cf.getElement(i, j).getStyle().setBorderStyle(solid);
				cf.getElement(i, j).getStyle().setBorderColor("white");
				String color = colorArray[index];
				cf.getElement(i, j).setAttribute("bgcolor", color);
				index++;

			}
		}
		colorGrid.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Cell cell = colorGrid.getCellForEvent(event);
				int rowIndex = cell.getRowIndex();
				int cellIndex = cell.getCellIndex();
				String colorName = cf.getElement(rowIndex, cellIndex)
						.getAttribute("bgcolor");
				setColor(colorName);
				hide();
				ValueChangeEvent.fire(ColorPalette.this, colorName);

			}
		});
	}

	private void addCustomColorButton(FlowPanel panel) {
		Button customButton = new Button("Custom Color");
		customButton.addStyleName("color-palette-custombutton");
		customButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
				final ColorPickerDialog picker = new ColorPickerDialog(
						getColor());
				picker.addValueChangeHandler(new ValueChangeHandler<String>() {

					@Override
					public void onValueChange(ValueChangeEvent<String> event) {
						if (picker.isShowing()) {
							return;
						}
						setColor(picker.getColor());
						ValueChangeEvent.fire(ColorPalette.this,
								picker.getColor());

					}
				});
				picker.show();
				picker.center();
			}
		});
		panel.add(customButton);

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
