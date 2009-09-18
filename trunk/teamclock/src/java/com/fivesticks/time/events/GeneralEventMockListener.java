/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.events;

/**
 * @author REID
 */
public class GeneralEventMockListener implements EventListener {

    boolean told;
    /* (non-Javadoc)
     * @see com.fivesticks.time.events.EventListener#tell(com.fivesticks.time.events.GeneralEvent)
     */
    public void tell(GeneralEvent event) throws EventListenerException {
        this.setTold(true);
    }

    /**
     * @return Returns the told.
     */
    public boolean isTold() {
        return told;
    }
    /**
     * @param told The told to set.
     */
    public void setTold(boolean told) {
        this.told = told;
    }
}
