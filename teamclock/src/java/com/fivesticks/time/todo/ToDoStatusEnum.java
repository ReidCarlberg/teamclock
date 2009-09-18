/*
 * Created on Feb 16, 2005 by REID
 */
package com.fivesticks.time.todo;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 */
public class ToDoStatusEnum extends Enum {

    public static final ToDoStatusEnum ASSIGNED = new ToDoStatusEnum("ASSIGNED");

    /**
     * @param arg0
     */
    protected ToDoStatusEnum(String arg0) {
        super(arg0);

    }

}