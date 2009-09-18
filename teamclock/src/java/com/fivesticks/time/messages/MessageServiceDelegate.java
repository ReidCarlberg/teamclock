/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.messages;

import java.util.Collection;

public interface MessageServiceDelegate {

    public Message get(Long id) throws MessageServiceDelegateException;
    
    public void save(Message message) throws MessageServiceDelegateException;
    
    public Collection findAll() throws MessageServiceDelegateException;
    
    public Collection find(MessageCriteriaParameters params) throws MessageServiceDelegateException;
    
    public void delete(Message message) throws MessageServiceDelegateException;
    
    
}
