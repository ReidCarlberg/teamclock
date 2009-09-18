/*
 * Created on Dec 2, 2004
 *
 * 
 */
package com.fivesticks.time.superuser.xwork;

import java.io.Serializable;

/**
 * @author Nick
 * 
 *  
 */
public class SystemSettingsModifyParameters implements Serializable {

    private Long numberOfActiveUsers;

    private boolean freeSystem;

    private boolean canHaveExternalUsers;

    private boolean canUseAccountTransactions;

    private boolean canUseBetaFeatures;

    public Long getNumberOfActiveUsers() {
        return numberOfActiveUsers;
    }

    public void setNumberOfActiveUsers(Long numberOfActiveUsers) {
        this.numberOfActiveUsers = numberOfActiveUsers;
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
}