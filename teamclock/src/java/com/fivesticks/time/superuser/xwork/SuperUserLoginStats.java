/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fstx.stdlib.authen.LoginHistoryBD;
import com.fstx.stdlib.authen.LoginHistoryBDFactory;
import com.fstx.stdlib.authen.LoginHistoryFilterParameters;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class SuperUserLoginStats extends SessionContextAwareSupport {

    private Collection byUser;
    
    private Collection byOwner;
    
    private LoginHistoryFilterParameters params = new LoginHistoryFilterParameters();

    public String execute() throws Exception {

        LoginHistoryBD bd = LoginHistoryBDFactory.factory.build();

        this.getSessionContext().setSuccessOverride(null);

        /*
         * reid
         * 
         * 2005-11-14 these times should be unresolved.
         */
        if (this.getParams().getDateFrom() == null) {
            SimpleDate start = SimpleDate.factory.buildMidnight();
            //start.advanceDay(-1);

            this.getParams().setDateFrom(start.getDate());
        }

        if (this.getParams().getDateTo() == null) {
            SimpleDate stop = SimpleDate.factory.buildMidnight();
            stop.advanceDay(1);
            this.getParams().setDateTo(stop.getDate());
        }
        
        this.setByUser(bd.searchStatsByUser(this.getParams()));

        this.setByOwner(bd.searchStatsByOwner(this.getParams()));

        return SUCCESS;
    }



    /**
     * @return Returns the byOwner.
     */
    public Collection getByOwner() {
        return byOwner;
    }



    /**
     * @param byOwner The byOwner to set.
     */
    public void setByOwner(Collection byOwner) {
        this.byOwner = byOwner;
    }



    /**
     * @return Returns the byUser.
     */
    public Collection getByUser() {
        return byUser;
    }



    /**
     * @param byUser The byUser to set.
     */
    public void setByUser(Collection byUser) {
        this.byUser = byUser;
    }



    /**
     * @return Returns the params.
     */
    public LoginHistoryFilterParameters getParams() {
        return params;
    }



    /**
     * @param params The params to set.
     */
    public void setParams(LoginHistoryFilterParameters params) {
        this.params = params;
    }
}