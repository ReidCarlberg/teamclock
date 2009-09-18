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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Nick
 *  
 */
public abstract class AbstractBin implements Bin {

    private Collection calendars = new ArrayList();

    private Date lowerBound;

    private Date upperBound;

    private String label;



    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[lower bound:").append(lowerBound);
        buffer.append("][upper bound:").append(upperBound);
        buffer.append("][label:").append(label).append("\n\t");
        Iterator i = calendars.iterator();

        while (i.hasNext()) {

            CalendarDisplayWrapper calTemp = (CalendarDisplayWrapper) i
                    .next();
            buffer.append(calTemp.getDescription()).append(" : ");
            buffer.append(calTemp.getCalendar().getStartDate());
            buffer.append("-");
            buffer.append(calTemp.getCalendar().getEndDate());
            buffer.append(":");
        }
        buffer.append("\n");
        return buffer.toString();
    }

    public void addCalendar(TcCalendar calendar) {
        if (this.fit(calendar)) {
            calendars.add(new CalendarDisplayWrapper(calendar));
        }
    }
    
    /**
     * @param label
     *            The label to set.
     */
    void setLabel(String label) {
        this.label = label;
    }

    /**
     * @param lowerBound
     *            The lowerBound to set.
     */
    void setLowerBound(Date lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * @param upperBound
     *            The upperBound to set.
     */
    void setUpperBound(Date upperBound) {
        this.upperBound = upperBound;
    }


    /*
     * lower upper | | Start---------------- -----------------------Stop -- line
     * show what are valid fits.
     * 
     * (non-Javadoc)
     * 
     * @see com.fstx.time.main.schedule.Bin#fit(com.fstx.time.main.FstxCalendar)
     */
    public boolean fit(TcCalendar calendar) {
        return handleFit(calendar);
    }

    /**
     * Reid
     * 
     * @param calendar
     * @return
     */
    private boolean handleFit(TcCalendar calendar) {

        //possibility 1: the event starts before and ends after the bin.
        if (calendar.getStartDate().before(lowerBound)
                && calendar.getEndDate().after(upperBound)) {
            //log.info("1");
            return true;
        }

        //possibility 2: the event starts before and ends during the bin
        if (calendar.getStartDate().before(lowerBound)
                && calendar.getEndDate().after(lowerBound)
                && (calendar.getEndDate().before(upperBound) || calendar
                        .getEndDate().equals(upperBound))) {
            //log.info("2");
            return true;
        }

        //possibility 3: the event starts during the period and
        //ends after the period.
        if ((calendar.getStartDate().equals(lowerBound) || (calendar
                .getStartDate().after(lowerBound) && calendar.getStartDate()
                .before(upperBound)))
                && calendar.getEndDate().after(upperBound)) {
            //log.info("3");
            return true;
        }

        //possibility 4: the even either matches the
        //period exactly or occues entirely within it
        if ((calendar.getStartDate().before(upperBound) || calendar
                .getStartDate().equals(upperBound))
                && (calendar.getEndDate().after(lowerBound) || calendar
                        .getEndDate().equals(lowerBound))) {
            //log.info("4");
            return true;
        }

        //possibility 5: no match
        return false;

    }

    protected static Log log = LogFactory.getLog(AbstractBin.class.getName());

    /**
     * @return
     */
    public Date getLowerBound() {
        return lowerBound;
    }

    public Integer getLabelInt() {
        Integer test = new Integer(this.getLabel());
        return test;
    }

    public String getLabel() {
        return label;
    }

    /**
     * @return
     */
    public Date getUpperBound() {
        return upperBound;
    }

    /**
     * @return
     */
    public Collection getCalendars() {
        return calendars;
    }

}