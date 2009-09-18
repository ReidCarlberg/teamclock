/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.todo.schedule;

import java.util.List;

/**
 * @author REID
 *  
 */
public interface ToDoScheduleDAO {

    public ToDoSchedule save(ToDoSchedule target);

    public ToDoSchedule get(Long id);

    public void delete(ToDoSchedule target);

    public List find(ToDoScheduleQueryParameters params);

}