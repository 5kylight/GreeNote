package com.project.greenote.client;

import com.google.gwt.core.client.EntryPoint;

public class GreeNote implements EntryPoint {
	private LoginServiceAsync loginSvc = GWT.create(LoginService.class);
	private VerticalPanel verticalPanel_2 = new VerticalPanel();
	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	private Label loginInfoLabel = new Label();
	private HorizontalPanel horizontalPanel_2 = new HorizontalPanel();

	/** * Entry point method. */
	public void onModuleLoad() {

		// Associate the Main panel with the HTML host page.

		RootPanel rootPanel = RootPanel.get("stockList");
		
		
		
		verticalPanel_2.setSize("240px", "229px");

		verticalPanel_2.add(horizontalPanel);
		horizontalPanel.setSize("240", "30");

		Label lblLogin = new Label("Please log in:");
		horizontalPanel.add(lblLogin);
		horizontalPanel.setCellHorizontalAlignment(lblLogin,
				HasHorizontalAlignment.ALIGN_CENTER);
		lblLogin.setSize("80px", "30px");

		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		verticalPanel_2.add(horizontalPanel_1);
		horizontalPanel_1.setSize("240px", "30px");

		Label lblLogin_1 = new Label("Login:");
		horizontalPanel_1.add(lblLogin_1);
		lblLogin_1.setSize("75px", "30px");

		final TextBox txtbxLogin = new TextBox();
		horizontalPanel_1.add(txtbxLogin);
		txtbxLogin.setSize("165px", "30px");
		// txtbxLogin.setText("Login");

		verticalPanel_2.add(horizontalPanel_2);
		horizontalPanel_2.setSize("240px", "30px");

		Label lblPassword = new Label("Password:");
		horizontalPanel_2.add(lblPassword);
		lblPassword.setSize("75px", "30px");

		final PasswordTextBox txtbxPasswd = new PasswordTextBox();
		horizontalPanel_2.add(txtbxPasswd);
		txtbxPasswd.setSize("165px", "30");
		// txtbxPasswd.setText("passwd");

		HorizontalPanel horizontalPanel_3 = new HorizontalPanel();
		verticalPanel_2.add(horizontalPanel_3);
		horizontalPanel_3.setSize("240px", "30px");

		Button btnLogin = new Button("Log In");
		horizontalPanel_3.add(btnLogin);
		horizontalPanel_3.setCellHorizontalAlignment(btnLogin,
				HasHorizontalAlignment.ALIGN_RIGHT);
		btnLogin.setSize("118px", "30");

		btnLogin.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String login = txtbxLogin.getValue();
				String passwd = txtbxPasswd.getValue();

				login(login, passwd);

			}
		});
		txtbxLogin.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					String login = txtbxLogin.getValue();
					String passwd = txtbxPasswd.getValue();
					login(login, passwd);
				}
			}
		});
		txtbxPasswd.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					String login = txtbxLogin.getValue();
					String passwd = txtbxPasswd.getValue();
					login(login, passwd);
				}
			}
		});
		HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
		verticalPanel_2.add(horizontalPanel_4);
		horizontalPanel_4.setSize("240", "30");

		Button btnRegister = new Button("Register");
		horizontalPanel_4.add(btnRegister);
		horizontalPanel_4.setCellHorizontalAlignment(btnRegister,
				HasHorizontalAlignment.ALIGN_RIGHT);
		btnRegister.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String login = txtbxLogin.getValue();
				String passwd = txtbxPasswd.getValue();
				createNewUser(login, passwd);
			}
		});

		rootPanel.add(verticalPanel_2);
		// errors
		loginInfoLabel.setStyleName("LoginMessage");
		loginInfoLabel.setVisible(false);

		rootPanel.add(loginInfoLabel);

		txtbxLogin.setFocus(true);

	}

	protected void login(String login, final String passwd) {
		if (loginSvc == null) {
			loginSvc = GWT.create(LoginService.class);
		}

		// Set up the callback object.
		AsyncCallback<String[]> callback = new AsyncCallback<String[]>() {
			public void onFailure(Throwable caught) {
				// If the stock code is in the list of delisted codes, display
				// an error message.
				String details = caught.getMessage();

				loginInfoLabel.setText("Error: " + details);
				loginInfoLabel.setVisible(true);
			}

			@Override
			public void onSuccess(String[] result) {

				if (result == null) {
					loginInfoLabel
							.setText("Nie ma takiego uzytkownika w bazie");
					loginInfoLabel.setVisible(true);
					return;
				}

				if (result[1].equals(passwd)) {
					loginInfoLabel.setText("Wszytko gra!~");
					loginInfoLabel.setVisible(true);

				} else {
					loginInfoLabel.setText(result[1]);
					loginInfoLabel.setVisible(true);
				}
			}
		};

		loginSvc.getLoginData(login, callback);

	}

	private boolean createNewUser(String login, String password) {
		if (loginSvc == null) {
			loginSvc = GWT.create(LoginService.class);
		}
		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				String details = caught.getMessage();

				loginInfoLabel.setText("Error: " + details);
				loginInfoLabel.setVisible(true);
				return;

			}

			@Override
			public void onSuccess(String result) {
				if (result.equals("ok")) {
					loginInfoLabel
							.setText("Creating an Acount done without any Warnings!");
					loginInfoLabel.setVisible(true);
					return;
				} else {
					loginInfoLabel
							.setText("Some problems with creating your account: ");
					loginInfoLabel.setText(result);
					loginInfoLabel.setVisible(true);
					return;
				}
			}
		};
		String[] usr = new String[5];
		usr[0] = login;
		usr[1] = password;
		loginSvc.createNewUser(usr, callback);
		return true;

	}
}
