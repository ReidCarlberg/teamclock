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
 * Created on Sep 9, 2003
 *
 */
package com.fivesticks.time.activity.xwork;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Reid
 *  
 */
public class TimeResolver {

    private Date date;

    private String timestring;

    private int resolvedHours;

    private int resolvedMinutes;

    /**
     *  
     */
    public TimeResolver(Date date, String timestring) {
        this.date = date;
        this.timestring = timestring;
    }

    public TimeResolver() {

    }

    public Date resolve() {

        if (this.date == null || this.timestring == null)
            throw new RuntimeException("Unable to resolve time.");

        Calendar c = new GregorianCalendar();
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        parseTimeString();

        Calendar c2 = new GregorianCalendar(year, month, day, this
                .getResolvedHours(), this.getResolvedMinutes());
        Date ret = new Date();
        ret.setTime(c2.getTimeInMillis());

        return ret;
    }

    public Date resolve(Date date, String timestring) {
        this.date = date;
        this.timestring = timestring;
        return resolve();
    }

    /**
     *  
     */
    private void parseTimeString() {
        StringTokenizer st = new StringTokenizer(timestring, ":. ");
        String hour = null;
        int intHour = 0;
        String minute = null;
        int intMinute = 0;
        String ampm = "A";

        //hours
        if (st.hasMoreTokens()) {
            hour = st.nextToken();
        }

        if (hour != null) {
            try {
                intHour = Integer.parseInt(hour);
            } catch (NumberFormatException e) {
                //do nothing
            }
        }

        //minutes
        if (st.hasMoreTokens()) {
            minute = st.nextToken();
            if (minute.endsWith("p") || minute.endsWith("pm")) {
                ampm = "P";
            } else if (minute.endsWith("a") || minute.endsWith("am")) {
                ampm = "A";
            }

            if (minute.length() > 2) {
                minute = minute.substring(0, 2);
            }
        }

        if (minute != null) {
            try {
                intMinute = Integer.parseInt(minute);
                //log.info("intMinute: " + intMinute);
            } catch (NumberFormatException e) {
                //do nothing
            }
        }

        //ampm
        if (st.hasMoreTokens()) {
            ampm = st.nextToken().toUpperCase();
            //must have the != or you'll get the wrong day.
        }

        if (ampm.startsWith("P") && intHour != 12) {
            intHour += 12;
        }

        this.resolvedHours = intHour;
        this.resolvedMinutes = intMinute;

    }

    /**
     * @return
     */
    public int getResolvedHours() {
        return resolvedHours;
    }

    /**
     * @return
     */
    public int getResolvedMinutes() {
        return resolvedMinutes;
    }

    protected static Log log = LogFactory.getLog(TimeResolver.class.getName());
}