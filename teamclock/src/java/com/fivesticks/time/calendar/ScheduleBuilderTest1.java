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

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Nick
 *  
 */
public class ScheduleBuilderTest1 extends TestCase {

    SystemOwner systemOwner;
    
    public ScheduleBuilderTest1(String arg0) throws Exception {
        super(arg0);
        JunitSettings.initializeTestSystem();
        
        systemOwner = SystemOwnerTestFactory.getOwner();
    }

    public void testBuildDailyBasic() throws Exception {

        
        CalendarFilterParameters filter = new CalendarFilterParameters();
        filter.setStart(SimpleDate.factory.build());
        Schedule s = ScheduleBuilderFactory.factory.buildMinute(systemOwner, filter).build();
        assertTrue(s.getBins().size() == 60 * 24);

    }

    public void testBuildDaily() throws Exception {

        //FstxCalendarFilterParameters filter = new
        // FstxCalendarFilterParameters();
        //filter.setStart(SimpleDate.factory.build());
        CalendarFilterParameters filter = new CalendarFilterParameters();
        
        new CalendarFilterDecorator()
                .decorateForDate(filter, new Date());
        Schedule s = ScheduleBuilderFactory.factory.buildDaily(systemOwner, filter, 30, 0, 24).build();

        assertTrue(s.getBins().size() == 48);

    }
}