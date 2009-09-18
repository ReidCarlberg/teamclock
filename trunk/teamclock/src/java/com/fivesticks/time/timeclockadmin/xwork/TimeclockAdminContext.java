/*
 * Created on Sep 29, 2004 by Reid
 */
package com.fivesticks.time.timeclockadmin.xwork;

import java.util.Date;

import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.DatePair;

/**
 * @author Reid
 */
public class TimeclockAdminContext {

    private User user;

    private DatePair payPeriod;

    private Date targetDate;

    public boolean isValid() {
        return this.getPayPeriod() != null && this.getUser() != null;
    }

    /**
     * @return Returns the payPeriod.
     */
    public DatePair getPayPeriod() {
        return payPeriod;
    }

    /**
     * @param payPeriod
     *            The payPeriod to set.
     */
    public void setPayPeriod(DatePair payPeriod) {
        this.payPeriod = payPeriod;
    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            The user to set.
     */
    public void setUser(User user) {
        this.user = user;
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
}