/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.events;

/**
 * @author REID
 */
public interface EventHandler {

    public void handleEvent(GeneralEvent event) throws EventHandlerException;
}
