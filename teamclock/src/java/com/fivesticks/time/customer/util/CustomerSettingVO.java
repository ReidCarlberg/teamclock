
package com.fivesticks.time.customer.util;


/**
 * 2005-10-6
 * This is a VO to contain all the settings.
 * 
 * @author Nick
 */
public class CustomerSettingVO {

    private String accountBalanceNotifyEmailAddress;

    private String accountBalanceNotifyFrequency;

    public String getAccountBalanceNotifyEmailAddress() {
        return accountBalanceNotifyEmailAddress;
    }

    public void setAccountBalanceNotifyEmailAddress(
            String accountBalanceNotifyEmailAddress) {
        this.accountBalanceNotifyEmailAddress = accountBalanceNotifyEmailAddress;
    }

    public String getAccountBalanceNotifyFrequency() {
        return accountBalanceNotifyFrequency;
    }

    public void setAccountBalanceNotifyFrequency(
            String freq) {
        this.accountBalanceNotifyFrequency = freq;
    }

}
