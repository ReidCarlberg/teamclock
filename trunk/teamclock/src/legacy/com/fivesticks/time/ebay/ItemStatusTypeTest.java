/*
 * Created on Mar 27, 2005 by REID
 */
package com.fivesticks.time.ebay;

import junit.framework.TestCase;

import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 */
public class ItemStatusTypeTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystemNoDatabase();
    }
    
    public void testCollection() throws Exception {
        
        assertTrue(ItemStatusType.getAll() != null);
        
    }
    
    public void testGetByName() throws Exception {
        
        ItemStatusType target = ItemStatusType.ACTIVE_ITEM;
        
        ItemStatusType test = ItemStatusType.getByName(target.getName());
        
        assertTrue(test == target);
        
    }

}
