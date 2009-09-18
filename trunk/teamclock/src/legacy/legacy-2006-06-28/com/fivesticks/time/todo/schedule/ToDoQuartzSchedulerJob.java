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
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateException;

/**
 * @author Nick
 * 
 *  
 */
public class ToDoQuartzSchedulerJob implements Job {

    private ToDoScheduleServiceDelegate todoScheduleServiceDelegate;

    private ToDoServiceDelegate todoServiceDelegate;

    public static String SPRING_BEAN_NAME = "todoQuartzScheduler";

    /*
     * (non-Javadoc)
     * 
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    // public void schedule(CmmsSessionContext cmmsSessionContext) {
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        /*
         * - Get all schedules with init dated before today. - Copy the basis
         * object, - increment all schedule
         */
        Collection schedules = null;
        try {
            schedules = getAllSchedulesThatAreDue();
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
        /*
         * For each schedule, make a copy of the basis work order, and increment
         * the initiation date.
         */
        for (Iterator i = schedules.iterator(); i.hasNext();) {
            ToDoSchedule todoSchedule = (ToDoSchedule) i.next();
            try {
                this.handleSchedule(todoSchedule);
            } catch (Exception e) {
                throw new JobExecutionException(e);
            }
            /*
             * Now that we have created all of the workorders for this schedule
             * we have to increment the initdate.
             */
            todoSchedule.incrementInitiationDate();
            try {
                this.getTodoScheduleServiceDelegate().save(todoSchedule);
            } catch (ServiceDelegateException e3) {
                throw new JobExecutionException(e3);

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

            this.getTodoServiceDelegate().save(scheduledTodo);

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

            schedules = this.getTodoScheduleServiceDelegate()
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

    public ToDoScheduleServiceDelegate getTodoScheduleServiceDelegate() {
        return todoScheduleServiceDelegate;
    }

    public void setTodoScheduleServiceDelegate(
            ToDoScheduleServiceDelegate todoScheduleServiceDelegate) {
        this.todoScheduleServiceDelegate = todoScheduleServiceDelegate;
    }

    private Log log = LogFactory.getLog(ToDoQuartzSchedulerJob.class);

    public ToDoServiceDelegate getTodoServiceDelegate() {
        return todoServiceDelegate;
    }

    public void setTodoServiceDelegate(ToDoServiceDelegate todoServiceDelegate) {
        this.todoServiceDelegate = todoServiceDelegate;
    }
}