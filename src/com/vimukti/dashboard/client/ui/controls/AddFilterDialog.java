package com.vimukti.dashboard.client.ui.controls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vimukti.dashboard.client.Dashboard;
import com.vimukti.dashboard.client.data.DashboardFilterOperation;
import com.vimukti.dashboard.client.data.DashboardFilterOptions;
import com.vimukti.dashboard.client.data.DashboardFilters;
import com.vimukti.dashboard.client.data.Field;
import com.vimukti.dashboard.client.data.FieldType;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

@SuppressWarnings("rawtypes")
public class AddFilterDialog extends BaseDialog {
	public static final int MAXIMUM_FILTERS_OPTIONS_SIZE = 12;
	private SelectListBox<Field> fields;
	private TextItem displayName;
	private Button addRow;
	private List<FilterOptions> filterOptionsPanels;
	private DashboardFilters filter;
	private FlowPanel optionsPanel;
	private FlowPanel addRowfPanel;

	// use this dialog to as edit Filter dialog
	public AddFilterDialog(DashboardFilters filter) {
		this.filter = filter;
		this.addStyleName("dashboard-filter-dialog");
		createControlsw();
	}

	protected void createControlsw() {
		prepareFields();
		setDefaultValueForDisplayName();

		displayName = new TextItem("DispalayName");
		displayName.addStyleName("displayname");
		this.add(displayName);
		String displayLabel = filter.getDisplayLabel();
		displayName.setText(displayLabel);

		HorizontalPanel hpanel = new HorizontalPanel();
		Label filterOptions = new Label("Filter Options");
		HTML helpText = new HTML();
		helpText.addStyleName("helpText-icon");
		hpanel.add(filterOptions);
		hpanel.add(helpText);
		this.add(hpanel);
		prepareFilterOptionsPanel();
	}

