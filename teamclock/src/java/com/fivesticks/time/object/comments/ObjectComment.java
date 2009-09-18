/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.object.comments;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.common.AbstractObjectTypeAndIdAwareTimeObject;

/**
 * @author Reid
 */
public class ObjectComment extends AbstractObjectTypeAndIdAwareTimeObject implements Serializable {

//    private Long id;
//
//    private String ownerKey;

//    private String objectType;

//    private Long objectId;

    private String comment;

    private String username;

    private Date timestamp;

    /**
     * @return Returns the comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            The comment to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

//    /**
//     * @return Returns the id.
//     */
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * @param id
//     *            The id to set.
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//2006-07-06
//    /**
//     * @return Returns the objectId.
//     */
//    public Long getObjectId() {
//        return objectId;
//    }
//
//    /**
//     * @param objectId
//     *            The objectId to set.
//     */
//    public void setObjectId(Long objectId) {
//        this.objectId = objectId;
//    }
//
//    /**
//     * @return Returns the objectType.
//     */
//    public String getObjectType() {
//        return objectType;
//    }
//
//    /**
//     * @param objectType
//     *            The objectType to set.
//     */
//    public void setObjectType(String objectType) {
//        this.objectType = objectType;
//    }

//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownerKey
//     *            The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }

    /**
     * @return Returns the timestamp.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            The timestamp to set.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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