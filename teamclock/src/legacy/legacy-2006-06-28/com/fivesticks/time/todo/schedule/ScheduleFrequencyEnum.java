/*
 * Created on Oct 7, 2004
 *
 * 
 */
package com.fivesticks.time.todo.schedule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Nick
 * 
 *  
 */
public class ScheduleFrequencyEnum extends Enum {

    private static Collection all = new ArrayList();

    public static ScheduleFrequencyEnum MONTHLY = new ScheduleFrequencyEnum(
            "MONTHLY");

    public static ScheduleFrequencyEnum WEEKLY = new ScheduleFrequencyEnum(
            "WEEKLY");

    protected ScheduleFrequencyEnum(String arg0) {
        super(arg0);
        all.add(this);

    }

    public static ScheduleFrequencyEnum getByName(String name) {
        for (Iterator i = getAll().iterator(); i.hasNext();) {
            ScheduleFrequencyEnum sa = (ScheduleFrequencyEnum) i.next();
            if (sa.getName().equals(name)) {
                return sa;
            }
        }
        return null;
    }

    public static Collection getAll() {

        return all;

    }

    public String getFriendlyName() {
        String friendlyName = this.getName();
        friendlyName = friendlyName.toLowerCase();
        String[] tokens = friendlyName.split("_");
        friendlyName = "";
        //  friendlyName = friendlyName.replaceAll("_", " ");
        for (int i = 0; i < tokens.length; i++) {
            String capToken = "";
            String[] splitToken = tokens[i].split("");

            /*
             * Index 1 is white space.
             */
            capToken = splitToken[1].toUpperCase();

            for (int j = 2; j < splitToken.length; j++) {
                capToken += splitToken[j];
            }
            friendlyName += " " + capToken;
        }

        return friendlyName;

    }

}