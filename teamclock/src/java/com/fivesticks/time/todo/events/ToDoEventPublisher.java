package com.fivesticks.time.todo.events;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.events.GeneralEventQueue;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 */
public class ToDoEventPublisher {

    public void publishToDoCompleteEvent(SessionContext sessionContext,
            User user, ToDo toDo) {
        this.publishToDoEvent(sessionContext, user, toDo,
                ToDoEventTypeEnum.COMPLETE);
    }

    public void publishToDoAcknowledgeEvent(SessionContext sessionContext,
            User user, ToDo toDo) {
        this.publishToDoEvent(sessionContext, user, toDo,
                ToDoEventTypeEnum.ACKNOWLEDGE);
    }

    public void publishToDoReassignedEvent(SessionContext sessionContext,
            User user, ToDo toDo) {
        this.publishToDoEvent(sessionContext, user, toDo,
                ToDoEventTypeEnum.REASSIGNED);
    }

    public void publishToDoAddedByExternalUserEvent(
            SessionContext sessionContext, User user, ToDo toDo) {
        this.publishToDoEvent(sessionContext, user, toDo,
                ToDoEventTypeEnum.EXERNALADD);
    }

    public void publishToDoEvent(SessionContext sessionContext, User user,
            ToDo toDo, ToDoEventTypeEnum toDoEventTypeEnum) {
        publishToDoEvent(sessionContext, user, toDo, toDoEventTypeEnum, null);
    }

    public void publishToDoEvent(SessionContext sessionContext, User user,
            ToDo toDo, ToDoEventTypeEnum toDoEventTypeEnum, String detail) {
        ToDoEventImpl event = new ToDoEventImpl();
        event.setSessionContext(sessionContext);
        event.setSystemOwner(sessionContext.getSystemOwner());
        event.setUser(user);
        event.setToDo(toDo);
        event.setToDoEventTypeEnum(toDoEventTypeEnum);
        event.setDetail(detail);
        GeneralEventQueue.singleton.add(event);
    }

    /**
     * @param systemOwner
     * @param user
     * @param target
     * @param comment
     */
    public void publishToDoCommented(SessionContext sessionContext, User user,
            ToDo target, String comment) {
        publishToDoEvent(sessionContext, user, target,
                ToDoEventTypeEnum.COMMENTED, comment);
    }

    /*
     * 2006-07-06 - reid - the event queue is largely useless, so I'm just
     * throwing in the code for this new event type.
     */
    public void publishToDoWorkedOnEvent(SessionContext sessionContext,
            Activity activity) throws Exception {

        if (activity.getToDoId() == null) {
            return;
        }

        ToDoServiceDelegate ds = ToDoServiceDelegateFactory.factory
                .build(sessionContext);
        ToDo targetToDo = ds.get(activity.getToDoId());

        if (targetToDo != null) {
            ds.decorateWithTotalActivityTotals(targetToDo);
        }

    }

}