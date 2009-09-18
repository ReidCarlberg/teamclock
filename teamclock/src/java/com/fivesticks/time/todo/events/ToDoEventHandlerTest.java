/*
 * Created on Jan 3, 2005 by REID
 */
package com.fivesticks.time.todo.events;

import java.util.Collection;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.events.EventChannelBroker;
import com.fivesticks.time.events.GeneralEventType;
import com.fivesticks.time.object.comments.ObjectComment;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;
import com.fivesticks.time.queuedmessages.QueuedMessageDAOFactory;
import com.fivesticks.time.queuedmessages.QueuedMessageFilter;
import com.fivesticks.time.system.messages.SystemMessageBean;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoDAOFactory;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoStatusEnum;
import com.fivesticks.time.todo.ToDoTestFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserFactory;

/**
 * @author REID
 */
public class ToDoEventHandlerTest extends AbstractTimeTestCase {



    public void testPublishEvent() throws Exception {

        User user2 = UserFactory.singleton
                .getPersistedWithSystemOwner(systemOwner);

        ToDo todo1 = ToDoTestFactory.getPersisted(customer, project,
                user, systemOwner);
        todo1.setAssignedToUser(user2.getUsername());
        todo1.setComplete(true);

        new ToDoDAOFactory().build().save(todo1);

        new ToDoEventPublisher().publishToDoEvent(this.sessionContext, user2,
                todo1, ToDoEventTypeEnum.COMPLETE);

        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT).getEvents().size() == 1);

    }

    public void testHandleEvent() throws Exception {

//        SystemOwner defaultSystemOwner = SystemOwnerTestFactory.singleton
//                .buildWithDefaultSettings();
//        FstxCustomer fstxCustomer = FstxCustomerTestFactory
//                .getPersisted(defaultSystemOwner);
//        FstxProject fstxProject = FstxProjectTestFactory.getPersisted(
//                defaultSystemOwner, fstxCustomer);
//        User user = UserFactory.singleton
//                .getPersistedWithSystemOwner(defaultSystemOwner);
        User user2 = UserFactory.singleton
                .getPersistedWithSystemOwner(systemOwner);

        ToDo todo1 = ToDoTestFactory.getPersisted(customer, project,
                user, systemOwner);
        todo1.setAssignedToUser(user2.getUsername());
        todo1.setComplete(true);

        new ToDoDAOFactory().build().save(todo1);

        new ToDoEventPublisher().publishToDoEvent(this.sessionContext, user2,
                todo1, ToDoEventTypeEnum.COMPLETE);

        assertTrue(EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT).getEvents().size() == 1);

        Collection events = EventChannelBroker.singleton.getChannel(GeneralEventType.TODO_EVENT).getEvents();

        ToDoEvent event = (ToDoEvent) events.toArray()[0];

        new ToDoEventHandler().handleEvent(event);

        /*
         * 2005-01-04 This should create a new To Do for review
         */
        Collection toDos = ToDoServiceDelegateFactory.factory
                .build(this.sessionContext).findIncomplete();

        assertTrue(toDos.size() == 1);

        ToDo toDoReview = (ToDo) toDos.toArray()[0];

        assertTrue(toDoReview.getAssignedToUser().equals(user.getUsername()));
    }

    public void testHandleCommentEventAddsSingleMessage() throws Exception {

        SystemMessageBean bean = SpringBeanBroker
                .getCommonMessage("newCommentNotification");


        User extUser = SystemUserTestFactory.singleton.buildExternal(this.sessionContext.getSystemOwner());

        ToDo todo1 = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);
//        todo1.setAssignedToUser(user1.getUsername());
        todo1.setExternalUser(extUser.getUsername());
        ToDoServiceDelegateFactory.factory.build(this.sessionContext).save(todo1);

        ObjectComment comment = ObjectCommentServiceDelegateFactory.factory.build(
                this.systemOwner).addComment(todo1, this.user, "Here is a comment");

        ToDoEventImpl event = new ToDoEventImpl();
        event.setUser(this.user);
        event.setToDoEventTypeEnum(ToDoEventTypeEnum.COMMENTED);
        event.setToDo(todo1);
        event.setSystemOwner(this.systemOwner);
        event.setSessionContext(this.sessionContext);
        event.setDetail(comment.getComment());

        new ToDoEventHandler().handleEvent(event);

        QueuedMessageFilter filter = new QueuedMessageFilter();
        filter.setBooleanSent(new Boolean(false));
        filter.setSendTimeRangeStop(new DateTime(DateTimeZone.UTC));
        filter.setOwnerKey(bean.getOwnerKey());

        Collection unsent = QueuedMessageDAOFactory.factory.build().find(filter);

        assertTrue(unsent.size() == 1);
    }

    public void testHandleReassignedEvent() throws Exception {


        User extUser = SystemUserTestFactory.singleton.buildExternal(this.systemOwner);

        ToDo todo1 = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);
        
        todo1.setExternalUser(extUser.getUsername());
        ToDoServiceDelegateFactory.factory.build(this.sessionContext).save(todo1);

        assertTrue(todo1.getStatus() == null);

        ToDoEventImpl event = new ToDoEventImpl();
        event.setSystemOwner(this.systemOwner);
        event.setSessionContext(this.sessionContext);
        event.setToDo(todo1);
        event.setToDoEventTypeEnum(ToDoEventTypeEnum.REASSIGNED);
        event.setUser(this.user);

        ToDoEventHandler handler = new ToDoEventHandler();
        handler.handleEvent(event);

        ToDo updated = ToDoServiceDelegateFactory.factory.build(this.sessionContext).get(
                todo1.getId());

        assertTrue(updated.getStatus()
                .equals(ToDoStatusEnum.ASSIGNED.getName()));
    }

}