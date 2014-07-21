package com.vimukti.dashboard.client.column;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.vimukti.dashboard.client.data.DashboardData;

public class DashboardColumnsPanel extends FlowPanel {

	private FlowPanel addIcon;
	private DashboardData dashboard;
	private DashboardColumn middleSection;
	private DashboardColumn leftSection;
	private DashboardColumn rightSection;

	public DashboardColumnsPanel(DashboardData dashboard) {
		this.dashboard = dashboard;
		this.addStyleName("columns-panel");
		CreateControls();
	}

	private void CreateControls() {
		columnAddIcon();
		createColumn();
	}

	private void columnAddIcon() {
		addIcon = new FlowPanel();
		addIcon.addStyleName("column-addIcon");
		addIcon.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				removeAllColumns();

				rightSection = middleSection;
				middleSection = leftSection;

				dashboard.setLeftSection(null);
				leftSection = createLeftColumn();

				dashboard.setMiddleSection(leftSection.getSection());
				dashboard.setRightSection(middleSection.getSection());

				DashboardColumnsPanel.this.add(leftSection);
				DashboardColumnsPanel.this.add(middleSection);
				DashboardColumnsPanel.this.add(rightSection);
			}
		}, ClickEvent.getType());
		this.add(addIcon);
	}

	public void icon2() {

	}

	private DashboardColumn createLeftColumn() {
		leftSection = new DashboardColumn(dashboard.getLeftSection()) {

			@Override
			protected void addColumn() {
				middleSection.removeFromParent();

				rightSection = middleSection;

				dashboard.setRightSection(middleSection.getSection());
				dashboard.setMiddleSection(null);
				middleSection = createMiddleColumn();

				DashboardColumnsPanel.this.add(middleSection);
				DashboardColumnsPanel.this.add(rightSection);

			}
		};
		leftSection.deleteColun(new DeleteDashboardColumn() {

			@Override
			public void deleteColumn() {

				removeAllColumns();

				leftSection = middleSection;
				middleSection = rightSection;

				dashboard.setLeftSection(middleSection.getSection());
				dashboard.setMiddleSection(rightSection.getSection());
				dashboard.setRightSection(null);
				rightSection.clear();

				DashboardColumnsPanel.this.add(leftSection);
				DashboardColumnsPanel.this.add(middleSection);
			}
		});
		return leftSection;
	}

	private DashboardColumn createMiddleColumn() {
		middleSection = new DashboardColumn(dashboard.getMiddleSection()) {

			@Override
			protected void addColumn() {
				rightSection = createRightColumn();
				DashboardColumnsPanel.this.add(rightSection);
			}
		};
		middleSection.deleteColun(new DeleteDashboardColumn() {

			@Override
			public void deleteColumn() {

				middleSection.removeFromParent();
				rightSection.removeFromParent();

				middleSection = rightSection;

				DashboardColumnsPanel.this.add(middleSection);
				dashboard.setMiddleSection(rightSection.getSection());

				dashboard.setRightSection(null);
				rightSection.clear();
			}
		});
		return middleSection;
	}

	private DashboardColumn createRightColumn() {
		rightSection = new DashboardColumn(dashboard.getRightSection()) {

			@Override
			protected void addColumn() {
				// TODO Auto-generated method stub

			}
		};
		rightSection.deleteColun(new DeleteDashboardColumn() {

			@Override
			public void deleteColumn() {
				rightSection.removeFromParent();
				dashboard.setRightSection(null);
			}
		});
		return rightSection;
	}

	private void createColumn() {
		if (dashboard == null) {
			dashboard = new DashboardData();
		}
		DashboardColumn leftPanel = createLeftColumn();
		DashboardColumn middlePanel = createMiddleColumn();
		DashboardColumn rightPanel = createRightColumn();
		this.add(leftPanel);
		this.add(middlePanel);
		this.add(rightPanel);
	}

	private void removeAllColumns() {
		leftSection.removeFromParent();
		middleSection.removeFromParent();
		rightSection.removeFromParent();
	}

}
