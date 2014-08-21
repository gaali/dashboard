package com.vimukti.dashboard.client.ui.controls;

import com.google.gwt.user.client.ui.Label;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.data.MetaObject;
import com.vimukti.dashboard.client.ui.controls.dnd.IDraggable;

public class DraggabelLableControl extends Label implements IDraggable {

	private String name;
	private String Id;
	private DataSourceListType type;

	public DraggabelLableControl(MetaObject object) {
		setName(object.getName());
		setId(object.getId());
		createControls();
		Dashboard.getDragAndDropController().addToMakeDraggable(this);
	}

	private void createControls() {
		setText(getName());
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public DataSourceListType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(DataSourceListType type) {
		this.type = type;
	}

}
