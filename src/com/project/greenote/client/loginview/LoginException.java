package com.project.greenote.client.loginview;

import java.io.Serializable;

public class LoginException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 878769754199322068L;
	private String error;

	
	public LoginException() {

	}

	public LoginException(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	public String toString(){
		return error;
	}

}
