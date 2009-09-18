/*
 * Created on Dec 15, 2004 by REID
 */
package com.fivesticks.time.queuedmessages;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.common.AbstractTimeObject;

/**
 * @author REID
 */
public class QueuedMessage extends AbstractTimeObject implements Serializable {

//    private Long id;
//
//    private String ownerKey;

    private Date sendTime;

    private String fromName;

    private String fromAddress;

    private String toName;

    private String toAddress;

    private String subject;

    private String message;

    private String modifiedByUsername;

    private boolean sent;

    /**
     * @return Returns the sent.
     */
    public boolean isSent() {
        return sent;
    }

    /**
     * @param sent
     *            The sent to set.
     */
    public void setSent(boolean sent) {
        this.sent = sent;
    }

    /**
     * @return Returns the fromAddress.
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * @param fromAddress
     *            The fromAddress to set.
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * @return Returns the fromName.
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * @param fromName
     *            The fromName to set.
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
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

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

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
     * @return Returns the sendTime.
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     *            The sendTime to set.
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            The subject to set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return Returns the toAddress.
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * @param toAddress
     *            The toAddress to set.
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * @return Returns the toName.
     */
    public String getToName() {
        return toName;
    }

    /**
     * @param toName
     *            The toName to set.
     */
    public void setToName(String toName) {
        this.toName = toName;
    }

    /**
     * @return Returns the modifiedByUser.
     */
    public String getModifiedByUsername() {
        return modifiedByUsername;
    }

    /**
     * @param modifiedByUser
     *            The modifiedByUser to set.
     */
    public void setModifiedByUsername(String modifiedByUser) {
        this.modifiedByUsername = modifiedByUser;
    }
}