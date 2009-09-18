/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account;

/**
 * @author Reid
 */
public class AccountTransactionException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 2098541838030785580L;

    /**
     *  
     */
    public AccountTransactionException() {
        super();

    }

    /**
     * @param message
     */
    public AccountTransactionException(String message) {
        super(message);

    }

    /**
     * @param cause
     */
    public AccountTransactionException(Throwable cause) {
        super(cause);

    }

    /**
     * @param message
     * @param cause
     */
    public AccountTransactionException(String message, Throwable cause) {
        super(message, cause);

    }

}