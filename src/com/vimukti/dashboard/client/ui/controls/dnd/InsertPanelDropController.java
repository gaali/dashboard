package com.vimukti.dashboard.client.ui.controls.dnd;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbstractInsertPanelDropController;
import com.allen_sauer.gwt.dnd.client.util.LocationWidgetComparator;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Widget;

public class InsertPanelDropController extends
		AbstractInsertPanelDropController {

	private HTML positioner;

	public InsertPanelDropController(InsertPanel dropTarget) {
		super(dropTarget);
	}

	@Override
	protected LocationWidgetComparator getLocationWidgetComparator() {
		return LocationWidgetComparator.BOTTOM_RIGHT_COMPARATOR;
	}

	@Override
	protected Widget newPositioner(DragContext context) {
		Widget draggable = context.draggable;
		this.positioner = new HTML();
		positioner.addStyleName("drop-positioner");
		positioner.setWidth(draggable.getOffsetWidth() + "px");
		positioner.setHeight(draggable.getOffsetHeight() + "px");
		return positioner;
	}

	public int getDropIndex() {
		return dropTarget.getWidgetIndex(positioner);
	}

}
