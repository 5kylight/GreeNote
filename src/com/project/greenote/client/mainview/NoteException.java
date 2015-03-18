package com.project.greenote.client.mainview;

import java.io.Serializable;

public class NoteException extends Exception implements Serializable {
	private String error;

	
	public NoteException(){
		
	}

	public NoteException(String error) {
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
