/*
 * Created on Jan 21, 2005 by REID
 */
package com.fivesticks.time.externaluser.xwork;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.externaluser.common.ExternalUserToDoContext;
import com.fivesticks.time.externaluser.common.ExternalUserToDoContextAware;
import com.fivesticks.time.externaluser.events.ExternalUserEventPublisher;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateException;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author REID
 */
public class ExternalUserAddCommentAction extends AbstractExternalCustomerAction implements
         ExternalUserToDoContextAware {

    

    private ExternalUserToDoContext externalUserCommentContext;

    private Long target;

    private String comment;

    private String submitComment;

    private String cancelComment;

    public String execute() throws Exception {

        if (this.getCancelComment() != null) {
            return SUCCESS;
        }

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext());

        if (this.getTarget() != null) {
            this.getExternalUserCommentContext().setTarget(
                    sd.get(this.getTarget()));
            return INPUT;
        }

        if (this.getExternalUserCommentContext().getTarget() == null) {
            return ERROR;
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

        new ExternalUserEventPublisher().publicToDoCommentedEvent(this
                .getSessionContext(), this.getSessionContext()
                .getUser().getUser(), this.getExternalUserCommentContext()
                .getTarget(), this.getComment());

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
                    this.getExternalUserCommentContext().getTarget(),
                    this.getSessionContext().getUser().getUser(),
                    this.getComment());
        } catch (ObjectCommentServiceDelegateException e) {
            this.addActionError("Unable to save comment.");
        }
    }

    /**
     * @return Returns the externalUserCommentContext.
     */
    public ExternalUserToDoContext getExternalUserCommentContext() {
        return externalUserCommentContext;
    }

    /**
     * @param externalUserCommentContext
     *            The externalUserCommentContext to set.
     */
    public void setExternalUserToDoContext(
            ExternalUserToDoContext externalUserCommentContext) {
        this.externalUserCommentContext = externalUserCommentContext;
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
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
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
}