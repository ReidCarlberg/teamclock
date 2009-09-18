/*
 * Created on Oct 6, 2005
 *
 * 
 */
package com.fivesticks.time.useradmin.settings;

import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;
import com.fstx.stdlib.authen.users.User;

public interface UserSettingServiceDelegate {

    public abstract UserSettingVO find(User user)
            throws ObjectMetricServiceDelegateException,
            ObjectMetricNotFoundException;

    public abstract void save(User user, UserSettingVO vo)
            throws ObjectMetricServiceDelegateException;

}