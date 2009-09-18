package com.fivesticks.time.customer.util;

import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

/**
 * 2005-10-6 This is a VO to contain all the settings.
 * 
 * @author Nick
 */
public class CustomerSettingServiceDelegateTest extends AbstractTimeTestCase {

    public void testCustomerSettings() throws Exception,
            ObjectMetricNotFoundException {
        CustomerSettingServiceDelegate cssd = CustomerSettingServiceDelegateFactory.factory
                .build(this.systemOwner);
        CustomerSettingVO vo = cssd.find(this.customer);
        assertNull(vo.getAccountBalanceNotifyEmailAddress());
        assertNull(vo.getAccountBalanceNotifyFrequency());

        vo.setAccountBalanceNotifyEmailAddress("test@test.com");
        vo.setAccountBalanceNotifyFrequency("Weekly");

        cssd.save(this.customer, vo);

        CustomerSettingVO voReload = cssd.find(this.customer);

        assertEquals("test@test.com", voReload
                .getAccountBalanceNotifyEmailAddress());
        assertEquals("Weekly", voReload.getAccountBalanceNotifyFrequency());

    }

}
