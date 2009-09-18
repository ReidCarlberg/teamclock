/*
 * Created on Mar 26, 2005 by REID
 */
package com.fivesticks.time.ebay.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.ItemStatusType;

/**
 * @author REID
 */
public class EbayHomeAction extends SessionContextAwareSupport {

    private Collection status = ItemStatusType.getAll();
    
    public String execute() throws Exception {
        
        this.getSessionContext().setSuccessOverride(null);
        
        return SUCCESS;
    }
    
    public Collection getStatus() {
        return status;
    }
    public void setStatus(Collection status) {
        this.status = status;
    }
}
