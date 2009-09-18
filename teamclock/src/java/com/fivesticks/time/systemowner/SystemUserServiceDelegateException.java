/*
 * Created on Aug 13, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

/**
 * @author Reid
 */
public class SystemUserServiceDelegateException extends Exception {

    /**
     *  
     */
    public SystemUserServiceDelegateException() {
        super();

    }

    /**
     * @param message
     */
    public SystemUserServiceDelegateException(String message) {
        super(message);

    }

    /**
     * @param message
     * @param cause
     */
    public SystemUserServiceDelegateException(String message, Throwable cause) {
        super(message, cause);

    }

    /**
     * @param cause
     */
    public SystemUserServiceDelegateException(Throwable cause) {
        super(cause);

    }
}