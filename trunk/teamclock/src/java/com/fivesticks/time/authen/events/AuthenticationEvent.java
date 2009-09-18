/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.authen.events;

import java.util.Date;

import com.fivesticks.time.events.AbstractGeneralEvent;
import com.fivesticks.time.events.GeneralEventType;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public class AuthenticationEvent extends AbstractGeneralEvent implements LoggableUserEvent {

    private GeneralEventType generalEventType;
    private String description;
    private User user;
    private Date date;
    private String location;
//    private SystemOwner systemOwner;
 
    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
    }
    /**
     * @param date The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return Returns the generalEventType.
     */
    public GeneralEventType getGeneralEventType() {
        return generalEventType;
    }
    /**
     * @param generalEventType The generalEventType to set.
     */
    public void setGeneralEventType(GeneralEventType generalEventType) {
        this.generalEventType = generalEventType;
    }
    /**
     * @return Returns the location.
     */
    public String getLocation() {
        return location;
    }
    /**
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }
//    /**
//     * @return Returns the systemOwner.
//     */
//    public SystemOwner getSystemOwner() {
//        return systemOwner;
//    }
//    /**
//     * @param systemOwner The systemOwner to set.
//     */
//    public void setSystemOwner(SystemOwner systemOwner) {
//        this.systemOwner = systemOwner;
//    }
//    /**
//     * @return Returns the username.
//     */
//    public String getUsername() {
//        return username;
//    }
//    /**
//     * @param username The username to set.
//     */
//    public void setUsername(String username) {
//        this.username = username;
//    }
/**
 * @return Returns the user.
 */
    public User getUser() {
	    return user;
	}
	/**
	 * @param user The user to set.
	 */
	public void setUser(User user) {
	    this.user = user;
	}
}
