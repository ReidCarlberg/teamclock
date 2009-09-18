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
 * Created on Feb 6, 2004
 *
 */
package com.fivesticks.time.timeclock;

import java.io.Serializable;
import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.timeclock.util.TimeclockDetailComparatorEventTimestampImpl;
import com.fivesticks.time.timeclock.util.TimeclockDetailDisplayWrapperSetBuilder;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *  
 */
public class UserShiftDisplayWrapper implements Serializable{

    private final UserShiftRecord current;
    
//    private final SystemOwner systemOwner;
    
    private Collection displayableEvents;

    /**
     * @param current
     */
    public UserShiftDisplayWrapper(SystemOwner systemOwner, UserShiftRecord current) {
        this.current = current;
//        this.systemOwner = systemOwner;
        this.setDisplayableEvents(new TimeclockDetailDisplayWrapperSetBuilder(systemOwner).build(current.getEvents(), new TimeclockDetailComparatorEventTimestampImpl(true)));
    }

    /**
     * @return
     */
    public UserShiftRecord getCurrent() {
        return current;
    }

    public String getUsername() {
        return current.getUser().getUsername();
    }

    public String getShiftStart() {
        return SimpleDate.factory.build(current.getShift().getStart())
                .getMmddyyyy();
    }

    public String getShiftStop() {
        return SimpleDate.factory.build(current.getShift().getEnd())
                .getMmddyyyy();
    }

    public int getRawMinutes() {
        return current.getShiftMinutes();
    }
    
    public int getRawBreakMinutes() {
        return current.getShiftBreakMinutes();
    }    

    public String getHoursAndMinutes() {
        StringBuffer ret = new StringBuffer();

        ret.append(current.getShiftMinutes() / 60);
        ret.append(":");
        ret.append(current.getShiftMinutes() % 60);

        return ret.toString();
    }

    public String getActualHoursAndHundredths() {

        return MinuteFormatter.formatAsHoursAndHundredths(current
                .getShiftMinutes());

    }

    public String getRoundedHoursAndHundredths() {

        return MinuteFormatter
                .formatAsHoursAndHundredths(MinuteRounderQuarterHourImpl
                        .round(current.getShiftMinutes()));

    }
    
    public String getRoundedBreakHoursAndHundredths() {

        return MinuteFormatter
                .formatAsHoursAndHundredths(MinuteRounderQuarterHourImpl
                        .round(current.getShiftBreakMinutes()));

    }    

    public String getStatus() {
        UserDateTimeclockStatusTypeEnum status = UserDateTimeclockStatusTypeEnum
                .getByName(current.getStatus().getName());
        return status.getFriendlyName();
    }

    /**
     * @return Returns the displayableEvents.
     */
    public Collection getDisplayableEvents() {
        return displayableEvents;
    }

    /**
     * @param displayableEvents The displayableEvents to set.
     */
    public void setDisplayableEvents(Collection displayableEvents) {
        this.displayableEvents = displayableEvents;
    }

}