/*
 * Created on Aug 10, 2004 by Reid
 */
package com.fivesticks.time.timeclock.xwork;

import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.json.AbstractJSONAction;
import com.fivesticks.time.common.json.ObjectToJSONConverter;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.timeclock.TiimeclockBDFactory;
import com.fivesticks.time.timeclock.TimeclockBD;
import com.fivesticks.time.timeclock.TimeclockFilterParameters;
import com.fivesticks.time.timeclock.TimeclockFilterParametersBuilder;
import com.fivesticks.time.timeclock.TimeclockSearchBD;
import com.fivesticks.time.timeclock.TimeclockSearchBDException;
import com.fivesticks.time.timeclock.UserDateTimeclockStatusTypeEnum;
import com.fivesticks.time.timeclock.UserShiftDisplayWrapper;
import com.fivesticks.time.timeclock.util.PayPeriodDeterminator;
import com.fivesticks.time.timeclock.util.TimeclockDetailComparator;
import com.fivesticks.time.timeclock.util.TimeclockDetailComparatorFactory;
import com.fivesticks.time.timeclock.util.TimeclockDetailDisplayWrapper;
import com.fivesticks.time.timeclock.util.TimeclockDetailDisplayWrapperSet;
import com.fivesticks.time.timeclock.util.TimeclockDetailDisplayWrapperSetBuilder;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummary;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummaryBuilder;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.AuthenticatedUser;
import com.fstx.stdlib.authen.Authenticator;
import com.fstx.stdlib.authen.UnableToAuthenticateException;
import com.fstx.stdlib.common.simpledate.DatePair;
import com.fstx.stdlib.common.simpledate.SimpleDate;
import com.opensymphony.xwork.Action;

/**
 * Designed to be more JSON
 * 
 * 
 * 
 * @author Reid
 */
public class HandleTimeclockOnlyPageActionV2 extends AbstractJSONAction {

    private String username;

    private String password;

    private String punch;

    private String check;

    private String period;

    public String execute() throws Exception {

        boolean failed = false;

        AuthenticatedUser auser = null;

        SystemOwner systemOwner = null;

        JSONObject results = new JSONObject();

        if (!this.isValid()) {

            failed = true;
        } else {
            // attempt to authenticate

            try {
                auser = Authenticator.singleton.authenticate(
                        this.getUsername(), getPassword());
            } catch (UnableToAuthenticateException e) {
                failed = true;

                results.put("result", "failed-authenticate");
            }
        }

        if (!failed) {
            systemOwner = SystemOwnerServiceDelegateFactory.factory.build()
                    .get(auser.getUser());

            this.setSessionContext(new SessionContext(auser, systemOwner));

            if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.EXTERNAL) {

                failed = true;
            }

        }

        if (!failed) {
            TimeclockBD timeclockBD = TiimeclockBDFactory.factory
                    .build(this.getSessionContext());

            if (this.getPunch() != null) {

                boolean done = false;
                try {
                    timeclockBD.punchIn(auser.getUser(), this
                            .getSessionRemoteAddress());
                    done = true;
                } catch (Exception e) {

                }

                if (!done) {
                    try {
                        timeclockBD.punchOut(auser.getUser(), this
                                .getSessionRemoteAddress());
                        done = true;
                    } catch (Exception e) {
                        failed = true;
                    }
                }

            } else if (this.getPeriod() != null) {
                JSONObject periodSummary = this.prepareUserPeriodSummaryReport(
                        this.getSessionContext(), auser, this.getPeriod());
                results.put("periodSummary", periodSummary);
            }

            results.put("result", "success");

            JSONArray current = this.handleSummary(auser);
            results.put("summary", current);

            results.put("username", this.getUsername());

            UserDateTimeclockStatusTypeEnum s = timeclockBD.getCurrentState(
                    auser.getUser(), this.getSessionContext().getResolvedNow());

            results.put("currentStatus", s.getFriendlyName());

        } else if (failed && !results.has("result")) {
            results.put("result", "failed");
        }

        this.setJsonResult(results);

        return Action.SUCCESS;

    }

    private JSONArray handleSummary(AuthenticatedUser user) throws Exception {

        JSONArray ret = new JSONArray();

        TimeclockFilterParameters filter = new TimeclockFilterParametersBuilder()
                .buildUserForDate(user.getUser(), this.getSessionContext()
                        .getResolvedNow());

        Collection raw;
        try {
            raw = new TimeclockSearchBD(this.getSystemOwner())
                    .getRawRecords(filter);
        } catch (TimeclockSearchBDException e) {
            e.printStackTrace();
            return ret;// need to handle this.
        }

        // do the comparator
        TimeclockDetailComparator comparator = TimeclockDetailComparatorFactory.factory
                .buildDefault();

        // send to displayable builder

        TimeclockDetailDisplayWrapperSet display = new TimeclockDetailDisplayWrapperSetBuilder(
                this.getSystemOwner()).build(raw, comparator);

        for (Iterator iter = display.iterator(); iter.hasNext();) {
            TimeclockDetailDisplayWrapper element = (TimeclockDetailDisplayWrapper) iter
                    .next();
            JSONObject o = new ObjectToJSONConverter().convert(element);
            ret.put(o);
        }

        return ret;

    }

    private JSONObject prepareUserPeriodSummaryReport(
            SessionContext sessionContext, AuthenticatedUser auser,
            String periodType) throws Exception {
        PayPeriodDeterminator ppd = new PayPeriodDeterminator();

        /*
         * 2005-11-09 Resolved dates.
         */
        DatePair targetPeriod = ppd.getBySessionTypeAndDate(sessionContext,
                periodType, SimpleDate.factory.buildMidnight(
                        this.getSessionContext().getResolvedNow()).getDate());

        UserPayPeriodSummary userPayPeriodSummary = new UserPayPeriodSummaryBuilder()
                .buildAlreadyResolvedForUser(this.getSystemOwner(), auser
                        .getUser(), targetPeriod);

        JSONObject ret = new JSONObject();

        ret.put("periodType", periodType);
        ret.put("totalRoundedHours", userPayPeriodSummary.getSummary()
                .getTotalRoundedHours());

        JSONArray shifts = new JSONArray();

        for (Iterator iter = userPayPeriodSummary.getDisplayableShifts()
                .iterator(); iter.hasNext();) {
            UserShiftDisplayWrapper element = (UserShiftDisplayWrapper) iter
                    .next();

            JSONObject shiftSummary = new JSONObject();

            shiftSummary.put("shiftStart", element.getShiftStart());
            shiftSummary.put("shiftHours", element
                    .getActualHoursAndHundredths());
            shifts.put(shiftSummary);

        }

        ret.put("shifts", shifts);

        return ret;

    }

    public boolean isValid() {

        boolean ret = true;

        ret = (this.getUsername() != null && this.getPassword() != null && (this
                .getPunch() != null
                || this.getCheck() != null || this.getPeriod() != null));

        return ret;

    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPunch() {
        return punch;
    }

    public void setPunch(String punch) {
        this.punch = punch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the period.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     *            The period to set.
     */
    public void setPeriod(String period) {
        this.period = period;
    }

}