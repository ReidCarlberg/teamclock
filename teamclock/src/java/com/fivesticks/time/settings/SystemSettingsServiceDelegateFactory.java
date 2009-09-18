/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.settings;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * <p>
 * 8/31/2004 Set the System Owner so that this is now system owner specific.
 * 
 * @author REID
 * 
 */
public class SystemSettingsServiceDelegateFactory extends
        AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME = "systemSettingsServiceDelegate";
    public static final SystemSettingsServiceDelegateFactory factory = new SystemSettingsServiceDelegateFactory();

    public SystemSettingsServiceDelegate build(SystemOwner systemOwner) {
        SystemSettingsServiceDelegateImpl ret = (SystemSettingsServiceDelegateImpl) this
                .getBean(SystemSettingsServiceDelegateFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);

        return ret;

    }

    public SystemSettingsServiceDelegate buildEmpty() {
        SystemSettingsServiceDelegateImpl ret = (SystemSettingsServiceDelegateImpl) this
                .getBean(SystemSettingsServiceDelegateFactory.SPRING_BEAN_NAME);

        return ret;

    }
}