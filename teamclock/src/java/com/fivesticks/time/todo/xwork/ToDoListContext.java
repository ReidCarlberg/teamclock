/*
 * Created on Jun 26, 2004
 *
 */
package com.fivesticks.time.todo.xwork;

import java.io.Serializable;

import com.fivesticks.time.todo.ToDoCriteriaParameters;

/**
 * @author Reid
 *  
 */
public class ToDoListContext implements Serializable {

    public static final String KEY = "context.todo.list";

    private ToDoCriteriaParameters params;

    /**
     * @return
     */
    public ToDoCriteriaParameters getParams() {
        return params;
    }

    /**
     * @param parameters
     */
    public void setParams(ToDoCriteriaParameters parameters) {
        params = parameters;
    }

}