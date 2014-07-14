package com.vimukti.dashboard.client.colorPicker;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is the implementation of the SimulatorColorPicker. It defines the user
 * interface, the glue and calculations necessary for SimulatorColorPicker
 * functionality.
 * 
 * <h1>Example</h1> <CODE>
 * public class SimulatorColorPickerExample implements EntryPoint {
 * 
 *  public void onModuleLoad() {
 *    // Make a new SimulatorColorPicker
 *    SimulatorColorPicker picker = new SimulatorColorPicker();
 * 
 *    // Add it to the root panel.
 *    RootPanel.get().add(picker);
 * 
 *    // Make a new button that does something when you click it.
 *    Button b = new Button("Pick!", new ClickListener() {
 *      public void onClick(Widget sender) {
 *        Window.alert("You chose " + picker.getHexColor());
 *      }
 *    });
 * 
 *    // Add it to the root panel.
 *    RootPanel.get().add(b);
 *  }
 * }
 * </CODE>
 * 
 * @author AurorisNET
 * @copyright (C) 2007 AurorisNET. All Rights Reserved.
 */
public class ColorPicker extends Composite implements
		KeyboardListener, ChangeListener {
	// Elements
	private HTML colorpreview;

	// main color picking slider. Leftmost UI
	// component.
	private SliderMap slidermap;
	// Auxiliary color picking slider. Center UI
	// component.
	private SliderBar valueSliderbar;

	// Text Box
	private TextBox tbHexColor;

	// Which color picking mode we are in
	private int red;
	private int green;
	private int blue;
	private int hue;
	private int saturation;
	private int brightness;

	public static final int HUE = 0;
	public static final int SATURATION = 100;
	public static final int BRIGHTNESS = 100;
	public static final int RED = 255;
	public static final int GREEN = 0;
	public static final int BLUE = 0;

	public static final int HUNDRED = 100;
	public static final int THREEHUNDREDSIXTY = 360;
	public static final int ONETWENTYEIGHT = 128;
	public static final int THREEHUNDREDFIFTYNINE = 359;
	public static final int EIGHTY = 80;

	private boolean needChangeBarColor = true;
	private int barSliderPosition = 0;

	public ColorPicker() {
		// UI Drawing
		// ------------------

		hue = HUE;
		saturation = SATURATION;
		brightness = BRIGHTNESS;
		red = RED;
		green = GREEN;
		blue = BLUE;

		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel panel = new HorizontalPanel();
		panel.addStyleName("simulator-color-picker");

		// Add the large slider map
		slidermap = new SliderMap(this);
		slidermap.getElement().getStyle().setBackgroundColor("black");
		panel.add(slidermap);
		panel.setCellWidth(slidermap, "135px");
		panel.setCellHeight(slidermap, "135px");
		slidermap.addStyleName("color-slidermap");

		// Add the small slider bar

		valueSliderbar = new SliderBar(this);
		valueSliderbar.setTitle("Variation");
		valueSliderbar.addStyleName("simulator-variation-slider");
		valueSliderbar.setColorSelectMode();
		valueSliderbar.setLayerOpacity(HUNDRED);
		valueSliderbar.setLayerColor("#ffffff");
		panel.add(valueSliderbar);
		panel.setCellWidth(valueSliderbar, "30px");
		panel.setCellHeight(valueSliderbar, "130px");

		// Define the Flextable's content
		// Color preview at the top
		HorizontalPanel hPanel = new HorizontalPanel();
		colorpreview = new HTML();
		colorpreview.addStyleName("color-preview");

		DOM.setStyleAttribute(colorpreview.getElement(), "border",
				"1px solid black");

		setPreview("ff0000");
		DOM.setStyleAttribute(colorpreview.getElement(), "cursor", "default");
		hPanel.add(colorpreview);

		tbHexColor = new TextBox();
		tbHexColor.setWidth("70px");
		tbHexColor.setHeight("25px");
		tbHexColor.addStyleName("hexa-textbox");
		tbHexColor.setText("ff0000");
		tbHexColor.setMaxLength(6);
		tbHexColor.setVisibleLength(6);
		tbHexColor.addChangeListener(this);

		tbHexColor.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String hex = getHexColor();
				try {
					setHex(hex);
				} catch (Exception e) {
					tbHexColor.setText("");
				}
			}
		});

		hPanel.add(tbHexColor);

		vPanel.add(panel);
		vPanel.add(hPanel);

		initWidget(vPanel);
	}

	protected native String rgbToHEX(String rgb) /*-{
		rgb = rgb.match(/^rgb\(\s*(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*\)$/);

		function hex(x) {
			return ("0" + parseInt(x).toString(16)).slice(-2);
		}
		return hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
	}-*/;

	/**
	 * This method is called when a widget is attached to the browser's
	 * document. To receive notification after a Widget has been added to the
	 * document, override the Widget.onLoad() method.
	 * 
	 * Subclasses that override this method must call <tt>super.onAttach()</tt>
	 * before doing anything else to ensure that the Widget has been properly
	 * attached to its underlying Element.
	 */
	public void onAttach() {
		// Called when we are shown (from being hidden)
		super.onAttach();
		updateSliders();
	}

	/**
	 * Called when the widget wants to update the preview color sample box in
	 * the top-right corner of the UI.
	 * 
	 * @param hex
	 *            Hexadecimal notation of RGB
	 */
	private void setPreview(String hex) {
		DOM.setStyleAttribute(colorpreview.getElement(), "backgroundColor", "#"
				+ hex);
	}

	/**
	 * Fires whenever the user generates picking events on the color picker map.
	 * 
	 * Subclasses that override this method must call
	 * <tt>super.onMapSelected(x,y)</tt> to ensure that the Widget recieves its
	 * events.
	 * 
	 * @param x
	 *            the distance along the x-axis of the user's selection, between
	 *            0 and 255, inclusive.
	 * @param y
	 *            the distance along the y-axis of the user's selection, between
	 *            0 and 255, inclusive.
	 */
	public void onMapSelected(int x, int y) {
		needChangeBarColor = true;
		hue = percentOf(x, THREEHUNDREDSIXTY);
		saturation = HUNDRED - percentOf(y, HUNDRED);
		onValueChange(brightness);

	}

	/**
	 * Fires whenever the user generates picking events along the color picker
	 * bar.
	 * 
	 * Subclasses that override this method must call
	 * <tt>super.onBarSelected(y)</tt> to ensure that the Widget recieves its
	 * events.
	 * 
	 * @param y
	 *            the distance along the y-axis of the user's selection, between
	 *            0 and 255, inclusive.
	 */
	public void onBarSelected(int y, SliderBar sliderBar) {
		barSliderPosition = y;
		needChangeBarColor = false;
		brightness = HUNDRED - percentOf(y, HUNDRED);
		onValueChange(brightness);
	}

	/**
	 * Fired when the user clicks on a widget.
	 * 
	 * Subclasses that override this method must call
	 * <tt>super.onClick(sender)</tt> to ensure that the Widget recieves its
	 * events.
	 * 
	 * @param sender
	 *            the widget sending the event.
	 */
	public void onClick(int sender) {
		slidermap.setColorSelectMode();
		// slidermap.setUnderlayColor("#000000");
		slidermap.setOverlayColor("transparent");

		try {
			Color color = new Color();
			color.setHSV(hue, saturation, HUNDRED);
		} catch (Exception e) {
		}
		slidermap.setUnderlayOpacity(brightness);

		slidermap
				.setSliderPosition(
						(int) ((Integer.valueOf(hue).floatValue() / THREEHUNDREDSIXTY) * ONETWENTYEIGHT),
						ONETWENTYEIGHT
								- (int) ((Integer.valueOf(saturation)
										.floatValue() / HUNDRED) * ONETWENTYEIGHT));
		// when click on slider map then only bar color will change
		// if bar slider change position it will not chage the slider bar color
		if (needChangeBarColor) {
			// if barSliderPostion (y axis) more then 90 slider color will not
			// change
			if (barSliderPosition < EIGHTY) {
				valueSliderbar.setLayerColor("#" + getHexColor());
			}
		}
	}

	/**
	 * Fired whenever something in this widget changes.
	 * 
	 * Subclasses that override this method must call
	 * <tt>super.onChange(sender)</tt> to ensure that the Widget recieves its
	 * events.
	 * 
	 * @param sender
	 *            the widget that has changed.
	 */
	public void onChange(Widget sender) {
		if (sender.equals(tbHexColor)) {
			// Figure out colors
			// Color class will do bounds check on hex input
			try {
				Color color = new Color();
				color.setHex(getHexColor());
				this.hue = color.getHue();
				this.saturation = color.getSaturation();
				this.brightness = color.getValue();
				this.red = color.getRed();
				this.blue = color.getBlue();
				this.green = color.getGreen();
				tbHexColor.setText(color.getHex());
				setPreview(getHexColor());
			} catch (Exception e) {
			}
		}

	}

	private void onValueChange(int sender) {

		if (brightness > HUNDRED) {
			brightness = HUNDRED;
		}

		// Figure out colors
		try {
			Color color = new Color();
			color.setHSV(hue, saturation, brightness);
			red = color.getRed();
			blue = color.getBlue();
			green = color.getGreen();
			tbHexColor.setText(color.getHex());
			setPreview(color.getHex());
		} catch (Exception e) {
		}
		// Let the sliders know something's changed
		onClick(sender);
	}

	/**
	 * Fired when a keyboard action generates a character. This occurs after
	 * onKeyDown and onKeyUp are fired for the physical key that was pressed.
	 * 
	 * It should be noted that many browsers do not generate keypress events for
	 * non-printing keyCode values, such as KEY_ENTER or arrow keys. These
	 * keyCodes can be reliably captured either with onKeyDown(Widget, char,
	 * int) or onKeyUp(Widget, char, int).
	 * 
	 * Subclasses that override this method must call
	 * <tt>super.onKeyPress(sender, keyCode, modifiers)</tt> to ensure that the
	 * Widget recieves its events.
	 * 
	 * @param sender
	 *            the widget that was focused when the event occurred.
	 * @param keyCode
	 *            the Unicode character that was generated by the keyboard
	 *            action.
	 * @param modifiers
	 *            the modifier keys pressed at when the event occurred. This
	 *            value is a combination of the bits defined by MODIFIER_SHIFT,
	 *            MODIFIER_CTRL, and MODIFIER_ALT.
	 * @see com.google.gwt.user.client.ui.KeyboardListener
	 */
	public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		if (sender.equals(tbHexColor)) {
			// Disallow non-hex in hexadecimal boxes
			if ((!Character.isDigit(keyCode)) && (keyCode != 'A')
					&& (keyCode != 'a') && (keyCode != 'B') && (keyCode != 'b')
					&& (keyCode != 'C') && (keyCode != 'c') && (keyCode != 'D')
					&& (keyCode != 'd') && (keyCode != 'E') && (keyCode != 'e')
					&& (keyCode != 'F') && (keyCode != 'f')
					&& (keyCode != (char) KeyboardListener.KEY_TAB)
					&& (keyCode != (char) KeyboardListener.KEY_BACKSPACE)
					&& (keyCode != (char) KeyboardListener.KEY_DELETE)
					&& (keyCode != (char) KeyboardListener.KEY_ENTER)
					&& (keyCode != (char) KeyboardListener.KEY_HOME)
					&& (keyCode != (char) KeyboardListener.KEY_END)
					&& (keyCode != (char) KeyboardListener.KEY_LEFT)
					&& (keyCode != (char) KeyboardListener.KEY_UP)
					&& (keyCode != (char) KeyboardListener.KEY_RIGHT)
					&& (keyCode != (char) KeyboardListener.KEY_DOWN)) {

				// FIXME commented as it is creating a issue in designer
				// ((TextBox) sender).cancelKey();
			}
		}
	}

	/**
	 * Fired when the user releases a physical key.
	 * 
	 * Subclasses that override this method must call
	 * <tt>super.onKeyUp(sender, keyCode, modifiers)</tt> to ensure that the
	 * Widget recieves its events.
	 * 
	 * @param sender
	 *            the widget that was focused when the event occurred.
	 * @param keyCode
	 *            the physical key that was released. Constants for this value
	 *            are defined in this interface with the KEY prefix.
	 * @param modifiers
	 *            the modifier keys pressed at when the event occurred. This
	 *            value is a combination of the bits defined by MODIFIER_SHIFT,
	 *            MODIFIER_CTRL, and MODIFIER_ALT.
	 * @see com.google.gwt.user.client.ui.KeyboardListener
	 */
	public void onKeyUp(Widget sender, char keyCode, int modifiers) {
	}

	/**
	 * Fired when the user depresses a physical key.
	 * 
	 * Subclasses that override this method must call
	 * <tt>super.onKeyDown(sender, keyCode, modifiers)</tt> to ensure that the
	 * Widget recieves its events.
	 * 
	 * @param sender
	 *            the widget that was focused when the event occurred.
	 * @param keyCode
	 *            the physical key that was depressed. Constants for this value
	 *            are defined in this interface with the KEY prefix.
	 * @param modifiers
	 *            he modifier keys pressed at when the event occurred. This
	 *            value is a combination of the bits defined by MODIFIER_SHIFT,
	 *            MODIFIER_CTRL, and MODIFIER_ALT.
	 * @see com.google.gwt.user.client.ui.KeyboardListener
	 */
	public void onKeyDown(Widget sender, char keyCode, int modifiers) {
	}

	/**
	 * Sets the Red, Green, and Blue color variables. This will automatically
	 * populate the Hue, Saturation and Brightness and Hexadecimal fields, too.
	 * 
	 * The RGB color model is an additive color model in which red, green, and
	 * blue light are added together in various ways to reproduce a broad array
	 * of colors. The name of the model comes from the initials of the three
	 * additive primary colors, red, green, and blue.
	 * 
	 * @param red
	 *            strength - valid range is 0-255
	 * @param green
	 *            strength - valid range is 0-255
	 * @param blue
	 *            strength - valid range is 0-255
	 * @throws java.lang.Exception
	 *             Exception if the Red, Green or Blue variables are out of
	 *             range.
	 */
	public void setRGB(int red, int green, int blue) throws GenerateException {
		Color color = new Color();
		color.setRGB(red, green, blue);

		this.red = red;
		this.green = green;
		this.blue = blue;
		this.hue = color.getHue();
		this.saturation = color.getSaturation();
		this.brightness = color.getValue();

		tbHexColor.setText(color.getHex());

		updateSliders();
	}

	/**
	 * Sets the hexadecimal notation for Red, Green, and Blue. This will
	 * automatically populate all the other fields, too.
	 * 
	 * @param hex
	 *            Hexadecimal notation of Red, Green and Blue in the range of
	 *            000000-FFFFFF
	 * @throws java.lang.Exception
	 *             A generic exception if the hexadecimal notation is bad.
	 */
	public void setHex(String hex) throws Exception {
		Color color = new Color();
		color.setHex(hex);

		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
		this.hue = color.getHue();
		this.saturation = color.getSaturation();
		this.brightness = color.getValue();

		tbHexColor.setText(color.getHex());
		onChange(tbHexColor);
	}

	/**
	 * Returns the hexadecimal notation of the current selected color.
	 * 
	 * @return Hexadecimal in the range of 000000-FFFFFF
	 */
	public String getHexColor() {
		return tbHexColor.getText();
	}

	/*
	 * Helper functions -- for common calculations
	 */
	/**
	 * Divides the first value by 256, then multiplies it by the second value.
	 * 
	 * @param val1
	 *            first value.
	 * @param val2
	 *            second value.
	 * @return result.
	 */
	private int percentOf(int val1, int val2) {
		return (int) (new Float(val1).floatValue() / ONETWENTYEIGHT * val2);
	}

	/**
	 * Called whenever the internal state has been changed and needs to
	 * synchronize the other components.
	 */
	// Let the sliders know something's changed
	private void updateSliders() {
		onChange(tbHexColor);
		onValueChange(hue);
		onClick(brightness);
	}

}
