/*
 * Created on Aug 30, 2006
 *
 */
package com.fivesticks.time.contactv2;

import java.util.Collection;

import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class ContactV2ServiceDelegateImplTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {

        ContactV2 test = ContactV2TestFactory.singleton.getPersisted(
                this.systemOwner, this.customer);

        assertNotNull(test);

    }

    public void testCustomerContactAssociationIsAssociated() throws Exception {

        ContactV2 test = ContactV2TestFactory.singleton.getPersisted(
                this.systemOwner, this.customer);

        CustomerContactV2Map m = new CustomerContactV2Map();
        m.setContactV2Id(test.getId());
        m.setCustomerId(this.customer.getId());
        m.setOwnerKey(this.systemOwner.getKey());
        GenericDAOFactory.factory.build().save(m);

        boolean ass = ContactV2ServiceDelegateFactory.factory.build(
                this.sessionContext).isAssociated(test, this.customer);

        assertTrue(ass);

    }

    public void testCustomerContactAssociate() throws Exception {

        ContactV2 test = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);

        assertFalse(ContactV2ServiceDelegateFactory.factory.build(
                this.sessionContext).isAssociated(test, this.customer));

        ContactV2ServiceDelegateFactory.factory.build(this.sessionContext)
                .associate(test, this.customer);

        assertTrue(ContactV2ServiceDelegateFactory.factory.build(
                this.sessionContext).isAssociated(test, this.customer));

        ContactV2 test2 = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);

        assertFalse(ContactV2ServiceDelegateFactory.factory.build(
                this.sessionContext).isAssociated(test2, this.customer));
    }

    public void testContactHasAssociation() throws Exception {

        ContactV2 test = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);

        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
                .build(this.sessionContext);

        assertFalse(sd.hasAssociation(test));

        sd.associate(test, this.customer);

        assertTrue(sd.hasAssociation(test));

    }

    public void testCustomerContactDissociate() throws Exception {

        ContactV2 test = ContactV2TestFactory.singleton.getPersisted(
                this.systemOwner, this.customer);
        ContactV2 test2 = ContactV2TestFactory.singleton.getPersisted(
                this.systemOwner, this.customer);

        ContactV2ServiceDelegateFactory.factory.build(this.sessionContext)
                .associate(test, this.customer);
        ContactV2ServiceDelegateFactory.factory.build(this.sessionContext)
                .associate(test2, this.customer);

        assertTrue(ContactV2ServiceDelegateFactory.factory.build(
                this.sessionContext).isAssociated(test, this.customer));
        assertTrue(ContactV2ServiceDelegateFactory.factory.build(
                this.sessionContext).isAssociated(test2, this.customer));

        ContactV2ServiceDelegateFactory.factory.build(this.sessionContext)
                .dissociate(test, this.customer);

        assertTrue(!ContactV2ServiceDelegateFactory.factory.build(
                this.sessionContext).isAssociated(test, this.customer));
        assertTrue(ContactV2ServiceDelegateFactory.factory.build(
                this.sessionContext).isAssociated(test2, this.customer));

    }

    public void testGetContactsByCustomer() throws Exception {

        ContactV2 test = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);
        ContactV2 test2 = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);

        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
                .build(this.sessionContext);

        sd.associate(test, this.customer);

        Collection c = sd.getByCustomer(this.customer);

        assertNotNull(c);

        assertEquals(1, c.size());

        assertTrue(c.toArray()[0] instanceof ContactV2);

        /*
         * now let's associate the other one.
         */
        sd.associate(test2, this.customer);

        Collection c2 = sd.getByCustomer(this.customer);

        assertNotNull(c2);

        assertEquals(2, c2.size());

        assertTrue(c2.toArray()[0] instanceof ContactV2);
        assertTrue(c2.toArray()[1] instanceof ContactV2);

        /*
         * had an error in coding so now we'll add a new customer and contact
         */
        Customer customer2 = CustomerTestFactory.getPersisted(this.systemOwner);
        ContactV2 test3 = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);

        sd.associate(test3, customer2);

        Collection c3 = sd.getByCustomer(this.customer);

        assertNotNull(c3);

        assertEquals(2, c3.size());

        Collection c4 = sd.getByCustomer(customer2);

        assertNotNull(c4);

        assertEquals(1, c4.size());
    }

    public void testDeleteAlsoDeletesCustomerContactMap() throws Exception {

        ContactV2 test = ContactV2TestFactory.singleton.getPersisted(
                this.systemOwner, this.customer);
        ContactV2 test2 = ContactV2TestFactory.singleton.getPersisted(
                this.systemOwner, this.customer);

        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
                .build(this.sessionContext);

        sd.associate(test, this.customer);

        long contactId = test.getId().longValue();

        /*
         * should delete
         */
        sd.delete(test);

        String hql = " from " + CustomerContactV2Map.class.getName()
                + " where " + " contactV2Id = " + contactId
                + " and ownerKey = :key ";

        Collection c = GenericDAOFactory.factory.build().find(hql,
                this.systemOwner);

        assertEquals(0, c.size());

        /*
         * should also delete
         */
        String hql2 = " from " + ContactV2LookupMap.class.getName() + " where "
                + " contactV2Id = " + contactId + " and ownerKey = :key ";

        Collection c2 = GenericDAOFactory.factory.build().find(hql2,
                this.systemOwner);

        assertEquals(0, c2.size());
    }

