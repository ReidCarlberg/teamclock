/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.config;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.fivesticks.time.activity.FstxActivity;
import com.fivesticks.time.activity.FstxActivityDAO;
import com.fivesticks.time.activity.FstxActivityFilterVO;
import com.fivesticks.time.calendar.FstxCalendar;
import com.fivesticks.time.calendar.FstxCalendarDAO;
import com.fivesticks.time.calendar.FstxCalendarFilterParameters;
import com.fivesticks.time.common.TransactionManagerAware;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerFilterVO;
import com.fivesticks.time.customer.FstxProject;
import com.fivesticks.time.customer.FstxProjectFilterVO;
import com.fivesticks.time.customer.FstxTask;
import com.fivesticks.time.customer.FstxTaskFilterParams;
import com.fivesticks.time.customers.FstxCustomerDAO;
import com.fivesticks.time.customers.FstxProjectDAO;
import com.fivesticks.time.customers.FstxTaskDAO;
import com.fivesticks.time.settings.SystemSettings;
import com.fivesticks.time.settings.SystemSettingsDao;
import com.fivesticks.time.settings.SystemSettingsParameters;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemUserServiceDelegate;
import com.fivesticks.time.timeclock.FstxTimeclock;
import com.fivesticks.time.timeclock.FstxTimeclockDAO;
import com.fivesticks.time.timeclock.TimeclockFilterParameters;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoDAO;
import com.fivesticks.time.todo.ToDoSearchParameters;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserDAO;
import com.fstx.stdlib.authen.users.UserFilterParameters;

/**
 * @author Reid
 */
public class MarkAllCurrentContentAsOwnedCommand implements
        TransactionManagerAware {

    public static final String SPRING_BEAN_NAME = "systemUpdateMarkCurrentAsOwnedCommand";

    private PlatformTransactionManager transactionManager;

    private SystemOwner newOwner;

    /**
     *  
     */
    public void execute() throws Exception {

        if (this.getNewOwner() == null) {
            throw new RuntimeException(
                    "You forgot to set the new owner, you bastard.");
        }

        try {
            TransactionTemplate transactionTemplate = new TransactionTemplate(
                    this.getTransactionManager());

            transactionTemplate
                    .setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                public void doInTransactionWithoutResult(
                        TransactionStatus status) {

                    try {
                        //activity
                        markActivity();

                        //calendar
                        markCalendar();

                        //timeclock
                        markTimeClock();

                        //customer
                        markCustomer();

                        //project
                        markProject();

                        //task
                        markTasks();

                        //todo
                        markTodos();

                        //system settings
                        markSystemSettings();

                        //handle users
                        handleUserAssociations();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("mark as owned failed.");
                    }
                }

            });

        } catch (RuntimeException e) {
            throw new Exception(e);
        }

    }

    private void handleUserAssociations() throws Exception {
        UserDAO dao = UserDAO.factory.build();

        Collection allRecords = dao.find(new UserFilterParameters());

        SystemUserServiceDelegate sd = SystemUserServiceDelegate.factory
                .build();

        for (Iterator i = allRecords.iterator(); i.hasNext();) {
            User current = (User) i.next();

            sd.associate(this.getNewOwner(), current, null);

        }
    }

    /**
     *  
     */
    private void markActivity() throws Exception {
        FstxActivityDAO dao = FstxActivityDAO.factory.build();
        Collection allRecords = dao.find(new FstxActivityFilterVO());

        for (Iterator i = allRecords.iterator(); i.hasNext();) {
            FstxActivity current = (FstxActivity) i.next();
            current.setOwnerKey(this.getNewOwner().getKey());
            dao.save(current);
        }
    }

    /**
     *  
     */
    private void markCalendar() {
        FstxCalendarDAO dao = FstxCalendarDAO.factory.build();
        Collection allRecords = dao.find(new FstxCalendarFilterParameters());

        for (Iterator i = allRecords.iterator(); i.hasNext();) {
            FstxCalendar current = (FstxCalendar) i.next();
            current.setOwnerKey(this.getNewOwner().getKey());
            dao.save(current);
        }
    }

    /**
     *  
     */
    private void markTimeClock() throws Exception {
        FstxTimeclockDAO dao = FstxTimeclockDAO.factory.build();
        Collection allRecords = dao.find(new TimeclockFilterParameters());

        for (Iterator i = allRecords.iterator(); i.hasNext();) {
            FstxTimeclock current = (FstxTimeclock) i.next();
            current.setOwnerKey(this.getNewOwner().getKey());
            dao.save(current);
        }
    }

    /**
     *  
     */
    private void markCustomer() throws Exception {
        GenericDAO dao = FstxCustomerDAO.factory.build();
        Collection allRecords = dao.find(new FstxCustomerFilterVO());

        for (Iterator i = allRecords.iterator(); i.hasNext();) {
            FstxCustomer current = (FstxCustomer) i.next();
            current.setOwnerKey(this.getNewOwner().getKey());
            dao.save(current);
        }
    }

    /**
     *  
     */
    private void markProject() throws Exception {
        FstxProjectDAO dao = FstxProjectDAO.factory.build();
        Collection allRecords = dao.find(new FstxProjectFilterVO());

        for (Iterator i = allRecords.iterator(); i.hasNext();) {
            FstxProject current = (FstxProject) i.next();
            current.setOwnerKey(this.getNewOwner().getKey());
            dao.save(current);
        }
    }

    /**
     *  
     */
    private void markTasks() throws Exception {
        FstxTaskDAO dao = FstxTaskDAO.factory.build();
        Collection allRecords = dao.find(new FstxTaskFilterParams());

        for (Iterator i = allRecords.iterator(); i.hasNext();) {
            FstxTask current = (FstxTask) i.next();
            current.setOwnerKey(this.getNewOwner().getKey());
            dao.save(current);
        }
    }

    /**
     *  
     */
    private void markTodos() {
        ToDoDAO dao = ToDoDAO.factory.build();
        Collection allRecords = dao.find(new ToDoSearchParameters());

        for (Iterator i = allRecords.iterator(); i.hasNext();) {
            ToDo current = (ToDo) i.next();
            current.setOwnerKey(this.getNewOwner().getKey());
            dao.save(current);
        }
    }

    /**
     *  
     */
    private void markSystemSettings() {
        SystemSettingsDao dao = SystemSettingsDao.factory.build();
        Collection allRecords = dao.find(new SystemSettingsParameters());

        for (Iterator i = allRecords.iterator(); i.hasNext();) {
            SystemSettings current = (SystemSettings) i.next();
            current.setOwnerKey(this.getNewOwner().getKey());
            dao.save(current);
        }
    }

    /**
     * @return Returns the newOwner.
     */
    public SystemOwner getNewOwner() {
        return newOwner;
    }

    /**
     * @param newOwner
     *            The newOwner to set.
     */
    public void setNewOwner(SystemOwner newOwner) {
        this.newOwner = newOwner;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.common.TransactionManagerAware#setTransactionManager(org.springframework.transaction.PlatformTransactionManager)
     */
    public void setTransactionManager(
            PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * @return Returns the transactionManager.
     */
    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }
}