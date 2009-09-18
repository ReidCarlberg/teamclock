/*
 * Created on Mar 29, 2005 by REID
 */
package com.fivesticks.time.todo.queue;

import java.util.Collection;

/**
 * @author REID
 */
public class PollEmailServiceProviderCommand {

//    private Log log = LogFactory.getLog(PollEmailServiceProviderCommand.class);

    private EmailServiceProvider emailServiceProvider;
    
    private String queueSmtpServer;

    private String queueSmtpFolder;

    private String queueSmtpUsername;

    private String queueSmtpPassword;

    public Collection execute() throws Exception {

        validate();
        
        return null;
    }
    
    private void validate() throws PollEmailServiceProviderCommandException {
        
        if (this.getEmailServiceProvider() == null) {
            throw new PollEmailServiceProviderCommandException("no service provider");
        }
        if (this.getQueueSmtpServer() == null || this.getQueueSmtpFolder() == null || this.getQueueSmtpPassword() == null || this.getQueueSmtpUsername() == null) {
            throw new PollEmailServiceProviderCommandException("settings incorrect");
        }
    }
    
    public String getQueueSmtpFolder() {
        return queueSmtpFolder;
    }
    public void setQueueSmtpFolder(String queueSmtpFolder) {
        this.queueSmtpFolder = queueSmtpFolder;
    }
    public String getQueueSmtpPassword() {
        return queueSmtpPassword;
    }
    public void setQueueSmtpPassword(String queueSmtpPassword) {
        this.queueSmtpPassword = queueSmtpPassword;
    }
    public String getQueueSmtpServer() {
        return queueSmtpServer;
    }
    public void setQueueSmtpServer(String queueSmtpServer) {
        this.queueSmtpServer = queueSmtpServer;
    }
    public String getQueueSmtpUsername() {
        return queueSmtpUsername;
    }
    public void setQueueSmtpUsername(String queueSmtpUsername) {
        this.queueSmtpUsername = queueSmtpUsername;
    }
    public EmailServiceProvider getEmailServiceProvider() {
        return emailServiceProvider;
    }
    public void setEmailServiceProvider(
            EmailServiceProvider emailServiceProvider) {
        this.emailServiceProvider = emailServiceProvider;
    }
}