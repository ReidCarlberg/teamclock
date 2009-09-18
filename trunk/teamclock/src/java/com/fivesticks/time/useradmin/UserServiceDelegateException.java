/*
 * Created on Sep 10, 2004 by Reid
 */
package com.fivesticks.time.useradmin;

/**
 * @author Reid
 */
public class UserServiceDelegateException extends Exception {

    /**
     *  
     */
    public UserServiceDelegateException() {
        super();

    }

    /**
     * @param message
     */
    public UserServiceDelegateException(String message) {
        super(message);

    }

    /**
     * @param message
     * @param cause
     */
    public UserServiceDelegateException(String message, Throwable cause) {
        super(message, cause);

    }

    /**
     * @param cause
     */
    public UserServiceDelegateException(Throwable cause) {
        super(cause);

    }
}