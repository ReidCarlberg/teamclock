/*
 * Created on Sep 2, 2004
 *  
 */
package com.fivesticks.time.calendar.xwork;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.calendar.CalendarBD;
import com.fivesticks.time.calendar.CalendarBDFactory;
import com.fivesticks.time.calendar.CalendarDeleteCommand;
import com.fivesticks.time.calendar.ScheduleTypeEnum;
import com.fivesticks.time.calendar.TcCalendar;
import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fivesticks.time.useradmin.xwork.UserListBroker;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class CalendarModifyAction extends SessionContextAwareSupport implements
        CalendarModifyContextAware, CalendarListContextAware {

    //    private SessionContext sessionContext;

    private CalendarListContext calendarListContext;

    private CalendarModifyContext calendarModifyContext;

    private String submitCalendar;

    private String cancelCalendar;

    private String deleteCalendar;

    private Long target;

    private String username;

    private Date startDate;

    private String targetDate;

    private Date endDate;

    private Long project;

    private String description;

    /*
     * 2004-10-29 Current Eliminated since we don't really do anything with it.
     */
    //    private boolean privateView;
    private int startMinutes;

    private int startHours;

    private int durationMinutes;

    private int durationHours;

    private Long typeLuId;

    private Collection startHoursSelection;

    private Collection startMinutesSelection;

    private Collection durationHoursSelection;

    private Collection durationMinutesSelection;

    //    private Collection projects;

    public String execute() throws Exception {

        if (this.getCancelCalendar() != null) {
            return getDestination();
        }

        CalendarBD calendarBD = CalendarBDFactory.factory.build(this
                .getSessionContext());

        // get all users
        //        FstxProjectBD projectBD = FstxProjectBD.factory.build(this
        //                .getSessionContext());
        //        projects = projectBD.getAllActive();

        if (this.getDeleteCalendar() != null) {

            CalendarDeleteCommand dc = new CalendarDeleteCommand(this
                    .getCalendarModifyContext().getTargetCalendar());
            DeleteContext dcontext = DeleteContextFactory.factory.build(dc, this
                    .getSessionContext().getSuccessOverride());
            this.getSessionContext().setDeleteContext(dcontext);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;

        }

        if (this.getSubmitCalendar() == null) {
            this.getCalendarModifyContext().setTargetCalendar(null);
            if (this.getTarget() != null) {
                TcCalendar calendarTarget = calendarBD.get(this.getTarget());
                if (calendarTarget != null) {
                    this.setUsername(calendarTarget.getUsername());
                    this.setProject(calendarTarget.getProjectId());
                    this.setStartDate(calendarTarget.getStartDate());
                    this.setDescription(calendarTarget.getDescription());

                    SimpleDate sDate = SimpleDate.factory.build(calendarTarget
                            .getStartDate());
                    SimpleDate eDate = SimpleDate.factory.build(calendarTarget
                            .getEndDate());

                    int hourInt = eDate.getMinutesBetween(sDate);

                    this.setDurationHours(sDate.getMinutesBetween(eDate) / 60);
                    this
                            .setDurationMinutes(sDate.getMinutesBetween(eDate) % 60);

                    this.setStartHours(sDate.getCalendar().get(
                            Calendar.HOUR_OF_DAY));
                    this.setStartMinutes(sDate.getCalendar().get(
                            Calendar.MINUTE));

                    this.getCalendarModifyContext().setTargetCalendar(
                            calendarTarget);
                    this.setTypeLuId(calendarTarget.getType());
                }
            } else {
                SimpleDate simpleDate = SimpleDate.factory.buildMidnight(this
                        .getTargetDate());
                this.setStartDate(simpleDate.getDate());
                this.setUsername(this.getSessionContext().getUser()
                        .getUsername());
            }
            return INPUT;
        }

        /*
         * validate
         */
        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        if (this.getCalendarModifyContext().getTargetCalendar() != null) {

            TcCalendar calendar = this.getCalendarModifyContext()
                    .getTargetCalendar();

            this.save(calendar);

        } else {
            TcCalendar fc = new TcCalendar();
            this.save(fc);
        }

        return getDestination();
    }

    private String getDestination() {
        String ret = SUCCESS;

        if (this.getSessionContext().getSuccessOverride() != null) {
            ret = this.getSessionContext().getSuccessOverride();
        } else if (this.getCalendarListContext().getScheduleTypeEnum() == ScheduleTypeEnum.WEEKLY)
            ret = SUCCESS + ".week";
        else if (this.getCalendarListContext().getScheduleTypeEnum() == ScheduleTypeEnum.MONTHLY)
            ret = SUCCESS + ".month";

        return ret;
    }

    public void validate() {
        ValidationHelper help = new ValidationHelper();

        try {
            if (this.getSessionContext().getUserTypeEnum() != UserTypeEnum.STANDARD
                    && help.isStringEmpty(this.getUsername())) {
                this.addFieldError("username", "You must enter a username.");
            }
        } catch (Exception e) {
            //do nothing..
            e.printStackTrace();
        }

        if (this.getDurationHours() == 0 && this.getDurationMinutes() == 0) {
            this.addFieldError("durationHours", "You must enter a duration.");
        }

        if (help.isStringEmpty(this.getDescription())) {
            this.addFieldError("description", "You must enter a description.");
        }
    }

    private void save(TcCalendar target) throws Exception {
        CalendarBD calendarBD = CalendarBDFactory.factory.build(this
                .getSessionContext());

        if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD) {
            target
                    .setUsername(this.getSessionContext().getUser()
                            .getUsername());
            target.setPublicViewable(true);
        } else {
            target.setUsername(this.getUsername());
            target.setPublicViewable(true); //2004-10-29 always true since we
            // don't need private.
        }

        SimpleDate start = SimpleDate.factory.build(this.getStartDate());
        start.setHours(this.getStartHours());
        start.setMinutes(this.getStartMinutes());
        target.setStartDate(start.getDate());

        SimpleDate end = start;
        end.advanceHour(this.getDurationHours());
        end.advanceMinute(this.getDurationMinutes());
        target.setEndDate(end.getDate());

        target.setProjectId(this.getProject());
        target.setDescription(this.getDescription());

        target.setType(this.getTypeLuId());
        try {
            calendarBD.save(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @return Returns the delete.
     */
    public String getDeleteCalendar() {
        return deleteCalendar;
    }

    /**
     * @param delete
     *            The delete to set.
     */
    public void setDeleteCalendar(String delete) {
        this.deleteCalendar = delete;
    }

    /**
     * @return Returns the cancel.
     */
    public String getCancelCalendar() {
        return cancelCalendar;
    }

    /**
     * @param cancel
     *            The cancel to set.
     */
    public void setCancelCalendar(String cancel) {
        this.cancelCalendar = cancel;
    }

    /**
     * @return Returns the startDateTest.
     */
    public String getTargetDate() {
        return targetDate;
    }

    /**
     * @param startDateTest
     *            The startDateTest to set.
     */
    public void setTargetDate(String startDateTest) {
        this.targetDate = startDateTest;
    }

    //    public SessionContext getSessionContext() {
    //        return sessionContext;
    //    }
    //
    //    public void setSessionContext(SessionContext sessionContext) {
    //        this.sessionContext = sessionContext;
    //    }

    public String getSubmitCalendar() {
        return submitCalendar;
    }

    public void setSubmitCalendar(String submit) {
        this.submitCalendar = submit;
    }

    public Collection getUsers() throws Exception {
        //        return SystemUserServiceDelegate.factory.build().list(
        //                this.getSessionContext().getSystemOwner());
        return UserListBroker.singleton.getUserList(this.getSessionContext()
                .getSystemOwner());
    }

    /**
     * @return Returns the durationHours.
     */
    public int getDurationHours() {
        return durationHours;
    }

    /**
     * @param durationHours
     *            The durationHours to set.
     */
    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    /**
     * @return Returns the durationMinutes.
     */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    /**
     * @param durationMinutes
     *            The durationMinutes to set.
     */
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    /**
     * @return Returns the startHours.
     */
    public int getStartHours() {
        return startHours;
    }

    /**
     * @param startHours
     *            The startHours to set.
     */
    public void setStartHours(int startHours) {
        this.startHours = startHours;
    }

    /**
     * @return Returns the startMinutes.
     */
    public int getStartMinutes() {
        return startMinutes;
    }

    /**
     * @param startMinutes
     *            The startMinutes to set.
     */
    public void setStartMinutes(int startMinutes) {
        this.startMinutes = startMinutes;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    //    /**
    //     * @return Returns the projects.
    //     */
    //    public Collection getProjects() {
    //        return projects;
    //    }
    //
    //    /**
    //     * @param projects
    //     * The projects to set.
    //     */
    //    public void setProjects(Collection projects) {
    //        this.projects = projects;
    //    }

    /**
     * @return Returns the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            The startDate to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return Returns the endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            The endDate to set.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the project.
     */
    public Long getProject() {
        return project;
    }

    /**
     * @param project
     *            The project to set.
     */
    public void setProject(Long project) {
        this.project = project;
    }

    //    /**
    //     * @return Returns the publicView.
    //     */
    //    public boolean isPrivateView() {
    //        return privateView;
    //    }
    //
    //    /**
    //     * @param publicView
    //     * The publicView to set.
    //     */
    //    public void setPrivateView(boolean privateView) {
    //        this.privateView = privateView;
    //    }

    /**
     * @return Returns the startHoursSelection.
     */
    public Collection getStartHoursSelection() {
        this.startHoursSelection = new ArrayList();
        int i = 7;
        while (i < 20) {
            SelectionHelper helper = new SelectionHelper();
            helper.setId(i);

            startHoursSelection.add(helper);
            i++;
        }

        return startHoursSelection;
    }

    /**
     * @param startHoursSelection
     *            The startHoursSelection to set.
     */
    public void setStartHoursSelection(Collection startHoursSelection) {
        this.startHoursSelection = startHoursSelection;
    }

    /**
     * @return Returns the startMinutesSelection.
     */
    public Collection getStartMinutesSelection() {
        this.startMinutesSelection = new ArrayList();
        int i = 0;

        while (i < 51) {
            SelectionHelper helper = new SelectionHelper();
            helper.setId(i);

            startMinutesSelection.add(helper);
            i += 10;
        }

        return startMinutesSelection;
    }

    /**
     * @param startMinutesSelection
     *            The startMinutesSelection to set.
     */
    public void setStartMinutesSelection(Collection startMinutesSelection) {
        this.startMinutesSelection = startMinutesSelection;
    }

    /**
     * @return Returns the durationHoursSelection.
     */
    public Collection getDurationHoursSelection() {
        this.durationHoursSelection = new ArrayList();
        int i = 0;
        while (i < 9) {
            SelectionHelper helper = new SelectionHelper();
            helper.setId(i);

            durationHoursSelection.add(helper);
            i++;
        }

        return durationHoursSelection;
    }

    /**
     * @param durationHoursSelection
     *            The durationHoursSelection to set.
     */
    public void setDurationHoursSelection(Collection durationHoursSelection) {
        this.durationHoursSelection = durationHoursSelection;
    }

    /**
     * @return Returns the durationMinutesSelection.
     */
    public Collection getDurationMinutesSelection() {
        this.durationMinutesSelection = new ArrayList();
        int i = 0;

        while (i < 51) {
            SelectionHelper helper = new SelectionHelper();
            helper.setId(i);

            durationMinutesSelection.add(helper);
            i += 10;
        }

        return durationMinutesSelection;
    }

    /**
     * @param durationMinutesSelection
     *            The durationMinutesSelection to set.
     */
    public void setDurationMinutesSelection(Collection durationMinutesSelection) {
        this.durationMinutesSelection = durationMinutesSelection;
    }

    /**
     * @return Returns the calendarListContext.
     */
    public CalendarListContext getCalendarListContext() {
        return calendarListContext;
    }

    /**
     * @param calendarListContext
     *            The calendarListContext to set.
     */
    public void setCalendarListContext(CalendarListContext calendarListContext) {
        this.calendarListContext = calendarListContext;
    }

    /**
     * @return Returns the calendarModifyContext.
     */
    public CalendarModifyContext getCalendarModifyContext() {
        return calendarModifyContext;
    }

    /**
     * @param calendarModifyContext
     *            The calendarModifyContext to set.
     */
    public void setCalendarModifyContext(
            CalendarModifyContext calendarModifyContext) {
        this.calendarModifyContext = calendarModifyContext;
    }

    public Collection getTypes() throws Exception {
        return LookupServiceDelegateFactory.factory.build(this.getSystemOwner())
                .getAll(LookupType.CALENDAR_TYPE);
    }

    /**
     * @return Returns the typeLuId.
     */
    public Long getTypeLuId() {
        return typeLuId;
    }

    /**
     * @param typeLuId
     *            The typeLuId to set.
     */
    public void setTypeLuId(Long typeLuId) {
        this.typeLuId = typeLuId;
    }
}