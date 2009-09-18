/*
 * Created on Oct 19, 2005
 *
 */
package com.fivesticks.time.dashboard.xwork;

import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class ViewDashboardTimeClockAJAXTest extends AbstractTimeTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testBasic() throws Exception {
        
        ViewDashboardTimeClockAJAX ajax = new ViewDashboardTimeClockAJAX();
        
        ajax.setSessionContext(this.sessionContext);
        
        ajax.setDashboardContext(new DashboardContext());
        
        ajax.getDashboardContext().setShowingTimeClockStatus(true);
        
        String r = ajax.execute();
        
        assertEquals(r,ActionSupport.SUCCESS);
        
        /*
         * 2006-06-15 JSON now so the tests are simpler.
         */
        assertNotNull(ajax.getJsonDataAsString());
        
        assertTrue(ajax.getJsonDataAsString().length() > 15);
        
//        assertNotNull(ajax.getTimeclockStatus());
//        
//        assertEquals(1,ajax.getTimeclockStatus().size());
//        
//        for (Iterator iter = ajax.getTimeclockStatus().iterator(); iter.hasNext();) {
//            UserShiftRecord element = (UserShiftRecord) iter.next();
//            
//            assertEquals(UserDateTimeclockStatusTypeEnum.NO_RECORD,element.getStatus());
//            
//        }
        
    }
}
