/*
 * Created on Dec 16, 2004 by Reid
 */
package com.fivesticks.time.queuedmessages;

/**
 * @author Reid
 */
public class QueuedMessageServiceDelegateException extends Exception {

    /**
     *  
     */
    public QueuedMessageServiceDelegateException() {
        super();

    }

    /**
     * @param message
     */
    public QueuedMessageServiceDelegateException(String message) {
        super(message);

    }

    /**
     * @param cause
     */
    public QueuedMessageServiceDelegateException(Throwable cause) {
        super(cause);

    }

    /**
     * @param message
     * @param cause
     */
    public QueuedMessageServiceDelegateException(String message, Throwable cause) {
        super(message, cause);

    }

}