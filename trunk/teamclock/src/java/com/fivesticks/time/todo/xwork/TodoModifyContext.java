/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.xwork;

import com.fivesticks.time.todo.ToDo;

/**
 * @author Reid
 *  
 */
public class TodoModifyContext {

    //public static final String KEY = "context.todo.modify";

    private ToDo target;

    /**
     * @return
     */
    public ToDo getTarget() {
        return target;
    }

    /**
     * @param do1
     */
    public void setTarget(ToDo do1) {
        target = do1;
    }

}