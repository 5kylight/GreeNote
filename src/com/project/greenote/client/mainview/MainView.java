package com.project.greenote.client.mainview;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.project.greenote.server.mainview.Notes;

public class MainView {

	private String login;

	private NoteServiceAsync noteSvc;

	private AbsolutePanel mainPanel = new AbsolutePanel();

	private FlexTable flexTable = new FlexTable();
	private FocusPanel focusPanel = new FocusPanel();
	private VerticalPanel addPanel = new VerticalPanel();
	private TextBox titleBox = new TextBox();
	private TextArea textArea = new TextArea();
	private Button addButton = new Button("ADD");
	private Label loginInfoLabel = new Label();

	public MainView(String login) {

		this.login = login;
		this.noteSvc = GWT.create(NoteService.class);

		flexTable.setText(0, 0, " Title");
		flexTable.setText(0, 1, " Text");
		flexTable.setCellSpacing(12);

		flexTable.setText(0, 2, " Date");
		flexTable.setText(0, 3, " Delete");

		mainPanel.add(flexTable);
		flexTable.setSize("757px", "100px");

		mainPanel.add(focusPanel);
		focusPanel.setSize("230px", "240px");

		addPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		focusPanel.setWidget(addPanel);
		addPanel.setSize("100%", "100%");

		loadNotes(login);

		titleBox.setName("Title");
		titleBox.setTitle("Uniqe Title");
		// titleBox.setText("Unique Title");
		addPanel.add(titleBox);
		titleBox.setWidth("230px");
		addPanel.add(textArea);
		textArea.setSize("100%", "147px");
		mainPanel.add(loginInfoLabel);
		addPanel.add(addButton);
		addPanel.setCellHorizontalAlignment(addButton,
				HasHorizontalAlignment.ALIGN_CENTER);
		addPanel.setCellVerticalAlignment(addButton,
				HasVerticalAlignment.ALIGN_MIDDLE);

		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String title = titleBox.getText();
				String text = textArea.getText();
				addNote(title, text);

			}
		});

	}

	private boolean addNote(String title, String text) {
		if (noteSvc == null) {
			noteSvc = GWT.create(NoteService.class);
		}
		AsyncCallback<String[]> callback = new AsyncCallback<String[]>() {

			@Override
			public void onFailure(Throwable caught) {
				String details = caught.toString();

				loginInfoLabel.setText("Error: " + details);
				loginInfoLabel.setVisible(true);

				return;
			}

			@Override
			public void onSuccess(String[] result) {
				if (result == null)
					return;
				// loadNotes("tom");
				// result 0 - login, 1 - title, 2 - text, 3 - date
				int row = flexTable.getRowCount();
				flexTable.setText(row, 0, result[1]);
				flexTable.setText(row, 1, result[2]);
				flexTable.setText(row, 2, result[3]);

			}
		};
		Date date = new Date();
		DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
		Integer id = flexTable.getRowCount();
		noteSvc.addNote(id + 1, login, title, text,
				dtf.format(date, TimeZone.createTimeZone(2)), callback);

		return false;

	}

	private boolean loadNotes(String login) {
		if (noteSvc == null) {
			noteSvc = GWT.create(NoteService.class);
		}

		AsyncCallback<ArrayList<String[]>> callback = new AsyncCallback<ArrayList<String[]>>() {

			@Override
			public void onFailure(Throwable caught) {
				String details = caught.toString();

				loginInfoLabel.setText("Error: " + details);
				loginInfoLabel.setVisible(true);

				return;
			}

			@Override
			public void onSuccess(ArrayList<String[]> result) {
				// TODO Auto-generated method stub
				
			}

			

		};
		noteSvc.getNotes(login, callback);

		return false;

	}

	public AbsolutePanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(AbsolutePanel mainPanel) {
		this.mainPanel = mainPanel;
	}
}
