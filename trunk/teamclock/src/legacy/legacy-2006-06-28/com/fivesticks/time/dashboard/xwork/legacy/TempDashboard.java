/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.dashboard.xwork.legacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import com.fivesticks.time.calendar.Schedule;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.customer.FstxProject;
import com.fivesticks.time.dashboard.xwork.DashboardContext;
import com.fivesticks.time.dashboard.xwork.DashboardContextAware;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.todo.ToDoCriteriaParameters;
import com.fivesticks.time.todo.ToDoDisplayBuilder;
import com.fivesticks.time.todo.ToDoFilterBuilder;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateException;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.legacy.ToDoTagListBuilder;
import com.fivesticks.time.todo.queue.QueueServiceDelegateException;
import com.fivesticks.time.todo.queue.QueueServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.settings.UserSettingVO;
import com.fivesticks.time.useradmin.xwork.UserListBroker;

/**
 * @author Reid
 * 
 */
public class TempDashboard extends SessionContextAwareSupport implements
        DashboardContextAware {

//    private Log log = LogFactory.getLog(ViewDashboard.class);

    private Collection myIncompleteToDos;

    private Collection delegatedToDos;

//    private FstxActivityWrapperArrayList myActivity;

    private Schedule schedule;

//    private UserSettingServiceDelegate userSettingServiceDelegate;
//
//    private UserSettingVO userSettingVO;

    private DashboardContext dashboardContext;

    private Collection timeclockStatus;

    public String execute() throws Exception {
        // System.out.println("HIsss");
        String toDoUsername = this.getSessionContext().getUser().getUsername();
        if (this.getDashboardContext().getToDoUsername() == null)
            this.getDashboardContext().setToDoUsername(toDoUsername);

        // todos
        ToDoServiceDelegate todosd = ToDoServiceDelegateFactory.factory.build(this
                .getSessionContext().getSystemOwner());

        if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD) {
            this.getDashboardContext().setToDoUsername(toDoUsername);
        }

        this.getDashboardContext().getTodoFilter().setReturnMaximum(249);

        Collection rawToDos = todosd.find(this.getDashboardContext()
                .getTodoFilter());

        this.setMyIncompleteToDos(new ToDoDisplayBuilder(this
                .getSessionContext().getSystemOwner()).build(rawToDos));
        
        this.getSessionContext().setMenuSection(MenuSection.HOME);

        /*
         * If the use has not explicitly told the system what to do,
         * we should set it to the default.
         */
       if(!this.getDashboardContext().isShowingTimeClockStatusExplicitlySet())
       {
           if(this.getUserSettingVO().isDefaultToShowingTimeClockStatus())
           {
               this.getDashboardContext().setShowingTimeClockStatus(true);
               
           }  
       }
        
        /*
         * Set up default view, in none selected.
         */
        if (this.getDashboardContext().getCalendarDisplayType() == null) {
            this.getDashboardContext().setCalendarDisplayType(
                    this.getUserSettingVO().getCalendarDefaultDashboardView());

        }



        // activity
        if (this.getDashboardContext().getActivityUsername() == null) {
            this.getDashboardContext().setActivityUsername(
                    this.getSessionContext().getUser().getUsername());
        }

        // this.setMyActivity(new FstxActivityWrapperArrayListBuilder()
        // .buildFxtxTimeWrapperArrayList(FstxActivityBD.factory.build(
        // this.getSessionContext()).getTodaysActivityForUser(
        // this.getDashboardContext().getActivityUsername()), this
        // .getSessionContext()));

        // calendar

        if (this.getDashboardContext().getCalendarUsername() == null)
            this.getDashboardContext().setCalendarUsername(
                    this.getSessionContext().getUser().getUsername());



        this.getSessionContext().setSuccessOverride(
                GlobalForwardStatics.GLOBAL_DASHBOARD);

        return SUCCESS;
    }

//    /**
//     * @return
//     */
//    public FstxActivityWrapperArrayList getMyActivity() {
//        return myActivity;
//    }

    /**
     * @return
     */
    public Collection getMyIncompleteToDos() {
        return myIncompleteToDos;
    }

