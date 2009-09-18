/*
 * Created on Jan 21, 2005 by REID
 */
package com.fivesticks.time.contactv2.xwork;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateException;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;

/**
 * @author REID
 */
public class ContactV2AddCommentAction extends SessionContextAwareSupport implements
        ContactV2ModifyContextAware {

    private ContactV2ModifyContext contactV2ModifyContext;

    //    private Long target;

    private String comment;

    private String submitComment;

    private String cancelComment;

    public String execute() throws Exception {

        if (this.getCancelComment() != null
                || this.getContactV2ModifyContext().getTarget() == null) {
            return SUCCESS;
        }

        if (this.getSubmitComment() == null) {
            return INPUT;
        }

        validate();

        if (this.hasErrors())
            return INPUT;

        persist();

        if (this.hasErrors())
            return INPUT;

        return SUCCESS;
    }

    public void validate() {
        ValidationHelper helper = new ValidationHelper();

        if (helper.isStringEmpty(this.getComment())) {
            this.addFieldError("comment", "You must include a comment.");
        }
    }

    private void persist() {

        try {
            ObjectCommentServiceDelegateFactory.factory.build(
                    this.getSessionContext().getSystemOwner()).addComment(
                    this.getContactV2ModifyContext().getTarget(),
                    this.getSessionContext().getUser().getUser(),
                    this.getComment());
        } catch (ObjectCommentServiceDelegateException e) {
            this.addActionError("Unable to save comment.");
        }
    }


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

    /**
     * @return Returns the cancelComment.
     */
    public String getCancelComment() {
        return cancelComment;
    }

    /**
     * @param cancelComment
     *            The cancelComment to set.
     */
    public void setCancelComment(String cancelComment) {
        this.cancelComment = cancelComment;
    }

    /**
     * @return Returns the submitComment.
     */
    public String getSubmitComment() {
        return submitComment;
    }

    /**
     * @param submitComment
     *            The submitComment to set.
     */
    public void setSubmitComment(String submitComment) {
        this.submitComment = submitComment;
    }

    /**
     * @return the contactV2ModifyContext
     */
    public ContactV2ModifyContext getContactV2ModifyContext() {
        return contactV2ModifyContext;
    }

    /**
     * @param contactV2ModifyContext the contactV2ModifyContext to set
     */
    public void setContactV2ModifyContext(
            ContactV2ModifyContext contactV2ModifyContext) {
        this.contactV2ModifyContext = contactV2ModifyContext;
    }


}