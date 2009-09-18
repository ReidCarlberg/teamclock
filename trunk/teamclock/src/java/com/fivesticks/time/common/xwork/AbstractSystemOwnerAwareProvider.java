/*
 * Created on Jun 18, 2005
 *
 */
package com.fivesticks.time.common.xwork;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerAware;

/**
 * @author Reid
 *
 */
public abstract class AbstractSystemOwnerAwareProvider implements SystemOwnerAware {

    private SystemOwner systemOwner;
    
    
    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        
        if (systemOwner == null)
            throw new RuntimeException("system owner is null");
        
        return systemOwner;
    }
    /**
     * @param systemOwner The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }
}
