/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.settings;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author REID
 *  
 */
public class SystemSettingsDaoFactory {

    public static final String SPRING_BEAN_NAME = "systemSettingsDao";
    public static final SystemSettingsDaoFactory factory = new SystemSettingsDaoFactory();

    public SystemSettingsDao build() {
        return (SystemSettingsDao) SpringBeanBroker.getBeanFactory().getBean(
                SystemSettingsDaoFactory.SPRING_BEAN_NAME);
    }
}