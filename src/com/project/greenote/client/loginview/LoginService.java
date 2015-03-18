package com.project.greenote.client.loginview;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("LoginView")
public interface LoginService extends RemoteService {

	boolean getLoginData(String login, String password) throws  LoginException;
	String createNewUser(String[] usr) throws LoginException;
	void start();

}
