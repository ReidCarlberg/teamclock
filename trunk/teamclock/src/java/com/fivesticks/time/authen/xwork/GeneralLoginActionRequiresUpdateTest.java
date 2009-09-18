/*
 * Created on Aug 24, 2006
 *
 */
package com.fivesticks.time.authen.xwork;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.Action;

public class GeneralLoginActionRequiresUpdateTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        this.systemOwner.setRequiresAccountUpdate(true);
        SystemOwnerServiceDelegateFactory.factory.build().save(this.systemOwner);
        
        GeneralLoginAction action = new GeneralLoginAction();
        
        SessionContext sessionContext = new SessionContext();
        action.setSessionContext(sessionContext);
        
        
        action.setLoginButton("submit");
        action.setUsername(this.user.getUsername());
        action.setPassword(this.userPassword);
        
        String s = action.execute();
        
        assertEquals(Action.SUCCESS + ".accountUpdate", s);
        
    }
}
