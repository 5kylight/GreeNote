package com.project.greenote.server.loginview;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

//import com.google.gwt.user.server.rpc.RemoteServiceServlet;



import de.novanic.eventservice.service.RemoteEventServiceServlet;

import com.project.greenote.client.loginview.LoginException;
import com.project.greenote.client.loginview.LoginService;
import com.project.greenote.client.loginview.ServerGeneratedNotification;
import com.project.greenote.server.HibernateUtil;

import de.novanic.eventservice.client.event.Event;


public class LoginServiceImpl extends RemoteEventServiceServlet implements
		LoginService {
	/**
	 * 
	 */

	private static Timer myEventGeneratorTimer;

	private static final long serialVersionUID = -8137563732356372118L;

	public boolean getLoginData(String login, String password)
			throws LoginException {
		try {
			Transaction trns = null;
			Session session = null;
			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null) {
				throw new LoginException("Error Creating Session");
			}
			if (login.equals("") || password.equals("")) {
				throw new LoginException("Empty login or password");
			}
			try {
				GreeUser user = new GreeUser(login, password);

				trns = session.beginTransaction();

				GreeUser usr = null;
				usr = (GreeUser) session.get(GreeUser.class, login);

				if (usr == null) {
					throw new LoginException(
							"This login doesn't exist in database");
				}
				session.getTransaction().commit();
				if (usr.getPassword().equals(password)) {
					return true;
				} else {
					return false;

				}
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
		return false;

	}

	@Override
	public String createNewUser(String[] usr) throws LoginException {
		try {
			Transaction trns = null;
			Session session = null;
			session = HibernateUtil.getSessionFactory().openSession();
			if (session == null) {
				throw new LoginException("Error Creating Session");
			}
			try {
				GreeUser user = new GreeUser(usr[0], usr[1]);

				trns = session.beginTransaction();
				String login = usr[0];
				String password = usr[1];
				if (login.equals(""))
					throw new LoginException("Your login shoudn't be empty");
				if (password.equals(""))
					throw new LoginException("Your password shoudn't be empty");

				GreeUser ur = null;
				ur = (GreeUser) session.get(GreeUser.class, login);
				
				if (ur != null) {
					throw new LoginException("Login USED, Try another one.");
				}

				try {

					session.save(user);
				} catch (HibernateError e) {
					throw new LoginException(e.toString());
				}
				session.getTransaction().commit();
			//	start();
				return "ok";
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
		return "Something goes wrong.";

	}

	@Override
	public void start() {
		if (myEventGeneratorTimer == null) {
			myEventGeneratorTimer = new Timer();
			myEventGeneratorTimer.schedule(new TimelyGenTask(), 0, 5000);
		}

	}

	private static String getCurrentTimeFormatted() {
		SimpleDateFormat theDateFormat = new SimpleDateFormat("HH:mm:ss");
		return theDateFormat.format(Calendar.getInstance().getTime());
	}

	public class TimelyGenTask extends TimerTask {

		@Override
		public void run() {

			final String theEventMessage = "GWTEventService is greeting everybody with \"Hello\" at "
					+ getCurrentTimeFormatted()
					+ " (and every five seconds again)! :-)";
			// create the event
			Event theEvent = new ServerGeneratedNotification(theEventMessage);
			// add the event, so clients can receive it
			
			addEvent(ServerGeneratedNotification.SERVER_MESSAGE_DOMAIN,
					theEvent);

		}

	}
}
