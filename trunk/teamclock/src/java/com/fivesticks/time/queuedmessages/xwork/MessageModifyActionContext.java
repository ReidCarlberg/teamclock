/*
 * Created on Dec 19, 2004 by REID
 */
package com.fivesticks.time.queuedmessages.xwork;

import com.fivesticks.time.queuedmessages.QueuedMessage;

/**
 * @author REID
 */
public class MessageModifyActionContext {

    private QueuedMessage target;

    /**
     * @return Returns the target.
     */
    public QueuedMessage getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(QueuedMessage target) {
        this.target = target;
    }
}