/*
 * Created on Oct 5, 2004
 *
 * 
 */
package com.fivesticks.time.todo.queue;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateException;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;

/**
 * @author Nick
 * 
 * 
 */

public class PopulateQueueFromEmailQuartzCommand {

//    private ToDoServiceDelegate toDoServiceDelegate;

    public static String SPRING_BEAN_NAME = "populateQueueFromEmailQuartzCommand";

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    // public void schedule(CmmsSessionContext cmmsSessionContext) {
    public void execute() throws PopulateQueueFromEmailQuartzCommandException {

        Folder folder = this.getFolder();

        try {
            javax.mail.Message message[] = folder.getMessages();

            for (int i = 0, n = message.length; i < n; i++) {

                ToDo todo = new ToDo();
                todo.setAssignedToUser("queue");
                todo.setComplete(false);
                todo.setCreateTimestamp(new Date());
                todo.setDeadlineTimestamp(new Date());
                todo.setDetail(message[i].getFrom()[0] + "\n"
                        + message[i].getSubject() + "\n"
                        + message[i].getContent());
                todo.setEnteredByUser("queue");

                todo.setProjectId(new Long(0));
                todo.setPriority("I");
                message[i].setFlag(Flags.Flag.DELETED, true);

                try {
                    this.getToDoServiceDelegate().save(todo);
                } catch (ToDoServiceDelegateException e) {
                    message[i].setFlag(Flags.Flag.DELETED, false);
                    // e.printStackTrace();
                }
            }
            folder.expunge();

        } catch (MessagingException e) {
            throw new PopulateQueueFromEmailQuartzCommandException(e);
        } catch (IOException e) {

            throw new PopulateQueueFromEmailQuartzCommandException(e);
        }

        try {
            folder.close(false);
        } catch (MessagingException e5) {

            e5.printStackTrace();
        }
        try {
            folder.getStore().close();
        } catch (MessagingException e6) {

            e6.printStackTrace();
        }

    }

    private Folder getFolder()
            throws PopulateQueueFromEmailQuartzCommandException {

        Properties props = new Properties();

        Session s = Session.getInstance(props, null);

        Store store;
        Folder folder = null;
        try {
            store = s.getStore("imap");

        } catch (NoSuchProviderException e1) {

            throw new PopulateQueueFromEmailQuartzCommandException(e1);
        }
        try {
            store.connect("pop.emailsrvr.com", "service@fivesticks.com",
                    "service123");
        } catch (MessagingException e2) {
            throw new PopulateQueueFromEmailQuartzCommandException(e2);
        }

        try {
            folder = store.getFolder("INBOX");
        } catch (MessagingException e3) {
            throw new PopulateQueueFromEmailQuartzCommandException(e3);
        }

        try {
            folder.open(Folder.READ_WRITE);
        } catch (MessagingException e) {
            throw new PopulateQueueFromEmailQuartzCommandException(e);

        }

        return folder;
    }

//    private Log log = LogFactory
//            .getLog(PopulateQueueFromEmailQuartzCommand.class);

    public ToDoServiceDelegate getToDoServiceDelegate() {

        SystemOwner so = null;
        try {
            so = SystemOwnerServiceDelegateFactory.factory.build().get("CXZASTPKGU");
        } catch (SystemOwnerServiceDelegateException e) {
            // throw new PopulateQueueFromEmailQuartzCommandException(e);

        }

        // this.toDoServiceDelegate.setSystemOwner(so);
        //
        // return toDoServiceDelegate;

        return ToDoServiceDelegateFactory.factory.build(so);
    }

//    public void setToDoServiceDelegate(ToDoServiceDelegate todoServiceDelegate) {
//        this.toDoServiceDelegate = todoServiceDelegate;
//    }
}