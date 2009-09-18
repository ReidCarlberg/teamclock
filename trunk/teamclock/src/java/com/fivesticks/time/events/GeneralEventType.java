/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.events;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 */
public class GeneralEventType extends Enum {

    public static final GeneralEventType AUTHENTICATION_EVENT = new GeneralEventType(
            "AUTHENTICATION_EVENT");

    public static final GeneralEventType SETTINGS_EVENT = new GeneralEventType(
            "SETTINGS_EVENT");;

    public static final GeneralEventType TODO_EVENT = new GeneralEventType(
            "TODO_EVENT");

    public static final GeneralEventType USER_EVENT = new GeneralEventType(
            "USER_EVENT");

    public static final GeneralEventType EXTERNAL_USER_EVENT = new GeneralEventType(
            "EXTERNAL_USER_EVENT");

    protected static final GeneralEventType MOCK = new GeneralEventType("MOCK");
    /**
     * @param arg0
     */
    public GeneralEventType(String arg0) {
        super(arg0);

    }

}