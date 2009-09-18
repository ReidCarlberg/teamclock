/*
 * Created on Jan 29, 2005 by Reid
 */
package com.fivesticks.time.settings.broker;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class RightsBroker implements SystemRights {

    private SystemOwner owner;

    private MasterSettingsBroker getMaster() {
        return MasterSettingsBroker.singleton;
    }

    /**
     * @return Returns the canHaveExternalUsers.
     */
    public boolean isCanHaveExternalUsers() {
        return this.getMaster().getRights(getOwner()).isCanHaveExternalUsers();
    }

    /**
     * @return Returns the canUseAccountTransactions.
     */
    public boolean isCanUseAccountTransactions() {
        return this.getMaster().getRights(getOwner())
                .isCanUseAccountTransactions();
    }

    /**
     * @return Returns the canUseBetaFeatures.
     */
    public boolean isCanUseBetaFeatures() {
        return this.getMaster().getRights(getOwner()).isCanUseBetaFeatures();
    }

    /**
     * @return Returns the freeSystem.
     */
    public boolean isFreeSystem() {
        return this.getMaster().getRights(getOwner()).isFreeSystem();
    }

    /**
     * @return Returns the owner.
     */
    public SystemOwner getOwner() {
        return owner;
    }

    /**
     * @param owner
     *            The owner to set.
     */
    public void setOwner(SystemOwner owner) {
        this.owner = owner;
    }
}