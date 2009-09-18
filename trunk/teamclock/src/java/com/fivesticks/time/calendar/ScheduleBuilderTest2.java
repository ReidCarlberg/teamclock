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
 * Created on Nov 15, 2003
 *
 */
package com.fivesticks.time.calendar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.config.DatabaseInitializer;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserFactory;

/**
 * @author Reid
 *  
 */
public class ScheduleBuilderTest2 extends TestCase {

    User u1;

    TcCalendar c1;

    TcCalendar c2;

    TcCalendar c3;

    TcCalendar c4;

    TcCalendar c5;

    protected static Log log = LogFactory.getLog(ScheduleBuilderTest2.class
            .getName());
    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        new DatabaseInitializer().initializeTables();
        u1 = UserFactory.singleton.build();
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        c1 = CalendarTestFactory.build(owner, u1);
        c2 = CalendarTestFactory.build(owner, u1);
        c3 = CalendarTestFactory.build(owner, u1);
        c4 = CalendarTestFactory.build(owner, u1);
        c5 = CalendarTestFactory.build(owner, u1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBasic_A() throws Exception {

    }

    public void testBasic_B() throws Exception {
        //		List c = new ArrayList();
        //		c.add(c1);
        //		c.add(c2);
        //		c.add(c3);
        //		c.add(c4);
        //		c.add(c5);

        //Schedule s = new ScheduleBuilderDailyImpl().build(c, 30, new Date());

        CalendarFilterParameters filter = new CalendarFilterParameters();
        
        new CalendarFilterDecorator().decorateForToday(filter);
        
        Schedule s = ScheduleBuilderFactory.factory.buildDaily(SystemOwnerTestFactory.getOwner(),
                filter, 30, 0, 24).build();

        assertTrue(s.getBins().size() == 48);

        Iterator i = s.getBins().iterator();

        while (i.hasNext()) {
            Bin current = (Bin) i.next();
            log.info("here " + current.toString());
        }
    }

    public void testBasic_C() throws Exception {
        List c = new ArrayList();
        c.add(c1);
        c.add(c2);
        c.add(c3);
        c.add(c4);
        c.add(c5);

        //		Schedule s = new ScheduleBuilderDailyImpl().build(c, 30, new Date());
        CalendarFilterParameters filter = new CalendarFilterParameters();
        
        new CalendarFilterDecorator().decorateForToday(filter);

        Schedule s = ScheduleBuilderFactory.factory.buildDaily(SystemOwnerTestFactory.getOwner(),
                filter, 30, 0, 24).build();

        assertTrue(s.getBins().size() == 48);

        Iterator i = s.getBins().iterator();

        while (i.hasNext()) {
            Bin current = (Bin) i.next();
            DailyBinDisplayWrapper display = new DailyBinDisplayWrapper(
                    (DailyBin) current);
            log.info(display.getBinRange());
        }
    }
}