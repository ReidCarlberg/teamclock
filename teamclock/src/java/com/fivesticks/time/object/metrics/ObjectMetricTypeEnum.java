/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.object.metrics;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 */
public class ObjectMetricTypeEnum extends Enum {

    public static final ObjectMetricTypeEnum __TEST = new ObjectMetricTypeEnum(
            "__TEST");

    public static final ObjectMetricTypeEnum SYSTEM_LASTLOGIN_DATE = new ObjectMetricTypeEnum(
            "SYSTEM_LASTLOGIN_DATE");

    public static final ObjectMetricTypeEnum SYSTEM_LASTLOGIN_USER = new ObjectMetricTypeEnum(
            "SYSTEM_LASTLOGIN_USER");

    public static final ObjectMetricTypeEnum SYSTEM_LOGIN_COUNT = new ObjectMetricTypeEnum(
            "SYSTEM_LOGIN_COUNT");

    public static final ObjectMetricTypeEnum USER_LASTLOGIN_DATE = new ObjectMetricTypeEnum(
            "USER_LASTLOGIN_DATE");

    public static final ObjectMetricTypeEnum USER_LOGIN_COUNT = new ObjectMetricTypeEnum(
            "USER_LOGIN_COUNT");

    /**
     * @param arg0
     */
    protected ObjectMetricTypeEnum(String arg0) {
        super(arg0);
    }

}