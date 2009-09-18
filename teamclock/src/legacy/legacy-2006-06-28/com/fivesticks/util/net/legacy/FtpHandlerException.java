/*
 * Created on Mar 30, 2005 by Reid
 */
package com.fivesticks.util.net.legacy;

/**
 * @author Reid
 */
public class FtpHandlerException extends Exception {

    /**
     * 
     */
    public FtpHandlerException() {
        super();

    }

    /**
     * @param message
     */
    public FtpHandlerException(String message) {
        super(message);

    }

    /**
     * @param cause
     */
    public FtpHandlerException(Throwable cause) {
        super(cause);

    }

    /**
     * @param message
     * @param cause
     */
    public FtpHandlerException(String message, Throwable cause) {
        super(message, cause);

    }

}