	private void prepareFields() {
		fields = new SelectListBox<Field>("Fields") {
			@Override
			public String getDisplayName(Field item) {
				return item.getName();
			}
		};
		fields.addStyleName("fields");

		List<Field> allFields = Dashboard.getAllFields();
		fields.setItems(allFields);

		// need to call Rpc very time open this dialog or when insert report get
		// all fields belongs to that report if 2 option is ok then where to
		// save those fields and how to access them when open filter dialog
		fields.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				optionsPanel.getElement().setPropertyBoolean("disabled", false);
			}
		});
		// need to add fields here depending on reports we have in columns
		this.add(fields);
	}

	private void setDefaultValueForDisplayName() {
		fields.addHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				displayName.setText(fields.getSelectedValue().toString());
			}
		}, ValueChangeEvent.getType());
	}

	private void prepareFilterOptionsPanel() {
		optionsPanel = new FlowPanel();
		optionsPanel.getElement().setPropertyBoolean("disabled", true);
		optionsPanel.addStyleName("filter-optionspanel");

		final VerticalPanel vPanel = new VerticalPanel();

		HorizontalPanel header = new HorizontalPanel();
		header.addStyleName("header-hpanel");
		Label operator = new Label("Operator");
		operator.addStyleName("header-operator");
		Label value = new Label("value");
		value.addStyleName("header-value");
		header.add(operator);
		header.add(value);

		vPanel.add(header);

		addRowfPanel = new FlowPanel();
		addRowfPanel.addStyleName("addButton-panel");
		addRow = new Button("Add Row");
		addRow.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (vPanel.getWidgetCount() <= MAXIMUM_FILTERS_OPTIONS_SIZE) {
					addRow(vPanel, null);
				}
			}
		});
		addRow.addStyleName("addRow-icon");
		addRowfPanel.add(addRow);

		vPanel.add(addRowfPanel);

		optionsPanel.add(vPanel);
		this.add(optionsPanel);
		filterOptionsPanels = new ArrayList<FilterOptions>();
		List<DashboardFilterOptions> dashboardFilterOptions = filter
				.getDashboardFilterOptions();
		if (dashboardFilterOptions != null) {
			for (DashboardFilterOptions filterOption : dashboardFilterOptions) {
				addRow(vPanel, filterOption);
			}
		}
	}

	/**
	 * creating row(panel) for given filter option
	 * 
	 * @param vPanel
	 * @param filterOption
	 */
	private void addRow(VerticalPanel vPanel,
			DashboardFilterOptions filterOption) {
		FilterOptions createOptionRow = new FilterOptions(filterOption);
		createOptionRow.addStyleName("options-row");
		filterOptionsPanels.add(createOptionRow);
		vPanel.insert(createOptionRow, vPanel.getWidgetIndex(addRowfPanel));
	}

	@Override
	protected boolean onOK() {
		filter.setName(fields.getSelectedValue().getName());
		filter.setDisplayLabel(displayName.getText());
		ArrayList<DashboardFilterOptions> filterOptionsList = getFilterOptionsList();
		filter.setDashboardFilterOptions(filterOptionsList);
		return true;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public ArrayList<DashboardFilterOptions> getFilterOptionsList() {
		ArrayList<DashboardFilterOptions> filterOptionsList = new ArrayList<DashboardFilterOptions>();
		for (FilterOptions panel : filterOptionsPanels) {
			DashboardFilterOperation selectedOperator = panel.operator
					.getSelectedValue();
			String value = panel.valueBox.getValue();
			String value2 = panel.valueBox2.getText();

			DashboardFilterOptions filterOption = panel.filterOption;
			filterOption.setOperator(selectedOperator);
			if (selectedOperator == DashboardFilterOperation.BETWEEN) {
				List<String> listOfValues = new ArrayList<String>();
				listOfValues.add(value);
				listOfValues.add("and");
				listOfValues.add(value2);
				filterOption.setValues(listOfValues);
			} else {
				filterOption.setValue(value);
			}
			filterOptionsList.add(filterOption);
		}
		return filterOptionsList;
	}

	class FilterOptions extends HorizontalPanel {
		SelectListBox<DashboardFilterOperation> operator;
		TextBox groupName;
		TextBox valueBox;
		TextBox valueBox2;
		private DashboardFilterOptions filterOption;

		public FilterOptions(DashboardFilterOptions filterOption) {
			super();
			this.filterOption = filterOption;
			createControlsForOptions();
		}

		/**
		 * @return the operator
		 */
		public SelectListBox<DashboardFilterOperation> getOperator() {
			return operator;
		}

		/**
		 * @param operator
		 *            the operator to set
		 */
		public void setOperator(SelectListBox<DashboardFilterOperation> operator) {
			this.operator = operator;
		}

		/**
		 * @return the groupName
		 */
		public TextBox getGroupName() {
			return groupName;
		}

		/**
		 * @param groupName
		 *            the groupName to set
		 */
		public void setGroupName(TextBox groupName) {
			this.groupName = groupName;
		}

		/**
		 * @return the valueBox
		 */
		public TextBox getValueBox() {
			return valueBox;
		}

		/**
		 * @param valueBox
		 *            the valueBox to set
		 */
		public void setValueBox(TextBox valueBox) {
			this.valueBox = valueBox;
		}

		/**
		 * @return the valueBox2
		 */
		public TextBox getValueBox2() {
			return valueBox2;
		}

		/**
		 * @param valueBox2
		 *            the valueBox2 to set
		 */
		public void setValueBox2(TextBox valueBox2) {
			this.valueBox2 = valueBox2;
		}

		private void createControlsForOptions() {
			// TODO add operators by selected field. operators are differ by
			// field type
			operator = new SelectListBox<DashboardFilterOperation>() {
				@Override
				public String getDisplayName(DashboardFilterOperation item) {
					return item.toString().toLowerCase();
				}
			};
			operator.addStyleName("operator");

			DashboardFilterOperation[] filterOperators = DashboardFilterOperation
					.values();
			List<DashboardFilterOperation> operatersList = Arrays
					.asList(filterOperators);
			List<DashboardFilterOperation> filteredOperaters = new ArrayList<DashboardFilterOperation>();
			if (fields.getSelectedValue() != null) {
				if (fields.getSelectedValue().getFieldType() == FieldType.INT) {
					operatersList.remove(DashboardFilterOperation.INCLUDES);
					operatersList.remove(DashboardFilterOperation.EXCLUDES);
					filteredOperaters.addAll(operatersList);
				} else if (fields.getSelectedValue().getFieldType() == FieldType.BOOLEAN) {
					filteredOperaters.add(DashboardFilterOperation.EQUALS);
					filteredOperaters.add(DashboardFilterOperation.NOT_EQUAL);
				} else if (fields.getSelectedValue().getFieldType() == FieldType.STRING) {
					operatersList.remove(DashboardFilterOperation.INCLUDES);
					operatersList.remove(DashboardFilterOperation.EXCLUDES);
					filteredOperaters.addAll(operatersList);
				} else if (fields.getSelectedValue().getFieldType() == FieldType.DATEANDTIME) {
					// TODO show operators by fields type
				}
			}
			operator.setItems(filteredOperaters);

			DashboardFilterOperation selectedOperation = filterOption
					.getOperator();

			operator.setSelectedValue(selectedOperation);

			groupName = new TextBox();
			groupName.setText(filterOption.getName());
			valueBox = new TextBox();
			String value = filterOption.getValue();
			valueBox.setText(value);

			HTML searchIcon = new HTML("<i class=searchIcon></i>");
			searchIcon.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub

				}
			});
			final HTML removeIcon = new HTML("<i class =removeicon></i>");
			removeIcon.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					FilterOptions.this.removeFromParent();
					filterOptionsPanels.remove(FilterOptions.this);
				}
			});

			this.add(operator);
			this.add(groupName);
			this.add(valueBox);
			if (operator.getSelectedValue() == DashboardFilterOperation.BETWEEN) {
				Label and = new Label("and");
				this.add(and);
				valueBox2 = new TextBox();
				this.add(valueBox2);

				List<String> values = filterOption.getValues();

				valueBox.setText(values.get(0));
				valueBox2.setText(values.get(1));
			}
			// TODO add this icon to panel by field type
			this.add(searchIcon);
			this.add(removeIcon);
		}
	}

}
