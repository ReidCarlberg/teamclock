/*
 * Created on Jun 24, 2005
 *
 */
package com.fivesticks.time.employee;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 *
 */
public class EmployeeServiceDelegateFactory {

    public static final String SPRING_BEAN_NAME = "employeeServiceDelegate";
    public static final EmployeeServiceDelegateFactory factory = new EmployeeServiceDelegateFactory();

    public EmployeeServiceDelegate build(SystemOwner systemOwner) {
        EmployeeServiceDelegateImpl ret = (EmployeeServiceDelegateImpl) SpringBeanBroker.getBeanFactory().getBean(EmployeeServiceDelegateFactory.SPRING_BEAN_NAME);
        
        ret.setSystemOwner(systemOwner);
        
        return ret;
    }
}
