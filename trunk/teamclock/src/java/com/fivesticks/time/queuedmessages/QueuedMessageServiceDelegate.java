/*
 * Created on Dec 15, 2004 by REID
 */
package com.fivesticks.time.queuedmessages;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwnerAware;

/**
 * @author REID
 */
public interface QueuedMessageServiceDelegate extends SystemOwnerAware {

    public QueuedMessage add(QueuedMessage queuedMessage)
            throws QueuedMessageServiceDelegateException;

    public QueuedMessage addSystemMessage(QueuedMessage queuedMessage)
            throws QueuedMessageServiceDelegateException;

    public void delete(QueuedMessage queuedMessage)
            throws QueuedMessageServiceDelegateException;

    public Collection findAllUnsent()
            throws QueuedMessageServiceDelegateException;

    public Collection search(QueuedMessageFilter queuedMessageFilter)
            throws QueuedMessageServiceDelegateException;

    public QueuedMessage get(Long id)
            throws QueuedMessageServiceDelegateException;

}