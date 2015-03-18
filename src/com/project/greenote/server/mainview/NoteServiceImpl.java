package com.project.greenote.server.mainview;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.project.greenote.client.mainview.NoteException;
import com.project.greenote.client.mainview.NoteService;
import com.project.greenote.server.HibernateUtil;
import com.project.greenote.server.mainview.Notes;

public class NoteServiceImpl extends RemoteServiceServlet implements
		NoteService {

	@Override
	public String[] addNote(Integer id, String login, String title,
			String text, String date) throws NoteException {

		try {
			Transaction trns = null;
			Session session = null;
			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null) {
				throw new NoteException("Error Creating Session");
			}
			try {

				trns = session.beginTransaction();

				Notes note = new Notes(id, login, title, text, date);

				if (login == null) {
					throw new NoteException("Error with login!");
				}

				try {

					session.save(note);
				} catch (HibernateError e) {
					throw new NoteException(e.toString());
				}
				session.getTransaction().commit();
				String[] result = null;
				result = new String[4];

				// result 0 - login, 1 - title, 2 - text, 3 - date

				result[0] = login;
				result[1] = title;
				result[2] = text;
				result[3] = date;
				return result;
			} catch (RuntimeException e) {
				if (trns != null) {
					trns.rollback();
				}
				e.printStackTrace();
			} finally {
				session.flush();
				session.close();
			}

		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();

		}

		return null;
	}

	@Override
	public boolean deleteNote(Integer id, String login, String title,
			String text, String date) throws NoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String[]> getNotes(String login) throws NoteException {
		try {
			Transaction trns = null;
			Session session = null;
			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null) {
				throw new NoteException("Error Creating Session");
			}
			try {
				trns = session.beginTransaction();
				ArrayList<Notes> result = new ArrayList<>();

				if (login == null) {
					throw new NoteException("Error with login!");
				}
				// Notes note = new Notes(login);
				try {

					Query query = session
							.createQuery("Select title from Notes where login = :login");
					query.setParameter("login", login);

					List<String> list = query.list();
					for(String s : list){
						System.out.println(s);
					}

				} catch (HibernateError e) {
					throw new NoteException(e.toString());
				}
				session.getTransaction().commit();
				return null;
				// result 0 - login, 1 - title, 2 - text, 3 - d
			} catch (RuntimeException e) {
				if (trns != null) {
					trns.rollback();
				}
				e.printStackTrace();
			} finally {
				session.flush();
				session.close();
			}

		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();

		}

		return null;
	}
}
