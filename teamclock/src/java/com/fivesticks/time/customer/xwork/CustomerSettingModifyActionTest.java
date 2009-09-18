/*
 * Created on 2005-10-7 Nick
 */
package com.fivesticks.time.customer.xwork;

import com.fivesticks.time.customer.util.CustomerSettingServiceDelegate;
import com.fivesticks.time.customer.util.CustomerSettingServiceDelegateFactory;
import com.fivesticks.time.customer.util.CustomerSettingVO;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class CustomerSettingModifyActionTest extends AbstractTimeTestCase {

    public void testCustomerSettings() throws Exception {

        CustomerSettingModifyAction action = new CustomerSettingModifyAction();
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.setSessionContext(this.sessionContext);
        action.getCustomerModifyContext().setTargetCustomer(this.customer);

        assertEquals(ActionSupport.INPUT, action.execute());

        CustomerSettingVO vo = action.getCustomerModifyContext()
                .getCustomerSettingVO();
        assertNotNull(vo);

        assertNull(vo.getAccountBalanceNotifyEmailAddress());
        assertNull(vo.getAccountBalanceNotifyFrequency());

        vo.setAccountBalanceNotifyEmailAddress("test@test.com");
        vo.setAccountBalanceNotifyFrequency("Weekly");

        action.setCustomerSettingSubmit("Submit");
        assertEquals(ActionSupport.SUCCESS, action.execute());

        CustomerSettingServiceDelegate cssd = CustomerSettingServiceDelegateFactory.factory
                .build(this.systemOwner);
        // CustomerSettingVO vo = cssd.find(this.customer);
        // assertNull(vo.getAccountBalanceNotifyEmailAddress());
        // assertNull(vo.getAccountBalanceNotifyFrequency());

        CustomerSettingVO voReload = cssd.find(this.customer);

        assertEquals("test@test.com", voReload
                .getAccountBalanceNotifyEmailAddress());
        assertEquals("Weekly", voReload.getAccountBalanceNotifyFrequency());
    }

}
