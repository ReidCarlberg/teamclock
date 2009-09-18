/*
 * Created on Sep 2, 2004
 *  
 */
package com.fivesticks.time.admin.xwork;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.TaskServiceDelegate;
import com.fivesticks.time.customer.TaskServiceDelegateFactory;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.useradmin.xwork.UserListBroker;
import com.fstx.stdlib.authen.LoginHistoryBD;
import com.fstx.stdlib.authen.LoginHistoryBDFactory;
import com.fstx.stdlib.authen.LoginHistoryException;
import com.fstx.stdlib.authen.LoginHistoryFilterParameters;
import com.fstx.stdlib.common.simpledate.SimpleDate;
import com.opensymphony.xwork.ActionSupport;

public class LoginHistoryListAction extends ActionSupport implements
        SessionContextAware, LoginHistoryListContextAware {

    private SessionContext sessionContext;

    private Date start;

    private Date stop;

    private String startTimeString;

    private String stopTimeString;

    private String search;

    private LoginHistoryFilterParameters params = new LoginHistoryFilterParameters();

    private LoginHistoryListContext loginHistoryListContext;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        this.getSessionContext().setMenuSection(MenuSection.ADMIN);

        LoginHistoryBD activityBD = LoginHistoryBDFactory.factory.build();

        //SimpleDateFormat dateFormat = new SimpleDateFormat();

        if (this.getSearch() == null) {
            this.setParams(this.getLoginHistoryListContext().getParams());
        }

        //fill the date fields on prams

        if (this.getParams().getDateFrom() == null) {
            SimpleDate start = SimpleDate.factory.buildMidnight();
            //start.advanceDay(-1);

            this.getParams().setDateFrom(start.getDate());
        }

        if (this.getParams().getDateTo() == null) {
            SimpleDate stop = SimpleDate.factory.buildMidnight();
            stop.advanceDay(1);
            this.getParams().setDateTo(stop.getDate());
        }

        /*
         * 2004-12-06 this area could be refactored at some point...
         */
        params.setOwnerKey(this.getSessionContext().getSystemOwner().getKey());

        this.getLoginHistoryListContext().setParams(this.getParams());

        if (!this.getSessionContext().isFeatureSuperUser()) {
            this.getParams().setOwnerKeyForSuperUser(null);
        }

        return SUCCESS;
    }

    public Collection getList() {

        try {
            //this.getParams().setDateFrom();
            Collection col = LoginHistoryBDFactory.factory.build().search(
                    this.getParams());

            return col;
        } catch (LoginHistoryException e) {
            e.printStackTrace();
            return null;
        }

    }

//    private Double calcTotal(Collection c) {
//        Iterator i = c.iterator();
//        FstxActivity fa = null;
//        double tot = 0;
//        while (i.hasNext()) {
//            fa = (FstxActivity) i.next();
//            if (fa.getElapsed() != null) {
//                tot += fa.getElapsed().doubleValue();
//            }
//        }
//
//        return new Double(tot);
//    }

    public LoginHistoryFilterParameters getParams() {
        return params;
    }

    /**
     * @param params
     *            The params to set.
     */
    public void setParams(LoginHistoryFilterParameters params) {
        this.params = params;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String submit) {
        this.search = submit;
    }

    /**
     * @return Returns the projects.
     */
    public Collection getProjects() throws Exception {
        //get all projects
        ProjectServiceDelegate projectBD = ProjectServiceDelegateFactory.factory.build(this
                .getSessionContext());
        //should be getAll()?? shuji sep. 12
        return projectBD.getAllActive();
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * @return Returns the tasks.
     */
    public Collection getTasks() throws Exception {
        TaskServiceDelegate taskBD = TaskServiceDelegateFactory.factory.build(this.getSessionContext());
        return taskBD.getAllTaskTypes();
    }

    /**
     * @return Returns the users.
     */
    public Collection getUsers() throws Exception {
        return UserListBroker.singleton.getUserList(this.getSessionContext()
                .getSystemOwner());
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStop() {
        return stop;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }

    /**
     * @return Returns the startTimeString.
     */
    public String getStartTimeString() {
        return startTimeString;
    }

    /**
     * @param startTimeString
     *            The startTimeString to set.
     */
    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    /**
     * @return Returns the stopTimeString.
     */
    public String getStopTimeString() {
        return stopTimeString;
    }

    /**
     * @param stopTimeString
     *            The stopTimeString to set.
     */
    public void setStopTimeString(String stopTimeString) {
        this.stopTimeString = stopTimeString;
    }

    /**
     * @return Returns the activityListContext.
     */
    public LoginHistoryListContext getLoginHistoryListContext() {
        return loginHistoryListContext;
    }

    /**
     * @param activityListContext
     *            The activityListContext to set.
     */
    public void setLoginHistoryListContext(
            LoginHistoryListContext loginHistoryListContext) {
        this.loginHistoryListContext = loginHistoryListContext;
    }

    public Collection getSystemOwners() throws Exception {
        if (this.getSessionContext().isFeatureSuperUser()) {
            return SystemOwnerServiceDelegateFactory.factory.build().findAll();
        }
        return null;
    }

}