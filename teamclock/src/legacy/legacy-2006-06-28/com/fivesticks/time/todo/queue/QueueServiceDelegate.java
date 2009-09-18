/*
 * Created on Mar 4, 2005
 *
 * 
 */
package com.fivesticks.time.todo.queue;

import java.util.Collection;

/**
 * @author Nick
 * 
 *  
 */
public interface QueueServiceDelegate {
    /*
     * The queue is implemented as a todo with a user named Queue
     */public abstract Collection getAll() throws QueueServiceDelegateException;

    public static String QUEUE_USERNAME = "Queue";

}