/*
 * Created on Jun 6, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.forms.util;

import junit.framework.TestCase;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerTestFactory;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.EbayItemImage;
import com.fivesticks.time.ebay.EbayItemImageTestFactory;
import com.fivesticks.time.ebay.EbayItemTestFactory;
import com.fivesticks.time.ebay.setup.forms.EbayForm;
import com.fivesticks.time.ebay.setup.forms.EbayFormTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class ListingFormDataMergerTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        FstxCustomer cust = FstxCustomerTestFactory.getPersisted(owner);
        EbayItem item = EbayItemTestFactory.singleton.getPersisted(owner, cust);
        
        EbayItemImage image = EbayItemImageTestFactory.factory.getPersisted(owner, item);
        
        EbayForm form = EbayFormTestFactory.build(FormType.LISTING, owner);
        
        
        
        ListingFormDataMerger m = new ListingFormDataMerger(owner, form, item, "linebreak", cust);

        assertTrue(form.getForm().indexOf(item.getListingHeadline()) == -1);
        
        assertTrue(form.getForm().indexOf(item.getListingDetail()) == -1);
        
        String r = m.getMergedData();
        
        assertTrue(r != null && r.length() > 0);
        
        assertTrue(r.indexOf(item.getListingHeadline()) > -1);
        
        assertTrue(r.indexOf(item.getListingDetail()) > -1);
        
        assertTrue(r.indexOf(image.getImageFull()) > -1);
        
        assertTrue(r.indexOf(image.getImageSmall()) > -1);
        
        System.out.println(r);
        
        assertTrue(r.indexOf("null") == -1);
        
        
    }
    


}
