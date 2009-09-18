/*
 * Created on Sep 16, 2004 by Reid
 */
package com.fivesticks.time.timeclock.xwork;

import java.text.SimpleDateFormat;

import com.fivesticks.time.dashboard.util.DashboardMiscellaneousRecentAction;
import com.fivesticks.time.timeclock.TiimeclockBDFactory;
import com.fivesticks.time.timeclock.TimeclockBDException;
import com.fivesticks.time.ws.xwork.AbstractJSONAction;

/**
 * @author Reid
 */
public class BreakStopAction extends AbstractJSONAction {

    

    private String timeclockResult;

    public String execute() throws Exception {
        try {
            TiimeclockBDFactory.factory.build(this.getSessionContext()).breakStop(
                    this.getSessionContext().getUser().getUser(), this.getSessionRemoteAddress());
            this.setTimeclockResult("Break stopped successfully.");
        } catch (TimeclockBDException e) {
            this.setTimeclockResult("Unable to stop break.");
        }
        
        DashboardMiscellaneousRecentAction a = new DashboardMiscellaneousRecentAction();
        a.setTypeName("Time clock: ");
        a.setDescription("You have stopped a break.  Time: " + SimpleDateFormat.getDateTimeInstance().format(this.getSessionContext().getResolvedNow()));

        this.getSessionContext().addToRecentActions(a);
        
        return SUCCESS;
    }



    /**
     * @return Returns the timeclockResult.
     */
    public String getTimeclockResult() {
        return timeclockResult;
    }

    /**
     * @param timeclockResult
     *            The timeclockResult to set.
     */
    public void setTimeclockResult(String timeclockResult) {
        this.timeclockResult = timeclockResult;
    }
}