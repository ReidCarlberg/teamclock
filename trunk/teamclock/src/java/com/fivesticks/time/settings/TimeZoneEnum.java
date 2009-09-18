/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public class TimeZoneEnum extends Enum {

    private static Collection allTypes = new ArrayList();

    public static final TimeZoneEnum SUPERUSER = new TimeZoneEnum("SUPERUSER");

  

    /**
     * @param arg0
     */
    public TimeZoneEnum(String arg0) {
        super(arg0);
        allTypes.add(this);
    }

    public static Collection getAllTypes() {
        return allTypes;
    }

    public static TimeZoneEnum getByName(String name) {
        TimeZoneEnum ret = null;

        for (Iterator iter = TimeZoneEnum.getAllTypes().iterator(); iter
                .hasNext();) {
            TimeZoneEnum element = (TimeZoneEnum) iter.next();
            if (element.getName().equals(name)) {
                ret = element;
                break;
            }
        }

        return ret;
    }

}