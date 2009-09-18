/*
 * Created on Mar 29, 2005 by REID
 */
package com.fivesticks.time.todo.queue;

import javax.mail.Folder;

import com.fivesticks.time.todo.queue.mock.MailAPIMockFactory;
import com.fivesticks.time.todo.queue.mock.MockFolder;

/**
 * @author REID
 */
public class EmailServiceProviderMockImpl implements EmailServiceProvider {

    public static MockFolder mockFolder;
    
    public EmailServiceProviderMockImpl() {
        mockFolder = MailAPIMockFactory.getFolderWithMessages(5);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.queue.EmailServiceProvider#getFolder(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public Folder getFolder(String server, String folder, String username,
            String password) {

        return MailAPIMockFactory.getFolderWithMessages(5);
    }



}