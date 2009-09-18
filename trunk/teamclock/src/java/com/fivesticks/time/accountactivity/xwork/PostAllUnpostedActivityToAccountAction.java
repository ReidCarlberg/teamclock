/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.accountactivity.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionException;
import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
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
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class PostAllUnpostedActivityToAccountAction extends ActionSupport
        implements SessionContextAware {

    private SessionContext sessionContext;

    private Long target;

    private Collection postList = new ArrayList();

    private ActivityBD activityBD;

    public String execute() throws Exception {

        activityBD = ActivityBDFactory.factory.build(this.getSessionContext());

        AccountActivityPoster accountActivityPoster = new AccountActivityPoster(this.getSessionContext());
        
        for (Iterator iter = this.getPostList().iterator(); iter.hasNext();) {
            PostUnpostedVO element = (PostUnpostedVO) iter.next();

            if (element.getId() != null) {
                Activity activity = activityBD.get(element.getId());
                if (activity != null && !element.isSkip()) {
                    activity.setChargeableElapsed(element
                            .getChargeableElapsed());
                    
                    accountActivityPoster.handlePost(activity);
                    
                    activityBD.save(activity);
                }
            }

        }

        return Action.SUCCESS;
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

    /**
     * @return Returns the postList.
     */
    public Collection getPostList() {
        if (postList.size() == 0) {
            for (int i = 0; i < 100; i++) {
                postList.add(new PostUnpostedVO());
            }
        }
        return postList;
    }

    /**
     * @param postList
     *            The postList to set.
     */
    public void setPostList(Collection postList) {
        this.postList = postList;
    }

}