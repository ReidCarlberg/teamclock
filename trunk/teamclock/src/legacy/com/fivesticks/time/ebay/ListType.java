/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import org.apache.commons.lang.enums.Enum;

/**
 * @author REID
 */
public class ListType extends Enum {

    public static final ListType ALL = new ListType("ALL");
    public static final ListType TOLIST = new ListType("TOLIST");
    public static final ListType OPEN = new ListType("OPEN");
    public static final ListType TOPAY = new ListType("TOPAY");
    public static final ListType CLOSED = new ListType("CLOSED");
    
    /**
     * @param arg0
     */
    protected ListType(String arg0) {
        super(arg0);
        
    }

}
