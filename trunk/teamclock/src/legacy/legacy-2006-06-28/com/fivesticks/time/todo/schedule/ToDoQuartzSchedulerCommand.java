/*
 * Created on Oct 5, 2004
 *
 * 
 */
package com.fivesticks.time.todo.schedule;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class ToDoQuartzSchedulerCommand {

    private ToDoScheduleServiceDelegate toDoScheduleServiceDelegate;

    private ToDoServiceDelegate toDoServiceDelegate;

    public static String SPRING_BEAN_NAME = "todoQuartzSchedulerCommand";

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    // public void schedule(CmmsSessionContext cmmsSessionContext) {
    public void execute() throws ToDoQuartzSchedulerCommandException {
        /*
         * We need a system owner, but this need to work with all system owners.
         */
        SystemOwner mockSystemOwner = new SystemOwner();
        toDoScheduleServiceDelegate.setSystemOwner(mockSystemOwner);

        /*
         * - Get all schedules with init dated before today. - Copy the basis
         * object, - increment all schedule
         */
        Collection schedules = null;
        try {
            schedules = getAllSchedulesThatAreDue();
        } catch (Exception e) {
            throw new ToDoQuartzSchedulerCommandException(e);
        }
        /*
         * For each schedule, make a copy of the basis work order, and increment
         * the initiation date.
         */
        for (Iterator i = schedules.iterator(); i.hasNext();) {
            ToDoSchedule todoSchedule = (ToDoSchedule) i.next();
            if (todoSchedule.getInitiationDate().before(new Date())) {

                try {
                    this.handleSchedule(todoSchedule);
                } catch (Exception e) {
                    throw new ToDoQuartzSchedulerCommandException(e);
                }
                /*
                 * Now that we have created all of the workorders for this
                 * schedule we have to increment the initdate.
                 */

                todoSchedule.incrementInitiationDate();
                try {
                    this.getToDoScheduleServiceDelegate().save(todoSchedule);
                } catch (ServiceDelegateException e3) {
                    throw new ToDoQuartzSchedulerCommandException(e3);

                }
            }
        }
    }

    private void handleSchedule(ToDoSchedule todoSchedule)
            throws ToDoServiceDelegateException {
        /*
         * We want to run the schedule, is the init Date is before the current
         * time. Should be since we query based on that. But it doesn't hurt to
         * double check.
         */

        if (todoSchedule.getInitiationDate().before(new Date())) {

            ToDo basis = todoSchedule.getToDo();

            ToDo scheduledTodo = null;
            scheduledTodo = (ToDo) basis.clone();
            scheduledTodo.setDeadlineTimestamp(new Date());
            scheduledTodo.setComplete(false);
            scheduledTodo.setId(null);

            SystemOwner so = null;
            try {
                so = SystemOwnerServiceDelegateFactory.factory.build().get(
                        basis.getOwnerKey());
            } catch (SystemOwnerServiceDelegateException e) {

                e.printStackTrace();
            }

            this.getToDoScheduleServiceDelegate().setSystemOwner(so);
            this.setToDoServiceDelegate(ToDoServiceDelegateFactory.factory.build(so));
//            this.getToDoServiceDelegate().setSystemOwner(so);
            this.getToDoServiceDelegate().save(scheduledTodo);

        }
    }

    private Collection getAllSchedulesThatAreDue() throws Exception {
        ToDoScheduleQueryParameters parameters = ToDoScheduleParametersBuilder.singleton
                .findDueForInitiation();

        /*
         * Get all the schedules that are due to run.
         */
        Collection schedules = null;
        try {

            schedules = this.getToDoScheduleServiceDelegate()
                    .search(parameters);

        } catch (ServiceDelegateException e) {
            log.error("Error searching for todo schedules: " + e.getMessage());
            e.printStackTrace();
            throw new Exception("Error searching for todo schedules", e);
        }

        if (schedules == null) {
            return null;
        }

        return schedules;
    }

    public ToDoScheduleServiceDelegate getToDoScheduleServiceDelegate() {
        return toDoScheduleServiceDelegate;
    }

    public void setToDoScheduleServiceDelegate(
            ToDoScheduleServiceDelegate todoScheduleServiceDelegate) {
        this.toDoScheduleServiceDelegate = todoScheduleServiceDelegate;
    }

    private Log log = LogFactory.getLog(ToDoQuartzSchedulerCommand.class);

    public ToDoServiceDelegate getToDoServiceDelegate() {
        return toDoServiceDelegate;
    }

    public void setToDoServiceDelegate(ToDoServiceDelegate todoServiceDelegate) {
        this.toDoServiceDelegate = todoServiceDelegate;
    }
}