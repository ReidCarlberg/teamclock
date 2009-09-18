/*
 * Created on Oct 20, 2004 by Reid
 */
package com.fivesticks.time.calendar.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.menu.MenuSection;
import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegate;
import com.fivesticks.time.useradmin.settings.UserSettingServiceDelegateFactory;
import com.fivesticks.time.useradmin.settings.UserSettingVO;

/**
 * @author Reid
 */
public class CalendarDefaultGatewayAction extends SessionContextAwareSupport {

    private UserSettingServiceDelegate userSettingServiceDelegate;

    private UserSettingVO userSettingVO;

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);
        this.getSessionContext().setMenuSection(MenuSection.CALENDAR);

        String forwardDirection = this.getUserSettingVO()
                .getCalendarDefaultTabView();
       
        
        return SUCCESS + "-" + forwardDirection;
    }

    // public Collection getUsers() throws Exception {
    // return SystemUserServiceDelegate.factory.build().list(
    // this.getSessionContext().getSystemOwner());
    // }

    public UserSettingServiceDelegate getUserSettingServiceDelegate() {
        if (this.userSettingServiceDelegate == null) {
            this.userSettingServiceDelegate = UserSettingServiceDelegateFactory.factory
                    .build(this.getSystemOwner());
        }

        return this.userSettingServiceDelegate;
    }

    public void setUserSettingServiceDelegate(
            UserSettingServiceDelegate userSettingServiceDelegate) {
        this.userSettingServiceDelegate = userSettingServiceDelegate;
    }

    public UserSettingVO getUserSettingVO()
            throws ObjectMetricServiceDelegateException,
            ObjectMetricNotFoundException {
        if (this.userSettingVO == null) {
            this.userSettingVO = this.getUserSettingServiceDelegate().find(
                    this.getSessionContext().getUser().getUser());

        }

        return userSettingVO;
    }

    public void setUserSettingVO(UserSettingVO userSettingVO) {
        this.userSettingVO = userSettingVO;
    }

}