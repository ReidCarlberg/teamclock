/*
 * Created on Aug 19, 2005
 *
 */
package com.fivesticks.time.calendar.xwork;

import java.util.Iterator;

import junit.framework.TestCase;

import com.fivesticks.time.calendar.CalendarBDFactory;
import com.fivesticks.time.calendar.CalendarFilterParameters;
import com.fivesticks.time.calendar.CalendarTestFactory;
import com.fivesticks.time.calendar.DailyBin;
import com.fivesticks.time.calendar.TcCalendar;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupTestFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

public class CalendarShowMonthActionTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasic() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();

        User user = SystemUserTestFactory.singleton.buildOwner(owner);

        Lookup lu = LookupTestFactory.build(owner, LookupType.CALENDAR_TYPE);

        TcCalendar cal1 = CalendarTestFactory.build(owner, user);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user);

        CalendarShowMonthAction action = new CalendarShowMonthAction();

        action.setSessionContext(sessionContext);

        action.setCalendarListContext(new CalendarListContext());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        boolean matched = false;
        for (Iterator iter = action.getSchedule().getBins().iterator(); iter
                .hasNext();) {
            DailyBin element = (DailyBin) iter.next();

            if (element.getLowerBound().compareTo(cal1.getStartDate()) < 0
                    && element.getUpperBound().compareTo(
                            cal1.getStartDate()) > 0) {

                assertTrue(element.getCalendars().size() == 1);
                
                matched = true;
                
            }

        }
        
        assertTrue(matched);
    }

    
    public void testBasicWithTwoOwners() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();


        User user = SystemUserTestFactory.singleton.buildOwner(owner);

        Lookup lu = LookupTestFactory.build(owner, LookupType.CALENDAR_TYPE);

        TcCalendar cal1 = CalendarTestFactory.build(owner, user);

        SystemOwner owner2 = SystemOwnerTestFactory.getOwner();

        User usero2 = SystemUserTestFactory.singleton.buildOwner(owner2);


        TcCalendar cal1o2 = CalendarTestFactory.build(owner2, usero2);

        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user);

        CalendarShowMonthAction action = new CalendarShowMonthAction();

        action.setSessionContext(sessionContext);

        action.setCalendarListContext(new CalendarListContext());

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        boolean matched = false;
        for (Iterator iter = action.getSchedule().getBins().iterator(); iter
                .hasNext();) {
            DailyBin element = (DailyBin) iter.next();

            if (element.getLowerBound().compareTo(cal1.getStartDate()) < 0
                    && element.getUpperBound().compareTo(
                            cal1.getStartDate()) > 0) {

                assertTrue(element.getCalendars().size() == 1);
                
                matched = true;
                
            }

        }
        
        assertTrue(matched);
    }

    
    
    public void testBasicWithLookups() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();

        User user = SystemUserTestFactory.singleton.buildOwner(owner);

        Lookup lu = LookupTestFactory.build(owner, LookupType.CALENDAR_TYPE);

        TcCalendar cal1 = CalendarTestFactory.build(owner, user);
        cal1.setType(lu.getId());
        CalendarBDFactory.factory.build(owner).save(cal1);
        
        TcCalendar cal2 = CalendarTestFactory.build(owner, user);
        
        SessionContext sessionContext = SessionContextTestFactory.getContext(
                owner, user);

        CalendarShowMonthAction action = new CalendarShowMonthAction();

        action.setSessionContext(sessionContext);

        action.setCalendarListContext(new CalendarListContext());
        action.getCalendarListContext().setParams(new CalendarFilterParameters());
        action.getCalendarListContext().getParams().setType(lu.getId());
        
        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

        boolean matched = false;
        for (Iterator iter = action.getSchedule().getBins().iterator(); iter
                .hasNext();) {
            DailyBin element = (DailyBin) iter.next();

            if (element.getLowerBound().compareTo(cal1.getStartDate()) < 0
                    && element.getUpperBound().compareTo(
                            cal1.getStartDate()) > 0) {

                assertTrue(element.getCalendars().size() == 1);
                
                matched = true;
                
            }

        }
        
        assertTrue(matched);
    }

}
