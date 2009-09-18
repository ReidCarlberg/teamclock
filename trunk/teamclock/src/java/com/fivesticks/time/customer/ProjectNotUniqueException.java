/*
 * Created on Apr 29, 2004
 *
 */
package com.fivesticks.time.customer;

/**
 * @author Nick
 *  
 */
public class ProjectNotUniqueException extends Exception {

    /**
     *  
     */
    public ProjectNotUniqueException() {
        super();
    }

    /**
     * @param arg0
     */
    public ProjectNotUniqueException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ProjectNotUniqueException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public ProjectNotUniqueException(Throwable arg0) {
        super(arg0);
    }

}