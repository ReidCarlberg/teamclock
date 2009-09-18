/*
 * Created on Jun 14, 2006
 *
 */
package com.fivesticks.time.timeclock.xwork;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class TimeClockHomeAsJSONActionTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        TimeClockHomeAsJSONAction a = new TimeClockHomeAsJSONAction();
        a.setSessionContext(this.sessionContext);
        
        a.execute();
        
        assertNotNull(a.getJsonDataAsString());
    }
}
