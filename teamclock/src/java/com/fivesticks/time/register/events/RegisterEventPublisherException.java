/*
 * Created on Jan 15, 2005 by Reid
 */
package com.fivesticks.time.register.events;

/**
 * @author Reid
 */
public class RegisterEventPublisherException extends Exception {

    /**
     *  
     */
    public RegisterEventPublisherException() {
        super();

    }

    /**
     * @param message
     */
    public RegisterEventPublisherException(String message) {
        super(message);

    }

    /**
     * @param cause
     */
    public RegisterEventPublisherException(Throwable cause) {
        super(cause);

    }

    /**
     * @param message
     * @param cause
     */
    public RegisterEventPublisherException(String message, Throwable cause) {
        super(message, cause);

    }

}