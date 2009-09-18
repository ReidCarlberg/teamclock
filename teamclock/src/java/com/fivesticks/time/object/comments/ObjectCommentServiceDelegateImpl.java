/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.object.comments;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.common.IdReadable;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public class ObjectCommentServiceDelegateImpl extends AbstractServiceDelegate
        implements ObjectCommentServiceDelegate {

//    private SystemOwner systemOwner;

//    private ObjectCommentDAO dao;

//    private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;

    public void delete(ObjectComment systemOwnerMetric)
            throws ObjectCommentServiceDelegateException {
//        try {
//            this.getOwnerKeyValidatorAndDecorator().validate(systemOwnerMetric,
//                    this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new ObjectCommentServiceDelegateException(e);
//        }

//        try {
        
        try {
            this.handleValidate(systemOwnerMetric);
        } catch (AbstractServiceDelegateException e) {
            throw new ObjectCommentServiceDelegateException(e);
        }
        
            this.getDao().delete(systemOwnerMetric);
//        } catch (ObjectCommentDAOException e1) {
//            throw new ObjectCommentServiceDelegateException(e1);
//        }
    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.fivesticks.time.systemowner.metric.SystemOwnerMetricDAOAware#setSystemOwnerMetricDAO(com.fivesticks.time.systemowner.metric.SystemOwnerMetricDAO)
//     */
//    public void setObjectCommentDAO(ObjectCommentDAO systemOwnerMetricDAO) {
//        this.dao = systemOwnerMetricDAO;
//    }
//
//    /**
//     * @return Returns the systemOwner.
//     */
//    public SystemOwner getSystemOwner() {
//        return systemOwner;
//    }
//
//    /**
//     * @param systemOwner
//     *            The systemOwner to set.
//     */
//    public void setSystemOwner(SystemOwner systemOwner) {
//        this.systemOwner = systemOwner;
//    }
//
//    /**
//     * @return Returns the systemOwnerMetricDAO.
//     */
//    public ObjectCommentDAO getDao() {
//        return dao;
//    }
//
//    /**
//     * @return Returns the ownerKeyValidatorAndDecorator.
//     */
//    public OwnerKeyValidatorAndDecorator getOwnerKeyValidatorAndDecorator() {
//        return ownerKeyValidatorAndDecorator;
//    }
//
//    /**
//     * @param ownerKeyValidatorAndDecorator
//     *            The ownerKeyValidatorAndDecorator to set.
//     */
//    public void setOwnerKeyValidatorAndDecorator(
//            OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator) {
//        this.ownerKeyValidatorAndDecorator = ownerKeyValidatorAndDecorator;
//    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.metrics.ObjectMetricServiceDelegate#getMetrics(com.fivesticks.time.common.IdReadable)
     */
    public Collection getComments(IdReadable idReadable)
            throws ObjectCommentServiceDelegateException {

        ObjectCommentFilter filter = new ObjectCommentFilter();
        filter.setObjectType(idReadable.getClass().getName());
        filter.setObjectId(idReadable.getId());

        try {
            this.handleDecorate(filter);
        } catch (AbstractServiceDelegateException e) {
            throw new ObjectCommentServiceDelegateException(e);
        }
//        try {
//            this.getOwnerKeyValidatorAndDecorator().decorate(filter,
//                    this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new ObjectCommentServiceDelegateException(e);
//        }

//        try {
            return this.getDao().find(filter);
//        } catch (ObjectCommentDAOException e1) {
//            throw new ObjectCommentServiceDelegateException(e1);
//        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.metrics.ObjectMetricServiceDelegate#getMetrics(com.fivesticks.time.common.IdReadable)
     */
    public Collection getComments(ObjectCommentFilter objectCommentFilter)
            throws ObjectCommentServiceDelegateException {

        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(
                    objectCommentFilter, this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ObjectCommentServiceDelegateException(e);
        }

//        try {
            return this.getDao().find(objectCommentFilter);
//        } catch (ObjectCommentDAOException e1) {
//            throw new ObjectCommentServiceDelegateException(e1);
//        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.object.comments.ObjectCommentServiceDelegate#addComment(com.fivesticks.time.common.IdReadable,
     *      com.fstx.stdlib.authen.users.User, java.lang.String)
     */
    public ObjectComment addComment(SystemOwnerKeyAware idReadable, User user,
            String comment) throws ObjectCommentServiceDelegateException {

        ObjectComment objectComment = new ObjectComment();

        objectComment.setObjectType(idReadable.getClass().getName());
        objectComment.setObjectId(idReadable.getId());
        objectComment.setTimestamp(new Date());
        objectComment.setUsername(user.getUsername());
        objectComment.setComment(comment);

        try {
            this.handleDecorate(objectComment);
        } catch (AbstractServiceDelegateException e) {
            throw new ObjectCommentServiceDelegateException(e);
        }
//        try {
//            this.getOwnerKeyValidatorAndDecorator().validate(idReadable,
//                    this.getSystemOwner());
//            this.getOwnerKeyValidatorAndDecorator().decorate(objectComment,
//                    this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new ObjectCommentServiceDelegateException(e);
//        }

//        try {
            this.getDao().save(objectComment);
//        } catch (ObjectCommentDAOException e1) {
//            throw new ObjectCommentServiceDelegateException(e1);
//        }

        return objectComment;

    }

}