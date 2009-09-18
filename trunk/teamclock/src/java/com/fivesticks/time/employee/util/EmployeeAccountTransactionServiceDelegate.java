/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.employee.util;

import com.fivesticks.time.account.AbstractAccountTransactionServiceDelegateIF;

/**
 * @author Reid
 */
public interface EmployeeAccountTransactionServiceDelegate extends AbstractAccountTransactionServiceDelegateIF {

    public static final String SPRING_BEAN_NAME = "employeeAccountTransactionServiceDelegate";

    public static final EmployeeAccountTransactionServiceDelegateFactory factory = new EmployeeAccountTransactionServiceDelegateFactory();

}