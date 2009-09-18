/*
 * Created on Sep 27, 2004 by Reid
 */
package com.fivesticks.time.timeclock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.enums.Enum;

/**
 * @author Reid
 */
public class PayPeriodTypeEnum extends Enum {

    public static final PayPeriodTypeEnum PAYPERIOD_WEEKLY = new PayPeriodTypeEnum(
            "Weekly");

    public static final PayPeriodTypeEnum PAYPERIOD_BIWEEKLY = new PayPeriodTypeEnum(
            "Biweekly");

    public static final PayPeriodTypeEnum PAYPERIOD_SEMIMONTHLY = new PayPeriodTypeEnum(
            "Semi-monthly");

    public static final PayPeriodTypeEnum PAYPERIOD_MONTHLY = new PayPeriodTypeEnum(
            "Monthly");

    private static Collection allTypes;

    /**
     * @param arg0
     */
    protected PayPeriodTypeEnum(String arg0) {
        super(arg0);
    }

    public static Collection getTypes() {
        if (allTypes == null) {
            allTypes = new ArrayList();
            allTypes.add(PAYPERIOD_WEEKLY);
            allTypes.add(PAYPERIOD_BIWEEKLY);
            allTypes.add(PAYPERIOD_SEMIMONTHLY);
            allTypes.add(PAYPERIOD_MONTHLY);
        }

        return allTypes;
    }

    public static PayPeriodTypeEnum getByName(String name) {
        PayPeriodTypeEnum ret = null;

        for (Iterator i = getTypes().iterator(); i.hasNext();) {
            PayPeriodTypeEnum current = (PayPeriodTypeEnum) i.next();
            if (current.getName().equals(name)) {
                ret = current;
                break;
            }
        }

        return ret;
    }

}