package com.vimukti.dashboard.client.ui.controls;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.vimukti.dashboard.client.colorPicker.ColorItem;
import com.vimukti.dashboard.client.data.ChartBackgroundDirection;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;

public class ComponentSettingsPanel extends FlowPanel {

	private ColorItem titleColor;
	private SelectListBox<String> titleSize;
	private ColorItem textColor;
	private SelectListBox<ChartBackgroundDirection> backgrounFadeDirection;
	private ColorItem startingColor;
	private ColorItem endingColor;
	private DashboardData settings;

	public ComponentSettingsPanel(DashboardData settings) {
		this.settings = settings;
		this.addStyleName("dashboard-component-settings-panel");
		createControls();
		init();
	}

	private void createControls() {
		titleColor = new ColorItem("Title Color");
		titleColor.addStyleName("title-color");
		titleColor.setColor("000000");

		prepareTextSizeCombo();

		textColor = new ColorItem("Text Color");
		textColor.addStyleName("textcolor");

		prepareBackgroundStylesComobo();

		startingColor = new ColorItem("Start Color");
		startingColor.addStyleName("starting-color");
		startingColor.setColor("FFFFFF");

		endingColor = new ColorItem("Ending Color");
		endingColor.addStyleName("ending-color");
		endingColor.setColor("FFFFFF");

		this.add(titleColor);
		this.add(titleSize);
		this.add(textColor);
		this.add(backgrounFadeDirection);
		this.add(startingColor);
		this.add(endingColor);
	}

	private void prepareTextSizeCombo() {
		titleSize = new SelectListBox<String>();
		titleSize.addStyleName("title-size");
		for (int i = 8; i <= 18; i++) {
			titleSize.addListItem(i + " pt", i + "pt");
		}
	}

	private void prepareBackgroundStylesComobo() {
		backgrounFadeDirection = new SelectListBox<ChartBackgroundDirection>(
				"Backgroun Fade Direction") {
			@Override
			public String getDisplayName(ChartBackgroundDirection item) {
				if (item == ChartBackgroundDirection.DIAGONAL) {
					return "Diagonal";
				} else if (item == ChartBackgroundDirection.LEFT_TO_RIGHT) {
					return "Left to Right";
				} else if (item == ChartBackgroundDirection.TOP_TO_BOTTOM) {
					return "Top to Buttom";
				}
				return null;
			}
		};
		backgrounFadeDirection.addStyleName("background-fade-direction");
		ChartBackgroundDirection[] values = ChartBackgroundDirection.values();
		List<ChartBackgroundDirection> directions = Arrays.asList(values);
		backgrounFadeDirection.setItems(directions);
	}

	private void init() {
		if (settings == null) {
			return;
		}
		String titleColor2 = settings.getTitleColor();
		if (titleColor2 != null) {
			titleColor.setColor(settings.getTitleColor());
		}
		int titleSize2 = settings.getTitleSize();
		if (titleSize2 >= 8 && titleSize2 <= 18) {
			// need To fixit .to store title size in database as string or int?
			titleSize.setItemSelected(settings.getTitleSize(), true);
		}
		String textColor2 = settings.getTextColor();
		if (textColor2 != null) {
			textColor.setColor(settings.getTextColor());
		}
		ChartBackgroundDirection backgroundFadeDirection = settings
				.getBackgroundFadeDirection();
		if (backgroundFadeDirection != null) {
			backgrounFadeDirection.setItemSelected(settings
					.getBackgroundFadeDirection().ordinal(), true);
		}
		String startingColor2 = settings.getBackgroundStartColor();
		if (startingColor2 != null) {
			startingColor.setColor(startingColor2);
		}
		String endingColor2 = settings.getBackgroundEndColor();
		if (endingColor2 != null) {
			endingColor.setColor(endingColor2);
		}
	}

	public void update() {
		settings.setTitleColor(titleColor.getColor());
		String selectedValue = titleSize.getSelectedValue();
		String[] split = selectedValue.split("px");
		settings.setTitleSize(Integer.parseInt(split[0]));
		settings.setTextColor(textColor.getColor());
		settings.setBackgroundFadeDirection(backgrounFadeDirection
				.getSelectedValue());
		settings.setBackgroundStartColor(startingColor.getColor());
		settings.setBackgroundEndColor(endingColor.getColor());
	}
}
