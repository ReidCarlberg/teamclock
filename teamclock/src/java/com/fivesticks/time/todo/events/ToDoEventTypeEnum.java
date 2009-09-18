/*
 * Created on Jan 3, 2005 by REID
 */
package com.fivesticks.time.todo.events;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 */
public class ToDoEventTypeEnum extends Enum {

    public static final ToDoEventTypeEnum COMPLETE = new ToDoEventTypeEnum(
            "COMPLETE");

    public static final ToDoEventTypeEnum REASSIGNED = new ToDoEventTypeEnum(
            "REASSIGNED");

    public static final ToDoEventTypeEnum EXERNALADD = new ToDoEventTypeEnum(
            "EXERNALADD");

    public static final ToDoEventTypeEnum COMMENTED = new ToDoEventTypeEnum(
            "COMMENTED");

    public static final ToDoEventTypeEnum ACKNOWLEDGE = new ToDoEventTypeEnum(
            "ACKNOWLEDGE");

    /**
     * @param arg0
     */
    public ToDoEventTypeEnum(String arg0) {
        super(arg0);

    }

}