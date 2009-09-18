/*
 * Created on Dec 3, 2004
 *
 * 
 */
package com.fivesticks.time.payperiodproductivity.xwork;

import java.util.Date;

import com.fivesticks.time.timeclock.UserShiftDisplayWrapper;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Nick
 * 
 *  
 */
public class PayPeriodReportVO {

    private Date date;

    private User user;

    private UserShiftDisplayWrapper userShiftDisplayWrapper;

    private DailyActivityVO activityVO;

    public double getActivity() {
        return this.getDailyActivityVO().getActivityHours();
    }

    public String getTimeclock() {
        return this.getUserShiftDisplayWrapper().getActualHoursAndHundredths();
    }

    public double getProductivity() {

        double act = this.getDailyActivityVO().getActivityHours();
        double time = (((double) this.getUserShiftDisplayWrapper()
                .getRawMinutes()) / 60);

        if (act == 0 || time == 0) {
            return 0;
        }

        double d = act / time;

        return d;

    }

    public DailyActivityVO getDailyActivityVO() {
        return activityVO;
    }

    public void setDailyActivityVO(DailyActivityVO activityVO) {
        this.activityVO = activityVO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserShiftDisplayWrapper getUserShiftDisplayWrapper() {
        return userShiftDisplayWrapper;
    }

    public void setUserShiftDisplayWrapper(
            UserShiftDisplayWrapper userShiftDisplayWrapper) {
        this.userShiftDisplayWrapper = userShiftDisplayWrapper;
    }
}