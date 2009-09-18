/*
 * Created on Dec 3, 2004
 *
 * 
 */
package com.fivesticks.time.payperiodproductivity.xwork;

import java.util.Date;

import com.fstx.stdlib.authen.users.User;

/**
 * @author Nick
 * 
 *  
 */
public class DailyActivityVO {
    private User user;

    private Date date;

    private double activityHours;

    public double getActivityHours() {
        return activityHours;
    }

    public void setActivityHours(double activityHours) {
        this.activityHours = activityHours;
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
}