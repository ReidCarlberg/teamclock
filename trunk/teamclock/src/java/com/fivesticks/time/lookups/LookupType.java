/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.util.DescriptiveEnum;

/**
 * @author Reid
 */
public class LookupType extends DescriptiveEnum {

    /*
     * type can be a max of 20 characters
     */
    
    public static LookupType CUSTOMER_STATUS = new LookupType(
            "CUSTOMER_STATUS", "Customer Status");

    public static LookupType EMPLOYEE_STATUS = new LookupType(
            "EMPLOYEE_STATUS", "Employee Status");
    
    public static LookupType VENDOR_TYPE = new LookupType("VENDOR_TYPE",
            "Vendor Type");

//    public static LookupType AUC_DISC_REASON = new LookupType(
//            "AUC_DISC_REASON", "Auction Discount Reason");

    public static LookupType CALENDAR_TYPE = new LookupType(
            "CALENDAR_TYPE", "Calendar Entry Type");

    public static LookupType PROJECT_CLASS = new LookupType(
            "PROJECT_CLASS", "Project Class");

    public static LookupType CONTACT_CLASS = new LookupType(
            "CONTACT_CLASS", "Contact Class");

    public static LookupType CONTACT_INTEREST = new LookupType(
            "CONTACT_INTEREST", "Contact Interests");

    public static LookupType CONTACT_SOURCE = new LookupType(
            "CONTACT_SOURCE", "Contact Source");

    private static Collection all;

    /**
     * @param shortName
     */
    public LookupType(String shortName) {
        super(shortName);
        handleAdd(this);
    }

    /**
     * @param shortName
     * @param friendlyName
     */
    public LookupType(String shortName, String friendlyName) {
        super(shortName, friendlyName);
        if (shortName.length() > 20 || friendlyName.length() > 100) {
            throw new RuntimeException("Lookup type is too long.");
        }
        handleAdd(this);
    }

    private void handleAdd(LookupType type) {
        LookupType.getAll().add(type);
    }

    public static Collection getAll() {
        if (all == null)
            all = new ArrayList();

        return all;
    }

    public static LookupType getByShortName(String shortName) {

        LookupType ret = null;

        for (Iterator iter = getAll().iterator(); iter.hasNext();) {
            LookupType element = (LookupType) iter.next();
            if (element.getName().equalsIgnoreCase(shortName)) {
                ret = element;
            }
        }

        return ret;
    }
}