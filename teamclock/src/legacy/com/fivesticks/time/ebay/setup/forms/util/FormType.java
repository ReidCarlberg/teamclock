/*
 * Created on May 4, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.forms.util;

import java.util.ArrayList;
import java.util.Collection;

import com.fivesticks.time.common.util.DescriptiveEnum;

/**
 * @author Reid
 */
public class FormType extends DescriptiveEnum {

    private static Collection all;
    
    public static final FormType CUSTOMER = new FormType("CUSTOMER","customer");
    public static final FormType ITEM = new FormType("ITEM", "item");
    public static final FormType LISTING = new FormType("LISTING", "listing");
    
    /**
     * @param shortName
     */
    public FormType(String shortName) {
        super(shortName);
        addToAll(this);
    }

    /**
     * @param shortName
     * @param friendlyName
     */
    public FormType(String shortName, String friendlyName) {
        super(shortName, friendlyName);
        addToAll(this);
    }
    
    public static Collection getAll() {
        return all;
    }
    
    private void addToAll(DescriptiveEnum enumCurrent) {
        if (all == null)
            all = new ArrayList();
        
        all.add(enumCurrent);
    }

}
