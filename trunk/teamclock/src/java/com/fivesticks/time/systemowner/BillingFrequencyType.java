/*
 * Created on Aug 23, 2006
 *
 */
package com.fivesticks.time.systemowner;

import org.apache.commons.lang.enums.Enum;

public class BillingFrequencyType extends Enum {

    public static final BillingFrequencyType MONTHLY = new BillingFrequencyType("Monthly");
    
    public static final BillingFrequencyType ANNUALLY = new BillingFrequencyType("Annual");
    
    protected BillingFrequencyType(String arg0) {
        super(arg0);
        
    }

    public static BillingFrequencyType getByName(String name) {
        return (BillingFrequencyType) getEnum(BillingFrequencyType.class, name);
    }
    
}
