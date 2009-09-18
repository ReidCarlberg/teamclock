/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.messages;

import java.io.Serializable;

import com.fivesticks.time.common.AbstractTimeObject;

public class Message extends AbstractTimeObject implements Serializable {


    private String name;
    private String subject;
    private String message;
    

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the subject.
     */
    public String getSubject() {
        return subject;
    }
    /**
     * @param subject The subject to set.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    
    
}
