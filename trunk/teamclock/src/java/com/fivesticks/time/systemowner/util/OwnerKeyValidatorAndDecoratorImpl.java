/*
 * Created on Dec 19, 2004 by REID
 */
package com.fivesticks.time.systemowner.util;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

/**
 * @author REID
 */
public class OwnerKeyValidatorAndDecoratorImpl implements
        OwnerKeyValidatorAndDecorator {

    public void decorate(SystemOwnerKeyAware systemOwnerKeyAware,
            SystemOwner systemOwner)
            throws OwnerKeyValidatorAndDecoratorException {
        if (systemOwnerKeyAware.getOwnerKey() != null
                && !systemOwnerKeyAware.getOwnerKey().equals(
                        systemOwner.getKey())) {
            throw new OwnerKeyValidatorAndDecoratorException(
                    "trying to replace an existing owner key ("
                            + systemOwnerKeyAware.getOwnerKey()
                            + ") with a new one (" + systemOwner.getKey()
                            + ").");
        } else if (systemOwnerKeyAware.getOwnerKey() == null
                || systemOwnerKeyAware.getOwnerKey().trim().length() == 0) {
            systemOwnerKeyAware.setOwnerKey(systemOwner.getKey());
        }
    }

    public void validate(SystemOwnerKeyAware systemOwnerKeyAware,
            SystemOwner systemOwner)
            throws OwnerKeyValidatorAndDecoratorException {
        
        if (systemOwnerKeyAware == null) {
            //do nothing.
            return;
        }
            
        if (systemOwnerKeyAware.getOwnerKey() != null
                && !systemOwnerKeyAware.getOwnerKey().equals(
                        systemOwner.getKey())) {
            throw new OwnerKeyValidatorAndDecoratorException(
                    "owner key invalid.");
        }

    }
}