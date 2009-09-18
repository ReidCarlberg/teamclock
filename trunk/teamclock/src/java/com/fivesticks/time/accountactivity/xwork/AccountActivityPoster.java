/*
 * Created on Jan 10, 2007
 *
 */
package com.fivesticks.time.accountactivity.xwork;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionException;
import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.common.AbstractSessionContextAwareServiceDelegate;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.util.GenericRounder;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class AccountActivityPoster extends AbstractSessionContextAwareServiceDelegate{

    ProjectServiceDelegate projectServiceDelegate;
    
    CustomerServiceDelegate customerServiceDelegate;
    
    AccountTransactionServiceDelegate accountTransactionServiceDelegate;
    
    public AccountActivityPoster(SessionContext sessionContext) {
        this.setSessionContext(sessionContext);
        
        projectServiceDelegate = ProjectServiceDelegateFactory.factory.build(sessionContext);
        
        customerServiceDelegate = CustomerServiceDelegateFactory.factory.build(sessionContext);
        
        accountTransactionServiceDelegate = CustomerAccountTransactionServiceDelegate.factory.buildTimeAccount(sessionContext.getSystemOwner());
    }
    
    public void handlePost(Activity targetActivity) throws Exception {

        /*
         * 2006-05-18 RSC if chargeable time is 0, let's not even get in here.
         */
        if (targetActivity.getChargeableElapsed() == null
                || (targetActivity.getChargeableElapsed() != null && targetActivity
                        .getChargeableElapsed().doubleValue() == 0.0)) {
            return;
        }
        
        Project project;
        try {
            project = this.getProjectServiceDelegate().getFstxProject(
                    targetActivity.getProjectId());
        } catch (ProjectBDException e) {
            throw new AccountTransactionException(e);
        }
        
        Customer customer;
        try {
            customer = this.getCustomerServiceDelegate().getFstxCustomer(
                    project.getCustId());
        } catch (CustomerServiceDelegateException e1) {
            throw new AccountTransactionException(e1);
        }

        double min = targetActivity.getChargeableElapsed().doubleValue() * 60;

        int minToPost = new GenericRounder().roundUp((int) min, this
                .getSessionContext().getSettings().getActivityRounderType(),
                false);

        StringBuffer comments = new StringBuffer();
        comments.append(SimpleDate.factory.buildMidnight(
                targetActivity.getDate()).getMmddyyyy());
        comments.append(" - ");
        comments.append(project.getShortName());
        comments.append(" - ");
        comments.append(targetActivity.getComments());
        comments.append(" - ");
        comments.append(targetActivity.getUsername());

        AccountTransaction accountTransaction = this.getAccountTransactionServiceDelegate()
                .decrease(customer, new Double(minToPost), comments.toString(),
                        this.getSessionContext().getUser().getUsername(),
                        targetActivity.getId());

        if (accountTransaction != null) {
            targetActivity.setAcctId(accountTransaction.getId());
        }

    }

    /**
     * @return the customerAccountTransactionServiceDelegate
     */
    public AccountTransactionServiceDelegate getAccountTransactionServiceDelegate() {
        return accountTransactionServiceDelegate;
    }

    /**
     * @param customerAccountTransactionServiceDelegate the customerAccountTransactionServiceDelegate to set
     */
    public void setAccountTransactionServiceDelegate(
            AccountTransactionServiceDelegate customerAccountTransactionServiceDelegate) {
        this.accountTransactionServiceDelegate = customerAccountTransactionServiceDelegate;
    }

    /**
     * @return the customerServiceDelegate
     */
    public CustomerServiceDelegate getCustomerServiceDelegate() {
        return customerServiceDelegate;
    }

    /**
     * @param customerServiceDelegate the customerServiceDelegate to set
     */
    public void setCustomerServiceDelegate(
            CustomerServiceDelegate customerServiceDelegate) {
        this.customerServiceDelegate = customerServiceDelegate;
    }

    /**
     * @return the projectServiceDelegate
     */
    public ProjectServiceDelegate getProjectServiceDelegate() {
        return projectServiceDelegate;
    }

    /**
     * @param projectServiceDelegate the projectServiceDelegate to set
     */
    public void setProjectServiceDelegate(
            ProjectServiceDelegate projectServiceDelegate) {
        this.projectServiceDelegate = projectServiceDelegate;
    }
}
