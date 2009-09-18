/*
 * Created on Jun 14, 2004
 *
 */
package com.fivesticks.time.activity;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class ActivityDAOFactory {

    public static final String SPRING_BEAN_NAME = "fstxActivityDAO";
    public static final ActivityDAOFactory factory = new ActivityDAOFactory();

    public ActivityDAO build() {
        return (ActivityDAO) SpringBeanBroker.getBeanFactory().getBean(
                ActivityDAOFactory.SPRING_BEAN_NAME);
    }
}