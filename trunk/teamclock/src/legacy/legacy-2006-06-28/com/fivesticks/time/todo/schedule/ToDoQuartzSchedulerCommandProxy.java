/*
 * Created on Dec 21, 2004 by REID
 */
package com.fivesticks.time.todo.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author REID
 */
public class ToDoQuartzSchedulerCommandProxy {

    private static Log log = LogFactory
            .getLog(ToDoQuartzSchedulerCommandProxy.class);

    public void execute() {
        ToDoQuartzSchedulerCommand command = (ToDoQuartzSchedulerCommand) SpringBeanBroker
                .getBeanFactory().getBean(
                        ToDoQuartzSchedulerCommand.SPRING_BEAN_NAME);
        try {
            command.execute();
        } catch (ToDoQuartzSchedulerCommandException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

}