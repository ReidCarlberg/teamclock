/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.xwork;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.CommonPrompts;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.TodoDeleteCommand;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *  
 */
public class ModifyAction extends SessionContextAwareSupport implements 
        TodoModifyContextAware {

    private TodoModifyContext todoModifyContext;

//    private Collection projects;

//    private Collection booleanSurrogate;

    private ToDo targetToDo = new ToDo();

    private ToDoScheduleBean targetToDoSchedule = new ToDoScheduleBean();

    private Long target;

    private String submitTodo;

    private String cancelTodo;

    private String deleteTodo;

//    private SessionContext sessionContext;

    public String execute() throws Exception {

        if (this.getCancelTodo() != null) {
            return getSuccessDestination();
        }

        if (this.getDeleteTodo() != null) {
            if (this.getTodoModifyContext().getTarget() == null)
                return INPUT;
            else {
                TodoDeleteCommand dc = new TodoDeleteCommand(this
                        .getTodoModifyContext().getTarget());
                this.getSessionContext().setDeleteContext(
                        DeleteContextFactory.factory.build(dc, this
                                .getSessionContext().getSuccessOverride()));
                return GlobalForwardStatics.GLOBAL_COMMON_DELETE;
            }
        }

        if (this.getSubmitTodo() == null) {

            if (this.getTarget() != null) {
                ToDo temp = ToDoServiceDelegateFactory.factory.build(
                        this.getSessionContext()).get(
                        this.getTarget());
                this.getTodoModifyContext().setTarget(temp);
                targetToDo = temp;
            } else {
                //                SessionContext context = (SessionContext) ActionContext
                //                        .getContext().getSession().get(SessionContext.KEY);
                targetToDo.setAssignedToUser(this.getSessionContext().getUser()
                        .getUsername());
                targetToDo.setEnteredByUser(this.getSessionContext().getUser()
                        .getUsername());
                targetToDo.setDeadlineTimestamp(SimpleDate.factory
                        .buildMidnight().getDate());
            }

            /*
             * 2006-07-05 reid@fivesticks.com
             */
//            if (targetToDo.getSchedules().size() != 0) {
//                ToDoSchedule sched1 = (ToDoSchedule) targetToDo.getSchedules()
//                        .toArray()[0];
//
//                this.getTargetToDoSchedule().setDescription(
//                        sched1.getDescription());
//                //sched.setFrequency(this.getTargetToDoSchedule().getFrequency());
//                this.getTargetToDoSchedule()
//                        .setFrequency(sched1.getFrequency());
//
//                this.getTargetToDoSchedule().setInitiationDate(
//                        sched1.getInitiationDate());
//                this.getTargetToDoSchedule().setMultiplier(
//                        new Integer(sched1.getMultiplier()));
//                this.getTargetToDoSchedule().setRecurring(true);
//
//            }

            return INPUT;
        }

        /*
         * validate
         */

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        /*
         * deal with the date field.
         */
        if (targetToDo.getDeadlineTimestamp() == null) {
            targetToDo.setDeadlineTimestamp(SimpleDate.factory.buildMidnight()
                    .getDate());
        }

        if (this.getTodoModifyContext().getTarget() != null) {

            targetToDo.setId(this.getTodoModifyContext().getTarget().getId());

            targetToDo.setCreateTimestamp(this.getTodoModifyContext()
                    .getTarget().getCreateTimestamp());

            targetToDo.setSchedules(this.getTodoModifyContext().getTarget()
                    .getSchedules());
            this.getTodoModifyContext().setTarget(null);

        } else {
            targetToDo.setCreateTimestamp(new Date());

            targetToDo.setComplete(false);

        }

        /*
         * figure out security
         */

        if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD) {
            if (targetToDo.getEnteredByUser() == null) {
                targetToDo.setEnteredByUser(this.getSessionContext().getUser()
                        .getUsername());
            }
            targetToDo.setAssignedToUser(this.getSessionContext().getUser()
                    .getUsername());
        }
        
        /*
         * 2006-07-05
         */

//        if (this.getTargetToDoSchedule().isRecurring()) {
//            ToDoSchedule sched = null;
//            if (targetToDo.getSchedules().size() != 0) {
//                sched = (ToDoSchedule) targetToDo.getSchedules().toArray()[0];
//            } else {
//                sched = new ToDoSchedule();
//            }
//
//            sched.setDescription(this.getTargetToDoSchedule().getDescription());
//            //sched.setFrequency(this.getTargetToDoSchedule().getFrequency());
//            sched.setFrequency(ScheduleFrequencyEnum.getByName(
//                    this.getTargetToDoSchedule().getFrequency()).getName());
//            sched.setInitiationDate(this.getTargetToDoSchedule()
//                    .getInitiationDate());
//            if (this.getTargetToDoSchedule().getMultiplier() != null) {
//                sched.setMultiplier(this.getTargetToDoSchedule()
//                        .getMultiplier().intValue());
//            } else {
//                sched.setMultiplier(1);
//            }
//            //  sched.setOwnerKey(this.getTargetToDo().getOwnerKey());
//            sched.setToDo(this.getTargetToDo());
//            ToDoScheduleServiceDelegateFactory.factory.build(
//                    this.getSessionContext().getSystemOwner()).save(sched);
//            targetToDo.getSchedules().add(sched);
//        }

        ToDoServiceDelegateFactory.factory.build(
                this.getSessionContext()).save(targetToDo);
        this.getTodoModifyContext().setTarget(targetToDo);

        return getSuccessDestination();
    }

    private String getSuccessDestination() {
        String ret = SUCCESS;

        if (this.getSessionContext().getSuccessOverride() != null) {
            ret = this.getSessionContext().getSuccessOverride();
        }

        return ret;
    }

    public void validate() {
        ValidationHelper help = new ValidationHelper();

        if (this.getTargetToDo().getProjectId() == null
                || this.getTargetToDo().getProjectId().longValue() == 0) {
            this.addFieldError("targetToDo.projectId",
                    "You must select a project.");
        }

        if (this.getTargetToDoSchedule().isRecurring()
                && this.getTargetToDoSchedule().getFrequency().equals("")) {
            this.addFieldError("targetToDoSchedule.frequency",
                    "You must select a frequency if todo is recurring.");
        }

        try {
            if (this.getSessionContext().getUserTypeEnum() != UserTypeEnum.STANDARD
                    && help.isStringEmpty(this.getTargetToDo()
                            .getAssignedToUser())) {
                this.addFieldError("targetToDo.assignedToUser",
                        "You must select a value for assigned to.");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        try {
            if (this.getSessionContext().getUserTypeEnum() != UserTypeEnum.STANDARD
                    && help.isStringEmpty(this.getTargetToDo()
                            .getEnteredByUser())) {
                this.addFieldError("targetToDo.enteredByUser",
                        "You must select a value for entered by.");
            }
        } catch (Exception e1) {

            e1.printStackTrace();
        }

        if (help.isStringEmpty(this.getTargetToDo().getDetail())) {
            this.addFieldError("targetToDo.detail",
                    "You must enter some detail.");
        }

    }

//    /**
//     * @return
//     */
//    public Collection getProjects() {
//        try {
//            projects = FstxProjectBD.factory.build(
//                    this.getSessionContext().getSystemOwner()).getAllActive();
//        } catch (FstxProjectBDException e) {
//            throw new RuntimeException("holy crap couldn't net projects!", e);
//        }
//        return projects;
//    }

    /**
     * @return
     */
    public ToDo getTargetToDo() {
        return targetToDo;
    }

    /**
     * @return
     */
    public Collection getUsers() throws Exception {
//        return UserListBroker.singleton.getInternalUserList(this
//                .getSessionContext().getSystemOwner());
        /*
         * 2005-07-02 REID
         */
        return this.getUserListProvider().getInternalUsersAll();
    }

    public Collection getExternalUsers() throws Exception {
//        return UserListBroker.singleton.getExternalUserList(this
//                .getSessionContext().getSystemOwner());
        
        /*
         * 2005-07-02 rsc
         */
        
        return this.getUserListProvider().getExternalUsers();
    }

//    /**
//     * @param collection
//     */
//    public void setProjects(Collection collection) {
//        projects = collection;
//    }

    /**
     * @param do1
     */
    public void setTargetToDo(ToDo do1) {
        targetToDo = do1;
    }

    /**
     * @return
     */
    public String getSubmitTodo() {
        return submitTodo;
    }

    /**
     * @param string
     */
    public void setSubmitTodo(String string) {
        submitTodo = string;
    }

    /**
     * @return
     */
    public String getCancelTodo() {
        return cancelTodo;
    }

    /**
     * @param string
     */
    public void setCancelTodo(String string) {
        cancelTodo = string;
    }

    /**
     * @return
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param long1
     */
    public void setTarget(Long long1) {
        target = long1;
    }

    /**
     * @return
     */
    public Collection getBooleanSurrogate() {

        return CommonPrompts.singleton.getBooleanSurrogates();

    }

//    /**
//     * @param collection
//     */
//    public void setBooleanSurrogate(Collection collection) {
//        booleanSurrogate = collection;
//    }

    public void setSurrogateComplete(String string) {
        this.getTargetToDo().setComplete(new Boolean(string).booleanValue());
    }

    public String getSurrogateComplete() {
        return new Boolean(this.getTargetToDo().isComplete()).toString();
    }

//    /**
//     * @return Returns the sessionContext.
//     */
//    public SessionContext getSessionContext() {
//        return sessionContext;
//    }
//
//    /**
//     * @param sessionContext
//     *            The sessionContext to set.
//     */
//    public void setSessionContext(SessionContext sessionContext) {
//        this.sessionContext = sessionContext;
//    }

    /**
     * @return Returns the todoModifyContext.
     */
    public TodoModifyContext getTodoModifyContext() {
        return todoModifyContext;
    }

    /**
     * @param todoModifyContext
     *            The todoModifyContext to set.
     */
    public void setTodoModifyContext(TodoModifyContext todoModifyContext) {
        this.todoModifyContext = todoModifyContext;
    }

    /**
     * @return Returns the deleteTodo.
     */
    public String getDeleteTodo() {
        return deleteTodo;
    }

    /**
     * @param deleteTodo
     *            The deleteTodo to set.
     */
    public void setDeleteTodo(String deleteTodo) {
        this.deleteTodo = deleteTodo;
    }

    public Collection getAllTypes() {
        return ToDoPriorityTypeEnum.getAll();
    }

    public ToDoScheduleBean getTargetToDoSchedule() {
        return targetToDoSchedule;
    }

    public void setTargetToDoSchedule(ToDoScheduleBean targetToDoSchedule) {
        this.targetToDoSchedule = targetToDoSchedule;
    }
}