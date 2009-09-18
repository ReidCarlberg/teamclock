/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.schedule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 *  
 */
public class ToDoScheduleDisplayBuilder {

    private SystemOwner systemOwner;

    public ToDoScheduleDisplayBuilder(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public Collection build(Collection raw) {
        Collection ret = new ArrayList();

        for (Iterator i = raw.iterator(); i.hasNext();) {

            ToDoScheduleDisplayWrapper display = new ToDoScheduleDisplayWrapper(
                    (ToDoSchedule) i.next(), systemOwner);
            ret.add(display);
        }

        return ret;

    }
}