/*
 * Created on Jan 21, 2005 by REID
 */
package com.fivesticks.time.externaluser.common;

import java.io.Serializable;

import com.fivesticks.time.todo.ToDo;

/**
 * @author REID
 */
public class ExternalUserToDoContext implements Serializable {

    private ToDo target;

    private String successValue;

    /**
     * @return Returns the successValue.
     */
    public String getSuccessValue() {
        return successValue;
    }

    /**
     * @param successValue
     *            The successValue to set.
     */
    public void setSuccessValue(String successValue) {
        this.successValue = successValue;
    }

    /**
     * @return Returns the target.
     */
    public ToDo getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(ToDo target) {
        this.target = target;
    }
}