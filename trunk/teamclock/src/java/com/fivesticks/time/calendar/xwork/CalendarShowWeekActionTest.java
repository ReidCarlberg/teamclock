/*
 * Created on Aug 20, 2005
 *
 */
package com.fivesticks.time.calendar.xwork;

import java.util.Iterator;

import com.fivesticks.time.calendar.CalendarFilterParameters;
import com.fivesticks.time.calendar.CalendarTestFactory;
import com.fivesticks.time.calendar.DailyBin;
import com.fivesticks.time.calendar.TcCalendar;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class CalendarShowWeekActionTest extends AbstractTimeTestCase {


    
    /*
     * 2006-04-24 RSC This is interesting because it essentially fails because
     */
    public void testBasic() throws Exception {
        
        TcCalendar cal1 = CalendarTestFactory.build(systemOwner, user);
        
        CalendarShowWeekAction action = new CalendarShowWeekAction();
        
        action.setSessionContext(sessionContext);
        action.setCalendarListContext(new CalendarListContext());
        action.getCalendarListContext().setParams(new CalendarFilterParameters());
        
        String s = action.execute();
        
        assertEquals(ActionSupport.SUCCESS, s);
     
        assertTrue(action.getSchedule().getBins().size() > 0);
        
        log.info("cal1 start date " + cal1.getStartDate());
        
        boolean matched = false;
        for (Iterator iter = action.getSchedule().getBins().iterator(); iter
                .hasNext();) {
            DailyBin element = (DailyBin) iter.next();

            log.info("lower bound: " + element.getLowerBound() + " upper: " + element.getUpperBound());
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
