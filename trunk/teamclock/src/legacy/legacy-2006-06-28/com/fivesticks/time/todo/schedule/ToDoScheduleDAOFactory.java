/*
 * Created on Mar 1, 2005
 *
 * 
 */
package com.fivesticks.time.todo.schedule;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Nick
 * 
 *  
 */
public class ToDoScheduleDAOFactory {

    public static final String SPRING_BEAN_NAME = "toDoScheduleDAO";
    public static final ToDoScheduleDAOFactory factory = new ToDoScheduleDAOFactory();

    public ToDoScheduleDAO build() {
        return (ToDoScheduleDAO) SpringBeanBroker.getBeanFactory().getBean(
                ToDoScheduleDAOFactory.SPRING_BEAN_NAME);
    }

}