/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.authen.events;

import java.util.Date;

import com.fivesticks.time.events.GeneralEvent;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public interface LoggableUserEvent extends GeneralEvent {

    public String getDescription();

    public User getUser();

    public Date getDate();

    public String getLocation();

}