/*
 * Created on Apr 26, 2006
 *
 */
package com.fivesticks.time.todo.rest;

import org.apache.commons.lang.enums.Enum;

public class RestToDoActionType extends Enum {

    public static final RestToDoActionType POST = new RestToDoActionType("POST");
    
    public static final RestToDoActionType LIST = new RestToDoActionType("LIST");
    
    protected RestToDoActionType(String arg0) {
        super(arg0);
    }
    
    public static RestToDoActionType getByName(String name) {
        return (RestToDoActionType) RestToDoActionType.getEnum(RestToDoActionType.class,name);
    }

}
