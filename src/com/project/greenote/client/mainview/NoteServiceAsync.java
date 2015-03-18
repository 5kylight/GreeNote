package com.project.greenote.client.mainview;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NoteServiceAsync {

	void addNote(Integer id, String login, String title, String text,
			String date, AsyncCallback<String[]> callback);

	void deleteNote(Integer id, String login, String title, String text,
			String date, AsyncCallback<Boolean> callback);

	void getNotes(String login, AsyncCallback<ArrayList<String[]>> callback);

}
