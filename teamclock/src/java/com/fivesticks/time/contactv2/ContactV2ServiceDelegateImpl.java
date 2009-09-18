/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.contactv2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fivesticks.time.common.AbstractSessionContextAwareServiceDelegate;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.systemowner.util.OwnerKeyValidatorAndDecoratorException;

/**
 * @author Reid
 */
public class ContactV2ServiceDelegateImpl extends
        AbstractSessionContextAwareServiceDelegate implements
        ContactV2ServiceDelegate {

    private Log log = LogFactory.getLog(ContactV2ServiceDelegateImpl.class);
    
    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.contact.ContactServiceDelegate#getByCustomer(com.fivesticks.time.customer.FstxCustomer)
     */
    public Collection getByCustomer(Customer cust1)
            throws ContactV2ServiceDelegateException {

        try {
            this.getOwnerKeyValidatorAndDecorator().validate(cust1,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactV2ServiceDelegateException(e);
        }

        String hql = "select contact from "
                + CustomerContactV2Map.class.getName() + " map, "
                + ContactV2.class.getName() + " contact "
                + " where map.contactV2Id = contact.id and "
                + " map.customerId = " + cust1.getId() + " and "
                + " contact.ownerKey = :key";

        Collection c = this.getDao().find(hql, this.getSystemOwner());

        return c;

    }

    public Collection getCustomersByContact(ContactV2 contact)
            throws ContactV2ServiceDelegateException {

        try {
            this.getOwnerKeyValidatorAndDecorator().validate(contact,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactV2ServiceDelegateException(e);
        }

        String hql = "select customer from "
                + CustomerContactV2Map.class.getName() + " map, "
                + Customer.class.getName() + " customer "
                + " where map.customerId = customer.id and "
                + " map.contactV2Id = " + contact.getId() + " and "
                + " customer.ownerKey = :key";

        Collection c = this.getDao().find(hql, this.getSystemOwner());

        return c;
    }

//    public Collection getList(ContactV2CriteriaParameters filter)
//            throws ContactV2ServiceDelegateException {
//        try {
//            this.getOwnerKeyValidatorAndDecorator().decorate(filter,
//                    this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new ContactV2ServiceDelegateException(e);
//        }
//
//        /*
//         * 2006-11-06
//         */
//
//        StringBuffer hql = new StringBuffer();
//
//        Collection<String> cri = new ArrayList<String>();
//
//        hql.append("SELECT cv2 FROM " + ContactV2.class.getName() + " as cv2 ");
//
//        if (filter.getContactClassLuId() != null
//                || filter.getContactInterestLuId() != null
//                || filter.getContactSourceLuId() != null) {
//            hql.append(", " + ContactV2LookupMap.class.getName() + " as lm ");
//
//            if (filter.getContactClassLuId() != null) {
//                String cc = "  ( lm.luType = '"
//                        + LookupType.CONTACT_CLASS.getName() + "' and "
//                        + "lm.contactV2Id = cv2.id and "
//                        + "lm.luId = :contactClassLuId) ";
//                cri.add(cc);
//            }
//
//            if (filter.getContactInterestLuId() != null) {
//                String cc = "  ( lm.luType = '"
//                        + LookupType.CONTACT_INTEREST.getName() + "' and "
//                        + "lm.contactV2Id = cv2.id and "
//                        + "lm.luId = :contactInterestLuId) ";
//                cri.add(cc);
//            }
//
//            if (filter.getContactSourceLuId() != null) {
//                String cc = "  ( lm.luType = '"
//                        + LookupType.CONTACT_SOURCE.getName() + "' and "
//                        + "lm.contactV2Id = cv2.id and "
//                        + "lm.luId = :contactSourceLuId) ";
//                cri.add(cc);
//            }
//        }
//
//        cri.add(" cv2.ownerKey = :ownerKey ");
//
//        if (cri.size() > 0) {
//            hql.append(" where ");
//        }
//
//        StringBuffer p = new StringBuffer();
//
//        for (Iterator<String> iter = cri.iterator(); iter.hasNext();) {
//            if (p.length() > 0) {
//                p.append(" and ");
//            }
//            p.append(iter.next());
//
//        }
//
//        hql.append(p);
//
//        log.info("HQL: " + hql.toString());
//        
//        return this.getDao().find(hql.toString(), filter);
//    }
    
    public Collection getListCri(ContactV2CriteriaParameters params) throws ContactV2ServiceDelegateException {
        
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(params,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactV2ServiceDelegateException(e);
        }

        
        Collection ret =  this.getDao().find(params);
        
        return ret;
        
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.contact.ContactServiceDelegate#delete(com.fivesticks.time.customer.contact.Contact)
     */
    public void delete(ContactV2 contact)
            throws ContactV2ServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().validate(contact,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactV2ServiceDelegateException(e);
        }

        /*
         * 2006-09-01 need to delete from customercontactma
         */
        if (this.hasAssociation(contact)) {
            String hql = "DELETE " + CustomerContactV2Map.class.getName()
                    + " where contactV2Id = :id";

            this.getDao().execute(hql, contact);
        }

        String hql = "DELETE " + ContactV2LookupMap.class.getName()
                + " where contactV2Id = :id";

        this.getDao().execute(hql, contact);

        this.getDao().delete(contact);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.contact.ContactServiceDelegate#save(com.fivesticks.time.customer.contact.Contact)
     */
    public void save(ContactV2 contact)
            throws ContactV2ServiceDelegateException {
        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(contact,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactV2ServiceDelegateException(e);
        }

        this.decorateDates(contact);

        if (contact.getNameFormatted() == null) {
            contact.setNameFormatted(contact.getNameFirst() + " "
                    + contact.getNameLast());
        } else if (contact.getNameFirst() == null
                && contact.getNameLast() == null) {
            String[] parts = contact.getNameFormatted().split(" ");
            if (parts.length == 2) {
                contact.setNameFirst(parts[0]);
                contact.setNameLast(parts[1]);
            } else if (parts.length == 1) {
                contact.setNameFirst(parts[0]);
            }
        }

        this.getDao().save(contact);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.contact.ContactServiceDelegate#get(java.lang.Long)
     */
    public ContactV2 get(Long id) throws ContactV2ServiceDelegateException {
        ContactV2 ret = (ContactV2) this.getDao().get(id);

        try {
            this.handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
            throw new ContactV2ServiceDelegateException(e);
        }

        return ret;
    }

    private boolean isAssociatable(ContactV2 contactV2, Customer customer) {
        boolean ret = false;

        if (contactV2 != null && customer != null && contactV2.getId() != null
                && customer.getId() != null && contactV2.getOwnerKey() != null
                && contactV2.getOwnerKey().equals(customer.getOwnerKey())) {
            ret = true;
        }

        return ret;
    }

    private boolean isAssociatable(ContactV2 contactV2, Lookup lookup) {
        boolean ret = false;

        if (contactV2 != null && lookup != null && contactV2.getId() != null
                && lookup.getId() != null && contactV2.getOwnerKey() != null
                && contactV2.getOwnerKey().equals(lookup.getOwnerKey())) {
            ret = true;
        }

        return ret;
    }

    public boolean isAssociated(ContactV2 contactV2, Customer customer)
            throws ContactV2ServiceDelegateException {
        boolean ret = false;

        if (isAssociatable(contactV2, customer)) {

            String hql = "from " + CustomerContactV2Map.class.getName()
                    + " where ownerKey = :key " + " and customerId = "
                    + customer.getId() + " and contactV2Id = "
                    + contactV2.getId();

            Collection c = this.getDao().find(hql, this.getSystemOwner());

            ret = c != null && c.size() > 0;
        }

        return ret;
    }

    public void associate(ContactV2 contactV2, Customer customer)
            throws ContactV2ServiceDelegateException {

        if (!this.isAssociatable(contactV2, customer)) {
            throw new ContactV2ServiceDelegateException(
                    "invalid contact or customer");
        }

        if (!isAssociated(contactV2, customer)) {

            CustomerContactV2Map m = new CustomerContactV2Map();
            m.setOwnerKey(this.getSystemOwner().getKey());
            m.setContactV2Id(contactV2.getId());
            m.setCustomerId(customer.getId());
            this.getDao().save(m);

        }
    }

    public void dissociate(ContactV2 contactV2, Customer customer)
            throws ContactV2ServiceDelegateException {

        if (!this.isAssociatable(contactV2, customer)) {
            throw new ContactV2ServiceDelegateException(
                    "invalid contact or customer");
        }

        if (this.isAssociated(contactV2, customer)) {

            String hql = "delete " + CustomerContactV2Map.class.getName()
                    + " where ownerKey = :key " + " and contactV2Id = "
                    + contactV2.getId() + " and customerId = "
                    + customer.getId();

            this.getDao().execute(hql, this.getSystemOwner());

        }

    }

    /*
     * 2006-09-01 rsc
     * 
     * partly used right now to enforce a single contact/customer association.
     * 
     * when we remove this in the future, we need to clean up the customer
     * delete as well.
     * 
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.contactv2.ContactV2ServiceDelegate#hasAssociation(com.fivesticks.time.customer.contactv2.ContactV2)
     */
    public boolean hasAssociation(ContactV2 contactV2)
            throws ContactV2ServiceDelegateException {
        boolean ret = false;

        if (contactV2 != null && contactV2.getId() != null) {

            String hql = "from " + CustomerContactV2Map.class.getName()
                    + " where ownerKey = :key " + " and contactV2Id = "
                    + contactV2.getId();

            Collection c = this.getDao().find(hql, this.getSystemOwner());

            ret = c != null && c.size() > 0;
        }

        return ret;
    }

//    public boolean isAssociated(ContactV2 contactV2, Lookup lookup)
//            throws ContactV2ServiceDelegateException {
//
//        boolean ret = false;
//
//        if (isAssociatable(contactV2, lookup)) {
//
//            String hql = "from " + ContactV2LookupMap.class.getName()
//                    + " where ownerKey = :key " + " and contactV2Id = "
//                    + contactV2.getId() + " and luType = '" + lookup.getType()
//                    + "' " + " and luId = " + lookup.getId();
//
//            Collection c = this.getDao().find(hql, this.getSystemOwner());
//
//            ret = c != null && c.size() > 0;
//        }
//
//        return ret;
//    }

//    public void associate(ContactV2 contactV2, Lookup lookup)
//            throws ContactV2ServiceDelegateException {
//
//        if (!this.isAssociatable(contactV2, lookup)) {
//            throw new ContactV2ServiceDelegateException(
//                    "invalid contact or customer");
//        }
//
//        if (!isAssociated(contactV2, lookup)) {
//
//            ContactV2LookupMap m = new ContactV2LookupMap();
//            m.setOwnerKey(this.getSystemOwner().getKey());
//            m.setContactV2Id(contactV2.getId());
//            m.setLuType(lookup.getType());
//            m.setLuId(lookup.getId());
//            this.getDao().save(m);
//
//        }
//
//    }

//    public void associateClass(ContactV2 contactV2, Lookup lookup)
//            throws ContactV2ServiceDelegateException {
//
//        if (!this.isAssociatable(contactV2, lookup)) {
//            throw new ContactV2ServiceDelegateException(
//                    "invalid contact or customer");
//        }
//
//        if (!isAssociated(contactV2, lookup)) {
//
//               contactV2.getClasses().add(lookup);
//               this.save(contactV2);
//
//        }
//
//    }

//    public void dissociate(ContactV2 contactV2, Lookup lookup)
//            throws ContactV2ServiceDelegateException {
//
//        if (!this.isAssociatable(contactV2, lookup)) {
//            throw new ContactV2ServiceDelegateException(
//                    "invalid contact or lookup");
//        }
//
//        if (this.isAssociated(contactV2, lookup)) {
//
//            String hql = "delete " + ContactV2LookupMap.class.getName()
//                    + " where ownerKey = :key " + " and contactV2Id = "
//                    + contactV2.getId() + " and luType = '" + lookup.getType()
//                    + "' " + " and luId = " + lookup.getId();
//
//            this.getDao().execute(hql, this.getSystemOwner());
//
//        }
//
//    }

//    public Collection getByLookup(Lookup lookup)
//            throws ContactV2ServiceDelegateException {
//
//        try {
//            this.getOwnerKeyValidatorAndDecorator().validate(lookup,
//                    this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new ContactV2ServiceDelegateException(e);
//        }
//
//        String hql = "select contact from "
//                + ContactV2LookupMap.class.getName() + " map, "
//                + ContactV2.class.getName() + " contact "
//                + " where map.contactV2Id = contact.id and " + " map.luId = "
//                + lookup.getId() + " and " + " map.luType = '"
//                + lookup.getType() + "' and " + " contact.ownerKey = :key";
//
//        Collection c = this.getDao().find(hql, this.getSystemOwner());
//
//        return c;
//
//    }

//    public Collection getLookupsByContact(ContactV2 contact,
//            LookupType lookupType) throws ContactV2ServiceDelegateException {
//
//        try {
//            this.getOwnerKeyValidatorAndDecorator().validate(contact,
//                    this.getSystemOwner());
//        } catch (OwnerKeyValidatorAndDecoratorException e) {
//            throw new ContactV2ServiceDelegateException(e);
//        }
//
//        String hql = "select lookup from " + ContactV2LookupMap.class.getName()
//                + " map, " + Lookup.class.getName() + " lookup "
//                + " where map.luId = lookup.id and " + " map.contactV2Id = "
//                + contact.getId() + " and " + " lookup.type = '"
//                + lookupType.getName() + "' and " + " lookup.ownerKey = :key";
//
//        Collection c = this.getDao().find(hql, this.getSystemOwner());
//
//        return c;
//    }

    public void add(ContactV2 contactV2, ContactV2KeyValue contactV2KeyValue)
            throws ContactV2ServiceDelegateException {

        if (contactV2.getId() == null) {
            throw new ContactV2ServiceDelegateException(
                    "contact v2 must have an id");
        }

        try {
            this.getOwnerKeyValidatorAndDecorator().validate(contactV2,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactV2ServiceDelegateException(e);
        }

        try {
            this.getOwnerKeyValidatorAndDecorator().decorate(contactV2KeyValue,
                    this.getSystemOwner());
        } catch (OwnerKeyValidatorAndDecoratorException e) {
            throw new ContactV2ServiceDelegateException(e);
        }

        contactV2KeyValue.setContactV2Id(contactV2.getId());

        this.getDao().save(contactV2KeyValue);

    }

    public Collection getKeyValues(ContactV2 contactV2)
            throws ContactV2ServiceDelegateException {

        String hql = "from " + ContactV2KeyValue.class.getName() + " where "
                + " contactV2Id = :id and ownerKey = :ownerKey "
                + " order by key";

        Collection c = this.getDao().find(hql, contactV2);

        return c;
    }

}
