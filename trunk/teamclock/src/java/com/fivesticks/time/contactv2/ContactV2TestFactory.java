/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.contactv2;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ContactV2TestFactory {

    public static final ContactV2TestFactory singleton = new ContactV2TestFactory();

    private int count;

    public ContactV2 getPersisted(SystemOwner owner) throws Exception {

        ContactV2 ret = new ContactV2();

        ret.setOwnerKey(owner.getKey());
        
        ret.setNameFirst("first" + (count++));
        ret.setNameLast("last" + count);
        ret.setOrganizationEmail("email" + count + "@domain.com");


        
        ContactV2ServiceDelegateFactory.factory.build(owner).save(ret);
        

        return ret;
    }
    
    public ContactV2 getPersisted(SystemOwner owner, Customer customer) throws Exception {

        ContactV2 ret = this.getPersisted(owner);


        
        ContactV2ServiceDelegateFactory.factory.build(owner).associate(ret, customer);
        return ret;
    }

}