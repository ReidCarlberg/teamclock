/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.settings.event;

import com.fivesticks.time.settings.broker.MasterSettingsBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * 2006-05-16 Updated to just remove the settings from the broker.
 * 
 * @author Reid
 */
public class SettingsEventPublisher {

    public void publishSettingsModifiedEvent(SystemOwner systemOwner) {

//        SettingsEventImpl event = new SettingsEventImpl();
//
//        event.setSettingsEventType(SettingsEventType.SETTINGS_UPDATED);
//
//        event.setSystemOwner(systemOwner);
//
//        GeneralEventQueue.singleton.add(event);
        
        MasterSettingsBroker.singleton.removeSettings(systemOwner);
    }
}