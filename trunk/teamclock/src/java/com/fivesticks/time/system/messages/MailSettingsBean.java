/*
 * Created on Dec 1, 2004
 *
 * 
 */
package com.fivesticks.time.system.messages;

/**
 * @author Nick
 * 
 *  
 */
public class MailSettingsBean {

    public static final String SPRING_BEAN_NAME = "mailSettingsBean";

    private String smtpServer;

    private String port;
    
    private String username;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the port.
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port The port to set.
     */
    public void setPort(String port) {
        this.port = port;
    }
}