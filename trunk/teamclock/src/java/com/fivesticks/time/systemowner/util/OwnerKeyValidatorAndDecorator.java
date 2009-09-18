/*
 * Created on Dec 19, 2004 by REID
 */
package com.fivesticks.time.systemowner.util;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

/**
 * @author REID
 */
public interface OwnerKeyValidatorAndDecorator {

    public void validate(SystemOwnerKeyAware systemOwnerKeyAware,
            SystemOwner systemOwner)
            throws OwnerKeyValidatorAndDecoratorException;

    public void decorate(SystemOwnerKeyAware systemOwnerKeyAware,
            SystemOwner systemOwner)
            throws OwnerKeyValidatorAndDecoratorException;
}