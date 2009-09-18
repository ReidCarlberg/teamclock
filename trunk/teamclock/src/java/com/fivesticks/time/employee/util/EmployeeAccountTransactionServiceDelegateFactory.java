/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.employee.util;

import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.account.AccountTransactionServiceDelegateFactory;
import com.fivesticks.time.account.AccountTransactionServiceDelegateImpl;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class EmployeeAccountTransactionServiceDelegateFactory {

    public AccountTransactionServiceDelegate build(SystemOwner systemOwner, EmployeeAccountTransactionType employeeAccountTransactionType) {
        AccountTransactionServiceDelegateImpl ret = (AccountTransactionServiceDelegateImpl) SpringBeanBroker
                .getBeanFactory().getBean(
                        AccountTransactionServiceDelegateFactory.SPRING_BEAN_NAME);
        
        ret.setSystemOwner(systemOwner);
        
        ret.setObjectTransactionType(employeeAccountTransactionType);
        
        return ret;

    }
    
    public AccountTransactionServiceDelegate buildPersonal(SystemOwner systemOwner) {
        
        return this.build(systemOwner, EmployeeAccountTransactionType.PERSONAL);

    }

    public AccountTransactionServiceDelegate buildSick(SystemOwner systemOwner) {
        
        return this.build(systemOwner, EmployeeAccountTransactionType.SICK);

    }

    public AccountTransactionServiceDelegate buildVacation(SystemOwner systemOwner) {
        
        return this.build(systemOwner, EmployeeAccountTransactionType.VACATION);

    }

}