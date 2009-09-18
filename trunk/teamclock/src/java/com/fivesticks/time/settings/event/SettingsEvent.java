/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.settings.event;

import com.fivesticks.time.events.GeneralEvent;

/**
 * @author Reid
 */
public interface SettingsEvent extends GeneralEvent {

    public SettingsEventType getSettingsEventType();
}