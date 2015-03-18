package com.project.greenote.server.mainview;

import java.io.Serializable;

public class Notes implements Serializable {
	private Integer id;
	private String login;
	private String title;
	private String text;
	private String date;

	public Notes() {
		this.id = (Integer) null;
		this.login = null;
		this.text = null;
		this.title = null;
		this.date = null;
	}

	public Notes(Integer id, String login, String title, String text,
			String date) {
		this.id = id;
		this.login = login;
		this.text = text;
		this.title = title;
		this.date = date;

	}

	public Notes(String login) {
		this.login = login;
		this.id = (Integer) null;
		this.text = null;
		this.title = null;
		this.date = null;
	}

	public String getLogin() {
		return login;
	}

	public String getTitle() {
		return title;
	}

	public String getDate() {
		return date;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
