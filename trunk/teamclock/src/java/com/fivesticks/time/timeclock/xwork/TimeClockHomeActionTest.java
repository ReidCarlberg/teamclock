/*
 * Created on Oct 19, 2005
 *
 * 
 */
package com.fivesticks.time.timeclock.xwork;

import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class TimeClockHomeActionTest extends AbstractTimeTestCase {

    public void testTimeClockHomeAction() throws Exception {
        TimeClockHomeAction action = new TimeClockHomeAction();
        action.setSessionContext(this.sessionContext);

        assertEquals(ActionSupport.SUCCESS, action.execute());

//        UserPayPeriodSummary summary = action.getTodaySummary();
//        assertNotNull(summary);
        
        assertNotNull(action.getUserPayPeriodSummary());
    }
}
