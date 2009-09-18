/*
 * Created on Mar 29, 2005 by REID
 */
package com.fivesticks.time.todo.queue.mock;

import java.util.ArrayList;
import java.util.Collection;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

/**
 * @author REID
 */
public class MockFolder extends Folder {

    private String name;
    private String fullName;
    private boolean open;
    
    private Collection mockMessages = new ArrayList();
    
    void addMockMessage(MockMessage message) {
        mockMessages.add(message);
    }
    /**
     * @param arg0
     */
    protected MockFolder(Store arg0) {
        super(arg0);
        
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#getParent()
     */
    public Folder getParent() throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#exists()
     */
    public boolean exists() throws MessagingException {
        
        return true;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#list(java.lang.String)
     */
    public Folder[] list(String arg0) throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#getSeparator()
     */
    public char getSeparator() throws MessagingException {
        
        return 0;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#getType()
     */
    public int getType() throws MessagingException {
        
        return 0;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#create(int)
     */
    public boolean create(int arg0) throws MessagingException {
        
        return false;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#hasNewMessages()
     */
    public boolean hasNewMessages() throws MessagingException {
        
        return true;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#getFolder(java.lang.String)
     */
    public Folder getFolder(String arg0) throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#delete(boolean)
     */
    public boolean delete(boolean arg0) throws MessagingException {
        
        return false;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#renameTo(javax.mail.Folder)
     */
    public boolean renameTo(Folder arg0) throws MessagingException {
        
        return false;
    }

    public synchronized Message[] getMessages() throws MessagingException {
        
        Message[] example={};
        
        return (Message[]) mockMessages.toArray(example);
    }
    /* (non-Javadoc)
     * @see javax.mail.Folder#open(int)
     */
    public void open(int arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#close(boolean)
     */
    public void close(boolean arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#isOpen()
     */
    public boolean isOpen() {
        
        return open;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#getPermanentFlags()
     */
    public Flags getPermanentFlags() {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#getMessageCount()
     */
    public int getMessageCount() throws MessagingException {
        
        return 0;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#getMessage(int)
     */
    public Message getMessage(int arg0) throws MessagingException {
        
        return null;
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#appendMessages(javax.mail.Message[])
     */
    public void appendMessages(Message[] arg0) throws MessagingException {
    }

    /* (non-Javadoc)
     * @see javax.mail.Folder#expunge()
     */
    public Message[] expunge() throws MessagingException {
        
        return null;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setOpen(boolean open) {
        this.open = open;
    }
}
