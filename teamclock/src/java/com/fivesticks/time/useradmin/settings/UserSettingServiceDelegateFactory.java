/*
 * Created on Oct 6, 2005
 *
 * 
 */
package com.fivesticks.time.useradmin.settings;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

public class UserSettingServiceDelegateFactory  {

    public static final String SPRING_BEAN_NAME = "userSettingServiceDelegate";
    public static final UserSettingServiceDelegateFactory factory = new UserSettingServiceDelegateFactory();

    public UserSettingServiceDelegateFactory() {
        super();

    }

    public UserSettingServiceDelegate build(SystemOwner owner) {
        
        UserSettingServiceDelegateImpl ret = (UserSettingServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                         UserSettingServiceDelegateFactory.SPRING_BEAN_NAME);
        ret.setSystemOwner(owner);
        return ret;
    }

}
