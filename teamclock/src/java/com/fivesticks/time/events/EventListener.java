/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.events;

/**
 * @author REID
 */
public interface EventListener {

    public void tell(GeneralEvent event) throws EventListenerException;
}
