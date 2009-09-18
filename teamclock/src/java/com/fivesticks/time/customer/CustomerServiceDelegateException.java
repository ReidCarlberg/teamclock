/*
 * Created on Apr 29, 2004
 *
 */
package com.fivesticks.time.customer;

/**
 * @author Nick
 *  
 */
public class CustomerServiceDelegateException extends Exception {

    /**
     *  
     */
    public CustomerServiceDelegateException() {
        super();
    }

    /**
     * @param arg0
     */
    public CustomerServiceDelegateException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public CustomerServiceDelegateException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public CustomerServiceDelegateException(Throwable arg0) {
        super(arg0);
    }

}