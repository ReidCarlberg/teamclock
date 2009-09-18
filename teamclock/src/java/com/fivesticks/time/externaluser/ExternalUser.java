/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.externaluser;

import java.io.Serializable;

import com.fivesticks.time.common.AbstractTimeObject;

/**
 * @author Reid
 */
public class ExternalUser extends AbstractTimeObject implements Serializable {



    private String username;

    private Long customerId;

    /**
     * @return Returns the customerId.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     *            The customerId to set.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }



    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}