package com.vimukti.dashboard.client.ui.controls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.vimukti.dashboard.client.data.CustomObject;
import com.vimukti.dashboard.client.data.DashboardData;
import com.vimukti.dashboard.client.data.DashboardFilterOperation;
import com.vimukti.dashboard.client.data.DashboardFilterOptions;
import com.vimukti.dashboard.client.data.DashboardFilters;
import com.vimukti.dashboard.client.data.Field;
import com.vimukti.dashboard.client.data.Field.FieldType;
import com.vimukti.dashboard.client.data.IDashboardServiceAsync;
import com.vimukti.dashboard.client.ui.utils.BaseDialog;
import com.vimukti.dashboard.client.ui.utils.SelectListBox;
import com.vimukti.dashboard.client.ui.utils.TextItem;

public class AddFilterDialog extends BaseDialog {

	private SelectListBox<Field> fields;
	private TextItem displayName;
	private Button addRow;
	private List<DashboardFilterOptions> filterOptionsList;
	private List<FilterOptions> filterOptionsPanels;
	private DashboardData dashboard;
	private DashboardFilters options;
	private boolean isNewFilter;
	private IDashboardServiceAsync dashboardServiceObject = Dashboard
			.getDashboardServiceObject();

	// use this dialog to as edit Filter dialog
	public AddFilterDialog(DashboardData dashboard, boolean isNewFilter) {
		this.dashboard = dashboard;
		this.addStyleName("dashboard-filter-dialog");
		createControls();
	}

	@Override
	protected void createControls() {
		super.createControls();
		if (isNewFilter) {
			prepareFields();
		}
		setDefaultValueForDisplayName();
		displayName = new TextItem("DispalayName");
		displayName.addStyleName("displayname");
		HorizontalPanel hpanel = new HorizontalPanel();
		Label filterOptions = new Label("Filter Options");
		// TODO to show help text use externalization (using properties file)
		HTML helpText = new HTML();
		helpText.addStyleName("helpText-icon");
		hpanel.add(filterOptions);
		hpanel.add(helpText);
		prepareFilterOptionsPanel();
		if (!isNewFilter) {
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
		FlowPanel optionsPanel = new FlowPanel();
		optionsPanel.addStyleName("filter-optionspanel");
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel headerName = new HorizontalPanel();
		Label operator = new Label("Operator");
		operator.addStyleName("header-operator");
		Label value = new Label("value");
		value.addStyleName("header-value");
		headerName.add(operator);
		headerName.add(value);
		vPanel.add(headerName);
		FlowPanel fPanel = new FlowPanel();
		fPanel.addStyleName("addButton-panel");
		addRow = new Button("Add Row");
		addRow.addStyleName("addRow-icon");
		addRow(vPanel);
		fPanel.add(addRow);
		this.add(optionsPanel);
	}

	private void addRow(final VerticalPanel vPanel) {
		addRow.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				FilterOptions createOptionRow = new FilterOptions();
				createOptionRow.addStyleName("options-row");
				filterOptionsPanels = new ArrayList<FilterOptions>();
				filterOptionsPanels.add(createOptionRow);
				vPanel.add(createOptionRow);

				if (options != null) {
					List<DashboardFilterOptions> dashboardFilterOptions = options
							.getDashboardFilterOptions();
					for (DashboardFilterOptions filter : dashboardFilterOptions) {
						FilterOptions createOptionRow1 = new FilterOptions();
						createOptionRow1.operator.setSelectedValue(filter
								.getOperator());
					}
				}
			}
		});
	}

	@Override
	protected boolean onOK() {
		update();
		DashboardFilters filters = new DashboardFilters();
		filters.setName(fields.getSelectedValue().getName());
		filters.setDisplayLabel(displayName.getText());
		filters.setDashboardFilterOptions(filterOptionsList);
		List<DashboardFilters> dashboardFilters = dashboard
				.getDashboardFilters();
		if (dashboardFilters == null) {
			dashboardFilters = new ArrayList<DashboardFilters>();
		}
		dashboardFilters.add(filters);
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
