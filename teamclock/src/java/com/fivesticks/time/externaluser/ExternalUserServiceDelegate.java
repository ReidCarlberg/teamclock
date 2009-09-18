/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.externaluser;

import java.util.Collection;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public interface ExternalUserServiceDelegate {

    /*
     * If already associated, does nothing.
     */
    public void associate(String username, Long customerId)
            throws ExternalUserServiceDelegateException;

    public void associate(User user, Customer customer)
            throws ExternalUserServiceDelegateException;

    /*
     * if it was never associated do nothing.
     */
    public void disassociate(String username, Long customerId)
            throws ExternalUserServiceDelegateException;

    public void disassociate(User user, Customer customer)
            throws ExternalUserServiceDelegateException;

    public Collection getCustomers(String username)
            throws ExternalUserServiceDelegateException;

    public Collection getUsers(Long customerId)
            throws ExternalUserServiceDelegateException;

    public Collection getUsers(SystemOwner systemOwner)
            throws ExternalUserServiceDelegateException;

    public void delete(ExternalUser externalUser)
            throws ExternalUserServiceDelegateException;

    /**
     * @param targetCustomer
     * @param user
     * @return
     */
    public boolean isAssociated(Customer targetCustomer, User user)
            throws ExternalUserServiceDelegateException;
}