/*
 * Created on Nov 8, 2005
 *
 */
package com.fivesticks.time.settings.broker;

import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.useradmin.settings.UserSettingVO;
import com.fstx.stdlib.authen.users.User;

public class UserSystemSettingsWrapper extends FstxTimeSettings {

    private final User user;
    private final SystemOwner systemOwner;
    
    private UserSettingVO userSettingVO;
    
    /**
     * @return Returns the userSettingVO.
     */
    public UserSettingVO getUserSettingVO() {
        return userSettingVO;
    }

    /**
     * @param userSettingVO The userSettingVO to set.
     */
    public void setUserSettingVO(UserSettingVO userSettingVO) {
        this.userSettingVO = userSettingVO;
    }

    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }

    public UserSystemSettingsWrapper(final User user, final SystemOwner systemOwner) {
        this.user = user;
        this.systemOwner = systemOwner;
    }
    
    /*
     * user time zone can override system timezone. (non-Javadoc)
     * @see com.fivesticks.time.settings.FstxTimeSettings#getSystemTimeZone()
     */
    public String getSystemTimeZone() {
        /*
         * All User setting should be available here.
         */
        UserSettingVO userSettingVO = this.getUserSettingVO();

        /*
         * Should Check if valie TimeZone.
         */
        if (userSettingVO != null && userSettingVO.isTimeZoneSet()) {
            return userSettingVO.getUserTimeZone();
        }

        return super.getSystemTimeZone();
    }
    
  /*
  * Delegated from the the User Settings.
  */
 public String getCalendarDefaultDashboardView() {
     return this.getUserSettingVO().getCalendarDefaultDashboardView();
 }

 public String getCalendarDefaultTabView() {
     return this.getUserSettingVO().getCalendarDefaultTabView();
 }

 public String getCalendarNotificationEmailAddress() {
     return this.getUserSettingVO().getCalendarNotificationEmailAddress();
 }

 public String getCalendarNotificationOnOrOff() {
     return this.getUserSettingVO().getCalendarNotificationOnOrOff();
 }

 public String getShowTimeClockStatus() {
     return this.getUserSettingVO().getShowTimeClockStatus();
 }

 public boolean isDefaultDashboardViewDaily() {
     return this.getUserSettingVO().isDefaultDashboardViewDaily();
 }

 public boolean isDefaultDashboardViewWeekly() {
     return this.getUserSettingVO().isDefaultDashboardViewWeekly();
 }

 public boolean isDefaultTabViewDaily() {
     return this.getUserSettingVO().isDefaultTabViewDaily();
 }

 public boolean isDefaultTabViewMonthly() {
     return this.getUserSettingVO().isDefaultTabViewMonthly();
 }

 public boolean isDefaultTabViewWeekly() {
     return this.getUserSettingVO().isDefaultTabViewWeekly();
 }

 public boolean isDefaultToShowingTimeClockStatus() {
     return this.getUserSettingVO().isDefaultToShowingTimeClockStatus();
 }
}
