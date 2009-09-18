/*
 * Created on Jun 4, 2004
 *
 */
package com.fivesticks.time.todo.schedule;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 *  
 */
public class ToDoScheduleServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "toDoScheduleServiceDelegate";
    public static final ToDoScheduleServiceDelegateFactory factory = new ToDoScheduleServiceDelegateFactory();

    public ToDoScheduleServiceDelegate build(SystemOwner systemOwner) {

        ToDoScheduleServiceDelegate toDoScheduleServiceDelegate = (ToDoScheduleServiceDelegate) SpringBeanBroker
                .getBeanFactory().getBean(
                        ToDoScheduleServiceDelegateFactory.SPRING_BEAN_NAME);

        toDoScheduleServiceDelegate.setSystemOwner(systemOwner);
        return toDoScheduleServiceDelegate;
    }

}