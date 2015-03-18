package com.project.greenote.client.mainview;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("MainView")
public interface NoteService extends RemoteService {
	String[] addNote(Integer id, String login, String title, String text,
			String date) throws NoteException;

	boolean deleteNote(Integer id, String login, String title, String text,
			String date) throws NoteException;

	ArrayList<String[]> getNotes(String login) throws NoteException;

}
