/*
 * Created on Dec 19, 2004 by REID
 */
package com.fivesticks.time.queuedmessages.xwork;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.queuedmessages.QueuedMessageFilter;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author REID
 */
public class MessageListAction extends ActionSupport implements
        SessionContextAware {

    private SessionContext sessionContext;

    private QueuedMessageFilter filter = new QueuedMessageFilter();

    private Collection messages;

    private String submitSearch;

    private String submitReset;

    public String execute() throws Exception {

        if (this.getSubmitSearch() == null && this.getSubmitReset() == null)
            return SUCCESS;
        else if (this.getSubmitReset() != null) {
            filter = new QueuedMessageFilter();
            return SUCCESS;
        }

        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory
                .build(this.getSessionContext());

        /*
         * only worring about unsent messages so far.
         */
        this.getFilter().setSent(false);

        this.setMessages(sd.search(this.getFilter()));

        return SUCCESS;
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * @return Returns the filter.
     */
    public QueuedMessageFilter getFilter() {
        return filter;
    }

    /**
     * @param filter
     *            The filter to set.
     */
    public void setFilter(QueuedMessageFilter filter) {
        this.filter = filter;
    }

    /**
     * @return Returns the messages.
     */
    public Collection getMessages() {
        return messages;
    }

    /**
     * @param messages
     *            The messages to set.
     */
    public void setMessages(Collection messages) {
        this.messages = messages;
    }

    /**
     * @return Returns the submitReset.
     */
    public String getSubmitReset() {
        return submitReset;
    }

    /**
     * @param submitReset
     *            The submitReset to set.
     */
    public void setSubmitReset(String submitReset) {
        this.submitReset = submitReset;
    }

    /**
     * @return Returns the submitSearch.
     */
    public String getSubmitSearch() {
        return submitSearch;
    }

    /**
     * @param submitSearch
     *            The submitSearch to set.
     */
    public void setSubmitSearch(String submitSearch) {
        this.submitSearch = submitSearch;
    }
}