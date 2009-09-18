/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen;

import java.util.Collection;
import java.util.Date;

/**
 * @author Reid
 *  
 */
public interface LoginHistoryBD  {

    public static final String LOGIN = "Login";

    public static final String LOGOUT = "Logout";

    public abstract void recordLogin(String username, Date timestamp,
            String location, String ownerKey) throws LoginHistoryException;

    public abstract void recordLogout(String username, Date timestamp,
            String location, String ownerKey) throws LoginHistoryException;

    public abstract void recordEvent(String username, Date timestamp,
            String location, String ownerKey, String eventDescription)
            throws LoginHistoryException;

    public abstract Collection list() throws LoginHistoryException;

    public abstract Collection search(LoginHistoryFilterParameters params)
            throws LoginHistoryException;
    
    
    public Collection searchStatsByUser(LoginHistoryFilterParameters params);
    
    public Collection searchStatsByOwner(LoginHistoryFilterParameters params);
}