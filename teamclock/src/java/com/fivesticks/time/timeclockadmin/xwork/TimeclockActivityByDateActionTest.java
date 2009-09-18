/*
 * Created on Aug 18, 2005
 *
 */
package com.fivesticks.time.timeclockadmin.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fivesticks.time.timeclock.TiimeclockBDFactory;
import com.fivesticks.time.timeclock.TimeclockBD;
import com.fivesticks.time.timeclock.UserDateTimeclockStatusTypeEnum;
import com.fivesticks.time.timeclock.UserShiftDisplayWrapper;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

public class TimeclockActivityByDateActionTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasic() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();

        User user = SystemUserTestFactory.singleton.buildOwner(owner);

        SessionContext context = SessionContextTestFactory.getContext(owner,
                user);

        TimeclockBD bd = TiimeclockBDFactory.factory.build(context);

        bd.punchIn(user,"testip");
        
        TimeclockActivityByDateAction action = new TimeclockActivityByDateAction();
        
        action.setSessionContext(context);
        
        action.setTimeclockActivityByDateContext(new TimeclockActivityByDateContext());
        
        String r = action.execute();
        
        assertEquals(ActionSupport.SUCCESS, r);
        
        assertEquals(1,action.getShifts().size());
        
        UserShiftDisplayWrapper current = (UserShiftDisplayWrapper) action.getShifts().toArray()[0];
        
        assertTrue(current.getStatus().equals(UserDateTimeclockStatusTypeEnum.CLOCKED_IN.getFriendlyName()));
        
    }
    
    public void testBasicPlus1User() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();

        User user = SystemUserTestFactory.singleton.buildOwner(owner);

        User user2 = SystemUserTestFactory.singleton.buildOwner(owner);

        SessionContext context = SessionContextTestFactory.getContext(owner,
                user);

        TimeclockBD bd = TiimeclockBDFactory.factory.build(context);

        bd.punchIn(user,"testip");
        
        TimeclockActivityByDateAction action = new TimeclockActivityByDateAction();
        
        action.setSessionContext(context);
        
        action.setTimeclockActivityByDateContext(new TimeclockActivityByDateContext());
        
        String r = action.execute();
        
        assertEquals(ActionSupport.SUCCESS, r);
        
        assertEquals(2,action.getShifts().size());
        
        UserShiftDisplayWrapper current = (UserShiftDisplayWrapper) action.getShifts().toArray()[0];
        
        assertTrue(current.getStatus().equals(UserDateTimeclockStatusTypeEnum.CLOCKED_IN.getFriendlyName()));

        UserShiftDisplayWrapper current2 = (UserShiftDisplayWrapper) action.getShifts().toArray()[1];
        
        assertTrue(current2.getStatus().equals(UserDateTimeclockStatusTypeEnum.NO_RECORD.getFriendlyName()));

    }    
}
