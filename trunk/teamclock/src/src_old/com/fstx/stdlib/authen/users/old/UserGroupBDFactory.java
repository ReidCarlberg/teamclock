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
public class UserGroupBDFactory {

    public UserGroupBD build() {
        return (UserGroupBD) SpringBeanBroker.getBeanFactory().getBean(
                UserGroupBD.SPRING_BEAN_NAME);
    }
}