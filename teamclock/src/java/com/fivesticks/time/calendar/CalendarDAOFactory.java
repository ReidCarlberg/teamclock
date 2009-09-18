/*
 * Created on Jun 14, 2004
 *
 */
package com.fivesticks.time.calendar;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class CalendarDAOFactory {

    public static final String SPRING_BEAN_NAME = "fstxCalendarDAO";
    public static final CalendarDAOFactory factory = new CalendarDAOFactory();

    public CalendarDAO build() {
        return (CalendarDAO) SpringBeanBroker.getBeanFactory().getBean(
                CalendarDAOFactory.SPRING_BEAN_NAME);
    }
}