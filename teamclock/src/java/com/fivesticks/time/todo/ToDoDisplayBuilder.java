/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 *  
 */
public class ToDoDisplayBuilder {

    private SystemOwner systemOwner;

    public ToDoDisplayBuilder(SessionContext sessionContext) {
        this.systemOwner = sessionContext.getSystemOwner();
    }
    
    public ToDoDisplayBuilder(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public Collection build(Collection raw) {
        Collection ret = new ArrayList();

        for (Iterator i = raw.iterator(); i.hasNext();) {

            ToDoDisplayWrapper display = new ToDoDisplayWrapper(
                    (ToDo) i.next(), systemOwner);
            ret.add(display);
        }

        return ret;

    }
}