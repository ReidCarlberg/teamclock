/*
 * Created on Mar 29, 2005 by REID
 */
package com.fivesticks.time.todo.queue.mock;

import javax.mail.Folder;
import javax.mail.Message;

/**
 * @author REID
 */
public class MailAPIMockFactory {

    public static MockFolder getFolderWithMessages(int messageQuantity) {
        
        MockFolder ret = new MockFolder(null);
        
        for (int i=0; i<messageQuantity; i++) {
            ret.addMockMessage(buildMockMessage());
        }
        
        return ret;
        
    }
    
    public static Folder buildFolder() {
        
        MockFolder ret = new MockFolder(null);
        
        
        
        
        return ret;
    }
    
    public static Message buildMessage() {
        
        return buildMockMessage();
    }
    
    public static MockMessage buildMockMessage() {
        MockMessage ret = new MockMessage();
        
        
        
        
        return ret;   
    }
    
    
    
    

}
