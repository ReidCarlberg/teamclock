/*
 * Created on Jan 2, 2005 by REID
 */
package com.fivesticks.time.todo.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.events.EventHandler;
import com.fivesticks.time.events.EventHandlerException;
import com.fivesticks.time.events.GeneralEvent;
import com.fivesticks.time.object.comments.ObjectComment;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateException;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageBuilder;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateException;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.system.messages.SystemMessageBean;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateException;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoStatusEnum;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author REID
 */
public class ToDoEventHandler implements EventHandler {

    public static final String SPRING_BEAN_NAME = "toDoEventHandler";

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.events.EventHandler#handleEvent(com.fivesticks.time.events.GeneralEvent)
     */
    public void handleEvent(final GeneralEvent event)
            throws EventHandlerException {
        if (!(event instanceof ToDoEvent)) {
            throw new EventHandlerException("bad event type");
        }

        ToDoEvent toDoEvent = (ToDoEvent) event;

        if (toDoEvent.getToDoEventTypeEnum() == ToDoEventTypeEnum.COMPLETE) {
            handleComplete(toDoEvent);
        } else if (toDoEvent.getToDoEventTypeEnum() == ToDoEventTypeEnum.COMMENTED) {
            handleCommented(toDoEvent);
        } else if (toDoEvent.getToDoEventTypeEnum() == ToDoEventTypeEnum.REASSIGNED) {
            handleReassigned(toDoEvent);
        } else if (toDoEvent.getToDoEventTypeEnum() == ToDoEventTypeEnum.ACKNOWLEDGE) {
            handleAcknowledge(toDoEvent);
        }

    }

    /**
     * @param toDoEvent
     */
    private void handleReassigned(ToDoEvent toDoEvent)
            throws EventHandlerException {

        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory
                .build(toDoEvent.getSessionContext());

        toDoEvent.getToDo().setStatus(ToDoStatusEnum.ASSIGNED.getName());

        try {
            sd.save(toDoEvent.getToDo());
        } catch (ToDoServiceDelegateException e) {
            throw new EventHandlerException(e);
        }
    }

    /**
     * @param toDoEvent
     */
    private void handleCommented(ToDoEvent toDoEvent)
            throws EventHandlerException {

        String messageName = "newCommentNotification";

        Project project;
        try {
            project = ProjectServiceDelegateFactory.factory.build(
                    toDoEvent.getSystemOwner()).getFstxProject(
                    toDoEvent.getToDo().getProjectId());
        } catch (ProjectBDException e) {
            throw new EventHandlerException(e);
        }

        Collection collection;
        try {
            collection = ObjectCommentServiceDelegateFactory.factory.build(
                    toDoEvent.getSystemOwner())
                    .getComments(toDoEvent.getToDo());
        } catch (ObjectCommentServiceDelegateException e1) {
            throw new EventHandlerException(e1);
        }
        SystemMessageBean message = SpringBeanBroker
                .getCommonMessage(messageName);

        QueuedMessage qm = new QueuedMessageBuilder().build(message);

        qm.setSubject(qm.getSubject().replaceAll("\\{0\\}",
                project.getShortName()));

        qm.setMessage(qm.getMessage().replaceAll("\\{0\\}",
                toDoEvent.getSystemOwner().getKey()));

        qm.setMessage(qm.getMessage().replaceAll("\\{1\\}",
                project.getShortName()));

        qm.setMessage(qm.getMessage().replaceAll("\\{2\\}",
                toDoEvent.getToDo().getDetail()));

        StringBuffer comments = new StringBuffer();

        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            ObjectComment element = (ObjectComment) iter.next();
            comments.append("By: " + element.getUsername() + "\n");
            comments.append("Date: " + element.getTimestamp() + "\n");
            comments.append("Comment:\n " + element.getComment() + "\n\n");

        }

        qm.setMessage(qm.getMessage()
                .replaceAll("\\{3\\}", comments.toString()));

        // who does this go to?
        User recipient = null;
        Collection recipients = new ArrayList();

        if (toDoEvent.getUser().getUsername().equals(
                toDoEvent.getToDo().getEnteredByUser())) {
            if (!toDoEvent.getToDo().getAssignedToUser().equals(
                    toDoEvent.getUser().getUsername())) {
                recipients.add(toDoEvent.getToDo().getAssignedToUser());
            }
            if (toDoEvent.getToDo().getExternalUser() != null) {
                recipients.add(toDoEvent.getToDo().getExternalUser());
            }
        } else if (toDoEvent.getUser().getUsername().equals(
                toDoEvent.getToDo().getExternalUser())) {
            recipients.add(toDoEvent.getToDo().getAssignedToUser());
            recipients.add(toDoEvent.getToDo().getEnteredByUser());
        }

        UserBD userBD = UserBDFactory.factory.build();
        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory
                .build(qm.getOwnerKey());

        for (Iterator iter = recipients.iterator(); iter.hasNext();) {
            String element = (String) iter.next();
            User user;
            try {
                user = userBD.getByUsername(element);
            } catch (UserBDException e3) {
                throw new EventHandlerException(e3);
            }
            try {
                qm.setToAddress(user.getEmail());
                qm.setToName(user.getEmail());
                sd.addSystemMessage(qm);
            } catch (QueuedMessageServiceDelegateException e2) {
                throw new EventHandlerException(e2);
            }
        }

    }

    /**
     * @param toDoEvent
     * @throws EventHandlerException
     */
    private void handleComplete(ToDoEvent toDoEvent)
            throws EventHandlerException {

        /*
         * basically this does nothing.
         */
        if (toDoEvent.getToDo().getAssignedToUser().equals(
                toDoEvent.getToDo().getEnteredByUser())) {
            return;
        }

        ToDoServiceDelegate toDoServiceDelegate = ToDoServiceDelegateFactory.factory
                .build(toDoEvent.getSessionContext());

        ToDo current = toDoEvent.getToDo();

        current.setId(null);
        current.setAssignedToUser(current.getEnteredByUser());
        current.setCreateTimestamp(new Date());
        current.setDetail("REVIEW: " + current.getDetail());
        current.setComplete(false);

        try {
            toDoServiceDelegate.save(current);
        } catch (ToDoServiceDelegateException e) {
            throw new EventHandlerException(e);
        }

    }

    private void handleAcknowledge(ToDoEvent toDoEvent)
            throws EventHandlerException {

        /*
         * basically this does nothing.
         */
        if (toDoEvent.getToDo().getAssignedToUser().equals(
                toDoEvent.getToDo().getEnteredByUser())) {
            return;
        }

        ToDoServiceDelegate toDoServiceDelegate = ToDoServiceDelegateFactory.factory
                .build(toDoEvent.getSessionContext());

        ToDo current = toDoEvent.getToDo();

        current.setId(null);
        current.setAssignedToUser(current.getEnteredByUser());
        current.setCreateTimestamp(new Date());
        current.setDetail("ACKNOWLEDGED: " + current.getDetail());
        current.setComplete(false);

        try {
            toDoServiceDelegate.save(current);
        } catch (ToDoServiceDelegateException e) {
            throw new EventHandlerException(e);
        }

    }

}