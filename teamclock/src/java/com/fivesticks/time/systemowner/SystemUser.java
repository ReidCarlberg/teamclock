/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.io.Serializable;

/**
 * @author Reid
 */
public class SystemUser implements Serializable {

    private Long id;

    private String ownerKey;

    private String username;

    private String userType;

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Returns the ownerKey.
     */
    public String getOwnerKey() {
        return ownerKey;
    }

    /**
     * @param ownerKey
     *            The ownerKey to set.
     */
    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
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

    /**
     * @return Returns the role.
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param role
     *            The role to set.
     */
    public void setUserType(String role) {
        this.userType = role;
    }
}