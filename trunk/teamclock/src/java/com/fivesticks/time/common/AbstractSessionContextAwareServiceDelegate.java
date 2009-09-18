/*
 * Created on Jul 5, 2006 by Reid
 */
package com.fivesticks.time.common;

import java.util.Date;

public abstract class AbstractSessionContextAwareServiceDelegate extends
        AbstractServiceDelegate {

    private SessionContext sessionContext;

    public SessionContext getSessionContext() {
        return sessionContext;
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
        
        this.setSystemOwner(sessionContext.getSystemOwner());
    }
    
    public void decorateDates(AbstractDateAwareTimeObject abstractDateAwareTimeObject) {
        
        Date now = new Date();
        if (sessionContext!= null) {
            now = sessionContext.getResolvedNow();
        }

        if (abstractDateAwareTimeObject.getId() == null) {
            abstractDateAwareTimeObject.setCreateDate(now);
        }
        
        abstractDateAwareTimeObject.setModifyDate(now);
        
    }
    
}
