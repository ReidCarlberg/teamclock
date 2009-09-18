/*
 * Created on Jun 6, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.forms.util;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.contact.Contact;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.setup.forms.EbayForm;
import com.fivesticks.time.ebay.setup.forms.FormType;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class FormDataMergerFactory {

    public static FormDataMerger build(SystemOwner systemOwner,
            EbayForm ebayForm, EbayItem ebayItem, String ebayImageSrc, FstxCustomer fstxCustomer) {

        if (!ebayForm.getType().equals(FormType.LISTING.getName()))
            throw new RuntimeException("invalid form type");
        FormDataMerger ret = new ListingFormDataMerger(systemOwner, ebayForm,
                ebayItem, ebayImageSrc, fstxCustomer);

        return ret;

    }

    public static FormDataMerger build(
            EbayForm ebayForm, FstxCustomer fstxCustomer, Contact contact) {
        if (!ebayForm.getType().equals(FormType.CUSTOMER.getName()))
            throw new RuntimeException("invalid form type");
        FormDataMerger ret = new CustomerFormDataMerger(ebayForm,
                fstxCustomer, contact);

        return ret;
    }

    public static FormDataMerger build(SystemOwner systemOwner,
            EbayForm ebayForm, EbayItem ebayItem) {
        if (!ebayForm.getType().equals(FormType.ITEM.getName()))
            throw new RuntimeException("invalid form type");

        FstxCustomer cust;
        try {
            cust = CustomerServiceDelegate.factory.build(systemOwner).getFstxCustomer(
                    ebayItem.getCustId());
        } catch (CustomerServiceDelegateException e) {
            throw new RuntimeException("invalid customer");
        }
        FormDataMerger ret = new ItemFormDataMerger(systemOwner, ebayForm,
                ebayItem, cust);

        return ret;
    }

}
