package com.project.greenote.server.loginview;

import java.io.Serializable;

public class GreeUser implements Serializable {

	private String login;
	private String password;
	private String email;
	private String date;
	private String about;
	private boolean active;

	public GreeUser() {
		this.login = null;
		this.password = null;
		this.email = null;
		this.date = null;
		this.about = null;
		this.active = false;

	}

	public GreeUser(String login, String password) {
		this.login = login;
		this.password = password;
		this.email = null;
		this.date = null;
		this.about = null;
		this.active = false;

	}

	public GreeUser(String login, String password, String email, String date,
			String about, boolean active) {

		this.login = login;
		this.password = password;
		this.email = email;
		this.date = date;
		this.about = about;
		this.active = active;
	}

	public String getAbout() {
		return about;
	}

	public boolean getActive() {
		return active;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getDate() {
		return date;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
