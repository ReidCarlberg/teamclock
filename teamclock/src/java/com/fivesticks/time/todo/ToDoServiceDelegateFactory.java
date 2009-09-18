/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.todo;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.common.SessionContext;

/**
 * @author REID
 * 
 */
public class ToDoServiceDelegateFactory extends AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME = "toDoServiceDelegate";

    public static final ToDoServiceDelegateFactory factory = new ToDoServiceDelegateFactory();

    public ToDoServiceDelegate build(SessionContext sessionContext) {

        ToDoServiceDelegateImpl ret = (ToDoServiceDelegateImpl) this
                .getBean(SPRING_BEAN_NAME);

        ret.setSessionContext(sessionContext);

        return ret;
    }
}