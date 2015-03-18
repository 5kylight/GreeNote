package com.project.greenote.client.loginview;

import de.novanic.eventservice.client.event.Event;
import de.novanic.eventservice.client.event.domain.Domain;
import de.novanic.eventservice.client.event.domain.DomainFactory;

public class ServerGeneratedNotification implements Event {

    public static final Domain SERVER_MESSAGE_DOMAIN = DomainFactory.getDomain("my_domain");

    private String myServerGeneratedMessage;

    /**
     * Needed for serialization
     */
    public ServerGeneratedNotification() {}

    public ServerGeneratedNotification(String aServerGeneratedMessage) {
        myServerGeneratedMessage = aServerGeneratedMessage;
    }

	    public String getServerGeneratedMessage() {
		return myServerGeneratedMessage;
	    }

	    public String toString() {
		StringBuilder theStringBuilder = new StringBuilder(100);
		theStringBuilder.append(ServerGeneratedNotification.class.getName());
		theStringBuilder.append(new char[] {' ', '('});
		theStringBuilder.append(myServerGeneratedMessage);
		theStringBuilder.append(')');
		return theStringBuilder.toString();        
	    }

}
