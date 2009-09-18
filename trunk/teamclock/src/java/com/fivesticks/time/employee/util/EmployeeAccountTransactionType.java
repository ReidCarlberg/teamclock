/*
 * Created on Jun 29, 2005
 *
 */
package com.fivesticks.time.employee.util;

import com.fivesticks.time.account.ObjectTransactionType;

/**
 * @author Reid
 * 
 */
public class EmployeeAccountTransactionType extends ObjectTransactionType {

    public static final EmployeeAccountTransactionType PTO = new EmployeeAccountTransactionType(
            "PTO");

    public static final EmployeeAccountTransactionType SICK = new EmployeeAccountTransactionType(
            "SICK");

    public static final EmployeeAccountTransactionType PERSONAL = new EmployeeAccountTransactionType(
            "PERSONAL");

    public static final EmployeeAccountTransactionType VACATION = new EmployeeAccountTransactionType(
            "VACATION");

    /**
     * @param arg0
     */
    protected EmployeeAccountTransactionType(String arg0) {
        super(arg0);

    }

    public static EmployeeAccountTransactionType getType(String name) {

        EmployeeAccountTransactionType ret = (EmployeeAccountTransactionType) EmployeeAccountTransactionType
                .getEnum(EmployeeAccountTransactionType.class, name);

        return ret;

    }

}
