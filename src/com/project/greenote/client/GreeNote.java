package com.project.greenote.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.project.greenote.client.loginview.LoginService;
import com.project.greenote.client.loginview.LoginServiceAsync;
import com.project.greenote.client.loginview.LoginView;
import com.project.greenote.client.mainview.MainView;

public class GreeNote implements EntryPoint {

	private LoginServiceAsync loginSvc = GWT.create(LoginService.class);
	
	private HorizontalPanel topLabel = new HorizontalPanel();
	private VerticalPanel centerPanel = new VerticalPanel();
	private HorizontalPanel bottomLabel = new HorizontalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private RootPanel rootPanel;
	private String login;

	public void onModuleLoad() {
		rootPanel = RootPanel.get("stockList");
		rootPanel.add(mainPanel);
		
		
		
		final LoginView loginView = new LoginView(loginSvc);
		mainPanel.add(loginView.getMainPanel());
		
		
		
		
		loginView.getFinalButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(loginView.isResultLog()){
					loginView.getDialogBox().setVisible(false);
					changeView();
					
					
				}
			}
		});

	}

	private void changeView() {
		MainView mainView = new MainView("tom");
		mainPanel.clear();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		mainPanel.add(mainView.getMainPanel());
		

	}

}
