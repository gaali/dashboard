package com.vimukt.dashboard.client.column;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.vimukti.dashboard.client.data.DashboardComponentSize;

public class ColumnHeader extends FlowPanel {

	private DashboardComponentSize columnSize;
	private Image remvoveIcon;

	public ColumnHeader() {
		this.addStyleName("columnheader");
		createControls();
	}

	private void createControls() {
		createSizeListBox();
		createRemoviewIcon();
	}

	private void createSizeListBox() {
		final ListBox box = new ListBox();
		box.addStyleName("columsizebox");
		final List<DashboardComponentSize> asList = Arrays.asList(columnSize);
		for (DashboardComponentSize size : asList) {
			String string = size.toString().toLowerCase();
			box.addItem(string);
		}
		box.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				DashboardColumn parent2 = (DashboardColumn) ColumnHeader.this
						.getParent();
				parent2.setColumnSize(asList.get(box.getSelectedIndex())
						.toString().toLowerCase());
			}
		});

	}

	private void createRemoviewIcon() {
		remvoveIcon = new Image();
		remvoveIcon.addStyleName("column-remove-icon");
		remvoveIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				remvoveIcon.getParent().getParent().removeFromParent();
				// TODO save this column has been removed
			}
		});
		this.add(remvoveIcon);
	}

}
