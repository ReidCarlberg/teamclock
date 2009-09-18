/*
 * Created on Mar 20, 2005 by REID
 */
package com.fivesticks.time.customer.util;

import com.fivesticks.time.object.metrics.ObjectMetricTypeEnum;

/**
 * @author REID
 */
public class CustomerSettingType extends ObjectMetricTypeEnum {

    public static final CustomerSettingType TRACKS_ACCOUNT_TXNS = new CustomerSettingType(
            "TRACKS_ACCOUNT_TXNS");

    public static final CustomerSettingType IS_AUCTION_CUSTOMER = new CustomerSettingType(
            "IS_AUCTION_CUSTOMER");

    public static final CustomerSettingType AUCTION_COMMISSION_OVERRIDE = new CustomerSettingType(
            "AUCTION_COMMISSION_OVERRIDE");

    /*
     * Nick 2005-10-6 Added general custmer Settings.
     */
    public static final CustomerSettingType ACCOUNT_BALANCE_NOTIFY_EMAIL_ADDRESS = new CustomerSettingType(
            "ACCOUNT_BALANCE_NOTIFY_EMAIL_ADDRESS");

    public static final CustomerSettingType ACCOUNT_BALANCE_NOTIFY_FREQUENCY = new CustomerSettingType(
            "ACCOUNT_BALANCE_NOTIFY_FREQUENCY");

    /**
     * @param arg0
     */
    public CustomerSettingType(String arg0) {
        super(arg0);
    }

}
