/*
 * Created on Dec 15, 2004 by REID
 */
package com.fivesticks.time.queuedmessages;

import org.joda.time.DateTime;

/**
 * @author REID
 */
public class QueuedMessageFilter extends QueuedMessage {

    private String messageLike;

    private DateTime sendTimeRangeStart;

    private DateTime sendTimeRangeStop;

    private Boolean booleanSent;

    /**
     * @return Returns the messageLike.
     */
    public String getMessageLike() {
        return messageLike;
    }

    /**
     * @param messageLike
     *            The messageLike to set.
     */
    public void setMessageLike(String messageLike) {
        this.messageLike = messageLike;
    }



    /**
     * @return Returns the sent.
     */
    public Boolean getBooleanSent() {
        return booleanSent;
    }

    /**
     * @param sent
     *            The sent to set.
     */
    public void setBooleanSent(Boolean sent) {
        this.booleanSent = sent;
    }

    public DateTime getSendTimeRangeStart() {
        return sendTimeRangeStart;
    }

    public void setSendTimeRangeStart(DateTime sendTimeRangeStart) {
        this.sendTimeRangeStart = sendTimeRangeStart;
    }

    public DateTime getSendTimeRangeStop() {
        return sendTimeRangeStop;
    }

    public void setSendTimeRangeStop(DateTime sendTimeRangeStop) {
        this.sendTimeRangeStop = sendTimeRangeStop;
    }
}