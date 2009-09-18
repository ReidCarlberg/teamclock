/*
 * Created on Jan 21, 2005 by REID
 */
package com.fivesticks.time.todo.xwork.legacy;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateException;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;
import com.fivesticks.time.todo.events.ToDoEventPublisher;
import com.fivesticks.time.todo.xwork.TodoModifyContext;
import com.fivesticks.time.todo.xwork.TodoModifyContextAware;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class ToDoAddCommentAction extends ActionSupport implements
        SessionContextAware, TodoModifyContextAware {

    private SessionContext sessionContext;

    private TodoModifyContext todoModifyContext;

    //    private Long target;

    private String comment;

    private String submitComment;

    private String cancelComment;

    public String execute() throws Exception {

        if (this.getCancelComment() != null
                || this.getTodoModifyContext().getTarget() == null) {
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

        new ToDoEventPublisher().publishToDoCommented(this.getSessionContext()
                .getSystemOwner(),
                this.getSessionContext().getUser().getUser(), this
                        .getTodoModifyContext().getTarget(), this.getComment());

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
                    this.getTodoModifyContext().getTarget(),
                    this.getSessionContext().getUser().getUser(),
                    this.getComment());
        } catch (ObjectCommentServiceDelegateException e) {
            this.addActionError("Unable to save comment.");
        }
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
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
     * @return Returns the todoModifyContext.
     */
    public TodoModifyContext getTodoModifyContext() {
        return todoModifyContext;
    }

    /**
     * @param todoModifyContext
     *            The todoModifyContext to set.
     */
    public void setTodoModifyContext(TodoModifyContext todoModifyContext) {
        this.todoModifyContext = todoModifyContext;
    }
}