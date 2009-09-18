/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fstx.stdlib.authen.users.old;

/**
 * @author Reid
 */
public class GroupMembershipServiceDelegateException extends Exception {

    /**
     *  
     */
    public GroupMembershipServiceDelegateException() {
        super();

    }

    /**
     * @param message
     */
    public GroupMembershipServiceDelegateException(String message) {
        super(message);

    }

    /**
     * @param cause
     */
    public GroupMembershipServiceDelegateException(Throwable cause) {
        super(cause);

    }

    /**
     * @param message
     * @param cause
     */
    public GroupMembershipServiceDelegateException(String message,
            Throwable cause) {
        super(message, cause);

    }

}