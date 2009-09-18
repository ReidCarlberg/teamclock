/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.externaluser;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateException;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 */
public class ExternalUserServiceDelegateImpl extends AbstractServiceDelegate implements ExternalUserServiceDelegate {

//    private SystemOwner systemOwner;
//
//    private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;

//    private ExternalUserDAO customerSystemUserDAO;

    /*
     * for internal use...
     */
    private ExternalUser lastMatched;

    /**
     * @return Returns the customerSystemUserDAO.
     */
//    public ExternalUserDAO getExternalUserDAO() {
//        return customerSystemUserDAO;
//    }

    /**
     * @param customerSystemUserDAO
     *            The customerSystemUserDAO to set.
     */
//    public void setExternalUserDAO(ExternalUserDAO customerSystemUserDAO) {
//        this.customerSystemUserDAO = customerSystemUserDAO;
//    }

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

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.user.CustomerSystemUserServiceDelegate#associate(java.lang.String,
     *      java.lang.Long)
     */
    public void associate(String username, Long customerId)
            throws ExternalUserServiceDelegateException {
        if (isAssociated(username, customerId))
            return;

        /*
         * make sure it's reasonable to associate these two.
         */
        handleUserValidation(username);

        handleCustomerValidation(customerId);

        ExternalUser newAssoc = new ExternalUser();
        newAssoc.setCustomerId(customerId);
        newAssoc.setUsername(username);
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(newAssoc,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ExternalUserServiceDelegateException(e);
        }

            this.getDao().save(newAssoc);
    }

    /**
     * @throws ExternalUserServiceDelegateException
     *  
     */
    private void handleCustomerValidation(Long customerId)
            throws ExternalUserServiceDelegateException {
        Customer cust = null;
        try {
            cust = CustomerServiceDelegateFactory.factory.build(this.getSystemOwner())
                    .getFstxCustomer(customerId);
        } catch (CustomerServiceDelegateException e) {
            throw new ExternalUserServiceDelegateException(e);
        }

        /*
         * 2004-12-29 shouldn't ever get here. just in case.
         */
        if (cust == null) {
            throw new ExternalUserServiceDelegateException();
        }

    }

    /**
     *  
     */
    private void handleUserValidation(String username)
            throws ExternalUserServiceDelegateException {

        SystemUser test = null;

        User user;
        try {
            user = UserBDFactory.factory.build().getByUsername(username);
        } catch (UserBDException e) {
            throw new ExternalUserServiceDelegateException(e);
        }

        try {
            test = SystemUserServiceDelegateFactory.factory.build()
                    .getBySystemAndUser(this.getSystemOwner(), user);
        } catch (SystemUserServiceDelegateException e1) {
            e1.printStackTrace();
            throw new ExternalUserServiceDelegateException(e1);
        }

    }

    public boolean isAssociated(String username, Long customerId)
            throws ExternalUserServiceDelegateException {
        boolean ret = false;
        Collection byCustomer = this.getCustomers(username);
        for (Iterator iter = byCustomer.iterator(); iter.hasNext();) {
            ExternalUser element = (ExternalUser) iter.next();
            if (element.getCustomerId().equals(customerId)) {
                ret = true;
                this.setLastMatched(element);
                break;
            }

        }
        return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.user.CustomerSystemUserServiceDelegate#disassociate(java.lang.String,
     *      java.lang.Long)
     */
    public void disassociate(String username, Long customerId)
            throws ExternalUserServiceDelegateException {
        if (!isAssociated(username, customerId))
            return;

            this.getDao().delete(this.getLastMatched());

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.user.CustomerSystemUserServiceDelegate#getCustomers(java.lang.String)
     */
    public Collection getCustomers(String username)
            throws ExternalUserServiceDelegateException {

        ExternalUserFilter params = new ExternalUserFilter();
        params.setUsername(username);
        return handleFind(params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.user.CustomerSystemUserServiceDelegate#getUsers(java.lang.Long)
     */
    public Collection getUsers(Long customerId)
            throws ExternalUserServiceDelegateException {

        ExternalUserFilter params = new ExternalUserFilter();
        params.setCustomerId(customerId);
        return handleFind(params);
    }

    public Collection getUsers(SystemOwner systemOwner)
            throws ExternalUserServiceDelegateException {

        ExternalUserFilter params = new ExternalUserFilter();
        return handleFind(params);
    }

    private Collection handleFind(ExternalUserFilter params)
            throws ExternalUserServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(params,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ExternalUserServiceDelegateException(e);
        }
            return this.getDao().find(params);
    }

    /**
     * @return Returns the lastMatched.
     */
    public ExternalUser getLastMatched() {
        ExternalUser ret = lastMatched;
        lastMatched = null;

        return ret;
    }

    /**
     * @param lastMatched
     *            The lastMatched to set.
     */
    public void setLastMatched(ExternalUser lastMatched) {
        this.lastMatched = lastMatched;
    }

    public void delete(ExternalUser externalUser)
            throws ExternalUserServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(externalUser,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e1) {

            throw new ExternalUserServiceDelegateException(e1);
        }

            this.getDao().delete(externalUser);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.externaluser.ExternalUserServiceDelegate#isAssociated(com.fivesticks.time.customer.FstxCustomer,
     *      com.fstx.stdlib.authen.users.User)
     */
    public boolean isAssociated(Customer targetCustomer, User user)
            throws ExternalUserServiceDelegateException {

        return this.isAssociated(user.getUsername(), targetCustomer.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.externaluser.ExternalUserServiceDelegate#associate(java.lang.String,
     *      com.fivesticks.time.customer.FstxCustomer)
     */
    public void associate(User user, Customer customer)
            throws ExternalUserServiceDelegateException {
        associate(user.getUsername(), customer.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.externaluser.ExternalUserServiceDelegate#disassociate(java.lang.String,
     *      com.fivesticks.time.customer.FstxCustomer)
     */
    public void disassociate(User user, Customer customer)
            throws ExternalUserServiceDelegateException {
        disassociate(user.getUsername(), customer.getId());
    }
}