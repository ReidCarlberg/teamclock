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
 * Created on Dec 1, 2003
 *
 */
package com.fivesticks.time.timeclock;

import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.util.TimezoneDateTimeResolver;
import com.fivesticks.time.settings.broker.MasterSettingsBroker;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 * 
 */
public class UserShiftRecordTest extends TestCase {

    private SystemOwner systemOwner;

    private SessionContext sessionContext;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                UserBDFactory.factory.build().getByUsername("admin"));
        sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemOwner);
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

    /**
     * basic buildter test.
     * 
     * @throws Exception
     */
    public void testBasic_B() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, new Date());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.NO_RECORD);
    }

    /**
     * basic clocked in
     * 
     * @author REID
     * 
     */
    public void testBasic_C() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, new Date());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.NO_RECORD);

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");

        UserShiftRecord t2 = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        log.info("status: " + t2.getStatus());

        assertTrue(t2.getStatus() == UserDateTimeclockStatusTypeEnum.CLOCKED_IN);

    }

    /**
     * basic clock in and clock out
     * 
     * @throws Exception
     */
    public void testBasic_D() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, new Date());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.NO_RECORD);

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");

        

        TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");

        UserShiftRecord t2 = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        assertTrue(t2.getStatus() == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT);

    }

    /**
     * basic clock in and break start
     * 
     * @throws Exception
     */
    public void testBasic_E() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, new Date());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.NO_RECORD);

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");

        Thread.sleep(1000);

        TiimeclockBDFactory.factory.build(sessionContext).breakStart(user,"testip");

        UserShiftRecord t2 = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        assertTrue(t2.getStatus() == UserDateTimeclockStatusTypeEnum.BREAK);

    }

    /**
     * basic clock in and break start
     * 
     * @throws Exception
     */
    public void testBasic_F() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, new Date());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.NO_RECORD);

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");

        Thread.sleep(1000);

        TiimeclockBDFactory.factory.build(sessionContext).breakStart(user,"testip");

        Thread.sleep(1000);

        TiimeclockBDFactory.factory.build(sessionContext).breakStop(user,"testip");

        Thread.sleep(1000);

        UserShiftRecord t2 = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        assertTrue(t2.getStatus() == UserDateTimeclockStatusTypeEnum.CLOCKED_IN);

    }

    /**
     * basic clock in and break and clockout Nick 2005-10-13 This failed on the
     * ant run, but passes here!
     * 
     * @throws Exception
     */
    public void testBasic_G() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, new Date());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.NO_RECORD);

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");

        Thread.sleep(1000);

        TiimeclockBDFactory.factory.build(sessionContext).breakStart(user,"testip");

        Thread.sleep(1000);

        TiimeclockBDFactory.factory.build(sessionContext).breakStop(user,"testip");

        Thread.sleep(1000);

        TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");

        Thread.sleep(1000);

        UserShiftRecord t2 = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        assertTrue(t2.getStatus() == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT);

    }

    /**
     * should fail -- can't clock out if you are not clocked in.
     * 
     * @throws Exception
     */
    public void testBasic_H() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, new Date());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.NO_RECORD);

        try {
            TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");
            assertTrue(false);
        } catch (TimeclockBDException e) {

        }

        // UserShiftRecord t2 = new
        // UserShiftRecordBuilder().buildByResolvedStartDate(user, new Date());

        // assertTrue(t2.getStatus() ==
        // UserDateTimeclockStatusTypeEnum.INCOMPLETE);

    }

    /**
     * should fail -- can't clock break start if you are punched out
     * 
     * @throws Exception
     */
    public void testBasic_I() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, new Date());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.NO_RECORD);

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");
        Thread.sleep(1000);

        TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");
        Thread.sleep(1000);

        try {
            TiimeclockBDFactory.factory.build(sessionContext).breakStart(user,"testip");
            assertTrue(false);
        } catch (TimeclockBDException e) {

        }

        // UserShiftRecord t2 = new
        // UserShiftRecordBuilder().buildByResolvedStartDate(user, new Date());

        // assertTrue(t2.getStatus() ==
        // UserDateTimeclockStatusTypeEnum.INCOMPLETE);

    }

    /**
     * ensures that we have the correct shift minute result. basic is just a
     * clock in and a clock out.
     * 
     * @throws Exception
     */
    public void testShiftMinutes_A() throws Exception {

        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        SystemOwner owner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(user);

        SimpleDate start = SimpleDate.factory.build();
        start.advanceHour(-2);
        SimpleDate stop = SimpleDate.factory.build();

        Timeclock in = new Timeclock();
        in.setUsername(user.getUsername());
        in.setEvent(TimeclockEventEnum.CLOCK_IN.getName());
        in.setTimestamp(new Date());
        in.setEventTimestamp(TimezoneDateTimeResolver.resolveStatic(start.getDate(),
                MasterSettingsBroker.singleton.getSettings(owner,user)
                        .getSystemTimeZone()));
        in.setOwnerKey(systemOwner.getKey());
        in = TimeclockDAOFactory.factory.build().save(in);

        Timeclock out = new Timeclock();
        out.setUsername(user.getUsername());
        out.setEvent(TimeclockEventEnum.CLOCK_OUT.getName());
        out.setTimestamp(new Date());
        out.setEventTimestamp(TimezoneDateTimeResolver.resolveStatic(stop.getDate(),
                MasterSettingsBroker.singleton.getSettings(owner,user)
                        .getSystemTimeZone()));
        out.setOwnerKey(systemOwner.getKey());
        out = TimeclockDAOFactory.factory.build().save(out);

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT);

        assertTrue(t.getShiftMinutes() == 120);

    }

    /**
     * ensures that we have the correct shift minute result.
     * 
     * tests a matched pair of clock in and outs.
     * 
     * @throws Exception
     */
    public void testShiftMinutes_B() throws Exception {

        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        SystemOwner owner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(user);

        SimpleDate start = SimpleDate.factory.build();
        start.advanceHour(-2);
        SimpleDate stop = SimpleDate.factory.build();

        Timeclock in = new Timeclock();
        in.setUsername(user.getUsername());
        in.setEvent(TimeclockEventEnum.CLOCK_IN.getName());
        in.setTimestamp(new Date());
        in.setEventTimestamp(TimezoneDateTimeResolver.resolveStatic(start.getDate(),
                MasterSettingsBroker.singleton.getSettings(owner,user)
                .getSystemTimeZone()));
        in.setOwnerKey(systemOwner.getKey());
        in = TimeclockDAOFactory.factory.build().save(in);

        Timeclock out = new Timeclock();
        out.setUsername(user.getUsername());
        out.setEvent(TimeclockEventEnum.CLOCK_OUT.getName());
        out.setTimestamp(new Date());
        out.setOwnerKey(systemOwner.getKey());
        stop.advanceHour(-1);
        out.setEventTimestamp(TimezoneDateTimeResolver.resolveStatic(stop.getDate(),
                MasterSettingsBroker.singleton.getSettings(owner,user)
                .getSystemTimeZone()));
        out = TimeclockDAOFactory.factory.build().save(out);

        SimpleDate start2 = SimpleDate.factory.build();
        start2.advanceHour(0);
        SimpleDate stop2 = SimpleDate.factory.build();

        Timeclock in2 = new Timeclock();
        in2.setUsername(user.getUsername());
        in2.setEvent(TimeclockEventEnum.CLOCK_IN.getName());
        in2.setTimestamp(new Date());
        in2.setEventTimestamp(TimezoneDateTimeResolver.resolveStatic(start2.getDate(),
                MasterSettingsBroker.singleton.getSettings(owner,user)
                .getSystemTimeZone()));
        in2.setOwnerKey(systemOwner.getKey());
        in2 = TimeclockDAOFactory.factory.build().save(in2);

        Thread.sleep(1000);

        Timeclock out2 = new Timeclock();
        out2.setUsername(user.getUsername());
        out2.setEvent(TimeclockEventEnum.CLOCK_OUT.getName());

        out2.setTimestamp(new Date());
        stop2.advanceHour(1);
        out2.setEventTimestamp(TimezoneDateTimeResolver.resolveStatic(stop2.getDate(),
                MasterSettingsBroker.singleton.getSettings(owner,user)
                .getSystemTimeZone()));
        out2.setOwnerKey(systemOwner.getKey());
        out2 = TimeclockDAOFactory.factory.build().save(out2);

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT);

        assertTrue(t.getShiftMinutes() == 120);

    }

    /**
     * ensures that we have the correct shift minute result.
     * 
     * here checks that it ignores an invalid break record.
     * 
     * @throws Exception
     */
    public void testShiftMinutes_C() throws Exception {

        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        SystemOwner owner = SystemOwnerServiceDelegateFactory.factory.build()
        .get(user);

        SimpleDate start = SimpleDate.factory.build();
        start.advanceHour(-2);
        SimpleDate stop = SimpleDate.factory.build();

        Timeclock in = new Timeclock();
        in.setUsername(user.getUsername());
        in.setEvent(TimeclockEventEnum.CLOCK_IN.getName());
        in.setTimestamp(new Date());
        in.setEventTimestamp(TimezoneDateTimeResolver.resolveStatic(start.getDate(),
                MasterSettingsBroker.singleton.getSettings(owner,user)
                .getSystemTimeZone()));
        in.setOwnerKey(systemOwner.getKey());
        in = TimeclockDAOFactory.factory.build().save(in);

        TiimeclockBDFactory.factory.build(sessionContext).breakStart(user,"testip");

        Timeclock out = new Timeclock();
        out.setUsername(user.getUsername());
        out.setEvent(TimeclockEventEnum.CLOCK_OUT.getName());
        out.setTimestamp(new Date());
        out.setEventTimestamp(TimezoneDateTimeResolver.resolveStatic(stop.getDate(),
                MasterSettingsBroker.singleton.getSettings(owner,user)
                .getSystemTimeZone()));
        out.setOwnerKey(systemOwner.getKey());
        out = TimeclockDAOFactory.factory.build().save(out);

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.INCOMPLETE);

        assertTrue(t.getShiftMinutes() == 120);

    }

    /**
     * ensures that we have the correct shift minute result.
     * 
     * here checks to find time using a full break. Nick 2005-10-13 This failed
     * on the ant run, but passes here!
     * 
     * @throws Exception
     */
    public void testShiftMinutes_D() throws Exception {

        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        SimpleDate start = SimpleDate.factory.build();
        start.advanceHour(-2);
        SimpleDate timezoneDate = SimpleDate.factory.build(start.getDate());

        start = TimezoneDateTimeResolver.resolveStatic(timezoneDate,
                "America/Chicago");

        Timeclock in = new Timeclock();
        in.setUsername(user.getUsername());
        in.setEvent(TimeclockEventEnum.CLOCK_IN.getName());
        in.setTimestamp(new Date());
        in.setEventTimestamp(start.getDate());
        in.setOwnerKey(systemOwner.getKey());
        in = TimeclockDAOFactory.factory.build().save(in);

        /*
         * RSC 2005-10-14 Since we're using the other date, these cause an
         * error.
         */
        // FstxTimeclockBD.factory.build(sessionContext).breakStart(user);
        //
        // FstxTimeclockBD.factory.build(sessionContext).breakStop(user);
        SimpleDate stop = SimpleDate.factory.build();
        stop = TimezoneDateTimeResolver.resolveStatic(stop, "America/Chicago");

        Timeclock out = new Timeclock();
        out.setUsername(user.getUsername());
        out.setEvent(TimeclockEventEnum.CLOCK_OUT.getName());
        out.setTimestamp(new Date());
        out.setEventTimestamp(stop.getDate());
        out.setOwnerKey(systemOwner.getKey());
        out = TimeclockDAOFactory.factory.build().save(out);

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        log.info("current status is " + t.getStatus().getName());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT);

        assertTrue(t.getShiftMinutes() == 120);

    }

    /**
     * 2006-06-15 reid
     * a way to track how long people are on break.
     * 
     * @throws Exception
     */
    public void testShiftBreakMinutes_A() throws Exception {

        User user = UserBDFactory.factory.build().getUserByEmail(
                "admin@fivesticks.com");

        SimpleDate start = SimpleDate.factory.build();
        start.advanceHour(-2);
        SimpleDate timezoneDate = SimpleDate.factory.build(start.getDate());

        start = TimezoneDateTimeResolver.resolveStatic(timezoneDate,
                "America/Chicago");

        Timeclock in = new Timeclock();
        in.setUsername(user.getUsername());
        in.setEvent(TimeclockEventEnum.CLOCK_IN.getName());
        in.setTimestamp(new Date());
        in.setEventTimestamp(start.getDate());
        in.setOwnerKey(systemOwner.getKey());
        in = TimeclockDAOFactory.factory.build().save(in);

        /*
         * 2006-06-15 then we advance 30 minutes and go on break.
         */
        start.advanceMinute(30);
        Timeclock bstart = new Timeclock();
        bstart.setUsername(user.getUsername());
        bstart.setEvent(TimeclockEventEnum.BREAK_START.getName());
        bstart.setTimestamp(new Date());
        bstart.setEventTimestamp(start.getDate());
        bstart.setOwnerKey(systemOwner.getKey());
        bstart = TimeclockDAOFactory.factory.build().save(bstart);        
        
        /*
         * advance 15 minutes and break stop.
         */
        start.advanceMinute(15);
        Timeclock bstop = new Timeclock();
        bstop.setUsername(user.getUsername());
        bstop.setEvent(TimeclockEventEnum.BREAK_STOP.getName());
        bstop.setTimestamp(new Date());
        bstop.setEventTimestamp(start.getDate());
        bstop.setOwnerKey(systemOwner.getKey());
        bstop = TimeclockDAOFactory.factory.build().save(bstop);   
        
        /*
         * RSC 2005-10-14 Since we're using the other date, these cause an
         * error.
         */
        // FstxTimeclockBD.factory.build(sessionContext).breakStart(user);
        //
        // FstxTimeclockBD.factory.build(sessionContext).breakStop(user);
        SimpleDate stop = SimpleDate.factory.build();
        stop = TimezoneDateTimeResolver.resolveStatic(stop, "America/Chicago");

        Timeclock out = new Timeclock();
        out.setUsername(user.getUsername());
        out.setEvent(TimeclockEventEnum.CLOCK_OUT.getName());
        out.setTimestamp(new Date());
        out.setEventTimestamp(stop.getDate());
        out.setOwnerKey(systemOwner.getKey());
        out = TimeclockDAOFactory.factory.build().save(out);

        UserShiftRecord t = new UserShiftRecordBuilder(this.systemOwner)
                .buildByResolvedStartDate(user, sessionContext.getResolvedNow());

        log.info("current status is " + t.getStatus().getName());

        assertTrue(t.getStatus() == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT);

        assertTrue(t.getShiftMinutes() == 120);
        
        log.info("SHIFT BREAK MINUTES IS " + t.getShiftBreakMinutes());
        
        assertTrue(t.getShiftBreakMinutes() == 15);

    }
    
    static Log log = LogFactory.getLog(UserShiftRecordTest.class.getName());

}