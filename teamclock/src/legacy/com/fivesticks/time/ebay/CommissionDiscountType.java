/*
 * Created on Jun 1, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import java.util.Collection;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public class CommissionDiscountType extends Enum {

    public static final CommissionDiscountType FLAT = new CommissionDiscountType("FLAT");
    
    public static final CommissionDiscountType PERCENT = new CommissionDiscountType("PERCENT");
    
    /**
     * @param arg0
     */
    protected CommissionDiscountType(String arg0) {
        super(arg0);
        
    }

    public static CommissionDiscountType getByName(String name) {
        return (CommissionDiscountType) CommissionDiscountType.getEnum(CommissionDiscountType.class, name);
    }
    
    public static Collection getDiscountTypes() {
        
        return CommissionDiscountType.getEnumList(CommissionDiscountType.class);
        
    }

}
