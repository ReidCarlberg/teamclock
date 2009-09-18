/*
 * Created on Jun 6, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms.util;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.contact.Contact;
import com.fivesticks.time.ebay.setup.forms.EbayForm;

/**
 * @author Reid
 *  
 */
public class CustomerFormDataMerger implements FormDataMerger {

//    private SystemOwner systemOwner;

    private EbayForm ebayForm;

    private Contact contact;

    private FstxCustomer fstxCustomer;

    public CustomerFormDataMerger(EbayForm ebayForm,
            FstxCustomer fstxCustomer, Contact contact) {
//        this.systemOwner = systemOwner;
        this.ebayForm = ebayForm;
        this.fstxCustomer = fstxCustomer;
        this.contact = contact;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.forms.util.FormDataMerger#getMergedData()
     */
    public String getMergedData() throws FormDataMergerException {

        String ret = this.ebayForm.getForm();

        ret = ret.replaceAll("<customer:nameAndAddress />", this
                .getNameAndAddress());

        ret = ret.replaceAll("<customer:contactNameAndAddress />", this
                .getContactNameAndAddress());

        ret = ret.replaceAll("<customer:contactFirstName />", this.contact
                .getNameFirst());

        ret = ret.replaceAll("<customer:contactLastName />", this.contact
                .getNameLast());

        ret = ret.replaceAll("<customer:name />", this.fstxCustomer.getName());

        ret = ret.replaceAll("<customer:id />", this.fstxCustomer.getId().toString());

        return ret;
    }

    /**
     * @return
     */
    private String getNameAndAddress() {

        String ret = "";
        
        ret = ret + this.fstxCustomer.getName() + "<br>";
        ret = ret + this.fstxCustomer.getStreet1() + "<br>";
        ret = ret + this.fstxCustomer.getStreet2() + "<br>";
        ret = ret + this.fstxCustomer.getCity() + ", ";
        ret = ret + this.fstxCustomer.getState() + " ";
        ret = ret + this.fstxCustomer.getZip() + "<br>";
        
        return ret;

    }

    private String getContactNameAndAddress() {

        String ret = "";

        ret = ret + this.contact.getNameFirst() + " " + this.contact.getNameLast();
        ret = ret + this.fstxCustomer.getName() + "<br>";
        ret = ret + this.fstxCustomer.getStreet1() + "<br>";
        ret = ret + this.fstxCustomer.getStreet2() + "<br>";
        ret = ret + this.fstxCustomer.getCity() + ", ";
        ret = ret + this.fstxCustomer.getState() + " ";
        ret = ret + this.fstxCustomer.getZip() + "<br>";
        
        return ret;

    }
}
