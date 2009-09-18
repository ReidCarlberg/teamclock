/*
 * Created on Sep 30, 2004 by Reid
 */
package com.fivesticks.time.todo;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.delete.DeleteCommand;
import com.fivesticks.time.common.delete.DeleteCommandFailedException;

/**
 * @author Reid
 */
public class TodoDeleteCommand extends DeleteCommand {

    private static final String DELETE_SUCCESS = "delete-success-todo";

    private final ToDo target;

    public TodoDeleteCommand(ToDo target) {
        this.target = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#handleDelete(com.fivesticks.time.common.SessionContext)
     */
    protected void handleDelete(SessionContext sessionContext)
            throws DeleteCommandFailedException {
        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory
                .build(sessionContext);

        try {
            sd.delete(this.getTarget());
        } catch (ToDoServiceDelegateException e) {

            throw new DeleteCommandFailedException(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getTargetDescription()
     */
    public String getTargetDescription() throws DeleteCommandFailedException {

        return this.getTarget().getDetail();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getEffectDescription()
     */
    public String getEffectDescription() throws DeleteCommandFailedException {

        return "This will delete this To Do record.";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.delete.DeleteCommand#getXWorkSuccess()
     */
    public String getXWorkSuccess() throws DeleteCommandFailedException {

        return DELETE_SUCCESS;
    }

    /**
     * @return Returns the target.
     */
    public ToDo getTarget() {
        return target;
    }
}