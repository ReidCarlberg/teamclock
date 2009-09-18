/*
 * Created on Jun 20, 2005
 *
 */
package com.fivesticks.time.account;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 *
 */
public abstract class ObjectTransactionType extends Enum {

    /**
     * @param arg0
     */
    protected ObjectTransactionType(String arg0) {
        super(arg0);
        if (arg0.length() > 25) {
            throw new RuntimeException("object transaction type is too big. " + arg0.length());
        }
    }

    public static ObjectTransactionType getByName(String name) {
        
        return (ObjectTransactionType) Enum.getEnum(ObjectTransactionType.class,name);
    }
    
}
