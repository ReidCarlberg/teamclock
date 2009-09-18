/*
 * Created on Jun 14, 2004
 *
 */
package com.fstx.stdlib.authen.users.old;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class UserGroupDAOFactory {

    public UserGroupDAO build() {
        return (UserGroupDAO) SpringBeanBroker.getBeanFactory().getBean(
                UserGroupDAO.SPRING_BEAN_NAME);
    }
}