/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class BoxServiceDelegateImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        
        Box box = new Box();
        box.setActive(true);
        box.setCost(new Double(1.1));
        box.setName("name");
        //box.setOwnerKey(owner.getKey());
        box.setLength(new Integer(1));
        box.setWidth(new Integer(1));
        box.setHeight(new Integer(1));
        box.setWeight(new Double(2));
        
        BoxServiceDelegate sd = BoxServiceDelegate.factory.build(owner);
        sd.save(box);
        
        assertTrue(box.getId() != null);
        assertTrue(box.getOwnerKey() != null);
        assertTrue(box.getOwnerKey().equals(owner.getKey()));
    }

}
