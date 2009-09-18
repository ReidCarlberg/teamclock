/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;

/**
 * @author Reid
 */
public class ListAction extends SessionContextAwareSupport implements
        ListContextAware {

    private ListContext listContext;
    
    private Collection lookups;
    
    private String target;
    
    public String execute() throws Exception {
    
        this.clearSuccessOverride();
        
        if (this.getTarget() != null) {
            LookupType t = LookupType.getByShortName(this.getTarget());
            if (t != null) {
                this.getListContext().setTargetType(t);
            }
        }
        
        if (this.getListContext().getTargetType() == null) {
            this.getListContext().setTargetType(LookupType.CUSTOMER_STATUS);
        } 
        
        LookupServiceDelegate sd = LookupServiceDelegateFactory.factory.build(this.getSystemOwner());
        
        this.setLookups(sd.getAll(this.getListContext().getTargetType()));
        
        return this.getSuccess();
    }
    /**
     * @return Returns the listContext.
     */
    public ListContext getListContext() {
        return listContext;
    }
    /**
     * @param listContext The listContext to set.
     */
    public void setListContext(ListContext listContext) {
        this.listContext = listContext;
    }
    /**
     * @return Returns the lookups.
     */
    public Collection getLookups() {
        return lookups;
    }
    /**
     * @param lookups The lookups to set.
     */
    public void setLookups(Collection lookups) {
        this.lookups = lookups;
    }
    
    public Collection getLookupTypes() {
        return LookupType.getAll();
    }    
    /**
     * @return Returns the target.
     */
    public String getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(String target) {
        this.target = target;
    }
}
