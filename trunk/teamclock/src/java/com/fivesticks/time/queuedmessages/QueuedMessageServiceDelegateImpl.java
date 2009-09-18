/*
 * Created on Dec 19, 2004 by REID
 */
package com.fivesticks.time.queuedmessages;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecorator;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;
import com.fstx.stdlib.common.DAOException;

/**
 * @author REID
 */
public class QueuedMessageServiceDelegateImpl implements
        QueuedMessageServiceDelegate, QueuedMessageDAOAware, SystemOwnerAware,
        OwnerKeyValidatorAndDecoratorAware {

    private SystemOwner systemOwner;

    private QueuedMessageDAO queuedMessageDAO;

    private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;

    public void delete(QueuedMessage queuedMessage)
            throws QueuedMessageServiceDelegateException {
        if (queuedMessage.getId() == null)
            throw new QueuedMessageServiceDelegateException("not saved yet.");

        try {
            this.getOwnerKeyValidatorAndDecorator().validate(queuedMessage,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new QueuedMessageServiceDelegateException(e);
        }

        try {
            this.getQueuedMessageDAO().delete(queuedMessage);
        } catch (DAOException e1) {
            throw new QueuedMessageServiceDelegateException(e1);
        }
    }

    public QueuedMessage add(QueuedMessage queuedMessage)
            throws QueuedMessageServiceDelegateException {

        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(queuedMessage,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new QueuedMessageServiceDelegateException(e);
        }

        try {
            return (QueuedMessage) this.getQueuedMessageDAO().save(
                    queuedMessage);
        } catch (DAOException e1) {
            throw new QueuedMessageServiceDelegateException(e1);
        }

    }

    public QueuedMessage addSystemMessage(QueuedMessage queuedMessage)
            throws QueuedMessageServiceDelegateException {

        //        try {
        //            this.getOwnerKeyValidatorAndDecorator().decorate(queuedMessage,
        // this.getSystemOwner());
        //        } catch (OwnerKeyValidatorAndDecoratorException e) {
        //            throw new QueuedMessageServiceDelegateException(e);
        //        }

        try {
            return (QueuedMessage) this.getQueuedMessageDAO().save(
                    queuedMessage);
        } catch (DAOException e1) {
            throw new QueuedMessageServiceDelegateException(e1);
        }

    }

    public QueuedMessage get(Long id)
            throws QueuedMessageServiceDelegateException {
        QueuedMessage ret = null;

        try {
            ret = this.getQueuedMessageDAO().get(id);
        } catch (DAOException e) {
            throw new QueuedMessageServiceDelegateException(e);
        }

        try {
            this.getOwnerKeyValidatorAndDecorator().validate(ret,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e1) {
            throw new QueuedMessageServiceDelegateException(e1);
        }

        return ret;
    }

    public Collection search(QueuedMessageFilter queuedMessageFilter)
            throws QueuedMessageServiceDelegateException {

        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(
                    queuedMessageFilter, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new QueuedMessageServiceDelegateException(e);
        }

        try {
            return this.getQueuedMessageDAO().find(queuedMessageFilter);
        } catch (DAOException e1) {
            throw new QueuedMessageServiceDelegateException(e1);
        }
    }

    /**
     * @return Returns the queuedMessageDAO.
     */
    public QueuedMessageDAO getQueuedMessageDAO() {
        return queuedMessageDAO;
    }

    /**
     * @param queuedMessageDAO
     *            The queuedMessageDAO to set.
     */
    public void setQueuedMessageDAO(QueuedMessageDAO queuedMessageDAO) {
        this.queuedMessageDAO = queuedMessageDAO;
    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @param systemOwner
     *            The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    /**
     * @return Returns the ownerKeyValidatorAndDecorator.
     */
    public OwnerKeyValidatorAndDecorator getOwnerKeyValidatorAndDecorator() {
        return ownerKeyValidatorAndDecorator;
    }

    /**
     * @param ownerKeyValidatorAndDecorator
     *            The ownerKeyValidatorAndDecorator to set.
     */
    public void setOwnerKeyValidatorAndDecorator(
            OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator) {
        this.ownerKeyValidatorAndDecorator = ownerKeyValidatorAndDecorator;
    }

    public Collection findAllUnsent() throws QueuedMessageServiceDelegateException {
        
        QueuedMessageFilter filter = new QueuedMessageFilter();
        filter.setBooleanSent(Boolean.FALSE);
        
        return this.search(filter);
    }
}