/*
 * Created on Nov 22, 2004 by Reid
 */
package com.fivesticks.time.accountactivity;

/**
 * @author Reid
 */
public class AccountActivityServiceDelegateException extends Exception {

    /**
     *  
     */
    public AccountActivityServiceDelegateException() {
        super();

    }

    /**
     * @param message
     */
    public AccountActivityServiceDelegateException(String message) {
        super(message);

    }

    /**
     * @param message
     * @param cause
     */
    public AccountActivityServiceDelegateException(String message,
            Throwable cause) {
        super(message, cause);

    }

    /**
     * @param cause
     */
    public AccountActivityServiceDelegateException(Throwable cause) {
        super(cause);

    }
}