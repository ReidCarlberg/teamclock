/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.setup.commission.SimpleCommissionCriteriaParameters;
import com.fivesticks.time.ebay.setup.commission.SimpleCommissionServiceDelegate;

/**
 * @author Reid
 *  
 */
public class ListAction extends SessionContextAwareSupport {

    private Collection commissions;

    public String execute() throws Exception {

        this.setCommissions(SimpleCommissionServiceDelegate.factory.build(
                this.getSystemOwner()).find(
                new SimpleCommissionCriteriaParameters()));
        
        return SUCCESS;
    }

    /**
     * @return Returns the commissions.
     */
    public Collection getCommissions() {
        return commissions;
    }

    /**
     * @param commissions
     *            The commissions to set.
     */
    public void setCommissions(Collection commissions) {
        this.commissions = commissions;
    }
}
