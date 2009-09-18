/*
 * Created on Aug 23, 2006
 *
 */
package com.fivesticks.time.systemowner;

import org.apache.commons.lang.enums.Enum;

public class AccountType extends Enum {

    
    public static final AccountType DEMO = new AccountType("Demo");
    
    public static final AccountType TIMECLOCK = new AccountType("Time Clock Only");
    
    public static final AccountType STANDARD = new AccountType("Standard");
    
    
    
    
    protected AccountType(String arg0) {
        super(arg0);
        
    }

    public static AccountType getByName(String name) {
        return (AccountType) getEnum(AccountType.class, name);
    }
}
