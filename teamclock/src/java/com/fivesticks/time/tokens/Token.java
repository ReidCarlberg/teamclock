/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.tokens;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.common.AbstractTimeObject;

public class Token extends AbstractTimeObject implements Serializable {

//    private Long id;
    private String type;
//    private String ownerKey;
    private String username;
    private Long custId;
    private Long projId;
    private String token;
    private boolean active;
    private Date issued;
    private Date revoked;
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
//    /**
//     * @param id The id to set.
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
    /**
     * @return Returns the issued.
     */
    public Date getIssued() {
        return issued;
    }
    /**
     * @param issued The issued to set.
     */
    public void setIssued(Date issued) {
        this.issued = issued;
    }
//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//    /**
//     * @param ownerKey The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }
    /**
     * @return Returns the revoked.
     */
    public Date getRevoked() {
        return revoked;
    }
    /**
     * @param revoked The revoked to set.
     */
    public void setRevoked(Date revoked) {
        this.revoked = revoked;
    }
    /**
     * @return Returns the token.
     */
    public String getToken() {
        return token;
    }
    /**
     * @param token The token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return Returns the user.
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param user The user to set.
     */
    public void setUsername(String user) {
        this.username = user;
    }
    /**
     * @return Returns the custId.
     */
    public Long getCustId() {
        return custId;
    }
    /**
     * @param custId The custId to set.
     */
    public void setCustId(Long custId) {
        this.custId = custId;
    }
    /**
     * @return Returns the projId.
     */
    public Long getProjId() {
        return projId;
    }
    /**
     * @param projId The projId to set.
     */
    public void setProjId(Long projId) {
        this.projId = projId;
    }
    
    
}
