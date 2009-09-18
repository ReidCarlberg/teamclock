/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.ebay.setup.forms.util.FormType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 *
 */
public class EbayFormServiceDelegateImplTest extends TestCase {

    SystemOwner owner;
    
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
    }
    
    public void testBasic() throws Exception {
        
        EbayForm f = EbayFormTestFactory.build(owner);
        
        assertTrue(f != null);
        
        assertTrue(f.getId() != null && f.getId().longValue() > 0);
        
    }
    
    public void testSearch() throws Exception {
        
        EbayForm f1 = EbayFormTestFactory.build(FormType.CUSTOMER, owner);
        
        EbayForm f2 = EbayFormTestFactory.build(FormType.CUSTOMER, owner);

        EbayForm f3 = EbayFormTestFactory.build(FormType.ITEM, owner);

        Collection c1 = EbayFormServiceDelegate.factory.build(owner).find(FormType.CUSTOMER);
        
        assertTrue(c1.size() == 2);
        
        Collection c2 = EbayFormServiceDelegate.factory.build(owner).find(FormType.ITEM);
        
        assertTrue(c2.size() == 1);
        
        
    }
    
    public void testOwners() throws Exception {
        
        SystemOwner owner2 = SystemOwnerTestFactory.getOwner();

        EbayForm f1a = EbayFormTestFactory.build(FormType.CUSTOMER, owner2);

        EbayForm f1 = EbayFormTestFactory.build(FormType.CUSTOMER, owner);
        
        EbayForm f2 = EbayFormTestFactory.build(FormType.CUSTOMER, owner);

        EbayForm f3 = EbayFormTestFactory.build(FormType.ITEM, owner);

        Collection c1 = EbayFormServiceDelegate.factory.build(owner).findAll();
        
        assertTrue(c1.size() == 3);

        Collection c2 = EbayFormServiceDelegate.factory.build(owner2).findAll();
        
        assertTrue(c2.size() == 1);

    }

}
