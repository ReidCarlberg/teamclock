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
package com.fivesticks.time.calendar;

import java.util.Date;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 *  
 */
public class ScheduleBuilderFactory {

    public static final ScheduleBuilderFactory factory = new ScheduleBuilderFactory();

    /**
     * @param parameters
     * @return
     */
    public ScheduleBuilder buildDaily(SystemOwner systemOwner, CalendarFilterParameters parameters,
            int minutes, long calendarNormalDayStarts, long calendarNormalDayEnds) {
        return new ScheduleBuilderDailyImpl(systemOwner, parameters, minutes, calendarNormalDayStarts, calendarNormalDayEnds);
    }

    /**
     * @param filter
     */
    public ScheduleBuilder buildMinute(SystemOwner systemOwner, CalendarFilterParameters parameters) {
        return new ScheduleBuilderMinuteImpl(systemOwner, parameters);
    }

    /**
     * @param parameters
     * @param i
     * @return
     */
    public ScheduleBuilder buildWeekly(SystemOwner systemOwner, CalendarFilterParameters parameters,
            int minutes, long dayStarts, long dayEnds, Date resolvedNow) {
        return new ScheduleBuilderWeeklyImpl(systemOwner, parameters, minutes, dayStarts, dayEnds, resolvedNow, ScheduleBuilder.MINUTES_PER_WEEK);
    }
    
    public ScheduleBuilder buildBiweekly(SystemOwner systemOwner, CalendarFilterParameters parameters,
            int minutes, long dayStarts, long dayEnds, Date resolvedNow) {
        return new ScheduleBuilderWeeklyImpl(systemOwner, parameters, minutes, dayStarts, dayEnds, resolvedNow, ScheduleBuilder.MINUTES_PER_TWOWEEKS);
    }

    /**
     * @param parameters
     * @return
     */
    public ScheduleBuilder buildMonthly(SystemOwner systemOwner, CalendarFilterParameters parameters, Date resolvedNow) {
        return new ScheduleBuilderMonthlyImpl(systemOwner, parameters, resolvedNow);
    }
}