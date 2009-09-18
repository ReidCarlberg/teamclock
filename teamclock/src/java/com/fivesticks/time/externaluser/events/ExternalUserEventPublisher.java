/*
 * Created on Jan 20, 2005 by REID
 */
package com.fivesticks.time.externaluser.events;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.events.GeneralEventQueue;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.events.ToDoEventPublisher;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public class ExternalUserEventPublisher {

    public void publishUserAssociatedEvent(SessionContext sessionContext, User user, 
            Customer fstxCustomer) {
        publishUserEvent(sessionContext, user, fstxCustomer,
                ExternalUserEventType.ASSOCIATED);
    }

    public void publishUserDisassociatedEvent(SessionContext sessionContext,
            User user, Customer fstxCustomer) {
        publishUserEvent(sessionContext, user, fstxCustomer,
                ExternalUserEventType.DISASSOCIATED);
    }

    private void publishUserEvent(SessionContext sessionContext, User user,
            Customer fstxCustomer, ExternalUserEventType eventType) {

        ExternalUserEventImpl event = new ExternalUserEventImpl();
        event.setSessionContext(sessionContext);
        event.setSystemOwner(sessionContext.getSystemOwner());
        event.setUser(user);
        event.setFstxCustomer(fstxCustomer);
        event.setExternalUserEventType(eventType);

        GeneralEventQueue.singleton.add(event);

    }

    public void publishToDoAddedEvent(SessionContext sessionContext, User user,
            ToDo toDo) {

        new ToDoEventPublisher().publishToDoAddedByExternalUserEvent(
                sessionContext, user, toDo);

    }

    /**
     * @param systemOwner
     * @param user
     * @param target
     * @param comment
     */
    public void publicToDoCommentedEvent(SessionContext sessionContext, User user,
            ToDo target, String comment) {

        new ToDoEventPublisher().publishToDoCommented(sessionContext, user,
                target, comment);
    }
}