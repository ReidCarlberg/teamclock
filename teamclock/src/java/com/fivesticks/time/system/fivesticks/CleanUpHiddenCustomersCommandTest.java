/*
 * Created on Sep 2, 2006
 *
 */
package com.fivesticks.time.system.fivesticks;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupTestFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.useradmin.UserTypeEnum;
import com.fstx.stdlib.authen.users.UserBDFactory;

public class CleanUpHiddenCustomersCommandTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {

        UserBDFactory.factory.build().addUser("reid", "12345",
                "reidtest1@teamclock.com");
        SystemUserServiceDelegateFactory.factory.build().associate(
                this.systemOwner,
                UserBDFactory.factory.build().getByUsername("reid"),
                UserTypeEnum.OWNERADMIN);
        Lookup l = LookupTestFactory.build(this.systemOwner,
                LookupType.CUSTOMER_STATUS);
        l.setShortName("ebay");
        LookupServiceDelegateFactory.factory.build(this.systemOwner).save(l);

        this.customer.setHidden(Boolean.TRUE);
        this.customer.setStatus(l.getId());

        CustomerServiceDelegateFactory.factory.build(this.sessionContext).save(
                this.customer);

        long custId = this.customer.getId().longValue();

        new CleanUpHiddenCustomersCommand().execute(this.systemOwner);

        try {
            Customer after = CustomerServiceDelegateFactory.factory.build(
                    this.sessionContext).getFstxCustomer(new Long(custId));
            assertTrue(false);
        } catch (Exception e) {
        }

    }
    
    public void testBasicPlus1() throws Exception {

        UserBDFactory.factory.build().addUser("reid", "12345",
                "reidtest1@teamclock.com");
        SystemUserServiceDelegateFactory.factory.build().associate(
                this.systemOwner,
                UserBDFactory.factory.build().getByUsername("reid"),
                UserTypeEnum.OWNERADMIN);
        Lookup l = LookupTestFactory.build(this.systemOwner,
                LookupType.CUSTOMER_STATUS);
        l.setShortName("ebay");
        LookupServiceDelegateFactory.factory.build(this.systemOwner).save(l);

        this.customer.setHidden(Boolean.TRUE);
        this.customer.setStatus(l.getId());

        CustomerServiceDelegateFactory.factory.build(this.sessionContext).save(
                this.customer);

        Customer customer2 = CustomerTestFactory.getPersisted(this.systemOwner);
        customer2.setHidden(Boolean.FALSE);
        CustomerServiceDelegateFactory.factory.build(this.sessionContext).save(
                customer2);
        
        long custId = this.customer.getId().longValue();

        new CleanUpHiddenCustomersCommand().execute(this.systemOwner);

        try {
            Customer after = CustomerServiceDelegateFactory.factory.build(
                    this.sessionContext).getFstxCustomer(new Long(custId));
            assertTrue(false);
        } catch (Exception e) {
        }

        Customer after2 = CustomerServiceDelegateFactory.factory.build(
                this.sessionContext).getFstxCustomer(customer2.getId());
        
        assertNotNull(after2);
    }
}
