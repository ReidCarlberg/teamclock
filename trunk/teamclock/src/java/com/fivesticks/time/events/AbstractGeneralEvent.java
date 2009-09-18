/*
 * Created on Jul 5, 2006 by Reid
 */
package com.fivesticks.time.events;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;

public abstract class AbstractGeneralEvent implements GeneralEvent {

    private SessionContext sessionContext;
    
    private SystemOwner systemOwner;

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }
}
