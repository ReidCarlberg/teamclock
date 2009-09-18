/*
 * Created on Mar 29, 2005 by REID
 */
package com.fivesticks.time.todo.queue;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 */
public class PollEmailServiceProviderCommandTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystemNoDatabase();
    }
    
    public void testValidation() throws Exception {
        EmailServiceProviderMockImpl mock = new EmailServiceProviderMockImpl();
        
        PollEmailServiceProviderCommand cmd = new PollEmailServiceProviderCommand();
        
        try {
            cmd.execute();
            assertTrue(false);
        } catch (Exception e) {
            
        }
        
        cmd.setEmailServiceProvider(mock);
        
        try {
            cmd.execute();
            assertTrue(false);
        } catch (Exception e) {
            
        }        
        
        cmd.setQueueSmtpFolder("nothing");
        cmd.setQueueSmtpPassword("nothing");
        cmd.setQueueSmtpServer("nothing");
        cmd.setQueueSmtpUsername("nothing");
        
        //should succeed
        cmd.execute();
        
    }
    public void testGetFolder() throws Exception {
        
        EmailServiceProviderMockImpl mock = new EmailServiceProviderMockImpl();
        
        PollEmailServiceProviderCommand cmd = new PollEmailServiceProviderCommand();
        
        cmd.setEmailServiceProvider(mock);
        
        cmd.setQueueSmtpServer("server");
        
        cmd.setQueueSmtpFolder("folder");
        
        cmd.setQueueSmtpUsername("username");
        
        cmd.setQueueSmtpPassword("passwrd");
        
        
    }
    
    public void testFolderWithMessages() throws Exception {
        
        PollEmailServiceProviderCommand cmd = new PollEmailServiceProviderCommand();
        
       
        
        
    }

}
