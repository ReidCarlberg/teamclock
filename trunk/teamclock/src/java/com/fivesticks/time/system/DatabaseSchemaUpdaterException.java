/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

/**
 * @author Reid
 *  
 */
public class DatabaseSchemaUpdaterException extends Exception {

    /**
     *  
     */
    public DatabaseSchemaUpdaterException() {
        super();
    }

    /**
     * @param arg0
     */
    public DatabaseSchemaUpdaterException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public DatabaseSchemaUpdaterException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public DatabaseSchemaUpdaterException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}