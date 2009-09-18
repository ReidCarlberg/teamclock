/*
 * Created on Jan 2, 2005 by REID
 */
package com.fivesticks.time.todo.events;

import com.fivesticks.time.events.AbstractGeneralEvent;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.todo.ToDo;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public class ToDoEventImpl extends AbstractGeneralEvent implements ToDoEvent {

//    private SystemOwner systemOwner;

    private User user;

    private ToDo toDo;

    private String detail;

    private ToDoEventTypeEnum toDoEventTypeEnum;

//    /**
//     * @return Returns the systemOwner.
//     */
//    public SystemOwner getSystemOwner() {
//        return systemOwner;
//    }
//
//    /**
//     * @param systemOwner
//     *            The systemOwner to set.
//     */
//    public void setSystemOwner(SystemOwner systemOwner) {
//        this.systemOwner = systemOwner;
//    }

    /**
     * @return Returns the toDo.
     */
    public ToDo getToDo() {
        return toDo;
    }

    /**
     * @param toDo
     *            The toDo to set.
     */
    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.events.GeneralEvent#getGeneralEventType()
     */
    public GeneralEventType getGeneralEventType() {

        return GeneralEventType.TODO_EVENT;
    }

    /**
     * @return Returns the toDoEventTypeEnum.
     */
    public ToDoEventTypeEnum getToDoEventTypeEnum() {
        return toDoEventTypeEnum;
    }

    /**
     * @param toDoEventTypeEnum
     *            The toDoEventTypeEnum to set.
     */
    public void setToDoEventTypeEnum(ToDoEventTypeEnum toDoEventTypeEnum) {
        this.toDoEventTypeEnum = toDoEventTypeEnum;
    }

    /**
     * @return Returns the detail.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            The detail to set.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }
}