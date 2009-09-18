/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.systemowner;

import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecorator;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorAware;

/**
 * @author Reid
 *
 */
public class SystemOwnerAwareSupport implements SystemOwnerAware, OwnerKeyValidatorAndDecoratorAware {

    private SystemOwner systemOwner;
    
    private OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator;

    /**
     * @return Returns the ownerKeyValidatorAndDecorator.
     */
    public OwnerKeyValidatorAndDecorator getOwnerKeyValidatorAndDecorator() {
        if (ownerKeyValidatorAndDecorator == null)
            throw new RuntimeException("OwnerKeyValidadorAndDecorator requested but it's null");
        return ownerKeyValidatorAndDecorator;
    }
    /**
     * @param ownerKeyValidatorAndDecorator The ownerKeyValidatorAndDecorator to set.
     */
    public void setOwnerKeyValidatorAndDecorator(
            OwnerKeyValidatorAndDecorator ownerKeyValidatorAndDecorator) {
        this.ownerKeyValidatorAndDecorator = ownerKeyValidatorAndDecorator;
    }
    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        if (systemOwner == null)
            throw new RuntimeException("SystemOwner requested but it's null");
        return systemOwner;
    }
    /**
     * @param systemOwner The systemOwner to set.
     */
    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }
}
