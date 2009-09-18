/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.accountactivity.xwork;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionException;
import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDException;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.common.util.GenericRounder;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fstx.stdlib.common.simpledate.SimpleDate;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class PostActivityToAccountAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private Long target;

    public String execute() throws Exception {

        if (this.getTarget() == null)
            return ERROR;

        Activity targetActivity = null;
        ActivityBD activityBD = ActivityBDFactory.factory.build(this.getSessionContext());
        try {
            targetActivity = activityBD.get(this.getTarget());
        } catch (ActivityBDException e) {
            return ERROR;
        }

        Project project;
        try {
            project = ProjectServiceDelegateFactory.factory.build(this.getSessionContext())
                    .getFstxProject(targetActivity.getProjectId());
        } catch (ProjectBDException e) {
            throw new AccountTransactionException(e);
        }
        
        Customer customer;
        try {
            customer = CustomerServiceDelegateFactory.factory.build(this.getSessionContext())
                    .getFstxCustomer(project.getCustId());
        } catch (CustomerServiceDelegateException e1) {
            throw new AccountTransactionException(e1);
        }

        /*
         * 2006-05-10 RSC changed from using elapsed to chargeableElapsed.
         */
        //double min = targetActivity.getElapsed().doubleValue() * 60;
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

        AccountTransaction txn = CustomerAccountTransactionServiceDelegate.factory.buildTimeAccount(
                this.getSessionContext().getSystemOwner()).decrease(customer,
                new Double(minToPost), comments.toString(),
                this.getSessionContext().getUser().getUsername(),
                targetActivity.getId());
        
        targetActivity.setAcctId(txn.getId());
        activityBD.save(targetActivity);

        return SUCCESS;
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }
}