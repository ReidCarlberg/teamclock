/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.settings.event;

import com.fivesticks.time.events.AbstractGeneralEvent;
import com.fivesticks.time.events.GeneralEventType;

/**
 * @author Reid
 */
public class SettingsEventImpl extends AbstractGeneralEvent implements SettingsEvent {

    private GeneralEventType generalEventType = GeneralEventType.SETTINGS_EVENT;

//    private SystemOwner systemOwner;

    private SettingsEventType settingsEventType;

    /**
     * @return Returns the generalEventType.
     */
    public GeneralEventType getGeneralEventType() {
        return generalEventType;
    }

    /**
     * @param generalEventType
     *            The generalEventType to set.
     */
    public void setGeneralEventType(GeneralEventType generalEventType) {
        this.generalEventType = generalEventType;
    }

    /**
     * @return Returns the settingsEventType.
     */
    public SettingsEventType getSettingsEventType() {
        return settingsEventType;
    }

    /**
     * @param settingsEventType
     *            The settingsEventType to set.
     */
    public void setSettingsEventType(SettingsEventType settingsEventType) {
        this.settingsEventType = settingsEventType;
    }

//    /**
//     * @return Returns the systemOwner.
//     */
//    public SystemOwner getSystemOwner() {
//        return systemOwner;
//    }
//
//    /**
//     * @param systemOwner
//     *            The systemOwner to set.
//     */
//    public void setSystemOwner(SystemOwner systemOwner) {
//        this.systemOwner = systemOwner;
//    }
}