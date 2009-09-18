package com.fivesticks.time.timeclock;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 * 
 */
public class TiimeclockBDFactory {

    public static final String SPRING_BEAN_NAME = "fstxTimeclockBD";
    public static final TiimeclockBDFactory factory = new TiimeclockBDFactory();

    public TimeclockBD build(SessionContext sessionContext) {
        return this.build(sessionContext.getSystemOwner(), sessionContext
                .getSettings().getSystemTimeZone(), sessionContext
                .getSettings().getTimeClockRounderType());
    }

    public TimeclockBD build(SystemOwner systemOwner, String timezone,
            String timeclockRounder) {
        TimeclockBDImpl ret = (TimeclockBDImpl) SpringBeanBroker
                .getBeanFactory().getBean(TiimeclockBDFactory.SPRING_BEAN_NAME);
        ret.setSystemOwner(systemOwner);
        ret.setTimezone(timezone);
        ret.setRounder(timeclockRounder);
        return ret;
    }
    
    public TimeclockBD build(SystemOwner systemOwner) {
        TimeclockBDImpl ret = (TimeclockBDImpl) SpringBeanBroker
                .getBeanFactory().getBean(TiimeclockBDFactory.SPRING_BEAN_NAME);
        ret.setSystemOwner(systemOwner);
        return ret;
    }
}