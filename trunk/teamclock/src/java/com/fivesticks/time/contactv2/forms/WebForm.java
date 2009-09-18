/*
 * Created on Sep 2, 2006
 *
 */
package com.fivesticks.time.contactv2.forms;

import com.fivesticks.time.common.AbstractDateAwareTimeObject;

public class WebForm extends AbstractDateAwareTimeObject {

    private String key;
    
    private String name;
    
    private String assignToUsername;
    
    private boolean redirectResponse;
    
    private String successURL;
    
    private String failureURL;
    
    private Long contactClass;
    
    private Long contactSource;
    
    private Long contactInterest;
    
    private boolean sendNotification;
    
    private Long notificationMessageId;
    
    private String notificationMessageRecipient;
    
    private boolean sendThankYouMessage;
    
    private Long thankYouMessageId;
    
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

    /**
     * @return Returns the assignToUsername.
     */
    public String getAssignToUsername() {
        return assignToUsername;
    }

    /**
     * @param assignToUsername The assignToUsername to set.
     */
    public void setAssignToUsername(String assignToUsername) {
        this.assignToUsername = assignToUsername;
    }

    /**
     * @return Returns the contactInterest.
     */
    public Long getContactInterest() {
        return contactInterest;
    }

    /**
     * @param contactInterest The contactInterest to set.
     */
    public void setContactInterest(Long contactInterest) {
        this.contactInterest = contactInterest;
    }

    /**
     * @return Returns the contactSource.
     */
    public Long getContactSource() {
        return contactSource;
    }

    /**
     * @param contactSource The contactSource to set.
     */
    public void setContactSource(Long contactSource) {
        this.contactSource = contactSource;
    }

    /**
     * @return Returns the contactStatus.
     */
    public Long getContactClass() {
        return contactClass;
    }

    /**
     * @param contactStatus The contactStatus to set.
     */
    public void setContactClass(Long contactStatus) {
        this.contactClass = contactStatus;
    }

    /**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the notificationMessageId.
     */
    public Long getNotificationMessageId() {
        return notificationMessageId;
    }

    /**
     * @param notificationMessageId The notificationMessageId to set.
     */
    public void setNotificationMessageId(Long notificationMessageId) {
        this.notificationMessageId = notificationMessageId;
    }

    /**
     * @return Returns the notificationMessageRecipient.
     */
    public String getNotificationMessageRecipient() {
        return notificationMessageRecipient;
    }

    /**
     * @param notificationMessageRecipient The notificationMessageRecipient to set.
     */
    public void setNotificationMessageRecipient(String notificationMessageRecipient) {
        this.notificationMessageRecipient = notificationMessageRecipient;
    }

    /**
     * @return Returns the sendNotification.
     */
    public boolean isSendNotification() {
        return sendNotification;
    }

    /**
     * @param sendNotification The sendNotification to set.
     */
    public void setSendNotification(boolean sendNotification) {
        this.sendNotification = sendNotification;
    }

    /**
     * @return Returns the sendThankYouMessage.
     */
    public boolean isSendThankYouMessage() {
        return sendThankYouMessage;
    }

    /**
     * @param sendThankYouMessage The sendThankYouMessage to set.
     */
    public void setSendThankYouMessage(boolean sendThankYouMessage) {
        this.sendThankYouMessage = sendThankYouMessage;
    }

    /**
     * @return Returns the successLocation.
     */
    public String getSuccessURL() {
        return successURL;
    }

    /**
     * @param successLocation The successLocation to set.
     */
    public void setSuccessURL(String successLocation) {
        this.successURL = successLocation;
    }

    /**
     * @return Returns the thankYouMessageId.
     */
    public Long getThankYouMessageId() {
        return thankYouMessageId;
    }

    /**
     * @param thankYouMessageId The thankYouMessageId to set.
     */
    public void setThankYouMessageId(Long thankYouMessageId) {
        this.thankYouMessageId = thankYouMessageId;
    }

    /**
     * @return Returns the failureURL.
     */
    public String getFailureURL() {
        return failureURL;
    }

    /**
     * @param failureURL The failureURL to set.
     */
    public void setFailureURL(String failureURL) {
        this.failureURL = failureURL;
    }

    /**
     * @return the redirectResponse
     */
    public boolean isRedirectResponse() {
        return redirectResponse;
    }

    /**
     * @param redirectResponse the redirectResponse to set
     */
    public void setRedirectResponse(boolean redirectResponse) {
        this.redirectResponse = redirectResponse;
    }
    
}
