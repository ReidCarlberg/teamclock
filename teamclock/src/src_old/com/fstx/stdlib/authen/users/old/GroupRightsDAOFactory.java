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
public class GroupRightsDAOFactory {

    public GroupRightsDAO build() {
        return (GroupRightsDAO) SpringBeanBroker.getBeanFactory().getBean(
                GroupRightsDAO.SPRING_BEAN_NAME);
    }
}