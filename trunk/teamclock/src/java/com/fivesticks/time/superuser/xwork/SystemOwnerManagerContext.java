/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import java.io.Serializable;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class SystemOwnerManagerContext implements Serializable {

    private SystemOwner activeSystemOwner;

    /**
     * @return Returns the activeSystemOwner.
     */
    public SystemOwner getActiveSystemOwner() {
        return activeSystemOwner;
    }

    /**
     * @param activeSystemOwner
     *            The activeSystemOwner to set.
     */
    public void setActiveSystemOwner(SystemOwner activeSystemOwner) {
        this.activeSystemOwner = activeSystemOwner;
    }

    public String getName() {
        return this.getActiveSystemOwner().getName();
    }
}