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
import com.vimukti.dashboard.client.data.CustomObject;
import com.vimukti.dashboard.client.data.DashboardFilterOperation;
import com.vimukti.dashboard.client.data.DashboardFilterOptions;
import com.vimukti.dashboard.client.data.DashboardFilters;
import com.vimukti.dashboard.client.data.Field;
import com.vimukti.dashboard.client.data.Field.FieldType;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

@SuppressWarnings("rawtypes")
public class AddFilterDialog extends BaseDialog {
	public static final int MAXIMUM_FILTERS_OPTIONS_SIZE = 12;
	private SelectListBox<Field> fields;
	private TextItem displayName;
	private Button addRow;
	private List<DashboardFilterOptions> filterOptionsList;
	private List<FilterOptions> filterOptionsPanels;
	private DashboardFilters options;
	private DashboardFilters filter;
	private FlowPanel optionsPanel;
	private FlowPanel addRowfPanel;
	private List<Field> fieldsList;

	// use this dialog to as edit Filter dialog
	public AddFilterDialog(DashboardFilters filter, List<Field> fieldsList) {
		this.filter = filter;
		this.fieldsList = fieldsList;
		this.addStyleName("dashboard-filter-dialog");
		createControlsw();
	}

	protected void createControlsw() {
		if (fieldsList == null) {
			prepareFields();
			setDefaultValueForDisplayName();
		}
		displayName = new TextItem("DispalayName");
		displayName.addStyleName("displayname");
		this.add(displayName);

		HorizontalPanel hpanel = new HorizontalPanel();
		Label filterOptions = new Label("Filter Options");
		HTML helpText = new HTML();
		helpText.addStyleName("helpText-icon");
		hpanel.add(filterOptions);
		hpanel.add(helpText);
		this.add(hpanel);
		prepareFilterOptionsPanel();
		if (fieldsList != null) {
			init();
		}
	}

	private void init() {
		// TODO
	}

	private void prepareFields() {
		fields = new SelectListBox<Field>("Fields") {
			@Override
			public String getDisplayName(Field item) {
				return item.toString();
			}
		};
		fields.addStyleName("fields");
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

	public List<Field> prepareFieldsFromCustomObject(CustomObject object) {
		List<Field> fields2 = object.getFields();
		List<Field> filterdFields = new ArrayList<Field>();
		for (Field field : fields2) {
			FieldType fieldType = field.getFieldType();
			if (fieldType == FieldType.REFRENCE) {
				prepareFieldsFromCustomObject(field.getRefrenceType());
				continue;
			}
			filterdFields.add(field);
		}
		return filterdFields;
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
		if (fieldsList == null) {
			optionsPanel.getElement().setPropertyBoolean("disabled", true);
		}
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
					addRow(vPanel);
				}
			}
		});
		addRow.addStyleName("addRow-icon");
		addRowfPanel.add(addRow);

		vPanel.add(addRowfPanel);
		addRow(vPanel);

		optionsPanel.add(vPanel);
		this.add(optionsPanel);
	}

	private void addRow(VerticalPanel vPanel) {
		FilterOptions createOptionRow = new FilterOptions();
		createOptionRow.addStyleName("options-row");
		filterOptionsPanels = new ArrayList<FilterOptions>();
		filterOptionsPanels.add(createOptionRow);
		vPanel.insert(createOptionRow, vPanel.getWidgetIndex(addRowfPanel));

		if (options != null) {
			List<DashboardFilterOptions> dashboardFilterOptions = options
					.getDashboardFilterOptions();
			for (DashboardFilterOptions filter : dashboardFilterOptions) {
				FilterOptions createOptionRow1 = new FilterOptions();
				createOptionRow1.operator
						.setSelectedValue(filter.getOperator());
			}
		}
	}

	@Override
	protected boolean onOK() {
		update();
		filter.setName(fields.getSelectedValue().getName());
		filter.setDisplayLabel(displayName.getText());
		filter.setDashboardFilterOptions(filterOptionsList);
		return true;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void update() {
		filterOptionsList = new ArrayList<DashboardFilterOptions>();
		for (FilterOptions panel : filterOptionsPanels) {
			DashboardFilterOperation selectedOperator = panel.operator
					.getSelectedValue();
			String value = panel.valueBox.getValue();
			String value2 = panel.valueBox2.getText();

			DashboardFilterOptions filter = new DashboardFilterOptions();
			filter.setOperator(selectedOperator);
			if (selectedOperator == DashboardFilterOperation.BETWEEN) {
				List<String> listOfValues = new ArrayList<String>();
				listOfValues.add(value);
				listOfValues.add("and");
				listOfValues.add(value2);
				filter.setValues(listOfValues);
			} else {
				filter.setValue(value);
			}
			filterOptionsList.add(filter);
		}

	}

	class FilterOptions extends HorizontalPanel {
		SelectListBox<DashboardFilterOperation> operator;
		TextBox groupName;
		TextBox valueBox;
		TextBox valueBox2;

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

		public FilterOptions() {
			createControlsForOptions();
		}

		private void createControlsForOptions() {
			// TODO add operators by selected field. operators are differ by
			// field
			// type
			operator = new SelectListBox<DashboardFilterOperation>() {
				@Override
				public String getDisplayName(DashboardFilterOperation item) {
					return item.toString().toLowerCase();
				}
			};
			operator.addStyleName("operator");
			DashboardFilterOperation[] values = DashboardFilterOperation
					.values();
			List<DashboardFilterOperation> operatersList = Arrays
					.asList(values);
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

			groupName = new TextBox();
			valueBox = new TextBox();
			valueBox2 = new TextBox();
			Label and = new Label("and");
			HTML searchIcon = new HTML("<i class=searchIcon></i>");
			searchIcon.addDomHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub

				}
			}, ClickEvent.getType());
			final HTML removeIcon = new HTML("<i class =removeicon></i>");
			removeIcon.addDomHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					HorizontalPanel parent2 = (HorizontalPanel) removeIcon
							.getParent();
					VerticalPanel parent3 = (VerticalPanel) parent2.getParent();
					parent3.remove(parent2);
				}
			}, ClickEvent.getType());

			this.add(operator);
			this.add(groupName);
			this.add(valueBox);
			if (operator.getSelectedValue() == DashboardFilterOperation.BETWEEN) {
				this.add(and);
				this.add(valueBox2);
			}
			// TODO add this icon to panel by field type
			this.add(searchIcon);
			this.add(removeIcon);
		}

	}

}
