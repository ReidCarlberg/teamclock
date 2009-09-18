/*
 * Created on Jan 22, 2005 by Reid
 */
package com.fivesticks.time.todo.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoDisplayWrapper;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author Reid
 */
public class ToDoViewDetailAction extends SessionContextAwareSupport implements TodoModifyContextAware {

    

    private TodoModifyContext todoModifyContext;

    private Long target;

    private ToDoDisplayWrapper todo;

    private Collection comments;

    public String execute() throws Exception {

        if (this.getTarget() == null
                && this.getTodoModifyContext().getTarget() == null)
            return INPUT;

        this.getSessionContext().setMenuSection(MenuSection.TODO);

        this.getSessionContext().setSuccessOverride(
                GlobalForwardStatics.GLOBAL_TODO_DETAIL);

        ToDo original = null;

        if (this.getTarget() != null) {
            original = ToDoServiceDelegateFactory.factory.build(
                    this.getSessionContext()).get(
                    this.getTarget());
        } else {
            original = this.getTodoModifyContext().getTarget();
        }

        this.setComments(ObjectCommentServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner())
                .getComments(original));

        this.setTodo(new ToDoDisplayWrapper(original, this.getSessionContext()
                .getSystemOwner()));

        this.getTodoModifyContext().setTarget(original);

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