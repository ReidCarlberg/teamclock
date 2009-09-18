/*
 * Created on Nov 27, 2005
 *
 */
package com.fivesticks.time.authen.xwork;

import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionSupport;

public class PasswordResetActivateLinkActionTest extends AbstractTimeTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }
    
    public void testResetLink() throws Exception {
        
        UserBD bd = UserBDFactory.factory.build();
        bd.addResetKey(this.user);
        
        PasswordResetActivateLinkAction action = new PasswordResetActivateLinkAction();
        
        action.setKey(user.getResetKey());
        action.setPassword1("pass1");
        action.setPassword2("pass1");
        
        action.setSubmitUpdate("submit");
        
        String s = action.execute();
        
        assertEquals(ActionSupport.SUCCESS, s);
        
        User u = bd.getUserByEmail(user.getEmail());
        
        assertNull(u.getResetKey());
    }
    
    public void testResetLinkWithInvalidKey() throws Exception {
        UserBD bd = UserBDFactory.factory.build();
        bd.addResetKey(this.user);
        
        PasswordResetActivateLinkAction action = new PasswordResetActivateLinkAction();
        
//        action.setKey(user.getResetKey());
//        action.setPassword1("pass1");
//        action.setPassword2("pass1");
        
        action.setSubmitUpdate("submit");
        
        String s = action.execute();
        
        assertEquals(ActionSupport.INPUT, s);
        

    }

    public void testResetLinkWithMismatchedPasswords() throws Exception {
        UserBD bd = UserBDFactory.factory.build();
        bd.addResetKey(this.user);
        
        PasswordResetActivateLinkAction action = new PasswordResetActivateLinkAction();
        
        action.setKey(user.getResetKey());
        action.setPassword1("pass1");
        action.setPassword2("pass1s");
        
        action.setSubmitUpdate("submit");
        
        String s = action.execute();
        
        assertEquals(ActionSupport.INPUT, s);
        

    }
}
