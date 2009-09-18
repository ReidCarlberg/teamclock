/*
 * Created on Jan 16, 2005 by REID
 */
package com.fivesticks.time.system.messages;

/**
 * @author REID
 */
public class SystemMessageException extends Exception {

    /**
     *  
     */
    public SystemMessageException() {
        super();

    }

    /**
     * @param arg0
     */
    public SystemMessageException(String arg0) {
        super(arg0);

    }

    /**
     * @param arg0
     */
    public SystemMessageException(Throwable arg0) {
        super(arg0);

    }

    /**
     * @param arg0
     * @param arg1
     */
    public SystemMessageException(String arg0, Throwable arg1) {
        super(arg0, arg1);

    }

}