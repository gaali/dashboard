package com.vimukti.dashboard.client.ui.controls.dnd;

import com.google.gwt.user.client.ui.Widget;

public interface IDroppable {

	public void onDrop(IDraggable draggabelWidget);

	public Widget asWidget();

	boolean canDroppable(IDraggable draggabelWidget);

}
