package com.fivesticks.time.events;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 */
public interface GeneralEvent {

    public SessionContext getSessionContext();
    
    public SystemOwner getSystemOwner();

    public GeneralEventType getGeneralEventType();

}