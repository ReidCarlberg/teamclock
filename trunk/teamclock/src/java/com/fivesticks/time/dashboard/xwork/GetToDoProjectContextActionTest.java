/*
 * Created on Jun 28, 2006
 *
 */
package com.fivesticks.time.dashboard.xwork;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class GetToDoProjectContextActionTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        DashboardContext dc = new DashboardContext();
        dc.setToDoProjectFilter(project.getId());
        
        GetToDoProjectContextAction action = new GetToDoProjectContextAction();
        
        action.setSessionContext(this.sessionContext);
        action.setDashboardContext(dc);
        
        String s = action.execute();
        
        assertTrue(action.getJsonDataAsString().length() > 10);
        
    }
}
