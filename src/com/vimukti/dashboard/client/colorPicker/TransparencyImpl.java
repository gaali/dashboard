package com.vimukti.dashboard.client.colorPicker;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * A helpful class to set transparencies in browsers GWT supports
 * 
 * @author AurorisNET
 */
public class TransparencyImpl {
	// Caches the original DXImageTransform.Microsoft.AlphaImageLoader settings
	// for IE6
	static Map map = new HashMap();

	private TransparencyImpl() {
		// Object cannot be created of this class
	}

	/*
	 * Given a DOM element, set the transparency value, with 100 being fully
	 * opaque and 0 being fully transparent
	 * 
	 * @param elem A com.google.gwt.user.client.Element object
	 * 
	 * @param alpha An alpha value
	 */
	public static void setTransparency(Element elem, int alpha) {
		float ieVersion = getIEVersion();

		if (ieVersion >= 5.5 && ieVersion < 7.0) {
			elem = DOM.getChild(elem, 0);

			// Cache existing filters on the image, then re-apply everything
			// with our Alpha filter
			// stacked on the end.
			if (map.containsKey(elem)) {
				if (alpha == 100) {
					DOM.setStyleAttribute(elem, "filter",
							String.valueOf(map.get(elem)));
				} else {
					DOM.setStyleAttribute(
							elem,
							"filter",
							map.get(elem)
									+ ", progid:DXImageTransform.Microsoft.Alpha(opacity="
									+ alpha + ");");
				}
			} else {
				map.put(elem, DOM.getStyleAttribute(elem, "filter"));

				if (alpha == 100) {
					DOM.setStyleAttribute(elem, "filter",
							String.valueOf(map.get(elem)));
				} else {
					DOM.setStyleAttribute(
							elem,
							"filter",
							map.get(elem)
									+ ", progid:DXImageTransform.Microsoft.Alpha(opacity="
									+ alpha + ");");
				}
			}
		}
		// If IE 7 (or better)
		else if (ieVersion >= 7.0) {
			DOM.setStyleAttribute(elem, "filter", "alpha(opacity=" + alpha
					+ ")");
		} // Everyone else
		else {
			DOM.setStyleAttribute(elem, "MozOpacity",
					String.valueOf(Integer.valueOf(alpha).floatValue() / 100));
			DOM.setStyleAttribute(elem, "opacity",
					String.valueOf(Integer.valueOf(alpha).floatValue() / 100));
		}
	}

	public static void setBackgroundColor(Element elem, String color) {
		DOM.setStyleAttribute(elem, "backgroundColor", color);
	}

	// Get IE version (provided by Microsoft)
	public static native float getIEVersion()
	/*-{
		var rv = -1; // Return value assumes failure.
		if (navigator.appName == 'Microsoft Internet Explorer') {
			var ua = navigator.userAgent;
			var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
			if (re.exec(ua) != null)
				rv = parseFloat(RegExp.$1);
		}
		return rv;
	}-*/;
}
