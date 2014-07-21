package com.vimukti.dashboard.client.ui.controls.dnd;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.DragEndEvent;
import com.allen_sauer.gwt.dnd.client.DragHandler;
import com.allen_sauer.gwt.dnd.client.DragStartEvent;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Widget;

public class DragAndDropController {

	private static Logger logger = Logger.getLogger("DragAndDropController");

	private HashMap<AbsolutePanel, PickupDragController> dragControllers = new HashMap<AbsolutePanel, PickupDragController>();

	private ArrayList<IDraggable> draggableList;

	public DragAndDropController(AbsolutePanel dndPanel) {
		addDragAndDrop(dndPanel);
		draggableList = new ArrayList<IDraggable>();
	}

	public void addToMakeDraggable(IDraggable draggable) {

		Widget dndPanel = draggable.asWidget();
		PickupDragController pdc = getDragController(dndPanel);
		if (pdc == null) {
			return;
		}

		if (draggableList.contains(draggable)) {
			draggableList.remove(draggable);
			makeNotDraggable(pdc, draggable);
		} else {
			draggableList.add(draggable);
			makeDraggable(pdc, draggable);
		}
	}

	private PickupDragController getDragController(Widget asWidget) {

		if (asWidget == null) {
			return null;
		}

		PickupDragController pdc = dragControllers.get(asWidget);
		if (pdc != null) {
			return pdc;
		}

		return getDragController(asWidget.getParent());
	}

	/**
	 * Adds Dnd control to given panel
	 * 
	 * @param dndPanel
	 * @param dragController
	 */
	private void addDnDControl(InsertPanel dndPanel,
			PickupDragController dragController) {

		// If it is InsertPanel and FormItem, then Register this panel to Drop
		// widgets
		if (dndPanel instanceof IDroppable) {
			InsertPanelDropController widgetDropController = new InsertPanelDropController(
					dndPanel);
			dragController.registerDropController(widgetDropController);
		}

	}

	/**
	 * 
	 * Make this Draggable
	 * 
	 * @param dragController
	 * @param draggable
	 */
	private void makeDraggable(PickupDragController dragController,
			IDraggable draggable) {
		Widget widget = draggable.asWidget();
		Element element = widget.getElement();
		element.addClassName("draggable-control");

		// Make is Draggable
		dragController.makeDraggable(widget);
	}

	private void makeNotDraggable(PickupDragController dragController,
			IDraggable draggable) {
		Widget widget = draggable.asWidget();
		Element element = widget.getElement();
		element.removeClassName("draggable-control");

		// Make is not Draggable
		dragController.makeNotDraggable(widget);
	}

	private void addDragAndDrop(AbsolutePanel dndPanel) {
		// Create DND Controller
		PickupDragController dragController = new PickupDragController(
				dndPanel, false);

		// Make this absolute
		makeAbsolute(dndPanel);
		// Put it in Map
		dragControllers.put(dndPanel, dragController);
		dragController.setBehaviorMultipleSelection(true);

		// Add Drag Handler
		dragController.addDragHandler(getDragHandler());
		dragController.registerDropController(new InsertPanelDropController(
				dndPanel));
		dragController.setBehaviorDragStartSensitivity(3);

		addDnDControl(dndPanel, dragController);
	}

	private void makeAbsolute(AbsolutePanel dndPanel) {
		dndPanel.getElement().getStyle().setPosition(Position.RELATIVE);
	}

	private DragHandler getDragHandler() {
		return new DragHandler() {

			private IDroppable dropTarget;

			@Override
			public void onPreviewDragStart(DragStartEvent event)
					throws VetoDragException {

			}

			@Override
			public void onPreviewDragEnd(DragEndEvent event)
					throws VetoDragException {
				dropTarget = null;
				InsertPanelDropController dropController = (InsertPanelDropController) event
						.getContext().finalDropController;

				if (dropController == null) {
					logger.warning("Droptarget should be Droppable !!");
					throw new VetoDragException();
				}

				Widget dropTarget = dropController.getDropTarget();

				if (!(dropTarget instanceof IDroppable)) {
					logger.warning("Droptarget should be Droppable !!");
					throw new VetoDragException();
				}

				if (!((IDroppable) dropTarget).canDroppable(event.getContext())) {
					logger.warning("Droptarget is not accepting dropped widget");
					throw new VetoDragException();
				}

				this.dropTarget = (IDroppable) dropTarget;

			}

			@Override
			public void onDragStart(DragStartEvent event) {

			}

			@Override
			public void onDragEnd(DragEndEvent event) {
				logger.info("Drag ended.");
				DragContext context = event.getContext();
				if (dropTarget == null || context.selectedWidgets == null) {
					logger.warning("drop target not founded, so we are not dropping dragged widget");
					return;
				}

				dropTarget.onDrop(drop(context);

				clearDraggableWidgets();
				logger.info("Drag ended. Resetting Control indexes");

			}
		};
	}

	private void clearDraggableWidgets() {

		Widget dndPanel = draggableList.get(0).asWidget();
		PickupDragController pdc = getDragController(dndPanel);

		for (IDraggable draggable : draggableList) {
			makeNotDraggable(pdc, draggable);
		}

		draggableList.clear();
	}

	public void clear() {
		dragControllers.clear();
	}
}