//    /**
//     * @param collection
//     */
//    public void setMyActivity(FstxActivityWrapperArrayList collection) {
//        myActivity = collection;
//    }

    /**
     * @param collection
     */
    public void setMyIncompleteToDos(Collection collection) {
        myIncompleteToDos = collection;
    }

    /**
     * @return
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * @param schedule
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * @return Returns the delegatedToDos.
     */
    public Collection getDelegatedToDos() {
        return delegatedToDos;
    }

    /**
     * @param delegatedToDos
     *            The delegatedToDos to set.
     */
    public void setDelegatedToDos(Collection delegatedToDos) {
        this.delegatedToDos = delegatedToDos;
    }

    /**
     * @return Returns the dashboardContext.
     */
    public DashboardContext getDashboardContext() {
        return dashboardContext;
    }

    /**
     * @param dashboardContext
     *            The dashboardContext to set.
     */
    public void setDashboardContext(DashboardContext dashboardContext) {
        this.dashboardContext = dashboardContext;
    }

    public Collection getActiveUsers() throws Exception {
        return UserListBroker.singleton.getActiveUserList(this
                .getSessionContext().getSystemOwner());
    }

    public Collection getProjects() throws Exception {
        Collection ret = new ArrayList();

        // ret =
        // FstxProjectBD.factory.build(this.getSessionContext().getSystemOwner()).getAllActive();

        FstxProject p = new FstxProject();
        p.setId(new Long(-1));
        p.setShortName("[ALL]");

        ret.add(p);
        ret.addAll(super.getProjects());

        return ret;
    }

    /*
     * Grab a list off all the tags currently in incomplete todos for the given
     * system..
     */
    public Collection getTags() {
        Set set = new TreeSet();
        Collection todos = null;
        try {
            ToDoServiceDelegate todosd = ToDoServiceDelegateFactory.factory.build(this
                    .getSessionContext().getSystemOwner());

            ToDoCriteriaParameters filter = new ToDoFilterBuilder()
                    .buildIncompleteByAssigneeWithTags(null, this.getDashboardContext().getPriority(), "true", new Boolean(true), null);
            filter.setReturnMaximum(249);
            todos = todosd.find(filter);

        } catch (ToDoServiceDelegateException e) {

            e.printStackTrace();
        }

//        for (Iterator iter = todos.iterator(); iter.hasNext();) {
//            ToDo todo = (ToDo) iter.next();
//
//            for (Iterator iterator = todo.getTags().iterator(); iterator
//                    .hasNext();) {
//                String element = (String) iterator.next();
//
//                set.add(element);
//            }
//            // set.addAll(todo.getTags());
//        }

        set.addAll(new ToDoTagListBuilder().getTags(todos));
        
        return set;

    }

    public Collection getToDoPriorities() {
        return ToDoPriorityTypeEnum.getAll();
    }

    /**
     * @return Returns the timeclockStatus.
     */
    public Collection getTimeclockStatus() {
        return timeclockStatus;
    }

    /**
     * @param timeclockStatus
     *            The timeclockStatus to set.
     */
    public void setTimeclockStatus(Collection timeclockStatus) {
        this.timeclockStatus = timeclockStatus;
    }

    public Collection getQueuedTodos() throws QueueServiceDelegateException {
        Collection raw = QueueServiceDelegateFactory.factory.build(
                this.getSessionContext().getSystemOwner()).getAll();

        return new ToDoDisplayBuilder(this.getSessionContext().getSystemOwner())
                .build(raw);
    }

//    public UserSettingServiceDelegate getUserSettingServiceDelegate() {
//        if (this.userSettingServiceDelegate == null) {
//            this.userSettingServiceDelegate = UserSettingServiceDelegateFactory.factory
//                    .build(this.getSystemOwner());
//        }
//
//        return this.userSettingServiceDelegate;
//    }
//
//    public void setUserSettingServiceDelegate(
//            UserSettingServiceDelegate userSettingServiceDelegate) {
//        this.userSettingServiceDelegate = userSettingServiceDelegate;
//    }

    public UserSettingVO getUserSettingVO() {
//        if (this.userSettingVO == null) {
//            this.userSettingVO = this.getUserSettingServiceDelegate().find(
//                    this.getSessionContext().getUser().getUser());
//
//        }
//
//        return userSettingVO;
        
        return this.getSessionContext().getUserSettings();
    }

//    public void setUserSettingVO(UserSettingVO userSettingVO) {
//        this.userSettingVO = userSettingVO;
//    }

}