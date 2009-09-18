/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.customer;

/**
 * @author Reid
 *  
 */
public class TaskBDException extends Exception {

    /**
     *  
     */
    public TaskBDException() {
        super();
    }

    /**
     * @param arg0
     */
    public TaskBDException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public TaskBDException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public TaskBDException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}