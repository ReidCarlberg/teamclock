/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.messages.xwork;

import com.fivesticks.time.messages.Message;
import com.fivesticks.time.messages.MessageTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class ModifyActionTest extends AbstractTimeTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }
    
    public void testBasic_Input() throws Exception {
        
        ModifyAction action = new ModifyAction();
        
        action.setSessionContext(this.sessionContext);
        action.setModifyContext(new ModifyContext());
        
        String s = action.execute();
        
        assertEquals(ActionSupport.INPUT, s);
        
    }
    
    public void testBasic_Save() throws Exception {

        ModifyAction action = new ModifyAction();
        
        action.setSessionContext(this.sessionContext);
        action.setModifyContext(new ModifyContext());
        action.setTargetMessage(new Message());
        
        action.getTargetMessage().setName("name");
        action.getTargetMessage().setSubject("subject1");
        action.getTargetMessage().setMessage("message");
        action.setSaveMessage("save");
        
        String s = action.execute();
        
        assertEquals(ActionSupport.SUCCESS, s);
    }
    
    public void testBasic_Copy() throws Exception {

        Message test = MessageTestFactory.getPersisted(this.systemOwner);
        
        ModifyAction action = new ModifyAction();
        
        action.setSessionContext(this.sessionContext);
        action.setModifyContext(new ModifyContext());
        action.getModifyContext().setTarget(test);
        action.setTargetMessage(test);
        
        action.setCopyMessage("copy");
        
        String s = action.execute();
        
        assertEquals(ActionSupport.INPUT, s);
        
        assertTrue(action.getTargetMessage().getId() == null);
        assertTrue(action.getModifyContext().getTarget() == null);
        
    }
    
}
