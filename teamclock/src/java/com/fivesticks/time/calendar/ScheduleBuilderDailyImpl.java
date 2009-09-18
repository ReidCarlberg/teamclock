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

import java.util.Calendar;
import java.util.Date;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 * 
 */
public class ScheduleBuilderDailyImpl extends AbstractScheduleBuilderImpl {

    private int binSizeInMinutes;
    
    private long calendarNormalDayStarts;
    private long calendarNormalDayEnds;

    public ScheduleBuilderDailyImpl(SystemOwner systemOwner,
            CalendarFilterParameters filter, int minutes, long starts, long ends) {
        super(filter);
        this.setSystemOwner(systemOwner);
        this.binSizeInMinutes = minutes;
        this.calendarNormalDayStarts = starts;
        this.calendarNormalDayEnds = ends;
    }

    /*
     * For Daily we don't need the stop date. (non-Javadoc)
     * 
     * @see com.fstx.time.calendar.AbstractScheduleBuilderImpl#handleBuildBins(com.fstx.time.calendar.Schedule,
     *      java.util.Date, java.util.Date)
     */
    protected void handleBuildBins(Schedule schedule, Date date, Date dateStop) {
        int minutes = 0;

        int hoursTemp = 0;
        int minTemp = 0;
        while (minutes < MINUTES_PER_DAY) {

            hoursTemp = minutes / 60;
            minTemp = minutes % 60;
            String label = hoursTemp + ":" + minTemp;
            // Get lower bound
            SimpleDate simpleDateLowerBound = SimpleDate.factory.build(date);
//            simpleDateLowerBound = TimezoneDateTimeResolver.resolve(
//                    simpleDateLowerBound, this.getTimeZone());

            simpleDateLowerBound.setHours(hoursTemp);
            simpleDateLowerBound.setMinutes(minTemp);
            simpleDateLowerBound.setSeconds(0);

            // Get upperbound
            minutes += this.binSizeInMinutes;
            hoursTemp = minutes / 60;
            minTemp = minutes % 60;
            SimpleDate simpleDateUpperBound = SimpleDate.factory.build(date);
         
            
            simpleDateUpperBound.setHours(hoursTemp);
            simpleDateUpperBound.setMinutes(minTemp - 1);
            simpleDateUpperBound.setSeconds(0);

            // DailyBin bin = new DailyBin(simpleDateLowerBound.getDate(),
            // simpleDateUpperBound.getDate(), label);

            DailyBin bin = new DailyBin();

            bin.setLowerBound(simpleDateLowerBound.getDate());
            bin.setUpperBound(simpleDateUpperBound.getDate());
            bin.setLabel(label);

            //System.out.print("start " + simpleDateLowerBound.getCalendar().get(Calendar.HOUR_OF_DAY) + "(" + this.calendarNormalDayStarts + ")");
            //System.out.print("end " + simpleDateUpperBound.getCalendar().get(Calendar.HOUR_OF_DAY)+ "(" + this.calendarNormalDayEnds + ")");
            
          if (simpleDateLowerBound.getCalendar().get(Calendar.HOUR_OF_DAY) >= this.calendarNormalDayStarts
          && simpleDateUpperBound.getCalendar().get(Calendar.HOUR_OF_DAY) < this.calendarNormalDayEnds) {

            schedule.addBin(bin);
          } else {
              //System.out.println("abnormal");
          }
        }

    }



//    private static int MINUTES_PER_DAY = 60 * 24;

    protected CalendarDisplayWrapperBuilder getDisplayWrapperBuilder() {
        
        return new DailyCalendarDisplayWrapperBuilder();
    }

}