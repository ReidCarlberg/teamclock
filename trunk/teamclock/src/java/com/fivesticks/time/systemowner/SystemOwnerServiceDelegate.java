/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public interface SystemOwnerServiceDelegate extends SystemOwnerDAOAware {

    public static final String SPRING_BEAN_NAME_DELETE = "deleteSystemOwnerTransactionCommand";

    public Collection findAll() throws SystemOwnerServiceDelegateException;

    public Collection findAllById() throws SystemOwnerServiceDelegateException;

    public Collection search(SystemOwnerCriteriaParameters params) ;
    
    public SystemOwner save(SystemOwner target)
            throws SystemOwnerServiceDelegateException;

    public SystemOwner decorateWithKeyAndSave(SystemOwner target)
            throws SystemOwnerServiceDelegateException;

    public SystemOwner get(Long target)
            throws SystemOwnerServiceDelegateException;

    public SystemOwner get(String key)
            throws SystemOwnerServiceDelegateException;

    public SystemOwner getByEmail(String email)
            throws SystemOwnerServiceDelegateException;

    public SystemOwner get(User user)
            throws SystemOwnerServiceDelegateException;

    /*
     * when we delete a system owner, we should also delete all owned data.
     */
    public void delete(SystemOwner target, SessionContext sessionContext)
            throws SystemOwnerServiceDelegateException;

    public boolean isCurrentOwnerKey(String target);

}