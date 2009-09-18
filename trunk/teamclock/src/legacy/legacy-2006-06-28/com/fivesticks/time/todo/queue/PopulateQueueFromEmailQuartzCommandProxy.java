/*
 * Created on Oct 5, 2004
 *
 * 
 */
package com.fivesticks.time.todo.queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Nick
 */

public class PopulateQueueFromEmailQuartzCommandProxy {

//    private ToDoServiceDelegate toDoServiceDelegate;

    public static String SPRING_BEAN_NAME = "populateQueueFromEmailQuartzCommandProxy";

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    // public void schedule(CmmsSessionContext cmmsSessionContext) {
    public void execute()  {
        PopulateQueueFromEmailQuartzCommand command = (PopulateQueueFromEmailQuartzCommand) SpringBeanBroker
                .getBeanFactory().getBean(
                        PopulateQueueFromEmailQuartzCommand.SPRING_BEAN_NAME);
        try {
            command.execute();
        } catch (PopulateQueueFromEmailQuartzCommandException e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    private Log log = LogFactory
            .getLog(PopulateQueueFromEmailQuartzCommandProxy.class);

}