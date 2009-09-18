/*
 * Created on Sep 13, 2004 by Reid
 */
package com.fstx.stdlib.authen.users.old;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 */
public class GroupMembershipServiceDelegateFactory {

    public GroupMembershipServiceDelegate build() {
        return (GroupMembershipServiceDelegate) SpringBeanBroker
                .getBeanFactory().getBean(
                        GroupMembershipServiceDelegate.SPRING_BEAN_NAME);
    }
}