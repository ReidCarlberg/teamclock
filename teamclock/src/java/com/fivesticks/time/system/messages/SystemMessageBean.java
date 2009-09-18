/*
 * Created on Jan 16, 2005 by REID
 */
package com.fivesticks.time.system.messages;

/**
 * @author REID
 */
public class SystemMessageBean {

    private String fromName;

    private String fromEmail;

    private String subject;

    private String message;

    private String modifiedByUser;

    private String ownerKey;

    /**
     * @return Returns the fromEmail.
     */
    public String getFromEmail() {
        return fromEmail;
    }

    /**
     * @param fromEmail
     *            The fromEmail to set.
     */
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
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
     * @return Returns the modifiedByUser.
     */
    public String getModifiedByUser() {
        return modifiedByUser;
    }

    /**
     * @param modifiedByUser
     *            The modifiedByUser to set.
     */
    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
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
}