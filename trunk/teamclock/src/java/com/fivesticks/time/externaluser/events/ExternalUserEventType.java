/*
 * Created on Jan 22, 2005 by Reid
 */
package com.fivesticks.time.externaluser.events;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public class ExternalUserEventType extends Enum {

    public static final ExternalUserEventType ASSOCIATED = new ExternalUserEventType(
            "ASSOCIATED");

    public static final ExternalUserEventType DISASSOCIATED = new ExternalUserEventType(
            "DISASSOCIATED");

    /**
     * @param arg0
     */
    protected ExternalUserEventType(String arg0) {
        super(arg0);

    }

}