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
public class GroupMembershipDAOFactory {

    public GroupMembershipDAO build() {
        return (GroupMembershipDAO) SpringBeanBroker.getBeanFactory().getBean(
                GroupMembershipDAO.SPRING_BEAN_NAME);
    }
}