/*
 * Created on Jun 11, 2004
 *
 */
package com.fivesticks.time.common.util;

/**
 * @author REID
 *  
 */
public class DateTimeRounderFactory {

    public static final DateTimeRounderFactory factory = new DateTimeRounderFactory();

    public Rounder build() {
        return new RounderRawImpl();
    }

    public Rounder build(String rounderName) {
        if (rounderName == null || rounderName.equalsIgnoreCase("raw"))
            return new RounderRawImpl();
        else if (rounderName.equalsIgnoreCase("quarter"))
            return new RounderQuarterImpl();
        else if (rounderName.equalsIgnoreCase("sixths"))
            return new RounderSixthsImpl();
        else if (rounderName.equalsIgnoreCase("tenths"))
            return new RounderTenthsImpl();
        else
            return new RounderRawImpl();
    }
}