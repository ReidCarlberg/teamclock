/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.contactv2.xwork;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.lookups.LookupType;

/**
 * @author Reid
 */
public class ContactV2ModifyContext {

    private ContactV2 target;
    
    private LookupType lookupType;

    /**
     * @return Returns the target.
     */
    public ContactV2 getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(ContactV2 target) {
        this.target = target;
    }
    /**
     * @return the lookupType
     */
    public LookupType getLookupType() {
        return lookupType;
    }
    /**
     * @param lookupType the lookupType to set
     */
    public void setLookupType(LookupType lookupType) {
        this.lookupType = lookupType;
    }
}
