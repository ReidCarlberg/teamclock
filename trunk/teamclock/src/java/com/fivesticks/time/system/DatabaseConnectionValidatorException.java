/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

/**
 * @author Reid
 *  
 */
public class DatabaseConnectionValidatorException extends Exception {

    /**
     *  
     */
    public DatabaseConnectionValidatorException() {
        super();
    }

    /**
     * @param arg0
     */
    public DatabaseConnectionValidatorException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public DatabaseConnectionValidatorException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public DatabaseConnectionValidatorException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}