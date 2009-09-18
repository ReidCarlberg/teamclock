/*
 * Created on Nov 14, 2005
 *
 */
package com.fivesticks.time.register.xwork;

import com.fivesticks.time.queuedmessages.SendQueuedMessagesCommandFactory;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.useradmin.UserServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

public class ResendActivationMessageActionTest extends AbstractTimeTestCase {

    User matchingUser;
    
    protected void setUp() throws Exception {
        super.setUp();
        UserServiceDelegateFactory.factory.build(this.systemOwner).createNewUser("matchingUser", "123455", this.systemOwner.getContactEmail(), UserTypeEnum.OWNERADMIN);
        
    }
    
    public void testBasic() throws Exception {
        
        this.systemOwner.setActivated(false);
        SystemOwnerServiceDelegateFactory.factory.build().save(this.systemOwner);
        
        ResendActivationMessageAction action = new ResendActivationMessageAction();
        action.setEmail(this.systemOwner.getContactEmail());
        
        String s = action.execute();
        
        assertEquals(ActionSupport.INPUT, s);
        
        /*
         * 2005-11-14 RSC 
         * one to me, one to them.
         */
        assertEquals(2, SendQueuedMessagesCommandFactory.factory.build().getMessagesToSend().size());
    }

}
