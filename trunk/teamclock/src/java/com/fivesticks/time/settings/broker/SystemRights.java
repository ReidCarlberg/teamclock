/*
 * Created on Jan 26, 2005 by REID
 */
package com.fivesticks.time.settings.broker;

import java.io.Serializable;

/**
 * @author REID
 */
public interface SystemRights extends Serializable {

    /**
     * @return Returns the canHaveExternalUsers.
     */
    public boolean isCanHaveExternalUsers();

    /**
     * @return Returns the canUseAccountTransactions.
     */
    public boolean isCanUseAccountTransactions();

    /**
     * @return Returns the canUseBetaFeatures.
     */
    public boolean isCanUseBetaFeatures();

    /**
     * @return Returns the freeSystem.
     */
    public boolean isFreeSystem();

}