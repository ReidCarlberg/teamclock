/*
 * Created on Nov 23, 2004 by Reid
 */
package com.fivesticks.time.accountactivity.xwork;

import java.util.Collection;

import com.fivesticks.time.accountactivity.AccountActivityServiceDelegateFactory;
import com.fivesticks.time.activity.ActivityWrapperArrayListBuilder;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class ListUnpostedAccountActivityAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private Collection unposted;
    
    

    public String execute() throws Exception {

        Collection rawUnposted = AccountActivityServiceDelegateFactory.factory.build(
                this.getSessionContext())
                .findUnpostedActivity(
                        this.getSessionContext().getSettings()
                                .getActivityPostedThrough());

        Collection display = new ActivityWrapperArrayListBuilder()
                .buildFxtxTimeWrapperArrayList(rawUnposted, this
                        .getSessionContext());

        this.setUnposted(display);

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
     * @return Returns the unposted.
     */
    public Collection getUnposted() {
        return unposted;
    }

    /**
     * @param unposted
     *            The unposted to set.
     */
    public void setUnposted(Collection unposted) {
        this.unposted = unposted;
    }
}