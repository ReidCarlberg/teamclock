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
public class GroupRightBDFactory {

    public GroupRightBD build() {
        return (GroupRightBD) SpringBeanBroker.getBeanFactory().getBean(
                GroupRightBD.SPRING_BEAN_NAME);
    }
}