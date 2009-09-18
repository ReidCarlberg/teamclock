/*
 * Created on Oct 22, 2004 by Reid
 */
package com.fivesticks.time.calendar.xwork;

import com.fivesticks.time.calendar.ScheduleTypeEnum;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.opensymphony.xwork.ActionSupport;

/**
 * Determines where the display should go after success.
 * 
 * @author Reid
 */
public class CalendarDeleteSuccessAction extends ActionSupport implements
        SessionContextAware, CalendarListContextAware {

    private SessionContext sessionContext;

    private CalendarListContext calendarListContext;

    public String execute() throws Exception {
        String ret = SUCCESS;

        if (this.getCalendarListContext().getScheduleTypeEnum() == ScheduleTypeEnum.WEEKLY)
            ret = SUCCESS + ".week";
        else if (this.getCalendarListContext().getScheduleTypeEnum() == ScheduleTypeEnum.MONTHLY)
            ret = SUCCESS + ".month";

        return ret;
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
}