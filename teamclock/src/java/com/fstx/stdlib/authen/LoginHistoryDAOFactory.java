/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.dao.GenericDAO;

/**
 * @author Reid
 *  
 */
public class LoginHistoryDAOFactory {

    public static final String SPRING_BEAN_NAME = "loginHistoryDAO";
    public static final LoginHistoryDAOFactory factory = new LoginHistoryDAOFactory();

    public GenericDAO build() {
        return (GenericDAO) SpringBeanBroker.getBeanFactory().getBean(
                LoginHistoryDAOFactory.SPRING_BEAN_NAME);
    }
}