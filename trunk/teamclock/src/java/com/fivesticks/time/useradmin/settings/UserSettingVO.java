package com.fivesticks.time.useradmin.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.util.TimeZoneListBuilder;

/**
 * 2005-10-6 This is a VO to contain all the settings. This must be coordinated
 * with the UserSetingType.
 * 
 * @author Nick
 */
public class UserSettingVO {

    private String loginDestination;
    
    private String calendarNotificationOnOrOff;

    private String calendarNotificationEmailAddress;

    private String calendarDefaultDashboardView;

    private String calendarDefaultTabView;

    private String showTimeClockStatus;

    private String userTimeZone;

    private String token;
    
    public String getCalendarDefaultDashboardView() {

        return calendarDefaultDashboardView;
    }

    public void setCalendarDefaultDashboardView(
            String calendarDefaultDashboardView) {
        this.calendarDefaultDashboardView = calendarDefaultDashboardView;
    }

    public String getCalendarDefaultTabView() {
        /*
         * If we don't have anything set, default to DAY
         */
        if (!this.isDefaultTabViewDaily() && !this.isDefaultTabViewWeekly()
                && !this.isDefaultTabViewMonthly()) {
            return DAY;
        }

        return calendarDefaultTabView;
    }

    public void setCalendarDefaultTabView(String calendarDefaultTabView) {
        this.calendarDefaultTabView = calendarDefaultTabView;
    }

    public String getCalendarNotificationEmailAddress() {
        return calendarNotificationEmailAddress;
    }

    public void setCalendarNotificationEmailAddress(
            String calendarNotificationEmailAddress) {
        this.calendarNotificationEmailAddress = calendarNotificationEmailAddress;
    }

    public String getCalendarNotificationOnOrOff() {
        return calendarNotificationOnOrOff;
    }

    public void setCalendarNotificationOnOrOff(
            String calendarNotificationOnOrOff) {
        this.calendarNotificationOnOrOff = calendarNotificationOnOrOff;
    }

    public String getShowTimeClockStatus() {
        return showTimeClockStatus;
    }

    public void setShowTimeClockStatus(String showTimeClockStatus) {
        this.showTimeClockStatus = showTimeClockStatus;
    }

    public Collection getStandardCalendarNotificationOnOrOffOptions() {
        Collection ret = new ArrayList();
        ret.add("On");
        ret.add("Off");
        return ret;

    }

    public Collection getStandardShowTimeClockStatusOptions() {
        Collection ret = new ArrayList();
        ret.add("Yes");
        ret.add("No");
        return ret;

    }

    public boolean isDefaultDashboardViewWeekly() {
        return this.calendarDefaultDashboardView != null
                && this.calendarDefaultDashboardView.equals(WEEK);
    }

    public boolean isDefaultDashboardViewDaily() {
        return this.calendarDefaultDashboardView != null
                && this.calendarDefaultDashboardView.equals(DAY);
    }

    public boolean isDefaultTabViewWeekly() {
        return this.calendarDefaultTabView != null
                && this.calendarDefaultTabView.equals(WEEK);
    }

    public boolean isDefaultTabViewDaily() {
        return this.calendarDefaultTabView != null
                && this.calendarDefaultTabView.equals(DAY);
    }

    public boolean isDefaultTabViewMonthly() {
        return this.calendarDefaultTabView != null
                && this.calendarDefaultTabView.equals(MONTH);
    }

    public Collection getStandardCalendarDefaultDashboardViewOptions() {
        Collection ret = new ArrayList();
        ret.add(DAY);
        ret.add(WEEK);

        return ret;
    }

    public Collection getStandardCalendarDefaultTabViewOptions() {
        Collection ret = new ArrayList();

        ret.add(DAY);
        ret.add(WEEK);
        ret.add(MONTH);
        return ret;
    }

    public Collection getStandardTimeZoneOptions() {
        return TimeZoneListBuilder.singleton.build();
    }

    /*
     * These values propogate to the JSP as well as the xwork.xml to direct the
     * the default view. see CalendarDefaultGatewayAction if these are changed.
     */
    public static String DAY = "Day";

    public static String WEEK = "Week";

    public static String MONTH = "Month";

    public boolean isDefaultToShowingTimeClockStatus() {
        if (this.getShowTimeClockStatus() != null
                && this.getShowTimeClockStatus().equals("Yes")) {
            return true;
        }

        return false;
    }

    public String getUserTimeZone() {
        return userTimeZone;
    }

    public void setUserTimeZone(String userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    /*
     * This will not only check null or emptt, but if the tz is valid.
     */
    public boolean isTimeZoneSet() {
        if (!StringUtils.hasText(this.getUserTimeZone())) {
            return false;
        }

        TimeZone tx = TimeZone.getTimeZone(this.getUserTimeZone());
        return tx != null && tx.getID().equals(this.getUserTimeZone());
    }

    /**
     * @return Returns the userToken.
     */
    public String getToken() {
        return token;
    }

    /**
     * @param userToken The userToken to set.
     */
    public void setToken(String userToken) {
        this.token = userToken;
    }

    /**
     * @return Returns the loginDestination.
     */
    public String getLoginDestination() {
        return loginDestination;
    }

    /**
     * @param loginDestination The loginDestination to set.
     */
    public void setLoginDestination(String loginDestination) {
        this.loginDestination = loginDestination;
    }

}
