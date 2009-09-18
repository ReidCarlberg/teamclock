/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.object.comments;

import java.util.Collection;

import com.fivesticks.time.common.IdReadable;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public interface ObjectCommentServiceDelegate {

    public Collection getComments(IdReadable idReadable)
            throws ObjectCommentServiceDelegateException;

    public Collection getComments(ObjectCommentFilter objectCommentFilter)
            throws ObjectCommentServiceDelegateException;

    public void delete(ObjectComment objectComment)
            throws ObjectCommentServiceDelegateException;

    public ObjectComment addComment(SystemOwnerKeyAware systemOwnerKeyAware,
            User user, String comment)
            throws ObjectCommentServiceDelegateException;

}