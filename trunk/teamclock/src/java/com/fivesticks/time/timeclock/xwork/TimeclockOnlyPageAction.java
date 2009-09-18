/*
 * Created on Aug 10, 2004 by Reid
 */
package com.fivesticks.time.timeclock.xwork;

import java.util.Collection;

import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.timeclock.TiimeclockBDFactory;
import com.fivesticks.time.timeclock.TimeclockBD;
import com.fivesticks.time.timeclock.TimeclockFilterParameters;
import com.fivesticks.time.timeclock.TimeclockFilterParametersBuilder;
import com.fivesticks.time.timeclock.TimeclockSearchBD;
import com.fivesticks.time.timeclock.TimeclockSearchBDException;
import com.fivesticks.time.timeclock.util.PayPeriodDeterminator;
import com.fivesticks.time.timeclock.util.TimeclockDetailComparator;
import com.fivesticks.time.timeclock.util.TimeclockDetailComparatorFactory;
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

/**
 * <p>
 * Replaces the Struts dedicated login pages.
 * 
 * @author Reid
 */
public class TimeclockOnlyPageAction extends AbstractTimeclockSupportAction {

//    private Log log = LogFactory.getLog(TimeclockOnlyPageAction.class);

    public static final String SUCCESS_PUNCHIN = "punchinsuccess";

    public static final String SUCCESS_PUNCHOUT = "punchoutsuccess";

    private String action;
    
    private String username;

    private String password;

    private String punchInButton;

    private String punchOutButton;

    private String quickReportButton;

    private String showThisPeriod;

    private String showLastPeriod;

    private String message = "test dadada";

    private String target;

    private TimeclockDetailDisplayWrapperSet display;

    private UserPayPeriodSummary userPayPeriodSummary;

