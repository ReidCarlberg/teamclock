/*
 * Created on Aug 21, 2005
 *
 */
package com.fivesticks.time.employee.team;

import java.io.Serializable;

import com.fivesticks.time.common.AbstractTimeObject;

public class TeamMember extends AbstractTimeObject implements Serializable {

//    private Long id;
//    
//    private String ownerKey;
    
    private Long teamId;
    
    private String username;
    
    private String role;
    
    private boolean active;

    /**
     * @return Returns the active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active The active to set.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

//    /**
//     * @return Returns the id.
//     */
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * @param id The id to set.
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownerKey The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }

    /**
     * @return Returns the role.
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return Returns the teamId.
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * @param teamId The teamId to set.
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
}
