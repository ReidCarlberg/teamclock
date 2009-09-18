/*
 * Created on Sep 28, 2004 by Reid
 */
package com.fivesticks.time.timeclock.util;

import java.util.Collection;

import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class UserActivityPayPeriodSummary {

    private User user;

    private Collection displayableShifts;

    private UserShiftSummaryVO summary;

    /**
     * @return Returns the displayableShifts.
     */
    public Collection getDisplayableShifts() {
        return displayableShifts;
    }

    /**
     * @param displayableShifts
     *            The displayableShifts to set.
     */
    public void setDisplayableShifts(Collection displayableShifts) {
        this.displayableShifts = displayableShifts;
    }

    /**
     * @return Returns the summary.
     */
    public UserShiftSummaryVO getSummary() {
        return summary;
    }

    /**
     * @param summary
     *            The summary to set.
     */
    public void setSummary(UserShiftSummaryVO summary) {
        this.summary = summary;
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
}