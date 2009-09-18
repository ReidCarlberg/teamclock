/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.settings.event;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public class SettingsEventType extends Enum {

    public static final SettingsEventType SETTINGS_UPDATED = new SettingsEventType(
            "SETTINGS_UPDATED");

    /**
     * @param arg0
     */
    protected SettingsEventType(String arg0) {
        super(arg0);

    }

}