/*
 * Created on Nov 25, 2004 by REID
 */
package com.fivesticks.time.useradmin.events;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 */
public class UseradminEventType extends Enum {

    public static final UseradminEventType ACTIVATED = new UseradminEventType(
            "ACTIVATED");

    public static final UseradminEventType DEACTIVATED = new UseradminEventType(
            "DEACTIVATED");

    public static final UseradminEventType TYPECHANGED = new UseradminEventType(
            "TYPECHANGED");

    public static final UseradminEventType CREATED = new UseradminEventType(
            "CREATED");

    public static final UseradminEventType DELETED = new UseradminEventType(
            "DELETED");

    public static final UseradminEventType CREATE_WITHOUT_ORGINATING_USER = new UseradminEventType(
            "CREATE_WITHOUT_ORGINATING_USER");

    public static final UseradminEventType PASSWORDCHANGED = new UseradminEventType(
            "PASSWORDCHANGED");;;

    /**
     * @param arg0
     */
    public UseradminEventType(String arg0) {
        super(arg0);

    }

}