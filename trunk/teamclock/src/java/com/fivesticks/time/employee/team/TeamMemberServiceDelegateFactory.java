/*
 * Created on Aug 24, 2005
 *
 */
package com.fivesticks.time.employee.team;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

public class TeamMemberServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "teamMemberServiceDelegate";
    public static final TeamMemberServiceDelegateFactory factory = new TeamMemberServiceDelegateFactory();

    public TeamMemberServiceDelegate build(SystemOwner systemOwner) {
        TeamMemberServiceDelegateImpl ret = (TeamMemberServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        TeamMemberServiceDelegateFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);

        return ret;

    }
}
