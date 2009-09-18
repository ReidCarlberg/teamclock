/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.events;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;

/**
 * @author REID
 */
public class GeneralEventMockImpl implements GeneralEvent {

    /* (non-Javadoc)
     * @see com.fivesticks.time.events.GeneralEvent#getSystemOwner()
     */
    
    
    public SystemOwner getSystemOwner() {
        
        return SystemOwnerTestFactory.singleton.buildMock();
    }

    /* (non-Javadoc)
     * @see com.fivesticks.time.events.GeneralEvent#getGeneralEventType()
     */
    public GeneralEventType getGeneralEventType() {
        
        return GeneralEventType.MOCK;
    }

    //2006-07-05 left this blank
    public SessionContext getSessionContext() {
        
        return null;
    }

}
