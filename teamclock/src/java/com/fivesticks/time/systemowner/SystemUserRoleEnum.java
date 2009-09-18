/*
 * Created on Dec 21, 2004 by REID
 */
package com.fivesticks.time.systemowner;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 */
public class SystemUserRoleEnum extends Enum {

    public static final SystemUserRoleEnum OPERATOR = new SystemUserRoleEnum(
            "OPERATOR");

    public static final SystemUserRoleEnum CUSTOMER = new SystemUserRoleEnum(
            "CUSTOMER");

    /**
     * @param arg0
     */
    public SystemUserRoleEnum(String arg0) {
        super(arg0);

    }

}