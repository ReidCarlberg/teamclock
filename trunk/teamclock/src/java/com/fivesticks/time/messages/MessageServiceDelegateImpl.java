/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.messages;

import java.util.Collection;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;

public class MessageServiceDelegateImpl extends AbstractServiceDelegate
        implements MessageServiceDelegate {

    public Message get(Long id) throws MessageServiceDelegateException {

        Message ret = (Message) this.getDao().get(id);

        try {
            this.handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
            throw new MessageServiceDelegateException(e);
        }

        return ret;

    }

    public void save(Message message) throws MessageServiceDelegateException {

        try {
            this.handleDecorate(message);
        } catch (AbstractServiceDelegateException e) {
            throw new MessageServiceDelegateException(e);
        }

        this.getDao().save(message);

    }

    public Collection findAll() throws MessageServiceDelegateException {

        return find(new MessageCriteriaParameters());
    }

    public Collection find(MessageCriteriaParameters params)
            throws MessageServiceDelegateException {

        try {
            this.handleDecorate(params);
        } catch (AbstractServiceDelegateException e) {
            throw new MessageServiceDelegateException(e);
        }

        return this.getDao().find(params);
    }

    public void delete(Message message) throws MessageServiceDelegateException {

        try {
            this.handleValidate(message);
        } catch (AbstractServiceDelegateException e) {
            throw new MessageServiceDelegateException(e);
        }

        this.getDao().delete(message);

    }

}
