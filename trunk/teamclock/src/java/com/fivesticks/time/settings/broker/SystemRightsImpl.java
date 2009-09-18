/*
 * Created on Jan 26, 2005 by REID
 */
package com.fivesticks.time.settings.broker;

/**
 * @author REID
 */
public class SystemRightsImpl implements SystemRights {

    private boolean freeSystem;

    private boolean canUseAccountTransactions;

    private boolean canHaveExternalUsers;

    private boolean canUseBetaFeatures;

    /**
     * @return Returns the canHaveExternalUsers.
     */
    public boolean isCanHaveExternalUsers() {
        return canHaveExternalUsers;
    }

    /**
     * @param canHaveExternalUsers
     *            The canHaveExternalUsers to set.
     */
    public void setCanHaveExternalUsers(boolean canHaveExternalUsers) {
        this.canHaveExternalUsers = canHaveExternalUsers;
    }

    /**
     * @return Returns the canUseAccountTransactions.
     */
    public boolean isCanUseAccountTransactions() {
        return canUseAccountTransactions;
    }

    /**
     * @param canUseAccountTransactions
     *            The canUseAccountTransactions to set.
     */
    public void setCanUseAccountTransactions(boolean canUseAccountTransactions) {
        this.canUseAccountTransactions = canUseAccountTransactions;
    }

    /**
     * @return Returns the canUseBetaFeatures.
     */
    public boolean isCanUseBetaFeatures() {
        return canUseBetaFeatures;
    }

    /**
     * @param canUseBetaFeatures
     *            The canUseBetaFeatures to set.
     */
    public void setCanUseBetaFeatures(boolean canUseBetaFeatures) {
        this.canUseBetaFeatures = canUseBetaFeatures;
    }

    /**
     * @return Returns the freeSystem.
     */
    public boolean isFreeSystem() {
        return freeSystem;
    }

    /**
     * @param freeSystem
     *            The freeSystem to set.
     */
    public void setFreeSystem(boolean freeSystem) {
        this.freeSystem = freeSystem;
    }

}