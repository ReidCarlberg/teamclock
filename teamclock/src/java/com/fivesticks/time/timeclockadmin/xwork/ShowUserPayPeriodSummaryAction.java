/*
 * Created on Sep 28, 2004 by Reid
 */
package com.fivesticks.time.timeclockadmin.xwork;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.timeclock.util.PayPeriodDeterminator;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummary;
import com.fivesticks.time.timeclock.util.UserPayPeriodSummaryBuilder;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.fstx.stdlib.common.simpledate.DatePair;

/**
 * @author Reid
 */
public class ShowUserPayPeriodSummaryAction extends SessionContextAwareSupport
        implements TimeclockAdminContextAware {

    private String targetUser;

    private String targetPeriod;

    private Date targetDate;

    private UserPayPeriodSummary userPayPeriodSummary;

    private TimeclockAdminContext timeclockAdminContext;

    public String execute() throws Exception {

        /*
         * 2006-06-28 reid
         */
        if (!StringUtils.hasText(this.getTargetUser()) && this.getTimeclockAdminContext().getUser() == null) {
            this.setTargetUser(this.getSessionContext().getUser().getUsername());
            this.getTimeclockAdminContext().setUser(this.getSessionContext().getUser().getUser());
        }
        
        if (this.getTargetDate() == null && this.getTargetPeriod() == null
                && !this.getTimeclockAdminContext().isValid()) {
            return INPUT;
        }

        User user = null;
        DatePair targetPayPeriod = null;

        if (this.getTargetUser() != null && this.getTargetUser().length() >0) {
            user = UserBDFactory.factory.build().getByUsername(
                    this.getTargetUser());

            PayPeriodDeterminator ppd = new PayPeriodDeterminator();

            if (this.getTargetPeriod() == null
                    || this.getTargetPeriod().trim().length() == 0)
                this.setTargetPeriod("Current");

            targetPayPeriod = ppd.getBySessionTypeAndDate(this
                    .getSessionContext(), this.getTargetPeriod(), this
                    .getTargetDate());

            this.getTimeclockAdminContext().setUser(user);
            this.getTimeclockAdminContext().setPayPeriod(targetPayPeriod);

        } else {
            user = this.getTimeclockAdminContext().getUser();
            targetPayPeriod = this.getTimeclockAdminContext().getPayPeriod();
        }

        this.setUserPayPeriodSummary(new UserPayPeriodSummaryBuilder()
                .buildAlreadyResolvedForUser(this.getSystemOwner(), user,
                        targetPayPeriod));

        return SUCCESS;
    }

    /**
     * @return Returns the targetDate.
     */
    public Date getTargetDate() {
        return targetDate;
    }

    /**
     * @param targetDate
     *            The targetDate to set.
     */
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * @return Returns the targetPeriod.
     */
    public String getTargetPeriod() {
        return targetPeriod;
    }

    /**
     * @param targetPeriod
     *            The targetPeriod to set.
     */
    public void setTargetPeriod(String targetPeriod) {
        this.targetPeriod = targetPeriod;
    }

    /**
     * @return Returns the targetUser.
     */
    public String getTargetUser() {
        return targetUser;
    }

    /**
     * @param targetUser
     *            The targetUser to set.
     */
    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
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
     * @return Returns the timeclockAdminContext.
     */
    public TimeclockAdminContext getTimeclockAdminContext() {
        return timeclockAdminContext;
    }

    /**
     * @param timeclockAdminContext
     *            The timeclockAdminContext to set.
     */
    public void setTimeclockAdminContext(
            TimeclockAdminContext timeclockAdminContext) {
        this.timeclockAdminContext = timeclockAdminContext;
    }
}