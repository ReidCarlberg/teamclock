/*
 * Created on Dec 31, 2004 by REID
 */
package com.fivesticks.time.authen.events;

import com.fivesticks.time.events.EventHandler;
import com.fivesticks.time.events.EventHandlerException;
import com.fivesticks.time.events.GeneralEvent;
import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegate;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateFactory;
import com.fivesticks.time.object.metrics.ObjectMetricTypeEnum;
import com.fstx.stdlib.authen.LoginHistoryBDFactory;
import com.fstx.stdlib.authen.LoginHistoryException;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 */
public class AuthenticationEventHandler implements EventHandler {

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.events.EventHandler#handleEvent(com.fivesticks.time.events.GeneralEvent)
     */
    public void handleEvent(GeneralEvent event) throws EventHandlerException {
        AuthenticationEvent current = (AuthenticationEvent) event;

        handleEventLoginHistory(current);

        handleEventSystemOwnerStats(current);

        handleEventUserStats(current);

    }

    private void handleEventLoginHistory(AuthenticationEvent current)
            throws EventHandlerException {
        try {
            LoginHistoryBDFactory.factory.build().recordEvent(
                    current.getUser().getUsername(), current.getDate(),
                    current.getLocation(), current.getSystemOwner().getKey(),
                    current.getDescription());
        } catch (LoginHistoryException e) {
            throw new EventHandlerException(current.getUser().getUsername(), e);
        }
    }

    private void handleEventSystemOwnerStats(AuthenticationEvent current)
            throws EventHandlerException {
        SimpleDate last = SimpleDate.factory.build(current.getDate());

        ObjectMetricServiceDelegate sd = ObjectMetricServiceDelegateFactory.factory
                .build(current.getSystemOwner());

        try {
            String value = "0";

            try {
                value = sd.getMetricValue(current.getSystemOwner(),
                        ObjectMetricTypeEnum.SYSTEM_LOGIN_COUNT);
            } catch (ObjectMetricNotFoundException e1) {

            }

            Long lastCount = new Long(value);
            lastCount = new Long(lastCount.longValue() + 1);

            sd.setValue(current.getSystemOwner(),
                    ObjectMetricTypeEnum.SYSTEM_LASTLOGIN_DATE, last
                            .getMmddyyyy());

            sd.setValue(current.getSystemOwner(),
                    ObjectMetricTypeEnum.SYSTEM_LASTLOGIN_USER, current
                            .getUser().getUsername());

            sd.setValue(current.getSystemOwner(),
                    ObjectMetricTypeEnum.SYSTEM_LOGIN_COUNT, lastCount
                            .toString());

        } catch (ObjectMetricServiceDelegateException e) {
            throw new EventHandlerException(e);
        }
    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.fstx.stdlib.authen.LoginHistoryBDAware#setLoginHistoryBD(com.fstx.stdlib.authen.LoginHistoryBD)
//     */
//    public void setLoginHistoryBD(LoginHistoryBD loginHistoryBD) {
//        this.loginHistoryBD = loginHistoryBD;
//    }

    /**
     * @param current
     * @throws EventHandlerException
     */
    private void handleEventUserStats(AuthenticationEvent current)
            throws EventHandlerException {
        SimpleDate last = SimpleDate.factory.build(current.getDate());

        ObjectMetricServiceDelegate sd = ObjectMetricServiceDelegateFactory.factory
                .build(current.getSystemOwner());

        try {
            sd.setValue(current.getUser(),
                    ObjectMetricTypeEnum.USER_LASTLOGIN_DATE, last
                            .getMmddyyyy());

            String value = "0";

            try {
                value = sd.getMetricValue(current.getUser(),
                        ObjectMetricTypeEnum.USER_LOGIN_COUNT);
            } catch (ObjectMetricNotFoundException e1) {

            }

            Long lastCount = new Long(value);
            lastCount = new Long(lastCount.longValue() + 1);

            sd
                    .setValue(current.getUser(),
                            ObjectMetricTypeEnum.USER_LOGIN_COUNT, lastCount
                                    .toString());

        } catch (ObjectMetricServiceDelegateException e) {
            throw new EventHandlerException(e);
        }
    }

}