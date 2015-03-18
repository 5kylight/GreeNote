package com.project.greenote.client.loginview;

import java.io.Serializable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7144176395851316982L;

	private LoginServiceAsync loginSvc;

	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	private Label loginInfoLabel = new Label();
	private HorizontalPanel horizontalPanel_2 = new HorizontalPanel();
	private Label lblLogin = new Label("Please log in:");
	private HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
	private Label lblLogin_1 = new Label("Login:");
	private final TextBox txtbxLogin = new TextBox();
	private Label lblPassword = new Label("Password:");
	private final PasswordTextBox txtbxPasswd = new PasswordTextBox();
	private Button btnLogin = new Button("Log In");
	private DialogBox dialogBox = new DialogBox(true);
	private HorizontalPanel horizontalPanel_3 = new HorizontalPanel();

	private boolean resultLog;
	
	
	
	private String login = null;
	 

	public LoginView(LoginServiceAsync loginSvc) {
		this.loginSvc = loginSvc;
		// setting mainPanel
		mainPanel.setSize("240px", "229px");
		// adding first horizontalPanel
		mainPanel.add(horizontalPanel);
		horizontalPanel.setSize("240", "30");
		// adding login label and lobin textbox
		horizontalPanel.add(lblLogin);
		horizontalPanel.setCellHorizontalAlignment(lblLogin,
				HasHorizontalAlignment.ALIGN_CENTER);
		lblLogin.setSize("80px", "30px");

		mainPanel.add(horizontalPanel_1);
		horizontalPanel_1.setSize("240px", "30px");

		horizontalPanel_1.add(lblLogin_1);
		lblLogin_1.setSize("75px", "30px");

		horizontalPanel_1.add(txtbxLogin);
		txtbxLogin.setSize("165px", "30px");

		mainPanel.add(horizontalPanel_2);
		horizontalPanel_2.setSize("240px", "30px");

		horizontalPanel_2.add(lblPassword);
		lblPassword.setSize("75px", "30px");

		horizontalPanel_2.add(txtbxPasswd);
		txtbxPasswd.setSize("165px", "30");

		mainPanel.add(horizontalPanel_3);
		horizontalPanel_3.setSize("240px", "30px");

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
					loginInfoLabel.setVisible(false);
					String login = txtbxLogin.getValue();
					String passwd = txtbxPasswd.getValue();

					login(login, passwd);
				}
			}
		});
		txtbxPasswd.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					loginInfoLabel.setVisible(false);
					 String login = txtbxLogin.getValue();
					String passwd = txtbxPasswd.getValue();
					login(login, passwd);
				}
			}
		});

		HorizontalPanel horizontalPanel_4 = new HorizontalPanel();
		mainPanel.add(horizontalPanel_4);
		horizontalPanel_4.setSize("240", "30");
		Button btnRegister = new Button("Register");
		horizontalPanel_4.add(btnRegister);
		horizontalPanel_4.setCellHorizontalAlignment(btnRegister,
				HasHorizontalAlignment.ALIGN_RIGHT);

		btnRegister.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loginInfoLabel.setVisible(false);
				String login = txtbxLogin.getValue();
				String passwd = txtbxPasswd.getValue();
				createNewUser(login, passwd);
			}
		});

		loginInfoLabel.setStyleName("LoginMessage");
		loginInfoLabel.setVisible(false);

	}

	public Integer getResultLogin() {
		return resultLogin;
	}

	public void setResultLogin(Integer resultLogin) {
		this.resultLogin = resultLogin;
	}

	private Integer resultLogin = null;
	//private final FlowPanel flowPanel = new FlowPanel();
	//private final Button btnNewButton = new Button("New button");
	private final Button finalButton = new Button("Continue.. ");

	public boolean login() {
		this.login = txtbxLogin.getValue();
		String passwd = txtbxPasswd.getValue();
		if (login(login, passwd))
			return true;
		return false;
	}

	private boolean login(String login, String password) {
		this.login = login;
		if (loginSvc == null) {
			loginSvc = GWT.create(LoginService.class);
		}

		// Set up the callback object.
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

			public void onFailure(Throwable caught) {

				String details = caught.toString();

				loginInfoLabel.setText("Error: " + details);
				loginInfoLabel.setVisible(true);
				resultLogin = 1;

			}

			@Override
			public void onSuccess(Boolean result) {

				if (result) {
					result = true;
					dialogBox.setAnimationEnabled(true);

					dialogBox.setText("Loging OK!");
					dialogBox.setTitle("Login OK");
					dialogBox.add(finalButton);
					dialogBox.center();
					
					dialogBox.show();
					dialogBox.setAutoHideEnabled(true);
					resultLogin = 0;
					resultLog = true;

				} else {

					dialogBox.setAnimationEnabled(true);
					dialogBox.setText("Loging :(!");
					dialogBox.setTitle("Loging");
					dialogBox.setGlassEnabled(true);
					// dialogBox.setPixelSize(200, 200);
					dialogBox.center();
					dialogBox.show();
					resultLogin = 1;
					resultLog = false;

				}
			}
		};

		loginSvc.getLoginData(login, password, callback);

		// // TESTING EVENT SERVICE
		// FlowPanel thePanel = new FlowPanel();
		//
		// thePanel.setSize("60%", "60%");
		// thePanel.setStyleName("borderPanel");
		//
		// final ListBox myEventReceiveBoxUI = new ListBox(true);
		// myEventReceiveBoxUI.setSize("99%", "91%");
		// myEventReceiveBoxUI.addItem("Listening for events...");
		//
		// LoginServiceAsync svrNot = GWT.create(LoginService.class);
		// svrNot.start(new AsyncCallback<Void>() {
		// @Override
		// public void onFailure(Throwable caught) {
		// }
		//
		// public void onSuccess(Void result) {
		// }
		// });

		//
		//
		// RemoteEventService eventSvc = RemoteEventServiceFactory.getInstance()
		// .getRemoteEventService();
		// eventSvc.addListener(ServerGeneratedNotification.SERVER_MESSAGE_DOMAIN,
		// new RemoteEventListener() {
		//
		// @Override
		// public void apply(Event anEvent) {
		// if (anEvent instanceof ServerGeneratedNotification) {
		// ServerGeneratedNotification notice = (ServerGeneratedNotification)
		// anEvent;
		// myEventReceiveBoxUI.addItem(notice
		// .getServerGeneratedMessage());
		// }
		//
		// }
		// });
		//
		// final Label theEventReceiveLabelUI = new
		// Label("Received server events");
		// theEventReceiveLabelUI.setSize("99%", "8%");
		//
		// thePanel.add(theEventReceiveLabelUI);
		// thePanel.add(myEventReceiveBoxUI);

		// mainPanel.add(thePanel);

		return resultLog;
	}

	private boolean createNewUser(String login, String password) {

		if (loginSvc == null) {
			loginSvc = GWT.create(LoginService.class);
		}
		AsyncCallback<String> callback = new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				String details = caught.toString();

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
		txtbxLogin.setFocus(true);
		loginSvc.createNewUser(usr, callback);
		return true;

	}

	public Label getLoginInfoLabel() {
		return loginInfoLabel;
	}

	public void setLoginInfoLabel(Label loginInfoLabel) {
		this.loginInfoLabel = loginInfoLabel;
	}

	public VerticalPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(VerticalPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public void setFocus() {
		txtbxLogin.setFocus(true);
	}

	public TextBox getTxtbxLogin() {
		return txtbxLogin;
	}

	public PasswordTextBox getTxtbxPasswd() {
		return txtbxPasswd;
	}

	public Button getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(Button btnLogin) {
		this.btnLogin = btnLogin;
	}

	public Button getFinalButton() {
		return finalButton;
	}

	public boolean isResultLog() {
		return resultLog;
	}

	public void setResultLog(boolean result) {
		this.resultLog = result;
	}

	public DialogBox getDialogBox() {
		return dialogBox;
	}

	public void setDialogBox(DialogBox dialogBox) {
		this.dialogBox = dialogBox;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
	

}
