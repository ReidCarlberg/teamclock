/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Nick
 *  
 */
public class TaskServiceDelegateFactory extends AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME = "taskServiceDelegate";
    public static final TaskServiceDelegateFactory factory = new TaskServiceDelegateFactory();

    public TaskServiceDelegate build(SessionContext sessionContext) {
        return build(sessionContext.getSystemOwner());
    }

    public TaskServiceDelegate build(SystemOwner systemOwner) {
        TaskServiceDelegate ret = (TaskServiceDelegate) this
                .getBean(TaskServiceDelegateFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);
        return ret;

    }

    public TaskServiceDelegate buildEmpty() {

        TaskServiceDelegate ret = (TaskServiceDelegate) this
                .getBean(TaskServiceDelegateFactory.SPRING_BEAN_NAME);

        return ret;
    }
}