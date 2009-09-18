/*
 * Created on Sep 2, 2004
 *  
 */
package com.fivesticks.time.calendar.xwork;

import com.fivesticks.time.calendar.CalendarFilterParameters;
import com.fivesticks.time.calendar.ScheduleTypeEnum;

public class CalendarListContext {

    private CalendarFilterParameters params;

    private ScheduleTypeEnum scheduleTypeEnum;

    /**
     * @return Returns the params.
     */
    public CalendarFilterParameters getParams() {
        return params;
    }

    /**
     * @param params
     *            The params to set.
     */
    public void setParams(CalendarFilterParameters params) {
        this.params = params;
    }

    /**
     * @return Returns the scheduleTypeEnum.
     */
    public ScheduleTypeEnum getScheduleTypeEnum() {
        return scheduleTypeEnum;
    }

    /**
     * @param scheduleTypeEnum
     *            The scheduleTypeEnum to set.
     */
    public void setScheduleTypeEnum(ScheduleTypeEnum scheduleTypeEnum) {
        this.scheduleTypeEnum = scheduleTypeEnum;
    }
}