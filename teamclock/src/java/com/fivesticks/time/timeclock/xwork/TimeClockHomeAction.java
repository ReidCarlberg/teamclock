/*
 * Created on Sep 16, 2004 by Reid
 */
package com.fivesticks.time.timeclock.xwork;

import java.util.Collection;

import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.timeclock.UserDateTimeclockStatusTypeEnum;
import com.fivesticks.time.timeclock.UserShiftCollectionBuilderException;
import com.fivesticks.time.timeclock.UserShiftRecordBuilder;
import com.fivesticks.time.timeclock.util.TimeclockDetailDisplayWrapperSetBuilder;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummary;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummaryBuilder;
import com.fstx.stdlib.common.simpledate.DatePair;

/**
 * @author Reid
 */
public class TimeClockHomeAction extends AbstractTimeclockSupportAction {

    private Collection display;

    private boolean showPunchIn;

    private boolean showBreakStart;

    private boolean showBreakStop;

    private boolean showPunchOut;

//    private static Log log = LogFactory.getLog(TimeClockHomeAction.class);

    public String execute() throws Exception {

        this.getSessionContext().setMenuSection(MenuSection.TIMECLOCK);

        /*
         * factored out on 2005-01-17
         * 
         * 2005-11-09 RSC needs to have the resolved date.
         */
        this.setDisplay(new TimeclockDetailDisplayWrapperSetBuilder(this
                .getSystemOwner())
                .buildStandard(this.getSessionContext().getUser().getUser(),
                        this.getSessionContext().getResolvedNow()));

        try {
            handlePromptSwitches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.initializeUserPayPeriodSummary("current",this.getSessionContext().getResolvedNow());

        return SUCCESS;
    }

    /*
     * reid 2005-11-03 Removed. Don't seem to be using.
     * reid 2005-11-28 Oh, yes we were using.  On the time clock home page and on the dashboard.
     */
    public UserPayPeriodSummary getTodaySummary()
            throws UserShiftCollectionBuilderException {

        DatePair targetPeriod = new DatePair(this.getSessionContext()
                .getResolvedNow(), this.getSessionContext().getResolvedNow());
        /*
         * 
         */

        UserPayPeriodSummary userPayPeriodSummary = new UserPayPeriodSummaryBuilder()
                .buildAlreadyResolvedForUser(this.getSystemOwner(), this
                        .getSessionContext().getUser().getUser(), targetPeriod);

        return userPayPeriodSummary;

    }

    /**
     * 
     */
    private void handlePromptSwitches() throws Exception {

        /*
         * 2005-11-03 Reid just have a new Date(); Should have a timezone
         * resolved data.
         */

        
        UserDateTimeclockStatusTypeEnum status = new UserShiftRecordBuilder(
                this.getSystemOwner()).buildByResolvedStartDate(
                this.getSessionContext().getUser().getUser(),
                this.getSessionContext().getResolvedNow()).getStatus();

        if (status == UserDateTimeclockStatusTypeEnum.NO_RECORD
                || status == UserDateTimeclockStatusTypeEnum.CLOCKED_OUT) {
            this.setShowPunchIn(true);
        } else if (status == UserDateTimeclockStatusTypeEnum.CLOCKED_IN) {
            this.setShowBreakStart(true);
            this.setShowPunchOut(true);
        } else if (status == UserDateTimeclockStatusTypeEnum.BREAK) {
            this.setShowBreakStop(true);
        }
    }

    /**
     * @return Returns the display.
     */
    public Collection getDisplay() {
        return display;
    }

    /**
     * @param display
     *            The display to set.
     */
    public void setDisplay(Collection display) {
        this.display = display;
    }

    /**
     * @return Returns the showBreakStart.
     */
    public boolean isShowBreakStart() {
        return showBreakStart;
    }

    /**
     * @param showBreakStart
     *            The showBreakStart to set.
     */
    public void setShowBreakStart(boolean showBreakStart) {
        this.showBreakStart = showBreakStart;
    }

    /**
     * @return Returns the showBreakStop.
     */
    public boolean isShowBreakStop() {
        return showBreakStop;
    }

    /**
     * @param showBreakStop
     *            The showBreakStop to set.
     */
    public void setShowBreakStop(boolean showBreakStop) {
        this.showBreakStop = showBreakStop;
    }

    /**
     * @return Returns the showPunchIn.
     */
    public boolean isShowPunchIn() {
        return showPunchIn;
    }

    /**
     * @param showPunchIn
     *            The showPunchIn to set.
     */
    public void setShowPunchIn(boolean showPunchIn) {
        this.showPunchIn = showPunchIn;
    }

    /**
     * @return Returns the showPunchOut.
     */
    public boolean isShowPunchOut() {
        return showPunchOut;
    }

    /**
     * @param showPunchOut
     *            The showPunchOut to set.
     */
    public void setShowPunchOut(boolean showPunchOut) {
        this.showPunchOut = showPunchOut;
    }

}