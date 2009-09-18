/*
 * Created on Dec 1, 2005
 *
 */
package com.fivesticks.time.timeclock.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fivesticks.time.common.util.DateTimeRounderFactory;
import com.fivesticks.time.timeclock.TiimeclockBDFactory;
import com.fivesticks.time.timeclock.TimeclockBD;
import com.fivesticks.time.timeclock.TimeclockBDException;
import com.fivesticks.time.timeclock.TimeclockEventEnum;
import com.fivesticks.time.timeclock.UserShiftCollectionBuilderException;
import com.fivesticks.time.timeclock.UserShiftRecord;
import com.fivesticks.time.timeclock.UserShiftRecordBuilder;
import com.fivesticks.time.timeclock.UserShiftRecordBuilderException;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummary;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummaryBuilder;
import com.fivesticks.time.ws.AbstractAuthBasedAuthenticationService;
import com.fivesticks.time.ws.xwork.AbstractRestAction;
import com.fstx.stdlib.common.simpledate.DatePair;

/*
 * 2005-12-01 RSC
 * 
 */
public class RestTimeclockAction extends
        AbstractRestAction {

    private TimeclockEventEnum timeclockAction;

    private String currentStatus;

    private String localizedTime;

    private String roundedLocalizedTime;

    private String shiftTime;





    protected void handleActionTypeValidate() {


        this.timeclockAction = TimeclockEventEnum.getByName(this
                .getAction());

        if (this.timeclockAction == null) {
            throw new RuntimeException();
        }

    }



    protected void handleAction() throws Exception {
        TimeclockBD timeclock = TiimeclockBDFactory.factory.build(this
                .getAuthenticationBasedServiceSupport().getSessionContext());

        try {
            timeclock.handleEvent(this.getAuthenticationBasedServiceSupport()
                    .getUser(), this.timeclockAction, this.getSessionRemoteAddress());
        } catch (TimeclockBDException e) {
            this.setResult(AbstractAuthBasedAuthenticationService.FAILED_EVENT);
            this.setVerboseResult(e.getMessage());
            
        }
        
        // status
        handleStatus();

        // time
        handleTime();
    }

    private void handleStatus() {
        UserShiftRecord shift = null;

        try {
            shift = new UserShiftRecordBuilder(this
                    .getAuthenticationBasedServiceSupport().getSystemOwner())
                    .buildByResolvedStartDate(this
                            .getAuthenticationBasedServiceSupport().getUser(),
                            this.getAuthenticationBasedServiceSupport()
                                    .getSessionContext().getResolvedNow());
        } catch (UserShiftRecordBuilderException e) {
            this
                    .setResult(AbstractAuthBasedAuthenticationService.FAILED_USER_SHIFT_BUIDLER);
            throw new RuntimeException();
        }
        this.setCurrentStatus(shift.getStatus().getFriendlyName());

        DatePair targetPeriod = new DatePair(this
                .getAuthenticationBasedServiceSupport().getSessionContext()
                .getResolvedNow(), this.getAuthenticationBasedServiceSupport()
                .getSessionContext().getResolvedNow());
        /*
         * 
         */

        UserPayPeriodSummary userPayPeriodSummary;
        try {
            userPayPeriodSummary = new UserPayPeriodSummaryBuilder()
                    .buildAlreadyResolvedForUser(this
                            .getAuthenticationBasedServiceSupport()
                            .getSystemOwner(), this
                            .getAuthenticationBasedServiceSupport()
                            .getSessionContext().getUser().getUser(),
                            targetPeriod);
        } catch (UserShiftCollectionBuilderException e) {
            throw new RuntimeException(e);
        }

        this.setShiftTime(userPayPeriodSummary.getSummary()
                .getTotalRoundedHours());
    }

    private void handleTime() {
        DateFormat sdf = SimpleDateFormat.getDateTimeInstance();

        this.setLocalizedTime(sdf.format(this
                .getAuthenticationBasedServiceSupport().getSessionContext()
                .getResolvedNow()));

        this.setRoundedLocalizedTime(sdf.format(DateTimeRounderFactory.factory.build(
                this.getAuthenticationBasedServiceSupport().getSessionContext()
                        .getSettings().getTimeClockRounderType()).round(
                this.getAuthenticationBasedServiceSupport().getSessionContext()
                        .getResolvedNow())));

    }

    /**
     * @return Returns the currentStatus.
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * @param currentStatus
     *            The currentStatus to set.
     */
    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    /**
     * @return Returns the localizedTime.
     */
    public String getLocalizedTime() {
        return localizedTime;
    }

    /**
     * @param localizedTime
     *            The localizedTime to set.
     */
    public void setLocalizedTime(String localizedTime) {
        this.localizedTime = localizedTime;
    }

    /**
     * @return Returns the roundedLocalizedTime.
     */
    public String getRoundedLocalizedTime() {
        return roundedLocalizedTime;
    }

    /**
     * @param roundedLocalizedTime
     *            The roundedLocalizedTime to set.
     */
    public void setRoundedLocalizedTime(String roundedLocalizedTime) {
        this.roundedLocalizedTime = roundedLocalizedTime;
    }

    /**
     * @return Returns the shiftTime.
     */
    public String getShiftTime() {
        return shiftTime;
    }

    /**
     * @param shiftTime
     *            The shiftTime to set.
     */
    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }
}
