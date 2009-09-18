/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.calendar.TcCalendar;
import com.fivesticks.time.common.AbstractSessionContextAwareServiceDelegate;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.contactv2.CustomerContactV2Map;
import com.fivesticks.time.customer.contactxx.Contact;
import com.fivesticks.time.customer.util.CustomerSettingType;
import com.fivesticks.time.externaluser.ExternalUser;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegate;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateException;
import com.fivesticks.time.externaluser.ExternalUserServiceDelegateFactory;
import com.fivesticks.time.object.comments.ObjectComment;
import com.fivesticks.time.object.metrics.ObjectMetric;
import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegate;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegate;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.todo.ToDo;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Nick
 * 
 */
public class CustomerServiceDelegateImpl extends
        AbstractSessionContextAwareServiceDelegate implements
        CustomerServiceDelegate {

    // private SystemOwner systemOwner;

    /*
     * 2004? Someplace in there.
     * 
     * The "Unassigned Customer" is a generic customer to assigne preesisting
     * projects to. Since the old project system had no idea of customers we
     * need a customer to link the converted records to.
     * 
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxCustomerBD#getUnassignedCustomer()
     */
    public Customer getUnassignedCustomer()
            throws CustomerServiceDelegateException {

        CustomerFilterVO filter = new CustomerFilterVO();
        filter.setName("Unassigned");
        Collection list = null;

        list = this.getDao().find(filter);

        if (list.size() == 0) {
            return createUnassignedCustomer();
        }

        Customer ret = (Customer) list.toArray()[0];
        validate(ret);
        return (ret);

    }

    /**
     * @return
     */
    private Customer createUnassignedCustomer()
            throws CustomerServiceDelegateException {
        Customer newCustomer = new Customer();
        
        newCustomer.setName("Unassigned.");
        

        if (newCustomer.getId() == null)
            decorate(newCustomer);
        else
            validate(newCustomer);

        this.save(newCustomer);
        return newCustomer;
    }

    /**
     * @param newCustomer
     */
    public Customer save(Customer newCustomer)
            throws CustomerServiceDelegateException {

        if (newCustomer.getId() == null)
            decorate(newCustomer);
        else
            validate(newCustomer);

        this.getDao().save(newCustomer);

        return newCustomer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxCustomerBD#getAll()
     */
    public Collection getAll() throws CustomerServiceDelegateException {

        CustomerFilterVO filter = new CustomerFilterVO();
        filter.setOwnerKey(this.getSystemOwner().getKey());

        Collection allCustomers = null;

        allCustomers = this.getDao().find(filter);

        return allCustomers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxCustomerBD#getFstxCustomer(java.lang.Long)
     */
    public Customer getFstxCustomer(Long id)
            throws CustomerServiceDelegateException {

        Customer ret;
        ret = (Customer) this.getDao().get(id);

        validate(ret);

        return ret;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxCustomerBD#searchByFilter(com.fstx.time.customer.FstxCustomerFilterVO)
     */
    public Collection searchByFilter(CustomerFilterVO filterVO)
            throws CustomerServiceDelegateException {

        // filter needs to be decorated
        filterVO.setOwnerKey(this.getSystemOwner().getKey());

        return this.getDao().find(filterVO);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fstx.time.customer.FstxCustomerBD#delete(com.fstx.time.customer.FstxCustomer)
     */
    public void delete(final Customer target)
            throws CustomerServiceDelegateException {

        /*
         * need to validate
         */

        validate(target);

        /*
         * see what we have for this customer.
         */

        ProjectServiceDelegate projectSD = ProjectServiceDelegateFactory.factory
                .build(this.getSystemOwner());
        Collection projects;
        try {
            projects = projectSD.getAllForCustomer(target);
        } catch (ProjectBDException e) {
            throw new CustomerServiceDelegateException(e);
        }

        final StringBuffer pids = new StringBuffer();
        for (Iterator iter = projects.iterator(); iter.hasNext();) {
            Project element = (Project) iter.next();
            if (pids.length() > 0) {
                pids.append(", ");
            }
            pids.append(element.getId());
        }

        /*
         * external users
         */
        final Collection externalUsers;
        final ExternalUserServiceDelegate esd = ExternalUserServiceDelegateFactory.factory
                .build(this.getSystemOwner());
        try {
            externalUsers = esd.getUsers(this.getSystemOwner());
        } catch (ExternalUserServiceDelegateException e1) {
            throw new CustomerServiceDelegateException(e1);
        }
        


        try {
            TransactionTemplate transactionTemplate = new TransactionTemplate(
                    this.getTransactionManager());

            transactionTemplate
                    .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                public void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    SystemUserServiceDelegate susd = SystemUserServiceDelegateFactory.factory
                            .build();

                    // get a list of projects

                    GenericDAO genericDAO = GenericDAOFactory.factory.build();

                    try {
                        // related external users
                        /*
                         * 2006-07-07 if we enable people to have multiple
                         * accounts we'll need to adjust this.
                         */
                        for (Iterator iter = externalUsers.iterator(); iter
                                .hasNext();) {
                            ExternalUser element = (ExternalUser) iter.next();

                            if (element.getCustomerId().equals(target.getId())
                                    && susd.list(element.getUsername()).size() == 1) {
                                genericDAO.execute("DELETE "
                                        + User.class.getName()
                                        + " where username ='"
                                        + element.getUsername() + "'");

                                genericDAO.execute("DELETE "
                                        + SystemUser.class.getName()
                                        + " where username ='"
                                        + element.getUsername() + "'");
                            }
                        }

                        genericDAO.execute("DELETE "
                                + ExternalUser.class.getName()
                                + " where customerId = " + target.getId());

                        // contacts - v1 
                        //2006-09-02 leaving in for now so we can still do the convertion and cleanups
                        genericDAO.execute("DELETE " + Contact.class.getName()
                                + " where custId = " + target.getId());
                        
                        // contacts - v2
                        
                        /*
                         * this does not delete the contacts
                         */
                        genericDAO.execute("DELETE " + CustomerContactV2Map.class.getName() 
                                + " where customerId = " + target.getId());
                       

                        // customer comments
                        genericDAO.execute("DELETE "
                                + ObjectComment.class.getName()
                                + " where objectType ='"
                                + Customer.class.getName() + "' "
                                + "and objectId = " + target.getId());

                        // account transactions
                        genericDAO.execute("DELETE "
                                + AccountTransaction.class.getName()
                                + " where objectType ='"
                                + Customer.class.getName() + "' "
                                + "and objectId = " + target.getId());

                        if (pids.length() > 0) {
                            // calendar items
                            genericDAO.execute("DELETE "
                                    + TcCalendar.class.getName()
                                    + " where projectId in (" + pids.toString()
                                    + ")");

                            // todo items
                            genericDAO.execute("DELETE " + ToDo.class.getName()
                                    + " where projectId in (" + pids.toString()
                                    + ")");

                            // activity items
                            genericDAO.execute("DELETE "
                                    + Activity.class.getName()
                                    + " where projectId in (" + pids.toString()
                                    + ")");

                            genericDAO.execute("DELETE "
                                    + Project.class.getName()
                                    + " where id in (" + pids.toString() + ")");

                        }

                        // and finally delete the customers
                        genericDAO.execute("DELETE "
                                + Customer.class.getName() + " where id = "
                                + target.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("Transaction failed."
                                + e.getMessage(), e);
                    }
                }

            });

        } catch (RuntimeException e) {
            throw new CustomerServiceDelegateException(e);
        }

        // this.getDao().delete(target);

    }

    private void validate(Customer fstxCustomer)
            throws CustomerServiceDelegateException {
        if (!fstxCustomer.getOwnerKey().equals(this.getSystemOwner().getKey())) {
            throw new CustomerServiceDelegateException(
                    "Couldn't validate that the customer in question belongs to the current SystemOwner");
        }
    }

    private void decorate(Customer fstxCustomer) {
        if (this.getSystemOwner() == null) {
            throw new RuntimeException("No system owner to decorate with.");
        }
        fstxCustomer.setOwnerKey(this.getSystemOwner().getKey());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.FstxCustomerBD#getCustomerBySetting(com.fivesticks.time.customer.CustomerSettingType,
     *      java.lang.String)
     */
    public Collection getCustomerBySetting(CustomerSettingType setting,
            String value) throws CustomerServiceDelegateException {

        Collection matchingObjects;
        try {
            matchingObjects = ObjectMetricServiceDelegateFactory.factory.build(
                    this.getSystemOwner()).getMatchingObjectsByMetricValue(
                    Customer.class,
                    CustomerSettingType.IS_AUCTION_CUSTOMER, "true");
        } catch (ObjectMetricServiceDelegateException e) {
            throw new CustomerServiceDelegateException(e);
        }
        CustomerServiceDelegate bd = CustomerServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        Collection ret = new ArrayList();

        for (Iterator iter = matchingObjects.iterator(); iter.hasNext();) {
            ObjectMetric element = (ObjectMetric) iter.next();

            Customer c = bd.getFstxCustomer(element.getObjectId());
            ret.add(c);

        }
        return ret;
    }

    public Collection getCustomerBySetting(CustomerFilterVO filter,
            CustomerSettingType setting, String value)
            throws CustomerServiceDelegateException {

        /*
         * RSC 2005-08-13
         * 
         * get a list of matching customers and then filter those to see who's
         * got the right setting value.
         */

        Collection ret = new ArrayList();

        Collection customer = this.searchByFilter(filter);

        ObjectMetricServiceDelegate sd = ObjectMetricServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        for (Iterator iter = customer.iterator(); iter.hasNext();) {
            Customer element = (Customer) iter.next();

            try {
                if (sd.getMetricValue(element, setting).equalsIgnoreCase(value)) {
                    ret.add(element);
                }
            } catch (ObjectMetricServiceDelegateException e) {
                throw new CustomerServiceDelegateException(e);
            } catch (ObjectMetricNotFoundException e) {
                // do nothing
            }

        }

        return ret;
    }
}