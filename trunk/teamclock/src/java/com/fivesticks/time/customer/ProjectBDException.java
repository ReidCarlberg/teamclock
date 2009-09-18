/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

/**
 * @author Nick
 *  
 */
public class ProjectBDException extends Exception {

    /**
     *  
     */
    public ProjectBDException() {
        super();
    }

    /**
     * @param arg0
     */
    public ProjectBDException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ProjectBDException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public ProjectBDException(Throwable arg0) {
        super(arg0);
    }

}