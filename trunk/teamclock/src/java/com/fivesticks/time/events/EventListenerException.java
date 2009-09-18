/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.events;

/**
 * @author REID
 */
public class EventListenerException extends Exception {

    /**
     * 
     */
    public EventListenerException() {
        super();

    }

    /**
     * @param arg0
     */
    public EventListenerException(String arg0) {
        super(arg0);

    }

    /**
     * @param arg0
     */
    public EventListenerException(Throwable arg0) {
        super(arg0);

    }

    /**
     * @param arg0
     * @param arg1
     */
    public EventListenerException(String arg0, Throwable arg1) {
        super(arg0, arg1);

    }

}
