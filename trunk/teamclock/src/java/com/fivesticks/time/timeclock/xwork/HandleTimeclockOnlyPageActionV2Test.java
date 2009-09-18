/*
 * Created on Aug 29, 2006 by Reid
 */
package com.fivesticks.time.timeclock.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.timeclock.TiimeclockBDFactory;
import com.fivesticks.time.timeclock.UserDateTimeclockStatusTypeEnum;
import com.opensymphony.xwork.Action;

public class HandleTimeclockOnlyPageActionV2Test extends AbstractTimeTestCase {

    public void testBasicFailure() throws Exception {

        HandleTimeclockOnlyPageActionV2 action = new HandleTimeclockOnlyPageActionV2();
        action.setSessionContext(new SessionContext());

        String s = action.execute();
        assertEquals(Action.SUCCESS, s);

        assertEquals("failed", action.getJsonObjectResult().getString("result"));

        action.setPunch("punch");

        action.setUsername(this.user.getUsername());
        action.setPassword(this.userPassword + "11");

        String s2 = action.execute();
        assertEquals(Action.SUCCESS, s2);

        assertEquals("failed-authenticate", action.getJsonObjectResult()
                .getString("result"));

    }

    public void testBasicPunchSucceeds() throws Exception {

        HandleTimeclockOnlyPageActionV2 action = new HandleTimeclockOnlyPageActionV2();
        action.setSessionContext(new SessionContext());

        action.setPunch("punch");
        action.setUsername(this.user.getUsername());
        action.setPassword(this.userPassword);
        String s2 = action.execute();

        assertEquals(Action.SUCCESS, s2);

        assertNotNull(action.getJsonObjectResult().get("result"));

        assertNotNull(action.getJsonObjectResult().get("summary"));
    }

    public void testSimplePunch() throws Exception {

        UserDateTimeclockStatusTypeEnum status = TiimeclockBDFactory.factory
                .build(this.sessionContext).getCurrentState(this.user,
                        this.sessionContext.getResolvedNow());

        assertEquals(UserDateTimeclockStatusTypeEnum.NO_RECORD, status);

        HandleTimeclockOnlyPageActionV2 action = new HandleTimeclockOnlyPageActionV2();
        action.setSessionContext(new SessionContext());

        action.setPunch("punch");
        action.setUsername(this.user.getUsername());
        action.setPassword(this.userPassword);
        String s2 = action.execute();

        assertEquals(Action.SUCCESS, s2);

        assertNotNull(action.getJsonObjectResult().get("result"));

        assertNotNull(action.getJsonObjectResult().get("summary"));

        UserDateTimeclockStatusTypeEnum statusAfter = TiimeclockBDFactory.factory
                .build(this.sessionContext).getCurrentState(this.user,
                        this.sessionContext.getResolvedNow());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_IN, statusAfter);

        String s3 = action.execute();
        assertEquals(Action.SUCCESS, s3);

        UserDateTimeclockStatusTypeEnum statusAfter2 = TiimeclockBDFactory.factory
                .build(this.sessionContext).getCurrentState(this.user,
                        this.sessionContext.getResolvedNow());

        assertEquals(UserDateTimeclockStatusTypeEnum.CLOCKED_OUT, statusAfter2);

    }
    
    public void testPayPeriod() throws Exception {

        HandleTimeclockOnlyPageActionV2 action = new HandleTimeclockOnlyPageActionV2();
        action.setSessionContext(new SessionContext());

        action.setPeriod("current");
        action.setUsername(this.user.getUsername());
        action.setPassword(this.userPassword);
        String s2 = action.execute();
        
        assertEquals(Action.SUCCESS, s2);
        
        assertTrue(action.getJsonObjectResult().has("periodSummary"));
        
    }

}
