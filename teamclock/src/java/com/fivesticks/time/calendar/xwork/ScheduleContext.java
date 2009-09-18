/*

 Five Sticks
 6957 W. North Ave., #202
 Chicago, IL 60302
 USA
 http://www.fivesticks.com
 mailto:info@fivesticks.com

 Copyright (c) 2003-2004, Five Sticks Publications, Inc.
 All rights reserved.

 Redistribution and use in source and binary forms, 
 with or without modification, are permitted provided
 that the following conditions are met:

 * Redistributions of source code must retain 
 the above copyright notice, this list of conditions 
 and the following disclaimer.
 * Redistributions in binary form must reproduce 
 the above copyright notice, this list of conditions 
 and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of the Five Sticks Publications, Inc.,
 nor the names of its contributors may be used to 
 endorse or promote products derived from this software 
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 SUCH DAMAGE.

 license: http://www.opensource.org/licenses/bsd-license.php

 This software uses a variety of Open Source software as
 a foundation.  See the file 

 [your install]/WEB-INF/component-acknowledgement.txt
 
 For more information.
 */
/*
 * Created on Dec 16, 2003
 *
 */
package com.fivesticks.time.calendar.xwork;

import java.text.SimpleDateFormat;

import com.fivesticks.time.calendar.CalendarFilterParameters;
import com.fivesticks.time.calendar.ScheduleTypeEnum;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 *  
 */
public class ScheduleContext {

    public static final String KEY = "context.schedule";

    private ScheduleTypeEnum viewType;

    private String forwardName;

    private CalendarFilterParameters filter;

    /*
     * This is the date that the schedule is based on. ie If a daily schedule it
     * will be that day if weekly, then it is the Sun - Sat week that includes
     * the base. This allows use to change view while focusing on the same date.
     */
    private SimpleDate baseDate;

    /**
     * @return
     */
    public CalendarFilterParameters getFilter() {
        return filter;
    }

    /**
     * @return
     */
    public String getForwardName() {
        return forwardName;
    }

    /**
     * @return
     */
    public ScheduleTypeEnum getViewType() {
        return viewType;
    }

    /**
     * @param parameters
     */
    public void setFilter(CalendarFilterParameters parameters) {
        filter = parameters;
    }

    /**
     * @param string
     */
    public void setForwardName(String string) {
        forwardName = string;
    }

    /**
     * @param enum
     */
    public void setViewType(ScheduleTypeEnum enumCurrent) {
        viewType = enumCurrent;
    }

    public String getStartDate() {
        return filter.getStart().getMmddyyyy();
    }

    public String getStartDateMinusOneDay() {
        SimpleDate start = SimpleDate.factory
                .build(filter.getStart().getDate());
        start.advanceDay(-1);
        return start.getMmddyyyy();
    }

    public String getStartDatePlusOneDay() {
        SimpleDate start = SimpleDate.factory
                .build(filter.getStart().getDate());
        start.advanceDay(1);
        return start.getMmddyyyy();
    }

    /**
     * @return
     */
    public SimpleDate getBaseDate() {
        return baseDate;
    }

    /**
     * @param date
     */
    public void setBaseDate(SimpleDate date) {
        baseDate = date;
    }

    public String getBaseDateMinusOneWeek() {
        String ret = "";
        SimpleDate date = SimpleDate.factory.build(baseDate.getDate());
        date.advanceWeekOfYear(-1);
        ret = date.getMmddyyyy();
        return ret;
    }

    public String getBaseDatePlusOneWeek() {
        String ret = "";
        SimpleDate date = SimpleDate.factory.build(baseDate.getDate());
        date.advanceWeekOfYear(1);
        ret = date.getMmddyyyy();
        return ret;
    }

    public String getBaseDateMinusOneMonth() {
        String ret = "";
        SimpleDate date = SimpleDate.factory.build(baseDate.getDate());
        date.advanceMonth(-1);
        ret = date.getMmddyyyy();
        return ret;
    }

    public String getBaseDatePlusOneMonth() {
        String ret = "";
        SimpleDate date = SimpleDate.factory.build(baseDate.getDate());
        date.advanceMonth(1);
        ret = date.getMmddyyyy();
        return ret;
    }

    public String getFormattedBaseDate() {
        String ret = "";
        SimpleDate date = SimpleDate.factory.build(baseDate.getDate());

        ret = date.getMmddyyyy();
        return ret;
    }

    public String getMonthName() {
        String ret = "";
        SimpleDateFormat format = new SimpleDateFormat("MMMMM");
        format.format(baseDate.getDate());
        ret = format.format(baseDate.getDate());

        return ret;

    }

}