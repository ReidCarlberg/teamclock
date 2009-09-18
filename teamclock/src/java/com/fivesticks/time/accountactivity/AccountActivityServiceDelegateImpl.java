/*
 * Created on Nov 22, 2004 by Reid
 */
package com.fivesticks.time.accountactivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fivesticks.time.account.AccountTransactionCriteriaParameters;
import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDException;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.activity.ActivityDAO;
import com.fivesticks.time.activity.ActivityDAOAware;
import com.fivesticks.time.activity.ActivityFilterVO;
import com.fivesticks.time.common.AbstractSessionContextAwareServiceDelegate;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionType;
import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class AccountActivityServiceDelegateImpl extends AbstractSessionContextAwareServiceDelegate implements SystemOwnerAware,
        AccountActivityServiceDelegate, ActivityDAOAware {

//    private SystemOwner systemOwner;

    private ActivityDAO fstxActivityDAO;

//    private AccountTransactionDAO accountTransactionDAO;


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
     * @see com.fivesticks.time.activity.ActivityAccountServiceDelegate#findUnpostedActivity(com.fstx.stdlib.common.simpledate.SimpleDate)
     */
    public Collection findUnpostedActivity(SimpleDate fromDate)
            throws AccountActivityServiceDelegateException {

        Map projects = new HashMap();

        ActivityBD activityBD = ActivityBDFactory.factory.build(this
                .getSessionContext());

        ProjectServiceDelegate projectBD = ProjectServiceDelegateFactory.factory.build(this
                .getSystemOwner());

        ActivityFilterVO filter = new ActivityFilterVO();

        filter.setStart(fromDate.getDate());
        /*
         * rsc 2006-05-10 changed to the chargeable elapsed
         */
//        filter.setMinimumElapsed(new Double(0.01));
        filter.setMinimumChargeableElapsed(new Double(0.01));
        filter.setOwnerKey(this.getSystemOwner().getKey());

        Collection all;

        try {
            all = activityBD.searchByFilter(filter);
        } catch (ActivityBDException e) {

            throw new AccountActivityServiceDelegateException(e);
        }

        Collection unmatched = new ArrayList();

        AccountTransactionCriteriaParameters accountFilter = new AccountTransactionCriteriaParameters();

        for (Iterator iter = all.iterator(); iter.hasNext();) {
            Activity element = (Activity) iter.next();

            /*
             * just using this to cache the projects
             */
            Project current = null;
            if (projects.containsKey(element.getProjectId())) {
                current = (Project) projects.get(element.getProjectId());
            } else {
                try {
                    current = projectBD.getFstxProject(element.getProjectId());
                } catch (ProjectBDException e2) {
                    throw new AccountActivityServiceDelegateException(e2);
                }
                projects.put(element.getProjectId(), current);
            }

            if (current.isPostable() != null
                    && current.isPostable().booleanValue()) {
                accountFilter.setOwnerKey(this.getSystemOwner().getKey());
                accountFilter.setActivityId(element.getId());
                accountFilter.setObjectType(Customer.class.getName());
                accountFilter.setObjectTransactionType(CustomerAccountTransactionType.TIME_ACCOUNT.getName());

                Collection temp;
               
                    temp = this.getDao().find(accountFilter);
                if (temp.size() == 0) {
                    unmatched.add(element);
                }
            }
        }

        return unmatched;
    }

    /**
     * @return Returns the fstxActivityDAO.
     */
    public ActivityDAO getFstxActivityDAO() {
        return fstxActivityDAO;
    }

    /**
     * @param fstxActivityDAO
     *            The fstxActivityDAO to set.
     */
    public void setFstxActivityDAO(ActivityDAO fstxActivityDAO) {
        this.fstxActivityDAO = fstxActivityDAO;
    }

//    /**
//     * @return Returns the accountTransactionDAO.
//     */
//    public AccountTransactionDAO getAccountTransactionDAO() {
//        return accountTransactionDAO;
//    }
//
//    /**
//     * @param accountTransactionDAO
//     *            The accountTransactionDAO to set.
//     */
//    public void setAccountTransactionDAO(
//            AccountTransactionDAO accountTransactionDAO) {
//        this.accountTransactionDAO = accountTransactionDAO;
//    }
}