/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo;

/**
 * @author REID
 *  
 */
public class ToDoServiceDelegateException extends Exception {

    /**
     *  
     */
    public ToDoServiceDelegateException() {
        super();
    }

    /**
     * @param message
     */
    public ToDoServiceDelegateException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ToDoServiceDelegateException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ToDoServiceDelegateException(String message, Throwable cause) {
        super(message, cause);
    }

}