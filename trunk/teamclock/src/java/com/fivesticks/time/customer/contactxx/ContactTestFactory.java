/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.customer.contactxx;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class ContactTestFactory {

    public static final ContactTestFactory singleton = new ContactTestFactory();

    private int count;

    public Contact getPersisted(SystemOwner owner, Customer customer) throws Exception {

        Contact ret = new Contact();

        ret.setOwnerKey(owner.getKey());
        ret.setCustId(customer.getId());
        ret.setNameFirst("first" + (count++));
        ret.setNameLast("last" + count);
        ret.setPrimaryEmail("email" + count + "@domain.com");

//        ContactDAO dao = (ContactDAO) SpringBeanBroker.getBeanFactory()
//                .getBean(ContactDAO.SPRING_BEAN_NAME);
        
        ContactServiceDelegateFactory.factory.build(owner).save(ret);
        
        return ret;
    }

}