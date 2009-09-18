package com.fivesticks.time.calendar;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 *  
 */
public class CalendarBDFactory {

    public static final String SPRING_BEAN_NAME = "fstxCalendarBD";
    public static final CalendarBDFactory factory = new CalendarBDFactory();

    public CalendarBD build() {
        return (CalendarBD) SpringBeanBroker.getBeanFactory().getBean(
                CalendarBDFactory.SPRING_BEAN_NAME);
    }

    public CalendarBD build(SessionContext sessionContext) {
        return build(sessionContext.getSystemOwner());
    }

    public CalendarBD build(SystemOwner systemOwner) {
        CalendarBD ret = (CalendarBD) SpringBeanBroker.getBeanFactory()
                .getBean(CalendarBDFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);
        return ret;

    }

}