/*
 * Created on Aug 24, 2005
 *
 */
package com.fivesticks.time.employee.team;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

public class TeamServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "teamServiceDelegate";
    public static final TeamServiceDelegateFactory factory = new TeamServiceDelegateFactory();

    public TeamServiceDelegate build(SystemOwner systemOwner) {
        TeamServiceDelegateImpl ret = (TeamServiceDelegateImpl) SpringBeanBroker.getBeanFactory().getBean(TeamServiceDelegateFactory.SPRING_BEAN_NAME);
        
        ret.setSystemOwner(systemOwner);
        
        return ret;
    }
    
    public TeamServiceDelegate build(String systemOwnerKey) {
        try {
            return build(SystemOwnerServiceDelegateFactory.factory.build().get(systemOwnerKey));
        } catch (SystemOwnerServiceDelegateException e) {
            throw new RuntimeException("failed miserably");
        }
    }
} 
