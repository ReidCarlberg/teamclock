/*
 * Created on Jan 22, 2005 by Reid
 */
package com.fivesticks.time.externaluser.xwork;

import java.util.Collection;

import com.fivesticks.time.externaluser.common.ExternalUserToDoContext;
import com.fivesticks.time.externaluser.common.ExternalUserToDoContextAware;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoDisplayWrapper;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author Reid
 */
public class ExternalUserViewToDoDetailAction extends AbstractExternalCustomerAction implements
         ExternalUserToDoContextAware {

    

    private ExternalUserToDoContext externalUserToDoContext;

    private Long target;

    private ToDoDisplayWrapper todo;

    private Collection comments;

    public String execute() throws Exception {

        if (this.getTarget() == null
                && this.getExternalUserToDoContext().getTarget() == null)
            return INPUT;

        ToDo original = null;
        if (this.getTarget() != null) {
            original = ToDoServiceDelegateFactory.factory.build(
                    this.getSessionContext()).get(
                    this.getTarget());
            this.getExternalUserToDoContext().setTarget(original);
        } else {
            original = this.getExternalUserToDoContext().getTarget();
        }

        this.setComments(ObjectCommentServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner())
                .getComments(original));

        this.setTodo(new ToDoDisplayWrapper(original, this.getSessionContext()
                .getSystemOwner()));

        return SUCCESS;
    }

    /**
     * @return Returns the comments.
     */
    public Collection getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(Collection comments) {
        this.comments = comments;
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
     * @return Returns the todo.
     */
    public ToDoDisplayWrapper getTodo() {
        return todo;
    }

    /**
     * @param todo
     *            The todo to set.
     */
    public void setTodo(ToDoDisplayWrapper todo) {
        this.todo = todo;
    }

    /**
     * @return Returns the externalUserToDoContext.
     */
    public ExternalUserToDoContext getExternalUserToDoContext() {
        return externalUserToDoContext;
    }

    /**
     * @param externalUserToDoContext
     *            The externalUserToDoContext to set.
     */
    public void setExternalUserToDoContext(
            ExternalUserToDoContext externalUserToDoContext) {
        this.externalUserToDoContext = externalUserToDoContext;
    }
}