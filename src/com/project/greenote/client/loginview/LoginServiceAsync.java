package com.project.greenote.client.loginview;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void getLoginData(String login, String password,
			AsyncCallback<Boolean> callback);

	void createNewUser(String[] usr, AsyncCallback<String> callback);

	void start(AsyncCallback<Void> callback);

}
