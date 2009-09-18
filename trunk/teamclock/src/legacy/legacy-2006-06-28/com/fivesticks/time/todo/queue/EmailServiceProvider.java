/*
 * Created on Mar 29, 2005 by REID
 */
package com.fivesticks.time.todo.queue;

import javax.mail.Folder;

/**
 * @author REID
 */
public interface EmailServiceProvider {

    public Folder getFolder(String server, String folder, String username,
            String password) throws EmailServiceProviderException;



}