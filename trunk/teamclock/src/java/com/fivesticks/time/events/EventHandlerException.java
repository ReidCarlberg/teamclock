/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.events;

/**
 * @author REID
 */
public class EventHandlerException extends Exception {

    /**
     *  
     */
    public EventHandlerException() {
        super();

    }

    /**
     * @param arg0
     */
    public EventHandlerException(String arg0) {
        super(arg0);

    }

    /**
     * @param arg0
     */
    public EventHandlerException(Throwable arg0) {
        super(arg0);

    }

    /**
     * @param arg0
     * @param arg1
     */
    public EventHandlerException(String arg0, Throwable arg1) {
        super(arg0, arg1);

    }

}