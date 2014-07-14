package com.vimukti.dashboard.client.ui.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base Dialog is abstract class which provides common ground for all small
 * Windows or Dialogs.like header, body, footer with help, ok & cancel buttons
 * 
 * @author kumar kasimala
 * 
 */
public abstract class BaseDialog<T> extends DialogBox {
	protected StyledPanel headerLayout;
	protected StyledPanel bodyLayout;
	protected StyledPanel footerLayout;
	protected StyledPanel mainPanel;

	protected Button cancelBtn;
	protected Button okbtn;
	private InputDialogHandler dialogHandler;
	private StyledPanel errorPanel;
	private final Map<Object, Widget> errorsMap = new HashMap<Object, Widget>();
	private static Logger logger = Logger.getLogger("BaseDialog");
	protected ActionCallback<T> callback;

	protected T data;
	private String text;

	/**
	 * Creates new Instance
	 */
	public BaseDialog() {
		this(null);
	}

	public BaseDialog(String text) {
		this.addStyleName("designer");
		this.text = text;
		createControls();
		if (okbtn != null) {
			okbtn.setFocus(true);
		}
		sinkEvents(Event.ONKEYPRESS);
		sinkEvents(Event.ONMOUSEOVER);
	}

	protected void createControls() {
		mainPanel = new StyledPanel("modal fade in");

		errorPanel = new StyledPanel("errorPanel");
		errorPanel.setVisible(false);
		errorPanel.addStyleName("errors error-panel");
		mainPanel.add(errorPanel);
		/**
		 * Header Layout
		 */
		headerLayout = new StyledPanel("modal-header");

		if (text != null) {
			Label label = new Label();
			label.setText(text);
			headerLayout.add(label);
		}

		HTML helpLink = new HTML("help for this Page");
		// need to insert link here by externalization(using properties file)
		helpLink.setStyleName("<a herf=''>");
		headerLayout.add(helpLink);

		Image helpIcon = new Image(
				"<i data-dismiss='modal' class='help-Icon right'></i>");
		helpIcon.setStyleName("help-icon");

		headerLayout.add(helpIcon);
		HTML closeIcon = new HTML(
				"<i data-dismiss='modal' class='icon-remove right'></i>");

		closeIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				processCancel();
			}
		});
		headerLayout.add(closeIcon);

		mainPanel.add(headerLayout);

		/**
		 * Body LayOut
		 */
		bodyLayout = new StyledPanel("modal-body");
		mainPanel.add(bodyLayout);

		/**
		 * Footer Layout
		 */
		footerLayout = new StyledPanel("modal-footer");

		mainPanel.add(footerLayout);
		createButtons(footerLayout);
		super.add(mainPanel);
	}

	public void createButtons(HasWidgets parent) {
		this.okbtn = new Button("OK");
		okbtn.addStyleName("btn-success");

		okbtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				processOK();
			}
		});
		okbtn.setFocus(true);
		okbtn.setStyleName("btn btn-primary btn-space");

		cancelBtn = new Button("Cancel");
		cancelBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				logger.info("Close Dialog");
				processCancel();
			}
		});
		cancelBtn.setStyleName("btn btn-space");
		parent.add(okbtn);
		parent.add(cancelBtn);
		okbtn.setEnabled(true);
		cancelBtn.setEnabled(true);
	}

	public void add(Widget w) {
		bodyLayout.add(w);
	}

	/**
	 * add body to this Dialog
	 * 
	 * @param layout
	 */
	public void setBodyLayout(Panel layout) {
		this.bodyLayout.add(layout);
	}

	/**
	 * called when cancelButton clicks
	 */
	protected void processCancel() {

		if (dialogHandler != null) {
			dialogHandler.onCancel();

		}
		if (onCancel()) {
			removeFromParent();
		}
	}

	protected void updateCompany() {

	}

	/**
	 * Called when Ok button clicked
	 */
	protected void processOK() {
		clearAllErrors();
		okbtn.setFocus(true);
		boolean ok = onOK();
		if (ok && dialogHandler != null) {
			ok |= dialogHandler.onOK();
		}
		if (ok) {
			this.removeFromParent();
		}
	}

	/**
	 * add InputDialog handler, methods in handler will call when particular
	 * event happen on this Dialog.default implementation does nothing
	 * 
	 * @param handler
	 */
	public void addInputDialogHandler(InputDialogHandler handler) {
		this.dialogHandler = handler;
	}

	@Override
	public void onBrowserEvent(Event event) {
		switch (DOM.eventGetType(event)) {
		case Event.ONKEYPRESS:
			int keycode = event.getKeyCode();
			if (KeyCodes.KEY_ESCAPE == keycode) {
				processCancel();
			}
			break;
		case Event.ONMOUSEOVER:
			// cancelBtn.setFocus(true);

		case Event.ONKEYDOWN:
			// cancelBtn.setFocus(true);
			break;
		default:
			break;
		}
		DomEvent.fireNativeEvent(event, this, this.getElement());
	}

	/**
	 * Adds Error
	 * 
	 * @param item
	 * @param erroMsg
	 */
	public void addError(Object item, String erroMsg) {
		HTML error = new HTML("<li>" + erroMsg + "</li>");
		this.errorPanel.add(error);
		this.errorPanel.setVisible(true);
		this.errorsMap.put(item, error);
	}

	/**
	 * Clears All Errors
	 */
	public void clearAllErrors() {
		this.errorsMap.clear();
		this.errorPanel.clear();
		this.errorPanel.setVisible(false);
	}

	/**
	 * Clears the given Error
	 * 
	 * @param obj
	 */
	public void clearError(Object obj) {
		Widget remove = this.errorsMap.remove(obj);
		if (remove != null) {
			this.errorPanel.remove(remove);
			if (this.errorsMap.isEmpty()) {
				errorPanel.setVisible(false);
			}
		}
	}

	public Object getGridColumnValue(T obj, int index) {
		return null;
	}

	protected void saveOrUpdate(T core) {

	}

	protected abstract boolean onOK();

	public ActionCallback<T> getCallback() {
		return callback;
	}

	public void setCallback(ActionCallback<T> callback) {
		this.callback = callback;
	}

	/**
	 * Used to tell the call backs about the result of showing this dialog
	 * 
	 * @param result
	 */
	public void setResult(T result) {
		if (this.callback != null) {
			this.callback.onSuccess(result);
		}
	}

	public abstract void setFocus();

	@Override
	protected void onAttach() {

		super.onAttach();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				setFocus();
			}
		});

	}

	protected boolean isViewDialog() {
		return true;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	protected boolean onCancel() {
		com.google.gwt.user.client.History.back();
		return true;
	}

	public String getText() {
		return text;
	}

	public void removeOkButton() {
		footerLayout.remove(okbtn);
	}

	public void removeCancelButton() {
		footerLayout.remove(cancelBtn);
	}

	public Button addNewButton(String name) {
		Button newButton = new Button(name);
		newButton.addStyleName("new-footer-button" + name);
		footerLayout.add(newButton);
		return newButton;
	}
}
