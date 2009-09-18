/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.externaluser;

/**
 * @author Reid
 */
public class ExternalUserServiceDelegateException extends Exception {

    /**
     *  
     */
    public ExternalUserServiceDelegateException() {
        super();

    }

    /**
     * @param message
     */
    public ExternalUserServiceDelegateException(String message) {
        super(message);

    }

    /**
     * @param cause
     */
    public ExternalUserServiceDelegateException(Throwable cause) {
        super(cause);

    }

    /**
     * @param message
     * @param cause
     */
    public ExternalUserServiceDelegateException(String message, Throwable cause) {
        super(message, cause);

    }

}