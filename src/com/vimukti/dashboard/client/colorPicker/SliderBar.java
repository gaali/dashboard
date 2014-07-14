package com.vimukti.dashboard.client.colorPicker;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

public final class SliderBar extends HTML implements EventPreview {
	private ColorPickerImageBundle cpImageBundle;
	private Image colorA;
	private Image slider;
	private ColorPicker parent = null;
	private boolean captureMouse = false;

	// enum
	public static final int BARD = 4;

	/***
	 * Initialize the SliderMap -- default mode is Saturation.
	 */
	public SliderBar(ColorPicker parent) {
		super();

		this.parent = parent;

		setWidth("10px");
		setHeight("130px");

		cpImageBundle = (ColorPickerImageBundle) GWT
				.create(ColorPickerImageBundle.class);

		colorA = new Image(cpImageBundle.bar_brightness());
		slider = new Image(cpImageBundle.rangearrows());

		DOM.appendChild(getElement(), colorA.getElement());
		DOM.appendChild(getElement(), slider.getElement());

		DOM.setStyleAttribute(getElement(), "position", "absolute");
		DOM.setStyleAttribute(colorA.getElement(), "border", "1px solid black");

		sinkEvents(Event.ONMOUSEMOVE);
		sinkEvents(Event.ONMOUSEDOWN);
		sinkEvents(Event.ONMOUSEUP);
	}

	/***
	 * This method is called when a widget is attached to the browser's
	 * document.
	 */
	public void onAttach() {
		super.onAttach();

		DOM.setStyleAttribute(colorA.getElement(), "position", "absolute");
		DOM.setStyleAttribute(colorA.getElement(), "left", "10px");
		DOM.setStyleAttribute(colorA.getElement(), "top", "0px");
		DOM.setStyleAttribute(slider.getElement(), "position", "absolute");
		DOM.setStyleAttribute(slider.getElement(), "left", "0px");
		DOM.setStyleAttribute(slider.getElement(), "top", "0px");
	}

	/**
	 * Set overlay's opacity.
	 * 
	 * @param alpha
	 *            An opacity percentage, between 100 (fully opaque) and 0
	 *            (invisible).
	 * @param layer
	 *            which bar to change opacity for, 1-4
	 */
	public void setLayerOpacity(int alpha) {
		if (alpha >= 0 && alpha <= 100 && isAttached()) {
			Element colorbar = colorA.getElement();
			TransparencyImpl.setTransparency(colorbar, alpha);
		}
	}

	/**
	 * Sets the color of a particular layer
	 * 
	 * @param color
	 *            Hexadecimal notation of RGB to change the layer's color
	 * @param layer
	 *            Which layer to affect
	 */
	public void setLayerColor(String color) {
		Element colorbar = colorA.getElement();
		TransparencyImpl.setBackgroundColor(colorbar, color);
	}

	/**
	 * Sets the slider's position on the y-axis.
	 * 
	 * @param y
	 *            Position along the y-axis to set the slider's position to.
	 */
	public void setSliderPosition(int yPosition) {
		if (yPosition == 0) {
			yPosition = 5;
		}

		int y = yPosition;
		if (y < 5) {
			y = 5;
		}
		if (y > 125) {
			y = 125;
		}
		DOM.setStyleAttribute(slider.getElement(), "top", y - 4 + "px");
	}

	/**
	 * Sets the color selection mode
	 * 
	 * @param mode
	 *            Can be one of: ColorBar.Saturation, ColorBar.Hue,
	 *            ColorBar.Brightness, ColorBar.Red, ColorBar.Green,
	 *            ColorBar.Blue, ColorBar.Red.
	 */
	public void setColorSelectMode() {
		if (!isAttached()) {
			return;
		}
		AbstractImagePrototype barWhite = AbstractImagePrototype
				.create(cpImageBundle.bar_brightness());

		barWhite.applyTo(colorA);

	}

	/**
	 * Fired whenever a browser event is received.
	 * 
	 * @param event
	 *            Event to process
	 */
	public void onBrowserEvent(Event event) {
		switch (DOM.eventGetType(event)) {
		case Event.ONMOUSEDOWN:
			captureMouse = true;
			DOM.addEventPreview(this);
			mouseEvent(event);
			break;
		default:
			break;
		}
	}

	/**
	 * Called when a browser event occurs and this event preview is on top of
	 * the preview stack.
	 * 
	 * @param event
	 *            Event to process
	 * @return boolean false to cancel the event.
	 */
	public boolean onEventPreview(Event event) {
		switch (DOM.eventGetType(event)) {
		case Event.ONMOUSEUP:
			captureMouse = false;
			DOM.removeEventPreview(this);
			break;

		case Event.ONMOUSEMOVE:
			if (captureMouse) {
				mouseEvent(event);
				return false;
			}
			break;
		}
		return true;
	}

	/**
	 * Called to process a mouse event.
	 * 
	 * @param event
	 *            Event to process
	 */
	private void mouseEvent(Event event) {
		DOM.eventPreventDefault(event);

		int y = Window.getScrollTop() + DOM.eventGetClientY(event)
				- getAbsoluteTop();

		if (y < 5) {
			y = 5;
		}
		if (y > 125) {
			y = 125;
		}
		DOM.setStyleAttribute(slider.getElement(), "top", y - 4 + "px");

		if (parent != null) {
			parent.onBarSelected(y, this);
		}
	}
}