    public String execute() throws Exception {

        this.getSessionContext().setMenuSection(MenuSection.TIMECLOCK_ONLY);

        if (this.getPunchInButton() == null && this.getPunchOutButton() == null
                && this.getQuickReportButton() == null
                && this.getShowLastPeriod() == null
                && this.getShowThisPeriod() == null) {

            if (this.getTarget() != null) {
                SystemOwner selected = SystemOwnerServiceDelegateFactory.factory
                        .build().get(this.getTarget());

                if (selected != null) {
                    if (!selected.isActivated())
                        return GlobalForwardStatics.GLOBAL_INACTIVE_ACCOUNT;

                    LogFactory.getLog(this.getClass()).info(selected);
                    this.getSessionContext().setSystemOwner(selected);
                }
            }

            return INPUT;
        }

        if (this.getUsername() == null
                || this.getUsername().trim().length() == 0) {
            this.addFieldError("username", "Username is required.");
        }

        if (this.getPassword() == null
                || this.getPassword().trim().length() == 0) {
            this.addFieldError("password", "Password is required.");
        }

        if (this.hasErrors())
            return INPUT;

        AuthenticatedUser auser = null;

        try {
            auser = Authenticator.singleton.authenticate(this.getUsername(),
                    getPassword());
        } catch (UnableToAuthenticateException e) {
            this
                    .addActionError("Could not authenticate that username and password.");
            return INPUT;
        }

        /*
         * really only works if it has a target
         */
        SystemOwner systemOwner = null;

        if (this.getTarget() != null) {
            systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                    this.getTarget());
        } else {
            systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                    auser.getUser());
        }

        if (systemOwner == null) {
            this
                    .addActionError("Could not determine system owner.  Trying using the link with your system owner key.  See your administrator.");
            return INPUT;

        }

        this.setSessionContext(new SessionContext(auser, systemOwner));

        if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.EXTERNAL) {
            this
                    .addActionError("Could not authenticate that username and password for time clock use.");
            return INPUT;
        }

        /*
         * reid 2005-11-12 refactored
         */
        TimeclockBD timeclockBD = TiimeclockBDFactory.factory.build(this.getSessionContext());
        
        if (this.getPunchInButton() != null) {
            // they want to log in.
            try {
                timeclockBD.punchIn(
                        auser.getUser(), this.getSessionRemoteAddress());
                // for now
            } catch (Exception e) {

                this.message = "You are already punched in! " + e.getMessage();
                return ERROR;
            }
            this.message = "Punch In Success!";
            return SUCCESS_PUNCHIN;

        } else if (this.getPunchOutButton() != null) {
            // they want to log out.
            try {
                timeclockBD.punchOut(
                        auser.getUser(), this.getSessionRemoteAddress());
            } catch (Exception e) {

                this.message = "You are already punched out!";
                return ERROR;
            }

            this.message = "Punch Out Success!";
            return SUCCESS_PUNCHOUT;

        } else if (this.getShowLastPeriod() != null
                || this.getShowThisPeriod() != null) {
            if (this.getSessionContext().getSettings()
                    .getTimeClockPayPeriodType() == null
                    || this.getSessionContext().getSettings()
                            .getTimeClockPayPeriodRefDate() == null) {
                this.addActionError("Not configured to handle this report.");
            } else {
                this.prepareUserPeriodSummaryReport(this.getSessionContext(),
                        auser);
                return SUCCESS + ".userPeriod";
            }
        } else {
            this.prepareQuickReport(auser);
            return "report";
        }

        return INPUT;
    }

    /**
     * @param sessionContext
     *            
     * @param auser
     */
    private void prepareUserPeriodSummaryReport(SessionContext sessionContext,
            AuthenticatedUser auser) throws Exception {
        PayPeriodDeterminator ppd = new PayPeriodDeterminator();

        String periodType = "current";

        if (this.getShowLastPeriod() != null)
            periodType = "previous";

        /*
         * 2005-11-09 Resolved dates.
         */
        DatePair targetPeriod = ppd.getBySessionTypeAndDate(sessionContext,
                periodType, SimpleDate.factory.buildMidnight(
                        this.getSessionContext().getResolvedNow()).getDate());

        UserPayPeriodSummary userPayPeriodSummary = new UserPayPeriodSummaryBuilder()
                .buildAlreadyResolvedForUser(this.getSystemOwner(), auser.getUser(),
                        targetPeriod);

        this.setUserPayPeriodSummary(userPayPeriodSummary);
    }

    private void prepareQuickReport(AuthenticatedUser user) {

        TimeclockFilterParameters filter = new TimeclockFilterParametersBuilder()
                .buildUserForDate(user.getUser(), this.getSessionContext().getResolvedNow());

        Collection raw;
        try {
            raw = new TimeclockSearchBD(this.getSystemOwner())
                    .getRawRecords(filter);
        } catch (TimeclockSearchBDException e) {
            e.printStackTrace();
            return;// need to handle this.
        }

        // do the comparator
        TimeclockDetailComparator comparator = TimeclockDetailComparatorFactory.factory
                .buildDefault();

        // send to displayable builder

        TimeclockDetailDisplayWrapperSet display = new TimeclockDetailDisplayWrapperSetBuilder(
                this.getSystemOwner()).build(raw, comparator);

        this.setDisplay(display);

    }

    /**
     * @return Returns the loginButton.
     */
    public String getPunchInButton() {
        return punchInButton;
    }

    /**
     * @param loginButton
     *            The loginButton to set.
     */
    public void setPunchInButton(String loginButton) {
        this.punchInButton = loginButton;
    }

    /**
     * @return Returns the logoutButton.
     */
    public String getPunchOutButton() {
        return punchOutButton;
    }

    /**
     * @param logoutButton
     *            The logoutButton to set.
     */
    public void setPunchOutButton(String logoutButton) {
        this.punchOutButton = logoutButton;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the test.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param test
     *            The test to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return Returns the showLastPeriod.
     */
    public String getShowLastPeriod() {
        return showLastPeriod;
    }

    /**
     * @param showLastPeriod
     *            The showLastPeriod to set.
     */
    public void setShowLastPeriod(String showLastPeriod) {
        this.showLastPeriod = showLastPeriod;
    }

    /**
     * @return Returns the showThisPeriod.
     */
    public String getShowThisPeriod() {
        return showThisPeriod;
    }

    /**
     * @param showThisPeriod
     *            The showThisPeriod to set.
     */
    public void setShowThisPeriod(String showThisPeriod) {
        this.showThisPeriod = showThisPeriod;
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

    /**
     * @return Returns the target.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return Returns the display.
     */
    public TimeclockDetailDisplayWrapperSet getDisplay() {
        return display;
    }

    /**
     * @param display
     *            The display to set.
     */
    public void setDisplay(TimeclockDetailDisplayWrapperSet display) {
        this.display = display;
    }

    /**
     * @return Returns the quickReportButton.
     */
    public String getQuickReportButton() {
        return quickReportButton;
    }

    /**
     * @param quickReportButton
     *            The quickReportButton to set.
     */
    public void setQuickReportButton(String quickReportButton) {
        this.quickReportButton = quickReportButton;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}