/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.settings.event;

import com.fivesticks.time.events.EventHandler;
import com.fivesticks.time.events.EventHandlerException;
import com.fivesticks.time.events.GeneralEvent;
import com.fivesticks.time.settings.broker.MasterSettingsBroker;

/**
 * @author Reid
 */
public class SettingsEventHandler implements EventHandler {

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.events.EventHandler#handleEvent(com.fivesticks.time.events.GeneralEvent)
     */
    public void handleEvent(GeneralEvent event) throws EventHandlerException {
        handleSettingsEvent((SettingsEvent) event);
    }

    /**
     * @param event
     */
    public void handleSettingsEvent(SettingsEvent event) {
        MasterSettingsBroker.singleton.removeSettings(event.getSystemOwner());

    }

}