/*
 * Created on Jun 14, 2004
 *
 */
package com.fivesticks.time.timeclock;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class TimeclockDAOFactory {

    public static final String SPRING_BEAN_NAME = "fstxTimeclockDAO";
    public static final TimeclockDAOFactory factory = new TimeclockDAOFactory();

    public TimeclockDAO build() {
        return (TimeclockDAO) SpringBeanBroker.getBeanFactory().getBean(
                TimeclockDAOFactory.SPRING_BEAN_NAME);
    }
}