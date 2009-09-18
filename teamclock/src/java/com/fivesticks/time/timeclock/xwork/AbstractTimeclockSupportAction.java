/*
 * Created on Nov 27, 2005
 *
 */
package com.fivesticks.time.timeclock.xwork;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fivesticks.time.common.util.DateTimeRounderFactory;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.timeclock.util.PayPeriodDeterminator;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummary;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummaryBuilder;
import com.fstx.stdlib.common.simpledate.DatePair;

public abstract class AbstractTimeclockSupportAction extends
        SessionContextAwareSupport {

    /*
     * 2005-11-27 RSC added since a yser requested it. Note that NOT using the
     * getDateTimeInstance() resulted in improperly timezone adjusted times.
     */
    // private static final SimpleDateFormat sdf = new SimpleDateFormat(
    // "h:mm a M/d/y");
    DateFormat sdf = SimpleDateFormat.getDateTimeInstance();

    private UserPayPeriodSummary userPayPeriodSummary;

    public String getFormattedResolvedNow() {
        return sdf.format(this.getSessionContext().getResolvedNow());
    }

    public String getFormattedRoundedResolvedNow() {
        return sdf.format(DateTimeRounderFactory.factory.build(
                this.getSessionContext().getSettings()
                        .getTimeClockRounderType()).round(
                this.getSessionContext().getResolvedNow()));
    }

    public void initializeUserPayPeriodSummary(String periodType,
            Date targetDate) throws Exception {

        PayPeriodDeterminator ppd = new PayPeriodDeterminator();

        /*
         * 2005-11-09 TargetDate DOES NOT need to be resolved
         */
        DatePair targetPeriod = ppd.getBySessionTypeAndDate(this
                .getSessionContext(), periodType, targetDate);

        UserPayPeriodSummary userPayPeriodSummary = new UserPayPeriodSummaryBuilder()
                .buildAlreadyResolvedForUser(this.getSystemOwner(), this
                        .getSessionContext().getUser().getUser(), targetPeriod);

        this.setUserPayPeriodSummary(userPayPeriodSummary);
    }

    /**
     * @return Returns the userPayPeriodSummary.
     */
    public UserPayPeriodSummary getUserPayPeriodSummary() {
        return userPayPeriodSummary;
    }

    /**
     * @param userPayPeriodSummary
     *            The userPayPeriodSummary to set.
     */
    public void setUserPayPeriodSummary(
            UserPayPeriodSummary userPayPeriodSummary) {
        this.userPayPeriodSummary = userPayPeriodSummary;
    }



}