//    public void testAssociateLookup() throws Exception {
//
//        Lookup sample = LookupTestFactory.build(this.systemOwner,
//                LookupType.CONTACT_CLASS);
//
//        ContactV2 test = ContactV2TestFactory.singleton
//                .getPersisted(this.systemOwner);
//
//        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
//                .build(this.sessionContext);
//
//        assertFalse(sd.isAssociated(test, sample));
//
//        sd.associate(test, sample);
//
//        assertTrue(sd.isAssociated(test, sample));
//
//        sd.dissociate(test, sample);
//
//        assertFalse(sd.isAssociated(test, sample));
//
//    }

//    public void testAssociateLookupMoreComplex() throws Exception {
//
//        Lookup sample = LookupTestFactory.build(this.systemOwner,
//                LookupType.CONTACT_CLASS);
//        Lookup sample2 = LookupTestFactory.build(this.systemOwner,
//                LookupType.CONTACT_CLASS);
//
//        ContactV2 test = ContactV2TestFactory.singleton
//                .getPersisted(this.systemOwner);
//        ContactV2 test2 = ContactV2TestFactory.singleton
//                .getPersisted(this.systemOwner);
//
//        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
//                .build(this.sessionContext);
//
//        assertFalse(sd.isAssociated(test, sample));
//        assertFalse(sd.isAssociated(test2, sample2));
//
//        sd.associate(test, sample);
//
//        assertTrue(sd.isAssociated(test, sample));
//        assertFalse(sd.isAssociated(test2, sample2));
//        assertFalse(sd.isAssociated(test, sample2));
//        assertFalse(sd.isAssociated(test2, sample));
//
//        sd.dissociate(test, sample);
//
//        assertFalse(sd.isAssociated(test, sample));
//        assertFalse(sd.isAssociated(test2, sample2));
//
//    }

//    public void testFindByLookup() throws Exception {
//
//        Lookup sample = LookupTestFactory.build(this.systemOwner,
//                LookupType.CONTACT_CLASS);
//
//        ContactV2 test = ContactV2TestFactory.singleton
//                .getPersisted(this.systemOwner);
//
//        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
//                .build(this.sessionContext);
//
//        Collection pre = sd.getByLookup(sample);
//
//        assertNotNull(pre);
//
//        assertEquals(0, pre.size());
//
//        sd.associate(test, sample);
//
//        Collection post = sd.getByLookup(sample);
//
//        assertNotNull(post);
//
//        assertEquals(1, post.size());
//
//        Lookup sample2 = LookupTestFactory.build(this.systemOwner,
//                LookupType.CONTACT_CLASS);
//
//        ContactV2 test2 = ContactV2TestFactory.singleton
//                .getPersisted(this.systemOwner);
//
//        sd.associate(test2, sample2);
//
//        Collection postA = sd.getByLookup(sample);
//
//        assertNotNull(postA);
//
//        assertEquals(1, postA.size());
//
//        sd.associate(test2, sample);
//
//        Collection postB = sd.getByLookup(sample);
//
//        assertNotNull(postB);
//
//        assertEquals(2, postB.size());
//    }

//    public void testFindLookupsByContact() throws Exception {
//
//        Lookup sample = LookupTestFactory.build(this.systemOwner,
//                LookupType.CONTACT_CLASS);
//        Lookup sample2 = LookupTestFactory.build(this.systemOwner,
//                LookupType.CONTACT_CLASS);
//        Lookup sample3 = LookupTestFactory.build(this.systemOwner,
//                LookupType.CONTACT_CLASS);
//
//        ContactV2 test = ContactV2TestFactory.singleton
//                .getPersisted(this.systemOwner);
//        ContactV2 test2 = ContactV2TestFactory.singleton
//                .getPersisted(this.systemOwner);
//        ContactV2 test3 = ContactV2TestFactory.singleton
//                .getPersisted(this.systemOwner);
//
//        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
//                .build(this.sessionContext);
//
//        sd.associate(test, sample);
//        sd.associate(test, sample2);
//        sd.associate(test2, sample);
//
//        Collection c = sd.getLookupsByContact(test, LookupType.CONTACT_CLASS);
//
//        assertEquals(2, c.size());
//
//        assertTrue(c.toArray()[0] instanceof Lookup);
//        assertTrue(c.toArray()[1] instanceof Lookup);
//    }

    public void testFindCustomersByContact() throws Exception {

        Customer customer2 = CustomerTestFactory.getPersisted(this.systemOwner);

        Customer customer3 = CustomerTestFactory.getPersisted(this.systemOwner);

        ContactV2 test1 = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);

        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
                .build(this.sessionContext);

        sd.associate(test1, customer);

        Collection r1 = sd.getCustomersByContact(test1);

        assertEquals(1, r1.size());

        assertTrue(r1.toArray()[0] instanceof Customer);

        sd.associate(test1, customer2);

        Collection r2 = sd.getCustomersByContact(test1);

        assertEquals(2, r2.size());

    }

    public void testKeyValueAdd() throws Exception {

        ContactV2 test1 = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);

        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
                .build(this.sessionContext);

        ContactV2KeyValue keyValue = new ContactV2KeyValue();
        keyValue.setKey("key1");
        keyValue.setValue("value1");
        
        sd.add(test1, keyValue);
    }
    
    public void testKeyValueList() throws Exception {

        ContactV2 test1 = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);

        ContactV2 test2 = ContactV2TestFactory.singleton
        .getPersisted(this.systemOwner);
        
        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
                .build(this.sessionContext);

        ContactV2KeyValue keyValue = new ContactV2KeyValue();
        keyValue.setKey("key1");
        keyValue.setValue("value1");
        
        sd.add(test1, keyValue);
        
        Collection kv = sd.getKeyValues(test1);
        
        assertNotNull(kv);
        
        assertEquals(1, kv.size());
        
        
        Collection kv2 = sd.getKeyValues(test2);
        
        assertNotNull(kv2);
        
        assertEquals(0, kv2.size());
    }
    
}
