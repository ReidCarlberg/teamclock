/*
 * Created on Feb 8, 2005 by Reid
 */
package com.fivesticks.time.register.xwork;

import com.fivesticks.time.register.events.RegisterEventPublisher;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class OtherInterestAction extends ActionSupport {

    private String contactEmail;

    private String contactName;

    private String name;

    private boolean interestedInEmail;

    private boolean interestedInUpdates;

    private boolean interestedInAutomation;

    private String comments;

    public String execute() throws Exception {

        new RegisterEventPublisher().publishOtherInterestsEvent(this);

        return SUCCESS;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return Returns the contactEmail.
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @param contactEmail
     *            The contactEmail to set.
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * @return Returns the contactName.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName
     *            The contactName to set.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return Returns the interestedInAutomation.
     */
    public boolean isInterestedInAutomation() {
        return interestedInAutomation;
    }

    /**
     * @param interestedInAutomation
     *            The interestedInAutomation to set.
     */
    public void setInterestedInAutomation(boolean interestedInAutomation) {
        this.interestedInAutomation = interestedInAutomation;
    }

    /**
     * @return Returns the interestedInEmail.
     */
    public boolean isInterestedInEmail() {
        return interestedInEmail;
    }

    /**
     * @param interestedInEmail
     *            The interestedInEmail to set.
     */
    public void setInterestedInEmail(boolean interestedInEmail) {
        this.interestedInEmail = interestedInEmail;
    }

    /**
     * @return Returns the interestedInUpdates.
     */
    public boolean isInterestedInUpdates() {
        return interestedInUpdates;
    }

    /**
     * @param interestedInUpdates
     *            The interestedInUpdates to set.
     */
    public void setInterestedInUpdates(boolean interestedInUpdates) {
        this.interestedInUpdates = interestedInUpdates;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}