/*
 * Created on Jan 2, 2005 by REID
 */
package com.fivesticks.time.todo.events;

import com.fivesticks.time.events.GeneralEvent;
import com.fivesticks.time.todo.ToDo;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public interface ToDoEvent extends GeneralEvent {

    public User getUser();

    public ToDo getToDo();

    public ToDoEventTypeEnum getToDoEventTypeEnum();

}