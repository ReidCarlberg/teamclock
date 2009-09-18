/*
 * Created on Aug 15, 2003
 *
 */
package com.fstx.stdlib.author.old;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.useradmin.UseradminEvent;
import com.fivesticks.time.useradmin.UseradminEventBroker;
import com.fivesticks.time.useradmin.UseradminEventListener;
import com.fivesticks.time.useradmin.UseradminEventType;
import com.fstx.stdlib.common.dao.todelete.DAOException;

/**
 * @author Reid
 *  
 */
public class AuthorizationManager implements UseradminEventListener {

    public static final AuthorizationManager singleton = new AuthorizationManager();

    private static Map authorMap = new HashMap();

    private AuthorizationManager() {
        super();
        UseradminEventBroker.singleton.subscribe(this);
    }

    /**
     * pulls it from the hash table. If it is not there, it builds one using the
     * authorizationbeanbuilder.
     * 
     * @param user
     *            name
     * @return
     */
    public AuthorizationBean getAuthorizationBean(String username)
            throws AuthorizationException {

        AuthorizationBean ab = null;
        if (authorMap.containsKey(username)) {
            ab = (AuthorizationBean) authorMap.get(username);
            //log.info("Getting authorization bean from the hash.");
        } else {
            try {
                //log.info("Getting authorization bean from the builder.");
                ab = new AuthorizationBeanBuilder()
                        .buildAuthorizationBean(username);
                authorMap.put(username, ab);
            } catch (DAOException e) {
                throw new AuthorizationException(
                        "Unable to retrieve authorization list for user:  "
                                + username, e);
            }
        }

        return ab;
    }

    public boolean removeAuthorizationBean(String username) {
        authorMap.remove(username);
        return !authorMap.containsKey(username);
    }

    public void recycleAllPermissions() {
        authorMap = new HashMap();

    }

    static Log log = LogFactory.getLog(AuthorizationManager.class.getName());

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.useradmin.UseradminEventListener#notify(com.fivesticks.time.useradmin.UseradminEvent)
     */
    public void notify(UseradminEvent useradminEvent) {

        if (useradminEvent.getUseradminEventType() == UseradminEventType.TYPECHANGED) {
            this
                    .removeAuthorizationBean(useradminEvent.getUser()
                            .getUsername());
        }

    }
}