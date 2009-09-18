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
 * Created on Nov 14, 2003
 *
 */
package com.fivesticks.time.calendar;

import java.util.Date;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 * 
 */
public class ScheduleBuilderMonthlyImpl extends AbstractScheduleBuilderImpl {

    private final Date resolvedNow;
    
    public ScheduleBuilderMonthlyImpl(SystemOwner systemOwner,
            CalendarFilterParameters filter, Date resolvedNow) {
        super(filter);
        this.setSystemOwner(systemOwner);
        this.resolvedNow = resolvedNow;
    }

    protected void handleBuildBins(Schedule schedule, Date startDate,
            Date stopDate) {

        int minutes = 0;

        int binSizeInMinutes = MINUTES_PER_DAY;

        int minInSchedule = getMinutesBetween(startDate, stopDate);
        int hoursTemp = 0;
        int minTemp = 0;
        while (minutes < minInSchedule) {

            hoursTemp = (minutes / 60);
            minTemp = minutes % 60;

            // Get lower bound
            SimpleDate simpleDateLowerBound = SimpleDate.factory
                    .build(startDate);

            simpleDateLowerBound.advanceDay(hoursTemp / 24);
            simpleDateLowerBound.setHours(hoursTemp % 24);
            simpleDateLowerBound.setMinutes(minTemp);

            simpleDateLowerBound.setSeconds(0);

            String label = simpleDateLowerBound.getMmddyyyy() + " " + hoursTemp
                    + ":" + minTemp;

            // Get upperbound
            minutes += binSizeInMinutes;
            hoursTemp = minutes / 60;
            minTemp = minutes % 60;
            SimpleDate simpleDateUpperBound = SimpleDate.factory
                    .build(startDate);
            // simpleDateUpperBound.advanceDay(minutes/MINUTES_PER_DAY );
            simpleDateUpperBound.advanceDay(hoursTemp / 24);
            simpleDateUpperBound.setHours(hoursTemp % 24);
            simpleDateUpperBound.setMinutes(minTemp - 1);
            simpleDateUpperBound.setSeconds(59);
            simpleDateUpperBound.setMilliseconds(999);

            // DailyBin bin = new DailyBin(simpleDateLowerBound.getDate(),
            // simpleDateUpperBound.getDate(), label);

            DailyBin bin = new DailyBin();

            //2006-06-28
            bin.setResolvedNow(this.resolvedNow);
            
            bin.setLowerBound(simpleDateLowerBound.getDate());
            bin.setUpperBound(simpleDateUpperBound.getDate());
            bin.setLabel(label);

            schedule.addBin(bin);

        }

    }

    /**
     * @param startDate
     * @param stopDate
     * @return
     */
    private int getMinutesBetween(Date startDate, Date stopDate) {
        /*
         * using midnights here since that will always work out to the correct #
         * of days.
         */
        SimpleDate simpleStart = SimpleDate.factory.buildMidnight(startDate);
        SimpleDate simpleStop = SimpleDate.factory.buildMidnight(stopDate);
        simpleStop.advanceHour(2);
        return simpleStart.getMinutesBetween(simpleStop);

    }

//    private static int MINUTES_PER_DAY = 60 * 24;
//
////    private static int MINUTES_PER_WEEK = 60 * 24 * 7;

    protected CalendarDisplayWrapperBuilder getDisplayWrapperBuilder() {
        
        return new MonthlyCalendarDisplayWrapperBuilder();
    }

}